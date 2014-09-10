package com.twilio.sdk.resource.list.sip;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.sip.CredentialListMappingFactory;
import com.twilio.sdk.resource.instance.sip.CredentialListMapping;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

public class CredentialListMappingList extends ListResource<CredentialListMapping, TwilioRestClient> implements CredentialListMappingFactory {

    private String requestSipDomainSid;

	/**
	 * Instantiates a new list of ip access control list mappings
	 *
	 * @param client the client
	 */
	public CredentialListMappingList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new list of ip access control list mappings
	 *
	 * @param client the client
     * @param sipDomainSid the sid of the sip domain owning this set of mappings
	 */
	public CredentialListMappingList(TwilioRestClient client, String sipDomainSid) {
		super(client);
        this.requestSipDomainSid = sipDomainSid;
	}

	/**
	 * Instantiates a new list of ip access control list mappings
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public CredentialListMappingList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION
            + "/Accounts/" + this.getRequestAccountSid()
            + "/SIP/Domains/" + this.getRequestSipDomainSid()
            + "/CredentialListMappings.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected CredentialListMapping makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new CredentialListMapping(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "credential_list_mappings";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.CredentialListMappingFactory#create(java.util.Map)
	 */
	public CredentialListMapping create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.CredentialListMappingFactory#create(java.util.List)
	 */
	public CredentialListMapping create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

    public String getRequestSipDomainSid() {
        return this.requestSipDomainSid;
    }

}
