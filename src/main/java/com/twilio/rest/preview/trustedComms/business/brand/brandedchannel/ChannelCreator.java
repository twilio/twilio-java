/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.trustedComms.business.brand.brandedchannel;

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
public class ChannelCreator extends Creator<Channel> {
    private final String pathBusinessSid;
    private final String pathBrandSid;
    private final String pathBrandedChannelSid;
    private final String phoneNumberSid;

    /**
     * Construct a new ChannelCreator.
     *
     * @param pathBusinessSid Business Sid.
     * @param pathBrandSid Brand Sid.
     * @param pathBrandedChannelSid Branded Channel Sid.
     * @param phoneNumberSid Phone Number Sid to be branded.
     */
    public ChannelCreator(final String pathBusinessSid,
                          final String pathBrandSid,
                          final String pathBrandedChannelSid,
                          final String phoneNumberSid) {
        this.pathBusinessSid = pathBusinessSid;
        this.pathBrandSid = pathBrandSid;
        this.pathBrandedChannelSid = pathBrandedChannelSid;
        this.phoneNumberSid = phoneNumberSid;
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
            Domains.PREVIEW.toString(),
            "/TrustedComms/Businesses/" + this.pathBusinessSid + "/Brands/" + this.pathBrandSid + "/BrandedChannels/" + this.pathBrandedChannelSid + "/Channels"
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
            throw new ApiException(restException);
        }

        return Channel.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (phoneNumberSid != null) {
            request.addPostParam("PhoneNumberSid", phoneNumberSid);
        }
    }
}