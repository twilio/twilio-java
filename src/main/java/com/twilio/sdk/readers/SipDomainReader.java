package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.SipDomain;

public class SipDomainReader extends Reader<SipDomain> {

    @Override
    public ResourceSet<SipDomain> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/SIP/Domains.json", client.getAccountSid());

        Page<SipDomain> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<SipDomain> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<SipDomain> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for SipDomain");
        }

        Page<SipDomain> result = new Page<>();
        result.deserialize("domains", response.getContent(), SipDomain.class, client.getObjectMapper());

        return result;
    }
}
