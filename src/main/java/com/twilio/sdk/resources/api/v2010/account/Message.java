package com.twilio.sdk.resources.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.v2010.account.MessageCreator;
import com.twilio.sdk.deleters.api.v2010.account.MessageDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.MessageFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.MessageReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.account.MessageUpdater;
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
public class Message extends SidResource {
    private static final long serialVersionUID = 8764009288306L;

    public enum Status {
        QUEUED("queued"),
        SENDING("sending"),
        SENT("sent"),
        FAILED("failed"),
        DELIVERED("delivered"),
        UNDELIVERED("undelivered"),
        RECEIVING("receiving"),
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
     * Send a message from the account used to make the request
     * 
     * @param accountSid The account_sid
     * @param to The phone number to receive the message
     * @param from The phone number that initiated the message
     * @param body The body
     * @return MessageCreator capable of executing the create
     */
    public static MessageCreator create(final String accountSid, final String to, final String from, final String body) {
        return new MessageCreator(accountSid, to, from, body);
    }

    /**
     * Send a message from the account used to make the request
     * 
     * @param accountSid The account_sid
     * @param to The phone number to receive the message
     * @param from The phone number that initiated the message
     * @param mediaUrl The media_url
     * @return MessageCreator capable of executing the create
     */
    public static MessageCreator create(final String accountSid, final String to, final String from, final List<URI> mediaUrl) {
        return new MessageCreator(accountSid, to, from, mediaUrl);
    }

    /**
     * Deletes a message record from your account
     * 
     * @param accountSid The account_sid
     * @param sid The message to delete
     * @return MessageDeleter capable of executing the delete
     */
    public static MessageDeleter delete(final String accountSid, final String sid) {
        return new MessageDeleter(accountSid, sid);
    }

    /**
     * Fetch a message belonging to the account used to make the request
     * 
     * @param accountSid The account_sid
     * @param sid Fetch by unique message Sid
     * @return MessageFetcher capable of executing the fetch
     */
    public static MessageFetcher fetch(final String accountSid, final String sid) {
        return new MessageFetcher(accountSid, sid);
    }

    /**
     * Retrieve a list of messages belonging to the account used to make the request
     * 
     * @param accountSid The account_sid
     * @return MessageReader capable of executing the read
     */
    public static MessageReader read(final String accountSid) {
        return new MessageReader(accountSid);
    }

    /**
     * To redact a message-body from a post-flight message record, post to the
     * message instance resource with an empty body
     * 
     * @param accountSid The account_sid
     * @param sid The message to redact
     * @return MessageUpdater capable of executing the update
     */
    public static MessageUpdater update(final String accountSid, final String sid) {
        return new MessageUpdater(accountSid, sid);
    }

    /**
     * Converts a JSON String into a Message object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Message object represented by the provided JSON
     */
    public static Message fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Message.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Message object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Message object represented by the provided JSON
     */
    public static Message fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Message.class);
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
    private final Message.Direction direction;
    private final Integer errorCode;
    private final String errorMessage;
    private final String from;
    private final String numMedia;
    private final String numSegments;
    private final BigDecimal price;
    private final Currency priceUnit;
    private final String sid;
    private final Message.Status status;
    private final Map<String, String> subresourceUris;
    private final String to;
    private final String uri;

