package com.twilio;

import com.twilio.exception.ApiException;
import com.twilio.exception.AuthenticationException;
import com.twilio.exception.CertificateValidationException;
import com.twilio.http.HttpMethod;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Singleton class to initialize Twilio environment.
 */
public class Twilio implements TwilioAPI {

    private String username = System.getenv("TWILIO_ACCOUNT_SID");
    private String password = System.getenv("TWILIO_AUTH_TOKEN");
    private String accountSid; // username used if Twilio is null
    private String region = System.getenv("TWILIO_REGION");
    private String edge = System.getenv("TWILIO_EDGE");
    private volatile TwilioRestClient restClient;
    private static volatile ExecutorService executorService;

    /*
     * Ensures that the ExecutorService is shutdown when the JVM exits.
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
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
    public Twilio(final String username, final String password) {
        setUsername(username);
        setPassword(password);
    }

    /**
     * Initialize the Twilio environment.
     *
     * @param username   account to use
     * @param password   auth token for the account
     * @param accountSid account sid to use
     */
    public Twilio(final String username, final String password, final String accountSid) {
        setUsername(username);
        setPassword(password);
        setAccountSid(accountSid);
    }


    /**
     * {@inheritdoc}
     */
    public synchronized void setUsername(final String username) {
        if (this.username == null) {
            throw new AuthenticationException("Username can not be null");
        }

        if (!username.equals(this.username)) {
            this.invalidate();
        }

        this.username = username;
    }

    /**
     * {@inheritdoc}
     */
    public synchronized void setPassword(final String password) {
        if (password == null) {
            throw new AuthenticationException("Password can not be null");
        }

        if (!password.equals(this.password)) {
            this.invalidate();
        }

        this.password = password;
    }

    /**
     * {@inheritdoc}
     */
    public synchronized void setAccountSid(final String accountSid) {
        if (accountSid == null) {
            throw new AuthenticationException("AccountSid can not be null");
        }

        if (!accountSid.equals(this.accountSid)) {
            this.invalidate();
        }

        this.accountSid = accountSid;
    }

    /**
     * {@inheritdoc}
     */
    public synchronized void setRegion(final String region) {
        if (!Objects.equals(region, this.region)) {
            this.invalidate();
        }

        this.region = region;
    }

    /**
     * {@inheritdoc}
     */
    public synchronized void setEdge(final String edge) {
        if (!Objects.equals(edge, this.edge)) {
            this.invalidate();
        }

        this.edge = edge;
    }

    /**
     * Returns (and initializes if not initialized) the Twilio Rest Client.
     *
     * @return the Twilio Rest Client
     * @throws AuthenticationException if initialization required and either accountSid or authToken is null
     */
    public synchronized TwilioRestClient getRestClient() {
        if (restClient == null) {
            restClient = buildRestClient();
        }

        return restClient;
    }

    private TwilioRestClient buildRestClient() {
        if (this.username == null || this.password == null) {
            throw new AuthenticationException(
                "TwilioRestClient was used before AccountSid and AuthToken were set, please call Twilio.init()"
            );
        }

        TwilioRestClient.Builder builder = new TwilioRestClient.Builder(this.username, this.password);

        if (this.accountSid != null) {
            builder.accountSid(this.accountSid);
        }

        builder.region(this.region);
        builder.edge(this.edge);

        return builder.build();
    }

    /**
     * Use a custom rest client.
     *
     * @param restClient rest client to use
     */
    public synchronized void setRestClient(final TwilioRestClient restClient) {
        this.restClient = restClient;
    }

    /**
     * Returns the Twilio executor service.
     *
     * @return the Twilio executor service
     */
    public static ExecutorService getExecutorService() {
        if (Twilio.executorService == null) {
            Twilio.executorService = Executors.newCachedThreadPool();
        }
        return Twilio.executorService;
    }

    /**
     * Use a custom executor service.
     *
     * @param executorService executor service to use
     */
    public static void setExecutorService(final ExecutorService executorService) {
        Twilio.executorService = executorService;
    }

    /**
     * Validate that we can connect to the new SSL certificate posted on api.twilio.com.
     *
     * @throws CertificateValidationException if the connection fails
     */
    public static void validateSslCertificate() {
        final NetworkHttpClient client = new NetworkHttpClient();
        final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");

        try {
            final Response response = client.makeRequest(request);

            if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
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
    private void invalidate() {
        this.restClient = null;
    }

    /**
     * {@inheritdoc}
     */
    public synchronized void destroy() {
        if (Twilio.executorService != null) {
            Twilio.executorService.shutdown();
        }
    }
}
