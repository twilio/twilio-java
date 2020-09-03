/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v2.service.user;

import com.twilio.base.Deleter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class UserChannelDeleter extends Deleter<UserChannel> {
    private final String pathServiceSid;
    private final String pathUserSid;
    private final String pathChannelSid;

    /**
     * Construct a new UserChannelDeleter.
     *
     * @param pathServiceSid The SID of the Service to read the resources from
     * @param pathUserSid The SID of the User to fetch the User Channel resources
     *                    from
     * @param pathChannelSid The SID of the Channel the resource belongs to
     */
    public UserChannelDeleter(final String pathServiceSid,
                              final String pathUserSid,
                              final String pathChannelSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathUserSid = pathUserSid;
        this.pathChannelSid = pathChannelSid;
    }

    /**
     * Make the request to the Twilio API to perform the delete.
     *
     * @param client TwilioRestClient with which to make the request
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public boolean delete(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.DELETE,
            Domains.CHAT.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Users/" + this.pathUserSid + "/Channels/" + this.pathChannelSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("UserChannel delete failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return response.getStatusCode() == 204;
    }
}
