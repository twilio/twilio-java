package com.twilio.sdk.resource.instance.taskrouter;

/**
 * Represents an activity statistic.
 */
public class ActivityStatistic {

	public static final String FRIENDLY_NAME_PROPERTY = "friendly_name";

	public static final String WORKERS_PROPERTY = "workers";

	private String sid;

	private String friendlyName;

	private Integer workers;

	/**
	 * Instantiates an ActivityStatistic.
	 *
	 * @param sid
	 * @param friendlyName
	 * @param workers
	 */
	public ActivityStatistic(final String sid, final String friendlyName, final Integer workers) {
		this.sid = sid;
		this.friendlyName = friendlyName;
		this.workers = workers;
	}

	/**
	 * Get the activity's sid.
	 *
	 * @return the activity's sid
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * Get the activity's friendly name.
	 *
	 * @return the activity's friendly name
	 */
	public String getFriendlyName() {

		return friendlyName;
	}

	/**
	 * Get the activity's number of workers.
	 *
	 * @return number of workers
	 */
	public Integer getWorkers() {
		return workers;
	}
}
