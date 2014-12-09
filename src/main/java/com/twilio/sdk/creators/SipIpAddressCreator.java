package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;
import com.twilio.sdk.resources.SipIpAddress;

public class SipIpAddressCreator extends Creator<SipIpAddress> {

    private final String friendlyName;
    private final String ipAddress;
    private final String ipAccessControlListSid;

    public SipIpAddressCreator(final String ipAccessControlListSid, final String friendlyName, final String ipAddress) {
        this.ipAccessControlListSid = ipAccessControlListSid;
        this.friendlyName = friendlyName;
        this.ipAddress = ipAddress;
    }

    public SipIpAddressCreator(final SipIpAccessControlList target, final String friendlyName, final String ipAddress) {
        this(target.getSid(), friendlyName, ipAddress);
    }

    @Override
    public SipIpAddress execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST,
                                      "/Accounts/{AccountSid}/SIP/IpAccessControlLists/ " + ipAccessControlListSid +
                                      "/IpAddresses.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SIP IpAddress creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            throw new ApiException(
                    "SIP IpAddress creation failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return SipIpAddress.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        request.addPostParam("FriendlyName", friendlyName);
        request.addPostParam("IpAddress", ipAddress);
    }
}
