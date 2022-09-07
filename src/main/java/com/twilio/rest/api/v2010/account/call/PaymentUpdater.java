/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account.call;

import com.twilio.base.Updater;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;




/*
    * Twilio - Api
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class PaymentUpdater extends Updater<Payment>{
    private String callSid;
    private String sid;
    private String idempotencyKey;
    private URI statusCallback;
    private String accountSid;
    private Payment.Capture capture;
    private Payment.Status status;

    public PaymentUpdater(final String callSid, final String sid, final String idempotencyKey, final URI statusCallback){
        this.callSid = callSid;
        this.sid = sid;
        this.idempotencyKey = idempotencyKey;
        this.statusCallback = statusCallback;
    }
    public PaymentUpdater(final String accountSid, final String callSid, final String sid, final String idempotencyKey, final URI statusCallback){
        this.accountSid = accountSid;
        this.callSid = callSid;
        this.sid = sid;
        this.idempotencyKey = idempotencyKey;
        this.statusCallback = statusCallback;
    }

    public PaymentUpdater setIdempotencyKey(final String idempotencyKey){
        this.idempotencyKey = idempotencyKey;
        return this;
    }
    public PaymentUpdater setStatusCallback(final URI statusCallback){
        this.statusCallback = statusCallback;
        return this;
    }

    public PaymentUpdater setStatusCallback(final String statusCallback){
    this.statusCallback = Promoter.uriFromString(statusCallback);
    return this;
    }
    public PaymentUpdater setCapture(final Payment.Capture capture){
        this.capture = capture;
        return this;
    }
    public PaymentUpdater setStatus(final Payment.Status status){
        this.status = status;
        return this;
    }

    @Override
    public Payment update(final TwilioRestClient client){
        String path = "/2010-04-01/Accounts/{AccountSid}/Calls/{CallSid}/Payments/{Sid}.json";

        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        path = path.replace("{"+"AccountSid"+"}", this.accountSid.toString());
        path = path.replace("{"+"CallSid"+"}", this.callSid.toString());
        path = path.replace("{"+"Sid"+"}", this.sid.toString());
        path = path.replace("{"+"IdempotencyKey"+"}", this.idempotencyKey.toString());
        path = path.replace("{"+"StatusCallback"+"}", this.statusCallback.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Payment update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Payment.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (idempotencyKey != null) {
            request.addPostParam("IdempotencyKey", idempotencyKey);
    
        }
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
    
        }
        if (capture != null) {
            request.addPostParam("Capture", capture.toString());
    
        }
        if (status != null) {
            request.addPostParam("Status", status.toString());
    
        }
    }
}
