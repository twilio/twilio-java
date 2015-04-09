package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.deleters.CallDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.CallFetcher;
import com.twilio.sdk.readers.CallReader;
import com.twilio.sdk.updaters.CallUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Call extends SidResource {
    private static final long serialVersionUID = -6797133745651458528L;

    public enum Status {
        QUEUED("queued"),
        RINGING("ringing"),
        IN_PROGRESS("in-progress"),
        COMPLETED("completed"),
        BUSY("busy"),
        FAILED("failed"),
        NO_ANSWER("no-answer"),
        CANCELED("canceled");

        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static Status forValue(final String value) {
            String munged = value.replace("-", "_").toUpperCase();
            return Status.valueOf(munged);
        }
    }

    /**
     * Create a new outgoing call to phones, SIP-enabled endpoints or Twilio
     * Client connections
     *
     * @param to Phone number, SIP address or client identifier to call
     * @param from Twilio number from which to originate the call
     * @param url Url from which to fetch TwiML
     * @return CallCreator capable of executing the create
     */
    public static CallCreator create(final String to, final String from, final URI url) {
        return new CallCreator(to, from, url);
    }

    /**
     * Create a new outgoing call to phones, SIP-enabled endpoints or Twilio
     * Client connections
     *
     * @param to Phone number, SIP address or client identifier to call
     * @param from Twilio number from which to originate the call
     * @param applicationSid ApplicationSid that configures from where to fetch
     *                       TwiML
     * @return CallCreator capable of executing the create
     */
    public static CallCreator create(final String to, final String from, final String applicationSid) {
        return new CallCreator(to, from, applicationSid);
    }

    /**
     * Deletes a call record from your account. Once the record is deleted, it
     * will no longer apper in the API and Account Portal logs.
     *
     * @param sid Call Sid that uniquely identifies the Call to delete
     * @return CallDeleter capable of executing the delete
     */
    public static CallDeleter delete(final String sid) {
        return new CallDeleter(sid);
    }

    /**
     * Fetch the Call specified by the provided Call Sid
     *
     * @param sid Call Sid that uniquely identifies the Call to fetch
     * @return CallFetcher capable of executing the fetch
     */
    public static CallFetcher fetch(final String sid) {
        return new CallFetcher(sid);
    }

    /**
     * Retrieves a collection of Calls made to and from your account
     *
     * @return CallReader capable of executing the read
     */
    public static CallReader read() {
        return new CallReader();
    }

    /**
     * Initiates a call redirect or terminates a call
     *
     * @param sid Call Sid that uniquely identifies the Call to update
     * @return CallUpdater capable of executing the update
     */
    public static CallUpdater update(final String sid) {
        return new CallUpdater(sid);
    }

    /**
     * Converts a JSON String into a Call object using the provided ObjectMapper
     *
     * @param json The raw JSON string
     * @param objectMapper The Jackson ObjectMapper
     * @return The Call object represented by the provided JSON
     */
    public static Call fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Call.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Call object using the provided
     * ObjectMapper
     *
     * @param json The raw JSON InputStream
     * @param objectMapper The Jackson ObjectMapper
     * @return The Call object represented by the provided JSON
     */
    public static Call fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Call.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String annotation;
    private final String answeredBy;
    private final String apiVersion;
    private final String callerName;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String direction;
    private final Integer duration;
    private final DateTime endTime;
    private final String forwardedFrom;
    private final String from;
    private final String fromFormatted;
    private final String groupSid;
    private final String parentCallSid;
    private final String phoneNumberSid;
    private final BigDecimal price;
    private final Currency priceUnit;
    private final String sid;
    private final DateTime startTime;
    private final Call.Status status;
    private final Map<String, String> subresourceUris;
    private final String to;
    private final String toFormatted;
    private final String uri;

    @JsonCreator
    private Call(@JsonProperty("account_sid") final String accountSid,
                 @JsonProperty("annotation") final String annotation,
                 @JsonProperty("answered_by") final String answeredBy,
                 @JsonProperty("api_version") final String apiVersion,
                 @JsonProperty("caller_name") final String callerName,
                 @JsonProperty("date_created") final String dateCreated,
                 @JsonProperty("date_updated") final String dateUpdated,
                 @JsonProperty("direction") final String direction,
                 @JsonProperty("duration") final Integer duration,
                 @JsonProperty("end_time") final String endTime,
                 @JsonProperty("forwarded_from") final String forwardedFrom,
                 @JsonProperty("from") final String from,
                 @JsonProperty("from_formatted") final String fromFormatted,
                 @JsonProperty("group_sid") final String groupSid,
                 @JsonProperty("parent_call_sid") final String parentCallSid,
                 @JsonProperty("phone_number_sid") final String phoneNumberSid,
                 @JsonProperty("price") final BigDecimal price,
                 @JsonProperty("price_unit") final Currency priceUnit,
                 @JsonProperty("sid") final String sid,
                 @JsonProperty("start_time") final String startTime,
                 @JsonProperty("status") final Call.Status status,
                 @JsonProperty("subresource_uris") final Map<String, String> subresourceUris,
                 @JsonProperty("to") final String to,
                 @JsonProperty("to_formatted") final String toFormatted,
                 @JsonProperty("uri") final String uri) {
        this.accountSid = accountSid;
        this.annotation = annotation;
        this.answeredBy = answeredBy;
        this.apiVersion = apiVersion;
        this.callerName = callerName;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.direction = direction;
        this.duration = duration;
        this.endTime = MarshalConverter.dateTimeFromString(endTime);
        this.forwardedFrom = forwardedFrom;
        this.from = from;
        this.fromFormatted = fromFormatted;
        this.groupSid = groupSid;
        this.parentCallSid = parentCallSid;
        this.phoneNumberSid = phoneNumberSid;
        this.price = price;
        this.priceUnit = priceUnit;
        this.sid = sid;
        this.startTime = MarshalConverter.dateTimeFromString(startTime);
        this.status = status;
        this.subresourceUris = subresourceUris;
        this.to = to;
        this.toFormatted = toFormatted;
        this.uri = uri;
    }

    /**
     * @return The unique id of the Account responsible for creating this Call.
     */
    public final String getAccountSid() {
        return accountSid;
    }

    /**
     * @return The annotation provided for the Call
     */
    public final String getAnnotation() {
        return annotation;
    }

    /**
     * @return If this call was initiated with answering machine detection,
     *         either `human` or `machine`. Empty otherwise.
     */
    public final String getAnsweredBy() {
        return answeredBy;
    }

    /**
     * @return The API Version the Call was created through
     */
    public final String getApiVersion() {
        return apiVersion;
    }

    /**
     * @return If this call was an incoming call to a phone number with Caller
     *         ID Lookup enabled, the caller's name. Empty otherwise.
     */
    public final String getCallerName() {
        return callerName;
    }

    /**
     * @return The date that this resource was created
     */
    public final DateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * @return The date that this resource was last updated
     */
    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @return A string describing the direction of the call. `inbound` for
     *         inbound calls, `outbound-api` for calls initiated via the REST
     *         API or `outbound-dial` for calls initiated by a `<Dial>` verb.
     */
    public final String getDirection() {
        return direction;
    }

    /**
     * @return The length of the call in seconds.
     */
    public final Integer getDuration() {
        return duration;
    }

    /**
     * @return The end time of the Call. Null if the call did not complete
     *         successfully.
     */
    public final DateTime getEndTime() {
        return endTime;
    }

    /**
     * @return If this Call was an incoming call forwarded from another number,
     *         the forwarding phone number (depends on carrier supporting
     *         forwarding). Empty otherwise.
     */
    public final String getForwardedFrom() {
        return forwardedFrom;
    }

    /**
     * @return The phone number, SIP address or Client identifier that made this
     *         Call. Phone numbers are in E.164 format (e.g. +16175551212). SIP
     *         addresses are formated as `name@company.com`. Client identifiers
     *         are formatted `client:name`.
     */
    public final String getFrom() {
        return from;
    }

    /**
     * @return The phone number, SIP address or Client identifier that made this
     *         Call. Formatted for display.
     */
    public final String getFromFormatted() {
        return fromFormatted;
    }

    /**
     * @return A 34 character Group Sid associated with this Call. Empty if no
     *         Group is associated with the Call.
     */
    public final String getGroupSid() {
        return groupSid;
    }

    /**
     * @return A 34 character string that uniquely identifies the Call that
     *         created this leg.
     */
    public final String getParentCallSid() {
        return parentCallSid;
    }

    /**
     * @return If the call was inbound, this is the Sid of the
     *         IncomingPhoneNumber that received the call. If the call was
     *         outbound, it is the Sid of the OutgoingCallerId from which the
     *         call was placed.
     */
    public final String getPhoneNumberSid() {
        return phoneNumberSid;
    }

    /**
     * @return The charge for this call, in the currency associated with the
     *         account. Populated after the call is completed. May not be
     *         immediately available.
     */
    public final BigDecimal getPrice() { return price; }

    /**
     * @return The currency in which `Price` is measured.
     */
    public final Currency getPriceUnit() {
        return priceUnit;
    }

    /**
     * @return A 34 character string that uniquely identifies this resource.
     */
    public final String getSid() {
        return sid;
    }

    /**
     * @return The start time of the Call. Null if the call has not yet been
     *         dialed.
     */
    public final DateTime getStartTime() {
        return startTime;
    }

    /**
     * @return Status of the Call.
     */
    public final Status getStatus() {
        return status;
    }

    /**
     * @return Call Instance Subresources
     */
    public final Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    /**
     * @return The phone number, SIP address or Client identifier that received
     *         this Call. Phone numbers are in E.164 format (e.g. +16175551212).
     *         SIP addresses are formatted as `name@company.com`. Client
     *         identifiers are formatted `client:name`.
     */
    public final String getTo() {
        return to;
    }

    /**
     * @return The phone number, SIP address or Client identifier that received
     *         this Call. Formatted for display.
     */
    public final String getToFormatted() {
        return toFormatted;
    }

    /**
     * @return The URI for this resource, relative to `https://api.twilio.com`
     */
    public final String getUri() {
        return uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Call other = (Call) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(annotation, other.annotation) &&
               Objects.equals(answeredBy, other.answeredBy) &&
               Objects.equals(apiVersion, other.apiVersion) &&
               Objects.equals(callerName, other.callerName) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(direction, other.direction) &&
               Objects.equals(duration, other.duration) &&
               Objects.equals(endTime, other.endTime) &&
               Objects.equals(forwardedFrom, other.forwardedFrom) &&
               Objects.equals(from, other.from) &&
               Objects.equals(fromFormatted, other.fromFormatted) &&
               Objects.equals(groupSid, other.groupSid) &&
               Objects.equals(parentCallSid, other.parentCallSid) &&
               Objects.equals(phoneNumberSid, other.phoneNumberSid) &&
               Objects.equals(price, other.price) &&
               Objects.equals(priceUnit, other.priceUnit) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(startTime, other.startTime) &&
               Objects.equals(status, other.status) &&
               Objects.equals(subresourceUris, other.subresourceUris) &&
               Objects.equals(to, other.to) &&
               Objects.equals(toFormatted, other.toFormatted) &&
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            annotation,
                            answeredBy,
                            apiVersion,
                            callerName,
                            dateCreated,
                            dateUpdated,
                            direction,
                            duration,
                            endTime,
                            forwardedFrom,
                            from,
                            fromFormatted,
                            groupSid,
                            parentCallSid,
                            phoneNumberSid,
                            price,
                            priceUnit,
                            sid,
                            startTime,
                            status,
                            subresourceUris,
                            to,
                            toFormatted,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("annotation", annotation)
                          .add("answeredBy", answeredBy)
                          .add("apiVersion", apiVersion)
                          .add("callerName", callerName)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("direction", direction)
                          .add("duration", duration)
                          .add("endTime", endTime)
                          .add("forwardedFrom", forwardedFrom)
                          .add("from", from)
                          .add("fromFormatted", fromFormatted)
                          .add("groupSid", groupSid)
                          .add("parentCallSid", parentCallSid)
                          .add("phoneNumberSid", phoneNumberSid)
                          .add("price", price)
                          .add("priceUnit", priceUnit)
                          .add("sid", sid)
                          .add("startTime", startTime)
                          .add("status", status)
                          .add("subresourceUris", subresourceUris)
                          .add("to", to)
                          .add("toFormatted", toFormatted)
                          .add("uri", uri)
                          .toString();
    }
}
