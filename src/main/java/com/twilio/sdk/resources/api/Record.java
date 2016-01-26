package com.twilio.sdk.resources.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.RecordReader;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Record extends SidResource {
    private static final long serialVersionUID = 99256854755798L;

    public enum Category {
        CALLERIDLOOKUPS("calleridlookups"),
        CALLS("calls"),
        CALLS_CLIENT("calls-client"),
        CALLS_INBOUND("calls-inbound"),
        CALLS_INBOUND_LOCAL("calls-inbound-local"),
        CALLS_INBOUND_TOLLFREE("calls-inbound-tollfree"),
        CALLS_OUTBOUND("calls-outbound"),
        CALLS_SIP("calls-sip"),
        PHONENUMBERS("phonenumbers"),
        PHONENUMBERS_LOCAL("phonenumbers-local"),
        PHONENUMBERS_TOLLFREE("phonenumbers-tollfree"),
        RECORDINGS("recordings"),
        RECORDINGSTORAGE("recordingstorage"),
        SHORTCODES("shortcodes"),
        SHORTCODES_CUSTOMEROWNED("shortcodes-customerowned"),
        SHORTCODES_RANDOM("shortcodes-random"),
        SHORTCODES_VANITY("shortcodes-vanity"),
        SMS("sms"),
        SMS_INBOUND("sms-inbound"),
        SMS_INBOUND_LONGCODE("sms-inbound-longcode"),
        SMS_INBOUND_SHORTCODE("sms-inbound-shortcode"),
        SMS_OUTBOUND("sms-outbound"),
        SMS_OUTBOUND_LONGCODE("sms-outbound-longcode"),
        SMS_OUTBOUND_SHORTCODE("sms-outbound-shortcode"),
        TOTALPRICE("totalprice"),
        TRANSCRIPTIONS("transcriptions");
    
        private final String value;
        
        private Category(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Category forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Category.valueOf(normalized);
        }
    }

    /**
     * Retrieve a list of usage-records belonging to the account used to make the
     * request
     * 
     * @param accountSid The account_sid
     * @return RecordReader capable of executing the read
     */
    public static RecordReader read(final String accountSid) {
        return new RecordReader(accountSid);
    }

    /**
     * Converts a JSON String into a Record object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Record object represented by the provided JSON
     */
    public static Record fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Record.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Record object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Record object represented by the provided JSON
     */
    public static Record fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Record.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String apiVersion;
    private final Record.Category category;
    private final String count;
    private final String countUnit;
    private final String description;
    private final DateTime endDate;
    private final BigDecimal price;
    private final Currency priceUnit;
    private final DateTime startDate;
    private final Map<String, String> subresourceUris;
    private final String uri;
    private final String usage;
    private final String usageUnit;

    @JsonCreator
    private Record(@JsonProperty("account_sid") final String accountSid, 
                   @JsonProperty("api_version") final String apiVersion, 
                   @JsonProperty("category") final Record.Category category, 
                   @JsonProperty("count") final String count, 
                   @JsonProperty("count_unit") final String countUnit, 
                   @JsonProperty("description") final String description, 
                   @JsonProperty("end_date") final String endDate, 
                   @JsonProperty("price") final BigDecimal price, 
                   @JsonProperty("price_unit") final Currency priceUnit, 
                   @JsonProperty("start_date") final String startDate, 
                   @JsonProperty("subresource_uris") final Map<String, String> subresourceUris, 
                   @JsonProperty("uri") final String uri, 
                   @JsonProperty("usage") final String usage, 
                   @JsonProperty("usage_unit") final String usageUnit) {
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.category = category;
        this.count = count;
        this.countUnit = countUnit;
        this.description = description;
        this.endDate = MarshalConverter.dateTimeFromString(endDate);
        this.price = price;
        this.priceUnit = priceUnit;
        this.startDate = MarshalConverter.dateTimeFromString(startDate);
        this.subresourceUris = subresourceUris;
        this.uri = uri;
        this.usage = usage;
        this.usageUnit = usageUnit;
    }

    /**
     * @return The Account that accrued the usage
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The api_version
     */
    public final String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * @return The category of usage
     */
    public final Record.Category getCategory() {
        return this.category;
    }

    /**
     * @return The number of usage events (e.g. the number of calls).
     */
    public final String getCount() {
        return this.count;
    }

    /**
     * @return The unit in which `Count` is measured
     */
    public final String getCountUnit() {
        return this.countUnit;
    }

    /**
     * @return A human-readable description of the usage category.
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * @return The last date usage is included in this record
     */
    public final DateTime getEndDate() {
        return this.endDate;
    }

    /**
     * @return The total price of the usage
     */
    public final BigDecimal getPrice() {
        return this.price;
    }

    /**
     * @return The currency in which `Price` is measured
     */
    public final Currency getPriceUnit() {
        return this.priceUnit;
    }

    /**
     * @return The first date usage is included in this record
     */
    public final DateTime getStartDate() {
        return this.startDate;
    }

    /**
     * @return Subresources Uris for this UsageRecord
     */
    public final Map<String, String> getSubresourceUris() {
        return this.subresourceUris;
    }

    /**
     * @return The URI for this resource
     */
    public final String getUri() {
        return this.uri;
    }

    /**
     * @return The amount of usage
     */
    public final String getUsage() {
        return this.usage;
    }

    /**
     * @return The units in which `Usage` is measured
     */
    public final String getUsageUnit() {
        return this.usageUnit;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Record other = (Record) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(apiVersion, other.apiVersion) && 
               Objects.equals(category, other.category) && 
               Objects.equals(count, other.count) && 
               Objects.equals(countUnit, other.countUnit) && 
               Objects.equals(description, other.description) && 
               Objects.equals(endDate, other.endDate) && 
               Objects.equals(price, other.price) && 
               Objects.equals(priceUnit, other.priceUnit) && 
               Objects.equals(startDate, other.startDate) && 
               Objects.equals(subresourceUris, other.subresourceUris) && 
               Objects.equals(uri, other.uri) && 
               Objects.equals(usage, other.usage) && 
               Objects.equals(usageUnit, other.usageUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            apiVersion,
                            category,
                            count,
                            countUnit,
                            description,
                            endDate,
                            price,
                            priceUnit,
                            startDate,
                            subresourceUris,
                            uri,
                            usage,
                            usageUnit);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("apiVersion", apiVersion)
                          .add("category", category)
                          .add("count", count)
                          .add("countUnit", countUnit)
                          .add("description", description)
                          .add("endDate", endDate)
                          .add("price", price)
                          .add("priceUnit", priceUnit)
                          .add("startDate", startDate)
                          .add("subresourceUris", subresourceUris)
                          .add("uri", uri)
                          .add("usage", usage)
                          .add("usageUnit", usageUnit)
                          .toString();
    }
}