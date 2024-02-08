/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Taskrouter
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.taskrouter.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Workspace extends Resource {

    private static final long serialVersionUID = 259156582462753L;

    public static WorkspaceCreator creator(final String friendlyName) {
        return new WorkspaceCreator(friendlyName);
    }

    public static WorkspaceDeleter deleter(final String pathSid) {
        return new WorkspaceDeleter(pathSid);
    }

    public static WorkspaceFetcher fetcher(final String pathSid) {
        return new WorkspaceFetcher(pathSid);
    }

    public static WorkspaceReader reader() {
        return new WorkspaceReader();
    }

    public static WorkspaceUpdater updater(final String pathSid) {
        return new WorkspaceUpdater(pathSid);
    }

    /**
     * Converts a JSON String into a Workspace object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Workspace object represented by the provided JSON
     */
    public static Workspace fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Workspace.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Workspace object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Workspace object represented by the provided JSON
     */
    public static Workspace fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Workspace.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String defaultActivityName;
    private final String defaultActivitySid;
    private final URI eventCallbackUrl;
    private final String eventsFilter;
    private final String friendlyName;
    private final Boolean multiTaskEnabled;
    private final String sid;
    private final String timeoutActivityName;
    private final String timeoutActivitySid;
    private final Workspace.QueueOrder prioritizeQueueOrder;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Workspace(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("default_activity_name") final String defaultActivityName,
        @JsonProperty("default_activity_sid") final String defaultActivitySid,
        @JsonProperty("event_callback_url") final URI eventCallbackUrl,
        @JsonProperty("events_filter") final String eventsFilter,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("multi_task_enabled") final Boolean multiTaskEnabled,
        @JsonProperty("sid") final String sid,
        @JsonProperty("timeout_activity_name") final String timeoutActivityName,
        @JsonProperty("timeout_activity_sid") final String timeoutActivitySid,
        @JsonProperty(
            "prioritize_queue_order"
        ) final Workspace.QueueOrder prioritizeQueueOrder,
        @JsonProperty("url") final URI url,
        @JsonProperty("links") final Map<String, String> links
    ) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.defaultActivityName = defaultActivityName;
        this.defaultActivitySid = defaultActivitySid;
        this.eventCallbackUrl = eventCallbackUrl;
        this.eventsFilter = eventsFilter;
        this.friendlyName = friendlyName;
        this.multiTaskEnabled = multiTaskEnabled;
        this.sid = sid;
        this.timeoutActivityName = timeoutActivityName;
        this.timeoutActivitySid = timeoutActivitySid;
        this.prioritizeQueueOrder = prioritizeQueueOrder;
        this.url = url;
        this.links = links;
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

    public final String getDefaultActivityName() {
        return this.defaultActivityName;
    }

    public final String getDefaultActivitySid() {
        return this.defaultActivitySid;
    }

    public final URI getEventCallbackUrl() {
        return this.eventCallbackUrl;
    }

    public final String getEventsFilter() {
        return this.eventsFilter;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final Boolean getMultiTaskEnabled() {
        return this.multiTaskEnabled;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getTimeoutActivityName() {
        return this.timeoutActivityName;
    }

    public final String getTimeoutActivitySid() {
        return this.timeoutActivitySid;
    }

    public final Workspace.QueueOrder getPrioritizeQueueOrder() {
        return this.prioritizeQueueOrder;
    }

    public final URI getUrl() {
        return this.url;
    }

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

        Workspace other = (Workspace) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(defaultActivityName, other.defaultActivityName) &&
            Objects.equals(defaultActivitySid, other.defaultActivitySid) &&
            Objects.equals(eventCallbackUrl, other.eventCallbackUrl) &&
            Objects.equals(eventsFilter, other.eventsFilter) &&
            Objects.equals(friendlyName, other.friendlyName) &&
            Objects.equals(multiTaskEnabled, other.multiTaskEnabled) &&
            Objects.equals(sid, other.sid) &&
            Objects.equals(timeoutActivityName, other.timeoutActivityName) &&
            Objects.equals(timeoutActivitySid, other.timeoutActivitySid) &&
            Objects.equals(prioritizeQueueOrder, other.prioritizeQueueOrder) &&
            Objects.equals(url, other.url) &&
            Objects.equals(links, other.links)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
            dateCreated,
            dateUpdated,
            defaultActivityName,
            defaultActivitySid,
            eventCallbackUrl,
            eventsFilter,
            friendlyName,
            multiTaskEnabled,
            sid,
            timeoutActivityName,
            timeoutActivitySid,
            prioritizeQueueOrder,
            url,
            links
        );
    }

    public enum QueueOrder {
        FIFO("FIFO"),
        LIFO("LIFO");

        private final String value;

        private QueueOrder(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static QueueOrder forValue(final String value) {
            return Promoter.enumFromString(value, QueueOrder.values());
        }
    }
}
