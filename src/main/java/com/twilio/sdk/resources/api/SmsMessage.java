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
import com.twilio.sdk.creators.api.SmsMessageCreator;
import com.twilio.sdk.deleters.api.SmsMessageDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.SmsMessageFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.SmsMessageReader;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.SmsMessageUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsMessage extends Resource {
    private static final long serialVersionUID = 35367205451086L;

    public enum Status {
        QUEUED("queued"),
        SENDING("sending"),
        SENT("sent"),
        FAILED("failed"),
        RECEIVED("received");
    
        private final String value;
        
        private Status(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Status forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Status.valueOf(normalized);
        }
    }

    public enum Direction {
        INBOUND("inbound"),
        OUTBOUND_API("outbound-api"),
        OUTBOUND_CALL("outbound-call"),
        OUTBOUND_REPLY("outbound-reply");
    
        private final String value;
        
        private Direction(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Direction forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Direction.valueOf(normalized);
        }
    }

    /**
     * create
     * 
     * @param accountSid The account_sid
     * @param to The to
     * @param from The from
     * @param body The body
     * @return SmsMessageCreator capable of executing the create
     */
    public static SmsMessageCreator create(final String accountSid, final String to, final String from, final String body) {
        return new SmsMessageCreator(accountSid, to, from, body);
    }

    /**
     * create
     * 
     * @param accountSid The account_sid
     * @param to The to
     * @param from The from
     * @param mediaUrl The media_url
     * @return SmsMessageCreator capable of executing the create
     */
    public static SmsMessageCreator create(final String accountSid, final String to, final String from, final List<URI> mediaUrl) {
        return new SmsMessageCreator(accountSid, to, from, mediaUrl);
    }

    /**
     * delete
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return SmsMessageDeleter capable of executing the delete
     */
    public static SmsMessageDeleter delete(final String accountSid, final String sid) {
        return new SmsMessageDeleter(accountSid, sid);
    }

    /**
     * fetch
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return SmsMessageFetcher capable of executing the fetch
     */
    public static SmsMessageFetcher fetch(final String accountSid, final String sid) {
        return new SmsMessageFetcher(accountSid, sid);
    }

    /**
     * read
     * 
     * @param accountSid The account_sid
     * @return SmsMessageReader capable of executing the read
     */
    public static SmsMessageReader read(final String accountSid) {
        return new SmsMessageReader(accountSid);
    }

    /**
     * update
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return SmsMessageUpdater capable of executing the update
     */
    public static SmsMessageUpdater update(final String accountSid, final String sid) {
        return new SmsMessageUpdater(accountSid, sid);
    }

    /**
     * Converts a JSON String into a SmsMessage object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return SmsMessage object represented by the provided JSON
     */
    public static SmsMessage fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, SmsMessage.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a SmsMessage object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return SmsMessage object represented by the provided JSON
     */
    public static SmsMessage fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, SmsMessage.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String apiVersion;
    private final String body;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final DateTime dateSent;
    private final SmsMessage.Direction direction;
    private final String from;
    private final BigDecimal price;
    private final Currency priceUnit;
    private final String sid;
    private final SmsMessage.Status status;
    private final String to;
    private final String uri;

    @JsonCreator
    private SmsMessage(@JsonProperty("account_sid") final String accountSid, 
                       @JsonProperty("api_version") final String apiVersion, 
                       @JsonProperty("body") final String body, 
                       @JsonProperty("date_created") final String dateCreated, 
                       @JsonProperty("date_updated") final String dateUpdated, 
                       @JsonProperty("date_sent") final String dateSent, 
                       @JsonProperty("direction") final SmsMessage.Direction direction, 
                       @JsonProperty("from") final String from, 
                       @JsonProperty("price") final BigDecimal price, 
                       @JsonProperty("price_unit") final Currency priceUnit, 
                       @JsonProperty("sid") final String sid, 
                       @JsonProperty("status") final SmsMessage.Status status, 
                       @JsonProperty("to") final String to, 
                       @JsonProperty("uri") final String uri) {
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.body = body;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.dateSent = MarshalConverter.dateTimeFromString(dateSent);
        this.direction = direction;
        this.from = from;
        this.price = price;
        this.priceUnit = priceUnit;
        this.sid = sid;
        this.status = status;
        this.to = to;
        this.uri = uri;
    }

    /**
     * @return The account_sid
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
     * @return The body
     */
    public final String getBody() {
        return this.body;
    }

    /**
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return The date_sent
     */
    public final DateTime getDateSent() {
        return this.dateSent;
    }

    /**
     * @return The direction
     */
    public final SmsMessage.Direction getDirection() {
        return this.direction;
    }

    /**
     * @return The from
     */
    public final String getFrom() {
        return this.from;
    }

    /**
     * @return The price
     */
    public final BigDecimal getPrice() {
        return this.price;
    }

    /**
     * @return The price_unit
     */
    public final Currency getPriceUnit() {
        return this.priceUnit;
    }

    /**
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The status
     */
    public final SmsMessage.Status getStatus() {
        return this.status;
    }

    /**
     * @return The to
     */
    public final String getTo() {
        return this.to;
    }

    /**
     * @return The uri
     */
    public final String getUri() {
        return this.uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        SmsMessage other = (SmsMessage) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(apiVersion, other.apiVersion) && 
               Objects.equals(body, other.body) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(dateSent, other.dateSent) && 
               Objects.equals(direction, other.direction) && 
               Objects.equals(from, other.from) && 
               Objects.equals(price, other.price) && 
               Objects.equals(priceUnit, other.priceUnit) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(status, other.status) && 
               Objects.equals(to, other.to) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            apiVersion,
                            body,
                            dateCreated,
                            dateUpdated,
                            dateSent,
                            direction,
                            from,
                            price,
                            priceUnit,
                            sid,
                            status,
                            to,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("apiVersion", apiVersion)
                          .add("body", body)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("dateSent", dateSent)
                          .add("direction", direction)
                          .add("from", from)
                          .add("price", price)
                          .add("priceUnit", priceUnit)
                          .add("sid", sid)
                          .add("status", status)
                          .add("to", to)
                          .add("uri", uri)
                          .toString();
    }
}