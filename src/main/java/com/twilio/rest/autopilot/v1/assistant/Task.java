/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1.assistant;

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
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task extends Resource {
    private static final long serialVersionUID = 120802226226536L;

    /**
     * Create a TaskFetcher to execute fetch.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resource to fetch
     * @param pathSid The unique string that identifies the resource to fetch
     * @return TaskFetcher capable of executing the fetch
     */
    public static TaskFetcher fetcher(final String pathAssistantSid,
                                      final String pathSid) {
        return new TaskFetcher(pathAssistantSid, pathSid);
    }

    /**
     * Create a TaskReader to execute read.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resources to read
     * @return TaskReader capable of executing the read
     */
    public static TaskReader reader(final String pathAssistantSid) {
        return new TaskReader(pathAssistantSid);
    }

    /**
     * Create a TaskCreator to execute create.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         new resource
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return TaskCreator capable of executing the create
     */
    public static TaskCreator creator(final String pathAssistantSid,
                                      final String uniqueName) {
        return new TaskCreator(pathAssistantSid, uniqueName);
    }

    /**
     * Create a TaskUpdater to execute update.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resource to update
     * @param pathSid The unique string that identifies the resource
     * @return TaskUpdater capable of executing the update
     */
    public static TaskUpdater updater(final String pathAssistantSid,
                                      final String pathSid) {
        return new TaskUpdater(pathAssistantSid, pathSid);
    }

    /**
     * Create a TaskDeleter to execute delete.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resources to delete
     * @param pathSid The unique string that identifies the resource to delete
     * @return TaskDeleter capable of executing the delete
     */
    public static TaskDeleter deleter(final String pathAssistantSid,
                                      final String pathSid) {
        return new TaskDeleter(pathAssistantSid, pathSid);
    }

    /**
     * Converts a JSON String into a Task object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Task object represented by the provided JSON
     */
    public static Task fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Task.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Task object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Task object represented by the provided JSON
     */
    public static Task fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Task.class);
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
    private final Map<String, String> links;
    private final String assistantSid;
    private final String sid;
    private final String uniqueName;
    private final URI actionsUrl;
    private final URI url;

    @JsonCreator
    private Task(@JsonProperty("account_sid")
                 final String accountSid,
                 @JsonProperty("date_created")
                 final String dateCreated,
                 @JsonProperty("date_updated")
                 final String dateUpdated,
                 @JsonProperty("friendly_name")
                 final String friendlyName,
                 @JsonProperty("links")
                 final Map<String, String> links,
                 @JsonProperty("assistant_sid")
                 final String assistantSid,
                 @JsonProperty("sid")
                 final String sid,
                 @JsonProperty("unique_name")
                 final String uniqueName,
                 @JsonProperty("actions_url")
                 final URI actionsUrl,
                 @JsonProperty("url")
                 final URI url) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.links = links;
        this.assistantSid = assistantSid;
        this.sid = sid;
        this.uniqueName = uniqueName;
        this.actionsUrl = actionsUrl;
        this.url = url;
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
     * Returns The RFC 2822 date and time in GMT when the resource was created.
     *
     * @return The RFC 2822 date and time in GMT when the resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was last updated.
     *
     * @return The RFC 2822 date and time in GMT when the resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns A list of the URLs of related resources.
     *
     * @return A list of the URLs of related resources
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    /**
     * Returns The SID of the Assistant that is the parent of the resource.
     *
     * @return The SID of the Assistant that is the parent of the resource
     */
    public final String getAssistantSid() {
        return this.assistantSid;
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
     * Returns An application-defined string that uniquely identifies the resource.
     *
     * @return An application-defined string that uniquely identifies the resource
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The URL from which the Assistant can fetch actions.
     *
     * @return The URL from which the Assistant can fetch actions
     */
    public final URI getActionsUrl() {
        return this.actionsUrl;
    }

    /**
     * Returns The absolute URL of the Task resource.
     *
     * @return The absolute URL of the Task resource
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task other = (Task) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(links, other.links) &&
               Objects.equals(assistantSid, other.assistantSid) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(actionsUrl, other.actionsUrl) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            links,
                            assistantSid,
                            sid,
                            uniqueName,
                            actionsUrl,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("links", links)
                          .add("assistantSid", assistantSid)
                          .add("sid", sid)
                          .add("uniqueName", uniqueName)
                          .add("actionsUrl", actionsUrl)
                          .add("url", url)
                          .toString();
    }
}