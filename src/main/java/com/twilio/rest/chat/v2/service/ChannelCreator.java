/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v2.service;

import com.twilio.base.Creator;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.time.ZonedDateTime;

public class ChannelCreator extends Creator<Channel> {
    private final String pathServiceSid;
    private String friendlyName;
    private String uniqueName;
    private String attributes;
    private Channel.ChannelType type;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private String createdBy;

    /**
     * Construct a new ChannelCreator.
     *
     * @param pathServiceSid The SID of the Service to create the Channel resource
     *                       under
     */
    public ChannelCreator(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    /**
     * A descriptive string that you create to describe the new resource. It can be
     * up to 64 characters long..
     *
     * @param friendlyName A string to describe the new resource
     * @return this
     */
    public ChannelCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * An application-defined string that uniquely identifies the resource. It can
     * be used to address the resource in place of the Channel resource's `sid` in
     * the URL. This value must be 64 characters or less in length and be unique
     * within the Service..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   Channel resource
     * @return this
     */
    public ChannelCreator setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * A valid JSON string that contains application-specific data..
     *
     * @param attributes A valid JSON string that contains application-specific data
     * @return this
     */
    public ChannelCreator setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The visibility of the channel. Can be: `public` or `private` and defaults to
     * `public`..
     *
     * @param type The visibility of the channel
     * @return this
     */
    public ChannelCreator setType(final Channel.ChannelType type) {
        this.type = type;
        return this;
    }

    /**
     * The date, specified in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601)
     * format, to assign to the resource as the date it was created. The default
     * value is the current time set by the Chat service.  Note that this should
     * only be used in cases where a Channel is being recreated from a
     * backup/separate source..
     *
     * @param dateCreated The ISO 8601 date and time in GMT when the resource was
     *                    created
     * @return this
     */
    public ChannelCreator setDateCreated(final ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    /**
     * The date, specified in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601)
     * format, to assign to the resource as the date it was last updated. The
     * default value is `null`. Note that this parameter should only be used in
     * cases where a Channel is being recreated from a backup/separate source  and
     * where a Message was previously updated..
     *
     * @param dateUpdated The ISO 8601 date and time in GMT when the resource was
     *                    updated
     * @return this
     */
    public ChannelCreator setDateUpdated(final ZonedDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    /**
     * The `identity` of the User that created the channel. Default is: `system`..
     *
     * @param createdBy The identity of the User that created the Channel
     * @return this
     */
    public ChannelCreator setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Channel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Channel create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.CHAT.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Channels",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Channel creation failed: Unable to connect to server");
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

        return Channel.fromJson(response.getStream(), client.getObjectMapper());
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

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }

        if (type != null) {
            request.addPostParam("Type", type.toString());
        }

        if (dateCreated != null) {
            request.addPostParam("DateCreated", dateCreated.toString());
        }

        if (dateUpdated != null) {
            request.addPostParam("DateUpdated", dateUpdated.toString());
        }

        if (createdBy != null) {
            request.addPostParam("CreatedBy", createdBy);
        }
    }
}
