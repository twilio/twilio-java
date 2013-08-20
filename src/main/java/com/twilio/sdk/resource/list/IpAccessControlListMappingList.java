package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.IpAccessControlListMappingFactory;
import com.twilio.sdk.resource.instance.IpAccessControlListMapping;

public class IpAccessControlListMappingList extends ListResource<IpAccessControlListMapping> implements IpAccessControlListMappingFactory {

    private String requestDomainSid;

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
	 * @param filters the filters
	 */
	public IpAccessControlListMappingList(TwilioRestClient client, String sipDomainSid) {
		super(client, filters);
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
            + "/SIP/Domains/" + this.getRequestDomainSid()
            + "/IpAccessControlListMappings.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected IpAccessControlListMapping makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new IpAccessControListMapping(client, params);
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

    public String getRequestDomainSid() {
        return this.requestDomainSid;
    }

}
