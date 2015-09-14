package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.trunking.Trunk;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents the Trunks list endpoint
 *
 * @see <a href="https://www.twilio.com/docs/sip-trunking/rest/trunks">Trunks</a>
 */
public class TrunkList extends NextGenListResource<Trunk, TwilioTrunkingClient> implements ResourceFactory<Trunk> {

	/**
	 * Initialize a trunk list
	 * @param client A TwilioTrunkingclient
	 */
	public TrunkList(final TwilioTrunkingClient client) {
		super(client);
	}

	/**
	 * Initializes an Trunk list
	 *
	 * @param client A TwilioTrunkingClient
	 * @param filters The filters for this query
	 */
	public TrunkList(final TwilioTrunkingClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	/**
	 * Instantiate a new Trunk
	 *
	 * @param client A TwilioTrunkingclient
	 * @param params The data associated with a Trunk
	 *
	 * @return A Trunk with the params provided
	 */
	@Override
	protected Trunk makeNew(final TwilioTrunkingClient client, final Map<String, Object> params) {
		return new Trunk(client, params);
	}

	/**
	 * Create a new trunk
	 *
	 * @param params The trunk parameters
	 *
	 * @return The created trunk
	 * @throws TwilioRestException
	 */
	@Override
	public Trunk create(final Map<String, String> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	/**
	 * Create a new trunk
	 *
	 * @param params The trunk parameters
	 *
	 * @return The created trunk
	 * @throws TwilioRestException
	 */
	@Override
	public Trunk create(final List<NameValuePair> params) throws TwilioRestException {
		final TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	/**
	 * Delete the trunk
	 *
	 * @return True iff the delete was successfull
	 * @throws TwilioRestException
	 */
	@SuppressWarnings("unchecked")
	public boolean delete() throws TwilioRestException {
		final TwilioRestResponse response = this.getClient()
				.safeRequest(this.getResourceLocation(), "DELETE", (Map) null);
		return !response.isError();
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTrunkingClient.DEFAULT_VERSION + "/Trunks";
	}

}
