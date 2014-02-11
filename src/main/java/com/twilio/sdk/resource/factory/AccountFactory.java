package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Account objects.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/subaccounts">https://www.twilio.com/docs/api/rest/subaccounts</a>
 */
public interface AccountFactory {

	/**
	 * Creates a subaccount.
	 *
	 * @param params the params
	 * @return the account
	 * @throws TwilioRestException
	 */
	public Account create(Map<String, String> params) throws TwilioRestException;
	public Account create(List<NameValuePair> params) throws TwilioRestException;
}
