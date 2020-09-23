/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import lombok.ToString;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Service extends Resource {
    private static final long serialVersionUID = 81815704097903L;

    /**
     * Create a ServiceFetcher to execute fetch.
     *
     * @param pathSid The SID of the Service resource to fetch
     * @return ServiceFetcher capable of executing the fetch
     */
    public static ServiceFetcher fetcher(final String pathSid) {
        return new ServiceFetcher(pathSid);
    }

    /**
     * Create a ServiceDeleter to execute delete.
     *
     * @param pathSid The SID of the Service resource to delete
     * @return ServiceDeleter capable of executing the delete
     */
    public static ServiceDeleter deleter(final String pathSid) {
        return new ServiceDeleter(pathSid);
    }

    /**
     * Create a ServiceCreator to execute create.
     *
     * @param friendlyName A string to describe the resource
     * @return ServiceCreator capable of executing the create
     */
    public static ServiceCreator creator(final String friendlyName) {
        return new ServiceCreator(friendlyName);
    }

    /**
     * Create a ServiceReader to execute read.
     *
     * @return ServiceReader capable of executing the read
     */
    public static ServiceReader reader() {
        return new ServiceReader();
    }

    /**
     * Create a ServiceUpdater to execute update.
     *
     * @param pathSid The SID of the Service resource to update
     * @return ServiceUpdater capable of executing the update
     */
    public static ServiceUpdater updater(final String pathSid) {
        return new ServiceUpdater(pathSid);
    }

    /**
     * Converts a JSON String into a Service object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Service object represented by the provided JSON
     */
    public static Service fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Service.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Service object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Service object represented by the provided JSON
     */
    public static Service fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Service.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String friendlyName;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String defaultServiceRoleSid;
    private final String defaultChannelRoleSid;
    private final String defaultChannelCreatorRoleSid;
    private final Boolean readStatusEnabled;
    private final Boolean reachabilityEnabled;
    private final Integer typingIndicatorTimeout;
    private final Integer consumptionReportInterval;
    private final Map<String, Object> limits;
    private final String preWebhookUrl;
    private final String postWebhookUrl;
    private final String webhookMethod;
    private final List<String> webhookFilters;
    private final Integer preWebhookRetryCount;
    private final Integer postWebhookRetryCount;
    private final Map<String, Object> notifications;
    private final Map<String, Object> media;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Service(@JsonProperty("sid")
                    final String sid,
                    @JsonProperty("account_sid")
                    final String accountSid,
                    @JsonProperty("friendly_name")
                    final String friendlyName,
                    @JsonProperty("date_created")
                    final String dateCreated,
                    @JsonProperty("date_updated")
                    final String dateUpdated,
                    @JsonProperty("default_service_role_sid")
                    final String defaultServiceRoleSid,
                    @JsonProperty("default_channel_role_sid")
                    final String defaultChannelRoleSid,
                    @JsonProperty("default_channel_creator_role_sid")
                    final String defaultChannelCreatorRoleSid,
                    @JsonProperty("read_status_enabled")
                    final Boolean readStatusEnabled,
                    @JsonProperty("reachability_enabled")
                    final Boolean reachabilityEnabled,
                    @JsonProperty("typing_indicator_timeout")
                    final Integer typingIndicatorTimeout,
                    @JsonProperty("consumption_report_interval")
                    final Integer consumptionReportInterval,
                    @JsonProperty("limits")
                    final Map<String, Object> limits,
                    @JsonProperty("pre_webhook_url")
                    final String preWebhookUrl,
                    @JsonProperty("post_webhook_url")
                    final String postWebhookUrl,
                    @JsonProperty("webhook_method")
                    final String webhookMethod,
                    @JsonProperty("webhook_filters")
                    final List<String> webhookFilters,
                    @JsonProperty("pre_webhook_retry_count")
                    final Integer preWebhookRetryCount,
                    @JsonProperty("post_webhook_retry_count")
                    final Integer postWebhookRetryCount,
                    @JsonProperty("notifications")
                    final Map<String, Object> notifications,
                    @JsonProperty("media")
                    final Map<String, Object> media,
                    @JsonProperty("url")
                    final URI url,
                    @JsonProperty("links")
                    final Map<String, String> links) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.defaultServiceRoleSid = defaultServiceRoleSid;
        this.defaultChannelRoleSid = defaultChannelRoleSid;
        this.defaultChannelCreatorRoleSid = defaultChannelCreatorRoleSid;
        this.readStatusEnabled = readStatusEnabled;
        this.reachabilityEnabled = reachabilityEnabled;
        this.typingIndicatorTimeout = typingIndicatorTimeout;
        this.consumptionReportInterval = consumptionReportInterval;
        this.limits = limits;
        this.preWebhookUrl = preWebhookUrl;
        this.postWebhookUrl = postWebhookUrl;
        this.webhookMethod = webhookMethod;
        this.webhookFilters = webhookFilters;
        this.preWebhookRetryCount = preWebhookRetryCount;
        this.postWebhookRetryCount = postWebhookRetryCount;
        this.notifications = notifications;
        this.media = media;
        this.url = url;
        this.links = links;
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
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
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
     * Returns The service role assigned to users when they are added to the
     * service.
     *
     * @return The service role assigned to users when they are added to the service
     */
    public final String getDefaultServiceRoleSid() {
        return this.defaultServiceRoleSid;
    }

    /**
     * Returns The channel role assigned to users when they are added to a channel.
     *
     * @return The channel role assigned to users when they are added to a channel
     */
    public final String getDefaultChannelRoleSid() {
        return this.defaultChannelRoleSid;
    }

    /**
     * Returns The channel role assigned to a channel creator when they join a new
     * channel.
     *
     * @return The channel role assigned to a channel creator when they join a new
     *         channel
     */
    public final String getDefaultChannelCreatorRoleSid() {
        return this.defaultChannelCreatorRoleSid;
    }

    /**
     * Returns Whether the Message Consumption Horizon feature is enabled.
     *
     * @return Whether the Message Consumption Horizon feature is enabled
     */
    public final Boolean getReadStatusEnabled() {
        return this.readStatusEnabled;
    }

    /**
     * Returns Whether the Reachability Indicator feature is enabled for this
     * Service instance.
     *
     * @return Whether the Reachability Indicator feature is enabled for this
     *         Service instance
     */
    public final Boolean getReachabilityEnabled() {
        return this.reachabilityEnabled;
    }

    /**
     * Returns How long in seconds to wait before assuming the user is no longer
     * typing.
     *
     * @return How long in seconds to wait before assuming the user is no longer
     *         typing
     */
    public final Integer getTypingIndicatorTimeout() {
        return this.typingIndicatorTimeout;
    }

    /**
     * Returns DEPRECATED.
     *
     * @return DEPRECATED
     */
    public final Integer getConsumptionReportInterval() {
        return this.consumptionReportInterval;
    }

    /**
     * Returns An object that describes the limits of the service instance.
     *
     * @return An object that describes the limits of the service instance
     */
    public final Map<String, Object> getLimits() {
        return this.limits;
    }

    /**
     * Returns The webhook URL for pre-event webhooks.
     *
     * @return The webhook URL for pre-event webhooks
     */
    public final String getPreWebhookUrl() {
        return this.preWebhookUrl;
    }

    /**
     * Returns The URL for post-event webhooks.
     *
     * @return The URL for post-event webhooks
     */
    public final String getPostWebhookUrl() {
        return this.postWebhookUrl;
    }

    /**
     * Returns The HTTP method  to use for both PRE and POST webhooks.
     *
     * @return The HTTP method  to use for both PRE and POST webhooks
     */
    public final String getWebhookMethod() {
        return this.webhookMethod;
    }

    /**
     * Returns The list of webhook events that are enabled for this Service
     * instance.
     *
     * @return The list of webhook events that are enabled for this Service instance
     */
    public final List<String> getWebhookFilters() {
        return this.webhookFilters;
    }

    /**
     * Returns Count of times webhook will be retried in case of timeout or
     * 429/503/504 HTTP responses.
     *
     * @return Count of times webhook will be retried in case of timeout or
     *         429/503/504 HTTP responses
     */
    public final Integer getPreWebhookRetryCount() {
        return this.preWebhookRetryCount;
    }

    /**
     * Returns The number of times calls to the `post_webhook_url` will be retried.
     *
     * @return The number of times calls to the `post_webhook_url` will be retried
     */
    public final Integer getPostWebhookRetryCount() {
        return this.postWebhookRetryCount;
    }

    /**
     * Returns The notification configuration for the Service instance.
     *
     * @return The notification configuration for the Service instance
     */
    public final Map<String, Object> getNotifications() {
        return this.notifications;
    }

    /**
     * Returns The properties of the media that the service supports.
     *
     * @return The properties of the media that the service supports
     */
    public final Map<String, Object> getMedia() {
        return this.media;
    }

    /**
     * Returns The absolute URL of the Service resource.
     *
     * @return The absolute URL of the Service resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The absolute URLs of the Service's Channels, Roles, and Users.
     *
     * @return The absolute URLs of the Service's Channels, Roles, and Users
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Service other = (Service) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(defaultServiceRoleSid, other.defaultServiceRoleSid) &&
               Objects.equals(defaultChannelRoleSid, other.defaultChannelRoleSid) &&
               Objects.equals(defaultChannelCreatorRoleSid, other.defaultChannelCreatorRoleSid) &&
               Objects.equals(readStatusEnabled, other.readStatusEnabled) &&
               Objects.equals(reachabilityEnabled, other.reachabilityEnabled) &&
               Objects.equals(typingIndicatorTimeout, other.typingIndicatorTimeout) &&
               Objects.equals(consumptionReportInterval, other.consumptionReportInterval) &&
               Objects.equals(limits, other.limits) &&
               Objects.equals(preWebhookUrl, other.preWebhookUrl) &&
               Objects.equals(postWebhookUrl, other.postWebhookUrl) &&
               Objects.equals(webhookMethod, other.webhookMethod) &&
               Objects.equals(webhookFilters, other.webhookFilters) &&
               Objects.equals(preWebhookRetryCount, other.preWebhookRetryCount) &&
               Objects.equals(postWebhookRetryCount, other.postWebhookRetryCount) &&
               Objects.equals(notifications, other.notifications) &&
               Objects.equals(media, other.media) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            friendlyName,
                            dateCreated,
                            dateUpdated,
                            defaultServiceRoleSid,
                            defaultChannelRoleSid,
                            defaultChannelCreatorRoleSid,
                            readStatusEnabled,
                            reachabilityEnabled,
                            typingIndicatorTimeout,
                            consumptionReportInterval,
                            limits,
                            preWebhookUrl,
                            postWebhookUrl,
                            webhookMethod,
                            webhookFilters,
                            preWebhookRetryCount,
                            postWebhookRetryCount,
                            notifications,
                            media,
                            url,
                            links);
    }
}
