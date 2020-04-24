/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.understand.assistant;

import com.twilio.base.Creator;
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
public class FieldTypeCreator extends Creator<FieldType> {
    private final String pathAssistantSid;
    private final String uniqueName;
    private String friendlyName;

    /**
     * Construct a new FieldTypeCreator.
     *
     * @param pathAssistantSid The assistant_sid
     * @param uniqueName A user-provided string that uniquely identifies this
     *                   resource as an alternative to the sid. Unique up to 64
     *                   characters long.
     */
    public FieldTypeCreator(final String pathAssistantSid,
                            final String uniqueName) {
        this.pathAssistantSid = pathAssistantSid;
        this.uniqueName = uniqueName;
    }

    /**
     * A user-provided string that identifies this resource. It is non-unique and
     * can up to 255 characters long..
     *
     * @param friendlyName A user-provided string that identifies this resource. It
     *                     is non-unique and can up to 255 characters long.
     * @return this
     */
    public FieldTypeCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created FieldType
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public FieldType create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/understand/Assistants/" + this.pathAssistantSid + "/FieldTypes",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("FieldType creation failed: Unable to connect to server");
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
                restException.getDetails(),
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
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }
}