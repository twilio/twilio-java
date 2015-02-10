package com.twilio.sdk.resource;

import com.twilio.sdk.TwilioClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;

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

	protected Calendar parseCalendar(final String inDate) {
		if (inDate == null) {
			return null;
		}

		try {
			ISO_8601_DATE_FORMAT.getCalendar().setTimeZone(TimeZone.getTimeZone("UTC"));
			GregorianCalendar c = new GregorianCalendar();
			c.setTimeZone(TimeZone.getTimeZone("UTC"));
			c.setTime(ISO_8601_DATE_FORMAT.parse(inDate));
			return c;
		} catch (ParseException e) {
			return null;
		}
	}
}
