/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1.workspace.taskqueue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import lombok.ToString;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class TaskQueueCumulativeStatistics extends Resource {
    private static final long serialVersionUID = 205432946594841L;

    /**
     * Create a TaskQueueCumulativeStatisticsFetcher to execute fetch.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the TaskQueue to fetch
     * @param pathTaskQueueSid The SID of the TaskQueue for which to fetch
     *                         statistics
     * @return TaskQueueCumulativeStatisticsFetcher capable of executing the fetch
     */
    public static TaskQueueCumulativeStatisticsFetcher fetcher(final String pathWorkspaceSid,
                                                               final String pathTaskQueueSid) {
        return new TaskQueueCumulativeStatisticsFetcher(pathWorkspaceSid, pathTaskQueueSid);
    }

    /**
     * Converts a JSON String into a TaskQueueCumulativeStatistics object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return TaskQueueCumulativeStatistics object represented by the provided JSON
     */
    public static TaskQueueCumulativeStatistics fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskQueueCumulativeStatistics.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a TaskQueueCumulativeStatistics object using
     * the provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return TaskQueueCumulativeStatistics object represented by the provided JSON
     */
    public static TaskQueueCumulativeStatistics fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskQueueCumulativeStatistics.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final Integer avgTaskAcceptanceTime;
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;
    private final Integer reservationsCreated;
    private final Integer reservationsAccepted;
    private final Integer reservationsRejected;
    private final Integer reservationsTimedOut;
    private final Integer reservationsCanceled;
    private final Integer reservationsRescinded;
    private final Map<String, Object> splitByWaitTime;
    private final String taskQueueSid;
    private final Map<String, Object> waitDurationUntilAccepted;
    private final Map<String, Object> waitDurationUntilCanceled;
    private final Map<String, Object> waitDurationInQueueUntilAccepted;
    private final Integer tasksCanceled;
    private final Integer tasksCompleted;
    private final Integer tasksDeleted;
    private final Integer tasksEntered;
    private final Integer tasksMoved;
    private final String workspaceSid;
    private final URI url;

    @JsonCreator
    private TaskQueueCumulativeStatistics(@JsonProperty("account_sid")
                                          final String accountSid,
                                          @JsonProperty("avg_task_acceptance_time")
                                          final Integer avgTaskAcceptanceTime,
                                          @JsonProperty("start_time")
                                          final String startTime,
                                          @JsonProperty("end_time")
                                          final String endTime,
                                          @JsonProperty("reservations_created")
                                          final Integer reservationsCreated,
                                          @JsonProperty("reservations_accepted")
                                          final Integer reservationsAccepted,
                                          @JsonProperty("reservations_rejected")
                                          final Integer reservationsRejected,
                                          @JsonProperty("reservations_timed_out")
                                          final Integer reservationsTimedOut,
                                          @JsonProperty("reservations_canceled")
                                          final Integer reservationsCanceled,
                                          @JsonProperty("reservations_rescinded")
                                          final Integer reservationsRescinded,
                                          @JsonProperty("split_by_wait_time")
                                          final Map<String, Object> splitByWaitTime,
                                          @JsonProperty("task_queue_sid")
                                          final String taskQueueSid,
                                          @JsonProperty("wait_duration_until_accepted")
                                          final Map<String, Object> waitDurationUntilAccepted,
                                          @JsonProperty("wait_duration_until_canceled")
                                          final Map<String, Object> waitDurationUntilCanceled,
                                          @JsonProperty("wait_duration_in_queue_until_accepted")
                                          final Map<String, Object> waitDurationInQueueUntilAccepted,
                                          @JsonProperty("tasks_canceled")
                                          final Integer tasksCanceled,
                                          @JsonProperty("tasks_completed")
                                          final Integer tasksCompleted,
                                          @JsonProperty("tasks_deleted")
                                          final Integer tasksDeleted,
                                          @JsonProperty("tasks_entered")
                                          final Integer tasksEntered,
                                          @JsonProperty("tasks_moved")
                                          final Integer tasksMoved,
                                          @JsonProperty("workspace_sid")
                                          final String workspaceSid,
                                          @JsonProperty("url")
                                          final URI url) {
        this.accountSid = accountSid;
        this.avgTaskAcceptanceTime = avgTaskAcceptanceTime;
        this.startTime = DateConverter.iso8601DateTimeFromString(startTime);
        this.endTime = DateConverter.iso8601DateTimeFromString(endTime);
        this.reservationsCreated = reservationsCreated;
        this.reservationsAccepted = reservationsAccepted;
        this.reservationsRejected = reservationsRejected;
        this.reservationsTimedOut = reservationsTimedOut;
        this.reservationsCanceled = reservationsCanceled;
        this.reservationsRescinded = reservationsRescinded;
        this.splitByWaitTime = splitByWaitTime;
        this.taskQueueSid = taskQueueSid;
        this.waitDurationUntilAccepted = waitDurationUntilAccepted;
        this.waitDurationUntilCanceled = waitDurationUntilCanceled;
        this.waitDurationInQueueUntilAccepted = waitDurationInQueueUntilAccepted;
        this.tasksCanceled = tasksCanceled;
        this.tasksCompleted = tasksCompleted;
        this.tasksDeleted = tasksDeleted;
        this.tasksEntered = tasksEntered;
        this.tasksMoved = tasksMoved;
        this.workspaceSid = workspaceSid;
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
     * Returns The average time in seconds between Task creation and acceptance.
     *
     * @return The average time in seconds between Task creation and acceptance
     */
    public final Integer getAvgTaskAcceptanceTime() {
        return this.avgTaskAcceptanceTime;
    }

    /**
     * Returns The beginning of the interval during which these statistics were
     * calculated.
     *
     * @return The beginning of the interval during which these statistics were
     *         calculated
     */
    public final ZonedDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns The end of the interval during which these statistics were
     * calculated.
     *
     * @return The end of the interval during which these statistics were calculated
     */
    public final ZonedDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns The total number of Reservations created for Tasks in the TaskQueue.
     *
     * @return The total number of Reservations created for Tasks in the TaskQueue
     */
    public final Integer getReservationsCreated() {
        return this.reservationsCreated;
    }

    /**
     * Returns The total number of Reservations accepted for Tasks in the TaskQueue.
     *
     * @return The total number of Reservations accepted for Tasks in the TaskQueue
     */
    public final Integer getReservationsAccepted() {
        return this.reservationsAccepted;
    }

    /**
     * Returns The total number of Reservations rejected for Tasks in the TaskQueue.
     *
     * @return The total number of Reservations rejected for Tasks in the TaskQueue
     */
    public final Integer getReservationsRejected() {
        return this.reservationsRejected;
    }

    /**
     * Returns The total number of Reservations that timed out for Tasks in the
     * TaskQueue.
     *
     * @return The total number of Reservations that timed out for Tasks in the
     *         TaskQueue
     */
    public final Integer getReservationsTimedOut() {
        return this.reservationsTimedOut;
    }

    /**
     * Returns The total number of Reservations canceled for Tasks in the TaskQueue.
     *
     * @return The total number of Reservations canceled for Tasks in the TaskQueue
     */
    public final Integer getReservationsCanceled() {
        return this.reservationsCanceled;
    }

    /**
     * Returns The total number of Reservations rescinded.
     *
     * @return The total number of Reservations rescinded
     */
    public final Integer getReservationsRescinded() {
        return this.reservationsRescinded;
    }

    /**
     * Returns A list of objects that describe the Tasks canceled and reservations
     * accepted above and below the specified thresholds.
     *
     * @return A list of objects that describe the Tasks canceled and reservations
     *         accepted above and below the specified thresholds
     */
    public final Map<String, Object> getSplitByWaitTime() {
        return this.splitByWaitTime;
    }

    /**
     * Returns The SID of the TaskQueue from which these statistics were calculated.
     *
     * @return The SID of the TaskQueue from which these statistics were calculated
     */
    public final String getTaskQueueSid() {
        return this.taskQueueSid;
    }

    /**
     * Returns The wait duration statistics for Tasks accepted while in the
     * TaskQueue.
     *
     * @return The wait duration statistics for Tasks accepted while in the
     *         TaskQueue
     */
    public final Map<String, Object> getWaitDurationUntilAccepted() {
        return this.waitDurationUntilAccepted;
    }

    /**
     * Returns The wait duration statistics for Tasks canceled while in the
     * TaskQueue.
     *
     * @return The wait duration statistics for Tasks canceled while in the
     *         TaskQueue
     */
    public final Map<String, Object> getWaitDurationUntilCanceled() {
        return this.waitDurationUntilCanceled;
    }

    /**
     * Returns The relative wait duration statistics for Tasks accepted while in the
     * TaskQueue.
     *
     * @return The relative wait duration statistics for Tasks accepted while in
     *         the TaskQueue
     */
    public final Map<String, Object> getWaitDurationInQueueUntilAccepted() {
        return this.waitDurationInQueueUntilAccepted;
    }

    /**
     * Returns The total number of Tasks canceled in the TaskQueue.
     *
     * @return The total number of Tasks canceled in the TaskQueue
     */
    public final Integer getTasksCanceled() {
        return this.tasksCanceled;
    }

    /**
     * Returns The total number of Tasks completed in the TaskQueue.
     *
     * @return The total number of Tasks completed in the TaskQueue
     */
    public final Integer getTasksCompleted() {
        return this.tasksCompleted;
    }

    /**
     * Returns The total number of Tasks deleted in the TaskQueue.
     *
     * @return The total number of Tasks deleted in the TaskQueue
     */
    public final Integer getTasksDeleted() {
        return this.tasksDeleted;
    }

    /**
     * Returns The total number of Tasks entered into the TaskQueue.
     *
     * @return The total number of Tasks entered into the TaskQueue
     */
    public final Integer getTasksEntered() {
        return this.tasksEntered;
    }

    /**
     * Returns The total number of Tasks that were moved from one queue to another.
     *
     * @return The total number of Tasks that were moved from one queue to another
     */
    public final Integer getTasksMoved() {
        return this.tasksMoved;
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
     * Returns The absolute URL of the TaskQueue statistics resource.
     *
     * @return The absolute URL of the TaskQueue statistics resource
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

        TaskQueueCumulativeStatistics other = (TaskQueueCumulativeStatistics) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(avgTaskAcceptanceTime, other.avgTaskAcceptanceTime) &&
               Objects.equals(startTime, other.startTime) &&
               Objects.equals(endTime, other.endTime) &&
               Objects.equals(reservationsCreated, other.reservationsCreated) &&
               Objects.equals(reservationsAccepted, other.reservationsAccepted) &&
               Objects.equals(reservationsRejected, other.reservationsRejected) &&
               Objects.equals(reservationsTimedOut, other.reservationsTimedOut) &&
               Objects.equals(reservationsCanceled, other.reservationsCanceled) &&
               Objects.equals(reservationsRescinded, other.reservationsRescinded) &&
               Objects.equals(splitByWaitTime, other.splitByWaitTime) &&
               Objects.equals(taskQueueSid, other.taskQueueSid) &&
               Objects.equals(waitDurationUntilAccepted, other.waitDurationUntilAccepted) &&
               Objects.equals(waitDurationUntilCanceled, other.waitDurationUntilCanceled) &&
               Objects.equals(waitDurationInQueueUntilAccepted, other.waitDurationInQueueUntilAccepted) &&
               Objects.equals(tasksCanceled, other.tasksCanceled) &&
               Objects.equals(tasksCompleted, other.tasksCompleted) &&
               Objects.equals(tasksDeleted, other.tasksDeleted) &&
               Objects.equals(tasksEntered, other.tasksEntered) &&
               Objects.equals(tasksMoved, other.tasksMoved) &&
               Objects.equals(workspaceSid, other.workspaceSid) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            avgTaskAcceptanceTime,
                            startTime,
                            endTime,
                            reservationsCreated,
                            reservationsAccepted,
                            reservationsRejected,
                            reservationsTimedOut,
                            reservationsCanceled,
                            reservationsRescinded,
                            splitByWaitTime,
                            taskQueueSid,
                            waitDurationUntilAccepted,
                            waitDurationUntilCanceled,
                            waitDurationInQueueUntilAccepted,
                            tasksCanceled,
                            tasksCompleted,
                            tasksDeleted,
                            tasksEntered,
                            tasksMoved,
                            workspaceSid,
                            url);
    }
}
