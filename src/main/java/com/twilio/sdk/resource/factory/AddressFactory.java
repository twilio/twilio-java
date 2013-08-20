package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Address;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SipDomain objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/address"> http://www.twilio.com/docs/api/rest/address</a>
 *
 */
public interface AddressFactory {


	/**
	 * Creates the address
	 *
	 * @param params the params
	 * @return the address
	 * @throws TwilioRestException
	 */
	public Address create(Map<String, String> params) throws TwilioRestException;

}
