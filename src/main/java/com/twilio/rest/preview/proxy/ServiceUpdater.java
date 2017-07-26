/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.proxy;

import com.twilio.annotations.Preview;
import com.twilio.base.Updater;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

@Preview
public class ServiceUpdater extends Updater<Service> {
    private final String pathSid;
    private String friendlyName;
    private Boolean autoCreate;
    private URI callbackUrl;

    /**
     * Construct a new ServiceUpdater.
     * 
     * @param pathSid A string that uniquely identifies this Service.
     */
    public ServiceUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * A human readable description of this resource, up to 64 characters..
     * 
     * @param friendlyName A human readable description of this resource
     * @return this
     */
    public ServiceUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Boolean flag specifying whether to create threads when a user communticates
     * out of band..
     * 
     * @param autoCreate Boolean flag specifying whether to auto-create threads.
     * @return this
     */
    public ServiceUpdater setAutoCreate(final Boolean autoCreate) {
        this.autoCreate = autoCreate;
        return this;
    }

    /**
     * The URL Twilio will request for callback notifications..
     * 
     * @param callbackUrl URL Twilio will request for callbacks.
     * @return this
     */
    public ServiceUpdater setCallbackUrl(final URI callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    /**
     * The URL Twilio will request for callback notifications..
     * 
     * @param callbackUrl URL Twilio will request for callbacks.
     * @return this
     */
    public ServiceUpdater setCallbackUrl(final String callbackUrl) {
        return setCallbackUrl(Promoter.uriFromString(callbackUrl));
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Service
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Service update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/Proxy/Services/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Service update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Service.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (autoCreate != null) {
            request.addPostParam("AutoCreate", autoCreate.toString());
        }

        if (callbackUrl != null) {
            request.addPostParam("CallbackUrl", callbackUrl.toString());
        }
    }
}