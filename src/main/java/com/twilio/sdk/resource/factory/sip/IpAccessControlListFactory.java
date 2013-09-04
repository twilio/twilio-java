package com.twilio.sdk.resource.factory.sip;

import java.util.Map;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.IpAccessControlList;
import org.apache.http.NameValuePair;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IpAccessControlList objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/ipaccesscontrollist"> http://www.twilio.com/docs/api/rest/ipaccesscontrollist</a>
 *
 */
public interface IpAccessControlListFactory {


	/**
	 * Creates the ip access control list
	 *
	 * @param params the params map
	 * @return the ip access control list
	 * @throws TwilioRestException
	 */
	public IpAccessControlList create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the ip access control list
	 *
	 * @param params the params list
	 * @return the ip access control list
	 * @throws TwilioRestException
	 */
	public IpAccessControlList create(List<NameValuePair> params) throws TwilioRestException;

}
