/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Preview
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.preview.wireless;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Command extends Resource {

    private static final long serialVersionUID = 211711437701945L;

    public static CommandCreator creator(final String command) {
        return new CommandCreator(command);
    }

    public static CommandFetcher fetcher(final String pathSid) {
        return new CommandFetcher(pathSid);
    }

    public static CommandReader reader() {
        return new CommandReader();
    }

    /**
     * Converts a JSON String into a Command object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Command object represented by the provided JSON
     */
    public static Command fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
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
    public static Command fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
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
    private final String deviceSid;
    private final String simSid;
    private final String command;
    private final String commandMode;
    private final String status;
    private final String direction;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private Command(
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("device_sid") final String deviceSid,
        @JsonProperty("sim_sid") final String simSid,
        @JsonProperty("command") final String command,
        @JsonProperty("command_mode") final String commandMode,
        @JsonProperty("status") final String status,
        @JsonProperty("direction") final String direction,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("url") final URI url
    ) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.deviceSid = deviceSid;
        this.simSid = simSid;
        this.command = command;
        this.commandMode = commandMode;
        this.status = status;
        this.direction = direction;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getDeviceSid() {
        return this.deviceSid;
    }

    public final String getSimSid() {
        return this.simSid;
    }

    public final String getCommand() {
        return this.command;
    }

    public final String getCommandMode() {
        return this.commandMode;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getDirection() {
        return this.direction;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

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

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(deviceSid, other.deviceSid) &&
            Objects.equals(simSid, other.simSid) &&
            Objects.equals(command, other.command) &&
            Objects.equals(commandMode, other.commandMode) &&
            Objects.equals(status, other.status) &&
            Objects.equals(direction, other.direction) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(url, other.url)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
            accountSid,
            deviceSid,
            simSid,
            command,
            commandMode,
            status,
            direction,
            dateCreated,
            dateUpdated,
            url
        );
    }
}
