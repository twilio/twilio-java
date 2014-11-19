package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Account;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;

public class AccountReader extends Reader<Account> {

    private Account.Status status;
    private String friendlyName;

    @Override
    public ResourceSet<Account> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts.json");
        addQueryParams(request);

        Page<Account> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Account> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<Account> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for Account");
        }

        Page<Account> result = new Page<>();
        result.deserialize("accounts", response.getContent(), Account.class, client.getObjectMapper());

        return result;
    }

    public AccountReader byStatus(final Account.Status status) {
        this.status = status;
        return this;
    }

    public AccountReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
    }
}
