/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.marketplace.availableaddon;

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
public class AvailableAddOnExtension extends Resource {
    private static final long serialVersionUID = 88592059649526L;

    /**
     * Create a AvailableAddOnExtensionFetcher to execute fetch.
     *
     * @param pathAvailableAddOnSid The SID of the AvailableAddOn resource with the
     *                              extension to fetch
     * @param pathSid The SID of the AvailableAddOn Extension resource to fetch
     * @return AvailableAddOnExtensionFetcher capable of executing the fetch
     */
    public static AvailableAddOnExtensionFetcher fetcher(final String pathAvailableAddOnSid,
                                                         final String pathSid) {
        return new AvailableAddOnExtensionFetcher(pathAvailableAddOnSid, pathSid);
    }

    /**
     * Create a AvailableAddOnExtensionReader to execute read.
     *
     * @param pathAvailableAddOnSid The SID of the AvailableAddOn resource with the
     *                              extensions to read
     * @return AvailableAddOnExtensionReader capable of executing the read
     */
    public static AvailableAddOnExtensionReader reader(final String pathAvailableAddOnSid) {
        return new AvailableAddOnExtensionReader(pathAvailableAddOnSid);
    }

    /**
     * Converts a JSON String into a AvailableAddOnExtension object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return AvailableAddOnExtension object represented by the provided JSON
     */
    public static AvailableAddOnExtension fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AvailableAddOnExtension.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a AvailableAddOnExtension object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return AvailableAddOnExtension object represented by the provided JSON
     */
    public static AvailableAddOnExtension fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AvailableAddOnExtension.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String availableAddOnSid;
    private final String friendlyName;
    private final String productName;
    private final String uniqueName;
    private final URI url;

    @JsonCreator
    private AvailableAddOnExtension(@JsonProperty("sid")
                                    final String sid,
                                    @JsonProperty("available_add_on_sid")
                                    final String availableAddOnSid,
                                    @JsonProperty("friendly_name")
                                    final String friendlyName,
                                    @JsonProperty("product_name")
                                    final String productName,
                                    @JsonProperty("unique_name")
                                    final String uniqueName,
                                    @JsonProperty("url")
                                    final URI url) {
        this.sid = sid;
        this.availableAddOnSid = availableAddOnSid;
        this.friendlyName = friendlyName;
        this.productName = productName;
        this.uniqueName = uniqueName;
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
     * Returns The SID of the AvailableAddOn resource to which this extension
     * applies.
     *
     * @return The SID of the AvailableAddOn resource to which this extension
     *         applies
     */
    public final String getAvailableAddOnSid() {
        return this.availableAddOnSid;
    }

    /**
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The name of the Extension's Product.
     *
     * @return The name of the Extension's Product
     */
    public final String getProductName() {
        return this.productName;
    }

    /**
     * Returns An application-defined string that uniquely identifies the resource.
     *
     * @return An application-defined string that uniquely identifies the resource
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
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

        AvailableAddOnExtension other = (AvailableAddOnExtension) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(availableAddOnSid, other.availableAddOnSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(productName, other.productName) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            availableAddOnSid,
                            friendlyName,
                            productName,
                            uniqueName,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("availableAddOnSid", availableAddOnSid)
                          .add("friendlyName", friendlyName)
                          .add("productName", productName)
                          .add("uniqueName", uniqueName)
                          .add("url", url)
                          .toString();
    }
}