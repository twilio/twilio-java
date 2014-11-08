package com.twilio.sdk.http;

import java.util.ArrayList;
import java.util.List;

public class MockHttpClient extends HttpClient {
    protected boolean withDelay = false;
    protected long delayLowerMillis;
    protected long delaySpreadMillis;
    protected List<Response> responses;
    protected List<Request> requests;

    public MockHttpClient() {
        responses = new ArrayList<>();
        requests = new ArrayList<>();
    }

    @Override
    public Response makeRequest(final Request request) {
        if (withDelay) {
            // Simulate network delay
            try {
                long delay = delayLowerMillis;
                delay += (delaySpreadMillis * Math.random());
                Thread.sleep(delay);
            } catch (final InterruptedException e) {
                // ignore
            }
        }

        Response response;

        if (responses.isEmpty()) {
            response = new ConsumableResponse("Mock Response Not Found", 404);
        } else {
            response = responses.get(0);
        }

        if (response instanceof ConsumableResponse) {
            ((ConsumableResponse) response).consume();
            if (((ConsumableResponse) response).isExhausted()) {
                responses.remove(0);
            }
        }

        requests.add(request);

        return response;
    }

    public void setDelay(final long lowerMillis, final long upperMillis) {
        withDelay = true;
        delayLowerMillis = lowerMillis;
        delaySpreadMillis = upperMillis - lowerMillis;
    }

    public void clearDelay() {
        withDelay = false;
    }

    public void setResponses(final List<Response> responses) {
        this.responses = responses;
    }

    public void addResponse(final Response response) {
        responses.add(response);
    }

    public List<Request> getRequests() {
        return requests;
    }
}
