package com.twilio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.twilio.Twilio;
import com.twilio.auth_strategy.AuthStrategy;
import com.twilio.auth_strategy.NoAuthStrategy;
import com.twilio.constant.EnumConstants;
import com.twilio.type.RegionEndpoints;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import lombok.Getter;
import org.apache.hc.core5.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The `TwilioRestClient` class is responsible for making HTTP requests to the Twilio API.
 * It provides methods to configure authentication, region, edge, and other settings
 * required to interact with Twilio's services.
 *
 * <p>Features:</p>
 * <ul>
 *   <li>Supports basic authentication and token-based authentication strategies.</li>
 *   <li>Allows configuration of custom HTTP clients and ObjectMapper for JSON processing.</li>
 *   <li>Handles request retries for specific HTTP status codes (e.g., 401 Unauthorized).</li>
 *   <li>Logs detailed request and response information for debugging purposes.</li>
 * </ul>
 *
 * <p>Usage Example:</p>
 * <pre>
 * {@code
 * TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN)
 *           .objectMapper(customMapper)
 *           .build();
 *
 * Message message = Message
 *           .creator(
 *               new PhoneNumber("+1xxxxxxxxxx"),
 *               new PhoneNumber("+1xxxxxxxxxx"),
 *               "This is the ship that made the Kessel Run in fourteen parsecs?"
 *           ).create(client);
 * }
 * </pre>
 *
 * <p>Note: This class is designed to be thread-safe and reusable.</p>
 */
public class TwilioRestClient {

    public static final int HTTP_STATUS_CODE_CREATED = 201;
    public static final int HTTP_STATUS_CODE_NO_CONTENT = 204;
    public static final int HTTP_STATUS_CODE_UNAUTHORIZED = 401;
    public static final int HTTP_STATUS_CODE_OK = 200;
    public static final Predicate<Integer> SUCCESS = i -> i != null && i >= 200 && i < 400;

    @Getter
    private AuthStrategy authStrategy;
    @Getter
    private final ObjectMapper objectMapper;
    private final String username;
    private final String password;
    @Getter
    private final String accountSid;
    @Getter
    private final String region;
    @Getter
    private final String edge;
    @Getter
    private final HttpClient httpClient;
    @Getter
    private final List<String> userAgentExtensions;
    private static final Logger logger = LoggerFactory.getLogger(TwilioRestClient.class);
    private static Map<String, String> regionMap = RegionEndpoints.getRegions();

    protected TwilioRestClient(Builder b) {
        this.username = b.username;
        this.password = b.password;
        this.authStrategy = b.authStrategy;
        this.accountSid = b.accountSid;
        this.region = b.region;
        if(b.edge == null && b.region != null) {
            logger.warn(
                "Setting default `Edge` for the provided `region`. For regional processing, DNS is of format product.<city>.<region>.twilio.com; otherwise use product.twilio.com."
            );
            this.edge = regionMap.get(this.region);
        }
        else
            this.edge = b.edge;
        this.httpClient = b.httpClient;
        this.objectMapper = b.objectMapper;
        this.userAgentExtensions = b.userAgentExtensions;
    }

    /**
     * Make a request to Twilio.
     *
     * @param request request to make
     * @return Response object
     */
    public Response request(final Request request) {
        // If authStrategy is passed from NoAuth API, no need to set authStrategy (ex TokenCreator).
        if (request.getAuthStrategy() == null) {
            if (username != null && password != null) {
                request.setAuth(username, password);
            } else if (authStrategy != null) {
                request.setAuth(authStrategy);
            }
        }

        if (region != null)
            request.setRegion(region);
        if (edge != null)
            request.setEdge(edge);

        // If NoAuthStrategy is used, clear region and edge as Token endpoint does not support region/edge.
        if (request.getAuthStrategy() != null && request.getAuthStrategy() instanceof NoAuthStrategy) {
            request.setRegion(null);
            request.setEdge(null);
        }


        if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
            request.setUserAgentExtensions(userAgentExtensions);
        }

        logRequest(request);
        Response response = httpClient.reliableRequest(request);
        if(response != null) {
            int statusCode = response.getStatusCode();
            if (statusCode == HTTP_STATUS_CODE_UNAUTHORIZED && authStrategy != null && EnumConstants.AuthType.TOKEN.equals(authStrategy.getAuthType())) {
                // Retry only once
                response = httpClient.reliableRequest(request);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("status code: {}", statusCode);
                Header[] responseHeaders = response.getHeaders();
                logger.debug("response headers:");
                for (int i = 0; i < responseHeaders.length; i++) {
                    logger.debug("responseHeader: {}", responseHeaders[i]);
                }
            }
        }

        return response;
    }

    public static class Builder {
        // This module configures the ObjectMapper to use
        // public API methods for manipulating java.time.*
        // classes. The alternative is to use reflection which
        // generates warnings from the module system on Java 9+
        private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

        private String username;
        private String password;
        private AuthStrategy authStrategy;
        private String accountSid;
        private String region;
        private String edge;
        private HttpClient httpClient;
        private List<String> userAgentExtensions;
        private ObjectMapper objectMapper = DEFAULT_OBJECT_MAPPER;

        /**
         * Create a new Twilio Rest Client.
         *
         * @param username username to use
         * @param password password for the username
         */
        public Builder(final String username, final String password) {
            this.username = username;
            this.password = password;
            this.accountSid = username;
        }

        /**
         * Create a new Twilio Rest Client.
         *
         * @param authStrategy authStrategy to use
         */
        public Builder(final AuthStrategy authStrategy) {
            this.authStrategy = authStrategy;
        }

        public Builder accountSid(final String accountSid) {
            this.accountSid = accountSid;
            return this;
        }

        public Builder region(final String region) {
            this.region = region;
            return this;
        }

        public Builder edge(final String edge) {
            this.edge = edge;
            return this;
        }

        public Builder httpClient(final HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder userAgentExtensions(final List<String> userAgentExtensions) {
            if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
                this.userAgentExtensions = new ArrayList<>(userAgentExtensions);
            }
            return this;
        }

        public Builder objectMapper(final ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        /**
         * Build new TwilioRestClient.
         *
         * @return TwilioRestClient instance
         */
        public TwilioRestClient build() {
            if (this.httpClient == null) {
                this.httpClient = new NetworkHttpClient();
            }
            return new TwilioRestClient(this);
        }
    }

    /**
     * Logging debug information about HTTP request.
     */
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
