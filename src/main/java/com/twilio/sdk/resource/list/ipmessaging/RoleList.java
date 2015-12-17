package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.ipmessaging.Role;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents roles endpoint for ip messaging
 */
public class RoleList extends NextGenListResource<Role, TwilioIPMessagingClient>
		implements ResourceFactory<Role> {

	private final String serviceSid;

	public RoleList(TwilioIPMessagingClient client, String serviceSid) {
		this(client, serviceSid, null);
	}

	public RoleList(TwilioIPMessagingClient client, String serviceSid, Map<String, String> filters) {
		super(client, filters);

		if (StringUtils.isEmpty(serviceSid)) {
			throw new IllegalArgumentException("serviceSid cannot be null");
		}
		this.serviceSid = serviceSid;
	}

	@Override
	protected Role makeNew(final TwilioIPMessagingClient client, final Map<String, Object> params) {
		return new Role(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services/" + this.serviceSid + "/Roles";
	}

	@Override
	public Role create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	@Override
	public Role create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
