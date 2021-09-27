/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
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
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserConversation extends Resource {
    private static final long serialVersionUID = 228878200976815L;

  public String toString() {
    return "UserConversation(accountSid=" + this.getAccountSid() + ", chatServiceSid=" + this.getChatServiceSid() + ", conversationSid=" + this.getConversationSid() + ", unreadMessagesCount=" + this.getUnreadMessagesCount() + ", lastReadMessageIndex=" + this.getLastReadMessageIndex() + ", participantSid=" + this.getParticipantSid() + ", userSid=" + this.getUserSid() + ", friendlyName=" + this.getFriendlyName() + ", conversationState=" + this.getConversationState() + ", timers=" + this.getTimers() + ", attributes=" + this.getAttributes() + ", dateCreated=" + this.getDateCreated() + ", dateUpdated=" + this.getDateUpdated() + ", createdBy=" + this.getCreatedBy() + ", notificationLevel=" + this.getNotificationLevel() + ", uniqueName=" + this.getUniqueName() + ", url=" + this.getUrl() + ", links=" + this.getLinks() + ")";
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

        /**
         * Generate a NotificationLevel from a string.
         * @param value string value
         * @return generated NotificationLevel
         */
        @JsonCreator
        public static NotificationLevel forValue(final String value) {
            return Promoter.enumFromString(value, NotificationLevel.values());
        }
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

        /**
         * Generate a State from a string.
         * @param value string value
         * @return generated State
         */
        @JsonCreator
        public static State forValue(final String value) {
            return Promoter.enumFromString(value, State.values());
        }
    }

    /**
     * Create a UserConversationUpdater to execute update.
     *
     * @param pathUserSid The unique SID identifier of the User.
     * @param pathConversationSid The unique SID identifier of the Conversation.
     * @return UserConversationUpdater capable of executing the update
     */
    public static UserConversationUpdater updater(final String pathUserSid,
                                                  final String pathConversationSid) {
        return new UserConversationUpdater(pathUserSid, pathConversationSid);
    }

    /**
     * Create a UserConversationDeleter to execute delete.
     *
     * @param pathUserSid The unique SID identifier of the User.
     * @param pathConversationSid The unique SID identifier of the Conversation.
     * @return UserConversationDeleter capable of executing the delete
     */
    public static UserConversationDeleter deleter(final String pathUserSid,
                                                  final String pathConversationSid) {
        return new UserConversationDeleter(pathUserSid, pathConversationSid);
    }

    /**
     * Create a UserConversationFetcher to execute fetch.
     *
     * @param pathUserSid The unique SID identifier of the User.
     * @param pathConversationSid The unique SID identifier of the Conversation.
     * @return UserConversationFetcher capable of executing the fetch
     */
    public static UserConversationFetcher fetcher(final String pathUserSid,
                                                  final String pathConversationSid) {
        return new UserConversationFetcher(pathUserSid, pathConversationSid);
    }

    /**
     * Create a UserConversationReader to execute read.
     *
     * @param pathUserSid The unique SID identifier of the User.
     * @return UserConversationReader capable of executing the read
     */
    public static UserConversationReader reader(final String pathUserSid) {
        return new UserConversationReader(pathUserSid);
    }

    /**
     * Converts a JSON String into a UserConversation object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return UserConversation object represented by the provided JSON
     */
    public static UserConversation fromJson(final String json, final ObjectMapper objectMapper) {
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
    public static UserConversation fromJson(final InputStream json, final ObjectMapper objectMapper) {
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
    private UserConversation(@JsonProperty("account_sid")
                             final String accountSid,
                             @JsonProperty("chat_service_sid")
                             final String chatServiceSid,
                             @JsonProperty("conversation_sid")
                             final String conversationSid,
                             @JsonProperty("unread_messages_count")
                             final Integer unreadMessagesCount,
                             @JsonProperty("last_read_message_index")
                             final Integer lastReadMessageIndex,
                             @JsonProperty("participant_sid")
                             final String participantSid,
                             @JsonProperty("user_sid")
                             final String userSid,
                             @JsonProperty("friendly_name")
                             final String friendlyName,
                             @JsonProperty("conversation_state")
                             final UserConversation.State conversationState,
                             @JsonProperty("timers")
                             final Map<String, Object> timers,
                             @JsonProperty("attributes")
                             final String attributes,
                             @JsonProperty("date_created")
                             final String dateCreated,
                             @JsonProperty("date_updated")
                             final String dateUpdated,
                             @JsonProperty("created_by")
                             final String createdBy,
                             @JsonProperty("notification_level")
                             final UserConversation.NotificationLevel notificationLevel,
                             @JsonProperty("unique_name")
                             final String uniqueName,
                             @JsonProperty("url")
                             final URI url,
                             @JsonProperty("links")
                             final Map<String, String> links) {
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

    /**
     * Returns The unique ID of the Account responsible for this conversation..
     *
     * @return The unique ID of the Account responsible for this conversation.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The unique ID of the Conversation Service this conversation belongs
     * to..
     *
     * @return The unique ID of the Conversation Service this conversation belongs
     *         to.
     */
    public final String getChatServiceSid() {
        return this.chatServiceSid;
    }

    /**
     * Returns The unique ID of the Conversation for this User Conversation..
     *
     * @return The unique ID of the Conversation for this User Conversation.
     */
    public final String getConversationSid() {
        return this.conversationSid;
    }

    /**
     * Returns The number of unread Messages in the Conversation..
     *
     * @return The number of unread Messages in the Conversation.
     */
    public final Integer getUnreadMessagesCount() {
        return this.unreadMessagesCount;
    }

    /**
     * Returns The index of the last read Message ..
     *
     * @return The index of the last read Message .
     */
    public final Integer getLastReadMessageIndex() {
        return this.lastReadMessageIndex;
    }

    /**
     * Returns Participant Sid..
     *
     * @return Participant Sid.
     */
    public final String getParticipantSid() {
        return this.participantSid;
    }

    /**
     * Returns The unique ID for the User..
     *
     * @return The unique ID for the User.
     */
    public final String getUserSid() {
        return this.userSid;
    }

    /**
     * Returns The human-readable name of this conversation..
     *
     * @return The human-readable name of this conversation.
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The current state of this User Conversation.
     *
     * @return The current state of this User Conversation
     */
    public final UserConversation.State getConversationState() {
        return this.conversationState;
    }

    /**
     * Returns Timer date values for this conversation..
     *
     * @return Timer date values for this conversation.
     */
    public final Map<String, Object> getTimers() {
        return this.timers;
    }

    /**
     * Returns An optional string metadata field you can use to store any data you
     * wish..
     *
     * @return An optional string metadata field you can use to store any data you
     *         wish.
     */
    public final String getAttributes() {
        return this.attributes;
    }

    /**
     * Returns The date that this conversation was created..
     *
     * @return The date that this conversation was created.
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The date that this conversation was last updated..
     *
     * @return The date that this conversation was last updated.
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns Creator of this conversation..
     *
     * @return Creator of this conversation.
     */
    public final String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Returns The Notification Level of this User Conversation..
     *
     * @return The Notification Level of this User Conversation.
     */
    public final UserConversation.NotificationLevel getNotificationLevel() {
        return this.notificationLevel;
    }

    /**
     * Returns An application-defined string that uniquely identifies the
     * Conversation resource..
     *
     * @return An application-defined string that uniquely identifies the
     *         Conversation resource.
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The url.
     *
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns Absolute URLs to access the participant and conversation of this user
     * conversation..
     *
     * @return Absolute URLs to access the participant and conversation of this
     *         user conversation.
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

        UserConversation other = (UserConversation) o;

        return Objects.equals(accountSid, other.accountSid) &&
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
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
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
                            links);
    }
}
