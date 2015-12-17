package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.ipmessaging.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represent users list endpoint for ip messaging
 */
public class UserList extends NextGenListResource<User, TwilioIPMessagingClient>
		implements ResourceFactory<User> {

	private final String serviceSid;

	public UserList(TwilioIPMessagingClient client, String serviceId) {
		this(client, serviceId, null);
	}

	public UserList(TwilioIPMessagingClient client, String serviceSid, Map<String, String> filters) {
		super(client, filters);

		if (StringUtils.isEmpty(serviceSid)) {
			throw new IllegalArgumentException("serviceSid cannot be null");
		}
		this.serviceSid = serviceSid;
	}

	@Override
	protected User makeNew(final TwilioIPMessagingClient client, final Map<String, Object> params) {
		return new User(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services/" + this.serviceSid + "/Users";
	}

	@Override
	public User create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	@Override
	public User create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
