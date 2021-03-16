package com.twilio.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ConnectionBackoffStrategy;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Implements Expontential Backoff for timeouts, 429s and 503s
 */
public class TwilioBackoffStrategy implements ConnectionBackoffStrategy {
    public boolean shouldBackoff(Throwable t) {
        return ((t instanceof SocketTimeoutException)) || ((t instanceof ConnectException));
    }
    public boolean shouldBackoff(HttpResponse resp) {
        return resp.getStatusLine().getStatusCode() == 429 || resp.getStatusLine().getStatusCode() == 503;
    }
}