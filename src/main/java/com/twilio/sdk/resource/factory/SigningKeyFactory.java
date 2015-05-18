package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.SigningKey;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

public interface SigningKeyFactory {

	SigningKey create(Map<String, String> params) throws TwilioRestException;

	SigningKey create(List<NameValuePair> params) throws TwilioRestException;
}
