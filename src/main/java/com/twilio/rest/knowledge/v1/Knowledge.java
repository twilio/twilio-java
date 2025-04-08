/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Knowledge
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.knowledge.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Knowledge extends Resource {

    private static final long serialVersionUID = 142704301669097L;

    @ToString
    public static class KnowledgeV1ServiceCreatePolicyRequest {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("description")
        @Getter
        @Setter
        private String description;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("id")
        @Getter
        @Setter
        private String id;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("name")
        @Getter
        @Setter
        private String name;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("policy_details")
        @Getter
        @Setter
        private Map<String, Object> policyDetails;

        public String getPolicyDetails() {
            return Converter.mapToJson(policyDetails);
        }

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("type")
        @Getter
        @Setter
        private String type;

        public static KnowledgeV1ServiceCreatePolicyRequest fromJson(
            String jsonString,
            ObjectMapper mapper
        ) throws IOException {
            return mapper.readValue(
                jsonString,
                KnowledgeV1ServiceCreatePolicyRequest.class
            );
        }
    }

    @ToString
    public static class KnowledgeV1ServiceCreateKnowledgeRequest {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("description")
        @Getter
        @Setter
        private String description;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("knowledge_source_details")
        @Getter
        @Setter
        private Map<String, Object> knowledgeSourceDetails;

        public String getKnowledgeSourceDetails() {
            return Converter.mapToJson(knowledgeSourceDetails);
        }

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("name")
        @Getter
        @Setter
        private String name;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("policy")
        @Getter
        @Setter
        private KnowledgeV1ServiceCreatePolicyRequest policy;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("type")
        @Getter
        @Setter
        private String type;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("embedding_model")
        @Getter
        @Setter
        private String embeddingModel;

        public KnowledgeV1ServiceCreateKnowledgeRequest() {}

        public static KnowledgeV1ServiceCreateKnowledgeRequest fromJson(
            String jsonString,
            ObjectMapper mapper
        ) throws IOException {
            return mapper.readValue(
                jsonString,
                KnowledgeV1ServiceCreateKnowledgeRequest.class
            );
        }
    }

    @ToString
    public static class KnowledgeV1ServiceUpdateKnowledgeRequest {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("description")
        @Getter
        @Setter
        private String description;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("knowledge_source_details")
        @Getter
        @Setter
        private Map<String, Object> knowledgeSourceDetails;

        public String getKnowledgeSourceDetails() {
            return Converter.mapToJson(knowledgeSourceDetails);
        }

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("name")
        @Getter
        @Setter
        private String name;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("policy")
        @Getter
        @Setter
        private KnowledgeV1ServiceCreatePolicyRequest policy;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("type")
        @Getter
        @Setter
        private String type;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("embedding_model")
        @Getter
        @Setter
        private String embeddingModel;

        public KnowledgeV1ServiceUpdateKnowledgeRequest() {}

        public static KnowledgeV1ServiceUpdateKnowledgeRequest fromJson(
            String jsonString,
            ObjectMapper mapper
        ) throws IOException {
            return mapper.readValue(
                jsonString,
                KnowledgeV1ServiceUpdateKnowledgeRequest.class
            );
        }
    }

    public static KnowledgeCreator creator(
        final Knowledge.KnowledgeV1ServiceCreateKnowledgeRequest knowledgeV1ServiceCreateKnowledgeRequest
    ) {
        return new KnowledgeCreator(knowledgeV1ServiceCreateKnowledgeRequest);
    }

    public static KnowledgeDeleter deleter(final String pathId) {
        return new KnowledgeDeleter(pathId);
    }

    public static KnowledgeFetcher fetcher(final String pathId) {
        return new KnowledgeFetcher(pathId);
    }

    public static KnowledgeReader reader() {
        return new KnowledgeReader();
    }

    public static KnowledgeUpdater updater(final String pathId) {
        return new KnowledgeUpdater(pathId);
    }

    /**
     * Converts a JSON String into a Knowledge object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Knowledge object represented by the provided JSON
     */
    public static Knowledge fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Knowledge.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Knowledge object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Knowledge object represented by the provided JSON
     */
    public static Knowledge fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Knowledge.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static String toJson(Object object, ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(object);
        } catch (final JsonMappingException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (JsonProcessingException e) {
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
    private Knowledge(
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

        Knowledge other = (Knowledge) o;

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
