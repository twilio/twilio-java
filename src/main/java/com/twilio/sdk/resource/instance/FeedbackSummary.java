package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A FeedbackSummary class. Used to get feedback summary resource.
 */
public class FeedbackSummary extends InstanceResource<TwilioRestClient> {

	public static final String ACCOUNT_SID_PROPERTY = "account_sid";
	public static final String CALL_COUNT_PROPERTY = "call_count";
	public static final String CALL_FEEDBACK_COUNT_PROPERTY = "call_feedback_count";
	public static final String END_DATE_PROPERTY = "end_date";
	public static final String INCLUDE_SUB_ACCOUNTS_PROPERTY = "include_subaccounts";
	public static final String ISSUES_PROPERTY = "issues";
	public static final String QUALITY_SCORE_AVERAGE_PROPERTY = "quality_score_average";
	public static final String QUALITY_SCORE_MEDIAN_PROPERTY = "quality_score_median";
	public static final String QUALITY_SCORE_STANDARD_DEVIATION_PROPERTY = "quality_score_standard_deviation";
	public static final String START_DATE_PROPERTY = "start_date";
	public static final String STATUS_PROPERTY = "status";

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Instantiates a new FeedbackSummary
	 *
	 * @param client the client
	 */
	public FeedbackSummary(final TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new FeedbackSummary.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public FeedbackSummary(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() +
		       "/Calls/FeedbackSummary" + getSid() + ".json";
	}

	public String getAccountSid() {
		return getProperty(ACCOUNT_SID_PROPERTY);
	}

	public Integer getCallCount() {
		try {
			return (Integer) getObject(CALL_COUNT_PROPERTY);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Integer getCallFeedbackCount() {
		try {
			return (Integer) getObject(CALL_FEEDBACK_COUNT_PROPERTY);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Date getDateCreated() {
		return getDateProperty(DATE_CREATED_PROPERTY);
	}

	public Date getDateUpdated() {
		return getDateProperty(DATE_UPDATED_PROPERTY);
	}

	public Date getEndDate() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		try {
			return format.parse(getProperty(END_DATE_PROPERTY));
		} catch (ParseException e) {
			return null;
		}
	}

	public boolean isIncludeSubAccounts() {
		return Boolean.parseBoolean(getProperty(INCLUDE_SUB_ACCOUNTS_PROPERTY));
	}

	public Set<FeedbackSummaryIssue> getIssues() {
		try {
			List<Map<String, Object>> props = (List<Map<String, Object>>) getObject(ISSUES_PROPERTY);

			Set<FeedbackSummaryIssue> issues = new HashSet<FeedbackSummaryIssue>();

			for (Map<String, Object> prop : props) {
				FeedbackSummaryIssue issueSummary = mapToFeedbackIssueSummary(prop);
				issues.add(issueSummary);
			}

			return Collections.unmodifiableSet(issues);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Double getQualityScoreAverage() {
		try {
			Object prop = getObject(QUALITY_SCORE_AVERAGE_PROPERTY);
			if (prop instanceof Integer) {
				return Double.parseDouble(prop.toString());
			} else {
				return (Double) prop;
			}
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Double getQualityScoreMedian() {
		try {
			Object prop = getObject(QUALITY_SCORE_MEDIAN_PROPERTY);
			if (prop instanceof Integer) {
				return Double.parseDouble(prop.toString());
			} else {
				return (Double) prop;
			}
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Double getQualityScoreStandardDeviation() {
		try {
			Object prop = getObject(QUALITY_SCORE_STANDARD_DEVIATION_PROPERTY);
			if (prop instanceof Integer) {
				return Double.parseDouble(prop.toString());
			} else {
				return (Double) prop;
			}
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Date getStartDate() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		try {
			return format.parse(getProperty(START_DATE_PROPERTY));
		} catch (ParseException e) {
			return null;
		}
	}

	public String getStatus() {
		return getProperty(STATUS_PROPERTY);
	}

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	protected FeedbackSummaryIssue mapToFeedbackIssueSummary(Map<String, Object> data) {
		String description;
		int count;
		double percentage;

		try {
			description = (String) data.get(FeedbackSummaryIssue.DESCRIPTION_PROPERTY);
			count = (Integer) data.get(FeedbackSummaryIssue.COUNT_PROPERTY);
			percentage = Double.parseDouble(
					((String) data.get(FeedbackSummaryIssue.PERCENTAGE_OF_TOTAL_CALLS_PROPERTY)).replace("%", ""));
		} catch (Exception nfe) {
			throw new IllegalStateException("A Feedback Summary Issue contained improperly formatted data.", nfe);
		}

		return new FeedbackSummaryIssue(description, count, percentage);
	}
}
