package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.factory.AddressFactory;
import com.twilio.sdk.resource.factory.ApplicationFactory;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.IncomingPhoneNumberFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.factory.OutgoingCallerIdFactory;
import com.twilio.sdk.resource.factory.QueueFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.factory.UsageTriggerFactory;
import com.twilio.sdk.resource.factory.sip.CredentialListFactory;
import com.twilio.sdk.resource.factory.sip.DomainFactory;
import com.twilio.sdk.resource.factory.sip.IpAccessControlListFactory;
import com.twilio.sdk.resource.instance.sip.CredentialListInstance;
import com.twilio.sdk.resource.instance.sip.Domain;
import com.twilio.sdk.resource.instance.sip.IpAccessControlList;
import com.twilio.sdk.resource.list.AddressList;
import com.twilio.sdk.resource.list.ApplicationList;
import com.twilio.sdk.resource.list.AuthorizedConnectAppList;
import com.twilio.sdk.resource.list.AvailablePhoneNumberList;
import com.twilio.sdk.resource.list.CallList;
import com.twilio.sdk.resource.list.ConferenceList;
import com.twilio.sdk.resource.list.ConnectAppList;
import com.twilio.sdk.resource.list.IncomingPhoneNumberList;
import com.twilio.sdk.resource.list.MediaList;
import com.twilio.sdk.resource.list.MessageList;
import com.twilio.sdk.resource.list.NotificationList;
import com.twilio.sdk.resource.list.OutgoingCallerIdList;
import com.twilio.sdk.resource.list.QueueList;
import com.twilio.sdk.resource.list.RecordingList;
import com.twilio.sdk.resource.list.ShortCodeList;
import com.twilio.sdk.resource.list.SmsList;
import com.twilio.sdk.resource.list.TranscriptionList;
import com.twilio.sdk.resource.list.TokenList;
import com.twilio.sdk.resource.list.UsageRecordList;
import com.twilio.sdk.resource.list.UsageTriggerList;
import com.twilio.sdk.resource.list.sip.CredentialListList;
import com.twilio.sdk.resource.list.sip.DomainList;
import com.twilio.sdk.resource.list.sip.IpAccessControlListList;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * The Class Account.
 * <p/>
 * For more information see <a href="https://www.twilio.com/docs/api/rest/account"
 * >https://www.twilio.com/docs/api/rest/account</a>
 */
public class Account extends InstanceResource<TwilioRestClient> {

	/** The Constant STATUS_PROPERTY. */
	private static final String STATUS_PROPERTY = "status";

	/** The Constant TYPE_PROPERTY. */
	private static final String TYPE_PROPERTY = "type";

	/** The Constant FRIENDLY_NAME_PROPERTY. */
	private static final String FRIENDLY_NAME_PROPERTY = "friendly_name";

	/** The Constant AUTH_TOKEN_PROPERTY. */
	private static final String AUTH_TOKEN_PROPERTY = "auth_token";

	/**
	 * Instantiates a new account.
	 *
	 * @param client the client
	 */
	public Account(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new account.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Account(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);

		Object ac = properties.get(SID_PROPERTY);
		if (ac != null && ac instanceof String) {
			String accountSid = (String) ac;
			this.setRequestAccountSid(accountSid);
		}

	}

	/*
	 * Account Methods
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
	 * Sets the sid.
	 *
	 * @param accountSid the new sid
	 */
	public void setSid(String accountSid) {
		this.setRequestAccountSid(accountSid);
		this.setProperty(SID_PROPERTY, accountSid);
	}

	/**
	 * Gets the auth token.
	 *
	 * @return the auth token
	 */
	public String getAuthToken() {
		return this.getProperty(AUTH_TOKEN_PROPERTY);
	}

	/**
	 * Sets the auth token.
	 *
	 * @param authToken the new auth token
	 */
	public void setAuthToken(String authToken) {
		this.setProperty(AUTH_TOKEN_PROPERTY, authToken);
	}

