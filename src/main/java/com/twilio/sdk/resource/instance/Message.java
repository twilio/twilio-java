package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.MediaList;

import java.util.Date;
import java.util.Map;

public class Message extends InstanceResource {

	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new message.
	 *
	 * @param client the client
	 */
	public Message(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Message(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for a Message can not be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Message(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
		protected String getResourceLocation() {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Messages/" + this.getSid() + ".json";
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
	 * Returns the a list of media.
	 *
	 * @return the MediaList associated with this message
	 */
	public MediaList getMedia() {
		MediaList media = new MediaList(this.getClient(), this.getSid());
		media.setRequestAccountSid(this.getRequestAccountSid());
		return media;
	}


	/**
	 * Returns the a list of media.
	 *
	 * @param mediaSid the sid of a media instance associated with this message
	 * @return the MediaList associated with this message
	 */
	public Media getMedia(String mediaSid) {
		Media media = new Media(this.getClient(), this.getSid(), mediaSid);
		media.setRequestAccountSid(this.getRequestAccountSid());
		return media;
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
	 * Gets the price.
	 *
	 * @return the price
	 */
	public String getPriceUnit() {
		return this.getProperty("price_unit");
	}

	/**
	 * Gets the number of segments used to deliver this message.
	 *
	 * @return the number of segments
	 */
	public int getNumSegments() {
		return Integer.parseInt(this.getProperty("num_segments"));
	}

	/**
	 * Gets the number of media associated with the message.
	 *
	 * @return the number of segments
	 */
	public int getNumMedia() {
		return Integer.parseInt(this.getProperty("num_media"));
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
