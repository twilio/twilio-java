/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Video
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.video.v1;

import com.twilio.base.Creator;
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




import java.net.URI;

/*
    * Twilio - Video
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class CompositionSettingsCreator extends Creator<CompositionSettings>{
    private String friendlyName;
    private String awsCredentialsSid;
    private String encryptionKeySid;
    private URI awsS3Url;
    private Boolean awsStorageEnabled;
    private Boolean encryptionEnabled;

    public CompositionSettingsCreator(final String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public CompositionSettingsCreator setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public CompositionSettingsCreator setAwsCredentialsSid(final String awsCredentialsSid){
        this.awsCredentialsSid = awsCredentialsSid;
        return this;
    }
    public CompositionSettingsCreator setEncryptionKeySid(final String encryptionKeySid){
        this.encryptionKeySid = encryptionKeySid;
        return this;
    }
    public CompositionSettingsCreator setAwsS3Url(final URI awsS3Url){
        this.awsS3Url = awsS3Url;
        return this;
    }

    public CompositionSettingsCreator setAwsS3Url(final String awsS3Url){
    this.awsS3Url = Promoter.uriFromString(awsS3Url);
    return this;
    }
    public CompositionSettingsCreator setAwsStorageEnabled(final Boolean awsStorageEnabled){
        this.awsStorageEnabled = awsStorageEnabled;
        return this;
    }
    public CompositionSettingsCreator setEncryptionEnabled(final Boolean encryptionEnabled){
        this.encryptionEnabled = encryptionEnabled;
        return this;
    }

    @Override
    public CompositionSettings create(final TwilioRestClient client){
        String path = "/v1/CompositionSettings/Default";

        path = path.replace("{"+"FriendlyName"+"}", this.friendlyName.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.VIDEO.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("CompositionSettings creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return CompositionSettings.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
    
        }
        if (awsCredentialsSid != null) {
            request.addPostParam("AwsCredentialsSid", awsCredentialsSid);
    
        }
        if (encryptionKeySid != null) {
            request.addPostParam("EncryptionKeySid", encryptionKeySid);
    
        }
        if (awsS3Url != null) {
            request.addPostParam("AwsS3Url", awsS3Url.toString());
    
        }
        if (awsStorageEnabled != null) {
            request.addPostParam("AwsStorageEnabled", awsStorageEnabled.toString());
    
        }
        if (encryptionEnabled != null) {
            request.addPostParam("EncryptionEnabled", encryptionEnabled.toString());
    
        }
    }
}
