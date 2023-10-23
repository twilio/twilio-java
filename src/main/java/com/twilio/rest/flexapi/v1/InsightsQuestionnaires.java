/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Flex
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.flexapi.v1;

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
public class InsightsQuestionnaires extends Resource {
    private static final long serialVersionUID = 101221063563372L;

    

    public static InsightsQuestionnairesCreator creator(final String name){
        return new InsightsQuestionnairesCreator(name);
    }

    public static InsightsQuestionnairesDeleter deleter(final String pathQuestionnaireSid){
        return new InsightsQuestionnairesDeleter(pathQuestionnaireSid);
    }

    public static InsightsQuestionnairesFetcher fetcher(final String pathQuestionnaireSid){
        return new InsightsQuestionnairesFetcher(pathQuestionnaireSid);
    }

    public static InsightsQuestionnairesReader reader(){
        return new InsightsQuestionnairesReader();
    }

    public static InsightsQuestionnairesUpdater updater(final String pathQuestionnaireSid, final Boolean active){
        return new InsightsQuestionnairesUpdater(pathQuestionnaireSid, active);
    }

    /**
    * Converts a JSON String into a InsightsQuestionnaires object using the provided ObjectMapper.
    *
    * @param json Raw JSON String
    * @param objectMapper Jackson ObjectMapper
    * @return InsightsQuestionnaires object represented by the provided JSON
    */
    public static InsightsQuestionnaires fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, InsightsQuestionnaires.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
    * Converts a JSON InputStream into a InsightsQuestionnaires object using the provided
    * ObjectMapper.
    *
    * @param json Raw JSON InputStream
    * @param objectMapper Jackson ObjectMapper
    * @return InsightsQuestionnaires object represented by the provided JSON
    */
    public static InsightsQuestionnaires fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, InsightsQuestionnaires.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }


    private final String accountSid;
    private final String questionnaireSid;
    private final String name;
    private final String description;
    private final Boolean active;
    private final List<Map<String, Object>> questions;
    private final URI url;

    @JsonCreator
    private InsightsQuestionnaires(
        @JsonProperty("account_sid")
        final String accountSid,

        @JsonProperty("questionnaire_sid")
        final String questionnaireSid,

        @JsonProperty("name")
        final String name,

        @JsonProperty("description")
        final String description,

        @JsonProperty("active")
        final Boolean active,

        @JsonProperty("questions")
        final List<Map<String, Object>> questions,

        @JsonProperty("url")
        final URI url
    ) {
        this.accountSid = accountSid;
        this.questionnaireSid = questionnaireSid;
        this.name = name;
        this.description = description;
        this.active = active;
        this.questions = questions;
        this.url = url;
    }

        public final String getAccountSid() {
            return this.accountSid;
        }
        public final String getQuestionnaireSid() {
            return this.questionnaireSid;
        }
        public final String getName() {
            return this.name;
        }
        public final String getDescription() {
            return this.description;
        }
        public final Boolean getActive() {
            return this.active;
        }
        public final List<Map<String, Object>> getQuestions() {
            return this.questions;
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

        InsightsQuestionnaires other = (InsightsQuestionnaires) o;

        return Objects.equals(accountSid, other.accountSid) &&  Objects.equals(questionnaireSid, other.questionnaireSid) &&  Objects.equals(name, other.name) &&  Objects.equals(description, other.description) &&  Objects.equals(active, other.active) &&  Objects.equals(questions, other.questions) &&  Objects.equals(url, other.url)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid, questionnaireSid, name, description, active, questions, url);
    }


}

