package com.twilio.sdk.resource.instance.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * Represents a single user resource for ip messaging
 */
public class User extends NextGenInstanceResource<TwilioIPMessagingClient> {

	private static final String SERVICE_SID_PROPERTY = "service_sid";

	public User(TwilioIPMessagingClient client, String serviceSid, String sid) {
		super(client);

		if (StringUtils.isEmpty(serviceSid)) {
			throw new IllegalArgumentException("serviceSid cannot be null");
		}
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("sid cannot be null");
		}
		setProperty(SERVICE_SID_PROPERTY, serviceSid);
		setProperty(SID_PROPERTY, sid);
	}

	public User(TwilioIPMessagingClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * The unique identifier for this user
	 *
	 * @return A sid string
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The account sid that owns this user
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The service sid that is associated to this user
	 *
	 * @return The service sid
	 */
	public String getServiceSid() {
		return getProperty(SERVICE_SID_PROPERTY);
	}

	/**
	 * The role sid for this user
	 *
	 * @return The role sid
	 */
	public String getRoleSid() {
		return getProperty("role_sid");
	}

	/**
	 * The identity for this user
	 *
	 * @return The identity
	 */
	public String getIdentity() {
		return getProperty("identity");
	}

	/**
	 * The date when this user was created
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date when this user was updated
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Absolute url of this user
	 *
	 * @return The url of this user
	 */
	public String getUrl() {
		return getProperty("url");
	}

	/**
	 * Deletes the user
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
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services/" + getServiceSid() + "/Users/" + getSid();
	}
}
