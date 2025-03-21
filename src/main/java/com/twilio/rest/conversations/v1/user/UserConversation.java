/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Conversations
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.conversations.v1.user;

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
public class UserConversation extends Resource {

    private static final long serialVersionUID = 269317644556189L;

    public static UserConversationDeleter deleter(
        final String pathUserSid,
        final String pathConversationSid
    ) {
        return new UserConversationDeleter(pathUserSid, pathConversationSid);
    }

    public static UserConversationFetcher fetcher(
        final String pathUserSid,
        final String pathConversationSid
    ) {
        return new UserConversationFetcher(pathUserSid, pathConversationSid);
    }

    public static UserConversationReader reader(final String pathUserSid) {
        return new UserConversationReader(pathUserSid);
    }

    public static UserConversationUpdater updater(
        final String pathUserSid,
        final String pathConversationSid
    ) {
        return new UserConversationUpdater(pathUserSid, pathConversationSid);
    }

    /**
     * Converts a JSON String into a UserConversation object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return UserConversation object represented by the provided JSON
     */
    public static UserConversation fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, UserConversation.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a UserConversation object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return UserConversation object represented by the provided JSON
     */
    public static UserConversation fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, UserConversation.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String chatServiceSid;
    private final String conversationSid;
    private final Integer unreadMessagesCount;
    private final Integer lastReadMessageIndex;
    private final String participantSid;
    private final String userSid;
    private final String friendlyName;
    private final UserConversation.State conversationState;
    private final Map<String, Object> timers;
    private final String attributes;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String createdBy;
    private final UserConversation.NotificationLevel notificationLevel;
    private final String uniqueName;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private UserConversation(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("chat_service_sid") final String chatServiceSid,
        @JsonProperty("conversation_sid") final String conversationSid,
        @JsonProperty(
            "unread_messages_count"
        ) final Integer unreadMessagesCount,
        @JsonProperty(
            "last_read_message_index"
        ) final Integer lastReadMessageIndex,
        @JsonProperty("participant_sid") final String participantSid,
        @JsonProperty("user_sid") final String userSid,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty(
            "conversation_state"
        ) final UserConversation.State conversationState,
        @JsonProperty("timers") final Map<String, Object> timers,
        @JsonProperty("attributes") final String attributes,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("created_by") final String createdBy,
        @JsonProperty(
            "notification_level"
        ) final UserConversation.NotificationLevel notificationLevel,
        @JsonProperty("unique_name") final String uniqueName,
        @JsonProperty("url") final URI url,
        @JsonProperty("links") final Map<String, String> links
    ) {
        this.accountSid = accountSid;
        this.chatServiceSid = chatServiceSid;
        this.conversationSid = conversationSid;
        this.unreadMessagesCount = unreadMessagesCount;
        this.lastReadMessageIndex = lastReadMessageIndex;
        this.participantSid = participantSid;
        this.userSid = userSid;
        this.friendlyName = friendlyName;
        this.conversationState = conversationState;
        this.timers = timers;
        this.attributes = attributes;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.createdBy = createdBy;
        this.notificationLevel = notificationLevel;
        this.uniqueName = uniqueName;
        this.url = url;
        this.links = links;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getChatServiceSid() {
        return this.chatServiceSid;
    }

    public final String getConversationSid() {
        return this.conversationSid;
    }

    public final Integer getUnreadMessagesCount() {
        return this.unreadMessagesCount;
    }

    public final Integer getLastReadMessageIndex() {
        return this.lastReadMessageIndex;
    }

    public final String getParticipantSid() {
        return this.participantSid;
    }

    public final String getUserSid() {
        return this.userSid;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final UserConversation.State getConversationState() {
        return this.conversationState;
    }

    public final Map<String, Object> getTimers() {
        return this.timers;
    }

    public final String getAttributes() {
        return this.attributes;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    public final String getCreatedBy() {
        return this.createdBy;
    }

    public final UserConversation.NotificationLevel getNotificationLevel() {
        return this.notificationLevel;
    }

    public final String getUniqueName() {
        return this.uniqueName;
    }

    public final URI getUrl() {
        return this.url;
    }

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

        UserConversation other = (UserConversation) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(chatServiceSid, other.chatServiceSid) &&
            Objects.equals(conversationSid, other.conversationSid) &&
            Objects.equals(unreadMessagesCount, other.unreadMessagesCount) &&
            Objects.equals(lastReadMessageIndex, other.lastReadMessageIndex) &&
            Objects.equals(participantSid, other.participantSid) &&
            Objects.equals(userSid, other.userSid) &&
            Objects.equals(friendlyName, other.friendlyName) &&
            Objects.equals(conversationState, other.conversationState) &&
            Objects.equals(timers, other.timers) &&
            Objects.equals(attributes, other.attributes) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(createdBy, other.createdBy) &&
            Objects.equals(notificationLevel, other.notificationLevel) &&
            Objects.equals(uniqueName, other.uniqueName) &&
            Objects.equals(url, other.url) &&
            Objects.equals(links, other.links)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
            chatServiceSid,
            conversationSid,
            unreadMessagesCount,
            lastReadMessageIndex,
            participantSid,
            userSid,
            friendlyName,
            conversationState,
            timers,
            attributes,
            dateCreated,
            dateUpdated,
            createdBy,
            notificationLevel,
            uniqueName,
            url,
            links
        );
    }

    public enum State {
        INACTIVE("inactive"),
        ACTIVE("active"),
        CLOSED("closed");

        private final String value;

        private State(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static State forValue(final String value) {
            return Promoter.enumFromString(value, State.values());
        }
    }

    public enum NotificationLevel {
        DEFAULT("default"),
        MUTED("muted");

        private final String value;

        private NotificationLevel(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static NotificationLevel forValue(final String value) {
            return Promoter.enumFromString(value, NotificationLevel.values());
        }
    }
}
