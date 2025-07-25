package com.twilio.http.noauth;

import com.twilio.Twilio;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;
import com.twilio.http.HttpMethod;
import com.twilio.http.HttpUtility;
import com.twilio.http.Response;
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
import org.apache.hc.core5.http.HttpVersion;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.BufferedHttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.hc.core5.http.message.BasicNameValuePair;

public class NoAuthNetworkHttpClient extends NoAuthHttpClient {

    protected final CloseableHttpClient client;

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

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

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
        HttpUriRequestBase httpUriRequestBase = null;
        switch (request.getMethod().toString().toUpperCase()) {
            case "POST": httpUriRequestBase = new HttpPost(request.constructURL().toString()); break;
            case "PUT": httpUriRequestBase = new HttpPut(request.constructURL().toString()); break;
            case "PATCH": httpUriRequestBase = new HttpPatch(request.constructURL().toString()); break;
            case "DELETE": httpUriRequestBase = new HttpDelete(request.constructURL().toString()); break;
            case "GET": httpUriRequestBase = new HttpGet(request.constructURL().toString()); break;
        }

        httpUriRequestBase.setConfig(DEFAULT_REQUEST_CONFIG);

        httpUriRequestBase.setVersion(HttpVersion.HTTP_1_1);

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
            HttpEntity entity = response.getEntity();
            return new Response(
                    // Consume the entire HTTP response before returning the stream
                    entity == null ? null : new BufferedHttpEntity(entity).getContent(),
                    response.getCode(),
                response.getHeaders()
            );
        } catch (IOException e) {
            throw new ApiException(e.getMessage(), e);
        }
    }
}
