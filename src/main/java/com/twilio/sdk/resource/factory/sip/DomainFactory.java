package com.twilio.sdk.resource.factory.sip;

import java.util.Map;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.Domain;
import org.apache.http.NameValuePair;

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
	 * @param params the params map
	 * @return the sip domain
	 * @throws TwilioRestException
	 */
	public Domain create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the sip domain.
	 *
	 * @param params the params list
	 * @return the sip domain
	 * @throws TwilioRestException
	 */
	public Domain create(List<NameValuePair> params) throws TwilioRestException;
}
