/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.proxy.v1.service.session;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.time.ZonedDateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant extends Resource {
    private static final long serialVersionUID = 233073623535354L;

    /**
     * Create a ParticipantFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the parent Service of the resource to fetch
     * @param pathSessionSid The SID of the parent Session of the resource to fetch
     * @param pathSid The unique string that identifies the resource
     * @return ParticipantFetcher capable of executing the fetch
     */
    public static ParticipantFetcher fetcher(final String pathServiceSid,
                                             final String pathSessionSid,
                                             final String pathSid) {
        return new ParticipantFetcher(pathServiceSid, pathSessionSid, pathSid);
    }

    /**
     * Create a ParticipantReader to execute read.
     *
     * @param pathServiceSid The SID of the parent Service of the resource to read
     * @param pathSessionSid The SID of the parent Session of the resource to read
     * @return ParticipantReader capable of executing the read
     */
    public static ParticipantReader reader(final String pathServiceSid,
                                           final String pathSessionSid) {
        return new ParticipantReader(pathServiceSid, pathSessionSid);
    }

    /**
     * Create a ParticipantCreator to execute create.
     *
     * @param pathServiceSid The SID of the parent Service resource
     * @param pathSessionSid The SID of the parent Session resource
     * @param identifier The phone number of the Participant
     * @return ParticipantCreator capable of executing the create
     */
    public static ParticipantCreator creator(final String pathServiceSid,
                                             final String pathSessionSid,
                                             final String identifier) {
        return new ParticipantCreator(pathServiceSid, pathSessionSid, identifier);
    }

    /**
     * Create a ParticipantDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the parent Service of the resource to delete
     * @param pathSessionSid The SID of the parent Session of the resource to delete
     * @param pathSid The unique string that identifies the resource
     * @return ParticipantDeleter capable of executing the delete
     */
    public static ParticipantDeleter deleter(final String pathServiceSid,
                                             final String pathSessionSid,
                                             final String pathSid) {
        return new ParticipantDeleter(pathServiceSid, pathSessionSid, pathSid);
    }

    /**
     * Converts a JSON String into a Participant object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Participant object represented by the provided JSON
     */
    public static Participant fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Participant object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Participant object represented by the provided JSON
     */
    public static Participant fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String sessionSid;
    private final String serviceSid;
    private final String accountSid;
    private final String friendlyName;
    private final String identifier;
    private final String proxyIdentifier;
    private final String proxyIdentifierSid;
    private final ZonedDateTime dateDeleted;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Participant(@JsonProperty("sid")
                        final String sid,
                        @JsonProperty("session_sid")
                        final String sessionSid,
                        @JsonProperty("service_sid")
                        final String serviceSid,
                        @JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("friendly_name")
                        final String friendlyName,
                        @JsonProperty("identifier")
                        final String identifier,
                        @JsonProperty("proxy_identifier")
                        final String proxyIdentifier,
                        @JsonProperty("proxy_identifier_sid")
                        final String proxyIdentifierSid,
                        @JsonProperty("date_deleted")
                        final String dateDeleted,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated,
                        @JsonProperty("url")
                        final URI url,
                        @JsonProperty("links")
                        final Map<String, String> links) {
        this.sid = sid;
        this.sessionSid = sessionSid;
        this.serviceSid = serviceSid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.identifier = identifier;
        this.proxyIdentifier = proxyIdentifier;
        this.proxyIdentifierSid = proxyIdentifierSid;
        this.dateDeleted = DateConverter.iso8601DateTimeFromString(dateDeleted);
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The SID of the resource's parent Session.
     *
     * @return The SID of the resource's parent Session
     */
    public final String getSessionSid() {
        return this.sessionSid;
    }

    /**
     * Returns The The SID of the resource's parent Service.
     *
     * @return The SID of the resource's parent Service
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The string that you assigned to describe the participant.
     *
     * @return The string that you assigned to describe the participant
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The The phone number of the Participant.
     *
     * @return The phone number of the Participant
     */
    public final String getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns The The phone number or short code of the participant's partner.
     *
     * @return The phone number or short code of the participant's partner
     */
    public final String getProxyIdentifier() {
        return this.proxyIdentifier;
    }

    /**
     * Returns The The SID of the Proxy Identifier assigned to the Participant.
     *
     * @return The SID of the Proxy Identifier assigned to the Participant
     */
    public final String getProxyIdentifierSid() {
        return this.proxyIdentifierSid;
    }

    /**
     * Returns The The ISO 8601 date the Participant was removed.
     *
     * @return The ISO 8601 date the Participant was removed
     */
    public final ZonedDateTime getDateDeleted() {
        return this.dateDeleted;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the resource was last
     * updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The absolute URL of the Participant resource.
     *
     * @return The absolute URL of the Participant resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The URLs to resources related the participant.
     *
     * @return The URLs to resources related the participant
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

        Participant other = (Participant) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(sessionSid, other.sessionSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(identifier, other.identifier) &&
               Objects.equals(proxyIdentifier, other.proxyIdentifier) &&
               Objects.equals(proxyIdentifierSid, other.proxyIdentifierSid) &&
               Objects.equals(dateDeleted, other.dateDeleted) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            sessionSid,
                            serviceSid,
                            accountSid,
                            friendlyName,
                            identifier,
                            proxyIdentifier,
                            proxyIdentifierSid,
                            dateDeleted,
                            dateCreated,
                            dateUpdated,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("sessionSid", sessionSid)
                          .add("serviceSid", serviceSid)
                          .add("accountSid", accountSid)
                          .add("friendlyName", friendlyName)
                          .add("identifier", identifier)
                          .add("proxyIdentifier", proxyIdentifier)
                          .add("proxyIdentifierSid", proxyIdentifierSid)
                          .add("dateDeleted", dateDeleted)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}
