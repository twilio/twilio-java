package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.ShortCodeFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.readers.ShortCodeReader;
import com.twilio.sdk.updaters.ShortCodeUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShortCode extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final DateTime dateUpdated;
    private final HttpMethod smsFallbackMethod;
    private final String friendlyName;
    private final String uri;
    private final URI smsFallbackUrl;
    private final String accountSid;
    private final String shortCode;
    private final HttpMethod smsMethod;
    private final String sid;
    private final DateTime dateCreated;
    private final URI smsUrl;
    private final String apiVersion;

    @JsonCreator
    private ShortCode(@JsonProperty("date_updated") final String dateUpdated,
                      @JsonProperty("sms_fallback_method") final HttpMethod smsFallbackMethod,
                      @JsonProperty("friendly_name") final String friendlyName, @JsonProperty("uri") final String uri,
                      @JsonProperty("sms_fallback_url") final URI smsFallbackUrl,
                      @JsonProperty("account_sid") final String accountSid,
                      @JsonProperty("short_code") final String shortCode,
                      @JsonProperty("sms_method") final HttpMethod smsMethod, @JsonProperty("sid") final String sid,
                      @JsonProperty("date_created") final String dateCreated, @JsonProperty("sms_url") final URI smsUrl,
                      @JsonProperty("api_version") final String apiVersion) {
        this.dateUpdated = safeDateTimeConvert(dateUpdated);
        this.smsFallbackMethod = smsFallbackMethod;
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.smsFallbackUrl = smsFallbackUrl;
        this.accountSid = accountSid;
        this.shortCode = shortCode;
        this.smsMethod = smsMethod;
        this.sid = sid;
        this.dateCreated = safeDateTimeConvert(dateCreated);
        this.smsUrl = smsUrl;
        this.apiVersion = apiVersion;

    }

    public static ShortCodeFetcher fetch(final String sid) {
        return new ShortCodeFetcher(sid);
    }

    public static ShortCodeReader list() {
        return new ShortCodeReader();
    }

    public static ShortCodeUpdater update(final ShortCode target) {
        return new ShortCodeUpdater(target);
    }

    public static ShortCodeUpdater update(final String sid) {
        return new ShortCodeUpdater(sid);
    }

    public static ShortCode fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, ShortCode.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static ShortCode fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, ShortCode.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
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

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getShortCode() {
        return shortCode;
    }

    public final HttpMethod getSmsMethod() {
        return smsMethod;
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

    public final String getApiVersion() {
        return apiVersion;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShortCode self = (ShortCode) o;

        return (Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(smsFallbackMethod, self.smsFallbackMethod) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(smsFallbackUrl, self.smsFallbackUrl) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(shortCode, self.shortCode) &&
                Objects.equals(smsMethod, self.smsMethod) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(smsUrl, self.smsUrl) &&
                Objects.equals(apiVersion, self.apiVersion));
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateUpdated, smsFallbackMethod, friendlyName, uri, smsFallbackUrl, accountSid, shortCode,
                            smsMethod, sid, dateCreated, smsUrl, apiVersion);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("dateUpdated", dateUpdated)
                          .add("smsFallbackMethod", smsFallbackMethod)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("smsFallbackUrl", smsFallbackUrl)
                          .add("accountSid", accountSid)
                          .add("shortCode", shortCode)
                          .add("smsMethod", smsMethod)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("smsUrl", smsUrl)
                          .add("apiVersion", apiVersion)
                          .toString();
    }
}
