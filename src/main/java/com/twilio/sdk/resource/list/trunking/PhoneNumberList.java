package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.trunking.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents phone numbers list endpoint
 *
 * @see <a href="https://www.twilio.com/docs/sip-trunking/rest/phone-numbers">Phone Numbers</a>
 */
public class PhoneNumberList extends NextGenListResource<PhoneNumber, TwilioTrunkingClient> implements ResourceFactory<PhoneNumber> {

	private final String trunkSid;

	/**
	 * Initializes phone numbers query without a filter
	 *
	 * @param client A TwilioTrunkingClient
	 * @param trunkSid The trunk sid
	 */
	public PhoneNumberList(final TwilioTrunkingClient client,
						   final String trunkSid) {
		this(client, trunkSid, null);
	}

	/**
	 * Initializes phone numbers query with a filter
	 *
	 * @param client A TwilioTrunkingClient
	 * @param trunkSid The trunk sid
	 * @param filters The filters to apply
	 */
	public PhoneNumberList(final TwilioTrunkingClient client,
						   final String trunkSid,
						   final Map<String, String> filters) {
		super(client, filters);
		if (StringUtils.isEmpty(trunkSid)) {
			throw new IllegalArgumentException("trunkSid cannot be null");
		}
		this.trunkSid = trunkSid;
	}

	@Override
	protected PhoneNumber makeNew(final TwilioTrunkingClient client, final Map<String, Object> params) {
		return new PhoneNumber(client, params);
	}

	/**
	 * Associate phone number with a trunk
	 *
	 * @param params The query params
	 * @return The associated phone number
	 * @throws TwilioRestException
	 */
	@Override
	public PhoneNumber create(final Map<String, String> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	/**
	 * Associate phone number with a trunk
	 *
	 * @param params The query params
	 * @return The associated phone number
	 * @throws TwilioRestException
	 */
	@Override
	public PhoneNumber create(final List<NameValuePair> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTrunkingClient.DEFAULT_VERSION
				+ "/Trunks/" + this.trunkSid
				+ "/PhoneNumbers";
	}
}
