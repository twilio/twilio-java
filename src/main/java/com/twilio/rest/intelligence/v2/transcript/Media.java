/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Intelligence
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.intelligence.v2.transcript;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Media extends Resource {

    private static final long serialVersionUID = 207829674287272L;

    public static MediaFetcher fetcher(final String pathSid) {
        return new MediaFetcher(pathSid);
    }

    /**
     * Converts a JSON String into a Media object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Media object represented by the provided JSON
     */
    public static Media fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
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
    public static Media fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
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
    private final URI mediaUrl;
    private final String serviceSid;
    private final String sid;
    private final URI url;

    @JsonCreator
    private Media(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("media_url") final URI mediaUrl,
        @JsonProperty("service_sid") final String serviceSid,
        @JsonProperty("sid") final String sid,
        @JsonProperty("url") final URI url
    ) {
        this.accountSid = accountSid;
        this.mediaUrl = mediaUrl;
        this.serviceSid = serviceSid;
        this.sid = sid;
        this.url = url;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final URI getMediaUrl() {
        return this.mediaUrl;
    }

    public final String getServiceSid() {
        return this.serviceSid;
    }

    public final String getSid() {
        return this.sid;
    }

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

        Media other = (Media) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(mediaUrl, other.mediaUrl) &&
            Objects.equals(serviceSid, other.serviceSid) &&
            Objects.equals(sid, other.sid) &&
            Objects.equals(url, other.url)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid, mediaUrl, serviceSid, sid, url);
    }
}
