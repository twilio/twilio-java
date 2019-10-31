/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.voice.v1.dialingpermissions;

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
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings extends Resource {
    private static final long serialVersionUID = 187417080450761L;

    /**
     * Create a SettingsFetcher to execute fetch.
     *
     * @return SettingsFetcher capable of executing the fetch
     */
    public static SettingsFetcher fetcher() {
        return new SettingsFetcher();
    }

    /**
     * Create a SettingsUpdater to execute update.
     *
     * @return SettingsUpdater capable of executing the update
     */
    public static SettingsUpdater updater() {
        return new SettingsUpdater();
    }

    /**
     * Converts a JSON String into a Settings object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Settings object represented by the provided JSON
     */
    public static Settings fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Settings.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Settings object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Settings object represented by the provided JSON
     */
    public static Settings fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Settings.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final Boolean dialingPermissionsInheritance;
    private final URI url;

    @JsonCreator
    private Settings(@JsonProperty("dialing_permissions_inheritance")
                     final Boolean dialingPermissionsInheritance,
                     @JsonProperty("url")
                     final URI url) {
        this.dialingPermissionsInheritance = dialingPermissionsInheritance;
        this.url = url;
    }

    /**
     * Returns The `true` if the sub-account will inherit voice dialing permissions
     * from the Master Project; otherwise `false`.
     *
     * @return `true` if the sub-account will inherit voice dialing permissions
     *         from the Master Project; otherwise `false`
     */
    public final Boolean getDialingPermissionsInheritance() {
        return this.dialingPermissionsInheritance;
    }

    /**
     * Returns The The absolute URL of this resource.
     *
     * @return The absolute URL of this resource
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

        Settings other = (Settings) o;

        return Objects.equals(dialingPermissionsInheritance, other.dialingPermissionsInheritance) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dialingPermissionsInheritance,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("dialingPermissionsInheritance", dialingPermissionsInheritance)
                          .add("url", url)
                          .toString();
    }
}