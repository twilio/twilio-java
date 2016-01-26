package com.twilio.sdk.creators.trunking.v1.trunk;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.trunking.OriginationUrl;

import java.net.URI;

public class OriginationUrlCreator extends Creator<OriginationUrl> {
    private final String trunkSid;
    private final Integer weight;
    private final Integer priority;
    private final Boolean enabled;
    private final String friendlyName;
    private final URI sipUrl;

    /**
     * Construct a new OriginationUrlCreator
     * 
     * @param trunkSid The trunk_sid
     * @param weight The weight
     * @param priority The priority
     * @param enabled The enabled
     * @param friendlyName The friendly_name
     * @param sipUrl The sip_url
     */
    public OriginationUrlCreator(final String trunkSid, final Integer weight, final Integer priority, final Boolean enabled, final String friendlyName, final URI sipUrl) {
        this.trunkSid = trunkSid;
        this.weight = weight;
        this.priority = priority;
        this.enabled = enabled;
        this.friendlyName = friendlyName;
        this.sipUrl = sipUrl;
    }

    /**
     * Make the request to the Twilio API to perform the create
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created OriginationUrl
     */
    @Override
    public OriginationUrl execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/v1/Trunks/" + this.trunkSid + "/OriginationUrls",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("OriginationUrl creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return OriginationUrl.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (weight != null) {
            request.addPostParam("Weight", weight.toString());
        }
        
        if (priority != null) {
            request.addPostParam("Priority", priority.toString());
        }
        
        if (enabled != null) {
            request.addPostParam("Enabled", enabled.toString());
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (sipUrl != null) {
            request.addPostParam("SipUrl", sipUrl.toString());
        }
    }
}