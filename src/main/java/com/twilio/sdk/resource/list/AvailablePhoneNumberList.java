package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.AvailablePhoneNumber;

import java.util.Map;

/**
 * The Class AvailablePhoneNumberList.
 * <p/>
 * For more information see <a href="https://www.twilio.com/docs/api/rest/available-phone-numbers">https://www.twilio.com/docs/api/rest/available-phone-numbers</a>
 */
public class AvailablePhoneNumberList extends ListResource<AvailablePhoneNumber, TwilioRestClient> {

	public static final String TYPE_LOCAL = "Local";
	public static final String TYPE_TOLLFREE = "TollFree";
	public static final String TYPE_MOBILE = "Mobile";

	/** The iso country. */
	private String isoCountry = "US";

	/** The type. */
	private String type = "Local";

	/**
	 * Instantiates a new available phone number list.
	 *
	 * @param client the client
	 */
	public AvailablePhoneNumberList(final TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new available phone number list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public AvailablePhoneNumberList(final TwilioRestClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	/**
	 * Instantiates a new available phone number list.
	 *
	 * @param client the client
	 * @param isoCountryCode the iso country code
	 * @param type the type
	 */
	public AvailablePhoneNumberList(final TwilioRestClient client, final String isoCountryCode, final String type) {
		super(client);
		isoCountry = isoCountryCode;
		this.type = type;
	}

	public AvailablePhoneNumberList(final TwilioRestClient client, final Map<String, String> filters,
	                                final String isoCountryCode, final String type) {
		super(client, filters);
		isoCountry = isoCountryCode;
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() +
		       "/AvailablePhoneNumbers/" + isoCountry + "/" + type + ".json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected AvailablePhoneNumber makeNew(final TwilioRestClient client, final Map<String, Object> params) {
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
