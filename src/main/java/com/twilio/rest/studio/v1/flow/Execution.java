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
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Execution extends Resource {
    private static final long serialVersionUID = 259041091594783L;

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
     * Create a ExecutionReader to execute read.
     *
     * @param pathFlowSid The SID of the Flow
     * @return ExecutionReader capable of executing the read
     */
    public static ExecutionReader reader(final String pathFlowSid) {
        return new ExecutionReader(pathFlowSid);
    }

    /**
     * Create a ExecutionFetcher to execute fetch.
     *
     * @param pathFlowSid The SID of the Flow
     * @param pathSid The SID of the Execution resource to fetch
     * @return ExecutionFetcher capable of executing the fetch
     */
    public static ExecutionFetcher fetcher(final String pathFlowSid,
                                           final String pathSid) {
        return new ExecutionFetcher(pathFlowSid, pathSid);
    }

    /**
     * Create a ExecutionCreator to execute create.
     *
     * @param pathFlowSid The SID of the Flow
     * @param to The Contact phone number to start a Studio Flow Execution
     * @param from The Twilio phone number to send messages or initiate calls from
     *             during the Flow Execution
     * @return ExecutionCreator capable of executing the create
     */
    public static ExecutionCreator creator(final String pathFlowSid,
                                           final com.twilio.type.PhoneNumber to,
                                           final com.twilio.type.PhoneNumber from) {
        return new ExecutionCreator(pathFlowSid, to, from);
    }

    /**
     * Create a ExecutionDeleter to execute delete.
     *
     * @param pathFlowSid The SID of the Flow
     * @param pathSid The SID of the Execution resource to delete
     * @return ExecutionDeleter capable of executing the delete
     */
    public static ExecutionDeleter deleter(final String pathFlowSid,
                                           final String pathSid) {
        return new ExecutionDeleter(pathFlowSid, pathSid);
    }

    /**
     * Create a ExecutionUpdater to execute update.
     *
     * @param pathFlowSid The SID of the Flow
     * @param pathSid The SID of the Execution resource to update
     * @param status The status of the Execution
     * @return ExecutionUpdater capable of executing the update
     */
    public static ExecutionUpdater updater(final String pathFlowSid,
                                           final String pathSid,
                                           final Execution.Status status) {
        return new ExecutionUpdater(pathFlowSid, pathSid, status);
    }

    /**
     * Converts a JSON String into a Execution object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Execution object represented by the provided JSON
     */
    public static Execution fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Execution.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Execution object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Execution object represented by the provided JSON
     */
    public static Execution fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Execution.class);
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
    private final Execution.Status status;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Execution(@JsonProperty("sid")
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
                      final Execution.Status status,
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
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Flow.
     *
     * @return The SID of the Flow
     */
    public final String getFlowSid() {
        return this.flowSid;
    }

    /**
     * Returns The SID of the Contact.
     *
     * @return The SID of the Contact
     */
    public final String getContactSid() {
        return this.contactSid;
    }

    /**
     * Returns The phone number, SIP address or Client identifier that triggered
     * the Execution.
     *
     * @return The phone number, SIP address or Client identifier that triggered
     *         the Execution
     */
    public final String getContactChannelAddress() {
        return this.contactChannelAddress;
    }

    /**
     * Returns The current state of the flow.
     *
     * @return The current state of the flow
     */
    public final Map<String, Object> getContext() {
        return this.context;
    }

    /**
     * Returns The status of the Execution.
     *
     * @return The status of the Execution
     */
    public final Execution.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns Nested resource URLs.
     *
     * @return Nested resource URLs
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

        Execution other = (Execution) o;

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