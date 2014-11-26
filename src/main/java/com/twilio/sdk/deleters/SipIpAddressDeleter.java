package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;
import com.twilio.sdk.resources.SipIpAddress;

public class SipIpAddressDeleter extends Deleter<SipIpAddress> {

    private final String IpAccessControlListSid;
    private final String sid;

    public SipIpAddressDeleter(final String IpAccessControlListSid, final String sid) {
        this.IpAccessControlListSid = IpAccessControlListSid;
        this.sid = sid;
    }

    public SipIpAddressDeleter(final SipIpAccessControlList targetIpAccessControlList, final String sid) {
        this(targetIpAccessControlList.getSid(), sid);
    }

    public SipIpAddressDeleter(final SipIpAddress target) {
        this(target.getIpAccessControlListSid(), target.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.DELETE,
                                      "/Accounts/{AccountSid}/SIP/IpAccessControlLists/" + IpAccessControlListSid +
                                      "/IpAddresses/" + sid + ".json");
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SIP IpAddress delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            throw new ApiException(
                    "SIP IpAddress delete failed: [" + response.getStatusCode() + "] " + response.getContent());
        }
    }
}
