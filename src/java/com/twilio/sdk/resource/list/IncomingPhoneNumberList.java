package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.IncomingPhoneNumberFactory;
import com.twilio.sdk.resource.instance.IncomingPhoneNumber;

// TODO: Auto-generated Javadoc
/**
 * The Class IncomingPhoneNumberList.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/incoming-phone-numbers">http://www.twilio.com/docs/api/rest/incoming-phone-numbers}
 */
public class IncomingPhoneNumberList extends ListResource<IncomingPhoneNumber>
		implements IncomingPhoneNumberFactory {

	/**
	 * Instantiates a new incoming phone number list.
	 *
	 * @param client the client
	 */
	public IncomingPhoneNumberList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new incoming phone number list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public IncomingPhoneNumberList(TwilioRestClient client,
			Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/IncomingPhoneNumbers.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected IncomingPhoneNumber makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new IncomingPhoneNumber(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "incoming_phone_numbers";
	}
	
	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.IncomingPhoneNumberFactory#create(java.util.Map)
	 */
	public IncomingPhoneNumber create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().request(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
