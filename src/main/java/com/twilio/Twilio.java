package com.twilio;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import com.twilio.exception.ApiException;
import com.twilio.exception.AuthenticationException;
import com.twilio.exception.CertificateValidationException;
import com.twilio.http.HttpMethod;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.Executors;

/**
 * Singleton class to initialize Twilio environment.
 */
public class Twilio {

    public static final String VERSION = "7.49.1";
    public static final String JAVA_VERSION = System.getProperty("java.version");

    private static String username;
    private static String password;
    private static String accountSid;
    private static TwilioRestClient restClient;
    private static ListeningExecutorService executorService;

    private Twilio() {}

    /**
     * Ensures that the ListeningExecutorService is shutdown when the JVM exits.
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (executorService != null) {
                    executorService.shutdownNow();
                }
            }
        });
    }

    /**
     * Initialize the Twilio environment.
     *
     * @param username account to use
     * @param password auth token for the account
     */
    public static void init(final String username, final String password) {
        Twilio.setUsername(username);
        Twilio.setPassword(password);
    }

    /**
     * Initialize the Twilio environment.
     *
     * @param username account to use
     * @param password auth token for the account
     * @param accountSid account sid to use
     */
    public static void init(final String username, final String password, final String accountSid) {
        Twilio.setUsername(username);
        Twilio.setPassword(password);
        Twilio.setAccountSid(accountSid);
    }

    /**
     * Set the username.
     *
     * @param username account to use
     * @throws AuthenticationException if username is null
     */
    public static void setUsername(final String username) {
        if (username == null) {
            throw new AuthenticationException("Username can not be null");
        }

        if (!username.equals(Twilio.username)) {
            Twilio.invalidate();
        }

        Twilio.username = username;
    }

    /**
     * Set the auth token.
     *
     * @param password auth token to use
     * @throws AuthenticationException if password is null
     */
    public static void setPassword(final String password) {
        if (password == null) {
            throw new AuthenticationException("Password can not be null");
        }

        if (!password.equals(Twilio.password)) {
            Twilio.invalidate();
        }

        Twilio.password = password;
    }

    /**
     * Set the account sid.
     *
     * @param accountSid account sid to use
     * @throws AuthenticationException if account sid is null
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
     * Returns (and initializes if not initialized) the Twilio Rest Client.
     *
     * @return the TWilio Rest Client
     * @throws AuthenticationException if initialization required and either accountSid or authToken is null
     */
    public static TwilioRestClient getRestClient() {
        if (Twilio.restClient == null) {
            if (Twilio.username == null || Twilio.password == null) {
                throw new AuthenticationException(
                    "TwilioRestClient was used before AccountSid and AuthToken were set, please call Twilio.init()"
                );
            }

            TwilioRestClient.Builder builder = new TwilioRestClient.Builder(Twilio.username, Twilio.password);
            if (Twilio.accountSid != null) {
                builder.accountSid(Twilio.accountSid);
            }
            Twilio.restClient = builder.build();
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
    public static synchronized void setExecutorService(final ListeningExecutorService executorService) {
        Twilio.executorService = executorService;
    }

    /**
     * Validate that we can connect to the new SSL certificate posted on api.twilio.com.
     *
     * @throws com.twilio.exception.CertificateValidationException if the connection fails
     */
    public static void validateSslCertificate() {
        final NetworkHttpClient client = new NetworkHttpClient();
        final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");

        try {
            final Response response = client.makeRequest(request);

            if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
                throw new CertificateValidationException(
                    "Unexpected response from certificate endpoint", request, response
                );
            }
        } catch (final ApiException e) {
            throw new CertificateValidationException("Could not get response from certificate endpoint", request);
        }
    }

    /**
     * Invalidates the volatile state held in the Twilio singleton.
     */
    private static void invalidate() {
        Twilio.restClient = null;
    }

    /**
     * Attempts to gracefully shutdown the ListeningExecutorService if it is present.
     */
    public static synchronized void destroy() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
