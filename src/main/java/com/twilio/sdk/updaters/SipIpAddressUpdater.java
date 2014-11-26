package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;
import com.twilio.sdk.resources.SipIpAddress;

public class SipIpAddressUpdater extends Updater<SipIpAddress> {

    private final String sid;
    private final String ipAccessControlListSid;
    private String friendlyName;
    private String ipAddress;

    public SipIpAddressUpdater(final String IpAccessControlListSid, final String sid) {
        this.ipAccessControlListSid = IpAccessControlListSid;
        this.sid = sid;
    }

    public SipIpAddressUpdater(final SipIpAddress target) {
        this(target.getIpAccessControlListSid(), target.getSid());
    }

    public SipIpAddressUpdater(final SipIpAccessControlList targetList, final String sid) {
        this(targetList.getSid(), sid);
    }

    public SipIpAddressUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public SipIpAddressUpdater setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    @Override
    public SipIpAddress execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/SIP/IpAddresses/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Account update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "SIP IpAddress update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return SipIpAddress.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(Request request) {
        if (ipAddress != null) {
            request.addPostParam("IpAddress", ipAddress);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }
}
