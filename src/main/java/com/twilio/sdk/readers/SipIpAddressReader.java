package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAddress;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.SipIpAccessControlList;

public class SipIpAddressReader extends Reader<SipIpAddress> {

    private final String IpAccessControlListSid;

    public SipIpAddressReader(final String IpAccessControlListSid) {
        this.IpAccessControlListSid = IpAccessControlListSid;
    }

    public SipIpAddressReader(final SipIpAccessControlList target) {
        this(target.getSid());
    }

    @Override
    public ResourceSet<SipIpAddress> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/SIP/IpAccessControlLists/" + IpAccessControlListSid + "IpAddresses.json");

        Page<SipIpAddress> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<SipIpAddress> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<SipIpAddress> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for SipIpAddress");
        }

        Page<SipIpAddress> result = new Page<>();
        result.deserialize("SipIpAddresss", response.getContent(), SipIpAddress.class, client.getObjectMapper());

        return result;
    }
}
