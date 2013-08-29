package com.twilio.sdk.resource.factory;

import java.util.Map;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import org.apache.http.NameValuePair;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Account objects.
 *
 * For more information see <a href="http://www.twilio.com/docs/api/rest/subaccounts">http://www.twilio.com/docs/api/rest/subaccounts</a>
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
