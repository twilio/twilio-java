package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Account;
import com.twilio.sdk.resources.RestException;

public class AccountUpdater extends Updater<Account> {

    private final String sid;
    private Account.Status status;
    private String friendlyName;

    public AccountUpdater(final String sid) {
        this.sid = sid;
    }

    public AccountUpdater(final Account target) {
        this(target.getSid());
    }

    public AccountUpdater setStatus(final Account.Status status) {
        this.status = status;
        return this;
    }

    public AccountUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public Account execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Account update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return Account.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (status != null) {
            request.addPostParam("Status", status.toString());
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

    }
}
