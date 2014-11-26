package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;
import com.twilio.sdk.resources.SipIpAddress;

public class SipIpAddressFetcher extends Fetcher<SipIpAddress> {

    private final String IpAccessControlListSid;
    private final String sid;

    public SipIpAddressFetcher(final String IpAccessControlListSid, final String sid) {
        this.IpAccessControlListSid = IpAccessControlListSid;
        this.sid = sid;
    }

    public SipIpAddressFetcher(final SipIpAccessControlList targetIpAccessControlList, final String sid) {
        this(targetIpAccessControlList.getSid(), sid);
    }

    public SipIpAddressFetcher(final SipIpAddress target) {
        this(target.getIpAccessControlListSid(), target.getSid());
    }

    @Override
    public SipIpAddress execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET,
                                      "/SIP/IpAccessControlLists/" + IpAccessControlListSid + "/IpAddresses/" + sid +
                                      ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "Unable to retrieve SIP IpAddress for Sid " + sid + ": [" + response.getStatusCode() + "] " +
                    response.getContent());
        }

        return SipIpAddress.fromJson(response.getStream(), client.getObjectMapper());
    }
}
