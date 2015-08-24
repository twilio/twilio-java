package com.twilio.sdk;

import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.list.AccountList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;

/**
 * The client class that access http://api.twilio.com.
 */
public class TwilioRestClient extends TwilioClient {

	private static final String API_URL = "https://api.twilio.com";

	@Override
	public String getEndpoint(){
		return API_URL;
	}
	/** The auth account. */
	private final Account authAccount;

	public TwilioRestClient(final String accountSid, final String authToken) {
		super(accountSid, authToken);

		authAccount = new Account(this);
		authAccount.setSid(accountSid);
		authAccount.setAuthToken(authToken);
	}

	/*
	 * Resource related methods
	 */

	/**
	 * Get a list of Account objects. For more info: {@link <a href="https://www.twilio.com/docs/api/rest/account">https://www.twilio.com/docs/api/rest/account</a>}
	 *
	 * @param params Filter the list with the given params. See the Twilio docs for available filters.
	 * @return the list of accounts.
	 */
	public AccountList getAccounts(final Map<String, String> params) {
		AccountList list = new AccountList(this, params);
		list.setRequestAccountSid(getAccountSid());
		return list;
	}

	/**
	 * Get all accounts. For more info: {@link <a href="https://www.twilio.com/docs/api/rest/account"
	 * >https://www.twilio.com/docs/api/rest/account</a>}
	 *
	 * @return the list of accounts.
	 */
	public AccountList getAccounts() {
		return getAccounts(new HashMap<String, String>());
	}


	/**
	 * A shortcut for the most common case, returning the Account object for this authenticated client.
	 *
	 * @return Account that maps to the authenticated account.
	 */
	public Account getAccount() {
		return authAccount;
	}

	public String execute(Map<String, String> map) throws TwilioRestException
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>(map.keySet().size());

		Set<String> keys = map.keySet();
		for(String key : keys){
			params.add(new BasicNameValuePair(key,map.get(key)));
		}

		MessageFactory messageFactory = getAccount().getMessageFactory();
		Message message = messageFactory.create(params);
		return message.getSid();
	}
}
