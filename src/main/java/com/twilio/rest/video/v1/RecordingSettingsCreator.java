/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
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

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class RecordingSettingsCreator extends Creator<RecordingSettings> {
    private final String friendlyName;
    private String awsCredentialsSid;
    private String encryptionKeySid;
    private URI awsS3Url;
    private Boolean awsStorageEnabled;
    private Boolean encryptionEnabled;

    /**
     * Construct a new RecordingSettingsCreator.
     *
     * @param friendlyName A string to describe the resource
     */
    public RecordingSettingsCreator(final String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * The SID of the stored Credential resource..
     *
     * @param awsCredentialsSid The SID of the stored Credential resource
     * @return this
     */
    public RecordingSettingsCreator setAwsCredentialsSid(final String awsCredentialsSid) {
        this.awsCredentialsSid = awsCredentialsSid;
        return this;
    }

    /**
     * The SID of the Public Key resource to use for encryption..
     *
     * @param encryptionKeySid The SID of the Public Key resource to use for
     *                         encryption
     * @return this
     */
    public RecordingSettingsCreator setEncryptionKeySid(final String encryptionKeySid) {
        this.encryptionKeySid = encryptionKeySid;
        return this;
    }

    /**
     * The URL of the AWS S3 bucket where the recordings should be stored. We only
     * support DNS-compliant URLs like
     * `http://&lt;my-bucket&gt;.s3-&lt;aws-region&gt;.amazonaws.com/recordings`,
     * where `recordings` is the path in which you want the recordings to be
     * stored..
     *
     * @param awsS3Url The URL of the AWS S3 bucket where the recordings should be
     *                 stored
     * @return this
     */
    public RecordingSettingsCreator setAwsS3Url(final URI awsS3Url) {
        this.awsS3Url = awsS3Url;
        return this;
    }

    /**
     * The URL of the AWS S3 bucket where the recordings should be stored. We only
     * support DNS-compliant URLs like
     * `http://&lt;my-bucket&gt;.s3-&lt;aws-region&gt;.amazonaws.com/recordings`,
     * where `recordings` is the path in which you want the recordings to be
     * stored..
     *
     * @param awsS3Url The URL of the AWS S3 bucket where the recordings should be
     *                 stored
     * @return this
     */
    public RecordingSettingsCreator setAwsS3Url(final String awsS3Url) {
        return setAwsS3Url(Promoter.uriFromString(awsS3Url));
    }

    /**
     * Whether all recordings should be written to the `aws_s3_url`. When `false`,
     * all recordings are stored in our cloud..
     *
     * @param awsStorageEnabled Whether all recordings should be written to the
     *                          aws_s3_url
     * @return this
     */
    public RecordingSettingsCreator setAwsStorageEnabled(final Boolean awsStorageEnabled) {
        this.awsStorageEnabled = awsStorageEnabled;
        return this;
    }

    /**
     * Whether all recordings should be stored in an encrypted form. The default is
     * `false`..
     *
     * @param encryptionEnabled Whether all recordings should be stored in an
     *                          encrypted form
     * @return this
     */
    public RecordingSettingsCreator setEncryptionEnabled(final Boolean encryptionEnabled) {
        this.encryptionEnabled = encryptionEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created RecordingSettings
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public RecordingSettings create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.VIDEO.toString(),
            "/v1/RecordingSettings/Default",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("RecordingSettings creation failed: Unable to connect to server");
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

        return RecordingSettings.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
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