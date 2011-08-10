package com.twilio.sdk.resource.instance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.factory.ApplicationFactory;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.IncomingPhoneNumberFactory;
import com.twilio.sdk.resource.factory.OutgoingCallerIdFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.list.ApplicationList;
import com.twilio.sdk.resource.list.AvailablePhoneNumberList;
import com.twilio.sdk.resource.list.CallList;
import com.twilio.sdk.resource.list.ConferenceList;
import com.twilio.sdk.resource.list.IncomingPhoneNumberList;
import com.twilio.sdk.resource.list.NotificationList;
import com.twilio.sdk.resource.list.OutgoingCallerIdList;
import com.twilio.sdk.resource.list.RecordingList;
import com.twilio.sdk.resource.list.ShortCodeList;
import com.twilio.sdk.resource.list.SmsList;
import com.twilio.sdk.resource.list.TranscriptionList;

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 * 
 * For more information see {@see <a
 * href="http://www.twilio.com/docs/api/rest/account"
 * >http://www.twilio.com/docs/api/rest/account}
 */
public class Account extends InstanceResource {

	/** The Constant DATE_UPDATED_PROPERTY. */
	private static final String DATE_UPDATED_PROPERTY = "date_updated";

	/** The Constant DATE_CREATED_PROPERTY. */
	private static final String DATE_CREATED_PROPERTY = "date_created";

	/** The Constant STATUS_PROPERTY. */
	private static final String STATUS_PROPERTY = "status";

	/** The Constant FRIENDLY_NAME_PROPERTY. */
	private static final String FRIENDLY_NAME_PROPERTY = "friendly_name";

	/** The Constant ACCOUNT_SID_PROPERTY. */
	private static final String ACCOUNT_SID_PROPERTY = "sid";

	/** The Constant AUTH_TOKEN_PROPERTY. */
	private static final String AUTH_TOKEN_PROPERTY = "auth_token";

	/**
	 * Instantiates a new account.
	 * 
	 * @param client
	 *            the client
	 */
	public Account(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new account.
	 * 
	 * @param client
	 *            the client
	 * @param properties
	 *            the properties
	 */
	public Account(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);

		Object ac = properties.get(ACCOUNT_SID_PROPERTY);
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
		if (this.getRequestAccountSid() != null) {
			return this.getRequestAccountSid();
		}

		return this.getProperty(ACCOUNT_SID_PROPERTY);
	}

	/**
	 * Sets the sid.
	 * 
	 * @param accountSid
	 *            the new sid
	 */
	public void setSid(String accountSid) {
		this.setRequestAccountSid(accountSid);
		this.setProperty(ACCOUNT_SID_PROPERTY, accountSid);
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
	 * @param authToken
	 *            the new auth token
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
	 * Gets the date created.
	 * 
	 * @return the date created
	 */
	public Date getDateCreated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty(DATE_CREATED_PROPERTY));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Gets the date updated.
	 * 
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty(DATE_UPDATED_PROPERTY));
		} catch (ParseException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + ".json";
	}

	/*
	 * Subresource methods
	 */
	/**
	 * Gets the calls list resource without any filters.
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/call">http://www.twilio.com/docs/api/rest/call</a>}
	 * 
	 * @return the calls
	 */
	public CallList getCalls() {
		return this.getCalls(new HashMap<String, String>());
	}

	/**
	 * Gets the calls list resource with the given filters.
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/call">http://www.twilio.com/docs/api/rest/call</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the calls
	 */
	public CallList getCalls(Map<String, String> filters) {
		CallList calls = new CallList(this.getClient(), filters);
		calls.setRequestAccountSid(this.getRequestAccountSid());

		return calls;
	}
	
	/**
	 * Get a given call instance by sid
	 * @param sid The 34 character sid starting with CA
	 * @return
	 */
	public Call getCall(String sid) {
		Call call = new Call(this.getClient(), sid);
		call.setRequestAccountSid(this.getRequestAccountSid());
		return call;
	}

	/**
	 * Gets the call factory, which lets you make outgoing calls.
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/making_calls">http://www.twilio.com/docs/api/rest/making_calls</a>}
	 * 
	 * @return the call factory
	 */
	public CallFactory getCallFactory() {
		return this.getCalls();
	}

	/**
	 * Gets the sms message list.
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/sms">http://www.twilio.com/docs/api/rest/sms</a>}
	 * 
	 * @return the sms messages
	 */
	public SmsList getSmsMessages() {
		return this.getSmsMessages(new HashMap<String, String>());
	}

	/**
	 * Gets the sms messages list with the given filters
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/sms">http://www.twilio.com/docs/api/rest/sms</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the sms messages
	 */
	public SmsList getSmsMessages(Map<String, String> filters) {
		SmsList sms = new SmsList(this.getClient(), filters);
		sms.setRequestAccountSid(this.getRequestAccountSid());

		return sms;
	}
	
	/**
	 * Get a given sms instance by sid
	 * @param sid The 34 character sid starting with SM
	 * @return
	 */
	public Sms getSms(String sid) {
		Sms sms = new Sms(this.getClient(), sid);
		sms.setRequestAccountSid(this.getRequestAccountSid());
		return sms;
	}
	
	/**
	 * Gets the sms factory which lets you send sms messages
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/sending-sms">http://www.twilio.com/docs/api/rest/sending-sms</a>}
	 * @return the sms factory
	 */
	public SmsFactory getSmsFactory() {
		return this.getSmsMessages();
	}

	/**
	 * Gets the application list
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/applications">http://www.twilio.com/docs/api/rest/applications</a>}
	 * @return the applications
	 */
	public ApplicationList getApplications() {
		return this.getApplications(new HashMap<String, String>());
	}

	/**
	 * Gets the application list with the given filters
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/applications">http://www.twilio.com/docs/api/rest/applications</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the applications
	 */
	public ApplicationList getApplications(Map<String, String> filters) {
		ApplicationList list = new ApplicationList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}
	
	/**
	 * Get a given applicaiton instance by sid
	 * @param sid The 34 character sid starting with AP
	 * @return
	 */
	public Application getApplicaiton(String sid) {
		Application app = new Application(this.getClient(), sid);
		app.setRequestAccountSid(this.getRequestAccountSid());
		return app;
	}
	
	
	/**
	 * Gets the application factory which lets you create new applications
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/applications#list-post">http://www.twilio.com/docs/api/rest/applications#list-post</a>}
	 * 
	 * @return the application factory
	 */
	public ApplicationFactory getApplicationFactory() {
		return this.getApplications();
	}
	
	

	/**
	 * Gets the available phone numbers. Defaults to US/Local
	 * 
	 * See: {@see <a href="http://www.twilio.com/docs/api/rest/available-phone-numbers">http://www.twilio.com/docs/api/rest/available-phone-numbers</a>}
	 * @return the available phone numbers
	 */
	public AvailablePhoneNumberList getAvailablePhoneNumbers() {
		return this.getAvailablePhoneNumbers(new HashMap<String, String>());
	}

	/**
	 * Gets the available phone numbers. With the given search filters. Defaults
	 * to US/Local numbers
	 * 
	 * See: {@see <a href="http://www.twilio.com/docs/api/rest/available-phone-numbers">http://www.twilio.com/docs/api/rest/available-phone-numbers</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the available phone numbers
	 */
	public AvailablePhoneNumberList getAvailablePhoneNumbers(
			Map<String, String> filters) {
		AvailablePhoneNumberList list = new AvailablePhoneNumberList(
				this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Gets the available phone numbers with the given iso country and type
	 * 
	 * See: {@see <a href="http://www.twilio.com/docs/api/rest/available-phone-numbers">http://www.twilio.com/docs/api/rest/available-phone-numbers</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @param isoCountry
	 *            the Iso Country code you are searching in
	 * @param type
	 *            the type of phone number. Possible values are
	 *            AvailablePhoneNumber.TYPE_LOCAL or
	 *            AvailablePhoneNumber.TYPE_TOLLFREE
	 * 
	 * @return the available phone numbers
	 */
	public AvailablePhoneNumberList getAvailablePhoneNumbers(
			Map<String, String> filters, String isoCountry, String type) {
		AvailablePhoneNumberList list = new AvailablePhoneNumberList(
				this.getClient(), filters, isoCountry, type);
		list.setRequestAccountSid(this.getRequestAccountSid());

		return list;
	}

	/**
	 * Gets the conference list
	 * 
	 * See: {@see <a href="http://www.twilio.com/docs/api/rest/conference">http://www.twilio.com/docs/api/rest/conference</a>}
	 * 
	 * @return the conferences
	 */
	public ConferenceList getConferences() {
		return this.getConferences(new HashMap<String, String>());
	}

	/**
	 * Gets the conferences list with the given filters
	 * 
	 * See: {@see <a href="http://www.twilio.com/docs/api/rest/conference">http://www.twilio.com/docs/api/rest/conference</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the conferences
	 */
	public ConferenceList getConferences(Map<String, String> filters) {
		ConferenceList list = new ConferenceList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}
	
	/**
	 * Get a given conference instance by sid
	 * @param sid The 34 character sid starting with CF
	 * @return
	 */
	public Conference getConference(String sid) {
		Conference conf = new Conference(this.getClient(), sid);
		conf.setRequestAccountSid(this.getRequestAccountSid());
		return conf;
	}

	/**
	 * Gets the incoming phone numbers list
	 * 
	 * See: {@see <a href="http://www.twilio.com/docs/api/rest/incoming-phone-nubmers">http://www.twilio.com/docs/api/rest/incoming-phone-numbers</a>}
	 * 
	 * @return the incoming phone numbers
	 */
	public IncomingPhoneNumberList getIncomingPhoneNumbers() {
		return this.getIncomingPhoneNumbers(new HashMap<String, String>());
	}

	/**
	 * Gets the incoming phone numbers list with the given filters
	 * 
	 * See: {@see <a href="http://www.twilio.com/docs/api/rest/incoming-phone-nubmers">http://www.twilio.com/docs/api/rest/incoming-phone-numbers</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the incoming phone numbers
	 */
	public IncomingPhoneNumberList getIncomingPhoneNumbers(
			Map<String, String> filters) {
		IncomingPhoneNumberList list = new IncomingPhoneNumberList(
				this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}
	
	/**
	 * Get a given incoming phone number instance by sid
	 * @param sid The 34 character sid starting with PN
	 * @return
	 */
	public IncomingPhoneNumber getIncomingPhoneNumber(String sid) {
		IncomingPhoneNumber pn = new IncomingPhoneNumber(this.getClient(), sid);
		pn.setRequestAccountSid(this.getRequestAccountSid());
		return pn;
	}
	
	/**
	 * Gets the incoming phone number factory.
	 * 
	 * See: {@see <a href="http://www.twilio.com/docs/api/rest/incoming-phone-nubmers#list-post">http://www.twilio.com/docs/api/rest/incoming-phone-numbers#list-post</a>}
	 * 
	 * @return the incoming phone number factory
	 */
	public IncomingPhoneNumberFactory getIncomingPhoneNumberFactory() {
		return this.getIncomingPhoneNumbers();
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
	 * @param filters
	 *            the filters
	 * @return the short code list
	 */
	public ShortCodeList getShortCodes(Map<String, String> filters) {
		ShortCodeList list = new ShortCodeList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given short code instance by sid
	 * @param sid The 34 character sid starting with SC
	 * @return
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
	 * @param filters
	 *            the filters
	 * @return the notifications
	 */
	public NotificationList getNotifications(Map<String, String> filters) {
		NotificationList list = new NotificationList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given notification instance by sid
	 * @param sid The 34 character sid starting with NO
	 * @return
	 */
	public Notification getNotification(String sid) {
		Notification n = new Notification(this.getClient(), sid);
		n.setRequestAccountSid(this.getRequestAccountSid());
		return n;
	}
	

	/**
	 * Gets the outgoing caller ids.
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/outoing-caller-ids">http://www.twilio.com/docs/api/rest/outgoing-caller-ids</a>}
	 * 
	 * @return the outgoing caller ids
	 */
	public OutgoingCallerIdList getOutgoingCallerIds() {
		return this.getOutgoingCallerIds(new HashMap<String, String>());
	}

	/**
	 * Gets the outgoing caller ids.
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/outoing-caller-ids">http://www.twilio.com/docs/api/rest/outgoing-caller-ids</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the outgoing caller ids
	 */
	public OutgoingCallerIdList getOutgoingCallerIds(Map<String, String> filters) {
		OutgoingCallerIdList list = new OutgoingCallerIdList(this.getClient(),
				filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given outgoing caller id instance by sid
	 * @param sid The 34 character sid starting with PN
	 * @return
	 */
	public OutgoingCallerId getOutgoingCallerId(String sid) {
		OutgoingCallerId number = new OutgoingCallerId(this.getClient(), sid);
		number.setRequestAccountSid(this.getRequestAccountSid());
		return number;
	}
	
	
	
	/**
	 * Gets the outgoing caller id factory which lets you create outgoing caller ids
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/outoing-caller-ids">http://www.twilio.com/docs/api/rest/outgoing-caller-ids</a>}
	 * 
	 * @return the outgoing caller id factory
	 */
	public OutgoingCallerIdFactory getOutgoingCallerIdFactory() {
		return this.getOutgoingCallerIds();
	}

	/**
	 * Gets the recordings list
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/recording">http://www.twilio.com/docs/api/rest/recording</a>}
	 * 
	 * @return the recordings
	 */
	public RecordingList getRecordings() {
		return this.getRecordings(new HashMap<String, String>());
	}

	/**
	 * Gets the recordings list 
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/recording">http://www.twilio.com/docs/api/rest/recording</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the recordings
	 */
	public RecordingList getRecordings(Map<String, String> filters) {
		RecordingList list = new RecordingList(this.getClient(), filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Get a given recording instance by sid
	 * @param sid The 34 character sid starting with RE
	 * @return
	 */
	public Recording getRecording(String sid) {
		Recording r = new Recording(this.getClient(), sid);
		r.setRequestAccountSid(this.getRequestAccountSid());
		return r;
	}
	
	/**
	 * Gets the transcriptions list
	 * 
	 * {@see <a href="http://www.twilio.com/docs/api/rest/transcription">http://www.twilio.com/docs/api/rest/transcription</a>}
	 * 
	 * @return the transcriptions
	 */
	public TranscriptionList getTranscriptions() {
		return this.getTranscriptions(new HashMap<String, String>());
	}

	/**
	 * Gets the transcriptions list with the given filters
	 * 
	 *  {@see <a href="http://www.twilio.com/docs/api/rest/transcription">http://www.twilio.com/docs/api/rest/transcription</a>}
	 * 
	 * @param filters
	 *            the filters
	 * @return the transcriptions
	 */
	public TranscriptionList getTranscriptions(Map<String, String> filters) {
		TranscriptionList list = new TranscriptionList(this.getClient(),
				filters);
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}
	
	/**
	 * Get a given transcription instance by sid
	 * @param sid The 34 character sid starting with TR
	 * @return
	 */
	public Transcription getTranscription(String sid) {
		Transcription tr = new Transcription(this.getClient(), sid);
		tr.setRequestAccountSid(this.getRequestAccountSid());
		return tr;
	}
}
