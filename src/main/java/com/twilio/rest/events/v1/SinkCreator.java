/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.events.v1;

import com.twilio.base.Creator;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.util.Map;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class SinkCreator extends Creator<Sink> {
    private final String description;
    private final Map<String, Object> sinkConfiguration;
    private final Sink.SinkType sinkType;

    /**
     * Construct a new SinkCreator.
     *
     * @param description Sink Description.
     * @param sinkConfiguration JSON Sink configuration.
     * @param sinkType Sink type.
     */
    public SinkCreator(final String description,
                       final Map<String, Object> sinkConfiguration,
                       final Sink.SinkType sinkType) {
        this.description = description;
        this.sinkConfiguration = sinkConfiguration;
        this.sinkType = sinkType;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Sink
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Sink create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.EVENTS.toString(),
            "/v1/Sinks"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Sink creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Sink.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (description != null) {
            request.addPostParam("Description", description);
        }

        if (sinkConfiguration != null) {
            request.addPostParam("SinkConfiguration", Converter.mapToJson(sinkConfiguration));
        }

        if (sinkType != null) {
            request.addPostParam("SinkType", sinkType.toString());
        }
    }
}