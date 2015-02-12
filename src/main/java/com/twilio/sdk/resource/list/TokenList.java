package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.TokenFactory;
import com.twilio.sdk.resource.instance.Token;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class TokenList.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/token">https://www.twilio.com/docs/api/rest/token</a>
 */
public class TokenList extends ListResource<Token, TwilioRestClient> implements TokenFactory {

	/**
	 * Instantiates a new Token list.
	 *
	 * @param client the client
	 */
	public TokenList(final TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new Token list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public TokenList(final TwilioRestClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ getRequestAccountSid() + "/Tokens.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Token makeNew(final TwilioRestClient client, final Map<String, Object> params) {
		return new Token(client, params);
	}

    @Override
    protected String getListKey() {
        return null;
    }

    /* (non-Javadoc)
     * @see com.twilio.sdk.resource.factory.TokenFactory#create(java.util.Map)
     */
	public Token create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}
}
