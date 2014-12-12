package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.creators.MessageCreator;
import com.twilio.sdk.deleters.MessageDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.MediaFetcher;
import com.twilio.sdk.fetchers.MessageFetcher;
import com.twilio.sdk.readers.MediaReader;
import com.twilio.sdk.readers.MessageReader;
import com.twilio.sdk.updaters.MessageUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String body;
    private final Integer numSegments;
    private final Message.Direction direction;
    private final String from;
    private final DateTime dateUpdated;
    private final BigDecimal price;
    private final String errorMessage;
    private final String uri;
    private final String accountSid;
    private final Integer numMedia;
    private final String to;
    private final Message.Status status;
    private final String sid;
    private final DateTime dateSent;
    private final DateTime dateCreated;
    private final Map<String, String> subresourceUris;
    private final Integer errorCode;
    private final Currency priceUnit;
    private final String apiVersion;

    @JsonCreator
    private Message(@JsonProperty("body") final String body, @JsonProperty("num_segments") final Integer numSegments,
                    @JsonProperty("direction") final Message.Direction direction,
                    @JsonProperty("from") final String from, @JsonProperty("date_updated") final String dateUpdated,
                    @JsonProperty("price") final BigDecimal price,
                    @JsonProperty("error_message") final String errorMessage, @JsonProperty("uri") final String uri,
                    @JsonProperty("account_sid") final String accountSid,
                    @JsonProperty("num_media") final Integer numMedia, @JsonProperty("to") final String to,
                    @JsonProperty("status") final Message.Status status, @JsonProperty("sid") final String sid,
                    @JsonProperty("date_sent") final String dateSent,
                    @JsonProperty("date_created") final String dateCreated,
                    @JsonProperty("subresource_uris") final Map<String, String> subresourceUris,
                    @JsonProperty("error_code") final Integer errorCode,
                    @JsonProperty("price_unit") final Currency priceUnit,
                    @JsonProperty("api_version") final String apiVersion) {
        this.body = body;
        this.numSegments = numSegments;
        this.direction = direction;
        this.from = from;
        this.dateUpdated = safeDateTimeConvert(dateUpdated);
        this.price = price;
        this.errorMessage = errorMessage;
        this.uri = uri;
        this.accountSid = accountSid;
        this.numMedia = numMedia;
        this.to = to;
        this.status = status;
        this.sid = sid;
        this.dateSent = safeDateTimeConvert(dateSent);
        this.dateCreated = safeDateTimeConvert(dateCreated);
        this.subresourceUris = subresourceUris;
        this.errorCode = errorCode;
        this.priceUnit = priceUnit;
        this.apiVersion = apiVersion;

    }

    public static MessageCreator create(final String to, final String from) {
        return new MessageCreator(to, from);
    }

    public static MessageDeleter delete(final String sid) {
        return new MessageDeleter(sid);
    }

    public static MessageDeleter delete(final Message target) {
        return new MessageDeleter(target);
    }

    public static MessageFetcher fetch(final String sid) {
        return new MessageFetcher(sid);
    }

    public static MessageReader list() {
        return new MessageReader();
    }

    public static MessageUpdater update(final Message target) {
        return new MessageUpdater(target);
    }

    public static MessageUpdater update(final String sid) {
        return new MessageUpdater(sid);
    }

    public MediaReader listMedia() {
        return new MediaReader(this);
    }

    public MediaFetcher fetchMedia(final String sid) {
        return new MediaFetcher(this, sid);
    }

    public static Message fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Message.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Message fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Message.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final String getBody() {
        return body;
    }

    public final Integer getNumSegments() {
        return numSegments;
    }

    public final Message.Direction getDirection() {
        return direction;
    }

    public final String getFrom() {
        return from;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final BigDecimal getPrice() {
        return price;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    public final String getUri() {
        return uri;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final Integer getNumMedia() {
        return numMedia;
    }

    public final String getTo() {
        return to;
    }

    public final Message.Status getStatus() {
        return status;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateSent() {
        return dateSent;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    public final Integer getErrorCode() {
        return errorCode;
    }

    public final Currency getPriceUnit() {
        return priceUnit;
    }

    public final String getApiVersion() {
        return apiVersion;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message self = (Message) o;

        return (Objects.equals(body, self.body) &&
                Objects.equals(numSegments, self.numSegments) &&
                Objects.equals(direction, self.direction) &&
                Objects.equals(from, self.from) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(price, self.price) &&
                Objects.equals(errorMessage, self.errorMessage) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(numMedia, self.numMedia) &&
                Objects.equals(to, self.to) &&
                Objects.equals(status, self.status) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateSent, self.dateSent) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(subresourceUris, self.subresourceUris) &&
                Objects.equals(errorCode, self.errorCode) &&
                Objects.equals(priceUnit, self.priceUnit) &&
                Objects.equals(apiVersion, self.apiVersion));
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, numSegments, direction, from, dateUpdated, price, errorMessage, uri, accountSid,
                            numMedia, to, status, sid, dateSent, dateCreated, subresourceUris, errorCode, priceUnit,
                            apiVersion);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("body", body)
                          .add("numSegments", numSegments)
                          .add("direction", direction)
                          .add("from", from)
                          .add("dateUpdated", dateUpdated)
                          .add("price", price)
                          .add("errorMessage", errorMessage)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("numMedia", numMedia)
                          .add("to", to)
                          .add("status", status)
                          .add("sid", sid)
                          .add("dateSent", dateSent)
                          .add("dateCreated", dateCreated)
                          .add("subresourceUris", subresourceUris)
                          .add("errorCode", errorCode)
                          .add("priceUnit", priceUnit)
                          .add("apiVersion", apiVersion)
                          .toString();
    }

    public enum Status {
        QUEUED("queued"), SENDING("sending"), SENT("sent"), FAILED("failed"), DELIVERED("delivered"), UNDELIVERED(
                "undelivered"), RECEIVING("receiving"), RECEIVED("received");
        private final String status;

        private Status(final String status) {
            this.status = status;
        }

        public String toString() {
            return status;
        }

        @JsonCreator
        public static Status forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Status.valueOf(munged);
        }
    }

    public enum Direction {
        INBOUND("inbound"), OUTBOUND_API("outbound-api"), OUTBOUND_CALL("outbound-call"), OUTBOUND_REPLY(
                "outbound-reply");
        private final String direction;

        private Direction(final String direction) {
            this.direction = direction;
        }

        public String toString() {
            return direction;
        }

        @JsonCreator
        public static Direction forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Direction.valueOf(munged);
        }
    }
}
