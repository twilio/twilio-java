package com.twilio.sdk;

import com.twilio.sdk.resource.instance.lookups.PhoneNumber;

import java.util.HashMap;
import java.util.Map;

public class LookupsClient extends TwilioClient {

	public static final String DEFAULT_VERSION = "v1";

	public LookupsClient(final String accountSid, final String authToken) {
		super(accountSid, authToken, "https://lookups.twilio.com");
	}

	public LookupsClient(final String accountSid, final String authToken, final String endpoint) {
		super(accountSid, authToken, endpoint);
	}

	public PhoneNumber getPhoneNumber(final String number) {
		return getPhoneNumber(number, null, false);
	}

	public PhoneNumber getPhoneNumber(final String number, final Boolean includeCarrierInfo) {
		return getPhoneNumber(number, null, includeCarrierInfo);
	}

	public PhoneNumber getPhoneNumber(final String number, final String countryCode, final Boolean includeCarrierInfo) {
		Map<String, String> params = new HashMap<String, String>();
		if (includeCarrierInfo) {
			params.put("Type", "carrier");
		}

		if (countryCode != null) {
			params.put("CountryCode", countryCode);
		}

		return new PhoneNumber(this, number, params);
	}
}
