package com.twilio.sdk.resource.factory.sip;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.IpAddress;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SipDomain objects.
 *
 * For more information see <a href=" https://www.twilio.com/docs/api/rest/ip-address"> https://www.twilio.com/docs/api/rest/ip-address</a>
 *
 */
public interface IpAddressFactory {


	/**
	 * Creates the IpAddress
	 *
	 * @param params the params map
	 * @return the IpAddress
	 * @throws TwilioRestException
	 */
	public IpAddress create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the IpAddress
	 *
	 * @param params the params list
	 * @return the IpAddress
	 * @throws TwilioRestException
	 */
	public IpAddress create(List<NameValuePair> params) throws TwilioRestException;

}
