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

package com.twilio.rest.preview.hostedNumbers.authorizationdocument;

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
import com.twilio.type.PhoneNumberCapabilities;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class DependentHostedNumberOrder extends Resource {

    private static final long serialVersionUID = 65583991731077L;

    public static DependentHostedNumberOrderReader reader(
        final String pathSigningDocumentSid
    ) {
        return new DependentHostedNumberOrderReader(pathSigningDocumentSid);
    }

    /**
     * Converts a JSON String into a DependentHostedNumberOrder object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return DependentHostedNumberOrder object represented by the provided JSON
     */
    public static DependentHostedNumberOrder fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(
                json,
                DependentHostedNumberOrder.class
            );
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a DependentHostedNumberOrder object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return DependentHostedNumberOrder object represented by the provided JSON
     */
    public static DependentHostedNumberOrder fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(
                json,
                DependentHostedNumberOrder.class
            );
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
    private final DependentHostedNumberOrder.Status status;
    private final String failureReason;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final Integer verificationAttempts;
    private final String email;
    private final List<String> ccEmails;
    private final DependentHostedNumberOrder.VerificationType verificationType;
    private final String verificationDocumentSid;
    private final String extension;
    private final Integer callDelay;
    private final String verificationCode;
    private final List<String> verificationCallSids;

    @JsonCreator
    private DependentHostedNumberOrder(
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty(
            "incoming_phone_number_sid"
        ) final String incomingPhoneNumberSid,
        @JsonProperty("address_sid") final String addressSid,
        @JsonProperty("signing_document_sid") final String signingDocumentSid,
        @JsonProperty(
            "phone_number"
        ) final com.twilio.type.PhoneNumber phoneNumber,
        @JsonProperty(
            "capabilities"
        ) final PhoneNumberCapabilities capabilities,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("unique_name") final String uniqueName,
        @JsonProperty("status") final DependentHostedNumberOrder.Status status,
        @JsonProperty("failure_reason") final String failureReason,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty(
            "verification_attempts"
        ) final Integer verificationAttempts,
        @JsonProperty("email") final String email,
        @JsonProperty("cc_emails") final List<String> ccEmails,
        @JsonProperty(
            "verification_type"
        ) final DependentHostedNumberOrder.VerificationType verificationType,
        @JsonProperty(
            "verification_document_sid"
        ) final String verificationDocumentSid,
        @JsonProperty("extension") final String extension,
        @JsonProperty("call_delay") final Integer callDelay,
        @JsonProperty("verification_code") final String verificationCode,
        @JsonProperty("verification_call_sids") final List<
            String
        > verificationCallSids
    ) {
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
        this.verificationType = verificationType;
        this.verificationDocumentSid = verificationDocumentSid;
        this.extension = extension;
        this.callDelay = callDelay;
        this.verificationCode = verificationCode;
        this.verificationCallSids = verificationCallSids;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getIncomingPhoneNumberSid() {
        return this.incomingPhoneNumberSid;
    }

    public final String getAddressSid() {
        return this.addressSid;
    }

    public final String getSigningDocumentSid() {
        return this.signingDocumentSid;
    }

    public final com.twilio.type.PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public final PhoneNumberCapabilities getCapabilities() {
        return this.capabilities;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final String getUniqueName() {
        return this.uniqueName;
    }

    public final DependentHostedNumberOrder.Status getStatus() {
        return this.status;
    }

    public final String getFailureReason() {
        return this.failureReason;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    public final Integer getVerificationAttempts() {
        return this.verificationAttempts;
    }

    public final String getEmail() {
        return this.email;
    }

    public final List<String> getCcEmails() {
        return this.ccEmails;
    }

    public final DependentHostedNumberOrder.VerificationType getVerificationType() {
        return this.verificationType;
    }

    public final String getVerificationDocumentSid() {
        return this.verificationDocumentSid;
    }

    public final String getExtension() {
        return this.extension;
    }

    public final Integer getCallDelay() {
        return this.callDelay;
    }

    public final String getVerificationCode() {
        return this.verificationCode;
    }

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

        DependentHostedNumberOrder other = (DependentHostedNumberOrder) o;

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(
                incomingPhoneNumberSid,
                other.incomingPhoneNumberSid
            ) &&
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
            Objects.equals(verificationType, other.verificationType) &&
            Objects.equals(
                verificationDocumentSid,
                other.verificationDocumentSid
            ) &&
            Objects.equals(extension, other.extension) &&
            Objects.equals(callDelay, other.callDelay) &&
            Objects.equals(verificationCode, other.verificationCode) &&
            Objects.equals(verificationCallSids, other.verificationCallSids)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
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
            verificationType,
            verificationDocumentSid,
            extension,
            callDelay,
            verificationCode,
            verificationCallSids
        );
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

        @JsonCreator
        public static VerificationType forValue(final String value) {
            return Promoter.enumFromString(value, VerificationType.values());
        }
    }

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

        @JsonCreator
        public static Status forValue(final String value) {
            return Promoter.enumFromString(value, Status.values());
        }
    }
}
