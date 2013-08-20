package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.IpAccessControlListMapping;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SipDomain objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/ipaccesscontrollistmapping"> http://www.twilio.com/docs/api/rest/ipaccesscontrollistmapping</a>
 *
 */
public interface IpAccessControlListMappingFactory {


	/**
	 * Creates the ip access control list mapping
	 *
	 * @param params the params
	 * @return the ip access control list mapping
	 * @throws TwilioRestException
	 */
	public IpAccessControlListMapping create(Map<String, String> params) throws TwilioRestException;

}
