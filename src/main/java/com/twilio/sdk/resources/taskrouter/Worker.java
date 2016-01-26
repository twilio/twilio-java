package com.twilio.sdk.resources.taskrouter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.taskrouter.WorkerCreator;
import com.twilio.sdk.deleters.taskrouter.WorkerDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.taskrouter.WorkerFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.taskrouter.WorkerReader;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.taskrouter.WorkerUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker extends SidResource {
    private static final long serialVersionUID = 267403389002552L;

    /**
     * read
     * 
     * @param workspaceSid The workspace_sid
     * @return WorkerReader capable of executing the read
     */
    public static WorkerReader read(final String workspaceSid) {
        return new WorkerReader(workspaceSid);
    }

    /**
     * create
     * 
     * @param workspaceSid The workspace_sid
     * @param friendlyName The friendly_name
     * @return WorkerCreator capable of executing the create
     */
    public static WorkerCreator create(final String workspaceSid, final String friendlyName) {
        return new WorkerCreator(workspaceSid, friendlyName);
    }

    /**
     * fetch
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return WorkerFetcher capable of executing the fetch
     */
    public static WorkerFetcher fetch(final String workspaceSid, final String sid) {
        return new WorkerFetcher(workspaceSid, sid);
    }

    /**
     * update
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return WorkerUpdater capable of executing the update
     */
    public static WorkerUpdater update(final String workspaceSid, final String sid) {
        return new WorkerUpdater(workspaceSid, sid);
    }

    /**
     * delete
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return WorkerDeleter capable of executing the delete
     */
    public static WorkerDeleter delete(final String workspaceSid, final String sid) {
        return new WorkerDeleter(workspaceSid, sid);
    }

    /**
     * Converts a JSON String into a Worker object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Worker object represented by the provided JSON
     */
    public static Worker fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Worker.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Worker object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Worker object represented by the provided JSON
     */
    public static Worker fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Worker.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String activityName;
    private final String activitySid;
    private final String attributes;
    private final Boolean available;
    private final DateTime dateCreated;
    private final DateTime dateStatusChanged;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String sid;
    private final String workspaceSid;

    @JsonCreator
    private Worker(@JsonProperty("account_sid") final String accountSid, 
                   @JsonProperty("activity_name") final String activityName, 
                   @JsonProperty("activity_sid") final String activitySid, 
                   @JsonProperty("attributes") final String attributes, 
                   @JsonProperty("available") final Boolean available, 
                   @JsonProperty("date_created") final String dateCreated, 
                   @JsonProperty("date_status_changed") final String dateStatusChanged, 
                   @JsonProperty("date_updated") final String dateUpdated, 
                   @JsonProperty("friendly_name") final String friendlyName, 
                   @JsonProperty("sid") final String sid, 
                   @JsonProperty("workspace_sid") final String workspaceSid) {
        this.accountSid = accountSid;
        this.activityName = activityName;
        this.activitySid = activitySid;
        this.attributes = attributes;
        this.available = available;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateStatusChanged = MarshalConverter.dateTimeFromString(dateStatusChanged);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.sid = sid;
        this.workspaceSid = workspaceSid;
    }

    /**
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The activity_name
     */
    public final String getActivityName() {
        return this.activityName;
    }

    /**
     * @return The activity_sid
     */
    public final String getActivitySid() {
        return this.activitySid;
    }

    /**
     * @return The attributes
     */
    public final String getAttributes() {
        return this.attributes;
    }

    /**
     * @return The available
     */
    public final Boolean getAvailable() {
        return this.available;
    }

    /**
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date_status_changed
     */
    public final DateTime getDateStatusChanged() {
        return this.dateStatusChanged;
    }

    /**
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return The friendly_name
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
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
        
        Worker other = (Worker) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(activityName, other.activityName) && 
               Objects.equals(activitySid, other.activitySid) && 
               Objects.equals(attributes, other.attributes) && 
               Objects.equals(available, other.available) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateStatusChanged, other.dateStatusChanged) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(workspaceSid, other.workspaceSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            activityName,
                            activitySid,
                            attributes,
                            available,
                            dateCreated,
                            dateStatusChanged,
                            dateUpdated,
                            friendlyName,
                            sid,
                            workspaceSid);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("activityName", activityName)
                          .add("activitySid", activitySid)
                          .add("attributes", attributes)
                          .add("available", available)
                          .add("dateCreated", dateCreated)
                          .add("dateStatusChanged", dateStatusChanged)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("sid", sid)
                          .add("workspaceSid", workspaceSid)
                          .toString();
    }
}