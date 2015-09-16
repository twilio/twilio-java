package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents channels endpoint for ip messages
 */
public class ChannelList extends NextGenListResource<Channel, TwilioIPMessagingClient>
		implements ResourceFactory<Channel> {

	private final String serviceSid;

	public ChannelList(TwilioIPMessagingClient client, String serviceSid) {
		this(client, serviceSid, null);
	}

	public ChannelList(TwilioIPMessagingClient client, String serviceSid, Map<String, String> filters) {
		super(client, filters);

		if (StringUtils.isEmpty(serviceSid)) {
			throw new IllegalArgumentException("serviceSid cannot be null");
		}
		this.serviceSid = serviceSid;
	}

	@Override
	protected Channel makeNew(final TwilioIPMessagingClient client, final Map<String, Object> params) {
		return new Channel(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services/" + this.serviceSid + "/Channels";
	}

	@Override
	public Channel create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	@Override
	public Channel create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
