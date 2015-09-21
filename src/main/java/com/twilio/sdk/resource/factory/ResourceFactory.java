package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.Resource;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Interface that defines factory for next gen resources
 */
public interface ResourceFactory<T extends Resource<?>> {

    public T create(Map<String, String> params) throws TwilioRestException;

    public T create(List<NameValuePair> params) throws TwilioRestException;

}
