package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.readers.DependentPhoneNumberReader;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DependentPhoneNumber extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final AddressRequirement addressRequirements;
    private final DateTime dateUpdated;
    private final URI voiceUrl;
    private final String smsApplicationSid;
    private final HttpMethod voiceFallbackMethod;
    private final String voiceApplicationSid;
    private final Map<String, Boolean> capabilities;
    private final String sid;
    private final HttpMethod statusCallbackMethod;
    private final URI voiceFallbackUrl;
    private final String phoneNumber;
    private final URI smsUrl;
    private final HttpMethod voiceMethod;
    private final Boolean voiceCallerIdLookup;
    private final String friendlyName;
    private final String uri;
    private final URI smsFallbackUrl;
    private final String accountSid;
    private final HttpMethod smsMethod;
    private final String apiVersion;
    private final HttpMethod smsFallbackMethod;
    private final DateTime dateCreated;
    private final URI statusCallback;

    @JsonCreator
    private DependentPhoneNumber(@JsonProperty("address_requirements") final AddressRequirement addressRequirements,
                                 @JsonProperty("date_updated") final String dateUpdated,
                                 @JsonProperty("voice_url") final URI voiceUrl,
                                 @JsonProperty("sms_application_sid") final String smsApplicationSid,
                                 @JsonProperty("voice_fallback_method") final HttpMethod voiceFallbackMethod,
                                 @JsonProperty("voice_application_sid") final String voiceApplicationSid,
                                 @JsonProperty("capabilities") final Map<String, Boolean> capabilities,
                                 @JsonProperty("sid") final String sid,
                                 @JsonProperty("status_callback_method") final HttpMethod statusCallbackMethod,
                                 @JsonProperty("voice_fallback_url") final URI voiceFallbackUrl,
                                 @JsonProperty("phone_number") final String phoneNumber,
                                 @JsonProperty("sms_url") final URI smsUrl,
                                 @JsonProperty("voice_method") final HttpMethod voiceMethod,
                                 @JsonProperty("voice_caller_id_lookup") final Boolean voiceCallerIdLookup,
                                 @JsonProperty("friendly_name") final String friendlyName,
                                 @JsonProperty("uri") final String uri,
                                 @JsonProperty("sms_fallback_url") final URI smsFallbackUrl,
                                 @JsonProperty("account_sid") final String accountSid,
                                 @JsonProperty("sms_method") final HttpMethod smsMethod,
                                 @JsonProperty("api_version") final String apiVersion,
                                 @JsonProperty("sms_fallback_method") final HttpMethod smsFallbackMethod,
                                 @JsonProperty("date_created") final String dateCreated,
                                 @JsonProperty("status_callback") final URI statusCallback) {
        this.addressRequirements = addressRequirements;
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.voiceUrl = voiceUrl;
        this.smsApplicationSid = smsApplicationSid;
        this.voiceFallbackMethod = voiceFallbackMethod;
        this.voiceApplicationSid = voiceApplicationSid;
        this.capabilities = capabilities;
        this.sid = sid;
        this.statusCallbackMethod = statusCallbackMethod;
        this.voiceFallbackUrl = voiceFallbackUrl;
        this.phoneNumber = phoneNumber;
        this.smsUrl = smsUrl;
        this.voiceMethod = voiceMethod;
        this.voiceCallerIdLookup = voiceCallerIdLookup;
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.smsFallbackUrl = smsFallbackUrl;
        this.accountSid = accountSid;
        this.smsMethod = smsMethod;
        this.apiVersion = apiVersion;
        this.smsFallbackMethod = smsFallbackMethod;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.statusCallback = statusCallback;

    }

    public static DependentPhoneNumberReader list(final String addressSid) {
        return new DependentPhoneNumberReader(addressSid);
    }

    public AddressRequirement getAddressRequirements() {
        return addressRequirements;
    }

    public DateTime getDateUpdated() {
        return dateUpdated;
    }

    public URI getVoiceUrl() {
        return voiceUrl;
    }

    public String getSmsApplicationSid() {
        return smsApplicationSid;
    }

    public HttpMethod getVoiceFallbackMethod() {
        return voiceFallbackMethod;
    }

    public String getVoiceApplicationSid() {
        return voiceApplicationSid;
    }

    public Map<String, Boolean> getCapabilities() {
        return capabilities;
    }

    public String getSid() {
        return sid;
    }

    public HttpMethod getStatusCallbackMethod() {
        return statusCallbackMethod;
    }

    public URI getVoiceFallbackUrl() {
        return voiceFallbackUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public URI getSmsUrl() {
        return smsUrl;
    }

    public HttpMethod getVoiceMethod() {
        return voiceMethod;
    }

    public Boolean getVoiceCallerIdLookup() {
        return voiceCallerIdLookup;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getUri() {
        return uri;
    }

    public URI getSmsFallbackUrl() {
        return smsFallbackUrl;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public HttpMethod getSmsMethod() {
        return smsMethod;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public HttpMethod getSmsFallbackMethod() {
        return smsFallbackMethod;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public URI getStatusCallback() {
        return statusCallback;
    }

    public static DependentPhoneNumber fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, DependentPhoneNumber.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static DependentPhoneNumber fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, DependentPhoneNumber.class);
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

        DependentPhoneNumber self = (DependentPhoneNumber) o;

        return (Objects.equals(addressRequirements, self.addressRequirements) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(voiceUrl, self.voiceUrl) &&
                Objects.equals(smsApplicationSid, self.smsApplicationSid) &&
                Objects.equals(voiceFallbackMethod, self.voiceFallbackMethod) &&
                Objects.equals(voiceApplicationSid, self.voiceApplicationSid) &&
                Objects.equals(capabilities, self.capabilities) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(statusCallbackMethod, self.statusCallbackMethod) &&
                Objects.equals(voiceFallbackUrl, self.voiceFallbackUrl) &&
                Objects.equals(phoneNumber, self.phoneNumber) &&
                Objects.equals(smsUrl, self.smsUrl) &&
                Objects.equals(voiceMethod, self.voiceMethod) &&
                Objects.equals(voiceCallerIdLookup, self.voiceCallerIdLookup) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(smsFallbackUrl, self.smsFallbackUrl) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(smsMethod, self.smsMethod) &&
                Objects.equals(apiVersion, self.apiVersion) &&
                Objects.equals(smsFallbackMethod, self.smsFallbackMethod) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(statusCallback, self.statusCallback));
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressRequirements, dateUpdated, voiceUrl, smsApplicationSid, voiceFallbackMethod,
                            voiceApplicationSid, capabilities, sid, statusCallbackMethod, voiceFallbackUrl, phoneNumber,
                            smsUrl, voiceMethod, voiceCallerIdLookup, friendlyName, uri, smsFallbackUrl, accountSid,
                            smsMethod, apiVersion, smsFallbackMethod, dateCreated, statusCallback);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("addressRequirements", addressRequirements)
                          .add("dateUpdated", dateUpdated)
                          .add("voiceUrl", voiceUrl)
                          .add("smsApplicationSid", smsApplicationSid)
                          .add("voiceFallbackMethod", voiceFallbackMethod)
                          .add("voiceApplicationSid", voiceApplicationSid)
                          .add("capabilities", capabilities)
                          .add("sid", sid)
                          .add("statusCallbackMethod", statusCallbackMethod)
                          .add("voiceFallbackUrl", voiceFallbackUrl)
                          .add("phoneNumber", phoneNumber)
                          .add("smsUrl", smsUrl)
                          .add("voiceMethod", voiceMethod)
                          .add("voiceCallerIdLookup", voiceCallerIdLookup)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("smsFallbackUrl", smsFallbackUrl)
                          .add("accountSid", accountSid)
                          .add("smsMethod", smsMethod)
                          .add("apiVersion", apiVersion)
                          .add("smsFallbackMethod", smsFallbackMethod)
                          .add("dateCreated", dateCreated)
                          .add("statusCallback", statusCallback)
                          .toString();
    }

}
