/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1.service.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
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
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Notification extends Resource {
    private static final long serialVersionUID = 28053260690009L;

    /**
     * Create a NotificationUpdater to execute update.
     *
     * @param pathChatServiceSid The SID of the Conversation Service that the
     *                           Configuration applies to.
     * @return NotificationUpdater capable of executing the update
     */
    public static NotificationUpdater updater(final String pathChatServiceSid) {
        return new NotificationUpdater(pathChatServiceSid);
    }

    /**
     * Create a NotificationFetcher to execute fetch.
     *
     * @param pathChatServiceSid The SID of the Conversation Service that the
     *                           Configuration applies to.
     * @return NotificationFetcher capable of executing the fetch
     */
    public static NotificationFetcher fetcher(final String pathChatServiceSid) {
        return new NotificationFetcher(pathChatServiceSid);
    }

    /**
     * Converts a JSON String into a Notification object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Notification object represented by the provided JSON
     */
    public static Notification fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Notification.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Notification object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Notification object represented by the provided JSON
     */
    public static Notification fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Notification.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String chatServiceSid;
    private final Map<String, Object> newMessage;
    private final Map<String, Object> addedToConversation;
    private final Map<String, Object> removedFromConversation;
    private final Boolean logEnabled;
    private final URI url;

    @JsonCreator
    private Notification(@JsonProperty("account_sid")
                         final String accountSid,
                         @JsonProperty("chat_service_sid")
                         final String chatServiceSid,
                         @JsonProperty("new_message")
                         final Map<String, Object> newMessage,
                         @JsonProperty("added_to_conversation")
                         final Map<String, Object> addedToConversation,
                         @JsonProperty("removed_from_conversation")
                         final Map<String, Object> removedFromConversation,
                         @JsonProperty("log_enabled")
                         final Boolean logEnabled,
                         @JsonProperty("url")
                         final URI url) {
        this.accountSid = accountSid;
        this.chatServiceSid = chatServiceSid;
        this.newMessage = newMessage;
        this.addedToConversation = addedToConversation;
        this.removedFromConversation = removedFromConversation;
        this.logEnabled = logEnabled;
        this.url = url;
    }

    /**
     * Returns The unique ID of the Account responsible for this configuration..
     *
     * @return The unique ID of the Account responsible for this configuration.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Conversation Service that the Configuration applies
     * to..
     *
     * @return The SID of the Conversation Service that the Configuration applies
     *         to.
     */
    public final String getChatServiceSid() {
        return this.chatServiceSid;
    }

    /**
     * Returns The Push Notification configuration for New Messages..
     *
     * @return The Push Notification configuration for New Messages.
     */
    public final Map<String, Object> getNewMessage() {
        return this.newMessage;
    }

    /**
     * Returns The Push Notification configuration for being added to a
     * Conversation..
     *
     * @return The Push Notification configuration for being added to a
     *         Conversation.
     */
    public final Map<String, Object> getAddedToConversation() {
        return this.addedToConversation;
    }

    /**
     * Returns The Push Notification configuration for being removed from a
     * Conversation..
     *
     * @return The Push Notification configuration for being removed from a
     *         Conversation.
     */
    public final Map<String, Object> getRemovedFromConversation() {
        return this.removedFromConversation;
    }

    /**
     * Returns Weather the notification logging is enabled..
     *
     * @return Weather the notification logging is enabled.
     */
    public final Boolean getLogEnabled() {
        return this.logEnabled;
    }

    /**
     * Returns An absolute URL for this configuration..
     *
     * @return An absolute URL for this configuration.
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

        Notification other = (Notification) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(chatServiceSid, other.chatServiceSid) &&
               Objects.equals(newMessage, other.newMessage) &&
               Objects.equals(addedToConversation, other.addedToConversation) &&
               Objects.equals(removedFromConversation, other.removedFromConversation) &&
               Objects.equals(logEnabled, other.logEnabled) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            chatServiceSid,
                            newMessage,
                            addedToConversation,
                            removedFromConversation,
                            logEnabled,
                            url);
    }
}