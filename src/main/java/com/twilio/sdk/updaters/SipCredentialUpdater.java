package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipCredential;
import com.twilio.sdk.resources.SipCredentialList;

public class SipCredentialUpdater extends Updater<SipCredential> {

    private final String sid;
    private final String credentialListSid;
    private final String password;

    public SipCredentialUpdater(final String credentialListSid, final String sid, final String password) {
        this.credentialListSid = credentialListSid;
        this.sid = sid;
        this.password = password;
    }

    public SipCredentialUpdater(final SipCredential target, final String password) {
        this(target.getCredentialListSid(), target.getSid(), password);
    }

    public SipCredentialUpdater(final SipCredentialList targetList, final String sid, final String password) {
        this(targetList.getSid(), sid, password);
    }

    @Override
    public SipCredential execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/SIP/Credentials/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Account update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "SIP Credential update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return SipCredential.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        request.addPostParam("Password", password);
    }
}
