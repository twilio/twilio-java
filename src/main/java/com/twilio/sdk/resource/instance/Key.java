package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * Instance resource for a Auth Key
 */
public class Key extends InstanceResource<TwilioRestClient> {

    /**
     * Instantiates a new Key
     *
     * @param client the Twilio client
     * @param sid the key sid
     */
    public Key(final TwilioRestClient client, final String sid) {
        super(client);
        if (sid == null) {
            throw new IllegalArgumentException("sid for a Key cannot be null");
        }
        setProperty(SID_PROPERTY, sid);
    }

    /**
     * Instantiates a new Key
     *
     * @param client the Twilio client
     * @param properties the properties of this key
     */
    public Key(final TwilioRestClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    /**
     * A unique identifier for this Key.
     *
     * @return the sid
     */
    public String getSid() {
        return getProperty(SID_PROPERTY);
    }

    /**
     * Date/time the Key resource was created.
     *
     * @return the date created
     */
    public Date getDateCreated() {
        return parseDate(getProperty(DATE_CREATED_PROPERTY));
    }

    /**
     * Date/time the Key resource was last updated.
     *
     * @return the date updated
     */
    public Date getDateUpdated() {
        return parseDate(getProperty(DATE_UPDATED_PROPERTY));
    }

    /**
     * An optional user-defined string describing this Key.
     *
     * @return the friendly name
     */
    public String getFriendlyName() {
        return getProperty("friendly_name");
    }

    /**
     * The secret of this Key. The value is only returned at creation time.
     *
     * @return the secret
     */
    public String getSecret() {
        return getProperty("secret");
    }

    /**
     * Delete the key.
     *
     * @return true, if successful
     * @throws TwilioRestException if there is an error in the request
     */
    public boolean delete() throws TwilioRestException {
        TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "DELETE", (Map) null);
        return !response.isError();
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Keys/" +
                getSid() + ".json";
    }
}
