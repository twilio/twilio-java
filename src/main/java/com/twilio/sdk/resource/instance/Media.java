package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;


/**
 * The Class Media.
 *
 *  For more information see <a href="https://www.twilio.com/docs/api/rest/media">https://www.twilio.com/docs/api/rest/media</a>
 */
public class Media extends InstanceResource {

	private static final String SID_PROPERTY = "sid";
	private static String requestMessageSid;

	/**
	 * Instantiates a new media instance.
	 *
	 * @param client the client
	 */
	public Media(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new media instance.
	 *
	 * @param client the client
	 * @param mediaSid the sid
	 */
	public Media(TwilioRestClient client, String mediaSid) {
		super(client);
		if (mediaSid == null) {
			throw new IllegalStateException("The sid for a Media instance can not be null");
		}
		this.setProperty(SID_PROPERTY, mediaSid);
	}

	/**
	 * Instantiates a new media instance.
	 *
	 * @param client the client
	 * @param messageSid the sid of the parent message
	 * @param mediaSid the sid
	 */
	public Media(TwilioRestClient client, String messageSid, String mediaSid) {
		super(client);
		this.requestMessageSid = messageSid;
		if (mediaSid == null) {
			throw new IllegalStateException("The sid for a Media instance can not be null");
		}
		this.setProperty(SID_PROPERTY, mediaSid);
	}

	/**
	 * Instantiates a new media instance.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Media(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	protected String getResourceLocation() {
		if (this.getRequestMessageSid() != null) {
			return "/" + TwilioRestClient.DEFAULT_VERSION
				+ "/Accounts/" + this.getRequestAccountSid()
				+ "/Messages/" + this.getRequestMessageSid()
				+ "/Media/" + this.getSid() + ".json";
		} else {
			return "/" + TwilioRestClient.DEFAULT_VERSION
				+ "/Accounts/" + this.getRequestAccountSid()
				+ "/Media/" + this.getSid() + ".json";
		}
	}

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return this.getProperty(SID_PROPERTY);
	}

	/**
	 * Gets the sid of the requesting message
	 *
	 * @return the sid of the requesting message
	 */
	public String getRequestMessageSid() {
		return this.requestMessageSid;
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(this.getProperty("date_created"));
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(this.getProperty("date_updated"));
	}

	/**
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return this.getProperty("account_sid");
	}

	/**
	 * Gets the parent sid.
	 *
	 * @return the account sid
	 */
	public String getParentSid() {
		return this.getProperty("parent_sid");
	}

	/**
	 * Gets the content type
	 *
	 * @return the content type
	 */
	public String getContentType() {
		return this.getProperty("content_type");
	}

	/**
	 * Gets the duration
	 *
	 * @return the duration
	 */
	public Integer getDuration() {
		Integer duration = (Integer) this.getObject("duration");
		if (duration != null) {
			return duration;
		} else {
			throw new IllegalStateException("The Media instance doesn't have the max size property set");
		}
	}

	/**
	 * Gets the api version.
	 *
	 * @return the api version
	 */
	public String getApiVersion() {
		return this.getProperty("api_version");
	}

	/**
	 * Gets the uri of this media
	 *
	 * @return the uri
	 */
	public String getUri() {
		return this.getProperty("uri");
	}

	/**
	 * Delete this Media
	 *
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "DELETE", (Map) null);

		return !response.isError();
	}

}
