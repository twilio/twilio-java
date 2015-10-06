package com.twilio.sdk.resource.instance.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * Represents a single credential for ip messaging
 */
public class Credential extends NextGenInstanceResource<TwilioIPMessagingClient> {

	public Credential(TwilioIPMessagingClient client, String sid) {
		super(client);

		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("sid cannot be null");
		}
		setProperty(SID_PROPERTY, sid);
	}

	public Credential(TwilioIPMessagingClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * The unique identifier for this credential
	 *
	 * @return A sid string
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The account sid that owns this credential
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The human readable name for this credential
	 *
	 * @return A human readable string
	 */
	public String getFriendlyName() {
		return getProperty(FRIENDLY_NAME_PROPERTY);
	}

	/**
	 * The type for this credential
	 *
	 * @return The type
	 */
	public String getType() {
		return getProperty("type");
	}

	/**
	 * The date when this credential was created
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date when this credential was updated
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Absolute url of this credential
	 *
	 * @return The url of this credential
	 */
	public String getUrl() {
		return getProperty("url");
	}

	/**
	 * Deletes the service
	 *
	 * @return True iff the delete is successful
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = this.getClient()
				.safeRequest(this.getResourceLocation(), "DELETE", (Map) null);
		return !response.isError();
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Credentials/" + getSid();
	}
}
