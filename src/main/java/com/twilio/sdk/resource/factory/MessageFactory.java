package com.twilio.sdk.resource.factory;

import java.util.Map;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Message objects.
 */
public interface MessageFactory {

	/**
	 * Creates the message.
	 *
	 * @param params the params list
	 * @return the message
	 * @throws TwilioRestException
   */
	public Message create(List<NameValuePair> params) throws TwilioRestException;
}
