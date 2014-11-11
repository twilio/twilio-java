package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.deleters.CallDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.CallFetcher;
import com.twilio.sdk.readers.CallReader;
import com.twilio.sdk.updaters.CallUpdater;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class Call extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    public enum Status {
        QUEUED("queued"),
        RINGING("ringing"),
        IN_PROGRESS("in-progress"),
        COMPLETED("completed"),
        BUSY("busy"),
        FAILED("failed"),
        NO_ANSWER("no-answer"),
        CANCELED("canceled");

        private final String status;

        private Status(final String status) {
            this.status = status;
        }

        public String toString() {
            return status;
        }

        @JsonCreator
        public static Status forValue(final String value) {
            String munged = value.replace("-", "_").toUpperCase();
            return Status.valueOf(munged);
        }

    }

    public static CallCreator create(final String to, final String from, final URI uri) {
        return new CallCreator(to, from, uri);
    }

    public static CallCreator create(final String to, final String from, final String applicationSid) {
        return new CallCreator(to, from, applicationSid);
    }

    public static CallDeleter delete(final String sid) {
        return new CallDeleter(sid);
    }

    public static CallDeleter delete(final Call call) {
        return new CallDeleter(call);
    }

    public static CallUpdater update(final String sid) {
        return new CallUpdater(sid);
    }

    public static CallUpdater update(final Call call) {
        return new CallUpdater(call);
    }

    public static CallReader list() {
        return new CallReader();
    }

    public static CallFetcher fetch(final String sid) {
        return new CallFetcher(sid);
    }

    private final String accountSid;
    private final String annotation;
    private final String answeredBy;
    private final String apiVersion;
    private final String callerName;
    private final Calendar dateCreated;
    private final Calendar dateUpdated;
    private final String direction;
    private final Integer duration;
    private final Calendar endTime;
    private final String forwardedFrom;
    private final String from;
    private final String fromFormatted;
    private final String groupSid;
    private final String parentCallSid;
    private final String phoneNumberSid;
    private final Double price;
    private final String priceUnit;
    private final String sid;
    private final Calendar startTime;
    private final Status status;
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
                 @JsonProperty("date_created") final Calendar dateCreated,
                 @JsonProperty("date_updated") final Calendar dateUpdated,
                 @JsonProperty("direction") final String direction, @JsonProperty("duration") final Integer duration,
                 @JsonProperty("end_time") final Calendar endTime,
                 @JsonProperty("forwarded_from") final String forwardedFrom, @JsonProperty("from") final String from,
                 @JsonProperty("from_formatted") final String fromFormatted,
                 @JsonProperty("group_sid") final String groupSid,
                 @JsonProperty("parent_call_sid") final String parentCallSid,
                 @JsonProperty("phone_number_sid") final String phoneNumberSid,
                 @JsonProperty("price") final Double price, @JsonProperty("price_unit") final String priceUnit,
                 @JsonProperty("sid") final String sid, @JsonProperty("start_time") final Calendar startTime,
                 @JsonProperty("status") final Status status,
                 @JsonProperty("subresource_uris") final Map<String, String> subresourceUris,
                 @JsonProperty("to") final String to, @JsonProperty("to_formatted") final String toFormatted,
                 @JsonProperty("uri") final String uri) {
        this.accountSid = accountSid;
        this.annotation = annotation;
        this.answeredBy = answeredBy;
        this.apiVersion = apiVersion;
        this.callerName = callerName;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.direction = direction;
        this.duration = duration;
        this.endTime = endTime;
        this.forwardedFrom = forwardedFrom;
        this.from = from;
        this.fromFormatted = fromFormatted;
        this.groupSid = groupSid;
        this.parentCallSid = parentCallSid;
        this.phoneNumberSid = phoneNumberSid;
        this.price = price;
        this.priceUnit = priceUnit;
        this.sid = sid;
        this.startTime = startTime;
        this.status = status;
        this.subresourceUris = subresourceUris;
        this.to = to;
        this.toFormatted = toFormatted;
        this.uri = uri;

    }

    public static Call fromJson(final String json, final ObjectMapper objectMapper) throws ApiException,
                                                                                           ApiConnectionException {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Call.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Call fromJson(final InputStream json, final ObjectMapper objectMapper) throws ApiException,
                                                                                                ApiConnectionException {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Call.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getAnnotation() {
        return annotation;
    }

    public final String getAnsweredBy() {
        return answeredBy;
    }

    public final String getApiVersion() {
        return apiVersion;
    }

    public final String getCallerName() {
        return callerName;
    }

    public final Calendar getDateCreated() {
        return dateCreated;
    }

    public final Calendar getDateUpdated() {
        return dateUpdated;
    }

    public final String getDirection() {
        return direction;
    }

    public final Integer getDuration() {
        return duration != null ? duration : 0;
    }

    public final Calendar getEndTime() {
        return endTime;
    }

    public final String getForwardedFrom() {
        return forwardedFrom;
    }

    public final String getFrom() {
        return from;
    }

    public final String getFromFormatted() {
        return fromFormatted;
    }

    public final String getGroupSid() {
        return groupSid;
    }

    public final String getParentCallSid() {
        return parentCallSid;
    }

    public final String getPhoneNumberSid() {
        return phoneNumberSid;
    }

    public final Double getPrice() {
        return price;
    }

    public final String getPriceUnit() {
        return priceUnit;
    }

    public final String getSid() {
        return sid;
    }

    public final Calendar getStartTime() {
        return startTime;
    }

    public final Status getStatus() {
        return status;
    }

    public final Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    public final String getTo() {
        return to;
    }

    public final String getToFormatted() {
        return toFormatted;
    }

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

        Call call = (Call) o;

        return Objects.equals(accountSid, call.accountSid) &&
               Objects.equals(annotation, call.annotation) &&
               Objects.equals(answeredBy, call.answeredBy) &&
               Objects.equals(apiVersion, call.apiVersion) &&
               Objects.equals(callerName, call.callerName) &&
               Objects.equals(dateCreated, call.dateCreated) &&
               Objects.equals(dateUpdated, call.dateUpdated) &&
               Objects.equals(direction, call.direction) &&
               Objects.equals(duration, call.duration) &&
               Objects.equals(endTime, call.endTime) &&
               Objects.equals(forwardedFrom, call.forwardedFrom) &&
               Objects.equals(from, call.from) &&
               Objects.equals(fromFormatted, call.fromFormatted) &&
               Objects.equals(groupSid, call.groupSid) &&
               Objects.equals(parentCallSid, call.parentCallSid) &&
               Objects.equals(phoneNumberSid, call.phoneNumberSid) &&
               Objects.equals(price, call.price) &&
               Objects.equals(priceUnit, call.priceUnit) &&
               Objects.equals(sid, call.sid) &&
               Objects.equals(startTime, call.startTime) &&
               Objects.equals(status, call.status) &&
               Objects.equals(subresourceUris, call.subresourceUris) &&
               Objects.equals(to, call.to) &&
               Objects.equals(toFormatted, call.toFormatted) &&
               Objects.equals(uri, call.uri);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(accountSid, annotation, answeredBy, apiVersion, callerName, dateCreated, dateUpdated, direction,
                      duration, endTime, forwardedFrom, from, fromFormatted, groupSid, parentCallSid, phoneNumberSid,
                      price, priceUnit, sid, startTime, status, subresourceUris, to, toFormatted, uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("accountSid", accountSid).add("annotation", annotation)
                          .add("answeredBy", answeredBy).add("apiVersion", apiVersion).add("callerName", callerName)
                          .add("dateCreated", dateCreated).add("dateUpdated", dateUpdated).add("direction", direction)
                          .add("duration", duration).add("endTime", endTime).add("forwardedFrom", forwardedFrom)
                          .add("from", from).add("fromFormatted", fromFormatted).add("groupSid", groupSid)
                          .add("parentCallSid", parentCallSid).add("phoneNumberSid", phoneNumberSid).add("price", price)
                          .add("priceUnit", priceUnit).add("sid", sid).add("startTime", startTime).add("status", status)
                          .add("subresourceUris", subresourceUris).add("to", to).add("toFormatted", toFormatted)
                          .add("uri", uri).toString();
    }
}
