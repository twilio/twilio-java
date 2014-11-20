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
public class TokenList extends ListResource<Token> implements TokenFactory {

	/**
	 * Instantiates a new Token list.
	 *
	 * @param client the client
	 */
	public TokenList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new Token list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public TokenList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Tokens.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Token makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new Token(client, params);
	}

    @Override
    protected String getListKey() {
        return null;
    }

    /* (non-Javadoc)
     * @see com.twilio.sdk.resource.factory.TokenFactory#create(java.util.Map)
     */
	public Token create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
