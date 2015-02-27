package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Calendar;
import java.util.Map;

/**
 * Activities describe the current status of your {@link com.twilio.sdk.resource.instance.taskrouter.Worker}
 * resources, which determines whether they are eligible to receive {@link com.twilio.sdk.resource.instance.taskrouter.Task}
 * assignments. Workers are always set to a single Activity.
 *
 * See <a href="https://www.twilio.com/docs/taskrouter/activities">the TaskRouter documentation</a>.
 */
public class Activity extends NextGenInstanceResource<TwilioTaskRouterClient> {

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates an activity.
	 *
	 * @param client the client
	 */
	public Activity(final TwilioTaskRouterClient client) {
		super(client);
	}

	/**
	 * Instantiates an activity.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Activity(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates an activity.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param activitySid the activity sid
	 */
	public Activity(final TwilioTaskRouterClient client, final String workspaceSid, final String activitySid) {
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
	 * The unique ID of the account that owns this Activity.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The date and time this Activity was created.
	 *
	 * @return the date created
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date and time this Activity was last updated.
	 *
	 * @return the date updated
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * A human-readable description of this Activity.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * This Activity's unique ID.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The unique ID of the Workspace that contains this Activity.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	/**
	 * Whether Workers in this Activity state are available to accept new tasks.
	 *
	 * @return true, if available
	 */
	public boolean isAvailable() {
		return (Boolean) getObject("available");
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Activities/" +
		       getSid();
	}
}
