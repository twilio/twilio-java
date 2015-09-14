package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.trunking.OriginationUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents the OriginationUrls list endpoint
 *
 * @see <a href="https://www.twilio.com/docs/sip-trunking/rest/origination-urls">OriginationUrls</a>
 */
public class OriginationUrlList extends NextGenListResource<OriginationUrl, TwilioTrunkingClient> implements ResourceFactory<OriginationUrl> {

	private final String trunkSid;

	/**
	 * Initialize an origination url list
	 *
	 * @param client A TwilioTrunkingClient
	 */
	public OriginationUrlList(final TwilioTrunkingClient client, final String trunkSid) {
		this(client, trunkSid, null);
	}

	/**
	 * Initialize an origination url list
	 *
	 * @param client A TwilioTrunkingClient
	 * @param filters The filters for this query
	 */
	public OriginationUrlList(final TwilioTrunkingClient client, final String trunkSid,
							  final Map<String, String> filters) {
		super(client, filters);

		if (StringUtils.isEmpty(trunkSid)) {
			throw new IllegalArgumentException("trunkSid cannot be null");
		}
		this.trunkSid = trunkSid;
	}

	/**
	 * Instantiate a new original urls
	 *
	 * @param client A TwilioTrunkingclient
	 * @param params The data associated with an OriginationUrl
	 *
	 * @return An OriginationUrl with params provided
	 */
	@Override
	protected OriginationUrl makeNew(final TwilioTrunkingClient client, final Map<String, Object> params) {
		return new OriginationUrl(client, params);
	}

	/**
	 * Create an origination url
	 *
	 * @param params The query params
	 * @return The created origination url
	 * @throws TwilioRestException
	 */
	@Override
	public OriginationUrl create(final Map<String, String> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	/**
	 * Create an origination url
	 *
	 * @param params The query params
	 * @return The created origination url
	 * @throws TwilioRestException
	 */
	@Override
	public OriginationUrl create(final List<NameValuePair> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTrunkingClient.DEFAULT_VERSION
				+ "/Trunks/" + this.trunkSid
				+ "/OriginationUrls";
	}
}
