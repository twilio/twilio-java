/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account.sip;

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
import java.time.ZonedDateTime;

import java.util.Map;
import java.util.Objects;


import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CredentialList extends Resource {
    private static final long serialVersionUID = 178395545365846L;

    public static CredentialListCreator creator(final String friendlyName){
        return new CredentialListCreator(friendlyName);
    }
    public static CredentialListCreator creator(final String accountSid, final String friendlyName){
        return new CredentialListCreator(accountSid, friendlyName);
    }

    public static CredentialListFetcher fetcher(final String sid){
        return new CredentialListFetcher(sid);
    }
    public static CredentialListFetcher fetcher(final String accountSid, final String sid){
        return new CredentialListFetcher(accountSid, sid);
    }

    public static CredentialListDeleter deleter(final String sid){
        return new CredentialListDeleter(sid);
    }
    public static CredentialListDeleter deleter(final String accountSid, final String sid){
        return new CredentialListDeleter(accountSid, sid);
    }

    public static CredentialListReader reader(){
        return new CredentialListReader();
    }
    public static CredentialListReader reader(final String accountSid){
        return new CredentialListReader(accountSid);
    }

    public static CredentialListUpdater updater(final String sid, final String friendlyName){
        return new CredentialListUpdater(sid, friendlyName);
    }
    public static CredentialListUpdater updater(final String accountSid, final String sid, final String friendlyName){
        return new CredentialListUpdater(accountSid, sid, friendlyName);
    }

    /**
    * Converts a JSON String into a CredentialList object using the provided ObjectMapper.
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
    * ObjectMapper.
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
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String friendlyName;
    private final String sid;
    private final Map<String, String> subresourceUris;
    private final String uri;

    @JsonCreator
    private CredentialList(
        @JsonProperty("account_sid")
        final String accountSid,

        @JsonProperty("date_created")
        final String dateCreated,

        @JsonProperty("date_updated")
        final String dateUpdated,

        @JsonProperty("friendly_name")
        final String friendlyName,

        @JsonProperty("sid")
        final String sid,

        @JsonProperty("subresource_uris")
        final Map<String, String> subresourceUris,

        @JsonProperty("uri")
        final String uri
    ) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.sid = sid;
        this.subresourceUris = subresourceUris;
        this.uri = uri;
    }

        public final String getAccountSid() {
            return this.accountSid;
        }
        public final ZonedDateTime getDateCreated() {
            return this.dateCreated;
        }
        public final ZonedDateTime getDateUpdated() {
            return this.dateUpdated;
        }
        public final String getFriendlyName() {
            return this.friendlyName;
        }
        public final String getSid() {
            return this.sid;
        }
        public final Map<String, String> getSubresourceUris() {
            return this.subresourceUris;
        }
        public final String getUri() {
            return this.uri;
        }

    @Override
    public boolean equals(final Object o) {
        if (this==o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CredentialList other = (CredentialList) o;

        return Objects.equals(accountSid, other.accountSid) &&  Objects.equals(dateCreated, other.dateCreated) &&  Objects.equals(dateUpdated, other.dateUpdated) &&  Objects.equals(friendlyName, other.friendlyName) &&  Objects.equals(sid, other.sid) &&  Objects.equals(subresourceUris, other.subresourceUris) &&  Objects.equals(uri, other.uri)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid, dateCreated, dateUpdated, friendlyName, sid, subresourceUris, uri);
    }

}

