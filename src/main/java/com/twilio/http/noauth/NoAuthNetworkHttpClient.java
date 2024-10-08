package com.twilio.http.noauth;

import com.twilio.Twilio;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;
import com.twilio.http.HttpMethod;
import com.twilio.http.HttpUtility;
import com.twilio.http.Request;
import com.twilio.http.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class NoAuthNetworkHttpClient extends NoAuthHttpClient {

    protected final org.apache.http.client.HttpClient client;
    public NoAuthNetworkHttpClient() {
        this(DEFAULT_REQUEST_CONFIG);
    }

    public NoAuthNetworkHttpClient(final RequestConfig requestConfig) {
        this(requestConfig, DEFAULT_SOCKET_CONFIG);
    }

    public NoAuthNetworkHttpClient(final RequestConfig requestConfig, final SocketConfig socketConfig) {
        Collection<BasicHeader> headers = Arrays.asList(
                new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
                new BasicHeader(HttpHeaders.ACCEPT, "application/json"),
                new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8")
        );

        String googleAppEngineVersion = System.getProperty("com.google.appengine.runtime.version");
        boolean isGoogleAppEngine = googleAppEngineVersion != null && !googleAppEngineVersion.isEmpty();

        org.apache.http.impl.client.HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        if (!isGoogleAppEngine) {
            clientBuilder.useSystemProperties();
        }

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(socketConfig);
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
                .setRedirectStrategy(this.getRedirectStrategy())
                .build();
    }
    
    @Override
    public Response makeRequest(NoAuthRequest request) {
        HttpMethod method = request.getMethod();
        RequestBuilder builder = RequestBuilder.create(method.toString())
                .setUri(request.constructURL().toString())
                .setVersion(HttpVersion.HTTP_1_1)
                .setCharset(StandardCharsets.UTF_8);

        for (Map.Entry<String, List<String>> entry : request.getHeaderParams().entrySet()) {
            for (String value : entry.getValue()) {
                builder.addHeader(entry.getKey(), value);
            }
        }

        if (method != HttpMethod.GET) {
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

        HttpResponse response = null;

        try {
            response = client.execute(builder.build());
            HttpEntity entity = response.getEntity();
            return new Response(
                    // Consume the entire HTTP response before returning the stream
                    entity == null ? null : new BufferedHttpEntity(entity).getContent(),
                    response.getStatusLine().getStatusCode(),
                    response.getAllHeaders()
            );
        } catch (IOException e) {
            throw new ApiException(e.getMessage(), e);
        } finally {

            // Ensure this response is properly closed
            HttpClientUtils.closeQuietly(response);

        }

    }
}
