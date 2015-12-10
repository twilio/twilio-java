package com.twilio.sdk.resource.instance.ipmessaging;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenInstanceResource;
import com.twilio.sdk.resource.list.ipmessaging.ChannelList;
import com.twilio.sdk.resource.list.ipmessaging.RoleList;
import com.twilio.sdk.resource.list.ipmessaging.UserList;

/**
 * Represents service for ip messaging
 */
public class Service extends NextGenInstanceResource<TwilioIPMessagingClient> {

	private static final String READ_STATUS_ENABLED_PROPERTY = "read_status_enabled";
	private static final String CONSUMPTION_REPORT_INTERVAL_PROPERTY = "consumption_report_interval";

	public Service(TwilioIPMessagingClient client, String sid) {
		super(client);
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("sid cannot be null");
		}
		setProperty(SID_PROPERTY, sid);
	}

	public Service(TwilioIPMessagingClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * The unique identifier for this service
	 *
	 * @return A sid string
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The account sid that owns this service
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The human readable name for this service
	 *
	 * @return A human readable string
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * Default service role assigned on initial user creation
	 *
	 * @return The default service role
	 */
	public String getDefaultServiceRoleSid() {
		return getProperty("default_service_role_sid");
	}

	/**
	 * Default channel role assigned on channel join
	 *
	 * @return The default channel role
	 */
	public String getDefaultChannelRoleSid() {
		return getProperty("default_channel_role_sid");
	}

	/**
	 * Returns read status enabled flag
	 *
	 * @return The read status
	 */
	public Boolean getReadStatusEnabled() {
		return (Boolean) getObject(READ_STATUS_ENABLED_PROPERTY);
	}

	/**
	 * Timeout after “started typing” event when client should assume that user is not typing anymore even
	 * if no “ended typing” message received
	 *
	 * @return An integer in seconds
	 */
	public Integer getTypingIndicatorTimeout() {
		return (Integer) getObject("typing_indicator_timeout");
	}

	/**
	 * Returns the Consumption Report Interval of the Service
	 *
	 * @return The consumption report interval
	 */
	public Integer getConsumptionReportInterval() {
		return getPropertyAsInteger(CONSUMPTION_REPORT_INTERVAL_PROPERTY);
	}

	/**
	 * Webhooks triggered on given method
	 *
	 * @return Webhooks
	 */
	public Object getWebhooks() {
		return getObject("webhooks");
	}

	/**
	 * Absolute url of this service
	 *
	 * @return The url of this service
	 */
	public String getUrl() {
		return getProperty("url");
	}

	/**
	 * The date when this service was created
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date when this service was updated
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Initialize channels without a filter
	 *
	 * @return Channels endpoint
	 */
	public ChannelList getChannels() {
		return getChannels(null);
	}

	/**
	 * Initialize channels with a filter
	 *
	 * @return Channels endpoint
	 */
	public ChannelList getChannels(Map<String, String> filters) {
		return new ChannelList(getClient(), getSid(), filters);
	}

	/**
	 * Get a single channel
	 *
	 * @param sid The channel sid
	 * @return The channel
	 */
	public Channel getChannel(String sid) {
		return new Channel(getClient(), getSid(), sid);
	}

	/**
	 * Initialize roles without a filter
	 *
	 * @return Roles endpoint
	 */
	public RoleList getRoles() {
		return getRoles(null);
	}

	/**
	 * Initialize roles with a filter
	 *
	 * @param filters The filters to apply
	 * @return Roles endpoint
	 */
	public RoleList getRoles(Map<String, String> filters) {
		return new RoleList(getClient(), getSid(), filters);
	}

	/**
	 * Get a role
	 *
	 * @param sid The role sid
	 * @return The role
	 */
	public Role getRole(String sid) {
		return new Role(getClient(), getSid(), sid);
	}

	/**
	 * Create a role
	 *
	 * @param params The params to provide
	 * @return The role
	 */
	public Role createRole(Map<String, String> params) throws TwilioRestException {
		return getRoles().create(params);
	}

	/**
	 * Create a role
	 *
	 * @param params The params to provide
	 * @return The role
	 */
	public Role createRole(List<NameValuePair> params) throws TwilioRestException {
		return getRoles().create(params);
	}

	/**
	 * Initialize users without a filter
	 *
	 * @return Users endpoint
	 */
	public UserList getUsers() {
		return getUsers(null);
	}

	/**
	 * Get a user
	 *
	 * @param sid The user sid
	 * @return The user instance
	 */
	public User getUser(String sid) {
		return new User(getClient(), getSid(), sid);
	}

	/**
	 * Initialize users with a filter
	 *
	 * @param filters The filters to apply
	 * @return Users endpoint
	 */
	public UserList getUsers(Map<String, String> filters) {
		return new UserList(getClient(), getSid(), filters);
	}

	/**
	 * Create a user
	 *
	 * @param params The params to provide
	 * @return The created user
	 * @throws TwilioRestException thrown when an error occurs
	 */
	public User createUser(Map<String, String> params) throws TwilioRestException {
		return getUsers().create(params);
	}

	/**
	 * Deletes the service
	 *
	 * @return True iff the delete is successful
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = this.getClient()
				.safeRequest(this.getResourceLocation(), "DELETE", (Map) null);
		return !response.isError();
	}

	/**
	 * Create a channel
	 *
	 * @param params The params to create with
	 * @return The created channel
	 * @throws TwilioRestException thrown when an error occurs
	 */
	public Channel createChannel(Map<String, String> params) throws TwilioRestException {
		return getChannels().create(params);
	}

	/**
	 * Create a channel
	 *
	 * @param params The params to create with
	 * @return The created channel
	 * @throws TwilioRestException thrown when an error occurs
	 */
	public Channel createChannel(List<NameValuePair> params) throws TwilioRestException {
		return getChannels().create(params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioIPMessagingClient.DEFAULT_VERSION + "/Services/" + getSid();
	}
}
