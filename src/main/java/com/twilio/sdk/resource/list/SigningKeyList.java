package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.SigningKeyFactory;
import com.twilio.sdk.resource.instance.SigningKey;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * The Class SigningKeyList.
 * <p/>
 * For more information see <a href="https://www.twilio.com/docs/api/rest/signing-keys">https://www.twilio.com/docs/api/rest/signing-keys</a>
 */
public class SigningKeyList extends ListResource<SigningKey, TwilioRestClient> implements SigningKeyFactory {

	/**
	 * Instantiates a new signing key list.
	 *
	 * @param client
	 */
	public SigningKeyList(final TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new signing key list.
	 *
	 * @param client
	 * @param filters
	 */
	public SigningKeyList(final TwilioRestClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	@Override
	protected SigningKey makeNew(final TwilioRestClient client, final Map<String, Object> params) {
		return new SigningKey(client, params);
	}

	@Override
	protected String getListKey() {
		return "signing_keys";
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/SigningKeys.json";
	}

	@Override
	public SigningKey create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public SigningKey create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}
}
