package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.ShortCode;

public class ShortCodeReader extends Reader<ShortCode> {

    private String shortCode;
    private String friendlyName;

    @Override
    public ResourceSet<ShortCode> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/SMS/ShortCodes.json");
        addQueryParams(request);

        Page<ShortCode> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<ShortCode> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<ShortCode> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<ShortCode> result = new Page<>();
        result.deserialize("short_codes", response.getContent(), ShortCode.class, client.getObjectMapper());

        return result;
    }

    public ShortCodeReader byShortCode(final String shortCode) {
        this.shortCode = shortCode;
        return this;
    }

    public ShortCodeReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (shortCode != null) {
            request.addQueryParam("ShortCode", shortCode);
        }
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
    }
}
