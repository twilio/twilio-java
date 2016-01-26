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
import com.twilio.sdk.creators.taskrouter.WorkflowCreator;
import com.twilio.sdk.deleters.taskrouter.WorkflowDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.taskrouter.WorkflowFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.taskrouter.WorkflowReader;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.taskrouter.WorkflowUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Workflow extends SidResource {
    private static final long serialVersionUID = 45221576687968L;

    /**
     * fetch
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return WorkflowFetcher capable of executing the fetch
     */
    public static WorkflowFetcher fetch(final String workspaceSid, final String sid) {
        return new WorkflowFetcher(workspaceSid, sid);
    }

    /**
     * update
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return WorkflowUpdater capable of executing the update
     */
    public static WorkflowUpdater update(final String workspaceSid, final String sid) {
        return new WorkflowUpdater(workspaceSid, sid);
    }

    /**
     * delete
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return WorkflowDeleter capable of executing the delete
     */
    public static WorkflowDeleter delete(final String workspaceSid, final String sid) {
        return new WorkflowDeleter(workspaceSid, sid);
    }

    /**
     * read
     * 
     * @param workspaceSid The workspace_sid
     * @return WorkflowReader capable of executing the read
     */
    public static WorkflowReader read(final String workspaceSid) {
        return new WorkflowReader(workspaceSid);
    }

    /**
     * create
     * 
     * @param workspaceSid The workspace_sid
     * @param friendlyName The friendly_name
     * @param configuration The configuration
     * @param assignmentCallbackUrl The assignment_callback_url
     * @return WorkflowCreator capable of executing the create
     */
    public static WorkflowCreator create(final String workspaceSid, final String friendlyName, final String configuration, final String assignmentCallbackUrl) {
        return new WorkflowCreator(workspaceSid, friendlyName, configuration, assignmentCallbackUrl);
    }

    /**
     * Converts a JSON String into a Workflow object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Workflow object represented by the provided JSON
     */
    public static Workflow fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Workflow.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Workflow object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Workflow object represented by the provided JSON
     */
    public static Workflow fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Workflow.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String assignmentCallbackUrl;
    private final String configuration;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String documentContentType;
    private final String fallbackAssignmentCallbackUrl;
    private final String friendlyName;
    private final String sid;
    private final Integer taskReservationTimeout;
    private final String workspaceSid;

    @JsonCreator
    private Workflow(@JsonProperty("account_sid") final String accountSid, 
                     @JsonProperty("assignment_callback_url") final String assignmentCallbackUrl, 
                     @JsonProperty("configuration") final String configuration, 
                     @JsonProperty("date_created") final String dateCreated, 
                     @JsonProperty("date_updated") final String dateUpdated, 
                     @JsonProperty("document_content_type") final String documentContentType, 
                     @JsonProperty("fallback_assignment_callback_url") final String fallbackAssignmentCallbackUrl, 
                     @JsonProperty("friendly_name") final String friendlyName, 
                     @JsonProperty("sid") final String sid, 
                     @JsonProperty("task_reservation_timeout") final Integer taskReservationTimeout, 
                     @JsonProperty("workspace_sid") final String workspaceSid) {
        this.accountSid = accountSid;
        this.assignmentCallbackUrl = assignmentCallbackUrl;
        this.configuration = configuration;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.documentContentType = documentContentType;
        this.fallbackAssignmentCallbackUrl = fallbackAssignmentCallbackUrl;
        this.friendlyName = friendlyName;
        this.sid = sid;
        this.taskReservationTimeout = taskReservationTimeout;
        this.workspaceSid = workspaceSid;
    }

    /**
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The assignment_callback_url
     */
    public final String getAssignmentCallbackUrl() {
        return this.assignmentCallbackUrl;
    }

    /**
     * @return The configuration
     */
    public final String getConfiguration() {
        return this.configuration;
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
     * @return The document_content_type
     */
    public final String getDocumentContentType() {
        return this.documentContentType;
    }

    /**
     * @return The fallback_assignment_callback_url
     */
    public final String getFallbackAssignmentCallbackUrl() {
        return this.fallbackAssignmentCallbackUrl;
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
     * @return The task_reservation_timeout
     */
    public final Integer getTaskReservationTimeout() {
        return this.taskReservationTimeout;
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
        
        Workflow other = (Workflow) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(assignmentCallbackUrl, other.assignmentCallbackUrl) && 
               Objects.equals(configuration, other.configuration) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(documentContentType, other.documentContentType) && 
               Objects.equals(fallbackAssignmentCallbackUrl, other.fallbackAssignmentCallbackUrl) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(taskReservationTimeout, other.taskReservationTimeout) && 
               Objects.equals(workspaceSid, other.workspaceSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            assignmentCallbackUrl,
                            configuration,
                            dateCreated,
                            dateUpdated,
                            documentContentType,
                            fallbackAssignmentCallbackUrl,
                            friendlyName,
                            sid,
                            taskReservationTimeout,
                            workspaceSid);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("assignmentCallbackUrl", assignmentCallbackUrl)
                          .add("configuration", configuration)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("documentContentType", documentContentType)
                          .add("fallbackAssignmentCallbackUrl", fallbackAssignmentCallbackUrl)
                          .add("friendlyName", friendlyName)
                          .add("sid", sid)
                          .add("taskReservationTimeout", taskReservationTimeout)
                          .add("workspaceSid", workspaceSid)
                          .toString();
    }
}