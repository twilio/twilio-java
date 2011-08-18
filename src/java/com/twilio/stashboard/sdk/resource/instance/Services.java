/**
 * 
 */
package com.twilio.stashboard.sdk.resource.instance;

import java.util.Map;

import com.twilio.stashboard.sdk.TwilioServiceStatusReadRestClient;
import com.twilio.stashboard.sdk.resource.ServicesListResource;

/**
 * 
 * Created Aug 15, 2011 9:01:44 PM
 * @author sdakara
 */
public class Services extends ServicesListResource<Service> {

	/** The Constant SERVICES_PROPERTY . */
	private static final String SERVICES_PROPERTY = "services";

	/**
	 * @param client
	 */
	public Services(TwilioServiceStatusReadRestClient client) {
		super(client);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		// TODO Auto-generated method stub
		return SERVICES_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		// TODO Auto-generated method stub
		return getClient().getEndpoint() + "/"
				+ TwilioServiceStatusReadRestClient.DEFAULT_VERSION
				+ "/"+SERVICES_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ServicesListResource#makeNew(com.twilio.sdk.TwilioServiceStatusReadRestClient, java.util.Map)
	 */
	@Override
	protected Service makeNew(TwilioServiceStatusReadRestClient client,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new Service(getClient(), params);
	}
}
