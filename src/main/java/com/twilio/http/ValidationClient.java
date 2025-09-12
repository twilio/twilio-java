package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Map.Entry;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import static io.jsonwebtoken.SignatureAlgorithm.RS256;

public class ValidationClient extends HttpClient {

    private final CloseableHttpClient client;

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
            .addRequestInterceptorLast(new ValidationInterceptor(accountSid, credentialSid, signingKey, privateKey, algorithm))
            .setRedirectStrategy(this.getRedirectStrategy())
            .build();
    }

    @Override
    public Response makeRequest(Request request) {
        HttpMethod method = request.getMethod();
        HttpUriRequestBase httpUriRequestBase = createHttpUriRequestBase(request);

        if (request.requiresAuthentication()) {
            httpUriRequestBase.addHeader(HttpHeaders.AUTHORIZATION, request.getAuthString());
        }

        if (method != HttpMethod.GET) {
            // TODO: It will be removed after one RC Release.
            if (request.getContentType() == null) request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
            if (EnumConstants.ContentType.JSON.getValue().equals(request.getContentType().getValue())) {
                HttpEntity entity = new StringEntity(request.getBody(), ContentType.APPLICATION_JSON);
                httpUriRequestBase.setEntity(entity);
                httpUriRequestBase.addHeader(
                        HttpHeaders.CONTENT_TYPE, EnumConstants.ContentType.JSON.getValue());
            } else {
                httpUriRequestBase.addHeader(
                        HttpHeaders.CONTENT_TYPE, EnumConstants.ContentType.FORM_URLENCODED.getValue());
                // Create your form parameters
                List<NameValuePair> formParams = new ArrayList<>();
                for ( Entry<String, List<String>> entry : request.getPostParams().entrySet()) {
                    for (String value : entry.getValue()) {
                        formParams.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }

                // Build the entity with URL form encoded parameters
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, StandardCharsets.UTF_8);
                // Set the entity on the request
                httpUriRequestBase.setEntity(formEntity);
            }
        }

        httpUriRequestBase.addHeader(HttpHeaders.USER_AGENT, HttpUtility.getUserAgentString(request.getUserAgentExtensions()));

        try {
            CloseableHttpResponse response = client.execute(httpUriRequestBase);
            return new Response(
                response.getEntity() == null ? null : response.getEntity().getContent(),
                response.getCode(),
                response.getHeaders()
            );
        } catch (IOException e) {
            throw new ApiException(e.getMessage(), e);
        }
    }
}
