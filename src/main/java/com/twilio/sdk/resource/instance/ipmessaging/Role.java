package com.twilio.sdk.resource.instance.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Represent a single role resource in ip messaging
 */
public class Role extends NextGenInstanceResource<TwilioIPMessagingClient> {

	private static final String SERVICE_SID_PROPERTY = "service_sid";

	public Role(TwilioIPMessagingClient client, String serviceSid, String sid) {
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

	public Role(TwilioIPMessagingClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * The unique identifier for this role
	 *
	 * @return A sid string
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The account sid that owns this role
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The service sid that is associated to this role
	 *
	 * @return The service sid
	 */
	public String getServiceSid() {
		return getProperty(SERVICE_SID_PROPERTY);
	}

	/**
	 * The human readable name for this channel
	 *
	 * @return A human readable string
	 */
	public String getFriendlyName() {
		return getProperty(FRIENDLY_NAME_PROPERTY);
	}

	/**
	 * Return the type for this role
	 *
	 * @return The role type
	 */
	public String getType() {
		return getProperty("type");
	}

	/**
	 * Return a list of permissions for this role
	 *
	 * @return The list of permissions for this role
	 */
	public List<String> getPermissions() {
		return (List<String>) getObject("permissions");
	}

	/**
	 * The date when this role was created
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date when this role was updated
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Absolute url of this role
	 *
	 * @return The url of this role
	 */
	public String getUrl() {
		return getProperty("url");
	}

	/**
	 * Deletes the Role
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
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services/" + getServiceSid() + "/Roles/" + getSid();
	}
}
