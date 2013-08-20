package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.SipDomainFactory;
import com.twilio.sdk.resource.instance.SipDomain;

// TODO: Auto-generated Javadoc
/**
 * The Class SipDomainList.
 *
 * For more information see <a href="http://www.twilio.com/docs/api/rest/sip-domain">http://www.twilio.com/docs/api/rest/sip-domain</a>
 */
public class SipDomainList extends ListResource<SipDomain> implements SipDomainFactory {

	/**
	 * Instantiates a new sip domain list.
	 *
	 * @param client the client
	 */
	public SipDomainList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new sip domain list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public SipDomainList(TwilioRestClient client, Map<String, String> filters) {
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
	protected SipDomain makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new SipDomain(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "sip_domains";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.SipDomainFactory#create(java.util.Map)
	 */
	public SipDomain create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
