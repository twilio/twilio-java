package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Base implementation for network HTTP clients using Apache HttpClient 5.
 * This class provides common functionality to reduce code duplication across different client types.
 */
public abstract class BaseNetworkHttpClient extends com.twilio.http.HttpClient {

    protected final HttpClient client;
    private boolean isCustomClient;

    /**
     * Create a new HTTP Client.
     */
    public BaseNetworkHttpClient() {
        this(DEFAULT_REQUEST_CONFIG);
    }

    /**
     * Create a new HTTP Client with a custom request config.
     *
     * @param requestConfig a RequestConfig.
     */
    public BaseNetworkHttpClient(final RequestConfig requestConfig) {
        Collection<BasicHeader> headers = getDefaultHeaders();

        String googleAppEngineVersion = System.getProperty("com.google.appengine.runtime.version");
        boolean isGoogleAppEngine = googleAppEngineVersion != null && !googleAppEngineVersion.isEmpty();

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        if (!isGoogleAppEngine) {
            clientBuilder.useSystemProperties();
        }

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        /*
         *  Example: Lets say client has one server.
         *  There are 4 servers on edge handling client request.
         *  Each request takes on an average 500ms (2 request per second)
         *  Total number request can be server in a second from a route: 20 * 4 * 2 (DefaultMaxPerRoute * edge servers * request per second)
         */
        connectionManager.setDefaultMaxPerRoute(20);
        connectionManager.setMaxTotal(100);

        client = clientBuilder
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .setDefaultHeaders(headers)
            .disableRedirectHandling() // Equivalent to empty redirect strategy
            .build();
    }

    /**
     * Create a new HTTP Client using custom configuration.
     * @param clientBuilder an HttpClientBuilder.
     */
    public BaseNetworkHttpClient(HttpClientBuilder clientBuilder) {
        Collection<BasicHeader> headers = getDefaultHeaders();
        isCustomClient = true;

        client = clientBuilder
            .setDefaultHeaders(headers)
            .disableRedirectHandling() // Equivalent to empty redirect strategy
            .build();
    }

    /**
     * Get the default headers for this client type.
     * Subclasses can override this to provide different headers.
     */
    protected Collection<BasicHeader> getDefaultHeaders() {
        return Arrays.asList(
            new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
            new BasicHeader(HttpHeaders.ACCEPT, "application/json"),
            new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8")
        );
    }

    /**
     * Build the HTTP request based on the request parameters.
     */
    public ClassicHttpRequest buildHttpRequest(final IRequest request) {
        HttpMethod method = request.getMethod();
        ClassicRequestBuilder builder = ClassicRequestBuilder.create(method.toString())
            .setUri(request.constructURL().toString())
            .setCharset(StandardCharsets.UTF_8);

        // Add authentication if required
        addAuthentication(builder, request);

        // Add headers
        for (Map.Entry<String, List<String>> entry : request.getHeaderParams().entrySet()) {
            for (String value : entry.getValue()) {
                builder.addHeader(entry.getKey(), value);
            }
        }

        // Add body for non-GET requests
        if (method != HttpMethod.GET) {
            addRequestBody(builder, request);
        }

        // Add user agent
        builder.addHeader(HttpHeaders.USER_AGENT, HttpUtility.getUserAgentString(request.getUserAgentExtensions(), isCustomClient));

        return builder.build();
    }

    /**
     * Add authentication to the request. Subclasses can override for different auth strategies.
     */
    protected abstract void addAuthentication(ClassicRequestBuilder builder, IRequest request);

    /**
     * Add the request body for non-GET requests.
     */
    protected void addRequestBody(ClassicRequestBuilder builder, IRequest request) {
        // Set default content type if not set
        if (request.getContentType() == null) {
            request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        }

        if (EnumConstants.ContentType.JSON.getValue().equals(request.getContentType().getValue())) {
            // JSON body
            HttpEntity entity = new StringEntity(request.getBody(), ContentType.APPLICATION_JSON);
            builder.setEntity(entity);
            builder.addHeader(HttpHeaders.CONTENT_TYPE, EnumConstants.ContentType.JSON.getValue());
        } else if (EnumConstants.ContentType.MULTIPART_FORM_DATA.getValue().equals(request.getContentType().getValue())) {
            // Multipart form data
            MultipartEntityBuilder multipartBuilder = MultipartEntityBuilder.create();
            for (Map.Entry<String, List<String>> entry : request.getPostParams().entrySet()) {
                for (String value : entry.getValue()) {
                    multipartBuilder.addTextBody(entry.getKey(), value);
                }
            }
            builder.setEntity(multipartBuilder.build());
        } else {
            // Form URL encoded (default)
            builder.addHeader(HttpHeaders.CONTENT_TYPE, EnumConstants.ContentType.FORM_URLENCODED.getValue());
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : request.getPostParams().entrySet()) {
                for (String value : entry.getValue()) {
                    params.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
            if (!params.isEmpty()) {
                builder.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            }
        }
    }

    /**
     * Execute the HTTP request and return a Response.
     */
    public Response executeRequest(ClassicHttpRequest httpRequest) {
        try (CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpRequest)) {
            HttpEntity entity = response.getEntity();
            InputStream content = null;
            if (entity != null) {
                content = entity.getContent();
                // Consume the entity to ensure it's fully read
                EntityUtils.consume(entity);
            }
            
            return new Response(
                content,
                response.getCode(),
                response.getHeaders()
            );
        } catch (IOException e) {
            throw new ApiException(e.getMessage(), e);
        }
    }
}