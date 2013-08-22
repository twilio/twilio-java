package com.twilio.sdk.resource.factory.sip;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.IpAccessControlList;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SipDomain objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/ipaccesscontrollist"> http://www.twilio.com/docs/api/rest/ipaccesscontrollist</a>
 *
 */
public interface IpAccessControlListFactory {


	/**
	 * Creates the ip access control list
	 *
	 * @param params the params
	 * @return the ip access control list
	 * @throws TwilioRestException
	 */
	public IpAccessControlList create(Map<String, String> params) throws TwilioRestException;

}
