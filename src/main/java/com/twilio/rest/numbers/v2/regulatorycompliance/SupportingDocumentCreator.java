/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.numbers.v2.regulatorycompliance;

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

public class SupportingDocumentCreator extends Creator<SupportingDocument> {
    private final String friendlyName;
    private final String type;
    private Map<String, Object> attributes;

    /**
     * Construct a new SupportingDocumentCreator.
     *
     * @param friendlyName The string that you assigned to describe the resource
     * @param type The type of the Supporting Document
     */
    public SupportingDocumentCreator(final String friendlyName,
                                     final String type) {
        this.friendlyName = friendlyName;
        this.type = type;
    }

    /**
     * The set of parameters that are the attributes of the Supporting Documents
     * resource which are derived Supporting Document Types..
     *
     * @param attributes The set of parameters that compose the Supporting
     *                   Documents resource
     * @return this
     */
    public SupportingDocumentCreator setAttributes(final Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created SupportingDocument
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public SupportingDocument create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.NUMBERS.toString(),
            "/v2/RegulatoryCompliance/SupportingDocuments"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SupportingDocument creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return SupportingDocument.fromJson(response.getStream(), client.getObjectMapper());
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

        if (type != null) {
            request.addPostParam("Type", type);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", Converter.mapToJson(attributes));
        }
    }
}