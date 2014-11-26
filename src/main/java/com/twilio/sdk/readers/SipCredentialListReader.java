package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.SipCredentialList;

public class SipCredentialListReader extends Reader<SipCredentialList> {

    @Override
    public ResourceSet<SipCredentialList> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/SIP/CredentialLists.json");
        addQueryParams(request);

        Page<SipCredentialList> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<SipCredentialList> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<SipCredentialList> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for SipCredentialList");
        }

        Page<SipCredentialList> result = new Page<>();
        result.deserialize("credential_lists", response.getContent(), SipCredentialList.class,
                           client.getObjectMapper());

        return result;
    }

    private void addQueryParams(final Request request) {
    }
}
