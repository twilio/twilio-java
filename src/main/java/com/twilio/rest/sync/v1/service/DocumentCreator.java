/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service;

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

public class DocumentCreator extends Creator<Document> {
    private final String pathServiceSid;
    private String uniqueName;
    private Map<String, Object> data;
    private Integer ttl;

    /**
     * Construct a new DocumentCreator.
     *
     * @param pathServiceSid The SID of the Sync Service to associate the Document
     *                       resource to create with
     */
    public DocumentCreator(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    /**
     * An application-defined string that uniquely identifies the Sync Document.
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   Sync Document
     * @return this
     */
    public DocumentCreator setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * A JSON string that represents an arbitrary, schema-less object that the Sync
     * Document stores. Can be up to 16 KiB in length..
     *
     * @param data A JSON string that represents an arbitrary, schema-less object
     *             that the Sync Document stores
     * @return this
     */
    public DocumentCreator setData(final Map<String, Object> data) {
        this.data = data;
        return this;
    }

    /**
     * How long, <a
     * href="https://www.twilio.com/docs/sync/limits#sync-payload-limits">in
     * seconds</a>, before the Sync Document expires and is deleted (the Sync
     * Document's time-to-live)..
     *
     * @param ttl How long, in seconds, before the Sync Document expires and is
     *            deleted
     * @return this
     */
    public DocumentCreator setTtl(final Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Document
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Document create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Documents"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Document creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Document.fromJson(response.getStream(), client.getObjectMapper());
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

        if (data != null) {
            request.addPostParam("Data", Converter.mapToJson(data));
        }

        if (ttl != null) {
            request.addPostParam("Ttl", ttl.toString());
        }
    }
}