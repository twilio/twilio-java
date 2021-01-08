/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1.service.conversation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
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
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Participant extends Resource {
    private static final long serialVersionUID = 258075290829490L;

    public enum WebhookEnabledType {
        TRUE("true"),
        FALSE("false");

        private final String value;

        private WebhookEnabledType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a WebhookEnabledType from a string.
         * @param value string value
         * @return generated WebhookEnabledType
         */
        @JsonCreator
        public static WebhookEnabledType forValue(final String value) {
            return Promoter.enumFromString(value, WebhookEnabledType.values());
        }
    }

    /**
     * Create a ParticipantCreator to execute create.
     *
     * @param pathChatServiceSid The SID of the Conversation Service that the
     *                           resource is associated with.
     * @param pathConversationSid The unique ID of the Conversation for this
     *                            participant.
     * @return ParticipantCreator capable of executing the create
     */
    public static ParticipantCreator creator(final String pathChatServiceSid,
                                             final String pathConversationSid) {
        return new ParticipantCreator(pathChatServiceSid, pathConversationSid);
    }

    /**
     * Create a ParticipantUpdater to execute update.
     *
     * @param pathChatServiceSid The SID of the Conversation Service that the
     *                           resource is associated with.
     * @param pathConversationSid The unique ID of the Conversation for this
     *                            participant.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     * @return ParticipantUpdater capable of executing the update
     */
    public static ParticipantUpdater updater(final String pathChatServiceSid,
                                             final String pathConversationSid,
                                             final String pathSid) {
        return new ParticipantUpdater(pathChatServiceSid, pathConversationSid, pathSid);
    }

    /**
     * Create a ParticipantDeleter to execute delete.
     *
     * @param pathChatServiceSid The SID of the Conversation Service that the
     *                           resource is associated with.
     * @param pathConversationSid The unique ID of the Conversation for this
     *                            participant.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     * @return ParticipantDeleter capable of executing the delete
     */
    public static ParticipantDeleter deleter(final String pathChatServiceSid,
                                             final String pathConversationSid,
                                             final String pathSid) {
        return new ParticipantDeleter(pathChatServiceSid, pathConversationSid, pathSid);
    }

    /**
     * Create a ParticipantFetcher to execute fetch.
     *
     * @param pathChatServiceSid The SID of the Conversation Service that the
     *                           resource is associated with.
     * @param pathConversationSid The unique ID of the Conversation for this
     *                            participant.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     * @return ParticipantFetcher capable of executing the fetch
     */
    public static ParticipantFetcher fetcher(final String pathChatServiceSid,
                                             final String pathConversationSid,
                                             final String pathSid) {
        return new ParticipantFetcher(pathChatServiceSid, pathConversationSid, pathSid);
    }

    /**
     * Create a ParticipantReader to execute read.
     *
     * @param pathChatServiceSid The SID of the Conversation Service that the
     *                           resource is associated with.
     * @param pathConversationSid The unique ID of the Conversation for
     *                            participants.
     * @return ParticipantReader capable of executing the read
     */
    public static ParticipantReader reader(final String pathChatServiceSid,
                                           final String pathConversationSid) {
        return new ParticipantReader(pathChatServiceSid, pathConversationSid);
    }

    /**
     * Converts a JSON String into a Participant object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Participant object represented by the provided JSON
     */
    public static Participant fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Participant object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Participant object represented by the provided JSON
     */
    public static Participant fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String chatServiceSid;
    private final String conversationSid;
    private final String sid;
    private final String identity;
    private final String attributes;
    private final Map<String, Object> messagingBinding;
    private final String roleSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;
    private final Long lastReadMessageIndex;
    private final String lastReadTimestamp;

    @JsonCreator
    private Participant(@JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("chat_service_sid")
                        final String chatServiceSid,
                        @JsonProperty("conversation_sid")
                        final String conversationSid,
                        @JsonProperty("sid")
                        final String sid,
                        @JsonProperty("identity")
                        final String identity,
                        @JsonProperty("attributes")
                        final String attributes,
                        @JsonProperty("messaging_binding")
                        final Map<String, Object> messagingBinding,
                        @JsonProperty("role_sid")
                        final String roleSid,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated,
                        @JsonProperty("url")
                        final URI url,
                        @JsonProperty("last_read_message_index")
                        final Long lastReadMessageIndex,
                        @JsonProperty("last_read_timestamp")
                        final String lastReadTimestamp) {
        this.accountSid = accountSid;
        this.chatServiceSid = chatServiceSid;
        this.conversationSid = conversationSid;
        this.sid = sid;
        this.identity = identity;
        this.attributes = attributes;
        this.messagingBinding = messagingBinding;
        this.roleSid = roleSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.lastReadMessageIndex = lastReadMessageIndex;
        this.lastReadTimestamp = lastReadTimestamp;
    }

    /**
     * Returns The unique ID of the Account responsible for this participant..
     *
     * @return The unique ID of the Account responsible for this participant.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Conversation Service that the resource is associated
     * with..
     *
     * @return The SID of the Conversation Service that the resource is associated
     *         with.
     */
    public final String getChatServiceSid() {
        return this.chatServiceSid;
    }

    /**
     * Returns The unique ID of the Conversation for this participant..
     *
     * @return The unique ID of the Conversation for this participant.
     */
    public final String getConversationSid() {
        return this.conversationSid;
    }

    /**
     * Returns A 34 character string that uniquely identifies this resource..
     *
     * @return A 34 character string that uniquely identifies this resource.
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns A unique string identifier for the conversation participant as
     * Conversation User..
     *
     * @return A unique string identifier for the conversation participant as
     *         Conversation User.
     */
    public final String getIdentity() {
        return this.identity;
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
     * Returns Information about how this participant exchanges messages with the
     * conversation..
     *
     * @return Information about how this participant exchanges messages with the
     *         conversation.
     */
    public final Map<String, Object> getMessagingBinding() {
        return this.messagingBinding;
    }

    /**
     * Returns The SID of a conversation-level Role to assign to the participant.
     *
     * @return The SID of a conversation-level Role to assign to the participant
     */
    public final String getRoleSid() {
        return this.roleSid;
    }

    /**
     * Returns The date that this resource was created..
     *
     * @return The date that this resource was created.
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The date that this resource was last updated..
     *
     * @return The date that this resource was last updated.
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns An absolute URL for this participant..
     *
     * @return An absolute URL for this participant.
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns Index of last “read” message in the Conversation for the
     * Participant..
     *
     * @return Index of last “read” message in the Conversation for the Participant.
     */
    public final Long getLastReadMessageIndex() {
        return this.lastReadMessageIndex;
    }

    /**
     * Returns Timestamp of last “read” message in the Conversation for the
     * Participant..
     *
     * @return Timestamp of last “read” message in the Conversation for the
     *         Participant.
     */
    public final String getLastReadTimestamp() {
        return this.lastReadTimestamp;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Participant other = (Participant) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(chatServiceSid, other.chatServiceSid) &&
               Objects.equals(conversationSid, other.conversationSid) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(identity, other.identity) &&
               Objects.equals(attributes, other.attributes) &&
               Objects.equals(messagingBinding, other.messagingBinding) &&
               Objects.equals(roleSid, other.roleSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url) &&
               Objects.equals(lastReadMessageIndex, other.lastReadMessageIndex) &&
               Objects.equals(lastReadTimestamp, other.lastReadTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            chatServiceSid,
                            conversationSid,
                            sid,
                            identity,
                            attributes,
                            messagingBinding,
                            roleSid,
                            dateCreated,
                            dateUpdated,
                            url,
                            lastReadMessageIndex,
                            lastReadTimestamp);
    }
}