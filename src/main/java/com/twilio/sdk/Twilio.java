package com.twilio.sdk;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.sdk.clients.TwilioRestClient;

import java.util.concurrent.Executors;

public class Twilio {
    private static String accountSid;
    private static String authToken;
    private static TwilioRestClient restClient;
    private static ListeningExecutorService executorService;

    public static void setAccountSid(String accountSid) {
        if (accountSid == null) {
            throw new RuntimeException("AccountSid can not be null");
        }

        if (!accountSid.equals(Twilio.accountSid)) {
            Twilio.invalidate();
        }

        Twilio.accountSid = accountSid;
    }

    public static void setAuthToken(String authToken) {
        if (authToken == null) {
            throw new RuntimeException("AuthToken can not be null");
        }

        if (!authToken.equals(Twilio.authToken)) {
            Twilio.invalidate();
        }

        Twilio.authToken = authToken;
    }

    public static TwilioRestClient getRestClient() {
        if (Twilio.restClient == null) {
            if (Twilio.accountSid == null || Twilio.authToken == null) {
                throw new RuntimeException("TwilioRestClient was used before AccountSid and AuthToken were set, make sure you call Twilio.setAccountSid() and Twilio.setAuthToken");
            }
            Twilio.restClient = new TwilioRestClient(Twilio.accountSid, Twilio.authToken);
        }

        return Twilio.restClient;
    }

    public static void setRestClient(TwilioRestClient restClient) {
        Twilio.restClient = restClient;
    }

    public static ListeningExecutorService getExecutorService() {
        if (Twilio.executorService == null) {
            Twilio.executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        }
        return Twilio.executorService;
    }

    public static void setExecutorService(ListeningExecutorService executorService) {
        Twilio.executorService = executorService;
    }

    /**
     * Invalidates the volatile state held in the Twilio singleton
     */
    private static void invalidate() {
        Twilio.restClient = null;
    }
}
