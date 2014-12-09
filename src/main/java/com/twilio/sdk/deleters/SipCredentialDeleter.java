package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipCredential;
import com.twilio.sdk.resources.SipCredentialList;

public class SipCredentialDeleter extends Deleter<SipCredential> {

    private final String credentialListSid;
    private final String sid;

    public SipCredentialDeleter(final String credentialListSid, final String sid) {
        this.credentialListSid = credentialListSid;
        this.sid = sid;
    }

    public SipCredentialDeleter(final SipCredentialList targetCredentialList, final String sid) {
        this(targetCredentialList.getSid(), sid);
    }

    public SipCredentialDeleter(final SipCredential target) {
        this(target.getCredentialListSid(), target.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.DELETE,
                                      String.format("/Accounts/{AccountSid}/SIP/CredentialLists/%s/Credentials/%s.json",
                                                    credentialListSid, sid), client.getAccountSid());
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SIP Credential delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            throw new ApiException(
                    "SIP Credential delete failed: [" + response.getStatusCode() + "] " + response.getContent());
        }
    }
}
