package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipCredential;
import com.twilio.sdk.resources.SipCredentialList;

public class SipCredentialCreator extends Creator<SipCredential> {

    private final String username;
    private final String password;
    private final String credentialListSid;

    public SipCredentialCreator(final String credentialListSid, final String username, final String password) {
        this.credentialListSid = credentialListSid;
        this.username = username;
        this.password = password;
    }

    public SipCredentialCreator(final SipCredentialList target, final String username, final String password) {
        this(target.getSid(), username, password);
    }

    @Override
    public SipCredential execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST,
                                      "/Accounts/{AccountSid}/SIP/CredentialLists/ " + credentialListSid +
                                      "/Credentials.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SIP Credential creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            throw new ApiException(
                    "SIP Credential creation failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return SipCredential.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(Request request) {
        request.addPostParam("Username", username);
        request.addPostParam("Password", password);
    }
}
