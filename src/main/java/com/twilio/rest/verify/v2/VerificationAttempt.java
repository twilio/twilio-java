/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Verify
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.verify.v2;

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
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class VerificationAttempt extends Resource {

    private static final long serialVersionUID = 105803800130690L;

    public static VerificationAttemptFetcher fetcher(final String pathSid) {
        return new VerificationAttemptFetcher(pathSid);
    }

    public static VerificationAttemptReader reader() {
        return new VerificationAttemptReader();
    }

    /**
     * Converts a JSON String into a VerificationAttempt object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return VerificationAttempt object represented by the provided JSON
     */
    public static VerificationAttempt fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, VerificationAttempt.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a VerificationAttempt object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return VerificationAttempt object represented by the provided JSON
     */
    public static VerificationAttempt fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, VerificationAttempt.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String verificationSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final VerificationAttempt.ConversionStatus conversionStatus;
    private final VerificationAttempt.Channels channel;
    private final Map<String, Object> price;
    private final Map<String, Object> channelData;
    private final URI url;

    @JsonCreator
    private VerificationAttempt(
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("service_sid") final String serviceSid,
        @JsonProperty("verification_sid") final String verificationSid,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty(
            "conversion_status"
        ) final VerificationAttempt.ConversionStatus conversionStatus,
        @JsonProperty("channel") final VerificationAttempt.Channels channel,
        @JsonProperty("price") final Map<String, Object> price,
        @JsonProperty("channel_data") final Map<String, Object> channelData,
        @JsonProperty("url") final URI url
    ) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.verificationSid = verificationSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.conversionStatus = conversionStatus;
        this.channel = channel;
        this.price = price;
        this.channelData = channelData;
        this.url = url;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getServiceSid() {
        return this.serviceSid;
    }

    public final String getVerificationSid() {
        return this.verificationSid;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    public final VerificationAttempt.ConversionStatus getConversionStatus() {
        return this.conversionStatus;
    }

    public final VerificationAttempt.Channels getChannel() {
        return this.channel;
    }

    public final Map<String, Object> getPrice() {
        return this.price;
    }

    public final Map<String, Object> getChannelData() {
        return this.channelData;
    }

    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VerificationAttempt other = (VerificationAttempt) o;

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(serviceSid, other.serviceSid) &&
            Objects.equals(verificationSid, other.verificationSid) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(conversionStatus, other.conversionStatus) &&
            Objects.equals(channel, other.channel) &&
            Objects.equals(price, other.price) &&
            Objects.equals(channelData, other.channelData) &&
            Objects.equals(url, other.url)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
            accountSid,
            serviceSid,
            verificationSid,
            dateCreated,
            dateUpdated,
            conversionStatus,
            channel,
            price,
            channelData,
            url
        );
    }

    public enum ConversionStatus {
        CONVERTED("converted"),
        UNCONVERTED("unconverted");

        private final String value;

        private ConversionStatus(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static ConversionStatus forValue(final String value) {
            return Promoter.enumFromString(value, ConversionStatus.values());
        }
    }

    public enum Channels {
        SMS("sms"),
        CALL("call"),
        EMAIL("email"),
        WHATSAPP("whatsapp");

        private final String value;

        private Channels(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static Channels forValue(final String value) {
            return Promoter.enumFromString(value, Channels.values());
        }
    }
}
