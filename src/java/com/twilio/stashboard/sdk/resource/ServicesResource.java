/**
 * 
 */
package com.twilio.stashboard.sdk.resource;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.stashboard.sdk.TwilioServiceStatusReadRestClient;

// TODO: Auto-generated Javadoc
/**
 * The Class Resource.
 */
public abstract class ServicesResource {

	/** The client. */
	private TwilioServiceStatusReadRestClient client;
	
	/** The filters. */
	protected Map<String, String> filters;
	
	/**
	 * Instantiates a new resource.
	 *
	 * @param client the client
	 */
	public ServicesResource(TwilioServiceStatusReadRestClient client) {
		this.client = client;
	}
	
	/**
	 * Gets the client.
	 *
	 * @return the client
	 */
	protected TwilioServiceStatusReadRestClient getClient() {
		return this.client;
	}
	
	/**
	 * Load.
	 *
	 * @param params the params
	 * @throws TwilioRestException the twilio rest exception
	 */
	protected void load(Map<String, String> params) throws TwilioRestException {
		String path = this.getResourceLocation();
		TwilioRestResponse response = this.getClient().safeRequest(path, "GET",
				params);

		this.parseResponse(response);
		this.loaded = true;
	}
	
	/**
	 * Parses the response.
	 *
	 * @param response the response
	 */
	protected abstract void parseResponse(TwilioRestResponse response);
	
	// flags whether or not the HTTP request to popluate
	// this data has occured. We can construct resources
	// that are lazily loaded
	/** The loaded. */
	private boolean loaded;

	/**
	 * Checks if is loaded.
	 *
	 * @return true, if is loaded
	 */
	protected boolean isLoaded() {
		return loaded;
	}
	
	/**
	 * Sets the loaded.
	 *
	 * @param loaded the new loaded
	 */
	protected void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	/**
	 * Gets the resource location.
	 *
	 * @return the resource location
	 */
	protected abstract String getResourceLocation();

}
