package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.ShortCode;

// TODO: Auto-generated Javadoc
/**
 * The Class IncomingPhoneNumberList.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/shortcodes">http://www.twilio.com/docs/api/rest/shortcodes}
 */
public class ShortCodeList extends ListResource<ShortCode> {

	/**
	 * Instantiates a new incoming phone number list.
	 *
	 * @param client the client
	 */
	public ShortCodeList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new incoming phone number list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public ShortCodeList(TwilioRestClient client,
			Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Shortcodes.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected ShortCode makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new ShortCode(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "short_codes";
	}
	
}
