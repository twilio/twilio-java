package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.IncomingPhoneNumberCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.IncomingPhoneNumberFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.readers.IncomingPhoneNumberReader;
import com.twilio.sdk.updaters.IncomingPhoneNumberUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

public class IncomingPhoneNumber extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String phoneNumber;
    private final Boolean voiceCallerIdLookup;
    private final DateTime dateUpdated;
    private final String voiceApplicationSid;
    private final HttpMethod smsFallbackMethod;
    private final String friendlyName;
    private final String uri;
    private final URI smsFallbackUrl;
    private final HttpMethod voiceMethod;
    private final URI voiceUrl;
    private final String smsApplicationSid;
    private final HttpMethod statusCallbackMethod;
    private final String sid;
    private final DateTime dateCreated;
    private final URI voiceFallbackUrl;
    private final HttpMethod voiceFallbackMethod;
    private final String apiVersion;
    private final URI statusCallback;

    @JsonCreator
    private IncomingPhoneNumber(@JsonProperty("phone_number") final String phoneNumber,
                                @JsonProperty("voice_caller_id_lookup") final Boolean voiceCallerIdLookup,
                                @JsonProperty("date_updated") final String dateUpdated,
                                @JsonProperty("voice_application_sid") final String voiceApplicationSid,
                                @JsonProperty("sms_fallback_method") final HttpMethod smsFallbackMethod,
                                @JsonProperty("friendly_name") final String friendlyName,
                                @JsonProperty("uri") final String uri,
                                @JsonProperty("sms_fallback_url") final URI smsFallbackUrl,
                                @JsonProperty("voice_method") final HttpMethod voiceMethod,
                                @JsonProperty("voice_url") final URI voiceUrl,
                                @JsonProperty("sms_application_sid") final String smsApplicationSid,
                                @JsonProperty("status_callback_method") final HttpMethod statusCallbackMethod,
                                @JsonProperty("sid") final String sid,
                                @JsonProperty("date_created") final String dateCreated,
                                @JsonProperty("voice_fallback_url") final URI voiceFallbackUrl,
                                @JsonProperty("voice_fallback_method") final HttpMethod voiceFallbackMethod,
                                @JsonProperty("api_version") final String apiVersion,
                                @JsonProperty("status_callback") final URI statusCallback) {
        this.phoneNumber = phoneNumber;
        this.voiceCallerIdLookup = voiceCallerIdLookup;
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.voiceApplicationSid = voiceApplicationSid;
        this.smsFallbackMethod = smsFallbackMethod;
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.smsFallbackUrl = smsFallbackUrl;
        this.voiceMethod = voiceMethod;
        this.voiceUrl = voiceUrl;
        this.smsApplicationSid = smsApplicationSid;
        this.statusCallbackMethod = statusCallbackMethod;
        this.sid = sid;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.voiceFallbackUrl = voiceFallbackUrl;
        this.voiceFallbackMethod = voiceFallbackMethod;
        this.apiVersion = apiVersion;
        this.statusCallback = statusCallback;

    }

    public static IncomingPhoneNumberCreator create() {
        return new IncomingPhoneNumberCreator();
    }

    public static IncomingPhoneNumberFetcher fetch(final String sid) {
        return new IncomingPhoneNumberFetcher(sid);
    }

    public static IncomingPhoneNumberReader list() {
        return new IncomingPhoneNumberReader();
    }

    public static IncomingPhoneNumberUpdater update(final IncomingPhoneNumber target) {
        return new IncomingPhoneNumberUpdater(target);
    }

    public static IncomingPhoneNumberUpdater update(final String sid) {
        return new IncomingPhoneNumberUpdater(sid);
    }

    public static IncomingPhoneNumber fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, IncomingPhoneNumber.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static IncomingPhoneNumber fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, IncomingPhoneNumber.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final Boolean getVoiceCallerIdLookup() {
        return voiceCallerIdLookup;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final String getVoiceApplicationSid() {
        return voiceApplicationSid;
    }

    public final HttpMethod getSmsFallbackMethod() {
        return smsFallbackMethod;
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

    public final HttpMethod getVoiceMethod() {
        return voiceMethod;
    }

    public final URI getVoiceUrl() {
        return voiceUrl;
    }

    public final String getSmsApplicationSid() {
        return smsApplicationSid;
    }

    public final HttpMethod getStatusCallbackMethod() {
        return statusCallbackMethod;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final URI getVoiceFallbackUrl() {
        return voiceFallbackUrl;
    }

    public final HttpMethod getVoiceFallbackMethod() {
        return voiceFallbackMethod;
    }

    public final String getApiVersion() {
        return apiVersion;
    }

    public final URI getStatusCallback() {
        return statusCallback;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IncomingPhoneNumber self = (IncomingPhoneNumber) o;

        return (Objects.equals(phoneNumber, self.phoneNumber) &&
                Objects.equals(voiceCallerIdLookup, self.voiceCallerIdLookup) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(voiceApplicationSid, self.voiceApplicationSid) &&
                Objects.equals(smsFallbackMethod, self.smsFallbackMethod) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(smsFallbackUrl, self.smsFallbackUrl) &&
                Objects.equals(voiceMethod, self.voiceMethod) &&
                Objects.equals(voiceUrl, self.voiceUrl) &&
                Objects.equals(smsApplicationSid, self.smsApplicationSid) &&
                Objects.equals(statusCallbackMethod, self.statusCallbackMethod) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(voiceFallbackUrl, self.voiceFallbackUrl) &&
                Objects.equals(voiceFallbackMethod, self.voiceFallbackMethod) &&
                Objects.equals(apiVersion, self.apiVersion) &&
                Objects.equals(statusCallback, self.statusCallback));
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, voiceCallerIdLookup, dateUpdated, voiceApplicationSid, smsFallbackMethod,
                            friendlyName, uri, smsFallbackUrl, voiceMethod, voiceUrl, smsApplicationSid,
                            statusCallbackMethod, sid, dateCreated, voiceFallbackUrl, voiceFallbackMethod, apiVersion,
                            statusCallback);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("phoneNumber", phoneNumber)
                          .add("voiceCallerIdLookup", voiceCallerIdLookup)
                          .add("dateUpdated", dateUpdated)
                          .add("voiceApplicationSid", voiceApplicationSid)
                          .add("smsFallbackMethod", smsFallbackMethod)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("smsFallbackUrl", smsFallbackUrl)
                          .add("voiceMethod", voiceMethod)
                          .add("voiceUrl", voiceUrl)
                          .add("smsApplicationSid", smsApplicationSid)
                          .add("statusCallbackMethod", statusCallbackMethod)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("voiceFallbackUrl", voiceFallbackUrl)
                          .add("voiceFallbackMethod", voiceFallbackMethod)
                          .add("apiVersion", apiVersion)
                          .add("statusCallback", statusCallback)
                          .toString();
    }

    public enum Status {
        ACTIVE("active"), SUSPENDED("suspended"), CLOSED("closed");
        private final String status;

        private Status(final String status) {
            this.status = status;
        }

        public String toString() {
            return status;
        }

        @JsonCreator
        public static Status forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Status.valueOf(munged);
        }
    }

    public enum Type {
        TRIAL("Trial"), FULL("Full");
        private final String type;

        private Type(final String type) {
            this.type = type;
        }

        public String toString() {
            return type;
        }

        @JsonCreator
        public static Status forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Status.valueOf(munged);
        }
    }
}
