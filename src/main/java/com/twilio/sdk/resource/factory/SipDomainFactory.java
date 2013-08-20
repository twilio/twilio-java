package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.SipDomain;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SipDomain objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/sip-domain"> http://www.twilio.com/docs/api/rest/sip-domain</a>
 *
 */
public interface SipDomainFactory {

	/**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the sip domain
	 * @throws TwilioRestException
	 */
	public SipDomain create(Map<String, String> params) throws TwilioRestException;
}
