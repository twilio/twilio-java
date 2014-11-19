package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Account;

public class AccountCreator extends Creator<Account> {

    private String friendlyName;

    public AccountCreator() {

    }

    public AccountCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public Account execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Account creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            throw new ApiException(
                    "Account creation failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Account.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }

}
