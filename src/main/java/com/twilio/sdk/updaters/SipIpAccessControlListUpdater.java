package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;

public class SipIpAccessControlListUpdater extends Updater<SipIpAccessControlList> {

    private final String sid;
    private String friendlyName;

    public SipIpAccessControlListUpdater(final String sid) {
        this.sid = sid;
    }

    public SipIpAccessControlListUpdater(final SipIpAccessControlList target) {
        this(target.getSid());
    }

    public SipIpAccessControlListUpdater setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public SipIpAccessControlList execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/SIP/IpAccessControlLists/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Account update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("SIP IpAccessControlList update failed: [" + response.getStatusCode() + "] " +
                                   response.getContent());
        }

        return SipIpAccessControlList.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }

}
