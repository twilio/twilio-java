/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1;

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
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Assistant extends Resource {
    private static final long serialVersionUID = 244014125424501L;

    /**
     * Create a AssistantFetcher to execute fetch.
     *
     * @param pathSid The unique string that identifies the resource
     * @return AssistantFetcher capable of executing the fetch
     */
    public static AssistantFetcher fetcher(final String pathSid) {
        return new AssistantFetcher(pathSid);
    }

    /**
     * Create a AssistantReader to execute read.
     *
     * @return AssistantReader capable of executing the read
     */
    public static AssistantReader reader() {
        return new AssistantReader();
    }

    /**
     * Create a AssistantCreator to execute create.
     *
     * @return AssistantCreator capable of executing the create
     */
    public static AssistantCreator creator() {
        return new AssistantCreator();
    }

    /**
     * Create a AssistantUpdater to execute update.
     *
     * @param pathSid The unique string that identifies the resource
     * @return AssistantUpdater capable of executing the update
     */
    public static AssistantUpdater updater(final String pathSid) {
        return new AssistantUpdater(pathSid);
    }

    /**
     * Create a AssistantDeleter to execute delete.
     *
     * @param pathSid The unique string that identifies the resource
     * @return AssistantDeleter capable of executing the delete
     */
    public static AssistantDeleter deleter(final String pathSid) {
        return new AssistantDeleter(pathSid);
    }

    /**
     * Converts a JSON String into a Assistant object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Assistant object represented by the provided JSON
     */
    public static Assistant fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Assistant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Assistant object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Assistant object represented by the provided JSON
     */
    public static Assistant fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Assistant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String latestModelBuildSid;
    private final Map<String, String> links;
    private final Boolean logQueries;
    private final String developmentStage;
    private final Boolean needsModelBuild;
    private final String sid;
    private final String uniqueName;
    private final URI url;
    private final URI callbackUrl;
    private final String callbackEvents;

    @JsonCreator
    private Assistant(@JsonProperty("account_sid")
                      final String accountSid,
                      @JsonProperty("date_created")
                      final String dateCreated,
                      @JsonProperty("date_updated")
                      final String dateUpdated,
                      @JsonProperty("friendly_name")
                      final String friendlyName,
                      @JsonProperty("latest_model_build_sid")
                      final String latestModelBuildSid,
                      @JsonProperty("links")
                      final Map<String, String> links,
                      @JsonProperty("log_queries")
                      final Boolean logQueries,
                      @JsonProperty("development_stage")
                      final String developmentStage,
                      @JsonProperty("needs_model_build")
                      final Boolean needsModelBuild,
                      @JsonProperty("sid")
                      final String sid,
                      @JsonProperty("unique_name")
                      final String uniqueName,
                      @JsonProperty("url")
                      final URI url,
                      @JsonProperty("callback_url")
                      final URI callbackUrl,
                      @JsonProperty("callback_events")
                      final String callbackEvents) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.latestModelBuildSid = latestModelBuildSid;
        this.links = links;
        this.logQueries = logQueries;
        this.developmentStage = developmentStage;
        this.needsModelBuild = needsModelBuild;
        this.sid = sid;
        this.uniqueName = uniqueName;
        this.url = url;
        this.callbackUrl = callbackUrl;
        this.callbackEvents = callbackEvents;
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
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns Reserved.
     *
     * @return Reserved
     */
    public final String getLatestModelBuildSid() {
        return this.latestModelBuildSid;
    }

    /**
     * Returns A list of the URLs of the Assistant's related resources.
     *
     * @return A list of the URLs of the Assistant's related resources
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    /**
     * Returns Whether queries should be logged and kept after training.
     *
     * @return Whether queries should be logged and kept after training
     */
    public final Boolean getLogQueries() {
        return this.logQueries;
    }

    /**
     * Returns A string describing the state of the assistant..
     *
     * @return A string describing the state of the assistant.
     */
    public final String getDevelopmentStage() {
        return this.developmentStage;
    }

    /**
     * Returns Whether model needs to be rebuilt.
     *
     * @return Whether model needs to be rebuilt
     */
    public final Boolean getNeedsModelBuild() {
        return this.needsModelBuild;
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
     * Returns An application-defined string that uniquely identifies the resource.
     *
     * @return An application-defined string that uniquely identifies the resource
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The absolute URL of the Assistant resource.
     *
     * @return The absolute URL of the Assistant resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns Reserved.
     *
     * @return Reserved
     */
    public final URI getCallbackUrl() {
        return this.callbackUrl;
    }

    /**
     * Returns Reserved.
     *
     * @return Reserved
     */
    public final String getCallbackEvents() {
        return this.callbackEvents;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Assistant other = (Assistant) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(latestModelBuildSid, other.latestModelBuildSid) &&
               Objects.equals(links, other.links) &&
               Objects.equals(logQueries, other.logQueries) &&
               Objects.equals(developmentStage, other.developmentStage) &&
               Objects.equals(needsModelBuild, other.needsModelBuild) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(url, other.url) &&
               Objects.equals(callbackUrl, other.callbackUrl) &&
               Objects.equals(callbackEvents, other.callbackEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            latestModelBuildSid,
                            links,
                            logQueries,
                            developmentStage,
                            needsModelBuild,
                            sid,
                            uniqueName,
                            url,
                            callbackUrl,
                            callbackEvents);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("latestModelBuildSid", latestModelBuildSid)
                          .add("links", links)
                          .add("logQueries", logQueries)
                          .add("developmentStage", developmentStage)
                          .add("needsModelBuild", needsModelBuild)
                          .add("sid", sid)
                          .add("uniqueName", uniqueName)
                          .add("url", url)
                          .add("callbackUrl", callbackUrl)
                          .add("callbackEvents", callbackEvents)
                          .toString();
    }
}