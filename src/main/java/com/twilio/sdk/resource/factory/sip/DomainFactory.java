package com.twilio.sdk.resource.factory.sip;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.Domain;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Domain objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/sip-domain"> http://www.twilio.com/docs/api/rest/sip-domain</a>
 *
 */
public interface DomainFactory {

	/**
	 * Creates the sip domain.
	 *
	 * @param params the params
	 * @return the sip domain
	 * @throws TwilioRestException
	 */
	public Domain create(Map<String, String> params) throws TwilioRestException;
}
