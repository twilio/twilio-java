package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.DependentPhoneNumber;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A list of phone numbers depending on a particular Address for legal requirements.
 * If non-empty, the corresponding Address cannot be deleted until another Address
 * is created on the account to satisfy these requirements (or the numbers are released).
 *
 * Fields on the DependentPhoneNumber instances match those on IncomingPhoneNumber.
 *
 *  For more information see <a href="https://www.twilio.com/docs/api/rest/address">https://www.twilio.com/docs/api/rest/address</a>
 */
public class DependentPhoneNumberList extends ListResource<DependentPhoneNumber, TwilioRestClient> {

	private String addressSid;

	/**
	 * Instantiates a new dependent phone number list.
	 *
	 * @param client the client
	 */
	public DependentPhoneNumberList(final TwilioRestClient client) {
		super(client);
	}

	public DependentPhoneNumberList(final TwilioRestClient client, final String addressSid) {
		super(client);
		this.addressSid = addressSid;
	}

	/**
	 * Instantiates a new dependent phone number list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public DependentPhoneNumberList(final TwilioRestClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ getRequestAccountSid() + "/Conferences.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "dependent_phone_numbers";
	}

	/* (non-Javadoc)
     * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
     */
	@Override
	protected DependentPhoneNumber makeNew(final TwilioRestClient client, final Map<String, Object> properties) {
		return new DependentPhoneNumber(client, properties);
	}

}
