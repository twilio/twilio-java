package com.twilio.sdk;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.AuthenticationException;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.Executors;

/**
 * Singleton class to initialize Twilio environment.
 */
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

    private Twilio() {}

    /**
     * Initialize the Twilio environment.
     *
     * @param accountSid account to use
     * @param authToken auth token for the account
     */
    public static void init(final String accountSid, final String authToken) {
        Twilio.setAccountSid(accountSid);
        Twilio.setAuthToken(authToken);
    }

    /**
     * Set the account sid.
     *
     * @param accountSid account sid to use
     * @throws AuthenticationException if accountSid is null
     */
    public static void setAccountSid(final String accountSid) {
        if (accountSid == null) {
            throw new AuthenticationException("AccountSid can not be null");
        }

        if (!accountSid.equals(Twilio.accountSid)) {
            Twilio.invalidate();
        }

        Twilio.accountSid = accountSid;
    }

    /**
     * Set the auth token.
     *
     * @param authToken auth token to use
     * @throws AuthenticationException if authToken is null
     */
    public static void setAuthToken(final String authToken) {
        if (authToken == null) {
            throw new AuthenticationException("AuthToken can not be null");
        }

        if (!authToken.equals(Twilio.authToken)) {
            Twilio.invalidate();
        }

        Twilio.authToken = authToken;
    }

    /**
     * Returns (and initializes if not initialized) the Twilio Rest Client.
     *
     * @return the TWilio Rest Client
     * @throws AuthenticationException if initialization required and either accountSid or authToken is null
     */
    public static TwilioRestClient getRestClient() {
        if (Twilio.restClient == null) {
            if (Twilio.accountSid == null || Twilio.authToken == null) {
                throw new AuthenticationException(
                    "TwilioRestClient was used before AccountSid and AuthToken were set, please call Twilio.init()"
                );
            }

            Twilio.restClient = new TwilioRestClient(Twilio.accountSid, Twilio.authToken);
        }

        return Twilio.restClient;
    }

    /**
     * Use a custom rest client.
     *
     * @param restClient rest client to use
     */
    public static void setRestClient(final TwilioRestClient restClient) {
        Twilio.restClient = restClient;
    }

    /**
     * Returns the Twilio executor service.
     *
     * @return the Twilio executor service
     */
    public static ListeningExecutorService getExecutorService() {
        if (Twilio.executorService == null) {
            Twilio.executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        }
        return Twilio.executorService;
    }

    /**
     * Use a custom executor service.
     *
     * @param executorService executor service to use
     */
    public static void setExecutorService(final ListeningExecutorService executorService) {
        Twilio.executorService = executorService;
    }

    /**
     * Invalidates the volatile state held in the Twilio singleton.
     */
    private static void invalidate() {
        Twilio.restClient = null;
    }
}
