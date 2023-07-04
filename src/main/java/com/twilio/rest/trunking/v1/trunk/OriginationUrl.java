/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Trunking
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.trunking.v1.trunk;

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

import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;

import java.util.Objects;

import lombok.ToString;


@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OriginationUrl extends Resource {
    private static final long serialVersionUID = 133388691973992L;

    

    public static OriginationUrlCreator creator(final String pathTrunkSid, final Integer weight, final Integer priority, final Boolean enabled, final String friendlyName, final URI sipUrl){
        return new OriginationUrlCreator(pathTrunkSid, weight, priority, enabled, friendlyName, sipUrl);
    }

    public static OriginationUrlDeleter deleter(final String pathTrunkSid, final String pathSid){
        return new OriginationUrlDeleter(pathTrunkSid, pathSid);
    }

    public static OriginationUrlFetcher fetcher(final String pathTrunkSid, final String pathSid){
        return new OriginationUrlFetcher(pathTrunkSid, pathSid);
    }

    public static OriginationUrlReader reader(final String pathTrunkSid){
        return new OriginationUrlReader(pathTrunkSid);
    }

    public static OriginationUrlUpdater updater(final String pathTrunkSid, final String pathSid){
        return new OriginationUrlUpdater(pathTrunkSid, pathSid);
    }

    /**
    * Converts a JSON String into a OriginationUrl object using the provided ObjectMapper.
    *
    * @param json Raw JSON String
    * @param objectMapper Jackson ObjectMapper
    * @return OriginationUrl object represented by the provided JSON
    */
    public static OriginationUrl fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, OriginationUrl.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
    * Converts a JSON InputStream into a OriginationUrl object using the provided
    * ObjectMapper.
    *
    * @param json Raw JSON InputStream
    * @param objectMapper Jackson ObjectMapper
    * @return OriginationUrl object represented by the provided JSON
    */
    public static OriginationUrl fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, OriginationUrl.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }


    private final String accountSid;
    private final String sid;
    private final String trunkSid;
    private final Integer weight;
    private final Boolean enabled;
    private final URI sipUrl;
    private final String friendlyName;
    private final Integer priority;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private OriginationUrl(
        @JsonProperty("account_sid")
        final String accountSid,

        @JsonProperty("sid")
        final String sid,

        @JsonProperty("trunk_sid")
        final String trunkSid,

        @JsonProperty("weight")
        final Integer weight,

        @JsonProperty("enabled")
        final Boolean enabled,

        @JsonProperty("sip_url")
        final URI sipUrl,

        @JsonProperty("friendly_name")
        final String friendlyName,

        @JsonProperty("priority")
        final Integer priority,

        @JsonProperty("date_created")
        final String dateCreated,

        @JsonProperty("date_updated")
        final String dateUpdated,

        @JsonProperty("url")
        final URI url
    ) {
        this.accountSid = accountSid;
        this.sid = sid;
        this.trunkSid = trunkSid;
        this.weight = weight;
        this.enabled = enabled;
        this.sipUrl = sipUrl;
        this.friendlyName = friendlyName;
        this.priority = priority;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
    }

        public final String getAccountSid() {
            return this.accountSid;
        }
        public final String getSid() {
            return this.sid;
        }
        public final String getTrunkSid() {
            return this.trunkSid;
        }
        public final Integer getWeight() {
            return this.weight;
        }
        public final Boolean getEnabled() {
            return this.enabled;
        }
        public final URI getSipUrl() {
            return this.sipUrl;
        }
        public final String getFriendlyName() {
            return this.friendlyName;
        }
        public final Integer getPriority() {
            return this.priority;
        }
        public final ZonedDateTime getDateCreated() {
            return this.dateCreated;
        }
        public final ZonedDateTime getDateUpdated() {
            return this.dateUpdated;
        }
        public final URI getUrl() {
            return this.url;
        }

    @Override
    public boolean equals(final Object o) {
        if (this==o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OriginationUrl other = (OriginationUrl) o;

        return Objects.equals(accountSid, other.accountSid) &&  Objects.equals(sid, other.sid) &&  Objects.equals(trunkSid, other.trunkSid) &&  Objects.equals(weight, other.weight) &&  Objects.equals(enabled, other.enabled) &&  Objects.equals(sipUrl, other.sipUrl) &&  Objects.equals(friendlyName, other.friendlyName) &&  Objects.equals(priority, other.priority) &&  Objects.equals(dateCreated, other.dateCreated) &&  Objects.equals(dateUpdated, other.dateUpdated) &&  Objects.equals(url, other.url)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid, sid, trunkSid, weight, enabled, sipUrl, friendlyName, priority, dateCreated, dateUpdated, url);
    }


}

