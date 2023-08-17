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

package com.twilio.rest.preview.understand.assistant;

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
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Query extends Resource {

    private static final long serialVersionUID = 180519234688811L;

    public static QueryCreator creator(
        final String pathAssistantSid,
        final String language,
        final String query
    ) {
        return new QueryCreator(pathAssistantSid, language, query);
    }

    public static QueryDeleter deleter(
        final String pathAssistantSid,
        final String pathSid
    ) {
        return new QueryDeleter(pathAssistantSid, pathSid);
    }

    public static QueryFetcher fetcher(
        final String pathAssistantSid,
        final String pathSid
    ) {
        return new QueryFetcher(pathAssistantSid, pathSid);
    }

    public static QueryReader reader(final String pathAssistantSid) {
        return new QueryReader(pathAssistantSid);
    }

    public static QueryUpdater updater(
        final String pathAssistantSid,
        final String pathSid
    ) {
        return new QueryUpdater(pathAssistantSid, pathSid);
    }

    /**
     * Converts a JSON String into a Query object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Query object represented by the provided JSON
     */
    public static Query fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Query.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Query object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Query object represented by the provided JSON
     */
    public static Query fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Query.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final Map<String, Object> results;
    private final String language;
    private final String modelBuildSid;
    private final String query;
    private final String sampleSid;
    private final String assistantSid;
    private final String sid;
    private final String status;
    private final URI url;
    private final String sourceChannel;

    @JsonCreator
    private Query(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("results") final Map<String, Object> results,
        @JsonProperty("language") final String language,
        @JsonProperty("model_build_sid") final String modelBuildSid,
        @JsonProperty("query") final String query,
        @JsonProperty("sample_sid") final String sampleSid,
        @JsonProperty("assistant_sid") final String assistantSid,
        @JsonProperty("sid") final String sid,
        @JsonProperty("status") final String status,
        @JsonProperty("url") final URI url,
        @JsonProperty("source_channel") final String sourceChannel
    ) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.results = results;
        this.language = language;
        this.modelBuildSid = modelBuildSid;
        this.query = query;
        this.sampleSid = sampleSid;
        this.assistantSid = assistantSid;
        this.sid = sid;
        this.status = status;
        this.url = url;
        this.sourceChannel = sourceChannel;
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

    public final Map<String, Object> getResults() {
        return this.results;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getModelBuildSid() {
        return this.modelBuildSid;
    }

    public final String getQuery() {
        return this.query;
    }

    public final String getSampleSid() {
        return this.sampleSid;
    }

    public final String getAssistantSid() {
        return this.assistantSid;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getStatus() {
        return this.status;
    }

    public final URI getUrl() {
        return this.url;
    }

    public final String getSourceChannel() {
        return this.sourceChannel;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Query other = (Query) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(results, other.results) &&
            Objects.equals(language, other.language) &&
            Objects.equals(modelBuildSid, other.modelBuildSid) &&
            Objects.equals(query, other.query) &&
            Objects.equals(sampleSid, other.sampleSid) &&
            Objects.equals(assistantSid, other.assistantSid) &&
            Objects.equals(sid, other.sid) &&
            Objects.equals(status, other.status) &&
            Objects.equals(url, other.url) &&
            Objects.equals(sourceChannel, other.sourceChannel)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
            dateCreated,
            dateUpdated,
            results,
            language,
            modelBuildSid,
            query,
            sampleSid,
            assistantSid,
            sid,
            status,
            url,
            sourceChannel
        );
    }
}
