package com.twilio.sdk.updaters.trunking.v1.trunk;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.Promoter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.trunking.OriginationUrl;
import com.twilio.sdk.updaters.Updater;

import java.net.URI;

public class OriginationUrlUpdater extends Updater<OriginationUrl> {
    private final String trunkSid;
    private final String sid;
    private Integer weight;
    private Integer priority;
    private Boolean enabled;
    private String friendlyName;
    private URI sipUrl;

    /**
     * Construct a new OriginationUrlUpdater
     * 
     * @param trunkSid The trunk_sid
     * @param sid The sid
     */
    public OriginationUrlUpdater(final String trunkSid, final String sid) {
        this.trunkSid = trunkSid;
        this.sid = sid;
    }

    /**
     * The weight
     * 
     * @param weight The weight
     * @return this
     */
    public OriginationUrlUpdater setWeight(final Integer weight) {
        this.weight = weight;
        return this;
    }

    /**
     * The priority
     * 
     * @param priority The priority
     * @return this
     */
    public OriginationUrlUpdater setPriority(final Integer priority) {
        this.priority = priority;
        return this;
    }

    /**
     * The enabled
     * 
     * @param enabled The enabled
     * @return this
     */
    public OriginationUrlUpdater setEnabled(final Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * The friendly_name
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public OriginationUrlUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The sip_url
     * 
     * @param sipUrl The sip_url
     * @return this
     */
    public OriginationUrlUpdater setSipUrl(final URI sipUrl) {
        this.sipUrl = sipUrl;
        return this;
    }

    /**
     * The sip_url
     * 
     * @param sipUrl The sip_url
     * @return this
     */
    public OriginationUrlUpdater setSipUrl(final String sipUrl) {
        return setSipUrl(Promoter.uriFromString(sipUrl));
    }

    /**
     * Make the request to the Twilio API to perform the update
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated OriginationUrl
     */
    @Override
    public OriginationUrl execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/v1/Trunks/" + this.trunkSid + "/OriginationUrls/" + this.sid + "",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("OriginationUrl update failed: Unable to connect to server");
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