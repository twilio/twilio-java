package com.twilio;

import com.twilio.annotations.Preview;
import com.twilio.exception.AuthenticationException;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Preview
public class TwilioBearerTokenAuth {
    private static String accessToken;
    @Getter
    private static List<String> userAgentExtensions;
    private static String region = System.getenv("TWILIO_REGION");
    private static String edge = System.getenv("TWILIO_EDGE");
    private static volatile BearerTokenTwilioRestClient restClient;

    private static volatile ExecutorService executorService;

    private TwilioBearerTokenAuth() {
    }

    public static synchronized void init(final String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new AuthenticationException("Access Token can not be null or Empty");
        }
        if (!accessToken.equals(TwilioBearerTokenAuth.accessToken)) {
            TwilioBearerTokenAuth.invalidate();
        }
        TwilioBearerTokenAuth.accessToken = accessToken;
    }

    public static BearerTokenTwilioRestClient getRestClient() {
        if (TwilioBearerTokenAuth.restClient == null) {
            synchronized (TwilioBearerTokenAuth.class) {
                if (TwilioBearerTokenAuth.restClient == null) {
                    TwilioBearerTokenAuth.restClient = buildOAuthRestClient();
                }
            }
        }
        return TwilioBearerTokenAuth.restClient;
    }
    /**
     * Returns the Twilio executor service.
     *
     * @return the Twilio executor service
     */
    public static ExecutorService getExecutorService() {
        if (TwilioBearerTokenAuth.executorService == null) {
            synchronized (TwilioBearerTokenAuth.class) {
                if (TwilioBearerTokenAuth.executorService == null) {
                    TwilioBearerTokenAuth.executorService = Executors.newCachedThreadPool();
                }
            }
        }
        return TwilioBearerTokenAuth.executorService;
    }
    
    private static BearerTokenTwilioRestClient buildOAuthRestClient() {

        BearerTokenTwilioRestClient.Builder builder = new BearerTokenTwilioRestClient.Builder(accessToken);

        if (userAgentExtensions != null) {
            builder.userAgentExtensions(TwilioBearerTokenAuth.userAgentExtensions);
        }

        builder.region(TwilioBearerTokenAuth.region);
        builder.edge(TwilioBearerTokenAuth.edge);

        return builder.build();
    }

    /**
     * Invalidates the volatile state held in the Twilio singleton.
     */
    private static void invalidate() {
        TwilioBearerTokenAuth.restClient = null;
    }


}