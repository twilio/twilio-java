package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.UsageRecord;
import com.twilio.sdk.resource.instance.UsageTrigger;

import java.util.Map;


public class UsageTriggerList extends ListResource<UsageTrigger> {

	/**
	 * Instantiates a new usage trigger list.
	 *
	 * @param client the client
	 */
	public UsageTriggerList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new usage trigger list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public UsageTriggerList(TwilioRestClient client,
                            Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Usage/Triggers";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected UsageTrigger makeNew(TwilioRestClient client,
			Map<String, Object> properties) {
		return new UsageTrigger(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "UsageTriggers";
	}
}
