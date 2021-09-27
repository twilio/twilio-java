/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1.conversation.message;

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
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryReceipt extends Resource {
    private static final long serialVersionUID = 77906133631055L;

  public String toString() {
    return "DeliveryReceipt(accountSid=" + this.getAccountSid() + ", conversationSid=" + this.getConversationSid() + ", sid=" + this.getSid() + ", messageSid=" + this.getMessageSid() + ", channelMessageSid=" + this.getChannelMessageSid() + ", participantSid=" + this.getParticipantSid() + ", status=" + this.getStatus() + ", errorCode=" + this.getErrorCode() + ", dateCreated=" + this.getDateCreated() + ", dateUpdated=" + this.getDateUpdated() + ", url=" + this.getUrl() + ")";
  }

  public enum DeliveryStatus {
        READ("read"),
        FAILED("failed"),
        DELIVERED("delivered"),
        UNDELIVERED("undelivered"),
        SENT("sent");

        private final String value;

        private DeliveryStatus(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a DeliveryStatus from a string.
         * @param value string value
         * @return generated DeliveryStatus
         */
        @JsonCreator
        public static DeliveryStatus forValue(final String value) {
            return Promoter.enumFromString(value, DeliveryStatus.values());
        }
    }

    /**
     * Create a DeliveryReceiptFetcher to execute fetch.
     *
     * @param pathConversationSid The unique ID of the Conversation for this
     *                            delivery receipt.
     * @param pathMessageSid The SID of the message the delivery receipt belongs to.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     * @return DeliveryReceiptFetcher capable of executing the fetch
     */
    public static DeliveryReceiptFetcher fetcher(final String pathConversationSid,
                                                 final String pathMessageSid,
                                                 final String pathSid) {
        return new DeliveryReceiptFetcher(pathConversationSid, pathMessageSid, pathSid);
    }

    /**
     * Create a DeliveryReceiptReader to execute read.
     *
     * @param pathConversationSid The unique ID of the Conversation for this
     *                            delivery receipt.
     * @param pathMessageSid The SID of the message the delivery receipt belongs to.
     * @return DeliveryReceiptReader capable of executing the read
     */
    public static DeliveryReceiptReader reader(final String pathConversationSid,
                                               final String pathMessageSid) {
        return new DeliveryReceiptReader(pathConversationSid, pathMessageSid);
    }

    /**
     * Converts a JSON String into a DeliveryReceipt object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return DeliveryReceipt object represented by the provided JSON
     */
    public static DeliveryReceipt fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, DeliveryReceipt.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a DeliveryReceipt object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return DeliveryReceipt object represented by the provided JSON
     */
    public static DeliveryReceipt fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, DeliveryReceipt.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String conversationSid;
    private final String sid;
    private final String messageSid;
    private final String channelMessageSid;
    private final String participantSid;
    private final DeliveryReceipt.DeliveryStatus status;
    private final Integer errorCode;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private DeliveryReceipt(@JsonProperty("account_sid")
                            final String accountSid,
                            @JsonProperty("conversation_sid")
                            final String conversationSid,
                            @JsonProperty("sid")
                            final String sid,
                            @JsonProperty("message_sid")
                            final String messageSid,
                            @JsonProperty("channel_message_sid")
                            final String channelMessageSid,
                            @JsonProperty("participant_sid")
                            final String participantSid,
                            @JsonProperty("status")
                            final DeliveryReceipt.DeliveryStatus status,
                            @JsonProperty("error_code")
                            final Integer errorCode,
                            @JsonProperty("date_created")
                            final String dateCreated,
                            @JsonProperty("date_updated")
                            final String dateUpdated,
                            @JsonProperty("url")
                            final URI url) {
        this.accountSid = accountSid;
        this.conversationSid = conversationSid;
        this.sid = sid;
        this.messageSid = messageSid;
        this.channelMessageSid = channelMessageSid;
        this.participantSid = participantSid;
        this.status = status;
        this.errorCode = errorCode;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
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
     * Returns The unique ID of the Conversation for this message..
     *
     * @return The unique ID of the Conversation for this message.
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
     * Returns The SID of the message the delivery receipt belongs to.
     *
     * @return The SID of the message the delivery receipt belongs to
     */
    public final String getMessageSid() {
        return this.messageSid;
    }

    /**
     * Returns A messaging channel-specific identifier for the message delivered to
     * participant.
     *
     * @return A messaging channel-specific identifier for the message delivered to
     *         participant
     */
    public final String getChannelMessageSid() {
        return this.channelMessageSid;
    }

    /**
     * Returns The unique ID of the participant the delivery receipt belongs to..
     *
     * @return The unique ID of the participant the delivery receipt belongs to.
     */
    public final String getParticipantSid() {
        return this.participantSid;
    }

    /**
     * Returns The message delivery status.
     *
     * @return The message delivery status
     */
    public final DeliveryReceipt.DeliveryStatus getStatus() {
        return this.status;
    }

    /**
     * Returns The message <a
     * href="https://www.twilio.com/docs/sms/api/message-resource#delivery-related-errors">delivery
     * error code</a> for a `failed` status.
     *
     * @return The message <a
     *         href="https://www.twilio.com/docs/sms/api/message-resource#delivery-related-errors">delivery
     *         error code</a> for a `failed` status
     */
    public final Integer getErrorCode() {
        return this.errorCode;
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
     * Returns An absolute URL for this delivery receipt..
     *
     * @return An absolute URL for this delivery receipt.
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

        DeliveryReceipt other = (DeliveryReceipt) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(conversationSid, other.conversationSid) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(messageSid, other.messageSid) &&
               Objects.equals(channelMessageSid, other.channelMessageSid) &&
               Objects.equals(participantSid, other.participantSid) &&
               Objects.equals(status, other.status) &&
               Objects.equals(errorCode, other.errorCode) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            conversationSid,
                            sid,
                            messageSid,
                            channelMessageSid,
                            participantSid,
                            status,
                            errorCode,
                            dateCreated,
                            dateUpdated,
                            url);
    }
}
