package com.twilio.sdk.resource.factory.sip;

import java.util.Map;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.CredentialListMapping;
import org.apache.http.NameValuePair;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SipDomain objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/credential-list-mappings"> http://www.twilio.com/docs/api/rest/credential-list-mappings</a>
 *
 */
public interface CredentialListMappingFactory {


	/**
	 * Creates the ip access control list mapping
	 *
	 * @param params the params map
	 * @return the ip access control list mapping
	 * @throws TwilioRestException
	 */
	public CredentialListMapping create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the ip access control list mapping
	 *
	 * @param params the params list
	 * @return the ip access control list mapping
	 * @throws TwilioRestException
	 */
	public CredentialListMapping create(List<NameValuePair> params) throws TwilioRestException;

}
