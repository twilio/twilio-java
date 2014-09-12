package com.twilio.sdk.resource.instance.wds;

import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * Activity class use to get an activity resource.
 */
public class Activity extends InstanceResource<TwilioWdsClient> {

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates an activity.
	 *
	 * @param client the client
	 */
	public Activity(final TwilioWdsClient client) {
		super(client);
	}

	/**
	 * Instantiates an activity.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Activity(final TwilioWdsClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates an activity.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param activitySid the activity sid
	 */
	public Activity(final TwilioWdsClient client, final String workspaceSid, final String activitySid) {
		super(client);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for an Activity cannot be null");
		}
		if (activitySid == null || "".equals(activitySid)) {
			throw new IllegalArgumentException("The activitySid for an Activity cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(SID_PROPERTY, activitySid);
	}

	/**
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Gets the friendly name
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * Gets the workspace sid.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	/**
	 * Checks if available.
	 *
	 * @return true, if available
	 */
	public boolean isAvailable() {
		return (Boolean) getObject("available");
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       getWorkspaceSid() + "/Activities/" + getSid();
	}
}
