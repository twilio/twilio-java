package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.SipDomainCreator;
import com.twilio.sdk.deleters.SipDomainDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.SipDomainFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.readers.SipDomainReader;
import com.twilio.sdk.updaters.SipDomainUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SipDomain extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String sid;
    private final String friendlyName;
    private final String accountSid;
    private final String apiVersion;
    private final String domainName;
    private final List<AuthType> authType; // This should probably turn into something better
    private final URI voiceUrl;
    private final HttpMethod voiceMethod;
    private final URI voiceFallbackUrl;
    private final HttpMethod voiceFallbackMethod;
    private final URI voiceStatusCallbackUrl;
    private final HttpMethod voiceStatusCallbackMethod;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final Map<String, String> subresourceUris;
    private final String uri;

    @JsonCreator
    private SipDomain(@JsonProperty("sid") final String sid,
                      @JsonProperty("friendly_name") final String friendlyName,
                      @JsonProperty("account_sid") final String accountSid,
                      @JsonProperty("api_version") final String apiVersion,
                      @JsonProperty("domain_name") final String domainName,
                      @JsonProperty("auth_type") final String authType,
                      @JsonProperty("voice_url") final URI voiceUrl,
                      @JsonProperty("voice_method") final HttpMethod voiceMethod,
                      @JsonProperty("voice_fallback_url") final URI voiceFallbackUrl,
                      @JsonProperty("voice_fallback_method") final HttpMethod voiceFallbackMethod,
                      @JsonProperty("voice_status_callback_url") final URI voiceStatusCallbackUrl,
                      @JsonProperty("voice_status_callback_method") final HttpMethod voiceStatusCallbackMethod,
                      @JsonProperty("date_created") final String dateCreated,
                      @JsonProperty("date_updated") final String dateUpdated,
                      @JsonProperty("subresource_uris") final Map<String, String> subresourceUris,
                      @JsonProperty("uri") final String uri) {
        this.sid = sid;
        this.friendlyName = friendlyName;
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.domainName = domainName;
        this.authType = parseAuthTypes(authType);
        this.voiceUrl = voiceUrl;
        this.voiceMethod = voiceMethod;
        this.voiceFallbackUrl = voiceFallbackUrl;
        this.voiceFallbackMethod = voiceFallbackMethod;
        this.voiceStatusCallbackUrl = voiceStatusCallbackUrl;
        this.voiceStatusCallbackMethod = voiceStatusCallbackMethod;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.subresourceUris = subresourceUris;
        this.uri = uri;
    }

    private static List<AuthType> parseAuthTypes(String serialized) {
        ArrayList<AuthType> parsed = new ArrayList<>();
        String[] types = serialized.split(",");
        for (String t : types) {
            if (!t.isEmpty()) {
                parsed.add(AuthType.valueOf(t));
            }
        }

        return parsed;
    }

    public static SipDomainCreator create(final String domainName) {
        return new SipDomainCreator(domainName);
    }

    public static SipDomainFetcher fetch(final String sid) {
        return new SipDomainFetcher(sid);
    }

    public static SipDomainReader list() {
        return new SipDomainReader();
    }

    public static SipDomainUpdater update(final SipDomain target) {
        return new SipDomainUpdater(target);
    }

    public static SipDomainUpdater update(final String sid) {
        return new SipDomainUpdater(sid);
    }

    public static SipDomainDeleter delete(final SipDomain target) {
        return new SipDomainDeleter(target);
    }

    public static SipDomainDeleter delete(final String sid) {
        return new SipDomainDeleter(sid);
    }

    public final String getSid() {
        return sid;
    }

    public final String getFriendlyName() {
        return friendlyName;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getApiVersion() {
        return apiVersion;
    }

    public final String getDomainName() {
        return domainName;
    }

    public final List<AuthType> getAuthType() {
        return authType;
    }

    public final boolean hasAuthType(AuthType auth) {
        return authType.contains(auth);
    }

    public final URI getVoiceUrl() {
        return voiceUrl;
    }

    public final HttpMethod getVoiceMethod() {
        return voiceMethod;
    }

    public final URI getVoiceFallbackUrl() {
        return voiceFallbackUrl;
    }

    public final HttpMethod getVoiceFallbackMethod() {
        return voiceFallbackMethod;
    }

    public final URI getVoiceStatusCallback() {
        return voiceStatusCallbackUrl;
    }

    public final HttpMethod getVoiceStatusCallbackMethod() {
        return voiceStatusCallbackMethod;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    public final String getUri() {
        return uri;
    }

    public static SipDomain fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipDomain.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static SipDomain fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipDomain.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SipDomain other = (SipDomain) o;
        return (Objects.equals(sid, other.sid) &&
                Objects.equals(friendlyName, other.friendlyName) &&
                Objects.equals(accountSid, other.accountSid) &&
                Objects.equals(apiVersion, other.apiVersion) &&
                Objects.equals(domainName, other.domainName) &&
                Objects.equals(authType, other.authType) &&
                Objects.equals(voiceUrl, other.voiceUrl) &&
                Objects.equals(voiceMethod, other.voiceMethod) &&
                Objects.equals(voiceFallbackUrl, other.voiceFallbackUrl) &&
                Objects.equals(voiceFallbackMethod, other.voiceFallbackMethod) &&
                Objects.equals(voiceStatusCallbackUrl, other.voiceStatusCallbackUrl) &&
                Objects.equals(voiceStatusCallbackMethod, other.voiceStatusCallbackMethod) &&
                Objects.equals(dateCreated, other.dateCreated) &&
                Objects.equals(dateUpdated, other.dateUpdated) &&
                Objects.equals(uri, other.uri));
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, friendlyName, accountSid, apiVersion, domainName, voiceUrl, voiceMethod,
                            voiceFallbackUrl, voiceFallbackMethod, voiceStatusCallbackUrl, voiceStatusCallbackMethod,
                            dateCreated, dateUpdated, uri);
    }

    public enum AuthType {
        IP_ACL, CREDENTIAL_LIST;
    }
}
