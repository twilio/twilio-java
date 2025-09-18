package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;

import com.twilio.http.IRequest.FormParameters;
import com.twilio.http.IRequest.FormParameters.Type;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
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
import org.apache.hc.core5.http.message.BasicNameValuePair;


public class NetworkHttpClient extends HttpClient {

    protected final CloseableHttpClient client;

    private boolean isCustomClient;

    /**
     * Create a new HTTP Client.
     */
    public NetworkHttpClient() {
        this(DEFAULT_REQUEST_CONFIG);
    }

    /**
     * Create a new HTTP Client with a custom request config.
     *
     * @param requestConfig a RequestConfig.
     */
    public NetworkHttpClient(final RequestConfig requestConfig) {
        this(requestConfig, DEFAULT_SOCKET_CONFIG);
    }

    /**
     * Create a new HTTP Client with a custom request and socket config.
     *
     * @param requestConfig a RequestConfig.
     * @param socketConfig  a SocketConfig.
     */
    public NetworkHttpClient(final RequestConfig requestConfig, final SocketConfig socketConfig) {
        Collection<BasicHeader> headers = Arrays.asList(
            new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
            // The Accept header is intentionally omitted to support both SCIM and JSON content types.
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

    /**
     * Create a new HTTP Client using custom configuration.
     * @param clientBuilder an HttpClientBuilder.
     */
    public NetworkHttpClient(HttpClientBuilder clientBuilder) {
        Collection<BasicHeader> headers = Arrays.asList(
                new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
                new BasicHeader(HttpHeaders.ACCEPT, "application/json"),
                new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8")
        );
        isCustomClient = true;

        client = clientBuilder
            .setDefaultHeaders(headers)
            .setRedirectStrategy(this.getRedirectStrategy())
            .build();
    }

    /**
     * Make a request.
     *
     * @param request request to make
     * @return Response of the HTTP request
     */
    public Response makeRequest(final Request request)  {

        HttpMethod method = request.getMethod();
        HttpUriRequestBase httpUriRequestBase = createHttpUriRequestBase(request);

        httpUriRequestBase.setConfig(DEFAULT_REQUEST_CONFIG);

        httpUriRequestBase.setVersion(HttpVersion.HTTP_1_1);

        if (request.requiresAuthentication()) {
            httpUriRequestBase.addHeader(HttpHeaders.AUTHORIZATION, request.getAuthString());
        }

        for (Entry<String, List<String>> entry : request.getHeaderParams().entrySet()) {
            for (String value : entry.getValue()) {
                httpUriRequestBase.addHeader(entry.getKey(), value);
            }
        }

        if (method != HttpMethod.GET) {
            // TODO: It will be removed after one RC Release.
            if (request.getContentType() == null) request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
            if (EnumConstants.ContentType.JSON.getValue().equals(request.getContentType().getValue())) {
                HttpEntity entity = new StringEntity(request.getBody(), ContentType.APPLICATION_JSON);
                httpUriRequestBase.setEntity(entity);
                httpUriRequestBase.addHeader(
                        HttpHeaders.CONTENT_TYPE, EnumConstants.ContentType.JSON.getValue());
            } else if (EnumConstants.ContentType.MULTIPART_FORM_DATA.getValue().equals(request.getContentType().getValue())) {

                MultipartEntityBuilder httpEntityBuilder = MultipartEntityBuilder.create();
                for( FormParameters formParameter: request.getFormParameters()) {
                    // Create a file to upload.
                    if ( formParameter.getType().equals(Type.TEXT) )
                        httpEntityBuilder.addTextBody(formParameter.getName(), formParameter.getValue().toString());
                    else if ( formParameter.getType().equals(Type.FILE) )
                    {
                        Path path = Paths.get(formParameter.getValue().toString());
                        byte[] fileBytes = null;
                        try{
                            fileBytes = Files.readAllBytes(path);
                        } catch (IOException e) {
                            System.err.println("Failed to read file for upload: " + path + ". " + e.getMessage());
                            throw new ApiException("Failed to read file for upload: " + path, e);
                        }
                        String fileName = path.getFileName().toString();
                        ContentType contentType = getContentType(path);
                        httpEntityBuilder.addBinaryBody(formParameter.getName(), fileBytes, contentType, fileName);
                    }
                }
                httpUriRequestBase.setEntity(httpEntityBuilder.build());
            }
            else {
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
        httpUriRequestBase.addHeader(HttpHeaders.USER_AGENT, HttpUtility.getUserAgentString(request.getUserAgentExtensions(), isCustomClient));


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
