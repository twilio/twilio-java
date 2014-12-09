package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipCredential;
import com.twilio.sdk.resources.SipCredentialList;

public class SipCredentialFetcher extends Fetcher<SipCredential> {

    private final String credentialListSid;
    private final String sid;

    public SipCredentialFetcher(final String credentialListSid, final String sid) {
        this.credentialListSid = credentialListSid;
        this.sid = sid;
    }

    public SipCredentialFetcher(final SipCredentialList targetCredentialList, final String sid) {
        this(targetCredentialList.getSid(), sid);
    }

    public SipCredentialFetcher(final SipCredential target) {
        this(target.getCredentialListSid(), target.getSid());
    }

    @Override
    public SipCredential execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET,
                                      String.format("/SIP/CredentialLists/%s/Credentials/%s.json", credentialListSid,
                                                    sid), client.getAccountSid());
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "Unable to retrieve SIP Credential for Sid " + sid + ": [" + response.getStatusCode() + "] " +
                    response.getContent());
        }

        return SipCredential.fromJson(response.getStream(), client.getObjectMapper());
    }
}
