package com.twilio.sdk.resource.instance.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * Represents a single message in ip messaging
 */
public class Message extends NextGenInstanceResource<TwilioIPMessagingClient> {

	private static final String SERVICE_SID_PROPERTY = "service_sid";
	private static final String INDEX_PROPERTY = "index";

	private String channelSid;

	public Message(TwilioIPMessagingClient client,
				   Map<String, Object> properties) {
		super(client, properties);
	}

	public Message(TwilioIPMessagingClient client,
				   String serviceSid,
				   String channelSid,
				   String sid) {
		super(client);

		if (StringUtils.isEmpty(serviceSid)) {
			throw new IllegalArgumentException("serviceSid cannot be null");
		}
		if (StringUtils.isEmpty(channelSid)) {
			throw new IllegalArgumentException("channelSid cannot be null");
		}
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("sid cannot be null");
		}

		setProperty(SERVICE_SID_PROPERTY, serviceSid);
		this.channelSid = channelSid;
		setProperty(SID_PROPERTY, sid);
	}

	/**
	 * The unique identifier for this message
	 *
	 * @return A sid string
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The account sid that created this message
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The service sid that is associated to this message
	 *
	 * @return The service sid
	 */
	public String getServiceSid() {
		return getProperty(SERVICE_SID_PROPERTY);
	}

	/**
	 * The destination of this message
	 *
	 * @return The message destination
	 */
	public String getTo() {
		return getProperty("to");
	}

	/**
	 * The origination of this message
	 *
	 * @return The message origination
	 */
	public String getFrom() {
		return getProperty("from");
	}

	/**
	 * True iff the message was ever edited
	 *
	 * @return A boolean value
	 */
	public Boolean isWasEdited() {
		return (Boolean) getObject("was_edited");
	}

	/**
	 * The body of the message
	 *
	 * @return The body of the message
	 */
	public String getBody() {
		return getProperty("body");
	}

	/**
	 * Returns the index of this message in the Channel
	 *
	 * @return the index
	 */
	public Integer getIndex() {
		return getPropertyAsInteger(INDEX_PROPERTY);
	}

	/**
	 * The date when this message was created
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date when this message was updated
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Absolute url of this user
	 *
	 * @return The url of this user
	 */
	public String getUrl() {
		return getProperty("url");
	}

	private String getChannelSid() {
		return this.channelSid == null ? this.getTo() : this.channelSid;
	}

	/**
	 * Deletes the message
	 *
	 * @return True iff the delete is successful
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = this.getClient()
				.safeRequest(this.getResourceLocation(), "DELETE", (Map) null);
		return !response.isError();
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION
				+ "/Services/" + getServiceSid()
				+ "/Channels/" + getChannelSid()
				+ "/Messages/" + getSid();
	}
}
