package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.SipIpAddressCreator;
import com.twilio.sdk.deleters.SipIpAddressDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.SipIpAddressFetcher;
import com.twilio.sdk.readers.SipIpAddressReader;
import com.twilio.sdk.updaters.SipIpAddressUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SipIpAddress extends SidResource {
    private static final long serialVersionUID = -5732541214023360255L;

    private final String sid;
    private final String accountSid;
    private final String ipAccessControlListSid;
    private final String friendlyName;
    private final String ipAddress;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String uri;

    @JsonCreator
    public SipIpAddress(@JsonProperty("sid") final String sid,
                        @JsonProperty("account_sid") final String accountSid,
                        @JsonProperty("ip_access_control_list_sid") final String ipAccessControlListSid,
                        @JsonProperty("friendly_name") final String friendlyName,
                        @JsonProperty("ip_address") final String ipAddress,
                        @JsonProperty("date_created") final String dateCreated,
                        @JsonProperty("date_updated") final String dateUpdated,
                        @JsonProperty("uri") final String uri) {

        this.sid = sid;
        this.accountSid = accountSid;
        this.ipAccessControlListSid = ipAccessControlListSid;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.friendlyName = friendlyName;
        this.ipAddress = ipAddress;
        this.uri = uri;
    }

    public static SipIpAddressCreator create(final String IpAddressListSid, final String friendlyName, final String ipAddress) {
        return new SipIpAddressCreator(IpAddressListSid, friendlyName, ipAddress);
    }

    public static SipIpAddressCreator create(final SipIpAccessControlList target, final String friendlyName, final String ipAddress) {
        return new SipIpAddressCreator(target, friendlyName, ipAddress);
    }

    public static SipIpAddressReader list(final String IpAddressListSid) {
        return new SipIpAddressReader(IpAddressListSid);
    }

    public static SipIpAddressReader list(final SipIpAccessControlList target) {
        return new SipIpAddressReader(target);
    }

    public static SipIpAddressFetcher fetch(final String IpAddressListSid, final String sid) {
        return new SipIpAddressFetcher(IpAddressListSid, sid);
    }

    public static SipIpAddressFetcher fetch(final SipIpAccessControlList target, final String sid) {
        return new SipIpAddressFetcher(target, sid);
    }

    public static SipIpAddressUpdater update(final String ipAccessControlListSid, final String sid) {
        return new SipIpAddressUpdater(ipAccessControlListSid, sid);
    }

    public static SipIpAddressUpdater update(final SipIpAccessControlList targetIpAcl, final String sid) {
        return new SipIpAddressUpdater(targetIpAcl, sid);
    }

    public static SipIpAddressUpdater update(final SipIpAddress target) {
        return new SipIpAddressUpdater(target);
    }

    public static SipIpAddressDeleter delete(final String IpAddressListSid, final String sid) {
        return new SipIpAddressDeleter(IpAddressListSid, sid);
    }

    public static SipIpAddressDeleter delete(final SipIpAccessControlList targetIpAddressList, final String sid) {
        return new SipIpAddressDeleter(targetIpAddressList, sid);
    }

    public static SipIpAddressDeleter delete(final SipIpAddress target) {
        return new SipIpAddressDeleter(target);
    }

    public String getSid() {
        return sid;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getIpAccessControlListSid() {
        return ipAccessControlListSid;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateUpdated() {
        return dateUpdated;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUri() {
        return uri;
    }

    public static SipIpAddress fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipIpAddress.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static SipIpAddress fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipIpAddress.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SipIpAddress self = (SipIpAddress) o;

        return (
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(ipAddress, self.ipAddress) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(ipAccessControlListSid, self.ipAccessControlListSid) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                dateUpdated,
                ipAddress,
                friendlyName,
                uri,
                accountSid,
                ipAccessControlListSid,
                sid,
                dateCreated
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sid", sid)
                .add("accountSid", accountSid)
                .add("ipAccessControlListSid", ipAccessControlListSid)
                .add("ipAddress", ipAddress)
                .add("friendlyName", friendlyName)
                .add("dateCreated", dateCreated)
                .add("dateUpdated", dateUpdated)
                .add("uri", uri)
                .toString();
    }

}
