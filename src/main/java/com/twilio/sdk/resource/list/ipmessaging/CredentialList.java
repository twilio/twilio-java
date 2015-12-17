package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.ipmessaging.Credential;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents credential list endpoint for ip messaging
 */
public class CredentialList extends NextGenListResource<Credential, TwilioIPMessagingClient>
		implements ResourceFactory<Credential> {

	public CredentialList(TwilioIPMessagingClient client) {
		super(client);
	}

	public CredentialList(TwilioIPMessagingClient client, Map<String, String> filters) {
		super(client, filters);
	}

	@Override
	protected Credential makeNew(final TwilioIPMessagingClient client, final Map<String, Object> params) {
		return new Credential(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Credentials";
	}

	@Override
	public Credential create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	@Override
	public Credential create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
