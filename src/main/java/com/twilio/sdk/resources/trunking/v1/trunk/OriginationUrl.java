package com.twilio.sdk.resources.trunking.v1.trunk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.trunking.v1.trunk.OriginationUrlCreator;
import com.twilio.sdk.deleters.trunking.v1.trunk.OriginationUrlDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.trunking.v1.trunk.OriginationUrlFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.trunking.v1.trunk.OriginationUrlReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.trunking.v1.trunk.OriginationUrlUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginationUrl extends SidResource {
    private static final long serialVersionUID = 147762448771620L;

    /**
     * Create a OriginationUrlFetcher to execute fetch.
     * 
     * @param trunkSid The trunk_sid
     * @param sid The sid
     * @return OriginationUrlFetcher capable of executing the fetch
     */
    public static OriginationUrlFetcher fetch(final String trunkSid, 
                                              final String sid) {
        return new OriginationUrlFetcher(trunkSid, sid);
    }

    /**
     * Create a OriginationUrlDeleter to execute delete.
     * 
     * @param trunkSid The trunk_sid
     * @param sid The sid
     * @return OriginationUrlDeleter capable of executing the delete
     */
    public static OriginationUrlDeleter delete(final String trunkSid, 
                                               final String sid) {
        return new OriginationUrlDeleter(trunkSid, sid);
    }

    /**
     * Create a OriginationUrlCreator to execute create.
     * 
     * @param trunkSid The trunk_sid
     * @param weight The weight
     * @param priority The priority
     * @param enabled The enabled
     * @param friendlyName The friendly_name
     * @param sipUrl The sip_url
     * @return OriginationUrlCreator capable of executing the create
     */
    public static OriginationUrlCreator create(final String trunkSid, 
                                               final Integer weight, 
                                               final Integer priority, 
                                               final Boolean enabled, 
                                               final String friendlyName, 
                                               final URI sipUrl) {
        return new OriginationUrlCreator(trunkSid, weight, priority, enabled, friendlyName, sipUrl);
    }

    /**
     * Create a OriginationUrlReader to execute read.
     * 
     * @param trunkSid The trunk_sid
     * @return OriginationUrlReader capable of executing the read
     */
    public static OriginationUrlReader read(final String trunkSid) {
        return new OriginationUrlReader(trunkSid);
    }

    /**
     * Create a OriginationUrlUpdater to execute update.
     * 
     * @param trunkSid The trunk_sid
     * @param sid The sid
     * @return OriginationUrlUpdater capable of executing the update
     */
    public static OriginationUrlUpdater update(final String trunkSid, 
                                               final String sid) {
        return new OriginationUrlUpdater(trunkSid, sid);
    }

    /**
     * Converts a JSON String into a OriginationUrl object using the provided
     * ObjectMapper.
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
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private OriginationUrl(@JsonProperty("account_sid")
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
                           final URI url) {
        this.accountSid = accountSid;
        this.sid = sid;
        this.trunkSid = trunkSid;
        this.weight = weight;
        this.enabled = enabled;
        this.sipUrl = sipUrl;
        this.friendlyName = friendlyName;
        this.priority = priority;
        this.dateCreated = MarshalConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
    }

    /**
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The sid.
     * 
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The trunk_sid.
     * 
     * @return The trunk_sid
     */
    public final String getTrunkSid() {
        return this.trunkSid;
    }

    /**
     * Returns The The weight.
     * 
     * @return The weight
     */
    public final Integer getWeight() {
        return this.weight;
    }

    /**
     * Returns The The enabled.
     * 
     * @return The enabled
     */
    public final Boolean getEnabled() {
        return this.enabled;
    }

    /**
     * Returns The The sip_url.
     * 
     * @return The sip_url
     */
    public final URI getSipUrl() {
        return this.sipUrl;
    }

    /**
     * Returns The The friendly_name.
     * 
     * @return The friendly_name
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The The priority.
     * 
     * @return The priority
     */
    public final Integer getPriority() {
        return this.priority;
    }

    /**
     * Returns The The date_created.
     * 
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The date_updated.
     * 
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The url.
     * 
     * @return The url
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
        
        OriginationUrl other = (OriginationUrl) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(trunkSid, other.trunkSid) && 
               Objects.equals(weight, other.weight) && 
               Objects.equals(enabled, other.enabled) && 
               Objects.equals(sipUrl, other.sipUrl) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(priority, other.priority) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            sid,
                            trunkSid,
                            weight,
                            enabled,
                            sipUrl,
                            friendlyName,
                            priority,
                            dateCreated,
                            dateUpdated,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("sid", sid)
                          .add("trunkSid", trunkSid)
                          .add("weight", weight)
                          .add("enabled", enabled)
                          .add("sipUrl", sipUrl)
                          .add("friendlyName", friendlyName)
                          .add("priority", priority)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .toString();
    }
}