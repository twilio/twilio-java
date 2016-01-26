package com.twilio.sdk.resources.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.deleters.api.v2010.account.OutgoingCallerIdDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.OutgoingCallerIdFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.numbers.PhoneNumber;
import com.twilio.sdk.readers.api.v2010.account.OutgoingCallerIdReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.account.OutgoingCallerIdUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutgoingCallerId extends SidResource {
    private static final long serialVersionUID = 160246512577628L;

    /**
     * Fetch an outgoing-caller-id belonging to the account used to make the request
     * 
     * @param accountSid The account_sid
     * @param sid Fetch by unique outgoing-caller-id Sid
     * @return OutgoingCallerIdFetcher capable of executing the fetch
     */
    public static OutgoingCallerIdFetcher fetch(final String accountSid, final String sid) {
        return new OutgoingCallerIdFetcher(accountSid, sid);
    }

    /**
     * Updates the caller-id
     * 
     * @param accountSid The account_sid
     * @param sid Update by unique outgoing-caller-id Sid
     * @return OutgoingCallerIdUpdater capable of executing the update
     */
    public static OutgoingCallerIdUpdater update(final String accountSid, final String sid) {
        return new OutgoingCallerIdUpdater(accountSid, sid);
    }

    /**
     * Delete the caller-id specified from the account
     * 
     * @param accountSid The account_sid
     * @param sid Delete by unique outgoing-caller-id Sid
     * @return OutgoingCallerIdDeleter capable of executing the delete
     */
    public static OutgoingCallerIdDeleter delete(final String accountSid, final String sid) {
        return new OutgoingCallerIdDeleter(accountSid, sid);
    }

    /**
     * Retrieve a list of outgoing-caller-ids belonging to the account used to make
     * the request
     * 
     * @param accountSid The account_sid
     * @return OutgoingCallerIdReader capable of executing the read
     */
    public static OutgoingCallerIdReader read(final String accountSid) {
        return new OutgoingCallerIdReader(accountSid);
    }

    /**
     * Converts a JSON String into a OutgoingCallerId object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return OutgoingCallerId object represented by the provided JSON
     */
    public static OutgoingCallerId fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, OutgoingCallerId.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a OutgoingCallerId object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return OutgoingCallerId object represented by the provided JSON
     */
    public static OutgoingCallerId fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, OutgoingCallerId.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String accountSid;
    private final PhoneNumber phoneNumber;
    private final String uri;

    @JsonCreator
    private OutgoingCallerId(@JsonProperty("sid") final String sid, 
                             @JsonProperty("date_created") final String dateCreated, 
                             @JsonProperty("date_updated") final String dateUpdated, 
                             @JsonProperty("friendly_name") final String friendlyName, 
                             @JsonProperty("account_sid") final String accountSid, 
                             @JsonProperty("phone_number") final PhoneNumber phoneNumber, 
                             @JsonProperty("uri") final String uri) {
        this.sid = sid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.accountSid = accountSid;
        this.phoneNumber = phoneNumber;
        this.uri = uri;
    }

    /**
     * @return A string that uniquely identifies this outgoing-caller-ids
     */
    public final String getSid() {
        return this.sid;
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
     * @return A human readable description for this resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The incoming phone number
     */
    public final PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
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
        
        OutgoingCallerId other = (OutgoingCallerId) o;
        
        return Objects.equals(sid, other.sid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(phoneNumber, other.phoneNumber) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            accountSid,
                            phoneNumber,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("accountSid", accountSid)
                          .add("phoneNumber", phoneNumber)
                          .add("uri", uri)
                          .toString();
    }
}