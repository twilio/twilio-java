package com.twilio.sdk.http;

import com.twilio.sdk.exceptions.InvalidRequestException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public abstract class HttpClient {
    public static final int ANY_500 = -500;
    public static final int ANY_400 = -400;
    public static final int ANY_300 = -300;
    public static final int ANY_200 = -200;
    public static final int ANY_100 = -100;

    public static final int[] RETRY_CODES = new int[]{ANY_500};
    public static final int RETRIES = 3;
    public static final long DELAY_MILLIS = 100L;

    public Response reliableRequest(final Request request) {
        return reliableRequest(request, RETRY_CODES, RETRIES, DELAY_MILLIS);
    }

    public Response reliableRequest(final Request request, final int[] retryCodes, int retries, final long delayMillis) {
        Response response = null;
        while (retries > 0) {
            response = makeRequest(request);

            if (!shouldRetry(response, retryCodes)) {
                return response;
            }

            try {
                Thread.sleep(delayMillis);
            } catch (final InterruptedException e) {
                // Delay failed, continue
            }

            // Decrement retries
            retries--;
        }
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

    public abstract Response makeRequest(Request request);

    protected String authentication(final String username, final String password) {
        String credentials = username + ":" + password;
        try {
            String encoded = new Base64().encodeAsString(credentials.getBytes("ascii"));
            return "Basic " + encoded;
        } catch (final UnsupportedEncodingException e) {
            throw new InvalidRequestException("It must be possible to encode credentials as ascii", credentials, e);
        }
    }
}
