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
	private Account authAccount;

	public TwilioRestClient(final String username, final String password) {
		this(username, password, "https://api.twilio.com");
	}

	public TwilioRestClient(final String username, final String password, String endpoint) {
		super(username, password, endpoint);

		authAccount = new Account(this);
		authAccount.setSid(username);
		authAccount.setAuthToken(password);
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
		return new AccountList(this, params);
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

	public void setRequestAccountSid(String sid) {
		authAccount = new Account(this);
		authAccount.setSid(sid);
		authAccount.setAuthToken(getPassword());
	}
}
