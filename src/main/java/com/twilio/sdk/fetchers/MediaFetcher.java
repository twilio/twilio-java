package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Media;
import com.twilio.sdk.resources.Message;
import com.twilio.sdk.resources.RestException;

public class MediaFetcher extends Fetcher<Media> {

    private final String messageSid;
    private final String sid;

    public MediaFetcher(final String messageSid, final String sid) {
        this.messageSid = messageSid;
        this.sid = sid;
    }

    public MediaFetcher(final Message target, final String sid) {
        this(target.getSid(), sid);
    }

    @Override
    public Media execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET,
                                      String.format("/2010-04-01/Accounts/{AccountSid}/Messages/%s/Media%s.json", messageSid, sid),
                                      client.getAccountSid());
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return Media.fromJson(response.getStream(), client.getObjectMapper());
    }
}