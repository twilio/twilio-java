package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.trunking.CredentialList;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents the CredentialList list endpoint
 */
public class CredentialListList extends NextGenListResource<CredentialList, TwilioTrunkingClient> implements ResourceFactory<CredentialList> {

	private final String trunkSid;

	/**
	 * Initialize a credential list
	 *
	 * @param client A TwilioTrunkingClient
	 * @param trunkSid The trunk sid
	 */
	public CredentialListList(final TwilioTrunkingClient client, final String trunkSid) {
		this(client, trunkSid, null);
	}

	/**
	 * Initialize a credential list with filters
	 *
	 * @param client A TwilioTrunkingClient
	 * @param trunkSid The trunk sid
	 * @param filters The filters for this query
	 */
	public CredentialListList(final TwilioTrunkingClient client,
							  final String trunkSid,
							  final Map<String, String> filters) {
		super(client, filters);

		if (StringUtils.isEmpty(trunkSid)) {
			throw new IllegalArgumentException("trunkSid cannot be null");
		}
		this.trunkSid = trunkSid;
	}

	/**
	 * Instantiate a new CredentialList
	 *
	 * @param client A TwilioTrunkingClient
	 * @param params The data associated with a CredentialList
	 *
	 * @return The credential list with params provided
	 */
	@Override
	protected CredentialList makeNew(final TwilioTrunkingClient client, final Map<String, Object> params) {
		return new CredentialList(client, params);
	}

	/**
	 * Associate credential list with a trunk
	 *
	 * @param params The query params
	 * @return The associated credential list
	 * @throws TwilioRestException
	 */
	@Override
	public CredentialList create(final Map<String, String> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	/**
	 * Associate credential list with a trunk
	 *
	 * @param params The query params
	 * @return The associated credential list
	 * @throws TwilioRestException
	 */
	@Override
	public CredentialList create(final List<NameValuePair> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTrunkingClient.DEFAULT_VERSION
				+ "/Trunks/" + this.trunkSid
				+ "/CredentialLists";
	}
}
