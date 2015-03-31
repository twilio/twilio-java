package com.twilio.sdk;

import com.twilio.sdk.resource.factory.AccountFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.list.AccountList;

import java.util.HashMap;
import java.util.Map;

/**
 * The client class that access http://api.twilio.com.
 */
public class TwilioRestClient extends TwilioClient {

	/** The auth account. */
	private final Account authAccount;

	public TwilioRestClient(final String accountSid, final String authToken) {
		super(accountSid, authToken, "https://api.twilio.com");

		authAccount = new Account(this);
		authAccount.setSid(accountSid);
		authAccount.setAuthToken(authToken);
	}

	public TwilioRestClient(final String accountSid, final String authToken, String endpoint) {
		super(accountSid, authToken, endpoint);

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
	 * Return an account factory to create new subaccounts
	 *
	 * @return the list of accounts
	 */
	public AccountFactory getAccountFactory() {
		return getAccounts();
	}

	/**
	 * A shortcut for the most common case, returning the Account object for this authenticated client.
	 *
	 * @return Account that maps to the authenticated account.
	 */
	public Account getAccount() {
		return authAccount;
	}

	/**
	 * Get an account by account sid.
	 *
	 * @param sid The sid of the account you want to fetch.
	 * @return the account
	 */
	public Account getAccount(final String sid) {
		Account account = new Account(this);
		account.setSid(sid);
		account.setRequestAccountSid(sid);

		return account;
	}
}
