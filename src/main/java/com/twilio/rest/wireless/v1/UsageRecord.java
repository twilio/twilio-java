/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Wireless
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.wireless.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UsageRecord extends Resource {

    private static final long serialVersionUID = 2654718760239L;

    public static UsageRecordReader reader() {
        return new UsageRecordReader();
    }

    /**
     * Converts a JSON String into a UsageRecord object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return UsageRecord object represented by the provided JSON
     */
    public static UsageRecord fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, UsageRecord.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a UsageRecord object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return UsageRecord object represented by the provided JSON
     */
    public static UsageRecord fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, UsageRecord.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final Map<String, Object> period;
    private final Map<String, Object> commands;
    private final Map<String, Object> data;

    @JsonCreator
    private UsageRecord(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("period") final Map<String, Object> period,
        @JsonProperty("commands") final Map<String, Object> commands,
        @JsonProperty("data") final Map<String, Object> data
    ) {
        this.accountSid = accountSid;
        this.period = period;
        this.commands = commands;
        this.data = data;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final Map<String, Object> getPeriod() {
        return this.period;
    }

    public final Map<String, Object> getCommands() {
        return this.commands;
    }

    public final Map<String, Object> getData() {
        return this.data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsageRecord other = (UsageRecord) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(period, other.period) &&
            Objects.equals(commands, other.commands) &&
            Objects.equals(data, other.data)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid, period, commands, data);
    }

    public enum Granularity {
        HOURLY("hourly"),
        DAILY("daily"),
        ALL("all");

        private final String value;

        private Granularity(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static Granularity forValue(final String value) {
            return Promoter.enumFromString(value, Granularity.values());
        }
    }
}
