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

package com.twilio.rest.preview.hostedNumbers;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.util.List;

import java.util.List;




/*
    * Twilio - Preview
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class AuthorizationDocumentCreator extends Creator<AuthorizationDocument>{
    private List<String> hostedNumberOrderSids;
    private String addressSid;
    private String email;
    private String contactTitle;
    private String contactPhoneNumber;
    private List<String> ccEmails;

    public AuthorizationDocumentCreator(final List<String> hostedNumberOrderSids, final String addressSid, final String email, final String contactTitle, final String contactPhoneNumber) {
        this.hostedNumberOrderSids = hostedNumberOrderSids;
        this.addressSid = addressSid;
        this.email = email;
        this.contactTitle = contactTitle;
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public AuthorizationDocumentCreator setHostedNumberOrderSids(final List<String> hostedNumberOrderSids){
        this.hostedNumberOrderSids = hostedNumberOrderSids;
        return this;
    }
    public AuthorizationDocumentCreator setAddressSid(final String addressSid){
        this.addressSid = addressSid;
        return this;
    }
    public AuthorizationDocumentCreator setEmail(final String email){
        this.email = email;
        return this;
    }
    public AuthorizationDocumentCreator setContactTitle(final String contactTitle){
        this.contactTitle = contactTitle;
        return this;
    }
    public AuthorizationDocumentCreator setContactPhoneNumber(final String contactPhoneNumber){
        this.contactPhoneNumber = contactPhoneNumber;
        return this;
    }
    public AuthorizationDocumentCreator setCcEmails(final List<String> ccEmails){
        this.ccEmails = ccEmails;
        return this;
    }

    @Override
    public AuthorizationDocument create(final TwilioRestClient client){
        String path = "/HostedNumbers/AuthorizationDocuments";

        path = path.replace("{"+"HostedNumberOrderSids"+"}", this.hostedNumberOrderSids.toString());
        path = path.replace("{"+"AddressSid"+"}", this.addressSid.toString());
        path = path.replace("{"+"Email"+"}", this.email.toString());
        path = path.replace("{"+"ContactTitle"+"}", this.contactTitle.toString());
        path = path.replace("{"+"ContactPhoneNumber"+"}", this.contactPhoneNumber.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("AuthorizationDocument creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return AuthorizationDocument.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (hostedNumberOrderSids != null) {
            for (String prop : hostedNumberOrderSids) {
                request.addPostParam("HostedNumberOrderSids", prop);
            }
    
        }
        if (addressSid != null) {
            request.addPostParam("AddressSid", addressSid);
    
        }
        if (email != null) {
            request.addPostParam("Email", email);
    
        }
        if (contactTitle != null) {
            request.addPostParam("ContactTitle", contactTitle);
    
        }
        if (contactPhoneNumber != null) {
            request.addPostParam("ContactPhoneNumber", contactPhoneNumber);
    
        }
        if (ccEmails != null) {
            for (String prop : ccEmails) {
                request.addPostParam("CcEmails", prop);
            }
    
        }
    }
}
