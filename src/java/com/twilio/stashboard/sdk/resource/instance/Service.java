/**
 * 
 */
package com.twilio.stashboard.sdk.resource.instance;

import java.util.Map;

import com.twilio.stashboard.sdk.TwilioServiceStatusReadRestClient;
import com.twilio.stashboard.sdk.resource.ServicesInstanceResource;

/**
 * 
 * Created Aug 15, 2011 4:32:44 PM
 * @author sdakara
 */
public class Service extends ServicesInstanceResource {

	/** The Constant URL_PROPERTY. */
	private static final String URL_PROPERTY = "url";

	/** The Constant ID_PROPERTY. */
	private static final String ID_PROPERTY = "id";

	/** The Constant DESCRIPTION_PROPERTY. */
	private static final String DESCRIPTION_PROPERTY = "description";

	/** The Constant NAME_PROPERTY . */
	private static final String NAME_PROPERTY = "name";

	/** The Constant CURRENT_EVENT_PROPERTY . */
	private static final String CURRENT_EVENT_PROPERTY = "current-event";

	/**
	 * @param client
	 * @param properties
	 */
	public Service(TwilioServiceStatusReadRestClient client, Map<String, Object> properties) {
		super(client, properties);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param client
	 */
	public Service(TwilioServiceStatusReadRestClient client, String id) {
		super(client);
		// TODO Auto-generated constructor stub
		this.setProperty(ID_PROPERTY, id);
	}

	public Event getCurrentEvent() {
		Map<String, Object> currentEventMap = (Map<String, Object>) this
				.getPropertyObject(CURRENT_EVENT_PROPERTY);
		return new Event(getClient(), currentEventMap);
	}
	
	public Events getEvents() {
		return new Events(getClient(), this);
	}

	public String getId() {
		return getProperty(ID_PROPERTY);
	}

	public String getDescription() {
		return getProperty(DESCRIPTION_PROPERTY);
	}

	public String getName() {
		return getProperty(NAME_PROPERTY);
	}

	public String getUrl() {
		return getProperty(URL_PROPERTY);
	}
	
	public void setId(String id){
		this.setProperty(ID_PROPERTY, id);
	}
	
	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/"+TwilioServiceStatusReadRestClient.DEFAULT_VERSION+"/services/"+this.getId();
	}
}
