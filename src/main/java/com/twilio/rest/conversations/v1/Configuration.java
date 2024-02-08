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

package com.twilio.rest.conversations.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Configuration extends Resource {

    private static final long serialVersionUID = 31286932068884L;

    public static ConfigurationFetcher fetcher() {
        return new ConfigurationFetcher();
    }

    public static ConfigurationUpdater updater() {
        return new ConfigurationUpdater();
    }

    /**
     * Converts a JSON String into a Configuration object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Configuration object represented by the provided JSON
     */
    public static Configuration fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Configuration.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Configuration object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Configuration object represented by the provided JSON
     */
    public static Configuration fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Configuration.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String defaultChatServiceSid;
    private final String defaultMessagingServiceSid;
    private final String defaultInactiveTimer;
    private final String defaultClosedTimer;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Configuration(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty(
            "default_chat_service_sid"
        ) final String defaultChatServiceSid,
        @JsonProperty(
            "default_messaging_service_sid"
        ) final String defaultMessagingServiceSid,
        @JsonProperty(
            "default_inactive_timer"
        ) final String defaultInactiveTimer,
        @JsonProperty("default_closed_timer") final String defaultClosedTimer,
        @JsonProperty("url") final URI url,
        @JsonProperty("links") final Map<String, String> links
    ) {
        this.accountSid = accountSid;
        this.defaultChatServiceSid = defaultChatServiceSid;
        this.defaultMessagingServiceSid = defaultMessagingServiceSid;
        this.defaultInactiveTimer = defaultInactiveTimer;
        this.defaultClosedTimer = defaultClosedTimer;
        this.url = url;
        this.links = links;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getDefaultChatServiceSid() {
        return this.defaultChatServiceSid;
    }

    public final String getDefaultMessagingServiceSid() {
        return this.defaultMessagingServiceSid;
    }

    public final String getDefaultInactiveTimer() {
        return this.defaultInactiveTimer;
    }

    public final String getDefaultClosedTimer() {
        return this.defaultClosedTimer;
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

        Configuration other = (Configuration) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(
                defaultChatServiceSid,
                other.defaultChatServiceSid
            ) &&
            Objects.equals(
                defaultMessagingServiceSid,
                other.defaultMessagingServiceSid
            ) &&
            Objects.equals(defaultInactiveTimer, other.defaultInactiveTimer) &&
            Objects.equals(defaultClosedTimer, other.defaultClosedTimer) &&
            Objects.equals(url, other.url) &&
            Objects.equals(links, other.links)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
            defaultChatServiceSid,
            defaultMessagingServiceSid,
            defaultInactiveTimer,
            defaultClosedTimer,
            url,
            links
        );
    }
}
