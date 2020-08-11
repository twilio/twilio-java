/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.sync.service.synclist;

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
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class SyncListItemDeleter extends Deleter<SyncListItem> {
    private final String pathServiceSid;
    private final String pathListSid;
    private final Integer pathIndex;

    /**
     * Construct a new SyncListItemDeleter.
     *
     * @param pathServiceSid The service_sid
     * @param pathListSid The list_sid
     * @param pathIndex The index
     */
    public SyncListItemDeleter(final String pathServiceSid,
                               final String pathListSid,
                               final Integer pathIndex) {
        this.pathServiceSid = pathServiceSid;
        this.pathListSid = pathListSid;
        this.pathIndex = pathIndex;
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
            Domains.PREVIEW.toString(),
            "/Sync/Services/" + this.pathServiceSid + "/Lists/" + this.pathListSid + "/Items/" + this.pathIndex + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SyncListItem delete failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return response.getStatusCode() == 204;
    }
}