/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service.document;

import com.twilio.base.Fetcher;
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
public class DocumentPermissionFetcher extends Fetcher<DocumentPermission> {
    private final String pathServiceSid;
    private final String pathDocumentSid;
    private final String pathIdentity;

    /**
     * Construct a new DocumentPermissionFetcher.
     *
     * @param pathServiceSid The SID of the Sync Service with the Document
     *                       Permission resource to fetch
     * @param pathDocumentSid The SID of the Sync Document with the Document
     *                        Permission resource to fetch
     * @param pathIdentity The application-defined string that uniquely identifies
     *                     the User's Document Permission resource to fetch
     */
    public DocumentPermissionFetcher(final String pathServiceSid,
                                     final String pathDocumentSid,
                                     final String pathIdentity) {
        this.pathServiceSid = pathServiceSid;
        this.pathDocumentSid = pathDocumentSid;
        this.pathIdentity = pathIdentity;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched DocumentPermission
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public DocumentPermission fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Documents/" + this.pathDocumentSid + "/Permissions/" + this.pathIdentity + "",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("DocumentPermission fetch failed: Unable to connect to server");
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

        return DocumentPermission.fromJson(response.getStream(), client.getObjectMapper());
    }
}