package com.twilio.http;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class NetworkHttpClient extends HttpClient {

    public static final int CONNECTION_TIMEOUT = 10000;

    public static final int SOCKET_TIMEOUT = 30500;

    public static final int DEFAULT_MAX_PER_ROUTE = 10;

    public static final int MAX_TOTAL = 20;

    public static final int VALIDATE_AFTER_INACTIVITY = 1000;

    public static final int KEEP_ALIVE_TIMEOUT = 30 * 1000;

    private final org.apache.http.client.HttpClient client;

    /**
     * Create a new HTTP Client.
     */
    public NetworkHttpClient() {
        this(defaultHttpClientBuilder());
    }

    /**
     * Create a new HTTP Client using custom configuration.
     * @param clientBuilder an HttpClientBuilder.
     */
    public NetworkHttpClient(HttpClientBuilder clientBuilder) {
        client = clientBuilder
                .setDefaultHeaders(twilioHeaders())
                .build();
    }

    private static List<Header> twilioHeaders() {
        return Lists.<Header>newArrayList(
                    new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
                    new BasicHeader(
                            HttpHeaders.USER_AGENT, "twilio-java/" + Twilio.VERSION + " (" + Twilio.JAVA_VERSION + ") custom"
                    ),
                    new BasicHeader(HttpHeaders.ACCEPT, "application/json"),
                    new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8")
            );
    }

    public static HttpClientBuilder defaultHttpClientBuilder() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        String googleAppEngineVersion = System.getProperty("com.google.appengine.runtime.version");
        boolean isNotGoogleAppEngine = Strings.isNullOrEmpty(googleAppEngineVersion);

        org.apache.http.impl.client.HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        if (isNotGoogleAppEngine) {
            clientBuilder.useSystemProperties();
        }

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        connectionManager.setMaxTotal(MAX_TOTAL);
        connectionManager.setValidateAfterInactivity(VALIDATE_AFTER_INACTIVITY);

        return clientBuilder
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(getKeepAliveStrategy())
                .setDefaultRequestConfig(config);
    }

    public static ConnectionKeepAliveStrategy getKeepAliveStrategy() {
        return new ConnectionKeepAliveStrategy() {
                @Override
                public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                    // Honor 'keep-alive' header
                    HeaderElementIterator it = new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));

                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout")) {
                            try {
                                return Long.parseLong(value) * 1000;
                            } catch (NumberFormatException ignore) {
                            }
                        }
                    }

                    return KEEP_ALIVE_TIMEOUT;
                }
            };
    }

    /**
     * Make a request.
     *
     * @param request request to make
     * @return Response of the HTTP request
     */
    public Response makeRequest(final Request request) {

        HttpMethod method = request.getMethod();
        RequestBuilder builder = RequestBuilder.create(method.toString())
            .setUri(request.constructURL().toString())
            .setVersion(HttpVersion.HTTP_1_1)
            .setCharset(StandardCharsets.UTF_8);

        if (request.requiresAuthentication()) {
            builder.addHeader(HttpHeaders.AUTHORIZATION, request.getAuthString());
        }

        if (method == HttpMethod.POST) {
            builder.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

            for (Map.Entry<String, List<String>> entry : request.getPostParams().entrySet()) {
                for (String value : entry.getValue()) {
                    builder.addParameter(entry.getKey(), value);
                }
            }
        }

        HttpResponse response = null;

        try {
            response = client.execute(builder.build());
            HttpEntity entity = response.getEntity();
            return new Response(
                // Consume the entire HTTP response before returning the stream
                entity == null ? null : new BufferedHttpEntity(entity).getContent(),
                response.getStatusLine().getStatusCode()
            );
        } catch (IOException e) {
            throw new ApiException(e.getMessage(), e);
        } finally {

            // Ensure this response is properly closed
            HttpClientUtils.closeQuietly(response);

        }

    }
}
