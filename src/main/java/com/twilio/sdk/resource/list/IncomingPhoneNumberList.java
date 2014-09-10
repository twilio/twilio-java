package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.IncomingPhoneNumberFactory;
import com.twilio.sdk.resource.instance.IncomingPhoneNumber;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * The Class IncomingPhoneNumberList.
 * <p/>
 * For more information see <a href="https://www.twilio.com/docs/api/rest/incoming-phone-numbers">https://www.twilio.com/docs/api/rest/incoming-phone-numbers</a>
 */
public class IncomingPhoneNumberList extends ListResource<IncomingPhoneNumber, TwilioRestClient>
		implements IncomingPhoneNumberFactory {

	public static final String TYPE_LOCAL = "Local";
	public static final String TYPE_TOLLFREE = "TollFree";
	public static final String TYPE_MOBILE = "Mobile";

	/** The type. */
	public String type = null;

	/**
	 * Instantiates a new incoming phone number list.
	 *
	 * @param client the client
	 */
	public IncomingPhoneNumberList(final TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new incoming phone number list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public IncomingPhoneNumberList(final TwilioRestClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	/**
	 * Instantiates a new incoming phone number list.
	 *
	 * @param client the client
	 * @param type the type
	 * @param filters the filters
	 */
	public IncomingPhoneNumberList(final TwilioRestClient client, final String type,
	                               final Map<String, String> filters) {
		super(client, filters);
		this.type = type;
	}

	/**
	 * Instantiates a new incoming phone number list.
	 *
	 * @param client the client
	 * @param type the type
	 */
	public IncomingPhoneNumberList(final TwilioRestClient client, final String type) {
		super(client);
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		if (type != null) {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/IncomingPhoneNumbers/" + type + ".json";
		} else {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/IncomingPhoneNumbers.json";
		}
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected IncomingPhoneNumber makeNew(final TwilioRestClient client, final Map<String, Object> params) {
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
	public IncomingPhoneNumber create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.IncomingPhoneNumberFactory#create(java.util.List)
	 */
	public IncomingPhoneNumber create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}
}
