/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.wireless.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
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
import java.time.ZonedDateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Command extends Resource {
    private static final long serialVersionUID = 13823495312115L;

    public enum Direction {
        FROM_SIM("from_sim"),
        TO_SIM("to_sim");

        private final String value;

        private Direction(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Direction from a string.
         * @param value string value
         * @return generated Direction
         */
        @JsonCreator
        public static Direction forValue(final String value) {
            return Promoter.enumFromString(value, Direction.values());
        }
    }

    public enum Status {
        QUEUED("queued"),
        SENT("sent"),
        DELIVERED("delivered"),
        RECEIVED("received"),
        FAILED("failed");

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

    public enum CommandMode {
        TEXT("text"),
        BINARY("binary");

        private final String value;

        private CommandMode(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a CommandMode from a string.
         * @param value string value
         * @return generated CommandMode
         */
        @JsonCreator
        public static CommandMode forValue(final String value) {
            return Promoter.enumFromString(value, CommandMode.values());
        }
    }

    public enum Transport {
        SMS("sms"),
        IP("ip");

        private final String value;

        private Transport(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Transport from a string.
         * @param value string value
         * @return generated Transport
         */
        @JsonCreator
        public static Transport forValue(final String value) {
            return Promoter.enumFromString(value, Transport.values());
        }
    }

    /**
     * Create a CommandFetcher to execute fetch.
     *
     * @param pathSid The SID that identifies the resource to fetch
     * @return CommandFetcher capable of executing the fetch
     */
    public static CommandFetcher fetcher(final String pathSid) {
        return new CommandFetcher(pathSid);
    }

    /**
     * Create a CommandReader to execute read.
     *
     * @return CommandReader capable of executing the read
     */
    public static CommandReader reader() {
        return new CommandReader();
    }

    /**
     * Create a CommandCreator to execute create.
     *
     * @param command The message body of the Command or a Base64 encoded byte
     *                string in binary mode
     * @return CommandCreator capable of executing the create
     */
    public static CommandCreator creator(final String command) {
        return new CommandCreator(command);
    }

    /**
     * Create a CommandDeleter to execute delete.
     *
     * @param pathSid The SID that identifies the resource to delete
     * @return CommandDeleter capable of executing the delete
     */
    public static CommandDeleter deleter(final String pathSid) {
        return new CommandDeleter(pathSid);
    }

    /**
     * Converts a JSON String into a Command object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Command object represented by the provided JSON
     */
    public static Command fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Command.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Command object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Command object represented by the provided JSON
     */
    public static Command fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Command.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String simSid;
    private final String command;
    private final Command.CommandMode commandMode;
    private final Command.Transport transport;
    private final Boolean deliveryReceiptRequested;
    private final Command.Status status;
    private final Command.Direction direction;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private Command(@JsonProperty("sid")
                    final String sid,
                    @JsonProperty("account_sid")
                    final String accountSid,
                    @JsonProperty("sim_sid")
                    final String simSid,
                    @JsonProperty("command")
                    final String command,
                    @JsonProperty("command_mode")
                    final Command.CommandMode commandMode,
                    @JsonProperty("transport")
                    final Command.Transport transport,
                    @JsonProperty("delivery_receipt_requested")
                    final Boolean deliveryReceiptRequested,
                    @JsonProperty("status")
                    final Command.Status status,
                    @JsonProperty("direction")
                    final Command.Direction direction,
                    @JsonProperty("date_created")
                    final String dateCreated,
                    @JsonProperty("date_updated")
                    final String dateUpdated,
                    @JsonProperty("url")
                    final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.simSid = simSid;
        this.command = command;
        this.commandMode = commandMode;
        this.transport = transport;
        this.deliveryReceiptRequested = deliveryReceiptRequested;
        this.status = status;
        this.direction = direction;
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
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Sim resource that the Command was sent to or from.
     *
     * @return The SID of the Sim resource that the Command was sent to or from
     */
    public final String getSimSid() {
        return this.simSid;
    }

    /**
     * Returns The message being sent to or from the SIM.
     *
     * @return The message being sent to or from the SIM
     */
    public final String getCommand() {
        return this.command;
    }

    /**
     * Returns The mode used to send the SMS message.
     *
     * @return The mode used to send the SMS message
     */
    public final Command.CommandMode getCommandMode() {
        return this.commandMode;
    }

    /**
     * Returns The type of transport used.
     *
     * @return The type of transport used
     */
    public final Command.Transport getTransport() {
        return this.transport;
    }

    /**
     * Returns Whether to request a delivery receipt.
     *
     * @return Whether to request a delivery receipt
     */
    public final Boolean getDeliveryReceiptRequested() {
        return this.deliveryReceiptRequested;
    }

    /**
     * Returns The status of the Command.
     *
     * @return The status of the Command
     */
    public final Command.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The direction of the Command.
     *
     * @return The direction of the Command
     */
    public final Command.Direction getDirection() {
        return this.direction;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was last updated
     * format.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     *         format
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
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

        Command other = (Command) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(simSid, other.simSid) &&
               Objects.equals(command, other.command) &&
               Objects.equals(commandMode, other.commandMode) &&
               Objects.equals(transport, other.transport) &&
               Objects.equals(deliveryReceiptRequested, other.deliveryReceiptRequested) &&
               Objects.equals(status, other.status) &&
               Objects.equals(direction, other.direction) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            simSid,
                            command,
                            commandMode,
                            transport,
                            deliveryReceiptRequested,
                            status,
                            direction,
                            dateCreated,
                            dateUpdated,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("simSid", simSid)
                          .add("command", command)
                          .add("commandMode", commandMode)
                          .add("transport", transport)
                          .add("deliveryReceiptRequested", deliveryReceiptRequested)
                          .add("status", status)
                          .add("direction", direction)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .toString();
    }
}
