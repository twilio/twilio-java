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
public class TaskQueue extends Resource {
    private static final long serialVersionUID = 31174194031326L;

    public enum TaskOrder {
        FIFO("FIFO"),
        LIFO("LIFO");

        private final String value;

        private TaskOrder(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a TaskOrder from a string.
         * @param value string value
         * @return generated TaskOrder
         */
        @JsonCreator
        public static TaskOrder forValue(final String value) {
            return Promoter.enumFromString(value, TaskOrder.values());
        }
    }

    /**
     * Create a TaskQueueFetcher to execute fetch.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskQueue to fetch
     * @param pathSid The SID of the resource to
     * @return TaskQueueFetcher capable of executing the fetch
     */
    public static TaskQueueFetcher fetcher(final String pathWorkspaceSid,
                                           final String pathSid) {
        return new TaskQueueFetcher(pathWorkspaceSid, pathSid);
    }

    /**
     * Create a TaskQueueUpdater to execute update.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskQueue to update
     * @param pathSid The SID of the resource to update
     * @return TaskQueueUpdater capable of executing the update
     */
    public static TaskQueueUpdater updater(final String pathWorkspaceSid,
                                           final String pathSid) {
        return new TaskQueueUpdater(pathWorkspaceSid, pathSid);
    }

    /**
     * Create a TaskQueueReader to execute read.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskQueue to read
     * @return TaskQueueReader capable of executing the read
     */
    public static TaskQueueReader reader(final String pathWorkspaceSid) {
        return new TaskQueueReader(pathWorkspaceSid);
    }

    /**
     * Create a TaskQueueCreator to execute create.
     *
     * @param pathWorkspaceSid The SID of the Workspace that the new TaskQueue
     *                         belongs to
     * @param friendlyName A string to describe the resource
     * @return TaskQueueCreator capable of executing the create
     */
    public static TaskQueueCreator creator(final String pathWorkspaceSid,
                                           final String friendlyName) {
        return new TaskQueueCreator(pathWorkspaceSid, friendlyName);
    }

    /**
     * Create a TaskQueueDeleter to execute delete.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskQueue to delete
     * @param pathSid The SID of the resource to delete
     * @return TaskQueueDeleter capable of executing the delete
     */
    public static TaskQueueDeleter deleter(final String pathWorkspaceSid,
                                           final String pathSid) {
        return new TaskQueueDeleter(pathWorkspaceSid, pathSid);
    }

    /**
     * Converts a JSON String into a TaskQueue object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return TaskQueue object represented by the provided JSON
     */
    public static TaskQueue fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskQueue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a TaskQueue object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return TaskQueue object represented by the provided JSON
     */
    public static TaskQueue fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskQueue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String assignmentActivitySid;
    private final String assignmentActivityName;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String friendlyName;
    private final Integer maxReservedWorkers;
    private final String reservationActivitySid;
    private final String reservationActivityName;
    private final String sid;
    private final String targetWorkers;
    private final TaskQueue.TaskOrder taskOrder;
    private final URI url;
    private final String workspaceSid;
    private final Map<String, String> links;

