/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.serverless.v1.service.environment;

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
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Log extends Resource {
    private static final long serialVersionUID = 212890795162634L;

    public enum Level {
        INFO("info"),
        WARN("warn"),
        ERROR("error");

        private final String value;

        private Level(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Level from a string.
         * @param value string value
         * @return generated Level
         */
        @JsonCreator
        public static Level forValue(final String value) {
            return Promoter.enumFromString(value, Level.values());
        }
    }

    /**
     * Create a LogReader to execute read.
     *
     * @param pathServiceSid The SID of the Service to read the Log resource from
     * @param pathEnvironmentSid The SID of the environment with the Log resources
     *                           to read
     * @return LogReader capable of executing the read
     */
    public static LogReader reader(final String pathServiceSid,
                                   final String pathEnvironmentSid) {
        return new LogReader(pathServiceSid, pathEnvironmentSid);
    }

    /**
     * Create a LogFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Service to fetch the Log resource from
     * @param pathEnvironmentSid The SID of the environment with the Log resource
     *                           to fetch
     * @param pathSid The SID that identifies the Log resource to fetch
     * @return LogFetcher capable of executing the fetch
     */
    public static LogFetcher fetcher(final String pathServiceSid,
                                     final String pathEnvironmentSid,
                                     final String pathSid) {
        return new LogFetcher(pathServiceSid, pathEnvironmentSid, pathSid);
    }

    /**
     * Converts a JSON String into a Log object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Log object represented by the provided JSON
     */
    public static Log fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Log.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Log object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Log object represented by the provided JSON
     */
    public static Log fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Log.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String environmentSid;
    private final String buildSid;
    private final String deploymentSid;
    private final String functionSid;
    private final String requestSid;
    private final Log.Level level;
    private final String message;
    private final DateTime dateCreated;
    private final URI url;

    @JsonCreator
    private Log(@JsonProperty("sid")
                final String sid,
                @JsonProperty("account_sid")
                final String accountSid,
                @JsonProperty("service_sid")
                final String serviceSid,
                @JsonProperty("environment_sid")
                final String environmentSid,
                @JsonProperty("build_sid")
                final String buildSid,
                @JsonProperty("deployment_sid")
                final String deploymentSid,
                @JsonProperty("function_sid")
                final String functionSid,
                @JsonProperty("request_sid")
                final String requestSid,
                @JsonProperty("level")
                final Log.Level level,
                @JsonProperty("message")
                final String message,
                @JsonProperty("date_created")
                final String dateCreated,
                @JsonProperty("url")
                final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.environmentSid = environmentSid;
        this.buildSid = buildSid;
        this.deploymentSid = deploymentSid;
        this.functionSid = functionSid;
        this.requestSid = requestSid;
        this.level = level;
        this.message = message;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.url = url;
    }

    /**
     * Returns The unique string that identifies the Log resource.
     *
     * @return The unique string that identifies the Log resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the Account that created the Log resource.
     *
     * @return The SID of the Account that created the Log resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Service that the Log resource is associated with.
     *
     * @return The SID of the Service that the Log resource is associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The SID of the environment in which the log occurred.
     *
     * @return The SID of the environment in which the log occurred
     */
    public final String getEnvironmentSid() {
        return this.environmentSid;
    }

    /**
     * Returns The SID of the build that corresponds to the log.
     *
     * @return The SID of the build that corresponds to the log
     */
    public final String getBuildSid() {
        return this.buildSid;
    }

    /**
     * Returns The SID of the deployment that corresponds to the log.
     *
     * @return The SID of the deployment that corresponds to the log
     */
    public final String getDeploymentSid() {
        return this.deploymentSid;
    }

    /**
     * Returns The SID of the function whose invocation produced the log.
     *
     * @return The SID of the function whose invocation produced the log
     */
    public final String getFunctionSid() {
        return this.functionSid;
    }

    /**
     * Returns The SID of the request associated with the log.
     *
     * @return The SID of the request associated with the log
     */
    public final String getRequestSid() {
        return this.requestSid;
    }

    /**
     * Returns The log level.
     *
     * @return The log level
     */
    public final Log.Level getLevel() {
        return this.level;
    }

    /**
     * Returns The log message.
     *
     * @return The log message
     */
    public final String getMessage() {
        return this.message;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the Log resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the Log resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The absolute URL of the Log resource.
     *
     * @return The absolute URL of the Log resource
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

        Log other = (Log) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(environmentSid, other.environmentSid) &&
               Objects.equals(buildSid, other.buildSid) &&
               Objects.equals(deploymentSid, other.deploymentSid) &&
               Objects.equals(functionSid, other.functionSid) &&
               Objects.equals(requestSid, other.requestSid) &&
               Objects.equals(level, other.level) &&
               Objects.equals(message, other.message) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            environmentSid,
                            buildSid,
                            deploymentSid,
                            functionSid,
                            requestSid,
                            level,
                            message,
                            dateCreated,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("serviceSid", serviceSid)
                          .add("environmentSid", environmentSid)
                          .add("buildSid", buildSid)
                          .add("deploymentSid", deploymentSid)
                          .add("functionSid", functionSid)
                          .add("requestSid", requestSid)
                          .add("level", level)
                          .add("message", message)
                          .add("dateCreated", dateCreated)
                          .add("url", url)
                          .toString();
    }
}