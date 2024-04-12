package com.twilio.http.noauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.twilio.http.HttpClient;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Response;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*
 * Use this NoAuth Rest Client if no authentication is involved in an API.
 */
public class NoAuthTwilioRestClient {
    @Getter
    private final ObjectMapper objectMapper;
    
    @Getter
    private final String region;
    @Getter
    private final String edge;
    @Getter
    private final HttpClient httpClient;
    @Getter
    private final List<String> userAgentExtensions;
    private static final Logger logger = LoggerFactory.getLogger(NoAuthTwilioRestClient.class);

    private NoAuthTwilioRestClient(NoAuthTwilioRestClient.Builder b) {
        this.region = b.region;
        this.edge = b.edge;
        this.httpClient = b.httpClient;
        this.objectMapper = new ObjectMapper();
        this.userAgentExtensions = b.userAgentExtensions;

        // This module configures the ObjectMapper to use
        // public API methods for manipulating java.time.*
        // classes. The alternative is to use reflection which
        // generates warnings from the module system on Java 9+
        objectMapper.registerModule(new JavaTimeModule());
    }
    
    public static class Builder {
        private String region;
        private String edge;
        private HttpClient httpClient;
        private List<String> userAgentExtensions;

        public Builder() {
            this.region = System.getenv("TWILIO_REGION");
            this.edge = System.getenv("TWILIO_EDGE");
            userAgentExtensions = new ArrayList<>();
        }

        public NoAuthTwilioRestClient.Builder region(final String region) {
            this.region = region;
            return this;
        }

        public NoAuthTwilioRestClient.Builder edge(final String edge) {
            this.edge = edge;
            return this;
        }

        public NoAuthTwilioRestClient.Builder httpClient(final HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public NoAuthTwilioRestClient.Builder userAgentExtensions(final List<String> userAgentExtensions) {
            if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
                this.userAgentExtensions = new ArrayList<>(userAgentExtensions);
            }
            return this;
        }

        public NoAuthTwilioRestClient build() {
            if (this.httpClient == null) {
                this.httpClient = new NetworkHttpClient();
            }
            return new NoAuthTwilioRestClient(this);
        }
    }
    public Response request(NoAuthRequest request) {
        if (region != null)
            request.setRegion(region);
        if (edge != null)
            request.setEdge(edge);

        if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
            request.setUserAgentExtensions(userAgentExtensions);
        }
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

    public void logRequest(final NoAuthRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug("-- BEGIN Twilio API NoAuthRequest --");
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

            logger.debug("-- END Twilio API NoAuthRequest --");
        }
    }
}
