package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents service endpoint for ip messaging
 */
public class ServiceList extends NextGenListResource<Service, TwilioIPMessagingClient>
		implements ResourceFactory<Service> {

	public ServiceList(TwilioIPMessagingClient client, Map<String, String> filters) {
		super(client, filters);
	}

	public ServiceList(TwilioIPMessagingClient client) {
		super(client);
	}

	@Override
	public Service create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	@Override
	public Service create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	@Override
	protected Service makeNew(final TwilioIPMessagingClient client, final Map<String, Object> params) {
		return new Service(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services";
	}
}
