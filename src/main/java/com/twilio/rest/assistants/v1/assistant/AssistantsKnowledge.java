/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Assistants
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.assistants.v1.assistant;

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
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AssistantsKnowledge extends Resource {

    private static final long serialVersionUID = 142704301669097L;

    public static AssistantsKnowledgeCreator creator(
        final String pathAssistantId,
        final String pathId
    ) {
        return new AssistantsKnowledgeCreator(pathAssistantId, pathId);
    }

    public static AssistantsKnowledgeDeleter deleter(
        final String pathAssistantId,
        final String pathId
    ) {
        return new AssistantsKnowledgeDeleter(pathAssistantId, pathId);
    }

    public static AssistantsKnowledgeReader reader(
        final String pathAssistantId
    ) {
        return new AssistantsKnowledgeReader(pathAssistantId);
    }

    /**
     * Converts a JSON String into a AssistantsKnowledge object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return AssistantsKnowledge object represented by the provided JSON
     */
    public static AssistantsKnowledge fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AssistantsKnowledge.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a AssistantsKnowledge object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return AssistantsKnowledge object represented by the provided JSON
     */
    public static AssistantsKnowledge fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AssistantsKnowledge.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String description;
    private final String id;
    private final String accountSid;
    private final Map<String, Object> knowledgeSourceDetails;
    private final String name;
    private final String status;
    private final String type;
    private final String url;
    private final String embeddingModel;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;

    @JsonCreator
    private AssistantsKnowledge(
        @JsonProperty("description") final String description,
        @JsonProperty("id") final String id,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("knowledge_source_details") final Map<
            String,
            Object
        > knowledgeSourceDetails,
        @JsonProperty("name") final String name,
        @JsonProperty("status") final String status,
        @JsonProperty("type") final String type,
        @JsonProperty("url") final String url,
        @JsonProperty("embedding_model") final String embeddingModel,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated
    ) {
        this.description = description;
        this.id = id;
        this.accountSid = accountSid;
        this.knowledgeSourceDetails = knowledgeSourceDetails;
        this.name = name;
        this.status = status;
        this.type = type;
        this.url = url;
        this.embeddingModel = embeddingModel;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final Map<String, Object> getKnowledgeSourceDetails() {
        return this.knowledgeSourceDetails;
    }

    public final String getName() {
        return this.name;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getEmbeddingModel() {
        return this.embeddingModel;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssistantsKnowledge other = (AssistantsKnowledge) o;

        return (
            Objects.equals(description, other.description) &&
            Objects.equals(id, other.id) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(
                knowledgeSourceDetails,
                other.knowledgeSourceDetails
            ) &&
            Objects.equals(name, other.name) &&
            Objects.equals(status, other.status) &&
            Objects.equals(type, other.type) &&
            Objects.equals(url, other.url) &&
            Objects.equals(embeddingModel, other.embeddingModel) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            description,
            id,
            accountSid,
            knowledgeSourceDetails,
            name,
            status,
            type,
            url,
            embeddingModel,
            dateCreated,
            dateUpdated
        );
    }
}
