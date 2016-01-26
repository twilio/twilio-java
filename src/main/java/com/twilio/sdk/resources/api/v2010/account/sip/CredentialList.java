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
import com.twilio.sdk.creators.api.v2010.account.sip.CredentialListCreator;
import com.twilio.sdk.deleters.api.v2010.account.sip.CredentialListDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.sip.CredentialListFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.sip.CredentialListReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.account.sip.CredentialListUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CredentialList extends SidResource {
    private static final long serialVersionUID = 214188792953524L;

    /**
     * Retrieve a list of Credentials belonging to the account used to make the
     * request
     * 
     * @param accountSid The account_sid
     * @return CredentialListReader capable of executing the read
     */
    public static CredentialListReader read(final String accountSid) {
        return new CredentialListReader(accountSid);
    }

    /**
     * Add a Credential to the list
     * 
     * @param accountSid The account_sid
     * @param friendlyName The friendly_name
     * @return CredentialListCreator capable of executing the create
     */
    public static CredentialListCreator create(final String accountSid, final String friendlyName) {
        return new CredentialListCreator(accountSid, friendlyName);
    }

    /**
     * Retrieve a specific Credential in a list
     * 
     * @param accountSid The account_sid
     * @param sid Fetch by unique credential Sid
     * @return CredentialListFetcher capable of executing the fetch
     */
    public static CredentialListFetcher fetch(final String accountSid, final String sid) {
        return new CredentialListFetcher(accountSid, sid);
    }

    /**
     * Change the password of a Credential record
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @param friendlyName The friendly_name
     * @return CredentialListUpdater capable of executing the update
     */
    public static CredentialListUpdater update(final String accountSid, final String sid, final String friendlyName) {
        return new CredentialListUpdater(accountSid, sid, friendlyName);
    }

    /**
     * Remove a credential from a CredentialList
     * 
     * @param accountSid The account_sid
     * @param sid Delete by unique credential Sid
     * @return CredentialListDeleter capable of executing the delete
     */
    public static CredentialListDeleter delete(final String accountSid, final String sid) {
        return new CredentialListDeleter(accountSid, sid);
    }

    /**
     * Converts a JSON String into a CredentialList object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return CredentialList object represented by the provided JSON
     */
    public static CredentialList fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CredentialList.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a CredentialList object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return CredentialList object represented by the provided JSON
     */
    public static CredentialList fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CredentialList.class);
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
    private final Map<String, String> subresourceUris;
    private final String uri;

    @JsonCreator
    private CredentialList(@JsonProperty("account_sid") final String accountSid, 
                           @JsonProperty("date_created") final String dateCreated, 
                           @JsonProperty("date_updated") final String dateUpdated, 
                           @JsonProperty("friendly_name") final String friendlyName, 
                           @JsonProperty("sid") final String sid, 
                           @JsonProperty("subresource_uris") final Map<String, String> subresourceUris, 
                           @JsonProperty("uri") final String uri) {
        this.accountSid = accountSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.sid = sid;
        this.subresourceUris = subresourceUris;
        this.uri = uri;
    }

    /**
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
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
     * @return The friendly_name
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return A string that uniquely identifies this credential
     */
    public final String getSid() {
        return this.sid;
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
        
        CredentialList other = (CredentialList) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(subresourceUris, other.subresourceUris) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            sid,
                            subresourceUris,
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
                          .add("subresourceUris", subresourceUris)
                          .add("uri", uri)
                          .toString();
    }
}