package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.deleters.MediaDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.MediaFetcher;
import com.twilio.sdk.readers.MediaReader;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String parentSid;
    private final DateTime dateUpdated;
    private final String uri;
    private final String accountSid;
    private final String contentType;
    private final String sid;
    private final DateTime dateCreated;
    
    @JsonCreator
    private Media(
        @JsonProperty("parent_sid") final String parentSid,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("uri") final String uri,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("content_type") final String contentType,
        @JsonProperty("sid") final String sid,
        @JsonProperty("date_created") final String dateCreated
        ) {
        this.parentSid = parentSid;
            this.dateUpdated = safeDateTimeConvert(dateUpdated);
            this.uri = uri;
            this.accountSid = accountSid;
            this.contentType = contentType;
            this.sid = sid;
            this.dateCreated = safeDateTimeConvert(dateCreated);
            
    }

    public static MediaDeleter delete(final String messageSid, final String sid) {
        return new MediaDeleter(messageSid, sid);
    }

    public static MediaDeleter delete(final Message targetMessage, final String sid) {
        return new MediaDeleter(targetMessage, sid);
    }

    public static MediaDeleter delete(final Media target) {
        return new MediaDeleter(target);
    }

    public static MediaFetcher fetch(final String messageSid, final String sid) {
        return new MediaFetcher(messageSid, sid);
    }

    public static MediaFetcher fetch(final Message targetMessage, final String sid) {
        return new MediaFetcher(targetMessage, sid);
    }

    public static MediaReader list(final String messageSid) {
        return new MediaReader(messageSid);
    }

    public static MediaReader list(final Message target) {
        return new MediaReader(target);
    }

    public static Media fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Media.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Media fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Media.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final String getParentSid() {
        return parentSid;
    }
    public final DateTime getDateUpdated() {
        return dateUpdated;
    }
    public final String getUri() {
        return uri;
    }
    public final String getAccountSid() {
        return accountSid;
    }
    public final String getContentType() {
        return contentType;
    }
    public final String getSid() {
        return sid;
    }
    public final DateTime getDateCreated() {
        return dateCreated;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Media self = (Media) o;

        return (
            Objects.equals(parentSid, self.parentSid) &&
            Objects.equals(dateUpdated, self.dateUpdated) &&
            Objects.equals(uri, self.uri) &&
            Objects.equals(accountSid, self.accountSid) &&
            Objects.equals(contentType, self.contentType) &&
            Objects.equals(sid, self.sid) &&
            Objects.equals(dateCreated, self.dateCreated) 
            );
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(
                parentSid,
                dateUpdated,
                uri,
                accountSid,
                contentType,
                sid,
                dateCreated
                );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("parentSid", parentSid)
                        .add("dateUpdated", dateUpdated)
                        .add("uri", uri)
                        .add("accountSid", accountSid)
                        .add("contentType", contentType)
                        .add("sid", sid)
                        .add("dateCreated", dateCreated)
                        .toString();
    }
}