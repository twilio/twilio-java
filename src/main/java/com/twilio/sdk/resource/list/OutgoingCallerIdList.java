package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.OutgoingCallerIdFactory;
import com.twilio.sdk.resource.instance.CallerIdValidation;
import com.twilio.sdk.resource.instance.OutgoingCallerId;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class OutgoingCallerIdList.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/outgoing-caller-ids">https://www.twilio.com/docs/api/rest/outgoing-caller-ids</a>
 */
public class OutgoingCallerIdList extends ListResource<OutgoingCallerId, TwilioRestClient>
		implements OutgoingCallerIdFactory {

	/**
	 * Instantiates a new outgoing caller id list.
	 *
	 * @param client the client
	 */
	public OutgoingCallerIdList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new outgoing caller id list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public OutgoingCallerIdList(TwilioRestClient client,
			Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/OutgoingCallerIds.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected OutgoingCallerId makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new OutgoingCallerId(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "outgoing_caller_ids";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.OutgoingCallerIdFactory#create(java.util.Map)
	 */
	public CallerIdValidation create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return new CallerIdValidation(response);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.OutgoingCallerIdFactory#create(java.util.List)
	 */
	public CallerIdValidation create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return new CallerIdValidation(response);
	}
}
