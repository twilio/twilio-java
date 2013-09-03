package com.twilio.sdk.resource.factory.sip;

import java.util.Map;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.Address;
import org.apache.http.NameValuePair;

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
	 * @param params the params map
	 * @return the address
	 * @throws TwilioRestException
	 */
	public Address create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the address
	 *
	 * @param params the params list
	 * @return the address
	 * @throws TwilioRestException
	 */
	public Address create(List<NameValuePair> params) throws TwilioRestException;

}
