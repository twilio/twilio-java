package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.UsageRecord;

/**
 * The Class UsageRecordList.
 *
 */
public class UsageRecordList extends ListResource<UsageRecord> {

	/**
	 * Instantiates a new usage record list.
	 *
	 * @param client the client
	 */
	public UsageRecordList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new usage record list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public UsageRecordList(TwilioRestClient client,
			Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Usage/Records";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected UsageRecord makeNew(TwilioRestClient client,
			Map<String, Object> properties) {
		return new UsageRecord(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "UsageRecords";
	}
}
