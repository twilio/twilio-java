package com.twilio.sdk.resource;

import com.twilio.sdk.TwilioClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public abstract class NextGenInstanceResource<C extends TwilioClient> extends InstanceResource<C> {

	protected static final SimpleDateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public NextGenInstanceResource(final C client) {
		super(client);
	}

	public NextGenInstanceResource(final C client, final Map<String, Object> properties) {
		super(client, properties);
	}

	public NextGenInstanceResource(final C client, final Map<String, Object> properties, final Map<String, String> filters) {
		super(client, properties, filters);
	}

	@Override
	protected Date parseDate(final String inDate) {
		if (inDate == null) {
			return null;
		}

		try {
			return ISO_8601_DATE_FORMAT.parse(inDate);
		} catch (ParseException e) {
			return null;
		}
	}
}
