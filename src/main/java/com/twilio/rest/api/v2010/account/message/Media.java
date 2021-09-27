/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media extends Resource {
    private static final long serialVersionUID = 138990472819672L;

    /**
     * Create a MediaDeleter to execute delete.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       delete
     * @param pathMessageSid The SID of the Message resource that this Media
     *                       resource belongs to
     * @param pathSid The unique string that identifies this resource
     * @return MediaDeleter capable of executing the delete
     */
    public static MediaDeleter deleter(final String pathAccountSid,
                                       final String pathMessageSid,
                                       final String pathSid) {
        return new MediaDeleter(pathAccountSid, pathMessageSid, pathSid);
    }

    /**
     * Create a MediaDeleter to execute delete.
     *
     * @param pathMessageSid The SID of the Message resource that this Media
     *                       resource belongs to
     * @param pathSid The unique string that identifies this resource
     * @return MediaDeleter capable of executing the delete
     */
    public static MediaDeleter deleter(final String pathMessageSid,
                                       final String pathSid) {
        return new MediaDeleter(pathMessageSid, pathSid);
    }

    /**
     * Create a MediaFetcher to execute fetch.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       fetch
     * @param pathMessageSid The SID of the Message resource that this Media
     *                       resource belongs to
     * @param pathSid The unique string that identifies this resource
     * @return MediaFetcher capable of executing the fetch
     */
    public static MediaFetcher fetcher(final String pathAccountSid,
                                       final String pathMessageSid,
                                       final String pathSid) {
        return new MediaFetcher(pathAccountSid, pathMessageSid, pathSid);
    }

    /**
     * Create a MediaFetcher to execute fetch.
     *
     * @param pathMessageSid The SID of the Message resource that this Media
     *                       resource belongs to
     * @param pathSid The unique string that identifies this resource
     * @return MediaFetcher capable of executing the fetch
     */
    public static MediaFetcher fetcher(final String pathMessageSid,
                                       final String pathSid) {
        return new MediaFetcher(pathMessageSid, pathSid);
    }

    /**
     * Create a MediaReader to execute read.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       read
     * @param pathMessageSid The SID of the Message resource that this Media
     *                       resource belongs to
     * @return MediaReader capable of executing the read
     */
    public static MediaReader reader(final String pathAccountSid,
                                     final String pathMessageSid) {
        return new MediaReader(pathAccountSid, pathMessageSid);
    }

    /**
     * Create a MediaReader to execute read.
     *
     * @param pathMessageSid The SID of the Message resource that this Media
     *                       resource belongs to
     * @return MediaReader capable of executing the read
     */
    public static MediaReader reader(final String pathMessageSid) {
        return new MediaReader(pathMessageSid);
    }

    /**
     * Converts a JSON String into a Media object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Media object represented by the provided JSON
     */
    public static Media fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Media.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Media object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Media object represented by the provided JSON
     */
    public static Media fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Media.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String contentType;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String parentSid;
    private final String sid;
    private final String uri;

    @JsonCreator
    private Media(@JsonProperty("account_sid")
                  final String accountSid,
                  @JsonProperty("content_type")
                  final String contentType,
                  @JsonProperty("date_created")
                  final String dateCreated,
                  @JsonProperty("date_updated")
                  final String dateUpdated,
                  @JsonProperty("parent_sid")
                  final String parentSid,
                  @JsonProperty("sid")
                  final String sid,
                  @JsonProperty("uri")
                  final String uri) {
        this.accountSid = accountSid;
        this.contentType = contentType;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.parentSid = parentSid;
        this.sid = sid;
        this.uri = uri;
    }

    /**
     * Returns The SID of the Account that created this resource.
     *
     * @return The SID of the Account that created this resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The default mime-type of the media.
     *
     * @return The default mime-type of the media
     */
    public final String getContentType() {
        return this.contentType;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that this resource was created.
     *
     * @return The RFC 2822 date and time in GMT that this resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that this resource was last
     * updated.
     *
     * @return The RFC 2822 date and time in GMT that this resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The SID of the resource that created the media.
     *
     * @return The SID of the resource that created the media
     */
    public final String getParentSid() {
        return this.parentSid;
    }

    /**
     * Returns The unique string that identifies this resource.
     *
     * @return The unique string that identifies this resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The URI of this resource, relative to `https://api.twilio.com`.
     *
     * @return The URI of this resource, relative to `https://api.twilio.com`
     */
    public final String getUri() {
        return this.uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Media other = (Media) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(contentType, other.contentType) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(parentSid, other.parentSid) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            contentType,
                            dateCreated,
                            dateUpdated,
                            parentSid,
                            sid,
                            uri);
    }

  public String toString() {
    return "Media(accountSid=" + this.getAccountSid() + ", contentType=" + this.getContentType() + ", dateCreated=" + this.getDateCreated() + ", dateUpdated=" + this.getDateUpdated() + ", parentSid=" + this.getParentSid() + ", sid=" + this.getSid() + ", uri=" + this.getUri() + ")";
  }
}
