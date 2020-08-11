/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.fax.v1.fax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
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
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FaxMedia extends Resource {
    private static final long serialVersionUID = 8543753722336L;

    /**
     * Create a FaxMediaFetcher to execute fetch.
     *
     * @param pathFaxSid The SID of the fax with the FaxMedia resource to fetch
     * @param pathSid The unique string that identifies the resource to fetch
     * @return FaxMediaFetcher capable of executing the fetch
     */
    public static FaxMediaFetcher fetcher(final String pathFaxSid,
                                          final String pathSid) {
        return new FaxMediaFetcher(pathFaxSid, pathSid);
    }

    /**
     * Create a FaxMediaReader to execute read.
     *
     * @param pathFaxSid The SID of the fax with the FaxMedia resources to read
     * @return FaxMediaReader capable of executing the read
     */
    public static FaxMediaReader reader(final String pathFaxSid) {
        return new FaxMediaReader(pathFaxSid);
    }

    /**
     * Create a FaxMediaDeleter to execute delete.
     *
     * @param pathFaxSid The SID of the fax with the FaxMedia resource to delete
     * @param pathSid The unique string that identifies the resource
     * @return FaxMediaDeleter capable of executing the delete
     */
    public static FaxMediaDeleter deleter(final String pathFaxSid,
                                          final String pathSid) {
        return new FaxMediaDeleter(pathFaxSid, pathSid);
    }

    /**
     * Converts a JSON String into a FaxMedia object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return FaxMedia object represented by the provided JSON
     */
    public static FaxMedia fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FaxMedia.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a FaxMedia object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return FaxMedia object represented by the provided JSON
     */
    public static FaxMedia fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FaxMedia.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String faxSid;
    private final String contentType;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private FaxMedia(@JsonProperty("sid")
                     final String sid,
                     @JsonProperty("account_sid")
                     final String accountSid,
                     @JsonProperty("fax_sid")
                     final String faxSid,
                     @JsonProperty("content_type")
                     final String contentType,
                     @JsonProperty("date_created")
                     final String dateCreated,
                     @JsonProperty("date_updated")
                     final String dateUpdated,
                     @JsonProperty("url")
                     final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.faxSid = faxSid;
        this.contentType = contentType;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
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
     * Returns The SID of the fax the FaxMedia resource is associated with.
     *
     * @return The SID of the fax the FaxMedia resource is associated with
     */
    public final String getFaxSid() {
        return this.faxSid;
    }

    /**
     * Returns The content type of the stored fax media.
     *
     * @return The content type of the stored fax media
     */
    public final String getContentType() {
        return this.contentType;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The absolute URL of the FaxMedia resource.
     *
     * @return The absolute URL of the FaxMedia resource
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

        FaxMedia other = (FaxMedia) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(faxSid, other.faxSid) &&
               Objects.equals(contentType, other.contentType) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            faxSid,
                            contentType,
                            dateCreated,
                            dateUpdated,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("faxSid", faxSid)
                          .add("contentType", contentType)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .toString();
    }
}
