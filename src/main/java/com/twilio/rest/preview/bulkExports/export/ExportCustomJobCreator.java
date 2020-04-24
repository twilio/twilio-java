/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.bulkExports.export;

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
public class ExportCustomJobCreator extends Creator<ExportCustomJob> {
    private final String pathResourceType;
    private String friendlyName;
    private String startDay;
    private String endDay;
    private String webhookUrl;
    private String webhookMethod;
    private String email;

    /**
     * Construct a new ExportCustomJobCreator.
     *
     * @param pathResourceType The type of communication – Messages, Calls
     */
    public ExportCustomJobCreator(final String pathResourceType) {
        this.pathResourceType = pathResourceType;
    }

    /**
     * The friendly_name.
     *
     * @param friendlyName The friendly_name
     * @return this
     */
    public ExportCustomJobCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The start_day.
     *
     * @param startDay The start_day
     * @return this
     */
    public ExportCustomJobCreator setStartDay(final String startDay) {
        this.startDay = startDay;
        return this;
    }

    /**
     * The end_day.
     *
     * @param endDay The end_day
     * @return this
     */
    public ExportCustomJobCreator setEndDay(final String endDay) {
        this.endDay = endDay;
        return this;
    }

    /**
     * The webhook_url.
     *
     * @param webhookUrl The webhook_url
     * @return this
     */
    public ExportCustomJobCreator setWebhookUrl(final String webhookUrl) {
        this.webhookUrl = webhookUrl;
        return this;
    }

    /**
     * The webhook_method.
     *
     * @param webhookMethod The webhook_method
     * @return this
     */
    public ExportCustomJobCreator setWebhookMethod(final String webhookMethod) {
        this.webhookMethod = webhookMethod;
        return this;
    }

    /**
     * The email.
     *
     * @param email The email
     * @return this
     */
    public ExportCustomJobCreator setEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created ExportCustomJob
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public ExportCustomJob create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/BulkExports/Exports/" + this.pathResourceType + "/Jobs",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ExportCustomJob creation failed: Unable to connect to server");
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

        return ExportCustomJob.fromJson(response.getStream(), client.getObjectMapper());
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

        if (startDay != null) {
            request.addPostParam("StartDay", startDay);
        }

        if (endDay != null) {
            request.addPostParam("EndDay", endDay);
        }

        if (webhookUrl != null) {
            request.addPostParam("WebhookUrl", webhookUrl);
        }

        if (webhookMethod != null) {
            request.addPostParam("WebhookMethod", webhookMethod);
        }

        if (email != null) {
            request.addPostParam("Email", email);
        }
    }
}