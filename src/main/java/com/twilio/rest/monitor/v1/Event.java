/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.monitor.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Event extends Resource {
    private static final long serialVersionUID = 124591176455880L;

    /**
     * Create a EventFetcher to execute fetch.
     *
     * @param pathSid The SID that identifies the resource to fetch
     * @return EventFetcher capable of executing the fetch
     */
    public static EventFetcher fetcher(final String pathSid) {
        return new EventFetcher(pathSid);
    }

    /**
     * Create a EventReader to execute read.
     *
     * @return EventReader capable of executing the read
     */
    public static EventReader reader() {
        return new EventReader();
    }

    /**
     * Converts a JSON String into a Event object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Event object represented by the provided JSON
     */
    public static Event fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Event object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Event object represented by the provided JSON
     */
    public static Event fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String actorSid;
    private final String actorType;
    private final String description;
    private final Map<String, Object> eventData;
    private final ZonedDateTime eventDate;
    private final String eventType;
    private final String resourceSid;
    private final String resourceType;
    private final String sid;
    private final String source;
    private final String sourceIpAddress;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Event(@JsonProperty("account_sid")
                  final String accountSid,
                  @JsonProperty("actor_sid")
                  final String actorSid,
                  @JsonProperty("actor_type")
                  final String actorType,
                  @JsonProperty("description")
                  final String description,
                  @JsonProperty("event_data")
                  final Map<String, Object> eventData,
                  @JsonProperty("event_date")
                  final String eventDate,
                  @JsonProperty("event_type")
                  final String eventType,
                  @JsonProperty("resource_sid")
                  final String resourceSid,
                  @JsonProperty("resource_type")
                  final String resourceType,
                  @JsonProperty("sid")
                  final String sid,
                  @JsonProperty("source")
                  final String source,
                  @JsonProperty("source_ip_address")
                  final String sourceIpAddress,
                  @JsonProperty("url")
                  final URI url,
                  @JsonProperty("links")
                  final Map<String, String> links) {
        this.accountSid = accountSid;
        this.actorSid = actorSid;
        this.actorType = actorType;
        this.description = description;
        this.eventData = eventData;
        this.eventDate = DateConverter.iso8601DateTimeFromString(eventDate);
        this.eventType = eventType;
        this.resourceSid = resourceSid;
        this.resourceType = resourceType;
        this.sid = sid;
        this.source = source;
        this.sourceIpAddress = sourceIpAddress;
        this.url = url;
        this.links = links;
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
     * Returns The SID of the actor that caused the event, if available.
     *
     * @return The SID of the actor that caused the event, if available
     */
    public final String getActorSid() {
        return this.actorSid;
    }

    /**
     * Returns The type of actor that caused the event.
     *
     * @return The type of actor that caused the event
     */
    public final String getActorType() {
        return this.actorType;
    }

    /**
     * Returns A description of the event.
     *
     * @return A description of the event
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * Returns A JSON string that represents an object with additional data about
     * the event.
     *
     * @return A JSON string that represents an object with additional data about
     *         the event
     */
    public final Map<String, Object> getEventData() {
        return this.eventData;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the event was recorded.
     *
     * @return The ISO 8601 date and time in GMT when the event was recorded
     */
    public final ZonedDateTime getEventDate() {
        return this.eventDate;
    }

    /**
     * Returns The event's type.
     *
     * @return The event's type
     */
    public final String getEventType() {
        return this.eventType;
    }

    /**
     * Returns The SID of the resource that was affected.
     *
     * @return The SID of the resource that was affected
     */
    public final String getResourceSid() {
        return this.resourceSid;
    }

    /**
     * Returns The type of resource that was affected.
     *
     * @return The type of resource that was affected
     */
    public final String getResourceType() {
        return this.resourceType;
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
     * Returns The originating system or interface that caused the event.
     *
     * @return The originating system or interface that caused the event
     */
    public final String getSource() {
        return this.source;
    }

    /**
     * Returns The IP address of the source.
     *
     * @return The IP address of the source
     */
    public final String getSourceIpAddress() {
        return this.sourceIpAddress;
    }

    /**
     * Returns The absolute URL of the resource that was affected.
     *
     * @return The absolute URL of the resource that was affected
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The absolute URLs of related resources.
     *
     * @return The absolute URLs of related resources
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event other = (Event) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(actorSid, other.actorSid) &&
               Objects.equals(actorType, other.actorType) &&
               Objects.equals(description, other.description) &&
               Objects.equals(eventData, other.eventData) &&
               Objects.equals(eventDate, other.eventDate) &&
               Objects.equals(eventType, other.eventType) &&
               Objects.equals(resourceSid, other.resourceSid) &&
               Objects.equals(resourceType, other.resourceType) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(source, other.source) &&
               Objects.equals(sourceIpAddress, other.sourceIpAddress) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            actorSid,
                            actorType,
                            description,
                            eventData,
                            eventDate,
                            eventType,
                            resourceSid,
                            resourceType,
                            sid,
                            source,
                            sourceIpAddress,
                            url,
                            links);
    }
}