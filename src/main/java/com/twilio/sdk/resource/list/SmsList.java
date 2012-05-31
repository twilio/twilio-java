package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;

// TODO: Auto-generated Javadoc
/**
 * The Class SmsList.
 * 
 * For more information see <a href="http://www.twilio.com/docs/api/rest/sms">http://www.twilio.com/docs/api/rest/sms</a>
 */
public class SmsList extends ListResource<Sms> implements SmsFactory {
	
	/**
	 * Instantiates a new sms list.
	 *
	 * @param client the client
	 */
	public SmsList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new sms list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public SmsList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/SMS/Messages.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Sms makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new Sms(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "sms_messages";
	}
	
	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.SmsFactory#create(java.util.Map)
	 */
	public Sms create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
