package com.twilio.sdk.resources.api.v2010.account.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.deleters.api.v2010.account.message.MediaDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.message.MediaFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.message.MediaReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media extends SidResource {
    private static final long serialVersionUID = 138990472819672L;

    /**
     * Delete media from your account. Once delete, you will no longer be billed
     * 
     * @param accountSid The account_sid
     * @param messageSid The message_sid
     * @param sid Delete by unique media Sid
     * @return MediaDeleter capable of executing the delete
     */
    public static MediaDeleter delete(final String accountSid, final String messageSid, final String sid) {
        return new MediaDeleter(accountSid, messageSid, sid);
    }

    /**
     * Fetch a single media instance belonging to the account used to make the
     * request
     * 
     * @param accountSid The account_sid
     * @param messageSid The message_sid
     * @param sid Fetch by unique media Sid
     * @return MediaFetcher capable of executing the fetch
     */
    public static MediaFetcher fetch(final String accountSid, final String messageSid, final String sid) {
        return new MediaFetcher(accountSid, messageSid, sid);
    }

    /**
     * Retrieve a list of medias belonging to the account used to make the request
     * 
     * @param accountSid The account_sid
     * @param messageSid The message_sid
     * @return MediaReader capable of executing the read
     */
    public static MediaReader read(final String accountSid, final String messageSid) {
        return new MediaReader(accountSid, messageSid);
    }

    /**
     * Converts a JSON String into a Media object using the provided ObjectMapper
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
     * ObjectMapper
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
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
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
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.parentSid = parentSid;
        this.sid = sid;
        this.uri = uri;
    }

    /**
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The default mime-type of the media
     */
    public final String getContentType() {
        return this.contentType;
    }

    /**
     * @return The date this resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date this resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return The unique id of the resource that created the media.
     */
    public final String getParentSid() {
        return this.parentSid;
    }

    /**
     * @return A string that uniquely identifies this media
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The URI for this resource
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("contentType", contentType)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("parentSid", parentSid)
                          .add("sid", sid)
                          .add("uri", uri)
                          .toString();
    }
}