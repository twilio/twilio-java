/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.studio.v1.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
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

@JsonIgnoreProperties(ignoreUnknown = true)
public class Engagement extends Resource {
    private static final long serialVersionUID = 131970036431728L;

    public enum Status {
        ACTIVE("active"),
        ENDED("ended");

        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Status from a string.
         * @param value string value
         * @return generated Status
         */
        @JsonCreator
        public static Status forValue(final String value) {
            return Promoter.enumFromString(value, Status.values());
        }
    }

    /**
     * Create a EngagementReader to execute read.
     *
     * @param pathFlowSid The SID of the Flow to read Engagements from
     * @return EngagementReader capable of executing the read
     */
    public static EngagementReader reader(final String pathFlowSid) {
        return new EngagementReader(pathFlowSid);
    }

    /**
     * Create a EngagementFetcher to execute fetch.
     *
     * @param pathFlowSid Flow SID
     * @param pathSid The SID of the Engagement resource to fetch
     * @return EngagementFetcher capable of executing the fetch
     */
    public static EngagementFetcher fetcher(final String pathFlowSid,
                                            final String pathSid) {
        return new EngagementFetcher(pathFlowSid, pathSid);
    }

    /**
     * Create a EngagementCreator to execute create.
     *
     * @param pathFlowSid The SID of the Flow
     * @param to The Contact phone number to start a Studio Flow Engagement
     * @param from The Twilio phone number to send messages or initiate calls from
     *             during the Flow Engagement
     * @return EngagementCreator capable of executing the create
     */
    public static EngagementCreator creator(final String pathFlowSid,
                                            final com.twilio.type.PhoneNumber to,
                                            final com.twilio.type.PhoneNumber from) {
        return new EngagementCreator(pathFlowSid, to, from);
    }

    /**
     * Create a EngagementDeleter to execute delete.
     *
     * @param pathFlowSid The SID of the Flow to delete Engagements from
     * @param pathSid The SID of the Engagement resource to delete
     * @return EngagementDeleter capable of executing the delete
     */
    public static EngagementDeleter deleter(final String pathFlowSid,
                                            final String pathSid) {
        return new EngagementDeleter(pathFlowSid, pathSid);
    }

    /**
     * Converts a JSON String into a Engagement object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Engagement object represented by the provided JSON
     */
    public static Engagement fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Engagement.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Engagement object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Engagement object represented by the provided JSON
     */
    public static Engagement fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Engagement.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String flowSid;
    private final String contactSid;
    private final String contactChannelAddress;
    private final Map<String, Object> context;
    private final Engagement.Status status;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Engagement(@JsonProperty("sid")
                       final String sid,
                       @JsonProperty("account_sid")
                       final String accountSid,
                       @JsonProperty("flow_sid")
                       final String flowSid,
                       @JsonProperty("contact_sid")
                       final String contactSid,
                       @JsonProperty("contact_channel_address")
                       final String contactChannelAddress,
                       @JsonProperty("context")
                       final Map<String, Object> context,
                       @JsonProperty("status")
                       final Engagement.Status status,
                       @JsonProperty("date_created")
                       final String dateCreated,
                       @JsonProperty("date_updated")
                       final String dateUpdated,
                       @JsonProperty("url")
                       final URI url,
                       @JsonProperty("links")
                       final Map<String, String> links) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.flowSid = flowSid;
        this.contactSid = contactSid;
        this.contactChannelAddress = contactChannelAddress;
        this.context = context;
        this.status = status;
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
     * Returns The The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The SID of the Flow.
     *
     * @return The SID of the Flow
     */
    public final String getFlowSid() {
        return this.flowSid;
    }

    /**
     * Returns The The SID of the Contact.
     *
     * @return The SID of the Contact
     */
    public final String getContactSid() {
        return this.contactSid;
    }

    /**
     * Returns The The phone number, SIP address or Client identifier that triggered
     * this Engagement.
     *
     * @return The phone number, SIP address or Client identifier that triggered
     *         this Engagement
     */
    public final String getContactChannelAddress() {
        return this.contactChannelAddress;
    }

    /**
     * Returns The The current state of the execution flow.
     *
     * @return The current state of the execution flow
     */
    public final Map<String, Object> getContext() {
        return this.context;
    }

    /**
     * Returns The The status of the Engagement.
     *
     * @return The status of the Engagement
     */
    public final Engagement.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the Engagement was
     * created.
     *
     * @return The ISO 8601 date and time in GMT when the Engagement was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the Engagement was last
     * updated.
     *
     * @return The ISO 8601 date and time in GMT when the Engagement was last
     *         updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The URLs of the Engagement's nested resources.
     *
     * @return The URLs of the Engagement's nested resources
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

        Engagement other = (Engagement) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(flowSid, other.flowSid) &&
               Objects.equals(contactSid, other.contactSid) &&
               Objects.equals(contactChannelAddress, other.contactChannelAddress) &&
               Objects.equals(context, other.context) &&
               Objects.equals(status, other.status) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            flowSid,
                            contactSid,
                            contactChannelAddress,
                            context,
                            status,
                            dateCreated,
                            dateUpdated,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("flowSid", flowSid)
                          .add("contactSid", contactSid)
                          .add("contactChannelAddress", contactChannelAddress)
                          .add("context", context)
                          .add("status", status)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}
