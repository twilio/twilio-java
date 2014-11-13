package com.twilio.sdk;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.AuthenticationException;
import com.twilio.sdk.http.MockHttpClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

public class Twilio {
    public static final String DATE_TIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss Z";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(Twilio.DATE_TIME_PATTERN)
                                                                              .withZone(DateTimeZone.UTC);

    private static String accountSid;
    private static String authToken;
    private static TwilioRestClient restClient;
    private static ListeningExecutorService executorService;
    private static List<Response> mockResponses;
    private static Long mockLowMillis;
    private static Long mockHighMillis;

    public static void init(final String accountSid, final String authToken) throws AuthenticationException {
        Twilio.setAccountSid(accountSid);
        Twilio.setAuthToken(authToken);
    }

    public static void setAccountSid(final String accountSid) throws AuthenticationException {
        if (accountSid == null) {
            throw new AuthenticationException("AccountSid can not be null");
        }

        if (!accountSid.equals(Twilio.accountSid)) {
            Twilio.invalidate();
        }

        Twilio.accountSid = accountSid;
    }

    public static void setAuthToken(final String authToken) throws AuthenticationException {
        if (authToken == null) {
            throw new AuthenticationException("AuthToken can not be null");
        }

        if (!authToken.equals(Twilio.authToken)) {
            Twilio.invalidate();
        }

        Twilio.authToken = authToken;
    }

    public static void setMockResponses(final Response... responses) {
        Twilio.mockResponses = new ArrayList<>();
        Collections.addAll(Twilio.mockResponses, responses);
        Twilio.invalidate();
    }

    public static void clearMockResponses() {
        Twilio.mockResponses = null;
        Twilio.invalidate();
    }

    public static void setMockDelay(final long millis) {
        Twilio.setMockDelay(millis, millis);
    }

    public static void setMockDelay(final long lowMillis, final long highMillis) {
        Twilio.mockLowMillis = lowMillis;
        Twilio.mockHighMillis = highMillis;
    }

    public static void clearMockDelay() {
        Twilio.mockLowMillis = null;
        Twilio.mockHighMillis = null;
    }

    public static TwilioRestClient getRestClient() throws AuthenticationException {
        if (Twilio.restClient == null) {
            if (Twilio.accountSid == null || Twilio.authToken == null) {
                throw new AuthenticationException(
                        "TwilioRestClient was used before AccountSid and AuthToken were set, make sure you call Twilio.init()");
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

    public static void setRestClient(final TwilioRestClient restClient) {
        Twilio.restClient = restClient;
    }

    public static ListeningExecutorService getExecutorService() {
        if (Twilio.executorService == null) {
            Twilio.executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        }
        return Twilio.executorService;
    }

    public static void setExecutorService(final ListeningExecutorService executorService) {
        Twilio.executorService = executorService;
    }

    public static Request getMockRequest() throws AuthenticationException {
        return Twilio.getMockRequest(0);
    }

    public static Request getMockRequest(final int index) throws AuthenticationException {
        List<Request> requests = Twilio.getMockRequests();
        if (index < requests.size()) {
            return requests.get(index);
        }
        return null;
    }

    public static List<Request> getMockRequests() throws AuthenticationException {
        if (isMockingEnabled() && (Twilio.getRestClient().getHttpClient() instanceof MockHttpClient)) {
            return ((MockHttpClient) Twilio.getRestClient().getHttpClient()).getRequests();
        }

        return new ArrayList<>();
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
