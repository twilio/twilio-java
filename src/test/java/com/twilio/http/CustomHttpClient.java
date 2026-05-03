package com.twilio.http;

import com.twilio.exception.ApiException;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpVersion;
import org.apache.hc.core5.http.io.entity.BufferedHttpEntity;

public class CustomHttpClient extends NetworkHttpClient {

    public CustomHttpClient() {
        super();
    }

    @Override
    public Response makeRequest(Request request) {
        HttpMethod method = request.getMethod();
        HttpUriRequestBase httpUriRequestBase = createHttpUriRequestBase(request);
        httpUriRequestBase.setVersion(HttpVersion.HTTP_1_1);
        if (request instanceof Request) {
            Request basicRequest = (Request) request;
            if (basicRequest.requiresAuthentication()) {
                httpUriRequestBase.addHeader(HttpHeaders.AUTHORIZATION, basicRequest.getAuthString());
            }
        }

        for (Map.Entry<String, List<String>> entry : request.getHeaderParams().entrySet()) {
            for (String value : entry.getValue()) {
                httpUriRequestBase.addHeader(entry.getKey(), value);
            }
        }
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for (Map.Entry<String, List<String>> entry : request.getPostParams().entrySet()) {
            for (String value : entry.getValue()) {
                multipartEntityBuilder.addPart(entry.getKey(), new StringBody(value, ContentType.TEXT_PLAIN));
            }
        }
        httpUriRequestBase.addHeader(HttpHeaders.USER_AGENT, HttpUtility.getUserAgentString(request.getUserAgentExtensions(), true));
        httpUriRequestBase.setEntity(multipartEntityBuilder.build());
        CloseableHttpResponse response = null;

        try {
            response = client.execute(httpUriRequestBase);
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
