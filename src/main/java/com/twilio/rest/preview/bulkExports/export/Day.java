/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.bulkExports.export;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Day extends Resource {
    private static final long serialVersionUID = 101410619433718L;

    /**
     * Create a DayFetcher to execute fetch.
     *
     * @param pathResourceType The type of communication – Messages, Calls
     * @param pathDay The date of the data in the file
     * @return DayFetcher capable of executing the fetch
     */
    public static DayFetcher fetcher(final String pathResourceType,
                                     final String pathDay) {
        return new DayFetcher(pathResourceType, pathDay);
    }

    /**
     * Create a DayReader to execute read.
     *
     * @param pathResourceType The type of communication – Messages, Calls
     * @return DayReader capable of executing the read
     */
    public static DayReader reader(final String pathResourceType) {
        return new DayReader(pathResourceType);
    }

    /**
     * Converts a JSON String into a Day object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Day object represented by the provided JSON
     */
    public static Day fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Day.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Day object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Day object represented by the provided JSON
     */
    public static Day fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Day.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final URI redirectTo;
    private final String day;
    private final Integer size;
    private final String createDate;
    private final String friendlyName;
    private final String resourceType;

    @JsonCreator
    private Day(@JsonProperty("redirect_to")
                final URI redirectTo,
                @JsonProperty("day")
                final String day,
                @JsonProperty("size")
                final Integer size,
                @JsonProperty("create_date")
                final String createDate,
                @JsonProperty("friendly_name")
                final String friendlyName,
                @JsonProperty("resource_type")
                final String resourceType) {
        this.redirectTo = redirectTo;
        this.day = day;
        this.size = size;
        this.createDate = createDate;
        this.friendlyName = friendlyName;
        this.resourceType = resourceType;
    }

    /**
     * Returns The redirect_to.
     *
     * @return The redirect_to
     */
    public final URI getRedirectTo() {
        return this.redirectTo;
    }

    /**
     * Returns The date of the data in the file.
     *
     * @return The date of the data in the file
     */
    public final String getDay() {
        return this.day;
    }

    /**
     * Returns Size of the file in bytes.
     *
     * @return Size of the file in bytes
     */
    public final Integer getSize() {
        return this.size;
    }

    /**
     * Returns The date when resource is created.
     *
     * @return The date when resource is created
     */
    public final String getCreateDate() {
        return this.createDate;
    }

    /**
     * Returns The friendly name specified when creating the job.
     *
     * @return The friendly name specified when creating the job
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The type of communication – Messages, Calls.
     *
     * @return The type of communication – Messages, Calls
     */
    public final String getResourceType() {
        return this.resourceType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Day other = (Day) o;

        return Objects.equals(redirectTo, other.redirectTo) &&
               Objects.equals(day, other.day) &&
               Objects.equals(size, other.size) &&
               Objects.equals(createDate, other.createDate) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(resourceType, other.resourceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redirectTo,
                            day,
                            size,
                            createDate,
                            friendlyName,
                            resourceType);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("redirectTo", redirectTo)
                          .add("day", day)
                          .add("size", size)
                          .add("createDate", createDate)
                          .add("friendlyName", friendlyName)
                          .add("resourceType", resourceType)
                          .toString();
    }
}