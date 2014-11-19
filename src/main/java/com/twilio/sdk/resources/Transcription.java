package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.deleters.TranscriptionDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.TranscriptionFetcher;
import com.twilio.sdk.readers.TranscriptionReader;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Transcription extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final Transcription.Status status;
    private final String recordingSid;
    private final DateTime dateUpdated;
    private final BigDecimal price;
    private final String uri;
    private final String ownerAccountSid;
    private final String accountSid;
    private final String sid;
    private final Integer duration;
    private final DateTime dateCreated;
    private final String transcriptionText;
    private final Currency priceUnit;

    @JsonCreator
    private Transcription(@JsonProperty("status") final Transcription.Status status,
                          @JsonProperty("recording_sid") final String recordingSid,
                          @JsonProperty("date_updated") final String dateUpdated,
                          @JsonProperty("price") final BigDecimal price, @JsonProperty("uri") final String uri,
                          @JsonProperty("owner_account_sid") final String ownerAccountSid,
                          @JsonProperty("account_sid") final String accountSid, @JsonProperty("sid") final String sid,
                          @JsonProperty("duration") final Integer duration,
                          @JsonProperty("date_created") final String dateCreated,
                          @JsonProperty("transcription_text") final String transcriptionText,
                          @JsonProperty("price_unit") final Currency priceUnit) {
        this.status = status;
        this.recordingSid = recordingSid;
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.price = price;
        this.uri = uri;
        this.ownerAccountSid = ownerAccountSid;
        this.accountSid = accountSid;
        this.sid = sid;
        this.duration = duration;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.transcriptionText = transcriptionText;
        this.priceUnit = priceUnit;

    }

    public static TranscriptionDeleter delete(final String sid) {
        return new TranscriptionDeleter(sid);
    }

    public static TranscriptionDeleter delete(final Transcription target) {
        return new TranscriptionDeleter(target);
    }

    public static TranscriptionFetcher fetch(final String sid) {
        return new TranscriptionFetcher(sid);
    }

    public static TranscriptionReader list() {
        return new TranscriptionReader();
    }

    public static Transcription fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Transcription.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Transcription fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Transcription.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final Transcription.Status getStatus() {
        return status;
    }

    public final String getRecordingSid() {
        return recordingSid;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final BigDecimal getPrice() {
        return price;
    }

    public final String getUri() {
        return uri;
    }

    public final String getOwnerAccountSid() {
        return ownerAccountSid;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getSid() {
        return sid;
    }

    public final Integer getDuration() {
        return duration;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final String getTranscriptionText() {
        return transcriptionText;
    }

    public final Currency getPriceUnit() {
        return priceUnit;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transcription self = (Transcription) o;

        return (Objects.equals(status, self.status) &&
                Objects.equals(recordingSid, self.recordingSid) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(price, self.price) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(ownerAccountSid, self.ownerAccountSid) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(duration, self.duration) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(transcriptionText, self.transcriptionText) &&
                Objects.equals(priceUnit, self.priceUnit));
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, recordingSid, dateUpdated, price, uri, ownerAccountSid, accountSid, sid, duration,
                            dateCreated, transcriptionText, priceUnit);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("status", status)
                          .add("recordingSid", recordingSid)
                          .add("dateUpdated", dateUpdated)
                          .add("price", price)
                          .add("uri", uri)
                          .add("ownerAccountSid", ownerAccountSid)
                          .add("accountSid", accountSid)
                          .add("sid", sid)
                          .add("duration", duration)
                          .add("dateCreated", dateCreated)
                          .add("transcriptionText", transcriptionText)
                          .add("priceUnit", priceUnit)
                          .toString();
    }

    public enum Status {
        IN_PROGRESS("in-progress"), COMPLETED("completed"), FAILED("failed");
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
}
