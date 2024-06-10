package com.twilio;

import com.twilio.exception.AuthenticationException;
import com.twilio.http.TwilioOAuthRestClient;
import lombok.Getter;

import java.util.List;

public class TwilioOauth {
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_ARCH = System.getProperty("os.arch");
    private static String clientId = System.getenv("TWILIO_CLIENT_ID");
    private static String clientSecret = System.getenv("TWILIO_CLIENT_SECRET");
    @Getter
    private static List<String> userAgentExtensions;
    private static String region = System.getenv("TWILIO_REGION");
    private static String edge = System.getenv("TWILIO_EDGE");

    private static volatile TwilioOAuthRestClient oAuthRestClient;
    
    private TwilioOauth() {
    }
    public static synchronized void init(final String clientId, final String clientSecret) {
        TwilioOauth.setClientId(clientId);
        TwilioOauth.setClientSecret(clientSecret);
    }

    public static synchronized void setClientId(final String clientId) {
        if (clientId == null) {
            throw new AuthenticationException("Client Id can not be null");
        }

        TwilioOauth.clientId = clientId;
    }

    public static synchronized void setClientSecret(final String clientSecret) {
        if (clientSecret == null) {
            throw new AuthenticationException("Client Secret can not be null");
        }

        TwilioOauth.clientSecret = clientSecret;
    }

    public static TwilioOAuthRestClient getRestClient() {
        if (TwilioOauth.oAuthRestClient == null) {
            synchronized (TwilioOauth.class) {
                if (TwilioOauth.oAuthRestClient == null) {
                    TwilioOauth.oAuthRestClient = buildOAuthRestClient();
                }
            }
        }

        return TwilioOauth.oAuthRestClient;
    }

    private static TwilioOAuthRestClient buildOAuthRestClient() {
        if (TwilioOauth.clientId == null || TwilioOauth.clientSecret == null) {
            throw new AuthenticationException(
                    "TwilioOAuthRestClient was used before ClientId and ClientSecret were set, please call TwilioOauth.init()"
            );
        }

        TwilioOAuthRestClient.Builder builder = new TwilioOAuthRestClient.Builder(TwilioOauth.clientId, TwilioOauth.clientSecret);

        if (userAgentExtensions != null) {
            builder.userAgentExtensions(TwilioOauth.userAgentExtensions);
        }

        builder.region(TwilioOauth.region);
        builder.edge(TwilioOauth.edge);

        return builder.build();
    }


}
