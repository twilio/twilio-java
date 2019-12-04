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
import java.time.ZonedDateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkspaceCumulativeStatistics extends Resource {
    private static final long serialVersionUID = 278709267705066L;

    /**
     * Create a WorkspaceCumulativeStatisticsFetcher to execute fetch.
     *
     * @param pathWorkspaceSid The SID of the Workspace to fetch
     * @return WorkspaceCumulativeStatisticsFetcher capable of executing the fetch
     */
    public static WorkspaceCumulativeStatisticsFetcher fetcher(final String pathWorkspaceSid) {
        return new WorkspaceCumulativeStatisticsFetcher(pathWorkspaceSid);
    }

    /**
     * Converts a JSON String into a WorkspaceCumulativeStatistics object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return WorkspaceCumulativeStatistics object represented by the provided JSON
     */
    public static WorkspaceCumulativeStatistics fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, WorkspaceCumulativeStatistics.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a WorkspaceCumulativeStatistics object using
     * the provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return WorkspaceCumulativeStatistics object represented by the provided JSON
     */
    public static WorkspaceCumulativeStatistics fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, WorkspaceCumulativeStatistics.class);
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
    private final Map<String, Object> waitDurationUntilAccepted;
    private final Map<String, Object> waitDurationUntilCanceled;
    private final Integer tasksCanceled;
    private final Integer tasksCompleted;
    private final Integer tasksCreated;
    private final Integer tasksDeleted;
    private final Integer tasksMoved;
    private final Integer tasksTimedOutInWorkflow;
    private final String workspaceSid;
    private final URI url;

    @JsonCreator
    private WorkspaceCumulativeStatistics(@JsonProperty("account_sid")
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
                                          @JsonProperty("wait_duration_until_accepted")
                                          final Map<String, Object> waitDurationUntilAccepted,
                                          @JsonProperty("wait_duration_until_canceled")
                                          final Map<String, Object> waitDurationUntilCanceled,
                                          @JsonProperty("tasks_canceled")
                                          final Integer tasksCanceled,
                                          @JsonProperty("tasks_completed")
                                          final Integer tasksCompleted,
                                          @JsonProperty("tasks_created")
                                          final Integer tasksCreated,
                                          @JsonProperty("tasks_deleted")
                                          final Integer tasksDeleted,
                                          @JsonProperty("tasks_moved")
                                          final Integer tasksMoved,
                                          @JsonProperty("tasks_timed_out_in_workflow")
                                          final Integer tasksTimedOutInWorkflow,
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
        this.waitDurationUntilAccepted = waitDurationUntilAccepted;
        this.waitDurationUntilCanceled = waitDurationUntilCanceled;
        this.tasksCanceled = tasksCanceled;
        this.tasksCompleted = tasksCompleted;
        this.tasksCreated = tasksCreated;
        this.tasksDeleted = tasksDeleted;
        this.tasksMoved = tasksMoved;
        this.tasksTimedOutInWorkflow = tasksTimedOutInWorkflow;
        this.workspaceSid = workspaceSid;
        this.url = url;
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
     * Returns The The average time in seconds between Task creation and acceptance.
     *
     * @return The average time in seconds between Task creation and acceptance
     */
    public final Integer getAvgTaskAcceptanceTime() {
        return this.avgTaskAcceptanceTime;
    }

    /**
     * Returns The The beginning of the interval during which these statistics were
     * calculated.
     *
     * @return The beginning of the interval during which these statistics were
     *         calculated
     */
    public final ZonedDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns The The end of the interval during which these statistics were
     * calculated.
     *
     * @return The end of the interval during which these statistics were calculated
     */
    public final ZonedDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns The The total number of Reservations that were created for Workers.
     *
     * @return The total number of Reservations that were created for Workers
     */
    public final Integer getReservationsCreated() {
        return this.reservationsCreated;
    }

    /**
     * Returns The The total number of Reservations accepted by Workers.
     *
     * @return The total number of Reservations accepted by Workers
     */
    public final Integer getReservationsAccepted() {
        return this.reservationsAccepted;
    }

    /**
     * Returns The The total number of Reservations that were rejected.
     *
     * @return The total number of Reservations that were rejected
     */
    public final Integer getReservationsRejected() {
        return this.reservationsRejected;
    }

    /**
     * Returns The The total number of Reservations that were timed out.
     *
     * @return The total number of Reservations that were timed out
     */
    public final Integer getReservationsTimedOut() {
        return this.reservationsTimedOut;
    }

    /**
     * Returns The The total number of Reservations that were canceled.
     *
     * @return The total number of Reservations that were canceled
     */
    public final Integer getReservationsCanceled() {
        return this.reservationsCanceled;
    }

    /**
     * Returns The The total number of Reservations that were rescinded.
     *
     * @return The total number of Reservations that were rescinded
     */
    public final Integer getReservationsRescinded() {
        return this.reservationsRescinded;
    }

    /**
     * Returns The A list of objects that describe the Tasks canceled and
     * reservations accepted above and below the specified thresholds.
     *
     * @return A list of objects that describe the Tasks canceled and reservations
     *         accepted above and below the specified thresholds
     */
    public final Map<String, Object> getSplitByWaitTime() {
        return this.splitByWaitTime;
    }

    /**
     * Returns The The wait duration statistics for Tasks that were accepted.
     *
     * @return The wait duration statistics for Tasks that were accepted
     */
    public final Map<String, Object> getWaitDurationUntilAccepted() {
        return this.waitDurationUntilAccepted;
    }

    /**
     * Returns The The wait duration statistics for Tasks that were canceled.
     *
     * @return The wait duration statistics for Tasks that were canceled
     */
    public final Map<String, Object> getWaitDurationUntilCanceled() {
        return this.waitDurationUntilCanceled;
    }

    /**
     * Returns The The total number of Tasks that were canceled.
     *
     * @return The total number of Tasks that were canceled
     */
    public final Integer getTasksCanceled() {
        return this.tasksCanceled;
    }

    /**
     * Returns The The total number of Tasks that were completed.
     *
     * @return The total number of Tasks that were completed
     */
    public final Integer getTasksCompleted() {
        return this.tasksCompleted;
    }

    /**
     * Returns The The total number of Tasks created.
     *
     * @return The total number of Tasks created
     */
    public final Integer getTasksCreated() {
        return this.tasksCreated;
    }

    /**
     * Returns The The total number of Tasks that were deleted.
     *
     * @return The total number of Tasks that were deleted
     */
    public final Integer getTasksDeleted() {
        return this.tasksDeleted;
    }

    /**
     * Returns The The total number of Tasks that were moved from one queue to
     * another.
     *
     * @return The total number of Tasks that were moved from one queue to another
     */
    public final Integer getTasksMoved() {
        return this.tasksMoved;
    }

    /**
     * Returns The The total number of Tasks that were timed out of their Workflows.
     *
     * @return The total number of Tasks that were timed out of their Workflows
     */
    public final Integer getTasksTimedOutInWorkflow() {
        return this.tasksTimedOutInWorkflow;
    }

    /**
     * Returns The The SID of the Workspace.
     *
     * @return The SID of the Workspace
     */
    public final String getWorkspaceSid() {
        return this.workspaceSid;
    }

    /**
     * Returns The The absolute URL of the Workspace statistics resource.
     *
     * @return The absolute URL of the Workspace statistics resource
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

        WorkspaceCumulativeStatistics other = (WorkspaceCumulativeStatistics) o;

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
               Objects.equals(waitDurationUntilAccepted, other.waitDurationUntilAccepted) &&
               Objects.equals(waitDurationUntilCanceled, other.waitDurationUntilCanceled) &&
               Objects.equals(tasksCanceled, other.tasksCanceled) &&
               Objects.equals(tasksCompleted, other.tasksCompleted) &&
               Objects.equals(tasksCreated, other.tasksCreated) &&
               Objects.equals(tasksDeleted, other.tasksDeleted) &&
               Objects.equals(tasksMoved, other.tasksMoved) &&
               Objects.equals(tasksTimedOutInWorkflow, other.tasksTimedOutInWorkflow) &&
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
                            waitDurationUntilAccepted,
                            waitDurationUntilCanceled,
                            tasksCanceled,
                            tasksCompleted,
                            tasksCreated,
                            tasksDeleted,
                            tasksMoved,
                            tasksTimedOutInWorkflow,
                            workspaceSid,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("avgTaskAcceptanceTime", avgTaskAcceptanceTime)
                          .add("startTime", startTime)
                          .add("endTime", endTime)
                          .add("reservationsCreated", reservationsCreated)
                          .add("reservationsAccepted", reservationsAccepted)
                          .add("reservationsRejected", reservationsRejected)
                          .add("reservationsTimedOut", reservationsTimedOut)
                          .add("reservationsCanceled", reservationsCanceled)
                          .add("reservationsRescinded", reservationsRescinded)
                          .add("splitByWaitTime", splitByWaitTime)
                          .add("waitDurationUntilAccepted", waitDurationUntilAccepted)
                          .add("waitDurationUntilCanceled", waitDurationUntilCanceled)
                          .add("tasksCanceled", tasksCanceled)
                          .add("tasksCompleted", tasksCompleted)
                          .add("tasksCreated", tasksCreated)
                          .add("tasksDeleted", tasksDeleted)
                          .add("tasksMoved", tasksMoved)
                          .add("tasksTimedOutInWorkflow", tasksTimedOutInWorkflow)
                          .add("workspaceSid", workspaceSid)
                          .add("url", url)
                          .toString();
    }
}
