/**
 * 
 */
package com.twilio.stashboard.sdk.resource.instance;

import java.util.Map;

import com.twilio.stashboard.sdk.TwilioServiceStatusReadRestClient;
import com.twilio.stashboard.sdk.resource.ServicesListResource;

/**
 * @author sdakara
 *
 */
public class Statuses extends ServicesListResource<Status> {
	
	/** The Constant STATUSES_PROPERTY . */
	private static final String STATUSES_PROPERTY = "statuses";
	
	/**
	 * @param client
	 */
	public Statuses(TwilioServiceStatusReadRestClient client) {
		super(client);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ServicesListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		// TODO Auto-generated method stub
		return STATUSES_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ServicesListResource#makeNew(com.twilio.sdk.TwilioServiceStatusReadRestClient, java.util.Map)
	 */
	@Override
	protected Status makeNew(TwilioServiceStatusReadRestClient client,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new Status(client,params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ServicesResource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		// TODO Auto-generated method stub
		return getClient().getEndpoint() + "/"
		+ TwilioServiceStatusReadRestClient.DEFAULT_VERSION
		+ "/" + STATUSES_PROPERTY;
	}

}
