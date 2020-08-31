/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.hostedNumbers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import com.twilio.type.PhoneNumberCapabilities;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class HostedNumberOrder extends Resource {
    private static final long serialVersionUID = 200143237925863L;

    public enum Status {
        RECEIVED("received"),
        PENDING_VERIFICATION("pending-verification"),
        VERIFIED("verified"),
        PENDING_LOA("pending-loa"),
        CARRIER_PROCESSING("carrier-processing"),
        TESTING("testing"),
        COMPLETED("completed"),
        FAILED("failed"),
        ACTION_REQUIRED("action-required");

        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Status from a string.
         * @param value string value
         * @return generated Status
         */
        @JsonCreator
        public static Status forValue(final String value) {
            return Promoter.enumFromString(value, Status.values());
        }
    }

    public enum VerificationType {
        PHONE_CALL("phone-call"),
        PHONE_BILL("phone-bill");

        private final String value;

        private VerificationType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a VerificationType from a string.
         * @param value string value
         * @return generated VerificationType
         */
        @JsonCreator
        public static VerificationType forValue(final String value) {
            return Promoter.enumFromString(value, VerificationType.values());
        }
    }

    /**
     * Create a HostedNumberOrderFetcher to execute fetch.
     *
     * @param pathSid HostedNumberOrder sid.
     * @return HostedNumberOrderFetcher capable of executing the fetch
     */
    public static HostedNumberOrderFetcher fetcher(final String pathSid) {
        return new HostedNumberOrderFetcher(pathSid);
    }

    /**
     * Create a HostedNumberOrderDeleter to execute delete.
     *
     * @param pathSid HostedNumberOrder sid.
     * @return HostedNumberOrderDeleter capable of executing the delete
     */
    public static HostedNumberOrderDeleter deleter(final String pathSid) {
        return new HostedNumberOrderDeleter(pathSid);
    }

    /**
     * Create a HostedNumberOrderUpdater to execute update.
     *
     * @param pathSid The sid
     * @return HostedNumberOrderUpdater capable of executing the update
     */
    public static HostedNumberOrderUpdater updater(final String pathSid) {
        return new HostedNumberOrderUpdater(pathSid);
    }

    /**
     * Create a HostedNumberOrderReader to execute read.
     *
     * @return HostedNumberOrderReader capable of executing the read
     */
    public static HostedNumberOrderReader reader() {
        return new HostedNumberOrderReader();
    }

    /**
     * Create a HostedNumberOrderCreator to execute create.
     *
     * @param phoneNumber An E164 formatted phone number.
     * @param smsCapability Specify SMS capability to host.
     * @return HostedNumberOrderCreator capable of executing the create
     */
    public static HostedNumberOrderCreator creator(final com.twilio.type.PhoneNumber phoneNumber,
                                                   final Boolean smsCapability) {
        return new HostedNumberOrderCreator(phoneNumber, smsCapability);
    }

    /**
     * Converts a JSON String into a HostedNumberOrder object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return HostedNumberOrder object represented by the provided JSON
     */
    public static HostedNumberOrder fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, HostedNumberOrder.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a HostedNumberOrder object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return HostedNumberOrder object represented by the provided JSON
     */
    public static HostedNumberOrder fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, HostedNumberOrder.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String incomingPhoneNumberSid;
    private final String addressSid;
    private final String signingDocumentSid;
    private final com.twilio.type.PhoneNumber phoneNumber;
    private final PhoneNumberCapabilities capabilities;
    private final String friendlyName;
    private final String uniqueName;
    private final HostedNumberOrder.Status status;
    private final String failureReason;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final Integer verificationAttempts;
    private final String email;
    private final List<String> ccEmails;
    private final URI url;
    private final HostedNumberOrder.VerificationType verificationType;
    private final String verificationDocumentSid;
    private final String extension;
    private final Integer callDelay;
    private final String verificationCode;
    private final List<String> verificationCallSids;

    @JsonCreator
    private HostedNumberOrder(@JsonProperty("sid")
                              final String sid,
                              @JsonProperty("account_sid")
                              final String accountSid,
                              @JsonProperty("incoming_phone_number_sid")
                              final String incomingPhoneNumberSid,
                              @JsonProperty("address_sid")
                              final String addressSid,
                              @JsonProperty("signing_document_sid")
                              final String signingDocumentSid,
                              @JsonProperty("phone_number")
                              final com.twilio.type.PhoneNumber phoneNumber,
                              @JsonProperty("capabilities")
                              final PhoneNumberCapabilities capabilities,
                              @JsonProperty("friendly_name")
                              final String friendlyName,
                              @JsonProperty("unique_name")
                              final String uniqueName,
                              @JsonProperty("status")
                              final HostedNumberOrder.Status status,
                              @JsonProperty("failure_reason")
                              final String failureReason,
                              @JsonProperty("date_created")
                              final String dateCreated,
                              @JsonProperty("date_updated")
                              final String dateUpdated,
                              @JsonProperty("verification_attempts")
                              final Integer verificationAttempts,
                              @JsonProperty("email")
                              final String email,
                              @JsonProperty("cc_emails")
                              final List<String> ccEmails,
                              @JsonProperty("url")
                              final URI url,
                              @JsonProperty("verification_type")
                              final HostedNumberOrder.VerificationType verificationType,
                              @JsonProperty("verification_document_sid")
                              final String verificationDocumentSid,
                              @JsonProperty("extension")
                              final String extension,
                              @JsonProperty("call_delay")
                              final Integer callDelay,
                              @JsonProperty("verification_code")
                              final String verificationCode,
                              @JsonProperty("verification_call_sids")
                              final List<String> verificationCallSids) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.incomingPhoneNumberSid = incomingPhoneNumberSid;
        this.addressSid = addressSid;
        this.signingDocumentSid = signingDocumentSid;
        this.phoneNumber = phoneNumber;
        this.capabilities = capabilities;
        this.friendlyName = friendlyName;
        this.uniqueName = uniqueName;
        this.status = status;
        this.failureReason = failureReason;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.verificationAttempts = verificationAttempts;
        this.email = email;
        this.ccEmails = ccEmails;
        this.url = url;
        this.verificationType = verificationType;
        this.verificationDocumentSid = verificationDocumentSid;
        this.extension = extension;
        this.callDelay = callDelay;
        this.verificationCode = verificationCode;
        this.verificationCallSids = verificationCallSids;
    }

    /**
     * Returns HostedNumberOrder sid..
     *
     * @return HostedNumberOrder sid.
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns Account Sid..
     *
     * @return Account Sid.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns IncomingPhoneNumber sid..
     *
     * @return IncomingPhoneNumber sid.
     */
    public final String getIncomingPhoneNumberSid() {
        return this.incomingPhoneNumberSid;
    }

    /**
     * Returns Address sid..
     *
     * @return Address sid.
     */
    public final String getAddressSid() {
        return this.addressSid;
    }

    /**
     * Returns LOA document sid..
     *
     * @return LOA document sid.
     */
    public final String getSigningDocumentSid() {
        return this.signingDocumentSid;
    }

    /**
     * Returns An E164 formatted phone number..
     *
     * @return An E164 formatted phone number.
     */
    public final com.twilio.type.PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns A mapping of phone number capabilities..
     *
     * @return A mapping of phone number capabilities.
     */
    public final PhoneNumberCapabilities getCapabilities() {
        return this.capabilities;
    }

    /**
     * Returns A human readable description of this resource..
     *
     * @return A human readable description of this resource.
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns A unique, developer assigned name of this HostedNumberOrder..
     *
     * @return A unique, developer assigned name of this HostedNumberOrder.
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The Status of this HostedNumberOrder..
     *
     * @return The Status of this HostedNumberOrder.
     */
    public final HostedNumberOrder.Status getStatus() {
        return this.status;
    }

    /**
     * Returns Why a hosted_number_order reached status "action-required".
     *
     * @return Why a hosted_number_order reached status "action-required"
     */
    public final String getFailureReason() {
        return this.failureReason;
    }

    /**
     * Returns The date this HostedNumberOrder was created..
     *
     * @return The date this HostedNumberOrder was created.
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The date this HostedNumberOrder was updated..
     *
     * @return The date this HostedNumberOrder was updated.
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The number of attempts made to verify ownership of the phone number..
     *
     * @return The number of attempts made to verify ownership of the phone number.
     */
    public final Integer getVerificationAttempts() {
        return this.verificationAttempts;
    }

    /**
     * Returns Email..
     *
     * @return Email.
     */
    public final String getEmail() {
        return this.email;
    }

    /**
     * Returns A list of emails..
     *
     * @return A list of emails.
     */
    public final List<String> getCcEmails() {
        return this.ccEmails;
    }

    /**
     * Returns The URL of this HostedNumberOrder..
     *
     * @return The URL of this HostedNumberOrder.
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The method used for verifying ownership of the number to be hosted..
     *
     * @return The method used for verifying ownership of the number to be hosted.
     */
    public final HostedNumberOrder.VerificationType getVerificationType() {
        return this.verificationType;
    }

    /**
     * Returns Verification Document Sid..
     *
     * @return Verification Document Sid.
     */
    public final String getVerificationDocumentSid() {
        return this.verificationDocumentSid;
    }

    /**
     * Returns Phone extension to use for ownership verification call..
     *
     * @return Phone extension to use for ownership verification call.
     */
    public final String getExtension() {
        return this.extension;
    }

    /**
     * Returns Seconds (0-30) to delay ownership verification call by..
     *
     * @return Seconds (0-30) to delay ownership verification call by.
     */
    public final Integer getCallDelay() {
        return this.callDelay;
    }

    /**
     * Returns The digits passed during the ownership verification call..
     *
     * @return The digits passed during the ownership verification call.
     */
    public final String getVerificationCode() {
        return this.verificationCode;
    }

    /**
     * Returns List of IDs for ownership verification calls..
     *
     * @return List of IDs for ownership verification calls.
     */
    public final List<String> getVerificationCallSids() {
        return this.verificationCallSids;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HostedNumberOrder other = (HostedNumberOrder) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(incomingPhoneNumberSid, other.incomingPhoneNumberSid) &&
               Objects.equals(addressSid, other.addressSid) &&
               Objects.equals(signingDocumentSid, other.signingDocumentSid) &&
               Objects.equals(phoneNumber, other.phoneNumber) &&
               Objects.equals(capabilities, other.capabilities) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(status, other.status) &&
               Objects.equals(failureReason, other.failureReason) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(verificationAttempts, other.verificationAttempts) &&
               Objects.equals(email, other.email) &&
               Objects.equals(ccEmails, other.ccEmails) &&
               Objects.equals(url, other.url) &&
               Objects.equals(verificationType, other.verificationType) &&
               Objects.equals(verificationDocumentSid, other.verificationDocumentSid) &&
               Objects.equals(extension, other.extension) &&
               Objects.equals(callDelay, other.callDelay) &&
               Objects.equals(verificationCode, other.verificationCode) &&
               Objects.equals(verificationCallSids, other.verificationCallSids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            incomingPhoneNumberSid,
                            addressSid,
                            signingDocumentSid,
                            phoneNumber,
                            capabilities,
                            friendlyName,
                            uniqueName,
                            status,
                            failureReason,
                            dateCreated,
                            dateUpdated,
                            verificationAttempts,
                            email,
                            ccEmails,
                            url,
                            verificationType,
                            verificationDocumentSid,
                            extension,
                            callDelay,
                            verificationCode,
                            verificationCallSids);
    }
}