package com.twilio.sdk.resource.instance.taskrouter;

/**
 * Represents an activity duration.
 */
public class ActivityDuration {

	public static final String AVERAGE_PROPERTY = "average";

	public static final String FRIENDLY_NAME_PROPERTY = "friendly_name";

	public static final String MAXIMUM_PROPERTY = "maximum";

	public static final String MINIMUM_PROPERTY = "minimum";

	private Double average;
	private String friendlyName;
	private Integer maximum;
	private Integer minimum;
	private String sid;

	/**
	 * Instantiates an ActivityDuration.
	 *
	 * @param sid
	 * @param friendlyName
	 * @param average
	 * @param maximum
	 * @param minimum
	 */
	public ActivityDuration(final String sid, final String friendlyName, final Double average, final Integer maximum,
	                        final Integer minimum) {
		this.average = average;
		this.friendlyName = friendlyName;
		this.maximum = maximum;
		this.minimum = minimum;
		this.sid = sid;
	}

	/**
	 * Get the average duration in seconds.
	 *
	 * @return the average duration in seconds
	 */
	public Double getAverage() {
		return average;
	}

	/**
	 * Get the activity's friendly name.
	 * @return the activity's friendly name
	 */
	public String getFriendlyName() {
		return friendlyName;
	}

	/**
	 * Get the maximum duration in seconds.
	 *
	 * @return the maximum duration in seconds
	 */
	public Integer getMaximum() {
		return maximum;
	}

	/**
	 * Get the minimum duration in seconds.
	 *
	 * @return the minimum duration in seconds
	 */
	public Integer getMinimum() {
		return minimum;
	}

	/**
	 * Get the activity's sid.
	 *
	 * @return the activity's sid
	 */
	public String getSid() {
		return sid;
	}

}
