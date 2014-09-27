package com.twilio.sdk.http;

public class MockHttpClient extends HttpClient {
    protected boolean withDelay = false;
    protected long delayLowerMillis;
    protected long delaySpreadMillis;

    @Override
    public Response makeRequest(Request request) {
        if (this.withDelay) {
            // Simulate network delay
            try {
                long delay = this.delayLowerMillis;
                delay += (this.delaySpreadMillis * Math.random());
                Thread.sleep(delay);
            } catch(InterruptedException e) {
                // ignore
            }
        }
        return request.getResponse();
    }

    public void setDelay(long lowerMillis, long upperMillis) {
        this.withDelay = true;
        this.delayLowerMillis = lowerMillis;
        this.delaySpreadMillis = upperMillis - lowerMillis;
    }

    public void clearDelay() {
        this.withDelay = false;
    }
}
