package com.twilio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.models.OAuthRequestBody;
import com.twilio.rest.content.v1.Content;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class TwilioOAuthRestClient {
    public static final int HTTP_STATUS_CODE_CREATED = 201;
    public static final int HTTP_STATUS_CODE_NO_CONTENT = 204;
    public static final int HTTP_STATUS_CODE_OK = 200;
    public static final Predicate<Integer> SUCCESS = i -> i != null && i >= 200 && i < 400;
    @Getter
    private final ObjectMapper objectMapper;
    @Getter
    private final String region;
    @Getter
    private final String edge;
    
    private OAuthRequestBody oAuthRequestBody;
    
    private static final Logger logger = LoggerFactory.getLogger(TwilioOAuthRestClient.class);
    
    @Getter
    private final HttpClient httpClient;
    @Getter
    private final List<String> userAgentExtensions;
    protected TwilioOAuthRestClient(Builder builder) {
        this.oAuthRequestBody = builder.oAuthModel;
        this.region = builder.region;
        this.edge = builder.edge;
        this.httpClient = builder.httpClient;
        this.objectMapper = new ObjectMapper();
        this.userAgentExtensions = builder.userAgentExtensions;
    }
    public static class Builder {
        private OAuthRequestBody oAuthModel;
        private String region;
        private String edge;
        private HttpClient httpClient;
        private List<String> userAgentExtensions;

        public Builder(final String clientId, final String clientSecret) {
            this.oAuthModel = new OAuthRequestBody(clientId, clientSecret);
        }

        public TwilioOAuthRestClient.Builder region(final String region) {
            this.region = region;
            return this;
        }

        public TwilioOAuthRestClient.Builder edge(final String edge) {
            this.edge = edge;
            return this;
        }

        public TwilioOAuthRestClient.Builder httpClient(final HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public TwilioOAuthRestClient.Builder userAgentExtensions(final List<String> userAgentExtensions) {
            if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
                this.userAgentExtensions = new ArrayList<>(userAgentExtensions);
            }
            return this;
        }
        
        public TwilioOAuthRestClient build() {
            if (this.httpClient == null) {
                this.httpClient = new OAuthHttpClient();
            }
            return new TwilioOAuthRestClient(this);
        }
    }

    public Response request(final OAuthRequest request) {

        buildRequest(request);
        logRequest(request);
        Response response = httpClient.reliableRequest(request);

        if (logger.isDebugEnabled()) {
            logger.debug("status code: {}", response.getStatusCode());
            org.apache.http.Header[] responseHeaders = response.getHeaders();
            logger.debug("response headers:");
            for (int i = 0; i < responseHeaders.length; i++) {
                logger.debug("responseHeader: {}", responseHeaders[i]);
            }
        }
        return response;
    }
    
    private void buildRequest(OAuthRequest request) {
        request.setBody(Content.toJson(oAuthRequestBody, objectMapper));
        if (region != null)
            request.setRegion(region);
        if (edge != null)
            request.setEdge(edge);

        if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
            request.setUserAgentExtensions(userAgentExtensions);
        }
    }
    
    private void handleOAuthResponse(Response response) {
        if (response == null) {
            throw new ApiConnectionException(
                    "Content creation failed: Unable to connect to server"
            );
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(
                    response.getStream(),
                    objectMapper
            );
            if (restException == null) {
                throw new ApiException(
                        "Server Error, no content",
                        response.getStatusCode()
                );
            }
            throw new ApiException(restException);
        }
    }



    public void logRequest(final Request request) {
        if (logger.isDebugEnabled()) {
            logger.debug("-- BEGIN Twilio API Request --");
            logger.debug("request method: " + request.getMethod());
            logger.debug("request URL: " + request.constructURL().toString());
            final Map<String, List<String>> queryParams = request.getQueryParams();
            final Map<String, List<String>> headerParams = request.getHeaderParams();

            if (queryParams != null && !queryParams.isEmpty()) {
                logger.debug("query parameters: " + queryParams);
            }

            if (headerParams != null && !headerParams.isEmpty()) {
                logger.debug("header parameters: ");
                for (String key : headerParams.keySet()) {
                    if (!key.toLowerCase().contains("authorization")) {
                        logger.debug(key + ": " + headerParams.get(key));
                    }
                }
            }

            logger.debug("-- END Twilio API Request --");
        }
    }
}
