/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.sync.service.syncmap;

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
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
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
     * @param pathServiceSid Sync Service Instance SID.
     * @param pathMapSid Sync Map SID or unique name.
     * @param pathIdentity Identity of the user to whom the Sync Map Permission
     *                     applies.
     * @param read Read access.
     * @param write Write access.
     * @param manage Manage access.
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
            Domains.PREVIEW.toString(),
            "/Sync/Services/" + this.pathServiceSid + "/Maps/" + this.pathMapSid + "/Permissions/" + this.pathIdentity + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SyncMapPermission update failed: Unable to connect to server");
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