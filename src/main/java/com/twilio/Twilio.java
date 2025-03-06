package com.twilio;

import com.twilio.annotations.Beta;
import com.twilio.auth_strategy.AuthStrategy;
import com.twilio.exception.ApiException;
import com.twilio.exception.AuthenticationException;
import com.twilio.exception.CertificateValidationException;
import com.twilio.credential.CredentialProvider;
import com.twilio.http.HttpMethod;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Singleton class to initialize Twilio environment.
 */
public class Twilio {

    public static final String VERSION = "11.0.0";
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_ARCH = System.getProperty("os.arch");
    private static String username = System.getenv("TWILIO_ACCOUNT_SID");
    private static String password = System.getenv("TWILIO_AUTH_TOKEN");
    private static String accountSid; // username used if this is null
    @Getter
    private static List<String> userAgentExtensions;
    private static String region = System.getenv("TWILIO_REGION");
    private static String edge = System.getenv("TWILIO_EDGE");
    private static volatile TwilioRestClient restClient;
    private static volatile ExecutorService executorService;

    private static CredentialProvider credentialProvider;

    private Twilio() {
    }

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
    public static synchronized void init(final String username, final String password) {
        Twilio.setUsername(username);
        Twilio.setPassword(password);
        Twilio.setAccountSid(null);
    }

    @Beta
    public static synchronized void init(final CredentialProvider credentialProvider) {
        Twilio.setCredentialProvider(credentialProvider);
        Twilio.setAccountSid(null);
    }

    @Beta
    public static synchronized void init(final CredentialProvider credentialProvider, String accountSid) {
        Twilio.setCredentialProvider(credentialProvider);
        Twilio.setAccountSid(accountSid);
    }

    private static void setCredentialProvider(final CredentialProvider credentialProvider) {
        if (credentialProvider == null) {
            throw new AuthenticationException("Credential Provider can not be null");
        }

        if (!credentialProvider.equals(Twilio.credentialProvider)) {
            Twilio.invalidate();
        }
        // Invalidate Basic Creds as they might be initialized via environment variables.
        invalidateBasicCreds();
        Twilio.credentialProvider = credentialProvider;
    }

    /**
     * Initialize the Twilio environment.
     *
     * @param username   account to use
     * @param password   auth token for the account
     * @param accountSid account sid to use
     */
    public static synchronized void init(final String username, final String password, final String accountSid) {
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
    public static synchronized void setUsername(final String username) {
        if (username == null) {
            throw new AuthenticationException("Username can not be null");
        }

        if (!username.equals(Twilio.username)) {
            Twilio.invalidate();
        }
        Twilio.invalidateOAuthCreds();

        Twilio.username = username;
    }

    /**
     * Set the auth token.
     *
     * @param password auth token to use
     * @throws AuthenticationException if password is null
     */
    public static synchronized void setPassword(final String password) {
        if (password == null) {
            throw new AuthenticationException("Password can not be null");
        }

        if (!password.equals(Twilio.password)) {
            Twilio.invalidate();
        }
        Twilio.invalidateOAuthCreds();

        Twilio.password = password;
    }

    /**
     * Set the account sid.
     *
     * @param accountSid account sid to use
     */
    public static synchronized void setAccountSid(final String accountSid) {
        if (!Objects.equals(accountSid, Twilio.accountSid)) {
            Twilio.invalidate();
        }

        Twilio.accountSid = accountSid;
    }

    public static synchronized void setUserAgentExtensions(final List<String> userAgentExtensions) {
        if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
            Twilio.userAgentExtensions = new ArrayList<>(userAgentExtensions);
        } else {
            // In case a developer wants to reset userAgentExtensions
            Twilio.userAgentExtensions = null;
        }
    }

    /**
     * Set the region.
     *
     * @param region region to make request
     */
    public static synchronized void setRegion(final String region) {
        if (!Objects.equals(region, Twilio.region)) {
            Twilio.invalidate();
        }

        Twilio.region = region;
    }

    /**
     * Set the edge.
     *
     * @param edge edge to make request
     */
    public static synchronized void setEdge(final String edge) {
        if (!Objects.equals(edge, Twilio.edge)) {
            Twilio.invalidate();
        }

        Twilio.edge = edge;
    }

    /**
     * Returns (and initializes if not initialized) the Twilio Rest Client.
     *
     * @return the Twilio Rest Client
     * @throws AuthenticationException if initialization required and either accountSid or authToken is null
     */
    public static TwilioRestClient getRestClient() {
        if (Twilio.restClient == null) {
            synchronized (Twilio.class) {
                if (Twilio.restClient == null) {
                    Twilio.restClient = buildRestClient();
                }
            }
        }

        return Twilio.restClient;
    }

    private static TwilioRestClient buildRestClient() {
        if (Twilio.username == null || Twilio.password == null) {
            if (credentialProvider == null) {
                throw new AuthenticationException(
                        "Credentials have not been initialized or changed, please call Twilio.init()"
                );
            }
        }
        TwilioRestClient.Builder builder;
        if (credentialProvider != null) {
            AuthStrategy authStrategy = credentialProvider.toAuthStrategy();
            builder = new TwilioRestClient.Builder(authStrategy);
        } else {
            builder = new TwilioRestClient.Builder(Twilio.username, Twilio.password);
        }

        if (Twilio.accountSid != null) {
            builder.accountSid(Twilio.accountSid);
        }

        if (userAgentExtensions != null) {
            builder.userAgentExtensions(Twilio.userAgentExtensions);
        }

        builder.region(Twilio.region);
        builder.edge(Twilio.edge);

        return builder.build();
    }

    /**
     * Use a custom rest client.
     *
     * @param restClient rest client to use
     */
    public static void setRestClient(final TwilioRestClient restClient) {
        synchronized (Twilio.class) {
            Twilio.restClient = restClient;
        }
    }

    /**
     * Returns the Twilio executor service.
     *
     * @return the Twilio executor service
     */
    public static ExecutorService getExecutorService() {
        if (Twilio.executorService == null) {
            synchronized (Twilio.class) {
                if (Twilio.executorService == null) {
                    Twilio.executorService = Executors.newCachedThreadPool();
                }
            }
        }
        return Twilio.executorService;
    }

    /**
     * Use a custom executor service.
     *
     * @param executorService executor service to use
     */
    public static void setExecutorService(final ExecutorService executorService) {
        synchronized (Twilio.class) {
            Twilio.executorService = executorService;
        }
    }

    /**
     * Validate that we can connect to the new SSL certificate posted on tls-test.twilio.com
     *
     * @throws CertificateValidationException if the connection fails
     */
    public static void validateSslCertificate() {
        final NetworkHttpClient client = new NetworkHttpClient();
        validateSslCertificate(client);
    }


    public static void validateSslCertificate(NetworkHttpClient client) {
        final Request request = new Request(HttpMethod.GET, "https://tls-test.twilio.com:443");
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
    private static void invalidate() {
        Twilio.restClient = null;
    }

    private static void invalidateOAuthCreds() {
        Twilio.credentialProvider = null;
    }

    private static void invalidateBasicCreds() {
        Twilio.username = null;
        Twilio.password = null;
    }

    /**
     * Attempts to gracefully shutdown the ExecutorService if it is present.
     */
    public static synchronized void destroy() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
