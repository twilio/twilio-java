/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Preview
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.preview.sync.service.document;

import com.twilio.base.Deleter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class DocumentPermissionDeleter extends Deleter<DocumentPermission> {

    private String pathServiceSid;
    private String pathDocumentSid;
    private String pathIdentity;

    public DocumentPermissionDeleter(
        final String pathServiceSid,
        final String pathDocumentSid,
        final String pathIdentity
    ) {
        this.pathServiceSid = pathServiceSid;
        this.pathDocumentSid = pathDocumentSid;
        this.pathIdentity = pathIdentity;
    }

    @Override
    public boolean delete(final TwilioRestClient client) {
        String path =
            "/Sync/Services/{ServiceSid}/Documents/{DocumentSid}/Permissions/{Identity}";

        path =
            path.replace(
                "{" + "ServiceSid" + "}",
                this.pathServiceSid.toString()
            );
        path =
            path.replace(
                "{" + "DocumentSid" + "}",
                this.pathDocumentSid.toString()
            );
        path =
            path.replace("{" + "Identity" + "}", this.pathIdentity.toString());

        Request request = new Request(
            HttpMethod.DELETE,
            Domains.PREVIEW.toString(),
            path
        );
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "DocumentPermission delete failed: Unable to connect to server"
            );
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(
                response.getStream(),
                client.getObjectMapper()
            );
            if (restException == null) {
                throw new ApiException(
                    "Server Error, no content",
                    response.getStatusCode()
                );
            }
            throw new ApiException(restException);
        }
        return response.getStatusCode() == 204;
    }
}
