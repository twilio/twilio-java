package com.twilio.sdk.resources.trunking.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.trunking.v1.TrunkCreator;
import com.twilio.sdk.deleters.trunking.v1.TrunkDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.trunking.v1.TrunkFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.trunking.v1.TrunkReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.trunking.v1.TrunkUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trunk extends SidResource {
    private static final long serialVersionUID = 276011649758607L;

    /**
     * fetch
     * 
     * @param sid The sid
     * @return TrunkFetcher capable of executing the fetch
     */
    public static TrunkFetcher fetch(final String sid) {
        return new TrunkFetcher(sid);
    }

    /**
     * delete
     * 
     * @param sid The sid
     * @return TrunkDeleter capable of executing the delete
     */
    public static TrunkDeleter delete(final String sid) {
        return new TrunkDeleter(sid);
    }

    /**
     * create
     * 
     * @return TrunkCreator capable of executing the create
     */
    public static TrunkCreator create() {
        return new TrunkCreator();
    }

    /**
     * read
     * 
     * @return TrunkReader capable of executing the read
     */
    public static TrunkReader read() {
        return new TrunkReader();
    }

    /**
     * update
     * 
     * @param sid The sid
     * @return TrunkUpdater capable of executing the update
     */
    public static TrunkUpdater update(final String sid) {
        return new TrunkUpdater(sid);
    }

    /**
     * Converts a JSON String into a Trunk object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Trunk object represented by the provided JSON
     */
    public static Trunk fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Trunk.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Trunk object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Trunk object represented by the provided JSON
     */
    public static Trunk fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Trunk.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String domainName;
    private final HttpMethod disasterRecoveryMethod;
    private final URI disasterRecoveryUrl;
    private final String friendlyName;
    private final Boolean secure;
    private final JsonNode recording;
    private final String authType;
    private final List<String> authTypeSet;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String sid;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Trunk(@JsonProperty("account_sid")
                  final String accountSid, 
                  @JsonProperty("domain_name")
                  final String domainName, 
                  @JsonProperty("disaster_recovery_method")
                  final HttpMethod disasterRecoveryMethod, 
                  @JsonProperty("disaster_recovery_url")
                  final URI disasterRecoveryUrl, 
                  @JsonProperty("friendly_name")
                  final String friendlyName, 
                  @JsonProperty("secure")
                  final Boolean secure, 
                  @JsonProperty("recording")
                  final JsonNode recording, 
                  @JsonProperty("auth_type")
                  final String authType, 
                  @JsonProperty("auth_type_set")
                  final List<String> authTypeSet, 
                  @JsonProperty("date_created")
                  final String dateCreated, 
                  @JsonProperty("date_updated")
                  final String dateUpdated, 
                  @JsonProperty("sid")
                  final String sid, 
                  @JsonProperty("url")
                  final URI url, 
                  @JsonProperty("links")
                  final Map<String, String> links) {
        this.accountSid = accountSid;
        this.domainName = domainName;
        this.disasterRecoveryMethod = disasterRecoveryMethod;
        this.disasterRecoveryUrl = disasterRecoveryUrl;
        this.friendlyName = friendlyName;
        this.secure = secure;
        this.recording = recording;
        this.authType = authType;
        this.authTypeSet = authTypeSet;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.sid = sid;
        this.url = url;
        this.links = links;
    }

    /**
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The domain_name
     */
    public final String getDomainName() {
        return this.domainName;
    }

    /**
     * @return The disaster_recovery_method
     */
    public final HttpMethod getDisasterRecoveryMethod() {
        return this.disasterRecoveryMethod;
    }

    /**
     * @return The disaster_recovery_url
     */
    public final URI getDisasterRecoveryUrl() {
        return this.disasterRecoveryUrl;
    }

    /**
     * @return The friendly_name
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return The secure
     */
    public final Boolean getSecure() {
        return this.secure;
    }

    /**
     * @return The recording
     */
    public final JsonNode getRecording() {
        return this.recording;
    }

    /**
     * @return The auth_type
     */
    public final String getAuthType() {
        return this.authType;
    }

    /**
     * @return The auth_type_set
     */
    public final List<String> getAuthTypeSet() {
        return this.authTypeSet;
    }

    /**
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
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
        
        Trunk other = (Trunk) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(domainName, other.domainName) && 
               Objects.equals(disasterRecoveryMethod, other.disasterRecoveryMethod) && 
               Objects.equals(disasterRecoveryUrl, other.disasterRecoveryUrl) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(secure, other.secure) && 
               Objects.equals(recording, other.recording) && 
               Objects.equals(authType, other.authType) && 
               Objects.equals(authTypeSet, other.authTypeSet) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(url, other.url) && 
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            domainName,
                            disasterRecoveryMethod,
                            disasterRecoveryUrl,
                            friendlyName,
                            secure,
                            recording,
                            authType,
                            authTypeSet,
                            dateCreated,
                            dateUpdated,
                            sid,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("domainName", domainName)
                          .add("disasterRecoveryMethod", disasterRecoveryMethod)
                          .add("disasterRecoveryUrl", disasterRecoveryUrl)
                          .add("friendlyName", friendlyName)
                          .add("secure", secure)
                          .add("recording", recording)
                          .add("authType", authType)
                          .add("authTypeSet", authTypeSet)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("sid", sid)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}