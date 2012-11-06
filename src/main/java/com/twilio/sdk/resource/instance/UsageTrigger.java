package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * The Class Usage.
 * <p/>
 * For more information see <a
 * href="http://www.twilio.com/docs/api/rest/usage-triggers"
 * >http://www.twilio.com/docs/api/rest/usage-triggers</a>
 */
public class UsageTrigger extends InstanceResource {

    /**
     * The Constant SID_PROPERTY.
     */
    private static final String SID_PROPERTY = "Sid";

    /**
     * Instantiates a new usageRecord.
     *
     * @param client the client
     */
    public UsageTrigger(TwilioRestClient client) {
        super(client);
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Usage/Triggers/" + this.getSid() + ".json";

    }

    public String getSid() {
        return this.getProperty(SID_PROPERTY);
    }

    /**
     * Instantiates a new usageRecord.
     *
     * @param client the client
     * @param sid    the sid
     */
    public UsageTrigger(TwilioRestClient client, String sid) {
        super(client);
        if (sid == null) {
            throw new IllegalStateException(
                    "The Sid for a UsageRecord can not be null");
        }
        this.setProperty(SID_PROPERTY, sid);
    }

    /**
     * Instantiates a new recordingRecord.
     *
     * @param client     the client
     * @param properties the properties
     */
    public UsageTrigger(TwilioRestClient client, Map<String, Object> properties) {
        super(client, properties);
    }


    public Date getDateCreated() {
        SimpleDateFormat format = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss Z");
        try {
            return format.parse(this.getProperty("DateCreated"));
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getDateUpdated() {
        SimpleDateFormat format = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss Z");
        try {
            return format.parse(this.getProperty("DateUpdated"));
        } catch (ParseException e) {
            return null;
        }
    }

    public String getFriendlyName() {
        return getProperty("FriendlyName");
    }

    public Recurrence getRecurring() {
        return Recurrence.valueOf(getProperty("Recurring"));
    }

    public UsageCategory getUsageCategory() {
        return UsageCategory.valueOf(getProperty("UsageCategory"));
    }

    public Trigger getTriggerBy() {
        return Trigger.valueOf(getProperty("TriggerBy"));
    }

    public BigDecimal getTriggerValue() {
        return new BigDecimal(getProperty("TriggerValue"));
    }

    public BigDecimal getCurrentValue() {
        return new BigDecimal(getProperty("CurrentValue"));
    }

    public String getUsageRecordUri() {
        return getProperty("UsageRecordUri");
    }

    public String getCallbackUrl() {
        return getProperty("CallbackUrl");
    }

    public String getCallbackMethod() {
        return getProperty("CallbackMethod");
    }

    public Date getDateFired() {
        SimpleDateFormat format = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss Z");
        try {
            String dateFired = this.getProperty("DateFired");
            if (dateFired == null)
                return null;
            return format.parse(dateFired);
        } catch (ParseException e) {
            return null;
        }
    }

    public String getUri() {
        return getProperty("Uri");
    }

    public boolean delete() throws TwilioRestException {
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "DELETE", null);

        return !response.isError();
    }
}
