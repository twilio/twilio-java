/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.wireless;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class RatePlanUpdater extends Updater<RatePlan> {
    private final String pathSid;
    private String uniqueName;
    private String friendlyName;

    /**
     * Construct a new RatePlanUpdater.
     *
     * @param pathSid The sid
     */
    public RatePlanUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * The unique_name.
     *
     * @param uniqueName The unique_name
     * @return this
     */
    public RatePlanUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * The friendly_name.
     *
     * @param friendlyName The friendly_name
     * @return this
     */
    public RatePlanUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated RatePlan
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public RatePlan update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/wireless/RatePlans/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("RatePlan update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return RatePlan.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }
}