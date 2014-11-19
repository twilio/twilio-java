package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.OutgoingCallerId;

public class OutgoingCallerIdFetcher extends Fetcher<OutgoingCallerId> {

    private final String sid;

    public OutgoingCallerIdFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public OutgoingCallerId execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/OutgoingCallerIds/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find OutgoingCallerId for Sid " + sid);
        }

        return OutgoingCallerId.fromJson(response.getStream(), client.getObjectMapper());
    }
}