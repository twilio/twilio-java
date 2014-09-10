package com.twilio.sdk.resource.list.sip;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.sip.DomainFactory;
import com.twilio.sdk.resource.instance.sip.Domain;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class DomainList.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/sip-domain">https://www.twilio.com/docs/api/rest/sip-domain</a>
 */
public class DomainList extends ListResource<Domain, TwilioRestClient> implements DomainFactory {

	/**
	 * Instantiates a new sip domain list.
	 *
	 * @param client the client
	 */
	public DomainList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new sip domain list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public DomainList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/SIP/Domains.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Domain makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new Domain(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "domains";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.DomainFactory#create(java.util.Map)
	 */
	public Domain create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.DomainFactory#create(java.util.List)
	 */
	public Domain create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
