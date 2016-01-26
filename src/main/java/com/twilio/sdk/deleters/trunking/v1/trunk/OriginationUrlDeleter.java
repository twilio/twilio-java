package com.twilio.sdk.deleters.trunking.v1.trunk;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.deleters.Deleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.trunking.OriginationUrl;

public class OriginationUrlDeleter extends Deleter<OriginationUrl> {
    private final String trunkSid;
    private final String sid;

    /**
     * Construct a new OriginationUrlDeleter
     * 
     * @param trunkSid The trunk_sid
     * @param sid The sid
     */
    public OriginationUrlDeleter(final String trunkSid, final String sid) {
        this.trunkSid = trunkSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the delete
     * 
     * @param client TwilioRestClient with which to make the request
     */
    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.DELETE,
            "/v1/Trunks/" + this.trunkSid + "/OriginationUrls/" + this.sid + "",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("OriginationUrl delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
    }
}