package com.twilio.http;

import com.twilio.exception.ApiException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class CustomHttpClient extends NetworkHttpClient {

    public CustomHttpClient() {
        super();
    }

    @Override
    public Response makeRequest(Request request) {
        HttpMethod method = request.getMethod();
        RequestBuilder builder = RequestBuilder.create(method.toString())
                .setUri(request.constructURL().toString())
                .setVersion(HttpVersion.HTTP_1_1)
                .setCharset(StandardCharsets.UTF_8);
        if (request instanceof Request) {
            Request basicRequest = (Request) request;
            if (basicRequest.requiresAuthentication()) {
                builder.addHeader(HttpHeaders.AUTHORIZATION, basicRequest.getAuthString());
            }
        }

        for (Map.Entry<String, List<String>> entry : request.getHeaderParams().entrySet()) {
            for (String value : entry.getValue()) {
                builder.addHeader(entry.getKey(), value);
            }
        }
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for (Map.Entry<String, List<String>> entry : request.getPostParams().entrySet()) {
            for (String value : entry.getValue()) {
                multipartEntityBuilder.addPart(entry.getKey(), new StringBody(value, ContentType.TEXT_PLAIN));
            }
        }
        builder.addHeader(HttpHeaders.USER_AGENT, HttpUtility.getUserAgentString(request.getUserAgentExtensions(), true));
        builder.setEntity(multipartEntityBuilder.build());
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
            HttpClientUtils.closeQuietly(response);
        }
    }
}
