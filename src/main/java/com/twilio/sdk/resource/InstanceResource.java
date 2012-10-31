package com.twilio.sdk.resource;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class InstanceResource.
 */
public abstract class InstanceResource extends Resource {
	
	/** The properties. */
	private Map<String, Object> properties;

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 */
	public InstanceResource(TwilioRestClient client) {
		super(client);
		this.properties = new HashMap<String, Object>();
	}

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public InstanceResource(TwilioRestClient client,
			Map<String, Object> properties) {
		super(client);
		this.properties = new HashMap<String, Object>(properties);
		this.setLoaded(true);
	}
	
        private Object getAndLoadIfNecessary(String name) {
        	Object prop = properties.get(name);
        
        	if (prop == null && !this.isLoaded()) {
        	    try {
        		this.load(new HashMap<String, String>());
        		return properties.get(name);
        	    } catch (TwilioRestException e) {
        		throw new RuntimeException(e);
        	    }
        	}
        	return prop;
        }

	/**
	 * Gets the property.
	 *
	 * @param name the name
	 * @return the property, 
	 * or null if it doesn't exist or is NULL in the response
	 */
	public String getProperty(String name) {
		Object prop = getAndLoadIfNecessary(name);

		if (prop == null) {
			return null;
		}

		if (prop instanceof String) {
			return (String) prop;
		}

		throw new IllegalArgumentException("Property " + name
				+ " is an object.  Use getObject() instead.");
	}

	protected Object getObject(String name) {
	    	Object prop = getAndLoadIfNecessary(name);

		if (prop == null) {
			throw new IllegalArgumentException("Property " + name
					+ " does not exist");
		}
		
		return prop;
	}
	

       /**
        * Sets the property as an Object
        *
        * @param name the name
        * @param value the value
        */
      protected void setProperty(String name, Object value) {
	  	properties.put(name, value);
      }

	
	/**
	 * Update.
	 *
	 * @param params the params
	 * @throws TwilioRestException the twilio rest exception
	 */
	public void update(Map<String, String> params) throws TwilioRestException {
		this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
	}
	
	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#parseResponse(com.twilio.sdk.TwilioRestResponse)
	 */
	@Override
	protected void parseResponse(TwilioRestResponse response) {
		Map<String, Object> properties = response.toMap();
		this.properties = new HashMap<String, Object>(properties);
	}
}
