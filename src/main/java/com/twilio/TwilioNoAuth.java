package com.twilio;

import com.twilio.annotations.Preview;
import com.twilio.http.noauth.NoAuthTwilioRestClient;
import lombok.Getter;

import java.util.List;
import com.twilio.exception.AuthenticationException;

@Preview
public class TwilioNoAuth {
    @Getter
    private static List<String> userAgentExtensions;
    private static String region = System.getenv("TWILIO_REGION");
    private static String edge = System.getenv("TWILIO_EDGE");

    private static volatile NoAuthTwilioRestClient noAuthTwilioRestClient;

    private TwilioNoAuth() {
    }

    public static NoAuthTwilioRestClient getRestClient() {
        if (TwilioNoAuth.noAuthTwilioRestClient == null) {
            synchronized (TwilioNoAuth.class) {
                if (TwilioNoAuth.noAuthTwilioRestClient == null) {
                    TwilioNoAuth.noAuthTwilioRestClient = buildOAuthRestClient();
                }
            }
        }
        return TwilioNoAuth.noAuthTwilioRestClient;
    }

    private static NoAuthTwilioRestClient buildOAuthRestClient() {

        NoAuthTwilioRestClient.Builder builder = new NoAuthTwilioRestClient.Builder();

        if (userAgentExtensions != null) {
            builder.userAgentExtensions(TwilioNoAuth.userAgentExtensions);
        }

        builder.region(TwilioNoAuth.region);
        builder.edge(TwilioNoAuth.edge);

        return builder.build();
    }


}
