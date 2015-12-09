package com.twilio.sdk.resource.instance.ipmessaging;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenInstanceResource;
import com.twilio.sdk.resource.list.ipmessaging.MemberList;
import com.twilio.sdk.resource.list.ipmessaging.MessageList;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Represents a single channel resource
 */
public class Channel extends NextGenInstanceResource<TwilioIPMessagingClient> {

	private static final String SERVICE_SID_PROPERTY = "service_sid";
	private static final String UNIQUE_NAME_PROPERTY = "unique_name";

	public Channel(TwilioIPMessagingClient client, String serviceSid, String sid) {
		super(client);

		if (StringUtils.isEmpty(serviceSid)) {
			throw new IllegalArgumentException("serviceSid cannot be null");
		}
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("sid cannot be null");
		}

		setProperty(SERVICE_SID_PROPERTY, serviceSid);
		setProperty(SID_PROPERTY, sid);
	}

	public Channel(TwilioIPMessagingClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * The unique identifier for this channel
	 *
	 * @return A sid string
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The account sid that owns this channel
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The service sid that is associated to this channel
	 *
	 * @return The service sid
	 */
	public String getServiceSid() {
		return getProperty(SERVICE_SID_PROPERTY);
	}

	/**
	 * The human readable name for this channel
	 *
	 * @return A human readable string
	 */
	public String getFriendlyName() {
		return getProperty(FRIENDLY_NAME_PROPERTY);
	}
	
	/**
	 * The human readable unique name for this channel
	 *
	 * @return A human readable string
	 */
	public String getUniqueName() {
		return getProperty(UNIQUE_NAME_PROPERTY);
	}

	/**
	 * The user who created this channel
	 *
	 * @return The user sid
	 */
	public String getCreatedBy() {
		return getProperty("created_by");
	}

	/**
	 * The attributes associated to this channel
	 *
	 * @return The channel attributes
	 */
	public String getAttributes() {
		return getProperty("attributes");
	}

	/**
	 * The type attribute for this channel
	 *
	 * @return The type
	 */
	public String getType() {
		return getProperty("type");
	}

	/**
	 * The date when this channel was created
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date when this channel was updated
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Absolute url of this channel
	 *
	 * @return The url of this channel
	 */
	public String getUrl() {
		return getProperty("url");
	}

	/**
	 * Initialize members without filters
	 *
	 * @return The members endpoint
	 */
	public MemberList getMembers() {
		return getMembers(null);
	}

	/**
	 * Get a single member
	 *
	 * @param sid The member sid
	 * @return The fetched member
	 */
	public Member getMember(String sid) {
		return new Member(getClient(), getServiceSid(), getSid(), sid);
	}

	/**
	 * Create a single member
	 *
	 * @param params The params to provide
	 * @return The created member
	 * @throws TwilioRestException
	 */
	public Member createMember(Map<String, String> params) throws TwilioRestException {
		return getMembers().create(params);
	}

	/**
	 * Initialize members with filters
	 *
	 * @param filters The filters to apply
	 * @return The members endpoint
	 */
	public MemberList getMembers(Map<String, String> filters) {
		return new MemberList(getClient(), getServiceSid(), getSid(), filters);
	}

	/**
	 * Initialize messages without filters
	 *
	 * @return The messages endpoint
	 */
	public MessageList getMessages() {
		return getMessages(null);
	}

	/**
	 * Initialize messages with filters
	 *
	 * @param filters The filters to apply
	 * @return The messages endpoint
	 */
	public MessageList getMessages(Map<String, String> filters) {
		return new MessageList(getClient(), getServiceSid(), getSid(), filters);
	}

	/**
	 * Fetch a single message
	 *
	 * @param sid The message sid
	 * @return The fetched message
	 */
	public Message getMessage(String sid) {
		return new Message(getClient(), getServiceSid(), getSid(), sid);
	}

	/**
	 * Create a message
	 *
	 * @param params The params to provide
	 * @return The created message
	 */
	public Message createMessage(Map<String, String> params) throws TwilioRestException {
		return getMessages().create(params);
	}

	/**
	 * Create a message
	 *
	 * @param params The params to provide
	 * @return The created message
	 */
	public Message createMessage(List<NameValuePair> params) throws TwilioRestException {
		return getMessages().create(params);
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
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services/" + getServiceSid() + "/Channels/" + getSid();
	}
}
