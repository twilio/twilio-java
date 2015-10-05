package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.trunking.IpAccessControlList;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents IpAccessControlLists list endpoint
 *
 * @see <a href="https://www.twilio.com/docs/sip-trunking/rest/ip-access-control-lists">Ip Access Control List</a>
 */
public class IpAccessControlListList extends NextGenListResource<IpAccessControlList, TwilioTrunkingClient> implements ResourceFactory<IpAccessControlList> {

	private final String trunkSid;

	/**
	 * Initialize ip access control list query without filters
	 *
	 * @param client A TwilioTrunkingClient
	 */
	public IpAccessControlListList(final TwilioTrunkingClient client, final String trunkSid) {
		this(client, trunkSid, null);
	}

	/**
	 * Initialize ip access control list query with filters
	 *
	 * @param client A TwilioTrunkingClient
	 * @param filters The filters for this query
	 */
	public IpAccessControlListList(final TwilioTrunkingClient client,
								   final String trunkSid,
								   final Map<String, String> filters) {
		super(client, filters);

		if (StringUtils.isEmpty(trunkSid)) {
			throw new IllegalArgumentException("trunkSid cannot be null");
		}
		this.trunkSid = trunkSid;
	}

	/**
	 * Instantiate a new ip access control lists
	 *
	 * @param client A TwilioTrunkingClient
	 * @param params The data associated with the ip access control list
	 *
	 * @return An IpAccessControlList with params provided
	 */
	@Override
	protected IpAccessControlList makeNew(final TwilioTrunkingClient client, final Map<String, Object> params) {
		return new IpAccessControlList(client, params);
	}

	/**
	 * Associate ip access control list to a trunk
	 *
	 * @param params The query params
	 * @return The associated ip access control list
	 * @throws TwilioRestException
	 */
	@Override
	public IpAccessControlList create(final Map<String, String> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	/**
	 * Associate ip access control list to a trunk
	 *
	 * @param params The query params
	 * @return The associated ip access control list
	 * @throws TwilioRestException
	 */
	@Override
	public IpAccessControlList create(final List<NameValuePair> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTrunkingClient.DEFAULT_VERSION
				+ "/Trunks/" + this.trunkSid
				+ "/IpAccessControlLists";
	}
}
