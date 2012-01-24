package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Application;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Application objects.
 * 
 * For more information see {@see <a href=" http://www.twilio.com/docs/api/rest/applications#list-post"> http://www.twilio.com/docs/api/rest/applications#list-post}
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
}
