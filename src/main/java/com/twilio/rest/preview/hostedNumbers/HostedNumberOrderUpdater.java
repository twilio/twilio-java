/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.hostedNumbers;

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

import java.util.List;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
public class HostedNumberOrderUpdater extends Updater<HostedNumberOrder> {
    private final String pathSid;
    private String friendlyName;
    private String uniqueName;
    private String email;
    private List<String> ccEmails;
    private HostedNumberOrder.Status status;
    private String verificationCode;
    private HostedNumberOrder.VerificationType verificationType;
    private String verificationDocumentSid;
    private String extension;
    private Integer callDelay;

    /**
     * Construct a new HostedNumberOrderUpdater.
     *
     * @param pathSid The sid
     */
    public HostedNumberOrderUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * A 64 character string that is a human readable text that describes this
     * resource..
     *
     * @param friendlyName A human readable description of this resource.
     * @return this
     */
    public HostedNumberOrderUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Provides a unique and addressable name to be assigned to this
     * HostedNumberOrder, assigned by the developer, to be optionally used in
     * addition to SID..
     *
     * @param uniqueName A unique, developer assigned name of this
     *                   HostedNumberOrder.
     * @return this
     */
    public HostedNumberOrderUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * Email of the owner of this phone number that is being hosted..
     *
     * @param email Email.
     * @return this
     */
    public HostedNumberOrderUpdater setEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Optional. A list of emails that LOA document for this HostedNumberOrder will
     * be carbon copied to..
     *
     * @param ccEmails A list of emails.
     * @return this
     */
    public HostedNumberOrderUpdater setCcEmails(final List<String> ccEmails) {
        this.ccEmails = ccEmails;
        return this;
    }

    /**
     * Optional. A list of emails that LOA document for this HostedNumberOrder will
     * be carbon copied to..
     *
     * @param ccEmails A list of emails.
     * @return this
     */
    public HostedNumberOrderUpdater setCcEmails(final String ccEmails) {
        return setCcEmails(Promoter.listOfOne(ccEmails));
    }

    /**
     * User can only post to `pending-verification` status to transition the
     * HostedNumberOrder to initiate a verification call or verification of
     * ownership with a copy of a phone bill..
     *
     * @param status The Status of this HostedNumberOrder.
     * @return this
     */
    public HostedNumberOrderUpdater setStatus(final HostedNumberOrder.Status status) {
        this.status = status;
        return this;
    }

    /**
     * A verification code that is given to the user via a phone call to the phone
     * number that is being hosted..
     *
     * @param verificationCode A verification code.
     * @return this
     */
    public HostedNumberOrderUpdater setVerificationCode(final String verificationCode) {
        this.verificationCode = verificationCode;
        return this;
    }

    /**
     * Optional. The method used for verifying ownership of the number to be
     * hosted. One of phone-call (default) or phone-bill..
     *
     * @param verificationType Verification Type.
     * @return this
     */
    public HostedNumberOrderUpdater setVerificationType(final HostedNumberOrder.VerificationType verificationType) {
        this.verificationType = verificationType;
        return this;
    }

    /**
     * Optional. The unique sid identifier of the Identity Document that represents
     * the document for verifying ownership of the number to be hosted. Required
     * when VerificationType is phone-bill..
     *
     * @param verificationDocumentSid Verification Document Sid
     * @return this
     */
    public HostedNumberOrderUpdater setVerificationDocumentSid(final String verificationDocumentSid) {
        this.verificationDocumentSid = verificationDocumentSid;
        return this;
    }

    /**
     * Digits to dial after connecting the verification call..
     *
     * @param extension Digits to dial after connecting the verification call.
     * @return this
     */
    public HostedNumberOrderUpdater setExtension(final String extension) {
        this.extension = extension;
        return this;
    }

    /**
     * The number of seconds, between 0 and 60, to delay before initiating the
     * verification call. Defaults to 0..
     *
     * @param callDelay The number of seconds, between 0 and 60, to delay before
     *                  initiating the verification call.
     * @return this
     */
    public HostedNumberOrderUpdater setCallDelay(final Integer callDelay) {
        this.callDelay = callDelay;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated HostedNumberOrder
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public HostedNumberOrder update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/HostedNumbers/HostedNumberOrders/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("HostedNumberOrder update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return HostedNumberOrder.fromJson(response.getStream(), client.getObjectMapper());
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

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (email != null) {
            request.addPostParam("Email", email);
        }

        if (ccEmails != null) {
            for (String prop : ccEmails) {
                request.addPostParam("CcEmails", prop);
            }
        }

        if (status != null) {
            request.addPostParam("Status", status.toString());
        }

        if (verificationCode != null) {
            request.addPostParam("VerificationCode", verificationCode);
        }

        if (verificationType != null) {
            request.addPostParam("VerificationType", verificationType.toString());
        }

        if (verificationDocumentSid != null) {
            request.addPostParam("VerificationDocumentSid", verificationDocumentSid);
        }

        if (extension != null) {
            request.addPostParam("Extension", extension);
        }

        if (callDelay != null) {
            request.addPostParam("CallDelay", callDelay.toString());
        }
    }
}