package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.AddressFactory;
import com.twilio.sdk.resource.instance.Address;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressList.
 *
 *  For more information see <a href="https://www.twilio.com/docs/api/rest/address">https://www.twilio.com/docs/api/rest/address</a>
 */
public class AddressList extends ListResource<Address> implements AddressFactory {

	/**
	 * Instantiates a new address list.
	 *
	 * @param client the client
	 */
	public AddressList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new address list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public AddressList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Conferences.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Address makeNew(TwilioRestClient client,
	                             Map<String, Object> params) {
		return new Address(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "addresses";
	}

	public Address create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	public Address create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params
		);
		return makeNew(this.getClient(), response.toMap());
	}


}
