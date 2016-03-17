package com.twilio.sdk.resources.api.v2010.account.sip.ipaccesscontrollist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.v2010.account.sip.ipaccesscontrollist.IpAddressCreator;
import com.twilio.sdk.deleters.api.v2010.account.sip.ipaccesscontrollist.IpAddressDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.sip.ipaccesscontrollist.IpAddressFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.sip.ipaccesscontrollist.IpAddressReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.account.sip.ipaccesscontrollist.IpAddressUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpAddress extends SidResource {
    private static final long serialVersionUID = 118701358673463L;

    /**
     * Create a IpAddressReader to execute read.
     * 
     * @param accountSid The account_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @return IpAddressReader capable of executing the read
     */
    public static IpAddressReader read(final String accountSid, 
                                       final String ipAccessControlListSid) {
        return new IpAddressReader(accountSid, ipAccessControlListSid);
    }

    /**
     * Create a IpAddressCreator to execute create.
     * 
     * @param accountSid The account_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @param friendlyName The friendly_name
     * @param ipAddress The ip_address
     * @return IpAddressCreator capable of executing the create
     */
    public static IpAddressCreator create(final String accountSid, 
                                          final String ipAccessControlListSid, 
                                          final String friendlyName, 
                                          final String ipAddress) {
        return new IpAddressCreator(accountSid, ipAccessControlListSid, friendlyName, ipAddress);
    }

    /**
     * Create a IpAddressFetcher to execute fetch.
     * 
     * @param accountSid The account_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @param sid The sid
     * @return IpAddressFetcher capable of executing the fetch
     */
    public static IpAddressFetcher fetch(final String accountSid, 
                                         final String ipAccessControlListSid, 
                                         final String sid) {
        return new IpAddressFetcher(accountSid, ipAccessControlListSid, sid);
    }

    /**
     * Create a IpAddressUpdater to execute update.
     * 
     * @param accountSid The account_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @param sid The sid
     * @param ipAddress The ip_address
     * @param friendlyName The friendly_name
     * @return IpAddressUpdater capable of executing the update
     */
    public static IpAddressUpdater update(final String accountSid, 
                                          final String ipAccessControlListSid, 
                                          final String sid, 
                                          final String ipAddress, 
                                          final String friendlyName) {
        return new IpAddressUpdater(accountSid, ipAccessControlListSid, sid, ipAddress, friendlyName);
    }

    /**
     * Create a IpAddressDeleter to execute delete.
     * 
     * @param accountSid The account_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @param sid The sid
     * @return IpAddressDeleter capable of executing the delete
     */
    public static IpAddressDeleter delete(final String accountSid, 
                                          final String ipAccessControlListSid, 
                                          final String sid) {
        return new IpAddressDeleter(accountSid, ipAccessControlListSid, sid);
    }

    /**
     * Converts a JSON String into a IpAddress object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return IpAddress object represented by the provided JSON
     */
    public static IpAddress fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, IpAddress.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a IpAddress object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return IpAddress object represented by the provided JSON
     */
    public static IpAddress fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, IpAddress.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String friendlyName;
    private final String ipAddress;
    private final String ipAccessControlListSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String uri;

    @JsonCreator
    private IpAddress(@JsonProperty("sid")
                      final String sid, 
                      @JsonProperty("account_sid")
                      final String accountSid, 
                      @JsonProperty("friendly_name")
                      final String friendlyName, 
                      @JsonProperty("ip_address")
                      final String ipAddress, 
                      @JsonProperty("ip_access_control_list_sid")
                      final String ipAccessControlListSid, 
                      @JsonProperty("date_created")
                      final String dateCreated, 
                      @JsonProperty("date_updated")
                      final String dateUpdated, 
                      @JsonProperty("uri")
                      final String uri) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.ipAddress = ipAddress;
        this.ipAccessControlListSid = ipAccessControlListSid;
        this.dateCreated = MarshalConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.rfc2822DateTimeFromString(dateUpdated);
        this.uri = uri;
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
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
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
     * Returns The The ip_address.
     * 
     * @return The ip_address
     */
    public final String getIpAddress() {
        return this.ipAddress;
    }

    /**
     * Returns The The ip_access_control_list_sid.
     * 
     * @return The ip_access_control_list_sid
     */
    public final String getIpAccessControlListSid() {
        return this.ipAccessControlListSid;
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
     * Returns The The uri.
     * 
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
        
        IpAddress other = (IpAddress) o;
        
        return Objects.equals(sid, other.sid) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(ipAddress, other.ipAddress) && 
               Objects.equals(ipAccessControlListSid, other.ipAccessControlListSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            friendlyName,
                            ipAddress,
                            ipAccessControlListSid,
                            dateCreated,
                            dateUpdated,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("friendlyName", friendlyName)
                          .add("ipAddress", ipAddress)
                          .add("ipAccessControlListSid", ipAccessControlListSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("uri", uri)
                          .toString();
    }
}