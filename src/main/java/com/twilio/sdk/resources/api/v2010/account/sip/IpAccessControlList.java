package com.twilio.sdk.resources.api.v2010.account.sip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.v2010.account.sip.IpAccessControlListCreator;
import com.twilio.sdk.deleters.api.v2010.account.sip.IpAccessControlListDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.sip.IpAccessControlListFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.sip.IpAccessControlListReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.account.sip.IpAccessControlListUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpAccessControlList extends SidResource {
    private static final long serialVersionUID = 214188792953524L;

    /**
     * Retrieve a list of ip-access-control-lists belonging to the account used to
     * make the request
     * 
     * @param accountSid The account_sid
     * @return IpAccessControlListReader capable of executing the read
     */
    public static IpAccessControlListReader read(final String accountSid) {
        return new IpAccessControlListReader(accountSid);
    }

    /**
     * Create a new IpAccessControlList resource
     * 
     * @param accountSid The account_sid
     * @param friendlyName A human readable description of this resource
     * @return IpAccessControlListCreator capable of executing the create
     */
    public static IpAccessControlListCreator create(final String accountSid, final String friendlyName) {
        return new IpAccessControlListCreator(accountSid, friendlyName);
    }

    /**
     * Fetch a specific instance of an IpAccessControlList
     * 
     * @param accountSid The account_sid
     * @param sid Fetch by unique ip-access-control-list Sid
     * @return IpAccessControlListFetcher capable of executing the fetch
     */
    public static IpAccessControlListFetcher fetch(final String accountSid, final String sid) {
        return new IpAccessControlListFetcher(accountSid, sid);
    }

    /**
     * Rename an IpAccessControlList
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @param friendlyName A human readable description of this resource
     * @return IpAccessControlListUpdater capable of executing the update
     */
    public static IpAccessControlListUpdater update(final String accountSid, final String sid, final String friendlyName) {
        return new IpAccessControlListUpdater(accountSid, sid, friendlyName);
    }

    /**
     * Delete an IpAccessControlList from the requested account
     * 
     * @param accountSid The account_sid
     * @param sid Delete by unique ip-access-control-list Sid
     * @return IpAccessControlListDeleter capable of executing the delete
     */
    public static IpAccessControlListDeleter delete(final String accountSid, final String sid) {
        return new IpAccessControlListDeleter(accountSid, sid);
    }

    /**
     * Converts a JSON String into a IpAccessControlList object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return IpAccessControlList object represented by the provided JSON
     */
    public static IpAccessControlList fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, IpAccessControlList.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a IpAccessControlList object using the
     * provided ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return IpAccessControlList object represented by the provided JSON
     */
    public static IpAccessControlList fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, IpAccessControlList.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String friendlyName;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final Map<String, String> subresourceUris;
    private final String uri;

    @JsonCreator
    private IpAccessControlList(@JsonProperty("sid")
                                final String sid, 
                                @JsonProperty("account_sid")
                                final String accountSid, 
                                @JsonProperty("friendly_name")
                                final String friendlyName, 
                                @JsonProperty("date_created")
                                final String dateCreated, 
                                @JsonProperty("date_updated")
                                final String dateUpdated, 
                                @JsonProperty("subresource_uris")
                                final Map<String, String> subresourceUris, 
                                @JsonProperty("uri")
                                final String uri) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.subresourceUris = subresourceUris;
        this.uri = uri;
    }

    /**
     * @return A string that uniquely identifies this resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return A human readable description of this resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
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
     * @return The subresource_uris
     */
    public final Map<String, String> getSubresourceUris() {
        return this.subresourceUris;
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
        
        IpAccessControlList other = (IpAccessControlList) o;
        
        return Objects.equals(sid, other.sid) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(subresourceUris, other.subresourceUris) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            friendlyName,
                            dateCreated,
                            dateUpdated,
                            subresourceUris,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("friendlyName", friendlyName)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("subresourceUris", subresourceUris)
                          .add("uri", uri)
                          .toString();
    }
}