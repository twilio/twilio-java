package com.twilio.sdk.resource.instance.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * Represents a single member instance in a channel
 */
public class Member extends NextGenInstanceResource<TwilioIPMessagingClient> {

	private static final String SERVICE_SID_PROPERTY = "service_sid";
	private static final String CHANNEL_SID_PROPERTY = "channel_sid";
	private static final String LAST_CONSUMED_MESSAGE_INDEX_PROPERTY = "last_consumed_message_index";
	private static final String LAST_CONSUMPTION_TIMESTAMP_PROPERTY = "last_consumption_timestamp";

	public Member(TwilioIPMessagingClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	public Member(TwilioIPMessagingClient client,
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
		setProperty(CHANNEL_SID_PROPERTY, channelSid);
		setProperty(SID_PROPERTY, sid);
	}

	/**
	 * The unique identifier for this member
	 *
	 * @return A sid string
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The account sid that owns this member
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The service sid that is associated to this member
	 *
	 * @return The service sid
	 */
	public String getServiceSid() {
		return getProperty(SERVICE_SID_PROPERTY);
	}

	/**
	 * The channel sid that is associated to this member
	 *
	 * @return The channel sid
	 */
	public String getChannelSid() {
		return getProperty(CHANNEL_SID_PROPERTY);
	}

	/**
	 * The identity of this member
	 *
	 * @return The identity
	 */
	public String getIdentity() {
		return getProperty("identity");
	}

	/**
	 * The role sid for this member
	 *
	 * @return The role sid
	 */
	public String getRoleSid() {
		return getProperty("role_sid");
	}

	/**
	 * Returns the last message index consumed by the member
	 *
	 * @return the last consumed message index
	 */
	public Integer getLastConsumedMessageIndex() {
		return getPropertyAsInteger(LAST_CONSUMED_MESSAGE_INDEX_PROPERTY);
	}

	/**
	 * Returns the Member's last message consumption time stamp
	 *
	 * @return the last consumption timestamp
	 */
	public Calendar getLastConsumptionTimestamp() {
		return parseCalendar(getProperty(LAST_CONSUMPTION_TIMESTAMP_PROPERTY));
	}

	/**
	 * The date when this user was created
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date when this user was updated
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

	/**
	 * Deletes the channel
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
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services/" + getServiceSid()
				+ "/Channels/" + getChannelSid()
				+ "/Members/" + getSid();
	}
}
