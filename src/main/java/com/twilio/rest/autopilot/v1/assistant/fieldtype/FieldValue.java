/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1.assistant.fieldtype;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldValue extends Resource {
    private static final long serialVersionUID = 196387084273361L;

    /**
     * Create a FieldValueFetcher to execute fetch.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         FieldType associated with the resource to fetch
     * @param pathFieldTypeSid The SID of the Field Type associated with  the Field
     *                         Value to fetch
     * @param pathSid The unique string that identifies the resource
     * @return FieldValueFetcher capable of executing the fetch
     */
    public static FieldValueFetcher fetcher(final String pathAssistantSid,
                                            final String pathFieldTypeSid,
                                            final String pathSid) {
        return new FieldValueFetcher(pathAssistantSid, pathFieldTypeSid, pathSid);
    }

    /**
     * Create a FieldValueReader to execute read.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         FieldType associated with the resources to read
     * @param pathFieldTypeSid The SID of the Field Type associated with the Field
     *                         Value to read
     * @return FieldValueReader capable of executing the read
     */
    public static FieldValueReader reader(final String pathAssistantSid,
                                          final String pathFieldTypeSid) {
        return new FieldValueReader(pathAssistantSid, pathFieldTypeSid);
    }

    /**
     * Create a FieldValueCreator to execute create.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         FieldType associated with the new resource
     * @param pathFieldTypeSid The SID of the Field Type associated with the Field
     *                         Value
     * @param language The ISO language-country tag that identifies the language of
     *                 the value
     * @param value The Field Value data
     * @return FieldValueCreator capable of executing the create
     */
    public static FieldValueCreator creator(final String pathAssistantSid,
                                            final String pathFieldTypeSid,
                                            final String language,
                                            final String value) {
        return new FieldValueCreator(pathAssistantSid, pathFieldTypeSid, language, value);
    }

    /**
     * Create a FieldValueDeleter to execute delete.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         FieldType associated with the resources to delete
     * @param pathFieldTypeSid The SID of the Field Type associated with the Field
     *                         Value to delete
     * @param pathSid The unique string that identifies the resource
     * @return FieldValueDeleter capable of executing the delete
     */
    public static FieldValueDeleter deleter(final String pathAssistantSid,
                                            final String pathFieldTypeSid,
                                            final String pathSid) {
        return new FieldValueDeleter(pathAssistantSid, pathFieldTypeSid, pathSid);
    }

    /**
     * Converts a JSON String into a FieldValue object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return FieldValue object represented by the provided JSON
     */
    public static FieldValue fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FieldValue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a FieldValue object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return FieldValue object represented by the provided JSON
     */
    public static FieldValue fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FieldValue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String fieldTypeSid;
    private final String language;
    private final String assistantSid;
    private final String sid;
    private final String value;
    private final URI url;
    private final String synonymOf;

    @JsonCreator
    private FieldValue(@JsonProperty("account_sid")
                       final String accountSid,
                       @JsonProperty("date_created")
                       final String dateCreated,
                       @JsonProperty("date_updated")
                       final String dateUpdated,
                       @JsonProperty("field_type_sid")
                       final String fieldTypeSid,
                       @JsonProperty("language")
                       final String language,
                       @JsonProperty("assistant_sid")
                       final String assistantSid,
                       @JsonProperty("sid")
                       final String sid,
                       @JsonProperty("value")
                       final String value,
                       @JsonProperty("url")
                       final URI url,
                       @JsonProperty("synonym_of")
                       final String synonymOf) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.fieldTypeSid = fieldTypeSid;
        this.language = language;
        this.assistantSid = assistantSid;
        this.sid = sid;
        this.value = value;
        this.url = url;
        this.synonymOf = synonymOf;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was created.
     *
     * @return The RFC 2822 date and time in GMT when the resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was last updated.
     *
     * @return The RFC 2822 date and time in GMT when the resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The SID of the Field Type associated with the Field Value.
     *
     * @return The SID of the Field Type associated with the Field Value
     */
    public final String getFieldTypeSid() {
        return this.fieldTypeSid;
    }

    /**
     * Returns The ISO language-country tag that identifies the language of the
     * value.
     *
     * @return The ISO language-country tag that identifies the language of the
     *         value
     */
    public final String getLanguage() {
        return this.language;
    }

    /**
     * Returns The SID of the Assistant that is the parent of the FieldType
     * associated with the resource.
     *
     * @return The SID of the Assistant that is the parent of the FieldType
     *         associated with the resource
     */
    public final String getAssistantSid() {
        return this.assistantSid;
    }

    /**
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The Field Value data.
     *
     * @return The Field Value data
     */
    public final String getValue() {
        return this.value;
    }

    /**
     * Returns The absolute URL of the FieldValue resource.
     *
     * @return The absolute URL of the FieldValue resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The word for which the field value is a synonym of.
     *
     * @return The word for which the field value is a synonym of
     */
    public final String getSynonymOf() {
        return this.synonymOf;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FieldValue other = (FieldValue) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(fieldTypeSid, other.fieldTypeSid) &&
               Objects.equals(language, other.language) &&
               Objects.equals(assistantSid, other.assistantSid) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(value, other.value) &&
               Objects.equals(url, other.url) &&
               Objects.equals(synonymOf, other.synonymOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            fieldTypeSid,
                            language,
                            assistantSid,
                            sid,
                            value,
                            url,
                            synonymOf);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("fieldTypeSid", fieldTypeSid)
                          .add("language", language)
                          .add("assistantSid", assistantSid)
                          .add("sid", sid)
                          .add("value", value)
                          .add("url", url)
                          .add("synonymOf", synonymOf)
                          .toString();
    }
}