package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.ApplicationCreator;
import com.twilio.sdk.deleters.ApplicationDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.ApplicationFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.readers.ApplicationReader;
import com.twilio.sdk.updaters.ApplicationUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

public class Application extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final URI smsStatusCallback;
    private final HttpMethod smsFallbackMethod;
    private final URI messageStatusCallback;
    private final DateTime dateUpdated;
    private final HttpMethod statusCallbackMethod;
    private final Boolean voiceCallerIdLookup;
    private final String friendlyName;
    private final String uri;
    private final URI smsFallbackUrl;
    private final String accountSid;
    private final URI voiceUrl;
    private final HttpMethod smsMethod;
    private final HttpMethod voiceMethod;
    private final String sid;
    private final DateTime dateCreated;
    private final URI smsUrl;
    private final URI statusCallback;
    private final HttpMethod voiceFallbackMethod;
    private final String apiVersion;
    private final URI voiceFallbackUrl;

    @JsonCreator
    private Application(@JsonProperty("sms_status_callback") final URI smsStatusCallback,
                        @JsonProperty("sms_fallback_method") final HttpMethod smsFallbackMethod,
                        @JsonProperty("message_status_callback") final URI messageStatusCallback,
                        @JsonProperty("date_updated") final String dateUpdated,
                        @JsonProperty("status_callback_method") final HttpMethod statusCallbackMethod,
                        @JsonProperty("voice_caller_id_lookup") final Boolean voiceCallerIdLookup,
                        @JsonProperty("friendly_name") final String friendlyName, @JsonProperty("uri") final String uri,
                        @JsonProperty("sms_fallback_url") final URI smsFallbackUrl,
                        @JsonProperty("account_sid") final String accountSid,
                        @JsonProperty("voice_url") final URI voiceUrl,
                        @JsonProperty("sms_method") final HttpMethod smsMethod,
                        @JsonProperty("voice_method") final HttpMethod voiceMethod,
                        @JsonProperty("sid") final String sid, @JsonProperty("date_created") final String dateCreated,
                        @JsonProperty("sms_url") final URI smsUrl,
                        @JsonProperty("status_callback") final URI statusCallback,
                        @JsonProperty("voice_fallback_method") final HttpMethod voiceFallbackMethod,
                        @JsonProperty("api_version") final String apiVersion,
                        @JsonProperty("voice_fallback_url") final URI voiceFallbackUrl) {
        this.smsStatusCallback = smsStatusCallback;
        this.smsFallbackMethod = smsFallbackMethod;
        this.messageStatusCallback = messageStatusCallback;
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.statusCallbackMethod = statusCallbackMethod;
        this.voiceCallerIdLookup = voiceCallerIdLookup;
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.smsFallbackUrl = smsFallbackUrl;
        this.accountSid = accountSid;
        this.voiceUrl = voiceUrl;
        this.smsMethod = smsMethod;
        this.voiceMethod = voiceMethod;
        this.sid = sid;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.smsUrl = smsUrl;
        this.statusCallback = statusCallback;
        this.voiceFallbackMethod = voiceFallbackMethod;
        this.apiVersion = apiVersion;
        this.voiceFallbackUrl = voiceFallbackUrl;

    }

    public static ApplicationCreator create(final String friendlyName) {
        return new ApplicationCreator(friendlyName);
    }

    public static ApplicationDeleter delete(final String sid) {
        return new ApplicationDeleter(sid);
    }

    public static ApplicationDeleter delete(final Application target) {
        return new ApplicationDeleter(target);
    }

    public static ApplicationFetcher fetch(final String sid) {
        return new ApplicationFetcher(sid);
    }

    public static ApplicationReader list() {
        return new ApplicationReader();
    }

    public static ApplicationUpdater update(final Application target) {
        return new ApplicationUpdater(target);
    }

    public static ApplicationUpdater update(final String sid) {
        return new ApplicationUpdater(sid);
    }

    public static Application fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Application.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Application fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Application.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final URI getSmsStatusCallback() {
        return smsStatusCallback;
    }

    public final HttpMethod getSmsFallbackMethod() {
        return smsFallbackMethod;
    }

    public final URI getMessageStatusCallback() {
        return messageStatusCallback;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final HttpMethod getStatusCallbackMethod() {
        return statusCallbackMethod;
    }

    public final Boolean getVoiceCallerIdLookup() {
        return voiceCallerIdLookup;
    }

    public final String getFriendlyName() {
        return friendlyName;
    }

    public final String getUri() {
        return uri;
    }

    public final URI getSmsFallbackUrl() {
        return smsFallbackUrl;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final URI getVoiceUrl() {
        return voiceUrl;
    }

    public final HttpMethod getSmsMethod() {
        return smsMethod;
    }

    public final HttpMethod getVoiceMethod() {
        return voiceMethod;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final URI getSmsUrl() {
        return smsUrl;
    }

    public final URI getStatusCallback() {
        return statusCallback;
    }

    public final HttpMethod getVoiceFallbackMethod() {
        return voiceFallbackMethod;
    }

    public final String getApiVersion() {
        return apiVersion;
    }

    public final URI getVoiceFallbackUrl() {
        return voiceFallbackUrl;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Application self = (Application) o;

        return (Objects.equals(smsStatusCallback, self.smsStatusCallback) &&
                Objects.equals(smsFallbackMethod, self.smsFallbackMethod) &&
                Objects.equals(messageStatusCallback, self.messageStatusCallback) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(statusCallbackMethod, self.statusCallbackMethod) &&
                Objects.equals(voiceCallerIdLookup, self.voiceCallerIdLookup) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(smsFallbackUrl, self.smsFallbackUrl) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(voiceUrl, self.voiceUrl) &&
                Objects.equals(smsMethod, self.smsMethod) &&
                Objects.equals(voiceMethod, self.voiceMethod) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(smsUrl, self.smsUrl) &&
                Objects.equals(statusCallback, self.statusCallback) &&
                Objects.equals(voiceFallbackMethod, self.voiceFallbackMethod) &&
                Objects.equals(apiVersion, self.apiVersion) &&
                Objects.equals(voiceFallbackUrl, self.voiceFallbackUrl));
    }

    @Override
    public int hashCode() {
        return Objects.hash(smsStatusCallback, smsFallbackMethod, messageStatusCallback, dateUpdated,
                            statusCallbackMethod, voiceCallerIdLookup, friendlyName, uri, smsFallbackUrl, accountSid,
                            voiceUrl, smsMethod, voiceMethod, sid, dateCreated, smsUrl, statusCallback,
                            voiceFallbackMethod, apiVersion, voiceFallbackUrl);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("smsStatusCallback", smsStatusCallback)
                          .add("smsFallbackMethod", smsFallbackMethod)
                          .add("messageStatusCallback", messageStatusCallback)
                          .add("dateUpdated", dateUpdated)
                          .add("statusCallbackMethod", statusCallbackMethod)
                          .add("voiceCallerIdLookup", voiceCallerIdLookup)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("smsFallbackUrl", smsFallbackUrl)
                          .add("accountSid", accountSid)
                          .add("voiceUrl", voiceUrl)
                          .add("smsMethod", smsMethod)
                          .add("voiceMethod", voiceMethod)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("smsUrl", smsUrl)
                          .add("statusCallback", statusCallback)
                          .add("voiceFallbackMethod", voiceFallbackMethod)
                          .add("apiVersion", apiVersion)
                          .add("voiceFallbackUrl", voiceFallbackUrl)
                          .toString();
    }
}
