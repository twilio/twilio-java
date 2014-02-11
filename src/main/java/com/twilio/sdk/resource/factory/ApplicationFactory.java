package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Application;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Application objects.
 *
 * For more information see <a href=" https://www.twilio.com/docs/api/rest/applications#list-post"> https://www.twilio.com/docs/api/rest/applications#list-post</a>
 *
 */
public interface ApplicationFactory {

	/**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the application
	 * @throws TwilioRestException
	 */
	public Application create(Map<String, String> params) throws TwilioRestException;
	public Application create(List<NameValuePair> params) throws TwilioRestException;
}
