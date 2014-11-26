package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.SipIpAccessControlList;

public class SipIpAccessControlListReader extends Reader<SipIpAccessControlList> {

    @Override
    public ResourceSet<SipIpAccessControlList> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/SIP/IpAccessControlLists.json");

        Page<SipIpAccessControlList> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<SipIpAccessControlList> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<SipIpAccessControlList> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for SipIpAccessControlList");
        }

        Page<SipIpAccessControlList> result = new Page<>();
        result.deserialize("ip_access_control_lists", response.getContent(), SipIpAccessControlList.class,
                           client.getObjectMapper());

        return result;
    }
}
