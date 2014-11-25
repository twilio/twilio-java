package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipCredential;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.SipCredentialList;

public class SipCredentialReader extends Reader<SipCredential> {

    private final String credentialListSid;

    public SipCredentialReader(final String credentialListSid) {
        this.credentialListSid = credentialListSid;
    }

    public SipCredentialReader(final SipCredentialList target) {
        this(target.getSid());
    }

    @Override
    public ResourceSet<SipCredential> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/SIP/CredentialLists/" + credentialListSid + "Credentials.json");

        Page<SipCredential> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<SipCredential> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<SipCredential> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for SipCredential");
        }

        Page<SipCredential> result = new Page<>();
        result.deserialize("credentials", response.getContent(), SipCredential.class, client.getObjectMapper());

        return result;
    }
}
