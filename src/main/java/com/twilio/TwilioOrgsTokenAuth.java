package com.twilio;

import com.twilio.annotations.Preview;
import com.twilio.exception.AuthenticationException;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.twilio.http.bearertoken.TokenManager;
import com.twilio.http.bearertoken.OrgsTokenManager;

@Preview
public class TwilioOrgsTokenAuth {
    private static String accessToken;
    @Getter
    private static List<String> userAgentExtensions;
    private static String region = System.getenv("TWILIO_REGION");
    private static String edge = System.getenv("TWILIO_EDGE");
    private static volatile BearerTokenTwilioRestClient restClient;
    @Getter @Setter
    private static TokenManager tokenManager;

    private static volatile ExecutorService executorService;

    private TwilioOrgsTokenAuth() {
    }

    public static synchronized void init(String grantType, String clientId, String clientSecret) {
        validateAuthCredentials(grantType, clientId, clientSecret);
        tokenManager = new OrgsTokenManager(grantType, clientId, clientSecret);
    }
    public static synchronized void init(String grantType, String clientId, String clientSecret, String code, String redirectUri, String audience, String refreshToken, String scope) {
        validateAuthCredentials(grantType, clientId, clientSecret);
        tokenManager = new OrgsTokenManager(grantType, clientId, clientSecret, code, redirectUri, audience, refreshToken, scope);
    }

    private static void validateAuthCredentials(String grantType, String clientId, String clientSecret){
        if (grantType == null) {
            throw new AuthenticationException("Grant Type cannot be null");
        }
        if (clientId == null) {
            throw new AuthenticationException("Client Id cannot be null");
        }
        if (clientSecret == null) {
            throw new AuthenticationException("Client Secret cannot be null");
        }
        return;
    }

    public static BearerTokenTwilioRestClient getRestClient() {
        if (TwilioOrgsTokenAuth.restClient == null) {
            synchronized (TwilioOrgsTokenAuth.class) {
                if (TwilioOrgsTokenAuth.restClient == null) {
                    TwilioOrgsTokenAuth.restClient = buildOAuthRestClient();
                }
            }
        }
        return TwilioOrgsTokenAuth.restClient;
    }
    /**
     * Returns the Twilio executor service.
     *
     * @return the Twilio executor service
     */
    public static ExecutorService getExecutorService() {
        if (TwilioOrgsTokenAuth.executorService == null) {
            synchronized (TwilioOrgsTokenAuth.class) {
                if (TwilioOrgsTokenAuth.executorService == null) {
                    TwilioOrgsTokenAuth.executorService = Executors.newCachedThreadPool();
                }
            }
        }
        return TwilioOrgsTokenAuth.executorService;
    }
    
    private static BearerTokenTwilioRestClient buildOAuthRestClient() {

        BearerTokenTwilioRestClient.Builder builder = new BearerTokenTwilioRestClient.Builder();

        if (userAgentExtensions != null) {
            builder.userAgentExtensions(TwilioOrgsTokenAuth.userAgentExtensions);
        }

        builder.region(TwilioOrgsTokenAuth.region);
        builder.edge(TwilioOrgsTokenAuth.edge);
        if(TwilioOrgsTokenAuth.tokenManager == null){
            throw new AuthenticationException("Either initialize the authentications class or pass a custom token manager");
        }
        builder.tokenManager(TwilioOrgsTokenAuth.tokenManager);

        return builder.build();
    }

    /**
     * Invalidates the volatile state held in the Twilio singleton.
     */
    private static void invalidate() {
        TwilioOrgsTokenAuth.restClient = null;
        TwilioOrgsTokenAuth.tokenManager = null;
    }


}