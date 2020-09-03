/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.incomingphonenumber.assignedaddon;

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
public class AssignedAddOnExtensionFetcher extends Fetcher<AssignedAddOnExtension> {
    private String pathAccountSid;
    private final String pathResourceSid;
    private final String pathAssignedAddOnSid;
    private final String pathSid;

    /**
     * Construct a new AssignedAddOnExtensionFetcher.
     *
     * @param pathResourceSid The SID of the Phone Number to which the Add-on is
     *                        assigned
     * @param pathAssignedAddOnSid The SID that uniquely identifies the assigned
     *                             Add-on installation
     * @param pathSid The unique string that identifies the resource
     */
    public AssignedAddOnExtensionFetcher(final String pathResourceSid,
                                         final String pathAssignedAddOnSid,
                                         final String pathSid) {
        this.pathResourceSid = pathResourceSid;
        this.pathAssignedAddOnSid = pathAssignedAddOnSid;
        this.pathSid = pathSid;
    }

    /**
     * Construct a new AssignedAddOnExtensionFetcher.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       fetch
     * @param pathResourceSid The SID of the Phone Number to which the Add-on is
     *                        assigned
     * @param pathAssignedAddOnSid The SID that uniquely identifies the assigned
     *                             Add-on installation
     * @param pathSid The unique string that identifies the resource
     */
    public AssignedAddOnExtensionFetcher(final String pathAccountSid,
                                         final String pathResourceSid,
                                         final String pathAssignedAddOnSid,
                                         final String pathSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathResourceSid = pathResourceSid;
        this.pathAssignedAddOnSid = pathAssignedAddOnSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched AssignedAddOnExtension
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public AssignedAddOnExtension fetch(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/IncomingPhoneNumbers/" + this.pathResourceSid + "/AssignedAddOns/" + this.pathAssignedAddOnSid + "/Extensions/" + this.pathSid + ".json"
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("AssignedAddOnExtension fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return AssignedAddOnExtension.fromJson(response.getStream(), client.getObjectMapper());
    }
}
