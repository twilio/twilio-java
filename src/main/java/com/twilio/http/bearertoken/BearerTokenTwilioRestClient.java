package com.twilio.http.bearertoken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.twilio.exception.AuthenticationException;
import com.twilio.http.HttpClient;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Response;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


/*
 * Use this BearerToken Rest Client if no authentication is involved in an API.
 */
public class BearerTokenTwilioRestClient {
    public static final int HTTP_STATUS_CODE_CREATED = 201;
    public static final int HTTP_STATUS_CODE_NO_CONTENT = 204;
    public static final int HTTP_STATUS_CODE_OK = 200;
    public static final Predicate<Integer> SUCCESS = i -> i != null && i >= 200 && i < 400;
    @Getter
    private final ObjectMapper objectMapper;
    private final String accessToken;
    @Getter
    private final String region;
    @Getter
    private final String edge;
    @Getter
    private final HttpClient httpClient;
    @Getter
    private final List<String> userAgentExtensions;
    private static final Logger logger = LoggerFactory.getLogger(BearerTokenTwilioRestClient.class);

    private BearerTokenTwilioRestClient(BearerTokenTwilioRestClient.Builder b) {
        this.accessToken = b.accessToken;
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
        private final String accessToken;
        private String region;
        private String edge;
        private HttpClient httpClient;
        private List<String> userAgentExtensions;

        public Builder(String accessToken) {
            this.accessToken = accessToken;
            this.region = System.getenv("TWILIO_REGION");
            this.edge = System.getenv("TWILIO_EDGE");
            userAgentExtensions = new ArrayList<>();
        }

        public BearerTokenTwilioRestClient.Builder region(final String region) {
            this.region = region;
            return this;
        }

        public BearerTokenTwilioRestClient.Builder edge(final String edge) {
            this.edge = edge;
            return this;
        }

        public BearerTokenTwilioRestClient.Builder httpClient(final HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public BearerTokenTwilioRestClient.Builder userAgentExtensions(final List<String> userAgentExtensions) {
            if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
                this.userAgentExtensions = new ArrayList<>(userAgentExtensions);
            }
            return this;
        }

        public BearerTokenTwilioRestClient build() {
            if (this.httpClient == null) {
                this.httpClient = new NetworkHttpClient();
            }
            return new BearerTokenTwilioRestClient(this);
        }
    }
    public Response request(BearerTokenRequest request) {
        
        if (accessToken == null || accessToken.isEmpty()) {
            throw new AuthenticationException("Access Token can not be Null or Empty.");
        }
        
        if (isAccessTokenExpired()) {
            throw new AuthenticationException("Access Token is expired.");
        }
        
        request.setAuth(accessToken);
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

    public boolean isAccessTokenExpired() {
        DecodedJWT jwt = JWT.decode(this.accessToken);
        Date expiresAt = jwt.getExpiresAt();
        // Add a buffer of 30 seconds
        long bufferMilliseconds = 30 * 1000;
        Date bufferExpiresAt = new Date(expiresAt.getTime() - bufferMilliseconds);
        return bufferExpiresAt.before(new Date());
    }

    public void logRequest(final BearerTokenRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug("-- BEGIN Twilio API BearerTokenRequest --");
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

            logger.debug("-- END Twilio API BearerTokenRequest --");
        }
    }
}
