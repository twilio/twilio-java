package com.twilio.sdk;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.AuthenticationException;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.Executors;

public class Twilio {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss Z";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern(Twilio.DATE_PATTERN)
                                                                         .withZone(DateTimeZone.UTC);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(Twilio.DATE_TIME_PATTERN)
                                                                              .withZone(DateTimeZone.UTC);

    private static String accountSid;
    private static String authToken;
    private static TwilioRestClient restClient;
    private static ListeningExecutorService executorService;

    private Twilio() {

    }

    public static void init(final String accountSid, final String authToken) {
        Twilio.setAccountSid(accountSid);
        Twilio.setAuthToken(authToken);
    }

    public static void setAccountSid(final String accountSid) {
        if (accountSid == null) {
            throw new AuthenticationException("AccountSid can not be null");
        }

        if (!accountSid.equals(Twilio.accountSid)) {
            Twilio.invalidate();
        }

        Twilio.accountSid = accountSid;
    }

    public static void setAuthToken(final String authToken) {
        if (authToken == null) {
            throw new AuthenticationException("AuthToken can not be null");
        }

        if (!authToken.equals(Twilio.authToken)) {
            Twilio.invalidate();
        }

        Twilio.authToken = authToken;
    }

    public static TwilioRestClient getRestClient() {
        if (Twilio.restClient == null) {
            if (Twilio.accountSid == null || Twilio.authToken == null) {
                throw new AuthenticationException(
                        "TwilioRestClient was used before AccountSid and AuthToken were set, make sure you call Twilio.init()");
            }

            Twilio.restClient = new TwilioRestClient(Twilio.accountSid, Twilio.authToken);
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

    /**
     * Invalidates the volatile state held in the Twilio singleton
     */
    private static void invalidate() {
        Twilio.restClient = null;
    }
}
