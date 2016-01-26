package com.twilio.sdk.fetchers.api.v2010.account.message;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.message.Media;

public class MediaFetcher extends Fetcher<Media> {
    private final String accountSid;
    private final String messageSid;
    private final String sid;

    /**
     * Construct a new MediaFetcher
     * 
     * @param accountSid The account_sid
     * @param messageSid The message_sid
     * @param sid Fetch by unique media Sid
     */
    public MediaFetcher(final String accountSid, final String messageSid, final String sid) {
        this.accountSid = accountSid;
        this.messageSid = messageSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Media
     */
    @Override
    public Media execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/" + this.accountSid + "/Messages/" + this.messageSid + "/Media/" + this.sid + ".json",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Media fetch failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Media.fromJson(response.getStream(), client.getObjectMapper());
    }
}