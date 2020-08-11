/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1.workspace;

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

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskChannel extends Resource {
    private static final long serialVersionUID = 87520156212725L;

    /**
     * Create a TaskChannelFetcher to execute fetch.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskChannel to
     *                         fetch
     * @param pathSid The SID of the TaskChannel resource to fetch
     * @return TaskChannelFetcher capable of executing the fetch
     */
    public static TaskChannelFetcher fetcher(final String pathWorkspaceSid,
                                             final String pathSid) {
        return new TaskChannelFetcher(pathWorkspaceSid, pathSid);
    }

    /**
     * Create a TaskChannelReader to execute read.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskChannel to read
     * @return TaskChannelReader capable of executing the read
     */
    public static TaskChannelReader reader(final String pathWorkspaceSid) {
        return new TaskChannelReader(pathWorkspaceSid);
    }

    /**
     * Create a TaskChannelUpdater to execute update.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskChannel to
     *                         update
     * @param pathSid The SID of the TaskChannel resource to update
     * @return TaskChannelUpdater capable of executing the update
     */
    public static TaskChannelUpdater updater(final String pathWorkspaceSid,
                                             final String pathSid) {
        return new TaskChannelUpdater(pathWorkspaceSid, pathSid);
    }

    /**
     * Create a TaskChannelDeleter to execute delete.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskChannel to
     *                         delete
     * @param pathSid The SID of the TaskChannel resource to delete
     * @return TaskChannelDeleter capable of executing the delete
     */
    public static TaskChannelDeleter deleter(final String pathWorkspaceSid,
                                             final String pathSid) {
        return new TaskChannelDeleter(pathWorkspaceSid, pathSid);
    }

    /**
     * Create a TaskChannelCreator to execute create.
     *
     * @param pathWorkspaceSid The SID of the Workspace that the new TaskChannel
     *                         belongs to
     * @param friendlyName A string to describe the TaskChannel resource
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   TaskChannel
     * @return TaskChannelCreator capable of executing the create
     */
    public static TaskChannelCreator creator(final String pathWorkspaceSid,
                                             final String friendlyName,
                                             final String uniqueName) {
        return new TaskChannelCreator(pathWorkspaceSid, friendlyName, uniqueName);
    }

    /**
     * Converts a JSON String into a TaskChannel object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return TaskChannel object represented by the provided JSON
     */
    public static TaskChannel fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskChannel.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a TaskChannel object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return TaskChannel object represented by the provided JSON
     */
    public static TaskChannel fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskChannel.class);
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
    private final String uniqueName;
    private final String workspaceSid;
    private final Boolean channelOptimizedRouting;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private TaskChannel(@JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated,
                        @JsonProperty("friendly_name")
                        final String friendlyName,
                        @JsonProperty("sid")
                        final String sid,
                        @JsonProperty("unique_name")
                        final String uniqueName,
                        @JsonProperty("workspace_sid")
                        final String workspaceSid,
                        @JsonProperty("channel_optimized_routing")
                        final Boolean channelOptimizedRouting,
                        @JsonProperty("url")
                        final URI url,
                        @JsonProperty("links")
                        final Map<String, String> links) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.sid = sid;
        this.uniqueName = uniqueName;
        this.workspaceSid = workspaceSid;
        this.channelOptimizedRouting = channelOptimizedRouting;
        this.url = url;
        this.links = links;
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
     * Returns The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
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
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns An application-defined string that uniquely identifies the
     * TaskChannel.
     *
     * @return An application-defined string that uniquely identifies the
     *         TaskChannel
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The SID of the Workspace that contains the TaskChannel.
     *
     * @return The SID of the Workspace that contains the TaskChannel
     */
    public final String getWorkspaceSid() {
        return this.workspaceSid;
    }

    /**
     * Returns Whether the TaskChannel will prioritize Workers that have been idle.
     *
     * @return Whether the TaskChannel will prioritize Workers that have been idle
     */
    public final Boolean getChannelOptimizedRouting() {
        return this.channelOptimizedRouting;
    }

    /**
     * Returns The absolute URL of the TaskChannel resource.
     *
     * @return The absolute URL of the TaskChannel resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The URLs of related resources.
     *
     * @return The URLs of related resources
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

        TaskChannel other = (TaskChannel) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(workspaceSid, other.workspaceSid) &&
               Objects.equals(channelOptimizedRouting, other.channelOptimizedRouting) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            sid,
                            uniqueName,
                            workspaceSid,
                            channelOptimizedRouting,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("sid", sid)
                          .add("uniqueName", uniqueName)
                          .add("workspaceSid", workspaceSid)
                          .add("channelOptimizedRouting", channelOptimizedRouting)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}
