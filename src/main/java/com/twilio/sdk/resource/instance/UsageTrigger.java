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
 * The Class UsageTrigger.
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Usage/Triggers/" + this.getSid() + ".json";

    }

    /**
     * Gets the sid.
     *
     * @return the sid
     */
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


    /**
     * Gets the date created.
     *
     * @return the date created
     */
    public Date getDateCreated() {
        SimpleDateFormat format = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss Z");
        try {
            return format.parse(this.getProperty("DateCreated"));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Gets the date updated.
     *
     * @return the date updated
     */
    public Date getDateUpdated() {
        SimpleDateFormat format = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss Z");
        try {
            return format.parse(this.getProperty("DateUpdated"));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Gets the friendly name.
     *
     * @return the friendly name
     */
    public String getFriendlyName() {
        return getProperty("FriendlyName");
    }

    /**
     * Gets the recurrence.
     *
     * @return the recurrence
     * @see Recurrence
     */
    public Recurrence getRecurring() {
        return Recurrence.valueOf(getProperty("Recurring"));
    }

    /**
     * Gets the usage category.
     *
     * @return the usage category
     * @see UsageCategory
     */
    public UsageCategory getUsageCategory() {
        return UsageCategory.valueOf(getProperty("UsageCategory"));
    }

    /**
     * Gets the trigger.
     *
     * @return the trigger
     * @see Trigger
     */
    public Trigger getTriggerBy() {
        return Trigger.valueOf(getProperty("TriggerBy"));
    }

    /**
     * Gets the trigger value.
     *
     * @return the trigger value
     */
    public BigDecimal getTriggerValue() {
        return new BigDecimal(getProperty("TriggerValue"));
    }

    /**
     * Gets the current value.
     *
     * @return the current value
     */
    public BigDecimal getCurrentValue() {
        return new BigDecimal(getProperty("CurrentValue"));
    }

    /**
     * Gets the usage record URI
     *
     * @return the usage record URI
     */
    public String getUsageRecordUri() {
        return getProperty("UsageRecordUri");
    }

    /**
     * Gets the callback URL
     *
     * @return the callback URL
     */
    public String getCallbackUrl() {
        return getProperty("CallbackUrl");
    }

    /**
     * Gets the callback Method
     *
     * @return the callback Method
     */
    public String getCallbackMethod() {
        return getProperty("CallbackMethod");
    }

    /**
     * Gets the date fired
     *
     * @return the date fired
     */
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

    /**
     * Get the URI
     *
     * @return the URI
     */
    public String getUri() {
        return getProperty("Uri");
    }

    /**
     * Delete
     *
     * @return true, if successful
     * @throws TwilioRestException the twilio rest exception
     */

    public boolean delete() throws TwilioRestException {
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "DELETE", null);

        return !response.isError();
    }
}
