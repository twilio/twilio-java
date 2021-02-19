/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.verify.v2.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Webhook extends Resource {
    private static final long serialVersionUID = 249630683343645L;

    public enum Status {
        ENABLED("enabled"),
        DISABLED("disabled");

        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Status from a string.
         * @param value string value
         * @return generated Status
         */
        @JsonCreator
        public static Status forValue(final String value) {
            return Promoter.enumFromString(value, Status.values());
        }
    }

    public enum Methods {
        GET("GET"),
        POST("POST");

        private final String value;

        private Methods(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Methods from a string.
         * @param value string value
         * @return generated Methods
         */
        @JsonCreator
        public static Methods forValue(final String value) {
            return Promoter.enumFromString(value, Methods.values());
        }
    }

    /**
     * Create a WebhookCreator to execute create.
     *
     * @param pathServiceSid Service Sid.
     * @param friendlyName The string that you assigned to describe the webhook
     * @param eventTypes The array of events that this Webhook is subscribed to.
     * @param webhookUrl The URL associated with this Webhook.
     * @return WebhookCreator capable of executing the create
     */
    public static WebhookCreator creator(final String pathServiceSid,
                                         final String friendlyName,
                                         final List<String> eventTypes,
                                         final String webhookUrl) {
        return new WebhookCreator(pathServiceSid, friendlyName, eventTypes, webhookUrl);
    }

    /**
     * Create a WebhookUpdater to execute update.
     *
     * @param pathServiceSid Service Sid.
     * @param pathSid The unique string that identifies the resource
     * @return WebhookUpdater capable of executing the update
     */
    public static WebhookUpdater updater(final String pathServiceSid,
                                         final String pathSid) {
        return new WebhookUpdater(pathServiceSid, pathSid);
    }

    /**
     * Create a WebhookDeleter to execute delete.
     *
     * @param pathServiceSid Service Sid.
     * @param pathSid The unique string that identifies the resource to delete
     * @return WebhookDeleter capable of executing the delete
     */
    public static WebhookDeleter deleter(final String pathServiceSid,
                                         final String pathSid) {
        return new WebhookDeleter(pathServiceSid, pathSid);
    }

    /**
     * Create a WebhookFetcher to execute fetch.
     *
     * @param pathServiceSid Service Sid.
     * @param pathSid The unique string that identifies the resource to fetch
     * @return WebhookFetcher capable of executing the fetch
     */
    public static WebhookFetcher fetcher(final String pathServiceSid,
                                         final String pathSid) {
        return new WebhookFetcher(pathServiceSid, pathSid);
    }

    /**
     * Create a WebhookReader to execute read.
     *
     * @param pathServiceSid Service Sid.
     * @return WebhookReader capable of executing the read
     */
    public static WebhookReader reader(final String pathServiceSid) {
        return new WebhookReader(pathServiceSid);
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

    private final String sid;
    private final String serviceSid;
    private final String accountSid;
    private final String friendlyName;
    private final List<String> eventTypes;
    private final Webhook.Status status;
    private final URI webhookUrl;
    private final Webhook.Methods webhookMethod;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private Webhook(@JsonProperty("sid")
                    final String sid,
                    @JsonProperty("service_sid")
                    final String serviceSid,
                    @JsonProperty("account_sid")
                    final String accountSid,
                    @JsonProperty("friendly_name")
                    final String friendlyName,
                    @JsonProperty("event_types")
                    final List<String> eventTypes,
                    @JsonProperty("status")
                    final Webhook.Status status,
                    @JsonProperty("webhook_url")
                    final URI webhookUrl,
                    @JsonProperty("webhook_method")
                    final Webhook.Methods webhookMethod,
                    @JsonProperty("date_created")
                    final String dateCreated,
                    @JsonProperty("date_updated")
                    final String dateUpdated,
                    @JsonProperty("url")
                    final URI url) {
        this.sid = sid;
        this.serviceSid = serviceSid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.eventTypes = eventTypes;
        this.status = status;
        this.webhookUrl = webhookUrl;
        this.webhookMethod = webhookMethod;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
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
     * Returns Service Sid..
     *
     * @return Service Sid.
     */
    public final String getServiceSid() {
        return this.serviceSid;
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
     * Returns The string that you assigned to describe the webhook.
     *
     * @return The string that you assigned to describe the webhook
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The array of events that this Webhook is subscribed to..
     *
     * @return The array of events that this Webhook is subscribed to.
     */
    public final List<String> getEventTypes() {
        return this.eventTypes;
    }

    /**
     * Returns The webhook status.
     *
     * @return The webhook status
     */
    public final Webhook.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The URL associated with this Webhook..
     *
     * @return The URL associated with this Webhook.
     */
    public final URI getWebhookUrl() {
        return this.webhookUrl;
    }

    /**
     * Returns The method used when calling the webhook's URL..
     *
     * @return The method used when calling the webhook's URL.
     */
    public final Webhook.Methods getWebhookMethod() {
        return this.webhookMethod;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was created.
     *
     * @return The RFC 2822 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was last updated.
     *
     * @return The RFC 2822 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The absolute URL of the Webhook resource.
     *
     * @return The absolute URL of the Webhook resource
     */
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

        Webhook other = (Webhook) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(eventTypes, other.eventTypes) &&
               Objects.equals(status, other.status) &&
               Objects.equals(webhookUrl, other.webhookUrl) &&
               Objects.equals(webhookMethod, other.webhookMethod) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            serviceSid,
                            accountSid,
                            friendlyName,
                            eventTypes,
                            status,
                            webhookUrl,
                            webhookMethod,
                            dateCreated,
                            dateUpdated,
                            url);
    }
}