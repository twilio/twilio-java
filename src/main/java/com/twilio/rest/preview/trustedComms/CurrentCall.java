/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.trustedComms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
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

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentCall extends Resource {
    private static final long serialVersionUID = 212286172550611L;

    /**
     * Create a CurrentCallFetcher to execute fetch.
     *
     * @return CurrentCallFetcher capable of executing the fetch
     */
    public static CurrentCallFetcher fetcher() {
        return new CurrentCallFetcher();
    }

    /**
     * Converts a JSON String into a CurrentCall object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return CurrentCall object represented by the provided JSON
     */
    public static CurrentCall fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CurrentCall.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a CurrentCall object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return CurrentCall object represented by the provided JSON
     */
    public static CurrentCall fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CurrentCall.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String bgColor;
    private final String caller;
    private final ZonedDateTime createdAt;
    private final String fontColor;
    private final String from;
    private final String logo;
    private final String manager;
    private final String reason;
    private final String shieldImg;
    private final String sid;
    private final String status;
    private final String to;
    private final URI url;
    private final String useCase;

    @JsonCreator
    private CurrentCall(@JsonProperty("bg_color")
                        final String bgColor,
                        @JsonProperty("caller")
                        final String caller,
                        @JsonProperty("created_at")
                        final String createdAt,
                        @JsonProperty("font_color")
                        final String fontColor,
                        @JsonProperty("from")
                        final String from,
                        @JsonProperty("logo")
                        final String logo,
                        @JsonProperty("manager")
                        final String manager,
                        @JsonProperty("reason")
                        final String reason,
                        @JsonProperty("shield_img")
                        final String shieldImg,
                        @JsonProperty("sid")
                        final String sid,
                        @JsonProperty("status")
                        final String status,
                        @JsonProperty("to")
                        final String to,
                        @JsonProperty("url")
                        final URI url,
                        @JsonProperty("use_case")
                        final String useCase) {
        this.bgColor = bgColor;
        this.caller = caller;
        this.createdAt = DateConverter.iso8601DateTimeFromString(createdAt);
        this.fontColor = fontColor;
        this.from = from;
        this.logo = logo;
        this.manager = manager;
        this.reason = reason;
        this.shieldImg = shieldImg;
        this.sid = sid;
        this.status = status;
        this.to = to;
        this.url = url;
        this.useCase = useCase;
    }

    /**
     * Returns Background color of the current phone call.
     *
     * @return Background color of the current phone call
     */
    public final String getBgColor() {
        return this.bgColor;
    }

    /**
     * Returns Caller name of the current phone call.
     *
     * @return Caller name of the current phone call
     */
    public final String getCaller() {
        return this.caller;
    }

    /**
     * Returns The date this current phone call was created.
     *
     * @return The date this current phone call was created
     */
    public final ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Returns Font color of the current phone call.
     *
     * @return Font color of the current phone call
     */
    public final String getFontColor() {
        return this.fontColor;
    }

    /**
     * Returns The originating phone number.
     *
     * @return The originating phone number
     */
    public final String getFrom() {
        return this.from;
    }

    /**
     * Returns Logo URL of the caller.
     *
     * @return Logo URL of the caller
     */
    public final String getLogo() {
        return this.logo;
    }

    /**
     * Returns The name of the CPS organization.
     *
     * @return The name of the CPS organization
     */
    public final String getManager() {
        return this.manager;
    }

    /**
     * Returns The business reason for this current phone call.
     *
     * @return The business reason for this current phone call
     */
    public final String getReason() {
        return this.reason;
    }

    /**
     * Returns Shield image URL that serves as authenticity proof of the current
     * phone call.
     *
     * @return Shield image URL that serves as authenticity proof of the current
     *         phone call
     */
    public final String getShieldImg() {
        return this.shieldImg;
    }

    /**
     * Returns A string that uniquely identifies this current branded phone call..
     *
     * @return A string that uniquely identifies this current branded phone call.
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The status of the current phone call.
     *
     * @return The status of the current phone call
     */
    public final String getStatus() {
        return this.status;
    }

    /**
     * Returns The terminating phone number.
     *
     * @return The terminating phone number
     */
    public final String getTo() {
        return this.to;
    }

    /**
     * Returns The URL of this resource..
     *
     * @return The URL of this resource.
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The use case for the current phone call.
     *
     * @return The use case for the current phone call
     */
    public final String getUseCase() {
        return this.useCase;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrentCall other = (CurrentCall) o;

        return Objects.equals(bgColor, other.bgColor) &&
               Objects.equals(caller, other.caller) &&
               Objects.equals(createdAt, other.createdAt) &&
               Objects.equals(fontColor, other.fontColor) &&
               Objects.equals(from, other.from) &&
               Objects.equals(logo, other.logo) &&
               Objects.equals(manager, other.manager) &&
               Objects.equals(reason, other.reason) &&
               Objects.equals(shieldImg, other.shieldImg) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(status, other.status) &&
               Objects.equals(to, other.to) &&
               Objects.equals(url, other.url) &&
               Objects.equals(useCase, other.useCase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bgColor,
                            caller,
                            createdAt,
                            fontColor,
                            from,
                            logo,
                            manager,
                            reason,
                            shieldImg,
                            sid,
                            status,
                            to,
                            url,
                            useCase);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("bgColor", bgColor)
                          .add("caller", caller)
                          .add("createdAt", createdAt)
                          .add("fontColor", fontColor)
                          .add("from", from)
                          .add("logo", logo)
                          .add("manager", manager)
                          .add("reason", reason)
                          .add("shieldImg", shieldImg)
                          .add("sid", sid)
                          .add("status", status)
                          .add("to", to)
                          .add("url", url)
                          .add("useCase", useCase)
                          .toString();
    }
}
