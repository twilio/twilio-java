/**
 * 
 */
package com.twilio.stashboard.sdk.resource.instance;

import java.util.Map;

import com.twilio.stashboard.sdk.TwilioServiceStatusReadRestClient;
import com.twilio.stashboard.sdk.resource.ServicesInstanceResource;

/**
 * 
 * Created Aug 15, 2011 3:59:14 PM
 * @author sdakara
 */
public class Status extends ServicesInstanceResource {

	/** The Constant URL_PROPERTY. */
	private static final String URL_PROPERTY = "url";

	/** The Constant ID_PROPERTY. */
	private static final String ID_PROPERTY = "id";

	/** The Constant DESCRIPTION_PROPERTY. */
	private static final String DESCRIPTION_PROPERTY = "description";

	/** The Constant NAME_PROPERTY . */
	private static final String NAME_PROPERTY = "name";

	/** The Constant LEVEL_PROPERTY . */
	private static final String LEVEL_PROPERTY = "level";

	/**
	 * @param client
	 * @param properties
	 */
	public Status(TwilioServiceStatusReadRestClient client, Map<String, Object> properties) {
		super(client, properties);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param client
	 */
	public Status(TwilioServiceStatusReadRestClient client, String id) {
		super(client);
		// TODO Auto-generated constructor stub
		this.setProperty(ID_PROPERTY, id);
	}

	public String getLevel() {
		return getProperty(LEVEL_PROPERTY);
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

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/"+TwilioServiceStatusReadRestClient.DEFAULT_VERSION+"/statuses/"+this.getId();
	}

}
