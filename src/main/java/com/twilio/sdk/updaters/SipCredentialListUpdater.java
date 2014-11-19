package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipCredentialList;



public class SipCredentialListUpdater extends Updater<SipCredentialList> {

    private final String sid;
    private String friendlyName;
    

    public SipCredentialListUpdater(final String sid) {
        this.sid = sid;
    }

    public SipCredentialListUpdater(final SipCredentialList target) {
        this(target.getSid());
    }

    
    public SipCredentialListUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }
    

    @Override
    public SipCredentialList execute(final TwilioRestClient client)  {
        Request request = new Request(HttpMethod.POST, "/SIP/CredentialLists/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SipCredentialList update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("SipCredentialList update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return SipCredentialList.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
    }
}