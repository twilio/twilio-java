/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Preview
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.preview.understand.assistant.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Field extends Resource {

    private static final long serialVersionUID = 118531839027400L;

    public static FieldCreator creator(
        final String pathAssistantSid,
        final String pathTaskSid,
        final String fieldType,
        final String uniqueName
    ) {
        return new FieldCreator(
            pathAssistantSid,
            pathTaskSid,
            fieldType,
            uniqueName
        );
    }

    public static FieldDeleter deleter(
        final String pathAssistantSid,
        final String pathTaskSid,
        final String pathSid
    ) {
        return new FieldDeleter(pathAssistantSid, pathTaskSid, pathSid);
    }

    public static FieldFetcher fetcher(
        final String pathAssistantSid,
        final String pathTaskSid,
        final String pathSid
    ) {
        return new FieldFetcher(pathAssistantSid, pathTaskSid, pathSid);
    }

    public static FieldReader reader(
        final String pathAssistantSid,
        final String pathTaskSid
    ) {
        return new FieldReader(pathAssistantSid, pathTaskSid);
    }

    /**
     * Converts a JSON String into a Field object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Field object represented by the provided JSON
     */
    public static Field fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Field.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Field object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Field object represented by the provided JSON
     */
    public static Field fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Field.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String fieldType;
    private final String taskSid;
    private final String assistantSid;
    private final String sid;
    private final String uniqueName;
    private final URI url;

    @JsonCreator
    private Field(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("field_type") final String fieldType,
        @JsonProperty("task_sid") final String taskSid,
        @JsonProperty("assistant_sid") final String assistantSid,
        @JsonProperty("sid") final String sid,
        @JsonProperty("unique_name") final String uniqueName,
        @JsonProperty("url") final URI url
    ) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.fieldType = fieldType;
        this.taskSid = taskSid;
        this.assistantSid = assistantSid;
        this.sid = sid;
        this.uniqueName = uniqueName;
        this.url = url;
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

    public final String getFieldType() {
        return this.fieldType;
    }

    public final String getTaskSid() {
        return this.taskSid;
    }

    public final String getAssistantSid() {
        return this.assistantSid;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getUniqueName() {
        return this.uniqueName;
    }

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

        Field other = (Field) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(fieldType, other.fieldType) &&
            Objects.equals(taskSid, other.taskSid) &&
            Objects.equals(assistantSid, other.assistantSid) &&
            Objects.equals(sid, other.sid) &&
            Objects.equals(uniqueName, other.uniqueName) &&
            Objects.equals(url, other.url)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
            dateCreated,
            dateUpdated,
            fieldType,
            taskSid,
            assistantSid,
            sid,
            uniqueName,
            url
        );
    }
}
