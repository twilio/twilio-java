package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.AccountFactory;
import com.twilio.sdk.resource.instance.Account;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountList.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/account">http://www.twilio.com/docs/api/rest/account}
 */
public class AccountList extends ListResource<Account> implements AccountFactory {

	/**
	 * Instantiates a new account list.
	 *
	 * @param client the client
	 */
	public AccountList(TwilioRestClient client) {
		super(client);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Account makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new Account(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "accounts";
	}
	
	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.AccountFactory#create(java.util.Map)
	 */
	public Account create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().request(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
