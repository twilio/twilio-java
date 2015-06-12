package com.twilio.sdk.resource;

import java.util.Map;

import com.twilio.sdk.TwilioClient;

public abstract class NextGenInstanceResource<C extends TwilioClient> extends InstanceResource<C> {

	public NextGenInstanceResource(final C client) {
		super(client);
	}

	public NextGenInstanceResource(final C client, final Map<String, Object> properties) {
		super(client, properties);
	}

	public NextGenInstanceResource(final C client, final Map<String, Object> properties, final Map<String, String> filters) {
		super(client, properties, filters);
	}
}
