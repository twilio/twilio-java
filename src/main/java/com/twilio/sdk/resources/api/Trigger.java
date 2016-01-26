package com.twilio.sdk.resources.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.TriggerCreator;
import com.twilio.sdk.deleters.api.TriggerDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.TriggerFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.TriggerReader;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.TriggerUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trigger extends Resource {
    private static final long serialVersionUID = 265472878252662L;

    public enum UsageCategory {
        CALLERIDLOOKUPS("calleridlookups"),
        CALLS("calls"),
        CALLS_CLIENT("calls-client"),
        CALLS_INBOUND("calls-inbound"),
        CALLS_INBOUND_LOCAL("calls-inbound-local"),
        CALLS_INBOUND_TOLLFREE("calls-inbound-tollfree"),
        CALLS_OUTBOUND("calls-outbound"),
        CALLS_SIP("calls-sip"),
        PHONENUMBERS("phonenumbers"),
        PHONENUMBERS_LOCAL("phonenumbers-local"),
        PHONENUMBERS_TOLLFREE("phonenumbers-tollfree"),
        RECORDINGS("recordings"),
        RECORDINGSTORAGE("recordingstorage"),
        SHORTCODES("shortcodes"),
        SHORTCODES_CUSTOMEROWNED("shortcodes-customerowned"),
        SHORTCODES_RANDOM("shortcodes-random"),
        SHORTCODES_VANITY("shortcodes-vanity"),
        SMS("sms"),
        SMS_INBOUND("sms-inbound"),
        SMS_INBOUND_LONGCODE("sms-inbound-longcode"),
        SMS_INBOUND_SHORTCODE("sms-inbound-shortcode"),
        SMS_OUTBOUND("sms-outbound"),
        SMS_OUTBOUND_LONGCODE("sms-outbound-longcode"),
        SMS_OUTBOUND_SHORTCODE("sms-outbound-shortcode"),
        TOTALPRICE("totalprice"),
        TRANSCRIPTIONS("transcriptions");
    
        private final String value;
        
        private UsageCategory(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static UsageCategory forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return UsageCategory.valueOf(normalized);
        }
    }

    public enum Recurring {
        DAILY("daily"),
        MONTHLY("monthly"),
        YEARLY("yearly"),
        ALLTIME("alltime");
    
        private final String value;
        
        private Recurring(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Recurring forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Recurring.valueOf(normalized);
        }
    }

    public enum TriggerField {
        COUNT("count"),
        USAGE("usage"),
        PRICE("price");
    
        private final String value;
        
        private TriggerField(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static TriggerField forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return TriggerField.valueOf(normalized);
        }
    }

    /**
     * Fetch and instance of a usage-trigger
     * 
     * @param accountSid The account_sid
     * @param sid Fetch by unique usage-trigger Sid
     * @return TriggerFetcher capable of executing the fetch
     */
    public static TriggerFetcher fetch(final String accountSid, final String sid) {
        return new TriggerFetcher(accountSid, sid);
    }

    /**
     * Update an instance of a usage trigger
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return TriggerUpdater capable of executing the update
     */
    public static TriggerUpdater update(final String accountSid, final String sid) {
        return new TriggerUpdater(accountSid, sid);
    }

    /**
     * delete
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return TriggerDeleter capable of executing the delete
     */
    public static TriggerDeleter delete(final String accountSid, final String sid) {
        return new TriggerDeleter(accountSid, sid);
    }

    /**
     * Create a new UsageTrigger
     * 
     * @param accountSid The account_sid
     * @param callbackUrl URL Twilio will request when the trigger fires
     * @param triggerValue the value at which the trigger will fire
     * @param usageCategory The usage category the trigger watches
     * @return TriggerCreator capable of executing the create
     */
    public static TriggerCreator create(final String accountSid, final URI callbackUrl, final String triggerValue, final Trigger.UsageCategory usageCategory) {
        return new TriggerCreator(accountSid, callbackUrl, triggerValue, usageCategory);
    }

    /**
     * Retrieve a list of usage-triggers belonging to the account used to make the
     * request
     * 
     * @param accountSid The account_sid
     * @return TriggerReader capable of executing the read
     */
    public static TriggerReader read(final String accountSid) {
        return new TriggerReader(accountSid);
    }

    /**
     * Converts a JSON String into a Trigger object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Trigger object represented by the provided JSON
     */
    public static Trigger fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Trigger.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Trigger object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Trigger object represented by the provided JSON
     */
    public static Trigger fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Trigger.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String apiVersion;
    private final HttpMethod callbackMethod;
    private final URI callbackUrl;
    private final String currentValue;
    private final DateTime dateCreated;
    private final DateTime dateFired;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final Trigger.Recurring recurring;
    private final String sid;
    private final Trigger.TriggerField triggerBy;
    private final String triggerValue;
    private final String uri;
    private final Trigger.UsageCategory usageCategory;
    private final String usageRecordUri;

    @JsonCreator
    private Trigger(@JsonProperty("account_sid") final String accountSid, 
                    @JsonProperty("api_version") final String apiVersion, 
                    @JsonProperty("callback_method") final HttpMethod callbackMethod, 
                    @JsonProperty("callback_url") final URI callbackUrl, 
                    @JsonProperty("current_value") final String currentValue, 
                    @JsonProperty("date_created") final String dateCreated, 
                    @JsonProperty("date_fired") final String dateFired, 
                    @JsonProperty("date_updated") final String dateUpdated, 
                    @JsonProperty("friendly_name") final String friendlyName, 
                    @JsonProperty("recurring") final Trigger.Recurring recurring, 
                    @JsonProperty("sid") final String sid, 
                    @JsonProperty("trigger_by") final Trigger.TriggerField triggerBy, 
                    @JsonProperty("trigger_value") final String triggerValue, 
                    @JsonProperty("uri") final String uri, 
                    @JsonProperty("usage_category") final Trigger.UsageCategory usageCategory, 
                    @JsonProperty("usage_record_uri") final String usageRecordUri) {
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.callbackMethod = callbackMethod;
        this.callbackUrl = callbackUrl;
        this.currentValue = currentValue;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateFired = MarshalConverter.dateTimeFromString(dateFired);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.recurring = recurring;
        this.sid = sid;
        this.triggerBy = triggerBy;
        this.triggerValue = triggerValue;
        this.uri = uri;
        this.usageCategory = usageCategory;
        this.usageRecordUri = usageRecordUri;
    }

    /**
     * @return The account this trigger monitors.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The api_version
     */
    public final String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * @return HTTP method to use with callback_url
     */
    public final HttpMethod getCallbackMethod() {
        return this.callbackMethod;
    }

    /**
     * @return URL Twilio will request when the trigger fires
     */
    public final URI getCallbackUrl() {
        return this.callbackUrl;
    }

    /**
     * @return The current value of the field the trigger is watching.
     */
    public final String getCurrentValue() {
        return this.currentValue;
    }

    /**
     * @return The date this resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date the trigger was last fired
     */
    public final DateTime getDateFired() {
        return this.dateFired;
    }

    /**
     * @return The date this resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return A user-specified, human-readable name for the trigger.
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return How this trigger recurs
     */
    public final Trigger.Recurring getRecurring() {
        return this.recurring;
    }

    /**
     * @return The trigger's unique Sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The field in the UsageRecord that fires the trigger
     */
    public final Trigger.TriggerField getTriggerBy() {
        return this.triggerBy;
    }

    /**
     * @return the value at which the trigger will fire
     */
    public final String getTriggerValue() {
        return this.triggerValue;
    }

    /**
     * @return The URI for this resource
     */
    public final String getUri() {
        return this.uri;
    }

    /**
     * @return The usage category the trigger watches
     */
    public final Trigger.UsageCategory getUsageCategory() {
        return this.usageCategory;
    }

    /**
     * @return The URI of the UsageRecord this trigger is watching
     */
    public final String getUsageRecordUri() {
        return this.usageRecordUri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Trigger other = (Trigger) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(apiVersion, other.apiVersion) && 
               Objects.equals(callbackMethod, other.callbackMethod) && 
               Objects.equals(callbackUrl, other.callbackUrl) && 
               Objects.equals(currentValue, other.currentValue) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateFired, other.dateFired) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(recurring, other.recurring) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(triggerBy, other.triggerBy) && 
               Objects.equals(triggerValue, other.triggerValue) && 
               Objects.equals(uri, other.uri) && 
               Objects.equals(usageCategory, other.usageCategory) && 
               Objects.equals(usageRecordUri, other.usageRecordUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            apiVersion,
                            callbackMethod,
                            callbackUrl,
                            currentValue,
                            dateCreated,
                            dateFired,
                            dateUpdated,
                            friendlyName,
                            recurring,
                            sid,
                            triggerBy,
                            triggerValue,
                            uri,
                            usageCategory,
                            usageRecordUri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("apiVersion", apiVersion)
                          .add("callbackMethod", callbackMethod)
                          .add("callbackUrl", callbackUrl)
                          .add("currentValue", currentValue)
                          .add("dateCreated", dateCreated)
                          .add("dateFired", dateFired)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("recurring", recurring)
                          .add("sid", sid)
                          .add("triggerBy", triggerBy)
                          .add("triggerValue", triggerValue)
                          .add("uri", uri)
                          .add("usageCategory", usageCategory)
                          .add("usageRecordUri", usageRecordUri)
                          .toString();
    }
}