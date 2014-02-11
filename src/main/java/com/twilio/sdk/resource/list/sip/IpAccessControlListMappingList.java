package com.twilio.sdk.resource.list.sip;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.sip.IpAccessControlListMappingFactory;
import com.twilio.sdk.resource.instance.sip.IpAccessControlListMapping;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

public class IpAccessControlListMappingList extends ListResource<IpAccessControlListMapping> implements IpAccessControlListMappingFactory {

    private String requestSipDomainSid;

	/**
	 * Instantiates a new list of ip access control list mappings
	 *
	 * @param client the client
	 */
	public IpAccessControlListMappingList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new list of ip access control list mappings
	 *
	 * @param client the client
     * @param sipDomainSid the sid of the sip domain owning this set of mappings
	 */
	public IpAccessControlListMappingList(TwilioRestClient client, String sipDomainSid) {
		super(client);
        this.requestSipDomainSid = sipDomainSid;
	}

	/**
	 * Instantiates a new list of ip access control list mappings
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public IpAccessControlListMappingList(TwilioRestClient client, Map<String, String> filters) {
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
            + "/IpAccessControlListMappings.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected IpAccessControlListMapping makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new IpAccessControlListMapping(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "ip_access_control_list_mappings";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.IpAccessControlListMappingFactory#create(java.util.Map)
	 */
	public IpAccessControlListMapping create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.IpAccessControlListMappingFactory#create(java.util.Map)
	 */
	public IpAccessControlListMapping create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

    public String getRequestSipDomainSid() {
        return this.requestSipDomainSid;
    }

}
