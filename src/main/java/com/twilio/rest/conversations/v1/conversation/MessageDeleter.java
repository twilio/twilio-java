/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1.conversation;

import com.twilio.base.Deleter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class MessageDeleter extends Deleter<Message> {
    private final String pathConversationSid;
    private final String pathSid;

    /**
     * Construct a new MessageDeleter.
     *
     * @param pathConversationSid The unique id of the Conversation for this
     *                            message.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     */
    public MessageDeleter(final String pathConversationSid,
                          final String pathSid) {
        this.pathConversationSid = pathConversationSid;
        this.pathSid = pathSid;
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
            Domains.CONVERSATIONS.toString(),
            "/v1/Conversations/" + this.pathConversationSid + "/Messages/" + this.pathSid + "",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Message delete failed: Unable to connect to server");
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

        return response.getStatusCode() == 204;
    }
}