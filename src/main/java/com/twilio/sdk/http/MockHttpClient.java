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
        this.responses = new ArrayList<Response>();
        this.requests = new ArrayList<Request>();
    }

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

        Response response;

        if (this.responses.isEmpty()) {
            response = new ConsumableResponse("Mock Response Not Found", 404);
        } else {
            response = this.responses.get(0);
        }

        if (response instanceof ConsumableResponse) {
            ((ConsumableResponse)response).consume();
            if (((ConsumableResponse)response).isExhausted()) {
                this.responses.remove(0);
            }
        }

        this.requests.add(request);

        return response;
    }

    public void setDelay(long lowerMillis, long upperMillis) {
        this.withDelay = true;
        this.delayLowerMillis = lowerMillis;
        this.delaySpreadMillis = upperMillis - lowerMillis;
    }

    public void clearDelay() {
        this.withDelay = false;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public void addResponse(Response response) {
        this.responses.add(response);
    }

    public List<Request> getRequests() {
        return this.requests;
    }
}
