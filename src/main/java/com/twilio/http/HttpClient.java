package com.twilio.http;

import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultRedirectStrategy;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.client5.http.protocol.RedirectStrategy;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.Timeout;

/**
 * Abstract class representing an HTTP client.
 * This class provides methods to make reliable HTTP requests with retry logic and customizable request configurations.
 */
public abstract class HttpClient {

    public static final Timeout CONNECTION_TIMEOUT = Timeout.ofMilliseconds(10000); // The default connection timeout is 10 seconds
    public static final Timeout CONNECT_TIMEOUT = Timeout.ofMilliseconds(30500); // The default socket timeout is 30.5 seconds
    public static final Timeout CONNECTION_KEEP_ALIVE = Timeout.ofMilliseconds(60000); // The default keep-alive timeout is 60 seconds
    public static final Timeout SOCKET_TIMEOUT = Timeout.ofMilliseconds(30000); // The default socket timeout is 30 seconds
    /**
     * Default request configuration for the HTTP client.
     * This configuration sets the connection request timeout, socket timeout, and connection keep-alive timeout.
     */
    public static final RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig
        .custom()
        .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
        .setConnectTimeout(CONNECT_TIMEOUT)
        .setConnectionKeepAlive(CONNECTION_KEEP_ALIVE)
        .build();
    /**
     * Default socket configuration for the HTTP client.
     * This configuration sets the socket timeout and enables keep-alive.
     */
    public static final SocketConfig DEFAULT_SOCKET_CONFIG = SocketConfig.custom()
        .setSoTimeout(SOCKET_TIMEOUT)
        .setSoKeepAlive(true)
        .build();


    public static final int ANY_500 = -500;
    public static final int ANY_400 = -400;
    public static final int ANY_300 = -300;
    public static final int ANY_200 = -200;
    public static final int ANY_100 = -100;

    public static final int[] RETRY_CODES = new int[] {ANY_500};
    public static final int RETRIES = 3;
    public static final long DELAY_MILLIS = 100L;

    /**
     * Custom redirect strategy that disables automatic redirects.
     */
    private static class TwilioRedirectStrategy extends DefaultRedirectStrategy {
        @Override
        public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
            // Disable all automatic redirects
            return false;
        }
    }

    // Default redirect strategy to not auto-redirect for any methods (empty string array).
    @Getter
    @Setter
    private RedirectStrategy redirectStrategy = new TwilioRedirectStrategy();

    @Getter
    private Response lastResponse;
    @Getter
    private Request lastRequest;

    /**
     * Make a request.
     *
     * @param request request to make
     * @return Response of the HTTP request
     */
    public Response reliableRequest(final Request request) {
        return reliableRequest(request, RETRY_CODES, RETRIES, DELAY_MILLIS);
    }

    /**
     * Make a request.
     *
     * @param request     request to make
     * @param retryCodes  codes used for retries
     * @param retries     max number of retries
     * @param delayMillis delays between retries
     * @return Response of the HTTP request
     */
    public Response reliableRequest(final Request request, final int[] retryCodes, int retries,
                                    final long delayMillis) {
        lastRequest = request;
        Response response = null;
        while (retries > 0) {
            response = makeRequest(request);

            if (!shouldRetry(response, retryCodes)) {
                break;
            }

            try {
                Thread.sleep(delayMillis);
            } catch (final InterruptedException e) {
                // Delay failed, continue
            }

            // Decrement retries
            retries--;
        }

        lastResponse = response;

        return response;
    }

    protected boolean shouldRetry(final Response response, final int[] retryCodes) {
        if (response == null) {
            return true;
        }

        int statusCode = response.getStatusCode();
        int category = (int) Math.floor(statusCode / 100.0);

        for (final int retryCode : retryCodes) {
            switch (retryCode) {
                case ANY_100:
                    if (category == 1) {
                        return true;
                    }
                    break;
                case ANY_200:
                    if (category == 2) {
                        return true;
                    }
                    break;
                case ANY_300:
                    if (category == 3) {
                        return true;
                    }
                    break;
                case ANY_400:
                    if (category == 4) {
                        return true;
                    }
                    break;
                case ANY_500:
                    if (category == 5) {
                        return true;
                    }
                    break;
                default:
                    if (statusCode == retryCode) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public static ContentType getContentType(Path filePath) {
        String fileName = filePath.getFileName().toString().toLowerCase();

        if (fileName.endsWith(".pdf")) {
            return ContentType.create("application/pdf");
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return ContentType.create("image/jpeg");
        } else if (fileName.endsWith(".png")) {
            return ContentType.create("image/png");
        } else {
            return ContentType.create("application/octet-stream"); // using "application/octet-stream" for generic binary data, often used by browsers for unknown binary files to prompt the user to download them
        }
    }

    public static HttpUriRequestBase createHttpUriRequestBase(final IRequest request) {
        HttpUriRequestBase httpUriRequestBase = null;
        switch (request.getMethod().toString().toUpperCase()) {
            case "POST": httpUriRequestBase = new HttpPost(request.constructURL().toString()); break;
            case "PUT": httpUriRequestBase = new HttpPut(request.constructURL().toString()); break;
            case "PATCH": httpUriRequestBase = new HttpPatch(request.constructURL().toString()); break;
            case "DELETE": httpUriRequestBase = new HttpDelete(request.constructURL().toString()); break;
            case "GET": httpUriRequestBase = new HttpGet(request.constructURL().toString()); break;
        }
        return httpUriRequestBase;
    }

    public abstract Response makeRequest(final Request request);
}
