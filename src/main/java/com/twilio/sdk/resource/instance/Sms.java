package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Sms.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/sms">https://www.twilio.com/docs/api/rest/sms</a>
 */
public class Sms extends InstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new sms.
	 *
	 * @param client the client
	 */
	public Sms(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new sms.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Sms(TwilioRestClient client, String sid) {
		super(client);
        if (sid == null) {
            throw new IllegalStateException("The Sid for an Sms can not be null");
        }
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new sms.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Sms(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/SMS/Messages/" + this.getSid() + ".json";
	}

	/*
	 * Property getters
	 */
	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return this.getProperty(SID_PROPERTY);
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
	 * Gets the date sent.
	 *
	 * @return the date sent
	 */
	public Date getDateSent() {
		return parseDate(this.getProperty("date_sent"));
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
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return this.getProperty("to");
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return this.getProperty("from");
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return this.getProperty("body");
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.getProperty("status");
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public String getPrice() {
		return this.getProperty("price");
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public String getDirection() {
		return this.getProperty("direction");
	}

	/**
	 * Gets the api version.
	 *
	 * @return the api version
	 */
	public String getApiVersion() {
		return this.getProperty("api_version");
	}

}
