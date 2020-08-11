/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.fax.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fax extends Resource {
    private static final long serialVersionUID = 272370485490742L;

    public enum Direction {
        INBOUND("inbound"),
        OUTBOUND("outbound");

        private final String value;

        private Direction(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Direction from a string.
         * @param value string value
         * @return generated Direction
         */
        @JsonCreator
        public static Direction forValue(final String value) {
            return Promoter.enumFromString(value, Direction.values());
        }
    }

    public enum Quality {
        STANDARD("standard"),
        FINE("fine"),
        SUPERFINE("superfine");

        private final String value;

        private Quality(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Quality from a string.
         * @param value string value
         * @return generated Quality
         */
        @JsonCreator
        public static Quality forValue(final String value) {
            return Promoter.enumFromString(value, Quality.values());
        }
    }

    public enum Status {
        QUEUED("queued"),
        PROCESSING("processing"),
        SENDING("sending"),
        DELIVERED("delivered"),
        RECEIVING("receiving"),
        RECEIVED("received"),
        NO_ANSWER("no-answer"),
        BUSY("busy"),
        FAILED("failed"),
        CANCELED("canceled");

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

    public enum UpdateStatus {
        CANCELED("canceled");

        private final String value;

        private UpdateStatus(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a UpdateStatus from a string.
         * @param value string value
         * @return generated UpdateStatus
         */
        @JsonCreator
        public static UpdateStatus forValue(final String value) {
            return Promoter.enumFromString(value, UpdateStatus.values());
        }
    }

    /**
     * Create a FaxFetcher to execute fetch.
     *
     * @param pathSid The unique string that identifies the resource
     * @return FaxFetcher capable of executing the fetch
     */
    public static FaxFetcher fetcher(final String pathSid) {
        return new FaxFetcher(pathSid);
    }

    /**
     * Create a FaxReader to execute read.
     *
     * @return FaxReader capable of executing the read
     */
    public static FaxReader reader() {
        return new FaxReader();
    }

    /**
     * Create a FaxCreator to execute create.
     *
     * @param to The phone number to receive the fax
     * @param mediaUrl The URL of the PDF that contains the fax
     * @return FaxCreator capable of executing the create
     */
    public static FaxCreator creator(final String to,
                                     final URI mediaUrl) {
        return new FaxCreator(to, mediaUrl);
    }

    /**
     * Create a FaxUpdater to execute update.
     *
     * @param pathSid The unique string that identifies the resource
     * @return FaxUpdater capable of executing the update
     */
    public static FaxUpdater updater(final String pathSid) {
        return new FaxUpdater(pathSid);
    }

    /**
     * Create a FaxDeleter to execute delete.
     *
     * @param pathSid The unique string that identifies the resource
     * @return FaxDeleter capable of executing the delete
     */
    public static FaxDeleter deleter(final String pathSid) {
        return new FaxDeleter(pathSid);
    }

    /**
     * Converts a JSON String into a Fax object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Fax object represented by the provided JSON
     */
    public static Fax fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Fax.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Fax object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Fax object represented by the provided JSON
     */
    public static Fax fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Fax.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String from;
    private final String to;
    private final Fax.Quality quality;
    private final String mediaSid;
    private final String mediaUrl;
    private final Integer numPages;
    private final Integer duration;
    private final Fax.Status status;
    private final Fax.Direction direction;
    private final String apiVersion;
    private final BigDecimal price;
    private final Currency priceUnit;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final Map<String, String> links;
    private final URI url;

    @JsonCreator
    private Fax(@JsonProperty("sid")
                final String sid,
                @JsonProperty("account_sid")
                final String accountSid,
                @JsonProperty("from")
                final String from,
                @JsonProperty("to")
                final String to,
                @JsonProperty("quality")
                final Fax.Quality quality,
                @JsonProperty("media_sid")
                final String mediaSid,
                @JsonProperty("media_url")
                final String mediaUrl,
                @JsonProperty("num_pages")
                final Integer numPages,
                @JsonProperty("duration")
                final Integer duration,
                @JsonProperty("status")
                final Fax.Status status,
                @JsonProperty("direction")
                final Fax.Direction direction,
                @JsonProperty("api_version")
                final String apiVersion,
                @JsonProperty("price")
                final BigDecimal price,
                @JsonProperty("price_unit")
                @JsonDeserialize(using = com.twilio.converter.CurrencyDeserializer.class)
                final Currency priceUnit,
                @JsonProperty("date_created")
                final String dateCreated,
                @JsonProperty("date_updated")
                final String dateUpdated,
                @JsonProperty("links")
                final Map<String, String> links,
                @JsonProperty("url")
                final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.from = from;
        this.to = to;
        this.quality = quality;
        this.mediaSid = mediaSid;
        this.mediaUrl = mediaUrl;
        this.numPages = numPages;
        this.duration = duration;
        this.status = status;
        this.direction = direction;
        this.apiVersion = apiVersion;
        this.price = price;
        this.priceUnit = priceUnit;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.links = links;
        this.url = url;
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
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The number the fax was sent from.
     *
     * @return The number the fax was sent from
     */
    public final String getFrom() {
        return this.from;
    }

    /**
     * Returns The phone number that received the fax.
     *
     * @return The phone number that received the fax
     */
    public final String getTo() {
        return this.to;
    }

    /**
     * Returns The quality of the fax.
     *
     * @return The quality of the fax
     */
    public final Fax.Quality getQuality() {
        return this.quality;
    }

    /**
     * Returns The SID of the FaxMedia resource that is associated with the Fax.
     *
     * @return The SID of the FaxMedia resource that is associated with the Fax
     */
    public final String getMediaSid() {
        return this.mediaSid;
    }

    /**
     * Returns The Twilio-hosted URL that can be used to download fax media.
     *
     * @return The Twilio-hosted URL that can be used to download fax media
     */
    public final String getMediaUrl() {
        return this.mediaUrl;
    }

    /**
     * Returns The number of pages contained in the fax document.
     *
     * @return The number of pages contained in the fax document
     */
    public final Integer getNumPages() {
        return this.numPages;
    }

    /**
     * Returns The time it took to transmit the fax.
     *
     * @return The time it took to transmit the fax
     */
    public final Integer getDuration() {
        return this.duration;
    }

    /**
     * Returns The status of the fax.
     *
     * @return The status of the fax
     */
    public final Fax.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The direction of the fax.
     *
     * @return The direction of the fax
     */
    public final Fax.Direction getDirection() {
        return this.direction;
    }

    /**
     * Returns The API version used to transmit the fax.
     *
     * @return The API version used to transmit the fax
     */
    public final String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Returns The fax transmission price.
     *
     * @return The fax transmission price
     */
    public final BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Returns The ISO 4217 currency used for billing.
     *
     * @return The ISO 4217 currency used for billing
     */
    public final Currency getPriceUnit() {
        return this.priceUnit;
    }

    /**
     * Returns The ISO 8601 formatted date and time in GMT when the resource was
     * created.
     *
     * @return The ISO 8601 formatted date and time in GMT when the resource was
     *         created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 formatted date and time in GMT when the resource was
     * last updated.
     *
     * @return The ISO 8601 formatted date and time in GMT when the resource was
     *         last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The URLs of the fax's related resources.
     *
     * @return The URLs of the fax's related resources
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    /**
     * Returns The absolute URL of the fax resource.
     *
     * @return The absolute URL of the fax resource
     */
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

        Fax other = (Fax) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(from, other.from) &&
               Objects.equals(to, other.to) &&
               Objects.equals(quality, other.quality) &&
               Objects.equals(mediaSid, other.mediaSid) &&
               Objects.equals(mediaUrl, other.mediaUrl) &&
               Objects.equals(numPages, other.numPages) &&
               Objects.equals(duration, other.duration) &&
               Objects.equals(status, other.status) &&
               Objects.equals(direction, other.direction) &&
               Objects.equals(apiVersion, other.apiVersion) &&
               Objects.equals(price, other.price) &&
               Objects.equals(priceUnit, other.priceUnit) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(links, other.links) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            from,
                            to,
                            quality,
                            mediaSid,
                            mediaUrl,
                            numPages,
                            duration,
                            status,
                            direction,
                            apiVersion,
                            price,
                            priceUnit,
                            dateCreated,
                            dateUpdated,
                            links,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("from", from)
                          .add("to", to)
                          .add("quality", quality)
                          .add("mediaSid", mediaSid)
                          .add("mediaUrl", mediaUrl)
                          .add("numPages", numPages)
                          .add("duration", duration)
                          .add("status", status)
                          .add("direction", direction)
                          .add("apiVersion", apiVersion)
                          .add("price", price)
                          .add("priceUnit", priceUnit)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("links", links)
                          .add("url", url)
                          .toString();
    }
}
