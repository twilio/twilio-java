package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.UsageTriggerCreator;
import com.twilio.sdk.deleters.UsageTriggerDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.UsageTriggerFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.readers.UsageTriggerReader;
import com.twilio.sdk.updaters.UsageTriggerUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageTrigger extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final DateTime dateUpdated;
    private final DateTime dateFired;
    private final String friendlyName;
    private final String uri;
    private final String accountSid;
    private final String usageRecordUri;
    private final UsageTrigger.TriggerField triggerBy;
    private final HttpMethod callbackMethod;
    private final String sid;
    private final String currentValue;
    private final DateTime dateCreated;
    private final URI callbackUrl;
    private final UsageTrigger.Recurring recurring;
    private final UsageCategory usageCategory;
    private final String triggerValue;

    @JsonCreator
    private UsageTrigger(@JsonProperty("date_updated") final String dateUpdated,
                         @JsonProperty("date_fired") final String dateFired,
                         @JsonProperty("friendly_name") final String friendlyName,
                         @JsonProperty("uri") final String uri, @JsonProperty("account_sid") final String accountSid,
                         @JsonProperty("usage_record_uri") final String usageRecordUri,
                         @JsonProperty("trigger_by") final UsageTrigger.TriggerField triggerBy,
                         @JsonProperty("callback_method") final HttpMethod callbackMethod,
                         @JsonProperty("sid") final String sid,
                         @JsonProperty("current_value") final String currentValue,
                         @JsonProperty("date_created") final String dateCreated,
                         @JsonProperty("callback_url") final URI callbackUrl,
                         @JsonProperty("recurring") final UsageTrigger.Recurring recurring,
                         @JsonProperty("usage_category") final UsageCategory usageCategory,
                         @JsonProperty("trigger_value") final String triggerValue) {
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.dateFired = MarshalConverter.dateTimeFromString(dateFired);
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.accountSid = accountSid;
        this.usageRecordUri = usageRecordUri;
        this.triggerBy = triggerBy;
        this.callbackMethod = callbackMethod;
        this.sid = sid;
        this.currentValue = currentValue;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.callbackUrl = callbackUrl;
        this.recurring = recurring;
        this.usageCategory = usageCategory;
        this.triggerValue = triggerValue;

    }

    public static UsageTriggerCreator create(final URI callbackUrl, final UsageCategory usageCategory,
                                             final String triggerValue) {
        return new UsageTriggerCreator(callbackUrl, usageCategory, triggerValue);
    }

    public static UsageTriggerDeleter delete(final String sid) {
        return new UsageTriggerDeleter(sid);
    }

    public static UsageTriggerDeleter delete(final UsageTrigger target) {
        return new UsageTriggerDeleter(target);
    }

    public static UsageTriggerFetcher fetch(final String sid) {
        return new UsageTriggerFetcher(sid);
    }

    public static UsageTriggerReader list() {
        return new UsageTriggerReader();
    }

    public static UsageTriggerUpdater update(final UsageTrigger target) {
        return new UsageTriggerUpdater(target);
    }

    public static UsageTriggerUpdater update(final String sid) {
        return new UsageTriggerUpdater(sid);
    }

    public static UsageTrigger fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, UsageTrigger.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static UsageTrigger fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, UsageTrigger.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final DateTime getDateFired() {
        return dateFired;
    }

    public final String getFriendlyName() {
        return friendlyName;
    }

    public final String getUri() {
        return uri;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getUsageRecordUri() {
        return usageRecordUri;
    }

    public final UsageTrigger.TriggerField getTriggerBy() {
        return triggerBy;
    }

    public final HttpMethod getCallbackMethod() {
        return callbackMethod;
    }

    public final String getSid() {
        return sid;
    }

    public final String getCurrentValue() {
        return currentValue;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final URI getCallbackUrl() {
        return callbackUrl;
    }

    public final UsageTrigger.Recurring getRecurring() {
        return recurring;
    }

    public final UsageCategory getUsageCategory() {
        return usageCategory;
    }

    public final String getTriggerValue() {
        return triggerValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsageTrigger self = (UsageTrigger) o;

        return (Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(dateFired, self.dateFired) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(usageRecordUri, self.usageRecordUri) &&
                Objects.equals(triggerBy, self.triggerBy) &&
                Objects.equals(callbackMethod, self.callbackMethod) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(currentValue, self.currentValue) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(callbackUrl, self.callbackUrl) &&
                Objects.equals(recurring, self.recurring) &&
                Objects.equals(usageCategory, self.usageCategory) &&
                Objects.equals(triggerValue, self.triggerValue));
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateUpdated, dateFired, friendlyName, uri, accountSid, usageRecordUri, triggerBy,
                            callbackMethod, sid, currentValue, dateCreated, callbackUrl, recurring, usageCategory,
                            triggerValue);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("dateUpdated", dateUpdated)
                          .add("dateFired", dateFired)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("usageRecordUri", usageRecordUri)
                          .add("triggerBy", triggerBy)
                          .add("callbackMethod", callbackMethod)
                          .add("sid", sid)
                          .add("currentValue", currentValue)
                          .add("dateCreated", dateCreated)
                          .add("callbackUrl", callbackUrl)
                          .add("recurring", recurring)
                          .add("usageCategory", usageCategory)
                          .add("triggerValue", triggerValue)
                          .toString();
    }

    public enum TriggerField {
        COUNT("count"),
        USAGE("usage"),
        PRICE("price");

        private final String triggerField;

        private TriggerField(final String triggerField) {
            this.triggerField = triggerField;
        }

        public String toString() {
            return triggerField;
        }

        @JsonCreator
        public static TriggerField forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return TriggerField.valueOf(munged);
        }
    }

    public enum Recurring {
        DAILY("daily"),
        MONTHLY("monthly"),
        YEARLY("yearly"),
        ALL_TIME("alltime");

        private final String recurring;

        private Recurring(final String recurring) {
            this.recurring = recurring;
        }

        public String toString() {
            return recurring;
        }

        @JsonCreator
        public static Recurring forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Recurring.valueOf(munged);
        }
    }
}