    @JsonCreator
    private TaskQueue(@JsonProperty("account_sid")
                      final String accountSid,
                      @JsonProperty("assignment_activity_sid")
                      final String assignmentActivitySid,
                      @JsonProperty("assignment_activity_name")
                      final String assignmentActivityName,
                      @JsonProperty("date_created")
                      final String dateCreated,
                      @JsonProperty("date_updated")
                      final String dateUpdated,
                      @JsonProperty("friendly_name")
                      final String friendlyName,
                      @JsonProperty("max_reserved_workers")
                      final Integer maxReservedWorkers,
                      @JsonProperty("reservation_activity_sid")
                      final String reservationActivitySid,
                      @JsonProperty("reservation_activity_name")
                      final String reservationActivityName,
                      @JsonProperty("sid")
                      final String sid,
                      @JsonProperty("target_workers")
                      final String targetWorkers,
                      @JsonProperty("task_order")
                      final TaskQueue.TaskOrder taskOrder,
                      @JsonProperty("url")
                      final URI url,
                      @JsonProperty("workspace_sid")
                      final String workspaceSid,
                      @JsonProperty("links")
                      final Map<String, String> links) {
        this.accountSid = accountSid;
        this.assignmentActivitySid = assignmentActivitySid;
        this.assignmentActivityName = assignmentActivityName;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.maxReservedWorkers = maxReservedWorkers;
        this.reservationActivitySid = reservationActivitySid;
        this.reservationActivityName = reservationActivityName;
        this.sid = sid;
        this.targetWorkers = targetWorkers;
        this.taskOrder = taskOrder;
        this.url = url;
        this.workspaceSid = workspaceSid;
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
     * Returns The SID of the Activity to assign Workers when a task is assigned for
     * them.
     *
     * @return The SID of the Activity to assign Workers when a task is assigned
     *         for them
     */
    public final String getAssignmentActivitySid() {
        return this.assignmentActivitySid;
    }

    /**
     * Returns The name of the Activity to assign Workers when a task is assigned
     * for them.
     *
     * @return The name of the Activity to assign Workers when a task is assigned
     *         for them
     */
    public final String getAssignmentActivityName() {
        return this.assignmentActivityName;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was created.
     *
     * @return The RFC 2822 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was last updated.
     *
     * @return The RFC 2822 date and time in GMT when the resource was last updated
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
     * Returns The maximum number of Workers to reserve.
     *
     * @return The maximum number of Workers to reserve
     */
    public final Integer getMaxReservedWorkers() {
        return this.maxReservedWorkers;
    }

    /**
     * Returns The SID of the Activity to assign Workers once a task is reserved for
     * them.
     *
     * @return The SID of the Activity to assign Workers once a task is reserved
     *         for them
     */
    public final String getReservationActivitySid() {
        return this.reservationActivitySid;
    }

    /**
     * Returns The name of the Activity to assign Workers once a task is reserved
     * for them.
     *
     * @return The name of the Activity to assign Workers once a task is reserved
     *         for them
     */
    public final String getReservationActivityName() {
        return this.reservationActivityName;
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
     * Returns A string describing the Worker selection criteria for any Tasks that
     * enter the TaskQueue.
     *
     * @return A string describing the Worker selection criteria for any Tasks that
     *         enter the TaskQueue
     */
    public final String getTargetWorkers() {
        return this.targetWorkers;
    }

    /**
     * Returns How Tasks will be assigned to Workers.
     *
     * @return How Tasks will be assigned to Workers
     */
    public final TaskQueue.TaskOrder getTaskOrder() {
        return this.taskOrder;
    }

    /**
     * Returns The absolute URL of the TaskQueue resource.
     *
     * @return The absolute URL of the TaskQueue resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The SID of the Workspace that contains the TaskQueue.
     *
     * @return The SID of the Workspace that contains the TaskQueue
     */
    public final String getWorkspaceSid() {
        return this.workspaceSid;
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

        TaskQueue other = (TaskQueue) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(assignmentActivitySid, other.assignmentActivitySid) &&
               Objects.equals(assignmentActivityName, other.assignmentActivityName) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(maxReservedWorkers, other.maxReservedWorkers) &&
               Objects.equals(reservationActivitySid, other.reservationActivitySid) &&
               Objects.equals(reservationActivityName, other.reservationActivityName) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(targetWorkers, other.targetWorkers) &&
               Objects.equals(taskOrder, other.taskOrder) &&
               Objects.equals(url, other.url) &&
               Objects.equals(workspaceSid, other.workspaceSid) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            assignmentActivitySid,
                            assignmentActivityName,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            maxReservedWorkers,
                            reservationActivitySid,
                            reservationActivityName,
                            sid,
                            targetWorkers,
                            taskOrder,
                            url,
                            workspaceSid,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("assignmentActivitySid", assignmentActivitySid)
                          .add("assignmentActivityName", assignmentActivityName)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("maxReservedWorkers", maxReservedWorkers)
                          .add("reservationActivitySid", reservationActivitySid)
                          .add("reservationActivityName", reservationActivityName)
                          .add("sid", sid)
                          .add("targetWorkers", targetWorkers)
                          .add("taskOrder", taskOrder)
                          .add("url", url)
                          .add("workspaceSid", workspaceSid)
                          .add("links", links)
                          .toString();
    }
}