    @JsonCreator
    private Message(@JsonProperty("account_sid") final String accountSid, 
                    @JsonProperty("api_version") final String apiVersion, 
                    @JsonProperty("body") final String body, 
                    @JsonProperty("date_created") final String dateCreated, 
                    @JsonProperty("date_updated") final String dateUpdated, 
                    @JsonProperty("date_sent") final String dateSent, 
                    @JsonProperty("direction") final Message.Direction direction, 
                    @JsonProperty("error_code") final Integer errorCode, 
                    @JsonProperty("error_message") final String errorMessage, 
                    @JsonProperty("from") final String from, 
                    @JsonProperty("num_media") final String numMedia, 
                    @JsonProperty("num_segments") final String numSegments, 
                    @JsonProperty("price") final BigDecimal price, 
                    @JsonProperty("price_unit") final Currency priceUnit, 
                    @JsonProperty("sid") final String sid, 
                    @JsonProperty("status") final Message.Status status, 
                    @JsonProperty("subresource_uris") final Map<String, String> subresourceUris, 
                    @JsonProperty("to") final String to, 
                    @JsonProperty("uri") final String uri) {
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.body = body;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.dateSent = MarshalConverter.dateTimeFromString(dateSent);
        this.direction = direction;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.from = from;
        this.numMedia = numMedia;
        this.numSegments = numSegments;
        this.price = price;
        this.priceUnit = priceUnit;
        this.sid = sid;
        this.status = status;
        this.subresourceUris = subresourceUris;
        this.to = to;
        this.uri = uri;
    }

    /**
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The version of the Twilio API used to process the message.
     */
    public final String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * @return The text body of the message. Up to 1600 characters long.
     */
    public final String getBody() {
        return this.body;
    }

    /**
     * @return The date this resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date this resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return The date the message was sent
     */
    public final DateTime getDateSent() {
        return this.dateSent;
    }

    /**
     * @return The direction of the message
     */
    public final Message.Direction getDirection() {
        return this.direction;
    }

    /**
     * @return The error code associated with the message
     */
    public final Integer getErrorCode() {
        return this.errorCode;
    }

    /**
     * @return Human readable description of the ErrorCode
     */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * @return The phone number that initiated the message
     */
    public final String getFrom() {
        return this.from;
    }

    /**
     * @return Number of media files associated with the message
     */
    public final String getNumMedia() {
        return this.numMedia;
    }

    /**
     * @return Indicates number of messages used to delivery the body
     */
    public final String getNumSegments() {
        return this.numSegments;
    }

    /**
     * @return The amount billed for the message
     */
    public final BigDecimal getPrice() {
        return this.price;
    }

    /**
     * @return The currency in which Price is measured
     */
    public final Currency getPriceUnit() {
        return this.priceUnit;
    }

    /**
     * @return A string that uniquely identifies this message
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The status of this message
     */
    public final Message.Status getStatus() {
        return this.status;
    }

    /**
     * @return The subresource_uris
     */
    public final Map<String, String> getSubresourceUris() {
        return this.subresourceUris;
    }

    /**
     * @return The phone number that received the message
     */
    public final String getTo() {
        return this.to;
    }

    /**
     * @return The URI for this resource
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
        
        Message other = (Message) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(apiVersion, other.apiVersion) && 
               Objects.equals(body, other.body) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(dateSent, other.dateSent) && 
               Objects.equals(direction, other.direction) && 
               Objects.equals(errorCode, other.errorCode) && 
               Objects.equals(errorMessage, other.errorMessage) && 
               Objects.equals(from, other.from) && 
               Objects.equals(numMedia, other.numMedia) && 
               Objects.equals(numSegments, other.numSegments) && 
               Objects.equals(price, other.price) && 
               Objects.equals(priceUnit, other.priceUnit) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(status, other.status) && 
               Objects.equals(subresourceUris, other.subresourceUris) && 
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
                            errorCode,
                            errorMessage,
                            from,
                            numMedia,
                            numSegments,
                            price,
                            priceUnit,
                            sid,
                            status,
                            subresourceUris,
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
                          .add("errorCode", errorCode)
                          .add("errorMessage", errorMessage)
                          .add("from", from)
                          .add("numMedia", numMedia)
                          .add("numSegments", numSegments)
                          .add("price", price)
                          .add("priceUnit", priceUnit)
                          .add("sid", sid)
                          .add("status", status)
                          .add("subresourceUris", subresourceUris)
                          .add("to", to)
                          .add("uri", uri)
                          .toString();
    }
}