package com.twilio.sdk.resources.taskrouter.v1.workspace.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.taskrouter.v1.workspace.task.ReservationFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.taskrouter.v1.workspace.task.ReservationReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.taskrouter.v1.workspace.task.ReservationUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservation extends SidResource {
    private static final long serialVersionUID = 269071000200631L;

    /**
     * read
     * 
     * @param workspaceSid The workspace_sid
     * @param taskSid The task_sid
     * @return ReservationReader capable of executing the read
     */
    public static ReservationReader read(final String workspaceSid, final String taskSid) {
        return new ReservationReader(workspaceSid, taskSid);
    }

    /**
     * fetch
     * 
     * @param workspaceSid The workspace_sid
     * @param taskSid The task_sid
     * @param sid The sid
     * @return ReservationFetcher capable of executing the fetch
     */
    public static ReservationFetcher fetch(final String workspaceSid, final String taskSid, final String sid) {
        return new ReservationFetcher(workspaceSid, taskSid, sid);
    }

    /**
     * update
     * 
     * @param workspaceSid The workspace_sid
     * @param taskSid The task_sid
     * @param sid The sid
     * @param reservationStatus The reservation_status
     * @return ReservationUpdater capable of executing the update
     */
    public static ReservationUpdater update(final String workspaceSid, final String taskSid, final String sid, final String reservationStatus) {
        return new ReservationUpdater(workspaceSid, taskSid, sid, reservationStatus);
    }

    /**
     * Converts a JSON String into a Reservation object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Reservation object represented by the provided JSON
     */
    public static Reservation fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Reservation.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Reservation object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Reservation object represented by the provided JSON
     */
    public static Reservation fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Reservation.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String reservationStatus;
    private final String sid;
    private final String taskSid;
    private final String workerName;
    private final String workerSid;
    private final String workspaceSid;

    @JsonCreator
    private Reservation(@JsonProperty("account_sid")
                        final String accountSid, 
                        @JsonProperty("date_created")
                        final String dateCreated, 
                        @JsonProperty("date_updated")
                        final String dateUpdated, 
                        @JsonProperty("reservation_status")
                        final String reservationStatus, 
                        @JsonProperty("sid")
                        final String sid, 
                        @JsonProperty("task_sid")
                        final String taskSid, 
                        @JsonProperty("worker_name")
                        final String workerName, 
                        @JsonProperty("worker_sid")
                        final String workerSid, 
                        @JsonProperty("workspace_sid")
                        final String workspaceSid) {
        this.accountSid = accountSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.reservationStatus = reservationStatus;
        this.sid = sid;
        this.taskSid = taskSid;
        this.workerName = workerName;
        this.workerSid = workerSid;
        this.workspaceSid = workspaceSid;
    }

    /**
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return The reservation_status
     */
    public final String getReservationStatus() {
        return this.reservationStatus;
    }

    /**
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The task_sid
     */
    public final String getTaskSid() {
        return this.taskSid;
    }

    /**
     * @return The worker_name
     */
    public final String getWorkerName() {
        return this.workerName;
    }

    /**
     * @return The worker_sid
     */
    public final String getWorkerSid() {
        return this.workerSid;
    }

    /**
     * @return The workspace_sid
     */
    public final String getWorkspaceSid() {
        return this.workspaceSid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Reservation other = (Reservation) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(reservationStatus, other.reservationStatus) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(taskSid, other.taskSid) && 
               Objects.equals(workerName, other.workerName) && 
               Objects.equals(workerSid, other.workerSid) && 
               Objects.equals(workspaceSid, other.workspaceSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            reservationStatus,
                            sid,
                            taskSid,
                            workerName,
                            workerSid,
                            workspaceSid);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("reservationStatus", reservationStatus)
                          .add("sid", sid)
                          .add("taskSid", taskSid)
                          .add("workerName", workerName)
                          .add("workerSid", workerSid)
                          .add("workspaceSid", workspaceSid)
                          .toString();
    }
}