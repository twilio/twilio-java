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
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class AuthorizationDocumentUpdater extends Updater<AuthorizationDocument> {
    private final String pathSid;
    private List<String> hostedNumberOrderSids;
    private String addressSid;
    private String email;
    private List<String> ccEmails;
    private AuthorizationDocument.Status status;
    private String contactTitle;
    private String contactPhoneNumber;

    /**
     * Construct a new AuthorizationDocumentUpdater.
     *
     * @param pathSid The sid
     */
    public AuthorizationDocumentUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * A list of HostedNumberOrder sids that this AuthorizationDocument will
     * authorize for hosting phone number capabilities on Twilio's platform..
     *
     * @param hostedNumberOrderSids A list of HostedNumberOrder sids.
     * @return this
     */
    public AuthorizationDocumentUpdater setHostedNumberOrderSids(final List<String> hostedNumberOrderSids) {
        this.hostedNumberOrderSids = hostedNumberOrderSids;
        return this;
    }

    /**
     * A list of HostedNumberOrder sids that this AuthorizationDocument will
     * authorize for hosting phone number capabilities on Twilio's platform..
     *
     * @param hostedNumberOrderSids A list of HostedNumberOrder sids.
     * @return this
     */
    public AuthorizationDocumentUpdater setHostedNumberOrderSids(final String hostedNumberOrderSids) {
        return setHostedNumberOrderSids(Promoter.listOfOne(hostedNumberOrderSids));
    }

    /**
     * A 34 character string that uniquely identifies the Address resource that is
     * associated with this AuthorizationDocument..
     *
     * @param addressSid Address sid.
     * @return this
     */
    public AuthorizationDocumentUpdater setAddressSid(final String addressSid) {
        this.addressSid = addressSid;
        return this;
    }

    /**
     * Email that this AuthorizationDocument will be sent to for signing..
     *
     * @param email Email.
     * @return this
     */
    public AuthorizationDocumentUpdater setEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Email recipients who will be informed when an Authorization Document has been
     * sent and signed.
     *
     * @param ccEmails A list of emails.
     * @return this
     */
    public AuthorizationDocumentUpdater setCcEmails(final List<String> ccEmails) {
        this.ccEmails = ccEmails;
        return this;
    }

    /**
     * Email recipients who will be informed when an Authorization Document has been
     * sent and signed.
     *
     * @param ccEmails A list of emails.
     * @return this
     */
    public AuthorizationDocumentUpdater setCcEmails(final String ccEmails) {
        return setCcEmails(Promoter.listOfOne(ccEmails));
    }

    /**
     * Status of an instance resource. It can hold one of the values: 1. opened 2.
     * signing, 3. signed LOA, 4. canceled, 5. failed. See the section entitled <a
     * href="https://www.twilio.com/docs/api/phone-numbers/hosted-number-authorization-documents#status-values">Status
     * Values</a> for more information on each of these statuses..
     *
     * @param status The Status of this AuthorizationDocument.
     * @return this
     */
    public AuthorizationDocumentUpdater setStatus(final AuthorizationDocument.Status status) {
        this.status = status;
        return this;
    }

    /**
     * The title of the person authorized to sign the Authorization Document for
     * this phone number..
     *
     * @param contactTitle Title of signee of this Authorization Document.
     * @return this
     */
    public AuthorizationDocumentUpdater setContactTitle(final String contactTitle) {
        this.contactTitle = contactTitle;
        return this;
    }

    /**
     * The contact phone number of the person authorized to sign the Authorization
     * Document..
     *
     * @param contactPhoneNumber Authorization Document's signee's phone number.
     * @return this
     */
    public AuthorizationDocumentUpdater setContactPhoneNumber(final String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated AuthorizationDocument
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public AuthorizationDocument update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/HostedNumbers/AuthorizationDocuments/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("AuthorizationDocument update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return AuthorizationDocument.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
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

        if (ccEmails != null) {
            for (String prop : ccEmails) {
                request.addPostParam("CcEmails", prop);
            }
        }

        if (status != null) {
            request.addPostParam("Status", status.toString());
        }

        if (contactTitle != null) {
            request.addPostParam("ContactTitle", contactTitle);
        }

        if (contactPhoneNumber != null) {
            request.addPostParam("ContactPhoneNumber", contactPhoneNumber);
        }
    }
}
