/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Organization Public API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.previewiam.organizations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.bearertoken.Resource;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class User extends Resource {

    private static final long serialVersionUID = 281207691925540L;

    @ToString
    public static class ScimName {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("givenName")
        @Getter
        @Setter
        private String givenName;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("familyName")
        @Getter
        @Setter
        private String familyName;

        public static ScimName fromJson(String jsonString, ObjectMapper mapper)
            throws IOException {
            return mapper.readValue(jsonString, ScimName.class);
        }
    }

    @ToString
    public static class ScimEmailAddress {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("primary")
        @Getter
        @Setter
        private Boolean primary;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("value")
        @Getter
        @Setter
        private String value;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("type")
        @Getter
        @Setter
        private String type;

        public static ScimEmailAddress fromJson(
            String jsonString,
            ObjectMapper mapper
        ) throws IOException {
            return mapper.readValue(jsonString, ScimEmailAddress.class);
        }
    }

    @ToString
    public static class ScimMeta {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("resourceType")
        @Getter
        @Setter
        private String resourceType;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("created")
        @Getter
        @Setter
        private ZonedDateTime created;

        public String getCreated() {
            return created.toInstant().toString();
        }

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("lastModified")
        @Getter
        @Setter
        private ZonedDateTime lastModified;

        public String getLastModified() {
            return lastModified.toInstant().toString();
        }

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("version")
        @Getter
        @Setter
        private String version;

        public static ScimMeta fromJson(String jsonString, ObjectMapper mapper)
            throws IOException {
            return mapper.readValue(jsonString, ScimMeta.class);
        }
    }

    @ToString
    public static class ScimUser {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("id")
        @Getter
        @Setter
        private String id;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("externalId")
        @Getter
        @Setter
        private String externalId;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("userName")
        @Getter
        @Setter
        private String userName;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("displayName")
        @Getter
        @Setter
        private String displayName;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("name")
        @Getter
        @Setter
        private ScimName name;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("emails")
        @Getter
        @Setter
        private List<ScimEmailAddress> emails;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("active")
        @Getter
        @Setter
        private Boolean active;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("locale")
        @Getter
        @Setter
        private String locale;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("timezone")
        @Getter
        @Setter
        private String timezone;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("schemas")
        @Getter
        @Setter
        private List<String> schemas;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("meta")
        @Getter
        @Setter
        private ScimMeta meta;

        public ScimUser(final String userName) {
            this.userName = userName;
        }

        public static ScimUser fromJson(String jsonString, ObjectMapper mapper)
            throws IOException {
            return mapper.readValue(jsonString, ScimUser.class);
        }
    }

    public static UserCreator creator(
        final String pathOrganizationSid,
        final User.ScimUser scimUser
    ) {
        return new UserCreator(pathOrganizationSid, scimUser);
    }

    public static UserDeleter deleter(
        final String pathOrganizationSid,
        final String pathUserSid
    ) {
        return new UserDeleter(pathOrganizationSid, pathUserSid);
    }

    public static UserFetcher fetcher(
        final String pathOrganizationSid,
        final String pathUserSid
    ) {
        return new UserFetcher(pathOrganizationSid, pathUserSid);
    }

    public static UserReader reader(final String pathOrganizationSid) {
        return new UserReader(pathOrganizationSid);
    }

    public static UserUpdater updater(
        final String pathOrganizationSid,
        final String pathUserSid,
        final User.ScimUser scimUser
    ) {
        return new UserUpdater(pathOrganizationSid, pathUserSid, scimUser);
    }

    /**
     * Converts a JSON String into a User object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return User object represented by the provided JSON
     */
    public static User fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, User.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a User object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return User object represented by the provided JSON
     */
    public static User fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, User.class);
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

    private final String id;
    private final String externalId;
    private final String userName;
    private final String displayName;
    private final ScimName name;
    private final List<ScimEmailAddress> emails;
    private final Boolean active;
    private final String locale;
    private final String timezone;
    private final List<String> schemas;
    private final ScimMeta meta;

    @JsonCreator
    private User(
        @JsonProperty("id") final String id,
        @JsonProperty("external_id") final String externalId,
        @JsonProperty("user_name") final String userName,
        @JsonProperty("display_name") final String displayName,
        @JsonProperty("name") final ScimName name,
        @JsonProperty("emails") final List<ScimEmailAddress> emails,
        @JsonProperty("active") final Boolean active,
        @JsonProperty("locale") final String locale,
        @JsonProperty("timezone") final String timezone,
        @JsonProperty("schemas") final List<String> schemas,
        @JsonProperty("meta") final ScimMeta meta
    ) {
        this.id = id;
        this.externalId = externalId;
        this.userName = userName;
        this.displayName = displayName;
        this.name = name;
        this.emails = emails;
        this.active = active;
        this.locale = locale;
        this.timezone = timezone;
        this.schemas = schemas;
        this.meta = meta;
    }

    public final String getId() {
        return this.id;
    }

    public final String getExternalId() {
        return this.externalId;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final ScimName getName() {
        return this.name;
    }

    public final List<ScimEmailAddress> getEmails() {
        return this.emails;
    }

    public final Boolean getActive() {
        return this.active;
    }

    public final String getLocale() {
        return this.locale;
    }

    public final String getTimezone() {
        return this.timezone;
    }

    public final List<String> getSchemas() {
        return this.schemas;
    }

    public final ScimMeta getMeta() {
        return this.meta;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User other = (User) o;

        return (
            Objects.equals(id, other.id) &&
            Objects.equals(externalId, other.externalId) &&
            Objects.equals(userName, other.userName) &&
            Objects.equals(displayName, other.displayName) &&
            Objects.equals(name, other.name) &&
            Objects.equals(emails, other.emails) &&
            Objects.equals(active, other.active) &&
            Objects.equals(locale, other.locale) &&
            Objects.equals(timezone, other.timezone) &&
            Objects.equals(schemas, other.schemas) &&
            Objects.equals(meta, other.meta)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            externalId,
            userName,
            displayName,
            name,
            emails,
            active,
            locale,
            timezone,
            schemas,
            meta
        );
    }
}
