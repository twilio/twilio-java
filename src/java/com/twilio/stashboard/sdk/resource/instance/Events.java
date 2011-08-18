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
public class Events extends ServicesListResource<Event> {

	/** The Service to which these events belong*/
	private Service service;
	/**
	 * @param client
	 */
	public Events(TwilioServiceStatusReadRestClient client, Service service) {
		super(client);
		this.service = service; 
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ServicesListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		// TODO Auto-generated method stub
		return "events";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ServicesResource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ServicesListResource#makeNew(com.twilio.sdk.TwilioServiceStatusReadRestClient, java.util.Map)
	 */
	@Override
	protected Event makeNew(TwilioServiceStatusReadRestClient client,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new Event(client,service);
	}

}
