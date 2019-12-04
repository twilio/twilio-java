/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1.assistant;

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
import java.time.ZonedDateTime;

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
public class Webhook extends Resource {
    private static final long serialVersionUID = 37970944416419L;

    /**
     * Create a WebhookFetcher to execute fetch.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resource to fetch
     * @param pathSid The unique string that identifies the resource to fetch
     * @return WebhookFetcher capable of executing the fetch
     */
    public static WebhookFetcher fetcher(final String pathAssistantSid,
                                         final String pathSid) {
        return new WebhookFetcher(pathAssistantSid, pathSid);
    }

    /**
     * Create a WebhookReader to execute read.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resources to read
     * @return WebhookReader capable of executing the read
     */
    public static WebhookReader reader(final String pathAssistantSid) {
        return new WebhookReader(pathAssistantSid);
    }

    /**
     * Create a WebhookCreator to execute create.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         new resource
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @param events The list of space-separated events that this Webhook will
     *               subscribe to.
     * @param webhookUrl The URL associated with this Webhook.
     * @return WebhookCreator capable of executing the create
     */
    public static WebhookCreator creator(final String pathAssistantSid,
                                         final String uniqueName,
                                         final String events,
                                         final URI webhookUrl) {
        return new WebhookCreator(pathAssistantSid, uniqueName, events, webhookUrl);
    }

    /**
     * Create a WebhookUpdater to execute update.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resource to update
     * @param pathSid The unique string that identifies the resource
     * @return WebhookUpdater capable of executing the update
     */
    public static WebhookUpdater updater(final String pathAssistantSid,
                                         final String pathSid) {
        return new WebhookUpdater(pathAssistantSid, pathSid);
    }

    /**
     * Create a WebhookDeleter to execute delete.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resources to delete
     * @param pathSid The unique string that identifies the resource to delete
     * @return WebhookDeleter capable of executing the delete
     */
    public static WebhookDeleter deleter(final String pathAssistantSid,
                                         final String pathSid) {
        return new WebhookDeleter(pathAssistantSid, pathSid);
    }

    /**
     * Converts a JSON String into a Webhook object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Webhook object represented by the provided JSON
     */
    public static Webhook fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Webhook.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Webhook object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Webhook object represented by the provided JSON
     */
    public static Webhook fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Webhook.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final URI url;
    private final String accountSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String assistantSid;
    private final String sid;
    private final String uniqueName;
    private final String events;
    private final URI webhookUrl;
    private final String webhookMethod;

    @JsonCreator
    private Webhook(@JsonProperty("url")
                    final URI url,
                    @JsonProperty("account_sid")
                    final String accountSid,
                    @JsonProperty("date_created")
                    final String dateCreated,
                    @JsonProperty("date_updated")
                    final String dateUpdated,
                    @JsonProperty("assistant_sid")
                    final String assistantSid,
                    @JsonProperty("sid")
                    final String sid,
                    @JsonProperty("unique_name")
                    final String uniqueName,
                    @JsonProperty("events")
                    final String events,
                    @JsonProperty("webhook_url")
                    final URI webhookUrl,
                    @JsonProperty("webhook_method")
                    final String webhookMethod) {
        this.url = url;
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.assistantSid = assistantSid;
        this.sid = sid;
        this.uniqueName = uniqueName;
        this.events = events;
        this.webhookUrl = webhookUrl;
        this.webhookMethod = webhookMethod;
    }

    /**
     * Returns The The absolute URL of the Webhook resource.
     *
     * @return The absolute URL of the Webhook resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The RFC 2822 date and time in GMT when the resource was created.
     *
     * @return The RFC 2822 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The RFC 2822 date and time in GMT when the resource was last
     * updated.
     *
     * @return The RFC 2822 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The SID of the Assistant that is the parent of the resource.
     *
     * @return The SID of the Assistant that is the parent of the resource
     */
    public final String getAssistantSid() {
        return this.assistantSid;
    }

    /**
     * Returns The The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The An application-defined string that uniquely identifies the
     * resource.
     *
     * @return An application-defined string that uniquely identifies the resource
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The The list of space-separated events that this Webhook is
     * subscribed to..
     *
     * @return The list of space-separated events that this Webhook is subscribed
     *         to.
     */
    public final String getEvents() {
        return this.events;
    }

    /**
     * Returns The The URL associated with this Webhook..
     *
     * @return The URL associated with this Webhook.
     */
    public final URI getWebhookUrl() {
        return this.webhookUrl;
    }

    /**
     * Returns The The method used when calling the webhook's URL..
     *
     * @return The method used when calling the webhook's URL.
     */
    public final String getWebhookMethod() {
        return this.webhookMethod;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Webhook other = (Webhook) o;

        return Objects.equals(url, other.url) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(assistantSid, other.assistantSid) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(events, other.events) &&
               Objects.equals(webhookUrl, other.webhookUrl) &&
               Objects.equals(webhookMethod, other.webhookMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url,
                            accountSid,
                            dateCreated,
                            dateUpdated,
                            assistantSid,
                            sid,
                            uniqueName,
                            events,
                            webhookUrl,
                            webhookMethod);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("url", url)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("assistantSid", assistantSid)
                          .add("sid", sid)
                          .add("uniqueName", uniqueName)
                          .add("events", events)
                          .add("webhookUrl", webhookUrl)
                          .add("webhookMethod", webhookMethod)
                          .toString();
    }
}
