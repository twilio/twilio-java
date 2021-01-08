/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.serverless.v1.service.function;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class FunctionVersion extends Resource {
    private static final long serialVersionUID = 198867416400366L;

    public enum Visibility {
        PUBLIC("public"),
        PRIVATE("private"),
        PROTECTED("protected");

        private final String value;

        private Visibility(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Visibility from a string.
         * @param value string value
         * @return generated Visibility
         */
        @JsonCreator
        public static Visibility forValue(final String value) {
            return Promoter.enumFromString(value, Visibility.values());
        }
    }

    /**
     * Create a FunctionVersionReader to execute read.
     *
     * @param pathServiceSid The SID of the Service to read the Function Version
     *                       resources from
     * @param pathFunctionSid The SID of the function that is the parent of the
     *                        Function Version resources to read
     * @return FunctionVersionReader capable of executing the read
     */
    public static FunctionVersionReader reader(final String pathServiceSid,
                                               final String pathFunctionSid) {
        return new FunctionVersionReader(pathServiceSid, pathFunctionSid);
    }

    /**
     * Create a FunctionVersionFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Service to fetch the Function Version
     *                       resource from
     * @param pathFunctionSid The SID of the function that is the parent of the
     *                        Function Version resource to fetch
     * @param pathSid The SID that identifies the Function Version resource to fetch
     * @return FunctionVersionFetcher capable of executing the fetch
     */
    public static FunctionVersionFetcher fetcher(final String pathServiceSid,
                                                 final String pathFunctionSid,
                                                 final String pathSid) {
        return new FunctionVersionFetcher(pathServiceSid, pathFunctionSid, pathSid);
    }

    /**
     * Converts a JSON String into a FunctionVersion object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return FunctionVersion object represented by the provided JSON
     */
    public static FunctionVersion fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FunctionVersion.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a FunctionVersion object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return FunctionVersion object represented by the provided JSON
     */
    public static FunctionVersion fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FunctionVersion.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String functionSid;
    private final String path;
    private final FunctionVersion.Visibility visibility;
    private final ZonedDateTime dateCreated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private FunctionVersion(@JsonProperty("sid")
                            final String sid,
                            @JsonProperty("account_sid")
                            final String accountSid,
                            @JsonProperty("service_sid")
                            final String serviceSid,
                            @JsonProperty("function_sid")
                            final String functionSid,
                            @JsonProperty("path")
                            final String path,
                            @JsonProperty("visibility")
                            final FunctionVersion.Visibility visibility,
                            @JsonProperty("date_created")
                            final String dateCreated,
                            @JsonProperty("url")
                            final URI url,
                            @JsonProperty("links")
                            final Map<String, String> links) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.functionSid = functionSid;
        this.path = path;
        this.visibility = visibility;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The unique string that identifies the Function Version resource.
     *
     * @return The unique string that identifies the Function Version resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the Account that created the Function Version resource.
     *
     * @return The SID of the Account that created the Function Version resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Service that the Function Version resource is
     * associated with.
     *
     * @return The SID of the Service that the Function Version resource is
     *         associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The SID of the Function resource that is the parent of the Function
     * Version resource.
     *
     * @return The SID of the Function resource that is the parent of the Function
     *         Version resource
     */
    public final String getFunctionSid() {
        return this.functionSid;
    }

    /**
     * Returns The URL-friendly string by which the Function Version resource can be
     * referenced.
     *
     * @return The URL-friendly string by which the Function Version resource can
     *         be referenced
     */
    public final String getPath() {
        return this.path;
    }

    /**
     * Returns The access control that determines how the Function Version resource
     * can be accessed.
     *
     * @return The access control that determines how the Function Version resource
     *         can be accessed
     */
    public final FunctionVersion.Visibility getVisibility() {
        return this.visibility;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the Function Version resource
     * was created.
     *
     * @return The ISO 8601 date and time in GMT when the Function Version resource
     *         was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The absolute URL of the Function Version resource.
     *
     * @return The absolute URL of the Function Version resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The links.
     *
     * @return The links
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

        FunctionVersion other = (FunctionVersion) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(functionSid, other.functionSid) &&
               Objects.equals(path, other.path) &&
               Objects.equals(visibility, other.visibility) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            functionSid,
                            path,
                            visibility,
                            dateCreated,
                            url,
                            links);
    }
}