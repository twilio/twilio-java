package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ValidationClient extends HttpClient {

    private final org.apache.http.client.HttpClient client;

    /**
     * Create a new ValidationClient.
     *
     * @param accountSid    Twilio Account SID
     * @param credentialSid Twilio Credential SID
     * @param signingKey    Twilio Signing key
     * @param privateKey    Private Key
     */
    public ValidationClient(final String accountSid,
                            final String credentialSid,
                            final String signingKey,
                            final PrivateKey privateKey) {
        this(accountSid, credentialSid, signingKey, privateKey, DEFAULT_REQUEST_CONFIG);
    }

    /**
     * Create a new ValidationClient.
     *
     * @param accountSid    Twilio Account SID
     * @param credentialSid Twilio Credential SID
     * @param signingKey    Twilio Signing key
     * @param privateKey    Private Key
     * @param requestConfig HTTP Request Config
     */
    public ValidationClient(final String accountSid,
                            final String credentialSid,
                            final String signingKey,
                            final PrivateKey privateKey,
                            final RequestConfig requestConfig) {
        this(accountSid, credentialSid, signingKey, privateKey, requestConfig, DEFAULT_SOCKET_CONFIG);
    }

    /**
     * Create a new ValidationClient.
     *
     * @param accountSid    Twilio Account SID
     * @param credentialSid Twilio Credential SID
     * @param signingKey    Twilio Signing key
     * @param privateKey    Private Key
     * @param requestConfig HTTP Request Config
     * @param socketConfig  HTTP Socket Config
     */
    public ValidationClient(final String accountSid,
                            final String credentialSid,
                            final String signingKey,
                            final PrivateKey privateKey,
                            final RequestConfig requestConfig,
                            final SocketConfig socketConfig) {
        Collection<BasicHeader> headers = Arrays.asList(
            new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
            new BasicHeader(HttpHeaders.ACCEPT, "application/json"),
            new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8")
        );

        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(socketConfig);

        client = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .setDefaultHeaders(headers)
            .setMaxConnPerRoute(10)
            .addInterceptorLast(new ValidationInterceptor(accountSid, credentialSid, signingKey, privateKey))
            .setRedirectStrategy(this.getRedirectStrategy())
            .build();
    }

    @Override
    public Response makeRequest(Request request) {
        RequestBuilder builder = RequestBuilder.create(request.getMethod().toString())
            .setUri(request.constructURL().toString())
            .setVersion(HttpVersion.HTTP_1_1)
            .setCharset(StandardCharsets.UTF_8);

        if (request.requiresAuthentication()) {
            builder.addHeader(HttpHeaders.AUTHORIZATION, request.getAuthString());
        }

        HttpMethod method = request.getMethod();
        if (method == HttpMethod.POST) {
            builder.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

            for (Map.Entry<String, List<String>> entry : request.getPostParams().entrySet()) {
                for (String value : entry.getValue()) {
                    builder.addParameter(entry.getKey(), value);
                }
            }
        }

        builder.addHeader(HttpHeaders.USER_AGENT, HttpUtility.getUserAgentString(request.getUserAgentExtensions()));

        try {
            HttpResponse response = client.execute(builder.build());
            return new Response(
                response.getEntity() == null ? null : response.getEntity().getContent(),
                response.getStatusLine().getStatusCode(),
                response.getAllHeaders()
            );
        } catch (IOException e) {
            throw new ApiException(e.getMessage(), e);
        }
    }
}
