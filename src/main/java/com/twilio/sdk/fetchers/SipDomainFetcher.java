package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipDomain;

public class SipDomainFetcher extends Fetcher<SipDomain> {

    private final String sid;

    public SipDomainFetcher(String sid) {
        this.sid = sid;
    }

    @Override
    public SipDomain execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/SIP/Domains/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "Unable to retrieve SIP Domain for Sid " + sid + ": [" + response.getStatusCode() + "] " +
                    response.getContent());
        }

        return SipDomain.fromJson(response.getStream(), client.getObjectMapper());
    }
}
