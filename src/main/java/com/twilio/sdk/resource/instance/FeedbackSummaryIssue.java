package com.twilio.sdk.resource.instance;

/**
 * Represents an issue in the call feedback summary.
 */
public class FeedbackSummaryIssue {

	public static final String DESCRIPTION_PROPERTY = "description";
	public static final String COUNT_PROPERTY = "count";
	public static final String PERCENTAGE_OF_TOTAL_CALLS_PROPERTY = "percentage_of_total_calls";

	private String description;
	private int count;
	private double percentageOfTotalCalls;

	/**
	 * Instantiates a FeedbackSummaryIssue.
	 *
	 * @param description
	 * @param count
	 * @param percentageOfTotalCalls
	 */
	public FeedbackSummaryIssue(final String description, final int count, final double percentageOfTotalCalls) {
		this.description = description;
		this.count = count;
		this.percentageOfTotalCalls = percentageOfTotalCalls;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		FeedbackSummaryIssue that = (FeedbackSummaryIssue) o;

		if (count != that.count) {
			return false;
		}
		if (Double.compare(that.percentageOfTotalCalls, percentageOfTotalCalls) != 0) {
			return false;
		}
		if (!description.equals(that.description)) {
			return false;
		}

		return true;
	}

	/**
	 * Gets the number of calls affected by the issue.
	 *
	 * @return number of calls
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the percentage of the total calls affected.
	 *
	 * @return percentage of calls affected
	 */
	public double getPercentageOfTotalCalls() {
		return percentageOfTotalCalls;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = description.hashCode();
		result = 31 * result + count;
		temp = Double.doubleToLongBits(percentageOfTotalCalls);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
