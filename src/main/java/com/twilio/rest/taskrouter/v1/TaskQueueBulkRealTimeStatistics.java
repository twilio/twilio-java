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
import com.twilio.exception.ApiConnectionException;

import com.twilio.exception.ApiException;

import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.ToString;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class TaskQueueBulkRealTimeStatistics extends Resource {
    private static final long serialVersionUID = 187525220896144L;

    

    public static TaskQueueBulkRealTimeStatisticsCreator creator(final String pathWorkspaceSid){
        return new TaskQueueBulkRealTimeStatisticsCreator(pathWorkspaceSid);
    }

    /**
    * Converts a JSON String into a TaskQueueBulkRealTimeStatistics object using the provided ObjectMapper.
    *
    * @param json Raw JSON String
    * @param objectMapper Jackson ObjectMapper
    * @return TaskQueueBulkRealTimeStatistics object represented by the provided JSON
    */
    public static TaskQueueBulkRealTimeStatistics fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskQueueBulkRealTimeStatistics.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
    * Converts a JSON InputStream into a TaskQueueBulkRealTimeStatistics object using the provided
    * ObjectMapper.
    *
    * @param json Raw JSON InputStream
    * @param objectMapper Jackson ObjectMapper
    * @return TaskQueueBulkRealTimeStatistics object represented by the provided JSON
    */
    public static TaskQueueBulkRealTimeStatistics fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskQueueBulkRealTimeStatistics.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }


    private final String accountSid;
    private final String workspaceSid;
    private final List<Map<String, Object>> taskQueueData;
    private final Integer taskQueueResponseCount;
    private final URI url;

    @JsonCreator
    private TaskQueueBulkRealTimeStatistics(
        @JsonProperty("account_sid")
        final String accountSid,

        @JsonProperty("workspace_sid")
        final String workspaceSid,

        @JsonProperty("task_queue_data")
        final List<Map<String, Object>> taskQueueData,

        @JsonProperty("task_queue_response_count")
        final Integer taskQueueResponseCount,

        @JsonProperty("url")
        final URI url
    ) {
        this.accountSid = accountSid;
        this.workspaceSid = workspaceSid;
        this.taskQueueData = taskQueueData;
        this.taskQueueResponseCount = taskQueueResponseCount;
        this.url = url;
    }

        public final String getAccountSid() {
            return this.accountSid;
        }
        public final String getWorkspaceSid() {
            return this.workspaceSid;
        }
        public final List<Map<String, Object>> getTaskQueueData() {
            return this.taskQueueData;
        }
        public final Integer getTaskQueueResponseCount() {
            return this.taskQueueResponseCount;
        }
        public final URI getUrl() {
            return this.url;
        }

    @Override
    public boolean equals(final Object o) {
        if (this==o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskQueueBulkRealTimeStatistics other = (TaskQueueBulkRealTimeStatistics) o;

        return Objects.equals(accountSid, other.accountSid) &&  Objects.equals(workspaceSid, other.workspaceSid) &&  Objects.equals(taskQueueData, other.taskQueueData) &&  Objects.equals(taskQueueResponseCount, other.taskQueueResponseCount) &&  Objects.equals(url, other.url)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid, workspaceSid, taskQueueData, taskQueueResponseCount, url);
    }


}

