package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;

public class SipIpAccessControlListCreator extends Creator<SipIpAccessControlList> {

    private final String friendlyName;

    public SipIpAccessControlListCreator(final String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public SipIpAccessControlList execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/SIP/IpAccessControlLists.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SIP IpAccessControlList creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            throw new ApiException("SIP IpAccessControlList creation failed: [" + response.getStatusCode() + "] " +
                                   response.getContent());
        }

        return SipIpAccessControlList.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        request.addPostParam("FriendlyName", friendlyName);
    }

}
