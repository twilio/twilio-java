package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;


public class SipIpAccessControlListDeleter extends Deleter<SipIpAccessControlList> {
    private final String sid;

    public SipIpAccessControlListDeleter(String sid) {
        this.sid = sid;
    }

    public SipIpAccessControlListDeleter(SipIpAccessControlList target) {
        this(target.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.DELETE, "/SIP/IpAccessControlLists/" + sid + ".json");
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SIP Domain delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            throw new ApiException(
                    "SIP IP ACL delete failed: [" + response.getStatusCode() + "] " + response.getContent());
        }
    }
}
