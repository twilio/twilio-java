/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.bulkExports.export;

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
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ExportCustomJob extends Resource {
    private static final long serialVersionUID = 147268074422838L;

    /**
     * Create a ExportCustomJobReader to execute read.
     *
     * @param pathResourceType The type of communication – Messages, Calls
     * @return ExportCustomJobReader capable of executing the read
     */
    public static ExportCustomJobReader reader(final String pathResourceType) {
        return new ExportCustomJobReader(pathResourceType);
    }

    /**
     * Create a ExportCustomJobCreator to execute create.
     *
     * @param pathResourceType The type of communication – Messages or Calls
     * @param startDay The start day for the custom export specified as a string in
     *                 the format of yyyy-mm-dd
     * @param endDay The end day for the custom export specified as a string in the
     *               format of yyyy-mm-dd. End day is inclusive and must be 2 days
     *               earlier than the current UTC day.
     * @param friendlyName The friendly name specified when creating the job
     * @return ExportCustomJobCreator capable of executing the create
     */
    public static ExportCustomJobCreator creator(final String pathResourceType,
                                                 final String startDay,
                                                 final String endDay,
                                                 final String friendlyName) {
        return new ExportCustomJobCreator(pathResourceType, startDay, endDay, friendlyName);
    }

    /**
     * Converts a JSON String into a ExportCustomJob object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return ExportCustomJob object represented by the provided JSON
     */
    public static ExportCustomJob fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ExportCustomJob.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a ExportCustomJob object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return ExportCustomJob object represented by the provided JSON
     */
    public static ExportCustomJob fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ExportCustomJob.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String friendlyName;
    private final String resourceType;
    private final String startDay;
    private final String endDay;
    private final String webhookUrl;
    private final String webhookMethod;
    private final String email;
    private final String jobSid;
    private final Map<String, Object> details;

    @JsonCreator
    private ExportCustomJob(@JsonProperty("friendly_name")
                            final String friendlyName,
                            @JsonProperty("resource_type")
                            final String resourceType,
                            @JsonProperty("start_day")
                            final String startDay,
                            @JsonProperty("end_day")
                            final String endDay,
                            @JsonProperty("webhook_url")
                            final String webhookUrl,
                            @JsonProperty("webhook_method")
                            final String webhookMethod,
                            @JsonProperty("email")
                            final String email,
                            @JsonProperty("job_sid")
                            final String jobSid,
                            @JsonProperty("details")
                            final Map<String, Object> details) {
        this.friendlyName = friendlyName;
        this.resourceType = resourceType;
        this.startDay = startDay;
        this.endDay = endDay;
        this.webhookUrl = webhookUrl;
        this.webhookMethod = webhookMethod;
        this.email = email;
        this.jobSid = jobSid;
        this.details = details;
    }

    /**
     * Returns The friendly name specified when creating the job.
     *
     * @return The friendly name specified when creating the job
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The type of communication – Messages, Calls.
     *
     * @return The type of communication – Messages, Calls
     */
    public final String getResourceType() {
        return this.resourceType;
    }

    /**
     * Returns The start day for the custom export specified as a string in the
     * format of yyyy-MM-dd.
     *
     * @return The start day for the custom export specified as a string in the
     *         format of yyyy-MM-dd
     */
    public final String getStartDay() {
        return this.startDay;
    }

    /**
     * Returns The end day for the custom export specified as a string in the format
     * of yyyy-MM-dd.
     *
     * @return The end day for the custom export specified as a string in the
     *         format of yyyy-MM-dd
     */
    public final String getEndDay() {
        return this.endDay;
    }

    /**
     * Returns The optional webhook url called on completion.
     *
     * @return The optional webhook url called on completion
     */
    public final String getWebhookUrl() {
        return this.webhookUrl;
    }

    /**
     * Returns This is the method used to call the webhook.
     *
     * @return This is the method used to call the webhook
     */
    public final String getWebhookMethod() {
        return this.webhookMethod;
    }

    /**
     * Returns The optional email to send the completion notification to.
     *
     * @return The optional email to send the completion notification to
     */
    public final String getEmail() {
        return this.email;
    }

    /**
     * Returns The unique job_sid returned when the custom export was created. This
     * can be used to look up the status of the job..
     *
     * @return The unique job_sid returned when the custom export was created. This
     *         can be used to look up the status of the job.
     */
    public final String getJobSid() {
        return this.jobSid;
    }

    /**
     * Returns The details.
     *
     * @return The details
     */
    public final Map<String, Object> getDetails() {
        return this.details;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExportCustomJob other = (ExportCustomJob) o;

        return Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(resourceType, other.resourceType) &&
               Objects.equals(startDay, other.startDay) &&
               Objects.equals(endDay, other.endDay) &&
               Objects.equals(webhookUrl, other.webhookUrl) &&
               Objects.equals(webhookMethod, other.webhookMethod) &&
               Objects.equals(email, other.email) &&
               Objects.equals(jobSid, other.jobSid) &&
               Objects.equals(details, other.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendlyName,
                            resourceType,
                            startDay,
                            endDay,
                            webhookUrl,
                            webhookMethod,
                            email,
                            jobSid,
                            details);
    }
}