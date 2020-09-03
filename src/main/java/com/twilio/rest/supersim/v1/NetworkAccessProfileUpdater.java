/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.supersim.v1;

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
public class NetworkAccessProfileUpdater extends Updater<NetworkAccessProfile> {
    private final String pathSid;
    private String uniqueName;

    /**
     * Construct a new NetworkAccessProfileUpdater.
     *
     * @param pathSid The SID of the resource to update
     */
    public NetworkAccessProfileUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * The new unique name of the Network Access Profile..
     *
     * @param uniqueName The new unique name of the resource
     * @return this
     */
    public NetworkAccessProfileUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated NetworkAccessProfile
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public NetworkAccessProfile update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SUPERSIM.toString(),
            "/v1/NetworkAccessProfiles/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("NetworkAccessProfile update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return NetworkAccessProfile.fromJson(response.getStream(), client.getObjectMapper());
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
    }
}