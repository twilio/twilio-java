/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1.assistant;

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
public class FieldTypeUpdater extends Updater<FieldType> {
    private final String pathAssistantSid;
    private final String pathSid;
    private String friendlyName;
    private String uniqueName;

    /**
     * Construct a new FieldTypeUpdater.
     * 
     * @param pathAssistantSid The unique ID of the Assistant.
     * @param pathSid A 34-character string that uniquely identifies this resource.
     */
    public FieldTypeUpdater(final String pathAssistantSid, 
                            final String pathSid) {
        this.pathAssistantSid = pathAssistantSid;
        this.pathSid = pathSid;
    }

    /**
     * A user-provided string that identifies this resource. It is non-unique and
     * can be up to 255 characters long..
     * 
     * @param friendlyName A user-provided string that identifies this resource. It
     *                     is non-unique and can be up to 255 characters long.
     * @return this
     */
    public FieldTypeUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * A user-provided string that uniquely identifies this resource as an
     * alternative to the sid. Unique up to 64 characters long..
     * 
     * @param uniqueName A user-provided string that uniquely identifies this
     *                   resource as an alternative to the sid. Unique up to 64
     *                   characters long.
     * @return this
     */
    public FieldTypeUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated FieldType
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public FieldType update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.AUTOPILOT.toString(),
            "/v1/Assistants/" + this.pathAssistantSid + "/FieldTypes/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("FieldType update failed: Unable to connect to server");
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

        return FieldType.fromJson(response.getStream(), client.getObjectMapper());
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

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }
    }
}