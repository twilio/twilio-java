/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service.syncmap;

import com.twilio.base.Updater;
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
public class SyncMapPermissionUpdater extends Updater<SyncMapPermission> {
    private final String pathServiceSid;
    private final String pathMapSid;
    private final String pathIdentity;
    private final Boolean read;
    private final Boolean write;
    private final Boolean manage;

    /**
     * Construct a new SyncMapPermissionUpdater.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync Map
     *                       Permission resource to update
     * @param pathMapSid The SID of the Sync Map with the Sync Map Permission
     *                   resource to update
     * @param pathIdentity The application-defined string that uniquely identifies
     *                     the User's Sync Map Permission resource to update
     * @param read Read access
     * @param write Write access
     * @param manage Manage access
     */
    public SyncMapPermissionUpdater(final String pathServiceSid,
                                    final String pathMapSid,
                                    final String pathIdentity,
                                    final Boolean read,
                                    final Boolean write,
                                    final Boolean manage) {
        this.pathServiceSid = pathServiceSid;
        this.pathMapSid = pathMapSid;
        this.pathIdentity = pathIdentity;
        this.read = read;
        this.write = write;
        this.manage = manage;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated SyncMapPermission
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public SyncMapPermission update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Maps/" + this.pathMapSid + "/Permissions/" + this.pathIdentity + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SyncMapPermission update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return SyncMapPermission.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (read != null) {
            request.addPostParam("Read", read.toString());
        }

        if (write != null) {
            request.addPostParam("Write", write.toString());
        }

        if (manage != null) {
            request.addPostParam("Manage", manage.toString());
        }
    }
}
