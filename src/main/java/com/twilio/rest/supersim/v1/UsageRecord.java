/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Supersim
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.supersim.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.twilio.base.Resource;
import com.twilio.converter.CurrencyDeserializer;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UsageRecord extends Resource {

    private static final long serialVersionUID = 269766941607639L;

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
    private final String simSid;
    private final String networkSid;
    private final String fleetSid;
    private final String isoCountry;
    private final Map<String, Object> period;
    private final Long dataUpload;
    private final Long dataDownload;
    private final Long dataTotal;
    private final BigDecimal dataTotalBilled;
    private final Currency billedUnit;

    @JsonCreator
    private UsageRecord(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("sim_sid") final String simSid,
        @JsonProperty("network_sid") final String networkSid,
        @JsonProperty("fleet_sid") final String fleetSid,
        @JsonProperty("iso_country") final String isoCountry,
        @JsonProperty("period") final Map<String, Object> period,
        @JsonProperty("data_upload") final Long dataUpload,
        @JsonProperty("data_download") final Long dataDownload,
        @JsonProperty("data_total") final Long dataTotal,
        @JsonProperty("data_total_billed") final BigDecimal dataTotalBilled,
        @JsonProperty("billed_unit") @JsonDeserialize(
            using = com.twilio.converter.CurrencyDeserializer.class
        ) final Currency billedUnit
    ) {
        this.accountSid = accountSid;
        this.simSid = simSid;
        this.networkSid = networkSid;
        this.fleetSid = fleetSid;
        this.isoCountry = isoCountry;
        this.period = period;
        this.dataUpload = dataUpload;
        this.dataDownload = dataDownload;
        this.dataTotal = dataTotal;
        this.dataTotalBilled = dataTotalBilled;
        this.billedUnit = billedUnit;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getSimSid() {
        return this.simSid;
    }

    public final String getNetworkSid() {
        return this.networkSid;
    }

    public final String getFleetSid() {
        return this.fleetSid;
    }

    public final String getIsoCountry() {
        return this.isoCountry;
    }

    public final Map<String, Object> getPeriod() {
        return this.period;
    }

    public final Long getDataUpload() {
        return this.dataUpload;
    }

    public final Long getDataDownload() {
        return this.dataDownload;
    }

    public final Long getDataTotal() {
        return this.dataTotal;
    }

    public final BigDecimal getDataTotalBilled() {
        return this.dataTotalBilled;
    }

    public final Currency getBilledUnit() {
        return this.billedUnit;
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
            Objects.equals(simSid, other.simSid) &&
            Objects.equals(networkSid, other.networkSid) &&
            Objects.equals(fleetSid, other.fleetSid) &&
            Objects.equals(isoCountry, other.isoCountry) &&
            Objects.equals(period, other.period) &&
            Objects.equals(dataUpload, other.dataUpload) &&
            Objects.equals(dataDownload, other.dataDownload) &&
            Objects.equals(dataTotal, other.dataTotal) &&
            Objects.equals(dataTotalBilled, other.dataTotalBilled) &&
            Objects.equals(billedUnit, other.billedUnit)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
            simSid,
            networkSid,
            fleetSid,
            isoCountry,
            period,
            dataUpload,
            dataDownload,
            dataTotal,
            dataTotalBilled,
            billedUnit
        );
    }

    public enum Group {
        SIM("sim"),
        FLEET("fleet"),
        NETWORK("network"),
        ISOCOUNTRY("isoCountry");

        private final String value;

        private Group(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static Group forValue(final String value) {
            return Promoter.enumFromString(value, Group.values());
        }
    }

    public enum Granularity {
        HOUR("hour"),
        DAY("day"),
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
