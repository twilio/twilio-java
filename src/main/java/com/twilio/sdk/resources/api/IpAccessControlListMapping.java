package com.twilio.sdk.resources.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.IpAccessControlListMappingCreator;
import com.twilio.sdk.deleters.api.IpAccessControlListMappingDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.IpAccessControlListMappingFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.IpAccessControlListMappingReader;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpAccessControlListMapping extends SidResource {
    private static final long serialVersionUID = 231948195815052L;

    /**
     * fetch
     * 
     * @param accountSid The account_sid
     * @param domainSid The domain_sid
     * @param sid The sid
     * @return IpAccessControlListMappingFetcher capable of executing the fetch
     */
    public static IpAccessControlListMappingFetcher fetch(final String accountSid, final String domainSid, final String sid) {
        return new IpAccessControlListMappingFetcher(accountSid, domainSid, sid);
    }

    /**
     * create
     * 
     * @param accountSid The account_sid
     * @param domainSid The domain_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @return IpAccessControlListMappingCreator capable of executing the create
     */
    public static IpAccessControlListMappingCreator create(final String accountSid, final String domainSid, final String ipAccessControlListSid) {
        return new IpAccessControlListMappingCreator(accountSid, domainSid, ipAccessControlListSid);
    }

    /**
     * read
     * 
     * @param accountSid The account_sid
     * @param domainSid The domain_sid
     * @return IpAccessControlListMappingReader capable of executing the read
     */
    public static IpAccessControlListMappingReader read(final String accountSid, final String domainSid) {
        return new IpAccessControlListMappingReader(accountSid, domainSid);
    }

    /**
     * delete
     * 
     * @param accountSid The account_sid
     * @param domainSid The domain_sid
     * @param sid The sid
     * @return IpAccessControlListMappingDeleter capable of executing the delete
     */
    public static IpAccessControlListMappingDeleter delete(final String accountSid, final String domainSid, final String sid) {
        return new IpAccessControlListMappingDeleter(accountSid, domainSid, sid);
    }

    /**
     * Converts a JSON String into a IpAccessControlListMapping object using the
     * provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return IpAccessControlListMapping object represented by the provided JSON
     */
    public static IpAccessControlListMapping fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, IpAccessControlListMapping.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a IpAccessControlListMapping object using
     * the provided ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return IpAccessControlListMapping object represented by the provided JSON
     */
    public static IpAccessControlListMapping fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, IpAccessControlListMapping.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String sid;
    private final String uri;

    @JsonCreator
    private IpAccessControlListMapping(@JsonProperty("account_sid") final String accountSid, 
                                       @JsonProperty("date_created") final String dateCreated, 
                                       @JsonProperty("date_updated") final String dateUpdated, 
                                       @JsonProperty("friendly_name") final String friendlyName, 
                                       @JsonProperty("sid") final String sid, 
                                       @JsonProperty("uri") final String uri) {
        this.accountSid = accountSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.sid = sid;
        this.uri = uri;
    }

    /**
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
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
     * @return The friendly_name
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The uri
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
        
        IpAccessControlListMapping other = (IpAccessControlListMapping) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            sid,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("sid", sid)
                          .add("uri", uri)
                          .toString();
    }
}