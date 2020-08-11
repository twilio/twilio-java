/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.verify.v2.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
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
import java.time.ZonedDateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VerificationCheck extends Resource {
    private static final long serialVersionUID = 185560198235176L;

    public enum Channel {
        SMS("sms"),
        CALL("call"),
        EMAIL("email");

        private final String value;

        private Channel(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Channel from a string.
         * @param value string value
         * @return generated Channel
         */
        @JsonCreator
        public static Channel forValue(final String value) {
            return Promoter.enumFromString(value, Channel.values());
        }
    }

    /**
     * Create a VerificationCheckCreator to execute create.
     *
     * @param pathServiceSid The SID of the verification Service to create the
     *                       resource under
     * @param code The verification string
     * @return VerificationCheckCreator capable of executing the create
     */
    public static VerificationCheckCreator creator(final String pathServiceSid,
                                                   final String code) {
        return new VerificationCheckCreator(pathServiceSid, code);
    }

    /**
     * Converts a JSON String into a VerificationCheck object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return VerificationCheck object represented by the provided JSON
     */
    public static VerificationCheck fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, VerificationCheck.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a VerificationCheck object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return VerificationCheck object represented by the provided JSON
     */
    public static VerificationCheck fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, VerificationCheck.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String serviceSid;
    private final String accountSid;
    private final String to;
    private final VerificationCheck.Channel channel;
    private final String status;
    private final Boolean valid;
    private final String amount;
    private final String payee;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;

    @JsonCreator
    private VerificationCheck(@JsonProperty("sid")
                              final String sid,
                              @JsonProperty("service_sid")
                              final String serviceSid,
                              @JsonProperty("account_sid")
                              final String accountSid,
                              @JsonProperty("to")
                              final String to,
                              @JsonProperty("channel")
                              final VerificationCheck.Channel channel,
                              @JsonProperty("status")
                              final String status,
                              @JsonProperty("valid")
                              final Boolean valid,
                              @JsonProperty("amount")
                              final String amount,
                              @JsonProperty("payee")
                              final String payee,
                              @JsonProperty("date_created")
                              final String dateCreated,
                              @JsonProperty("date_updated")
                              final String dateUpdated) {
        this.sid = sid;
        this.serviceSid = serviceSid;
        this.accountSid = accountSid;
        this.to = to;
        this.channel = channel;
        this.status = status;
        this.valid = valid;
        this.amount = amount;
        this.payee = payee;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
    }

    /**
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the Service that the resource is associated with.
     *
     * @return The SID of the Service that the resource is associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The phone number or email being verified.
     *
     * @return The phone number or email being verified
     */
    public final String getTo() {
        return this.to;
    }

    /**
     * Returns The verification method to use.
     *
     * @return The verification method to use
     */
    public final VerificationCheck.Channel getChannel() {
        return this.channel;
    }

    /**
     * Returns The status of the verification resource.
     *
     * @return The status of the verification resource
     */
    public final String getStatus() {
        return this.status;
    }

    /**
     * Returns Whether the verification was successful.
     *
     * @return Whether the verification was successful
     */
    public final Boolean getValid() {
        return this.valid;
    }

    /**
     * Returns The amount of the associated PSD2 compliant transaction..
     *
     * @return The amount of the associated PSD2 compliant transaction.
     */
    public final String getAmount() {
        return this.amount;
    }

    /**
     * Returns The payee of the associated PSD2 compliant transaction.
     *
     * @return The payee of the associated PSD2 compliant transaction
     */
    public final String getPayee() {
        return this.payee;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the Verification Check
     * resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the Verification Check
     *         resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the Verification Check
     * resource was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the Verification Check
     *         resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VerificationCheck other = (VerificationCheck) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(to, other.to) &&
               Objects.equals(channel, other.channel) &&
               Objects.equals(status, other.status) &&
               Objects.equals(valid, other.valid) &&
               Objects.equals(amount, other.amount) &&
               Objects.equals(payee, other.payee) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            serviceSid,
                            accountSid,
                            to,
                            channel,
                            status,
                            valid,
                            amount,
                            payee,
                            dateCreated,
                            dateUpdated);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("serviceSid", serviceSid)
                          .add("accountSid", accountSid)
                          .add("to", to)
                          .add("channel", channel)
                          .add("status", status)
                          .add("valid", valid)
                          .add("amount", amount)
                          .add("payee", payee)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .toString();
    }
}
