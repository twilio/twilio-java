package com.twilio.sdk.resources.taskrouter.v1.workspace;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.taskrouter.v1.workspace.WorkspaceStatisticsFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkspaceStatistics extends Resource {
    private static final long serialVersionUID = 176548291123699L;

    /**
     * Create a WorkspaceStatisticsFetcher to execute fetch.
     * 
     * @param workspaceSid The workspace_sid
     * @return WorkspaceStatisticsFetcher capable of executing the fetch
     */
    public static WorkspaceStatisticsFetcher fetch(final String workspaceSid) {
        return new WorkspaceStatisticsFetcher(workspaceSid);
    }

    /**
     * Converts a JSON String into a WorkspaceStatistics object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return WorkspaceStatistics object represented by the provided JSON
     */
    public static WorkspaceStatistics fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, WorkspaceStatistics.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a WorkspaceStatistics object using the
     * provided ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return WorkspaceStatistics object represented by the provided JSON
     */
    public static WorkspaceStatistics fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, WorkspaceStatistics.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final JsonNode realtime;
    private final JsonNode cumulative;
    private final String accountSid;
    private final String workspaceSid;

    @JsonCreator
    private WorkspaceStatistics(@JsonProperty("realtime")
                                final JsonNode realtime, 
                                @JsonProperty("cumulative")
                                final JsonNode cumulative, 
                                @JsonProperty("account_sid")
                                final String accountSid, 
                                @JsonProperty("workspace_sid")
                                final String workspaceSid) {
        this.realtime = realtime;
        this.cumulative = cumulative;
        this.accountSid = accountSid;
        this.workspaceSid = workspaceSid;
    }

    /**
     * Returns The The realtime.
     * 
     * @return The realtime
     */
    public final JsonNode getRealtime() {
        return this.realtime;
    }

    /**
     * Returns The The cumulative.
     * 
     * @return The cumulative
     */
    public final JsonNode getCumulative() {
        return this.cumulative;
    }

    /**
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The workspace_sid.
     * 
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
        
        WorkspaceStatistics other = (WorkspaceStatistics) o;
        
        return Objects.equals(realtime, other.realtime) && 
               Objects.equals(cumulative, other.cumulative) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(workspaceSid, other.workspaceSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realtime,
                            cumulative,
                            accountSid,
                            workspaceSid);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("realtime", realtime)
                          .add("cumulative", cumulative)
                          .add("accountSid", accountSid)
                          .add("workspaceSid", workspaceSid)
                          .toString();
    }
}