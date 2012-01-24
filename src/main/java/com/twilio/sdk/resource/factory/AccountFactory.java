package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Account objects.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/subaccounts">http://www.twilio.com/docs/api/rest/subaccounts}
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
}
