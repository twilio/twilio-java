/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.flexapi.v1;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class WebChannelCreator extends Creator<WebChannel> {
    private final String flexFlowSid;
    private final String identity;
    private final String customerFriendlyName;
    private final String chatFriendlyName;
    private String chatUniqueName;
    private String preEngagementData;

    /**
     * Construct a new WebChannelCreator.
     *
     * @param flexFlowSid The SID of the FlexFlow
     * @param identity The chat identity
     * @param customerFriendlyName The chat participant's friendly name
     * @param chatFriendlyName The chat channel's friendly name
     */
    public WebChannelCreator(final String flexFlowSid,
                             final String identity,
                             final String customerFriendlyName,
                             final String chatFriendlyName) {
        this.flexFlowSid = flexFlowSid;
        this.identity = identity;
        this.customerFriendlyName = customerFriendlyName;
        this.chatFriendlyName = chatFriendlyName;
    }

    /**
     * The chat channel's unique name..
     *
     * @param chatUniqueName The chat channel's unique name
     * @return this
     */
    public WebChannelCreator setChatUniqueName(final String chatUniqueName) {
        this.chatUniqueName = chatUniqueName;
        return this;
    }

    /**
     * The pre-engagement data..
     *
     * @param preEngagementData The pre-engagement data
     * @return this
     */
    public WebChannelCreator setPreEngagementData(final String preEngagementData) {
        this.preEngagementData = preEngagementData;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created WebChannel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public WebChannel create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.FLEXAPI.toString(),
            "/v1/WebChannels",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("WebChannel creation failed: Unable to connect to server");
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

        return WebChannel.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (flexFlowSid != null) {
            request.addPostParam("FlexFlowSid", flexFlowSid);
        }

        if (identity != null) {
            request.addPostParam("Identity", identity);
        }

        if (customerFriendlyName != null) {
            request.addPostParam("CustomerFriendlyName", customerFriendlyName);
        }

        if (chatFriendlyName != null) {
            request.addPostParam("ChatFriendlyName", chatFriendlyName);
        }

        if (chatUniqueName != null) {
            request.addPostParam("ChatUniqueName", chatUniqueName);
        }

        if (preEngagementData != null) {
            request.addPostParam("PreEngagementData", preEngagementData);
        }
    }
}