	/**
	 * Gets the friendly name.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return this.getProperty(FRIENDLY_NAME_PROPERTY);
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.getProperty(STATUS_PROPERTY);
	}

	/**
	 * Gets the account type
	 *
	 * @return the account type
	 */
	public String getType() {
		return this.getProperty(TYPE_PROPERTY);
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return getDateProperty(DATE_CREATED_PROPERTY);
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return getDateProperty(DATE_UPDATED_PROPERTY);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
   */
	@Override
	protected String getResourceLocation() {
		return getResourceLocation(".json");
	}

	private String getResourceLocation(String extension) {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + this.getRequestAccountSid() + extension;
	}

	/*
	 * Subresource methods
   */

	/**
	 * Gets the calls list resource without any filters.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/call">https://www.twilio.com/docs/api/rest/call</a>
	 *
	 * @return the calls
	 */
	public CallList getCalls() {
		return this.getCalls(new HashMap<String, String>());
	}

	/**
	 * Gets the calls list resource with the given filters.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/call">https://www.twilio.com/docs/api/rest/call</a>
	 *
	 * @param filters the filters
	 * @return the calls
	 */
	public CallList getCalls(Map<String, String> filters) {
		CallList calls = new CallList(this.getClient(), filters);
		calls.setRequestAccountSid(this.getRequestAccountSid());

		return calls;
	}

	/**
	 * Get a given call instance by sid
	 *
	 * @param sid The 34 character sid starting with CA
	 */
	public Call getCall(String sid) {
		Call call = new Call(this.getClient(), sid);
		call.setRequestAccountSid(this.getRequestAccountSid());
		return call;
	}

	/**
	 * Gets the call factory, which lets you make outgoing calls.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/making_calls">https://www.twilio.com/docs/api/rest/making_calls</a>
	 *
	 * @return the call factory
	 */
	public CallFactory getCallFactory() {
		return this.getCalls();
	}

	/**
	 * Gets the sms message list.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sms">https://www.twilio.com/docs/api/rest/sms</a>
	 *
	 * @return the sms messages
	 */
	public SmsList getSmsMessages() {
		return this.getSmsMessages(new HashMap<String, String>());
	}

	/**
	 * Gets the sms messages list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sms">https://www.twilio.com/docs/api/rest/sms</a>
	 *
	 * @param filters the filters
	 * @return the sms messages
	 */
	public SmsList getSmsMessages(Map<String, String> filters) {
		SmsList sms = new SmsList(this.getClient(), filters);
		sms.setRequestAccountSid(this.getRequestAccountSid());

		return sms;
	}

	/**
	 * Get a given sms instance by sid
	 *
	 * @param sid The 34 character sid starting with SM
	 */
	public Sms getSms(String sid) {
		Sms sms = new Sms(this.getClient(), sid);
		sms.setRequestAccountSid(this.getRequestAccountSid());
		return sms;
	}

	/**
	 * Gets the sms factory which lets you send sms messages
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sending-sms">https://www.twilio.com/docs/api/rest/sending-sms</a>
	 *
	 * @return the sms factory
	 */
	public SmsFactory getSmsFactory() {
		return this.getSmsMessages();
	}

	/**
	 * Gets the message list.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/messages">https://www.twilio.com/docs/api/rest/messages</a>
	 *
	 * @return the messages
	 */
	public MessageList getMessages() {
		return this.getMessages(new HashMap<String, String>());
	}

	/**
	 * Gets the messages list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/messages">https://www.twilio.com/docs/api/rest/messages</a>
	 *
	 * @param filters the filters
	 * @return the messages
	 */
	public MessageList getMessages(Map<String, String> filters) {
		MessageList messages = new MessageList(this.getClient(), filters);
		messages.setRequestAccountSid(this.getRequestAccountSid());

		return messages;
	}

	/**
	 * Gets the message factory which lets you send messages
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sending-messages">https://www.twilio.com/docs/api/rest/sending-messages</a>
	 *
	 * @return the message factory
	 */
	public MessageFactory getMessageFactory() {
		return this.getMessages();
	}

	/**
	 * Get a given message instance by sid
	 *
	 * @param sid The 34 character sid starting with MM or SM
	 */
	public Message getMessage(String sid) {
		Message message = new Message(this.getClient(), sid);
		message.setRequestAccountSid(this.getRequestAccountSid());
		return message;
	}

	/**
	 * Gets the media list.
	 *
	 * @return the media list
	 */
	public MediaList getMedia() {
		return this.getMedia(new HashMap<String, String>());
	}

	/**
	 * Gets the media list with the given filters.
	 *
	 * @return the media list
	 */
	public MediaList getMedia(Map<String, String> filters) {
		MediaList mediaList = new MediaList(this.getClient(), filters);
		mediaList.setRequestAccountSid(this.getRequestAccountSid());

		return mediaList;
	}


	/**
	 * Gets the media instance for this sid.
	 *
	 * @return the media
	 */
	public Media getMedia(String sid) {
		Media media = new Media(this.getClient(), sid);
		media.setRequestAccountSid(this.getRequestAccountSid());
		return media;
	}

	/**
	 * Gets the application list
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/applications">https://www.twilio.com/docs/api/rest/applications</a>
	 *
	 * @return the applications
	 */
	public ApplicationList getApplications() {
		return this.getApplications(new HashMap<String, String>());
	}

	/**
	 * Gets the application list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/applications">https://www.twilio.com/docs/api/rest/applications</a>
	 *
	 * @param filters the filters
	 * @return the applications
	 */
	public ApplicationList getApplications(Map<String, String> filters) {
		ApplicationList list = new ApplicationList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given application instance by sid
	 *
	 * @param sid The 34 character sid starting with AP
	 */
	public Application getApplication(String sid) {
		Application app = new Application(this.getClient(), sid);
		app.setRequestAccountSid(this.getRequestAccountSid());
		return app;
	}

	/**
	 * Gets the application factory which lets you create new applications
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/applications#list-post">https://www.twilio.com/docs/api/rest/applications#list-post</a>
	 *
	 * @return the application factory
	 */
	public ApplicationFactory getApplicationFactory() {
		return this.getApplications();
	}

	/**
	 * Gets the available phone numbers. Defaults to US/Local
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/available-phone-numbers">https://www.twilio.com/docs/api/rest/available-phone-numbers</a>
	 *
	 * @return the available phone numbers
	 */
	public AvailablePhoneNumberList getAvailablePhoneNumbers() {
		return this.getAvailablePhoneNumbers(new HashMap<String, String>());
	}

	/**
	 * Gets the available phone numbers. With the given search filters. Defaults to US/Local numbers
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/available-phone-numbers">https://www.twilio.com/docs/api/rest/available-phone-numbers</a>
	 *
	 * @param filters the filters
	 * @return the available phone numbers
	 */
	public AvailablePhoneNumberList getAvailablePhoneNumbers(Map<String, String> filters) {
		AvailablePhoneNumberList list = new AvailablePhoneNumberList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Gets the available phone numbers with the given iso country and type
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/available-phone-numbers">https://www.twilio.com/docs/api/rest/available-phone-numbers</a>
	 *
	 * @param filters the filters
	 * @param isoCountry the Iso Country code you are searching in
	 * @param type the type of phone number. Possible values are AvailablePhoneNumber.TYPE_LOCAL or
	 * AvailablePhoneNumber.TYPE_TOLLFREE
	 * @return the available phone numbers
	 */
	public AvailablePhoneNumberList getAvailablePhoneNumbers(Map<String, String> filters, String isoCountry,
	                                                         String type) {
		AvailablePhoneNumberList list = new AvailablePhoneNumberList(this.getClient(), filters, isoCountry, type);
		list.setRequestAccountSid(this.getRequestAccountSid());

		return list;
	}

	/**
	 * Gets the conference list
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/conference">https://www.twilio.com/docs/api/rest/conference</a>
	 *
	 * @return the conferences
	 */
	public ConferenceList getConferences() {
		return this.getConferences(new HashMap<String, String>());
	}

	/**
	 * Gets the conferences list with the given filters
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/conference">https://www.twilio.com/docs/api/rest/conference</a>
	 *
	 * @param filters the filters
	 * @return the conferences
	 */
	public ConferenceList getConferences(Map<String, String> filters) {
		ConferenceList list = new ConferenceList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given conference instance by sid
	 *
	 * @param sid The 34 character sid starting with CF
	 */
	public Conference getConference(String sid) {
		Conference conf = new Conference(this.getClient(), sid);
		conf.setRequestAccountSid(this.getRequestAccountSid());
		return conf;
	}

	/**
	 * Gets the queue list
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/queue">https://www.twilio.com/docs/api/rest/queue</a>
	 *
	 * @return the {@link QueueList}
	 */
	public QueueList getQueues() {
		QueueList list = new QueueList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given queue by sid
	 *
	 * @param sid The Sid starting with QU
	 * @return the queue object
	 */
	public Queue getQueue(String sid) {
		Queue queue = new Queue(this.getClient(), sid);
		queue.setRequestAccountSid(this.getRequestAccountSid());
		return queue;
	}

	/**
	 * Get the queue factory
	 */
	public QueueFactory getQueueFactory() {
		return this.getQueues();
	}

	/**
	 * Gets the incoming phone numbers list
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/incoming-phone-numbers">https://www.twilio.com/docs/api/rest/incoming-phone-numbers</a>
	 *
	 * @return the incoming phone numbers
	 */
	public IncomingPhoneNumberList getIncomingPhoneNumbers() {
		return this.getIncomingPhoneNumbers(new HashMap<String, String>());
	}

	/**
	 * Gets the incoming phone numbers list with the given filters
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/incoming-phone-numbers">https://www.twilio.com/docs/api/rest/incoming-phone-numbers</a>
	 *
	 * @param filters the filters
	 * @return the incoming phone numbers
	 */
	public IncomingPhoneNumberList getIncomingPhoneNumbers(Map<String, String> filters) {
		IncomingPhoneNumberList list = new IncomingPhoneNumberList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given incoming phone number instance by sid
	 *
	 * @param sid The 34 character sid starting with PN
	 */
	public IncomingPhoneNumber getIncomingPhoneNumber(String sid) {
		IncomingPhoneNumber pn = new IncomingPhoneNumber(this.getClient(), sid);
		pn.setRequestAccountSid(this.getRequestAccountSid());
		return pn;
	}

	/**
	 * Gets the incoming phone number factory.
	 * <p/>
	 * See: <a href="https://www.twilio.com/docs/api/rest/incoming-phone-numbers#list-post">https://www.twilio.com/docs/api/rest/incoming-phone-numbers#list-post</a>
	 *
	 * @return the incoming phone number factory
	 */
	public IncomingPhoneNumberFactory getIncomingPhoneNumberFactory() {
		return this.getIncomingPhoneNumbers();
	}

	public AddressList getAddresses(Map<String, String> filters) {
		AddressList list = new AddressList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	public AddressList getAddresses() {
		return this.getAddresses(new HashMap<String, String>());
	}

	public AddressFactory getAddressFactory() {
		return this.getAddresses();
	}

	public Address getAddress(String sid) {
		Address address = new Address(this.getClient(), sid);
		address.setRequestAccountSid(this.getRequestAccountSid());
		return address;
	}

	/**
	 * Gets the shortcode list.
	 *
	 * @return the short code list
	 */
	public ShortCodeList getShortCodes() {
		return this.getShortCodes(new HashMap<String, String>());
	}

	/**
	 * Gets the short code list with filters
	 *
	 * @param filters the filters
	 * @return the short code list
	 */
	public ShortCodeList getShortCodes(Map<String, String> filters) {
		ShortCodeList list = new ShortCodeList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given short code instance by sid
	 *
	 * @param sid The 34 character sid starting with SC
	 */
	public ShortCode getShortCode(String sid) {
		ShortCode sc = new ShortCode(this.getClient(), sid);
		sc.setRequestAccountSid(this.getRequestAccountSid());
		return sc;
	}

	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public NotificationList getNotifications() {
		return this.getNotifications(new HashMap<String, String>());
	}

	/**
	 * Gets the notifications.
	 *
	 * @param filters the filters
	 * @return the notifications
	 */
	public NotificationList getNotifications(Map<String, String> filters) {
		NotificationList list = new NotificationList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given notification instance by sid
	 *
	 * @param sid The 34 character sid starting with NO
	 */
	public Notification getNotification(String sid) {
		Notification n = new Notification(this.getClient(), sid);
		n.setRequestAccountSid(this.getRequestAccountSid());
		return n;
	}


	/**
	 * Gets the outgoing caller ids.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/outgoing-caller-ids">https://www.twilio.com/docs/api/rest/outgoing-caller-ids</a>
	 *
	 * @return the outgoing caller ids
	 */
	public OutgoingCallerIdList getOutgoingCallerIds() {
		return this.getOutgoingCallerIds(new HashMap<String, String>());
	}

	/**
	 * Gets the outgoing caller ids.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/outgoing-caller-ids">https://www.twilio.com/docs/api/rest/outgoing-caller-ids</a>
	 *
	 * @param filters the filters
	 * @return the outgoing caller ids
	 */
	public OutgoingCallerIdList getOutgoingCallerIds(Map<String, String> filters) {
		OutgoingCallerIdList list = new OutgoingCallerIdList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given outgoing caller id instance by sid
	 *
	 * @param sid The 34 character sid starting with PN
	 */
	public OutgoingCallerId getOutgoingCallerId(String sid) {
		OutgoingCallerId number = new OutgoingCallerId(this.getClient(), sid);
		number.setRequestAccountSid(this.getRequestAccountSid());
		return number;
	}

	/**
	 * Gets the outgoing caller id factory which lets you create outgoing caller ids
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/outgoing-caller-ids">https://www.twilio.com/docs/api/rest/outgoing-caller-ids</a>
	 *
	 * @return the outgoing caller id factory
	 */
	public OutgoingCallerIdFactory getOutgoingCallerIdFactory() {
		return this.getOutgoingCallerIds();
	}

	/**
	 * Gets the recordings list
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/recording">https://www.twilio.com/docs/api/rest/recording</a>
	 *
	 * @return the recordings
	 */
	public RecordingList getRecordings() {
		return this.getRecordings(new HashMap<String, String>());
	}

	/**
	 * Gets the recordings list
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/recording">https://www.twilio.com/docs/api/rest/recording</a>
	 *
	 * @param filters the filters
	 * @return the recordings
	 */
	public RecordingList getRecordings(Map<String, String> filters) {
		RecordingList list = new RecordingList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given recording instance by sid
	 *
	 * @param sid The 34 character sid starting with RE
	 */
	public Recording getRecording(String sid) {
		Recording r = new Recording(this.getClient(), sid);
		r.setRequestAccountSid(this.getRequestAccountSid());
		return r;
	}

	/**
	 * Gets the transcriptions list
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/transcription">https://www.twilio.com/docs/api/rest/transcription</a>
	 *
	 * @return the transcriptions
	 */
	public TranscriptionList getTranscriptions() {
		return this.getTranscriptions(new HashMap<String, String>());
	}

	/**
	 * Gets the transcriptions list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/transcription">https://www.twilio.com/docs/api/rest/transcription</a>
	 *
	 * @param filters the filters
	 * @return the transcriptions
	 */
	public TranscriptionList getTranscriptions(Map<String, String> filters) {
		TranscriptionList list = new TranscriptionList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given transcription instance by sid
	 *
	 * @param sid The 34 character sid starting with TR
	 */
	public Transcription getTranscription(String sid) {
		Transcription tr = new Transcription(this.getClient(), sid);
		tr.setRequestAccountSid(this.getRequestAccountSid());
		return tr;
	}

	/**
	 * Gets the Usage Record list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/usage-records">https://www.twilio.com/docs/api/rest/usage-records</a>
	 *
	 * @param filters the filters
	 * @return the usage records
	 */
	public UsageRecordList getUsageRecords(Map<String, String> filters) {
		UsageRecordList list = new UsageRecordList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Gets the Usage Record list
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/usage-records">https://www.twilio.com/docs/api/rest/usage-records</a>
	 *
	 * @return the usage records
	 */
	public UsageRecordList getUsageRecords() {
		return this.getUsageRecords(new HashMap<String, String>());
	}

	/**
	 * Get a specific Usage Trigger
	 */
	public UsageTrigger getUsageTrigger(String sid) {
		UsageTrigger trigger = new UsageTrigger(this.getClient(), sid);
		trigger.setRequestAccountSid(this.getRequestAccountSid());
		return trigger;
	}

	/**
	 * Gets the Usage Trigger factory which lets you create usage triggers
	 *
	 * @return the usage trigger factory
	 */
	public UsageTriggerFactory getUsageTriggerFactory() {
		return this.getUsageTriggers();
	}

	/**
	 * Gets the Usage Triggers list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/usage-triggers">https://www.twilio.com/docs/api/rest/usage-triggers</a>
	 *
	 * @param filters the filters
	 * @return the usage records
	 */
	public UsageTriggerList getUsageTriggers(Map<String, String> filters) {
		UsageTriggerList list = new UsageTriggerList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Gets the Usage Triggers list
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/usage-triggers">https://www.twilio.com/docs/api/rest/usage-triggers</a>
	 *
	 * @return the usage triggers
	 */
	public UsageTriggerList getUsageTriggers() {
		return this.getUsageTriggers(new HashMap<String, String>());
	}

	/**
	 * Gets the connect app list
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/connect-apps">https://www.twilio.com/docs/api/rest/connect-apps</a>
	 *
	 * @return the connect app list
	 */
	public ConnectAppList getConnectApps() {
		return this.getConnectApps(new HashMap<String, String>());
	}

	/**
	 * Gets the connect app list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/connect-apps">https://www.twilio.com/docs/api/rest/connect-apps</a>
	 *
	 * @param filters the filters
	 * @return the connect app list
	 */
	public ConnectAppList getConnectApps(Map<String, String> filters) {
		ConnectAppList list = new ConnectAppList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given connect app instance by sid
	 *
	 * @param sid The 34 character sid starting with CN
	 * @return the connect app
	 */
	public ConnectApp getConnectApp(String sid) {
		ConnectApp cn = new ConnectApp(this.getClient(), sid);
		cn.setRequestAccountSid(this.getRequestAccountSid());
		return cn;
	}

	/**
	 * Gets the authorized connect app list
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/authorized-connect-apps">https://www.twilio.com/docs/api/rest/authorized-connect-apps</a>
	 *
	 * @return the connect app list
	 */
	public AuthorizedConnectAppList getAuthorizedConnectApps() {
		return this.getAuthorizedConnectApps(new HashMap<String, String>());
	}

	/**
	 * Gets the authorized connect app list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/authorized-connect-apps">https://www.twilio.com/docs/api/rest/authorized-connect-apps</a>
	 *
	 * @param filters the filters
	 * @return the connect app list
	 */
	public AuthorizedConnectAppList getAuthorizedConnectApps(Map<String, String> filters) {
		AuthorizedConnectAppList list = new AuthorizedConnectAppList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given connect app instance by sid
	 *
	 * @param sid The 34 character sid starting with CN
	 * @return the connect app
	 */
	public AuthorizedConnectApp getAuthorizedConnectApp(String sid) {
		AuthorizedConnectApp cn = new AuthorizedConnectApp(this.getClient(), sid);
		cn.setRequestAccountSid(this.getRequestAccountSid());
		return cn;
	}

	/**
	 * Gets the sip domains list.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sip-domains">https://www.twilio.com/docs/api/rest/sip-domains</a>
	 *
	 * @return the sip domain
	 */
	public DomainList getDomains() {
		return this.getDomains(new HashMap<String, String>());
	}

	/**
	 * Gets the domain list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sip-domains">https://www.twilio.com/docs/api/rest/sip-domains</a>
	 *
	 * @param filters the filters
	 * @return the sip domain list
	 */
	public DomainList getDomains(Map<String, String> filters) {
		DomainList list = new DomainList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given sip domain instance by sid
	 *
	 * @param sid The 34 character sid starting with SD
	 * @return the sip domain
	 */
	public Domain getDomain(String sid) {
		Domain domain = new Domain(this.getClient(), sid);
		domain.setRequestAccountSid(this.getRequestAccountSid());
		return domain;
	}

	/**
	 * Gets a DomainFactory
	 *
	 * @return a DomainFactory that lets you create new Domains
	 */
	public DomainFactory getDomainFactory() {
		return this.getDomains();
	}

	/**
	 * Gets the sip IpAccessControlLists list.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sip-ip-access-control-lists">https://www.twilio.com/docs/api/rest/sip-ip-access-control-lists</a>
	 *
	 * @return the sip IpAccessControlList
	 */
	public IpAccessControlListList getIpAccessControlLists() {
		return this.getIpAccessControlLists(new HashMap<String, String>());
	}

	/**
	 * Gets the IpAccessControlList list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sip-ip-access-control-lists">https://www.twilio.com/docs/api/rest/sip-ip-access-control-lists</a>
	 *
	 * @param filters the filters
	 * @return the sip IpAccessControlList list
	 */
	public IpAccessControlListList getIpAccessControlLists(Map<String, String> filters) {
		IpAccessControlListList list = new IpAccessControlListList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given IpAccessControlList instance by sid
	 *
	 * @param sid The 34 character sid starting with AL
	 * @return the sip IpAccessControlList
	 */
	public IpAccessControlList getIpAccessControlList(String sid) {
		IpAccessControlList ipAccessControlList = new IpAccessControlList(this.getClient(), sid);
		ipAccessControlList.setRequestAccountSid(this.getRequestAccountSid());
		return ipAccessControlList;
	}

	/**
	 * Gets an IpAccessControlListFactory
	 *
	 * @return an IpAccessControlListFactory that lets you create new IpAccessControlLists
	 */
	public IpAccessControlListFactory getIpAccessControlListFactory() {
		return this.getIpAccessControlLists();
	}

	/**
	 * Gets the sip CredentialLists list.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sip-credential-lists">https://www.twilio.com/docs/api/rest/sip-credential-lists</a>
	 *
	 * @return the sip CredentialList
	 */
	public CredentialListList getCredentialLists() {
		return this.getCredentialLists(new HashMap<String, String>());
	}

	/**
	 * Gets the CredentialList list with the given filters
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sip-credential-lists">https://www.twilio.com/docs/api/rest/sip-credential-lists</a>
	 *
	 * @param filters the filters
	 * @return the sip CredentialList list
	 */
	public CredentialListList getCredentialLists(Map<String, String> filters) {
		CredentialListList list = new CredentialListList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given CredentialList instance by sid
	 *
	 * @param sid The 34 character sid starting with AL
	 * @return the sip CredentialList
	 */
	public CredentialListInstance getCredentialList(String sid) {
		CredentialListInstance credentialList = new CredentialListInstance(this.getClient(), sid);
		credentialList.setRequestAccountSid(this.getRequestAccountSid());
		return credentialList;
	}

	/**
	 * Gets a CredentialListFactory
	 *
	 * @return the CredentialListFactory that lets you make new CredentialLists
	 */
	public CredentialListFactory getCredentialListFactory() {
		return this.getCredentialLists();
	}

	/**
	 * Get the developer sandbox
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/sandbox">https://www.twilio.com/docs/api/rest/sandbox</a>
	 *
	 * @return the sandbox
	 */
	public Sandbox getSandbox() {
		Sandbox sb = new Sandbox(this.getClient());
		sb.setRequestAccountSid(this.getRequestAccountSid());
		return sb;
	}

	/**
	 * Close this subaccount. This will release all phone numbers assigned to it and shut it down completely. You will
	 * still have access to historical data for that subaccount, but you cannot reopen a closed account.
	 *
	 * @return true, if successful
	 * @throws TwilioRestException if there is an error in the request
	 */
	public boolean close() throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Status", "closed");
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);

		return !response.isError();
	}

	/**
	 * Creates a call feedback summary for this account's calls.
	 *
	 * @param filters the filters
	 * @return the call feedback summary
	 * @throws TwilioRestException the twilio rest exception
	 */
	public FeedbackSummary createFeedbackSummary(final Map<String, String> filters) throws TwilioRestException {
		TwilioRestResponse response = getClient()
				.safeRequest(getResourceLocation("") + "/Calls/FeedbackSummary.json", "POST", filters);
		return new FeedbackSummary(getClient(), response.toMap());
	}

	/**
	 * Deletes a call feedback summary for this account's calls.
	 *
	 * @param sid the summary sid
	 * @throws TwilioRestException the twilio rest exception
	 */
	public void deleteFeedbackSummary(final String sid) throws TwilioRestException {
		getClient()
				.safeRequest(getResourceLocation("") + "/Calls/FeedbackSummary/" + sid + ".json", "DELETE", (Map) null);
	}

	/**
	 * Gets a call feedback summary for this account's calls.
	 *
	 * @param sid the summary sid
	 * @return the call feedback summary
	 * @throws TwilioRestException the twilio rest exception
	 */
	public FeedbackSummary getFeedbackSummary(final String sid) throws TwilioRestException {
		TwilioRestResponse response = getClient()
				.safeRequest(getResourceLocation("") + "/Calls/FeedbackSummary/" + sid + ".json", "GET", (Map) null);
		return new FeedbackSummary(getClient(), response.toMap());
	}

	/**
	 * Gets the factory for creating new tokens.
	 * <p/>
	 * <a href="https://www.twilio.com/docs/api/rest/tokens">https://www.twilio.com/docs/api/rest/tokens</a>
	 *
	 * @param filters the filters
	 * @return the token factory
	 */
	public TokenList getTokenFactory() {
		TokenList tokens = new TokenList(this.getClient());
		tokens.setRequestAccountSid(this.getRequestAccountSid());
		return tokens;
	}
}
