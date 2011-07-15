package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.AvailablePhoneNumber;

// TODO: Auto-generated Javadoc
/**
 * The Class AvailablePhoneNumberList.
 * 
 *  For more information see {@see <a href="http://www.twilio.com/docs/api/rest/available-phone-numbers">http://www.twilio.com/docs/api/rest/available-phone-numbers}
 */
public class AvailablePhoneNumberList extends
		ListResource<AvailablePhoneNumber> {

	public static final String TYPE_LOCAL = "Local";
	public static final String TYPE_TOLLFREE = "TollFree";
	
	/** The iso country. */
	private String isoCountry = "US";
	
	/** The type. */
	private String type = "Local";

	/**
	 * Instantiates a new available phone number list.
	 *
	 * @param client the client
	 */
	public AvailablePhoneNumberList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new available phone number list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public AvailablePhoneNumberList(TwilioRestClient client,
			Map<String, String> filters) {
		super(client, filters);
	}

	/**
	 * Instantiates a new available phone number list.
	 *
	 * @param client the client
	 * @param isoCountryCode the iso country code
	 * @param type the type
	 */
	public AvailablePhoneNumberList(TwilioRestClient client,
			String isoCountryCode, String type) {
		super(client);
		this.isoCountry = isoCountryCode;
		this.type = type;
	}
	
	public AvailablePhoneNumberList(TwilioRestClient client,
			Map<String, String> filters, String isoCountryCode, String type) {
		super(client, filters);
		this.isoCountry = isoCountryCode;
		this.type = type;
	}
	

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/AvailablePhoneNumbers/"
				+ this.isoCountry + "/" + this.type + ".json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected AvailablePhoneNumber makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new AvailablePhoneNumber(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "available_phone_numbers";
	}
}
