package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.SipIpAccessControlListCreator;
import com.twilio.sdk.deleters.SipIpAccessControlListDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.SipIpAccessControlListFetcher;
import com.twilio.sdk.readers.SipIpAccessControlListReader;
import com.twilio.sdk.updaters.SipIpAccessControlListUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

public class SipIpAccessControlList extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String sid;
    private final String accountSid;
    private final String friendlyName;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final Map<String, String> subresourceUris;
    private final String uri;

    @JsonCreator
    private SipIpAccessControlList(@JsonProperty("sid") final String sid,
                                   @JsonProperty("account_sid") final String accountSid,
                                   @JsonProperty("friendly_name") final String friendlyName,
                                   @JsonProperty("date_created") final String dateCreated,
                                   @JsonProperty("date_updated") final String dateUpdated,
                                   @JsonProperty("subresource_uris") final Map<String, String> subresourceUris,
                                   @JsonProperty("uri") final String uri) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.subresourceUris = subresourceUris;
        this.uri = uri;
    }
    
    public static SipIpAccessControlListCreator create(final String domainName) {
        return new SipIpAccessControlListCreator(domainName);
    }

    public static SipIpAccessControlListFetcher fetch(final String sid) {
        return new SipIpAccessControlListFetcher(sid);
    }

    public static SipIpAccessControlListReader list() {
        return new SipIpAccessControlListReader();
    }

    public static SipIpAccessControlListUpdater update(final SipIpAccessControlList target) {
        return new SipIpAccessControlListUpdater(target);
    }

    public static SipIpAccessControlListUpdater update(final String sid) {
        return new SipIpAccessControlListUpdater(sid);
    }

    public static SipIpAccessControlListDeleter delete(final SipIpAccessControlList target) {
        return new SipIpAccessControlListDeleter(target);
    }

    public static SipIpAccessControlListDeleter delete(final String sid) {
        return new SipIpAccessControlListDeleter(sid);
    }

    public String getSid() {
        return sid;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateUpdated() {
        return dateUpdated;
    }

    public Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    public String getUri() {
        return uri;
    }
    
    public static SipIpAccessControlList fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipIpAccessControlList.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static SipIpAccessControlList fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipIpAccessControlList.class);
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

        SipIpAccessControlList other = (SipIpAccessControlList) o;
        return (Objects.equals(sid, other.sid) &&
                Objects.equals(friendlyName, other.friendlyName) &&
                Objects.equals(accountSid, other.accountSid) &&
                Objects.equals(dateCreated, other.dateCreated) &&
                Objects.equals(dateUpdated, other.dateUpdated) &&
                Objects.equals(uri, other.uri));
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, friendlyName, accountSid, dateCreated, dateUpdated, uri);
    }
}
