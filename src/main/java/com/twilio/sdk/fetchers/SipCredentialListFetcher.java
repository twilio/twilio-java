package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipCredentialList;

public class SipCredentialListFetcher extends Fetcher<SipCredentialList> {

    private final String sid;

    public SipCredentialListFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public SipCredentialList execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/SIP/CredentialLists/" + sid + ".json", client.getAccountSid());
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find SipCredentialList for Sid " + sid);
        }

        return SipCredentialList.fromJson(response.getStream(), client.getObjectMapper());
    }
}
