package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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

import static io.jsonwebtoken.SignatureAlgorithm.RS256;

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
     * @param algorithm     Client validation algorithm
     */
    public ValidationClient(final String accountSid,
                            final String credentialSid,
                            final String signingKey,
                            final PrivateKey privateKey,
                            final SignatureAlgorithm algorithm) {
        this(accountSid, credentialSid, signingKey, privateKey, DEFAULT_REQUEST_CONFIG, algorithm);
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
        this(accountSid, credentialSid, signingKey, privateKey, requestConfig, DEFAULT_SOCKET_CONFIG, RS256);
    }

    /**
     * Create a new ValidationClient.
     *
     * @param accountSid    Twilio Account SID
     * @param credentialSid Twilio Credential SID
     * @param signingKey    Twilio Signing key
     * @param privateKey    Private Key
     * @param requestConfig HTTP Request Config
     * @param algorithm     Client validation algorithm
     */
    public ValidationClient(final String accountSid,
                            final String credentialSid,
                            final String signingKey,
                            final PrivateKey privateKey,
                            final RequestConfig requestConfig,
                            final SignatureAlgorithm algorithm) {
        this(accountSid, credentialSid, signingKey, privateKey, requestConfig, DEFAULT_SOCKET_CONFIG, algorithm);
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

         this(accountSid, credentialSid, signingKey, privateKey, requestConfig, socketConfig, RS256);
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
     * @param algorithm     Client validation algorithm
     */
    public ValidationClient(final String accountSid,
                            final String credentialSid,
                            final String signingKey,
                            final PrivateKey privateKey,
                            final RequestConfig requestConfig,
                            final SocketConfig socketConfig,
                            final SignatureAlgorithm algorithm) {

        Collection<BasicHeader> headers = Arrays.asList(
            new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
            new BasicHeader(HttpHeaders.ACCEPT, "application/json"),
            new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8")
        );

        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(socketConfig);
        /* 
         *  Example: Lets say client has one server.
         *  There are 4 servers on edge handling client request.
         *  Each request takes on an average 500ms (2 request per second)
         *  Total number request can be server in a second from a route: 20 * 4 * 2 (DefaultMaxPerRoute * edge servers * request per second)
         */
        connectionManager.setDefaultMaxPerRoute(20);
        connectionManager.setMaxTotal(100);
        client = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .setDefaultHeaders(headers)
            .addInterceptorLast(new ValidationInterceptor(accountSid, credentialSid, signingKey, privateKey, algorithm))
            .setRedirectStrategy(this.getRedirectStrategy())
            .build();
    }

    @Override
    public Response makeRequest(IRequest iRequest) {
        Request request = (Request)iRequest;
        RequestBuilder builder = RequestBuilder.create(request.getMethod().toString())
            .setUri(request.constructURL().toString())
            .setVersion(HttpVersion.HTTP_1_1)
            .setCharset(StandardCharsets.UTF_8);

        if (request.requiresAuthentication()) {
            builder.addHeader(HttpHeaders.AUTHORIZATION, request.getAuthString());
        }

        HttpMethod method = request.getMethod();
        if (method == HttpMethod.POST) {
            // TODO: It will be removed after one RC Release.
            if (request.getContentType() == null) request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
            if (EnumConstants.ContentType.JSON.getValue().equals(request.getContentType().getValue())) {
                HttpEntity entity = new StringEntity(request.getBody(), ContentType.APPLICATION_JSON);
                builder.setEntity(entity);
                builder.addHeader(
                        HttpHeaders.CONTENT_TYPE, EnumConstants.ContentType.JSON.getValue());
            } else {
                builder.addHeader(
                        HttpHeaders.CONTENT_TYPE, EnumConstants.ContentType.FORM_URLENCODED.getValue());
                for (Map.Entry<String, List<String>> entry : request.getPostParams().entrySet()) {
                    for (String value : entry.getValue()) {
                        builder.addParameter(entry.getKey(), value);
                    }
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
