package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.OutgoingCallerId;
import com.twilio.sdk.resources.RestException;

public class OutgoingCallerIdFetcher extends Fetcher<OutgoingCallerId> {

    private final String sid;

    public OutgoingCallerIdFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public OutgoingCallerId execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/OutgoingCallerIds/" + sid + ".json",
                                      client.getAccountSid());
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return OutgoingCallerId.fromJson(response.getStream(), client.getObjectMapper());
    }
}
