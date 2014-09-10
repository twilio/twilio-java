package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.UsageRecord;

import java.util.Map;

/**
 * The Class UsageRecordList.
 */
public class UsageRecordList extends ListResource<UsageRecord, TwilioRestClient> {

	private final Type type;

	/**
	 * Instantiates a new usage record list.
	 *
	 * @param client the client
	 */
	public UsageRecordList(final TwilioRestClient client) {
		this(client, (Type) null);
	}

	/**
	 * Instantiates a new usage record list.
	 *
	 * @param client the client
	 * @param type
	 */
	public UsageRecordList(final TwilioRestClient client, final Type type) {
		super(client);
		this.type = type;
	}

	/**
	 * Instantiates a new usage record list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public UsageRecordList(final TwilioRestClient client, final Map<String, String> filters) {
		this(client, filters, null);
	}

	/**
	 * Instantiates a new usage record list.
	 *
	 * @param client the client
	 * @param filters the filters
	 * @param type
	 */
	public UsageRecordList(final TwilioRestClient client, final Map<String, String> filters, final Type type) {
		super(client, filters);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		if (type == null) {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Usage/Records";
		} else {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Usage/Records/" + type
					.getValue();
		}
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected UsageRecord makeNew(final TwilioRestClient client, final Map<String, Object> properties) {
		return new UsageRecord(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "UsageRecords";
	}

	public enum Type {
		ALL_TIME("AllTime"),
		DAILY("Daily"),
		LAST_MONTH("LastMonth"),
		MONTHLY("Monthly"),
		THIS_MONTH("ThisMonth"),
		TODAY("Today"),
		YEARLY("Yearly"),
		YESTERDAY("Yesterday");

		private final String value;

		private Type(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
