package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;
import com.twilio.sdk.resources.RestException;

public class SipIpAccessControlListDeleter extends Deleter<SipIpAccessControlList> {

    private final String sid;

    public SipIpAccessControlListDeleter(final String sid) {
        this.sid = sid;
    }

    public SipIpAccessControlListDeleter(final SipIpAccessControlList target) {
        this(target.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client)  {
        Request request = new Request(HttpMethod.DELETE, "/2010-04-01/Accounts/{AccountSid}/SIP/IpAccessControlLists/" + sid + ".json", client.getAccountSid());
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SipIpAccessControlList delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }
    }
}