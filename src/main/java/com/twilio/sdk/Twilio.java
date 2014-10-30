package com.twilio.sdk;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.ConsumableResponse;
import com.twilio.sdk.http.MockHttpClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

public class Twilio {
    private static String accountSid;
    private static String authToken;
    private static TwilioRestClient restClient;
    private static ListeningExecutorService executorService;
    private static List<ConsumableResponse> mockResponses;
    private static Long mockLowMillis;
    private static Long mockHighMillis;

    public static void init(String accountSid, String authToken) {
        Twilio.setAccountSid(accountSid);
        Twilio.setAuthToken(authToken);
    }

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

    public static void setMockResponses(ConsumableResponse... responses) {
        Twilio.mockResponses = new ArrayList<ConsumableResponse>();
        Collections.addAll(Twilio.mockResponses, responses);
        Twilio.invalidate();
    }

    public static void clearMockResponses() {
        Twilio.mockResponses = null;
        Twilio.invalidate();
    }

    public static void setMockDelay(long millis) {
        Twilio.setMockDelay(millis, millis);
    }

    public static void setMockDelay(long lowMillis, long highMillis) {
        Twilio.mockLowMillis = lowMillis;
        Twilio.mockHighMillis = highMillis;
    }

    public static void clearMockDelay() {
        Twilio.mockLowMillis = null;
        Twilio.mockHighMillis = null;
    }

    public static TwilioRestClient getRestClient() {
        if (Twilio.restClient == null) {
            if (Twilio.accountSid == null || Twilio.authToken == null) {
                throw new RuntimeException("TwilioRestClient was used before AccountSid and AuthToken were set, make sure you call Twilio.init()");
            }

            Twilio.restClient = new TwilioRestClient(Twilio.accountSid, Twilio.authToken);

            if (Twilio.isMockingEnabled()) {
                MockHttpClient mockClient = new MockHttpClient();
                if (Twilio.mockLowMillis != null && Twilio.mockHighMillis != null) {
                    mockClient.setDelay(Twilio.mockLowMillis, Twilio.mockHighMillis);
                }
                mockClient.setResponses(Twilio.mockResponses);
                Twilio.restClient.setHttpClient(mockClient);
            }
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

    private static boolean isMockingEnabled() {
        return Twilio.mockResponses != null && Twilio.mockResponses.size() > 0;
    }
}
