package com.twilio.http.noauth;

import com.twilio.http.HttpClient;
import com.twilio.http.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.DefaultRedirectStrategy;

public abstract class NoAuthHttpClient {
    public static final RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig
            .custom()
            .setConnectTimeout(HttpClient.CONNECTION_TIMEOUT)
            .setSocketTimeout(HttpClient.SOCKET_TIMEOUT)
            .build();
    public static final SocketConfig DEFAULT_SOCKET_CONFIG = SocketConfig
            .custom()
            .setSoTimeout(HttpClient.SOCKET_TIMEOUT)
            .build();
    @Getter
    @Setter
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(new String[0]);

    @Getter
    private Response lastResponse;
    @Getter
    private NoAuthRequest lastRequest;

    public Response reliableRequest(final NoAuthRequest request) {
        return reliableRequest(request, HttpClient.RETRY_CODES, HttpClient.RETRIES, HttpClient.DELAY_MILLIS);
    }

    public Response reliableRequest(final NoAuthRequest request, final int[] retryCodes, int retries,
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
                case HttpClient.ANY_100:
                    if (category == 1) {
                        return true;
                    }
                    break;
                case HttpClient.ANY_200:
                    if (category == 2) {
                        return true;
                    }
                    break;
                case HttpClient.ANY_300:
                    if (category == 3) {
                        return true;
                    }
                    break;
                case HttpClient.ANY_400:
                    if (category == 4) {
                        return true;
                    }
                    break;
                case HttpClient.ANY_500:
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
    
    public abstract Response makeRequest(NoAuthRequest request);
}
