package com.twilio.sdk.resources;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

public class CallResult extends Result<Call> {
    public CallResult(TwilioRestClient client, Page<Call> page) {
        super(client, page);
    }

    @Override
    protected void fetchNextPage() {
        Request request = new Request("GET", this.page.getNextPageUri());
        Response response = this.client.request(request);

        if (response.getStatusCode() == 200) {
            this.page = CallPage.fromJson(response.getContent());
            this.iterator = this.page.getRecords().iterator();
        } else {
            this.page = null;
            this.iterator = null;
        }
    }
}
