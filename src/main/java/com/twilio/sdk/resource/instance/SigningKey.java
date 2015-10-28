package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Calendar;
import java.util.Map;

public class SigningKey extends NextGenInstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new SigningKey.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public SigningKey(final TwilioRestClient client, final String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for an SigningKey cannot be null");
		}
		setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new SingingKey.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public SigningKey(final TwilioRestClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/SigningKeys/" +
		       getSid() + ".json";
	}

	/**
	 * A unique identifier for this SigningKey.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * Date/time the SigningKey resource was created.
	 *
	 * @return the date created
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * Date/time the SigningKey resource was last updated.
	 *
	 * @return the date updated
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * An optional user-defined string describing this SigningKey.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * The secret of this SigningKey. The value is only returned at creation time.
	 *
	 * @return the secret
	 */
	public String getSecret() {
		return getProperty("secret");
	}

	/**
	 * Delete the SigningKey.
	 *
	 * @return true, if successful
	 * @throws TwilioRestException if there is an error in the request
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "DELETE", (Map) null);
		return !response.isError();
	}
}
