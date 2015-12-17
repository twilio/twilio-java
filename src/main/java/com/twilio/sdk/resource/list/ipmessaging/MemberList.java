package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.ipmessaging.Member;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Represents members list endpoint for ip messaging
 */
public class MemberList extends NextGenListResource<Member, TwilioIPMessagingClient>
		implements ResourceFactory<Member> {

	private final String serviceSid;
	private final String channelSid;

	public MemberList(TwilioIPMessagingClient client,
					  String serviceSid,
					  String channelSid) {
		this(client, serviceSid, channelSid, null);
	}

	public MemberList(TwilioIPMessagingClient client,
					  String serviceSid,
					  String channelSid,
					  Map<String, String> filters) {
		super(client, filters);
		if (StringUtils.isEmpty(serviceSid)) {
			throw new IllegalArgumentException("serviceSid cannot be null");
		}
		if (StringUtils.isEmpty(channelSid)) {
			throw new IllegalArgumentException("channelSid cannot be null");
		}
		this.serviceSid = serviceSid;
		this.channelSid = channelSid;
	}

	@Override
	protected Member makeNew(final TwilioIPMessagingClient client, final Map<String, Object> params) {
		return new Member(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION
				+ "/Services/" + this.serviceSid
				+ "/Channels/" + this.channelSid
				+ "/Members";
	}

	@Override
	public Member create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	@Override
	public Member create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
