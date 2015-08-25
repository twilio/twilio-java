package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.factory.FeedbackFactory;
import com.twilio.sdk.resource.factory.impl.FeedbackFactoryImpl;
import com.twilio.sdk.resource.list.RecordingList;
import com.twilio.sdk.resource.list.TranscriptionList;
import com.twilio.sdk.resource.list.NotificationList;
import org.apache.http.NameValuePair;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * The Class Call. For more information see <a href="https://www.twilio.com/docs/api/rest/call">https://www.twilio.com/docs/api/rest/call</a>
 */
public class Call extends InstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new call.
	 *
	 * @param client the client
	 */
	public Call(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new call.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Call(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for a Call can not be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new call.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Call(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return getResourceLocation(".json");
	}

	private String getResourceLocation(String extension) {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + this.getRequestAccountSid() + "/Calls/" +
		       this.getSid() + extension;
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
	 * Returns the list of associated transcriptions
	 *
	 * @return the TranscriptionList associated this this call
	 */
	public TranscriptionList getTranscriptions() {
		TranscriptionList transcriptions = new TranscriptionList(this.getClient(), this.getSid());
		transcriptions.setRequestAccountSid(this.getRequestAccountSid());
		return transcriptions;
	}

	/**
	 * Returns the list of associated recordings
	 *
	 * @return the RecordingList associated this this call
	 */
	public RecordingList getRecordings() {
		RecordingList recordings = new RecordingList(this.getClient(), this.getSid());
		recordings.setRequestAccountSid(this.getRequestAccountSid());
		return recordings;
	}

	/**
	 * Returns the list of associated notifications
	 *
	 * @return the NotificationList associated this this call
	 */
	public NotificationList getNotifications() {
		NotificationList notifications = new NotificationList(this.getClient(), this.getSid());
		notifications.setRequestAccountSid(this.getRequestAccountSid());
		return notifications;
	}

	/**
	 * Gets the parent call sid.
	 *
	 * @return the parent call sid
	 */
	public String getParentCallSid() {
		return this.getProperty("parent_call_sid");
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return getDateProperty("date_created");
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return getDateProperty("date_updated");
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
	 * Gets the phone number sid.
	 *
	 * @return the phone number sid
	 */
	public String getPhoneNumberSid() {
		return this.getProperty("phone_number_sid");
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
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public Date getStartTime() {
		return getDateProperty("start_time");
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public Date getEndTime() {
		return getDateProperty("end_time");
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return this.getProperty("duration");
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
	 * Gets the answered by.
	 *
	 * @return the answered by
	 */
	public String getAnsweredBy() {
		return this.getProperty("answered_by");
	}

	/**
	 * Gets the forwarded from.
	 *
	 * @return the forwarded from
	 */
	public String getForwardedFrom() {
		return this.getProperty("forwarded_from");
	}

	/**
	 * Gets the caller name.
	 *
	 * @return the caller name
	 */
	public String getCallerName() {
		return this.getProperty("caller_name");
	}

	/**
	 * The API version that Twilio used for TwiML requests during this call.
	 * @return TwiML API version
	 */
	public String getApiVersion() {
		return getProperty("api_version");
	}

	/**
	 * Gets the feedback factory, which lets add feedback to this call.
	 *
	 * @return the feedback factory
	 */
	public FeedbackFactory getFeedbackFactory() {
		return new FeedbackFactoryImpl(this.getClient(), this.getResourceLocation(""));
	}

	/**
	 * Delete the call feedback.
	 *
	 * @return true, if successful
	 * @throws TwilioRestException if there is an error in the request
	 */
	public boolean deleteFeedback() throws TwilioRestException {
		TwilioRestResponse response = this.getClient()
		                                  .safeRequest(this.getResourceLocation("") + "/Feedback.json", "DELETE",
		                                               (Map) null);

		return !response.isError();
	}

	/**
	 * Sets the call feedback. This method is a synonym of {@link com.twilio.sdk.resource.factory.FeedbackFactory#create}.
	 *
	 * @param params the feedback parameters
	 * @throws TwilioRestException
	 */
	public void setFeedback(List<NameValuePair> params) throws TwilioRestException {
		FeedbackFactory factory = getFeedbackFactory();
		factory.create(params);
	}

	/**
	 * Gets the feedback for the call.
	 *
	 * @return The call feedback object.
	 */
	public Feedback getFeedback() {
		Feedback feedback = new Feedback(this.getClient(), this.getResourceLocation());
		feedback.setRequestAccountSid(this.getRequestAccountSid());
		return feedback;
	}

    /*
     *
     * Useful functions
     */

	/**
	 * Redirect.
	 *
	 * @param url the url
	 * @param method the method
	 * @return the call
	 * @throws TwilioRestException the twilio rest exception
	 */
	public Call redirect(String url, String method) throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Method", method);
		vars.put("Url", url);
		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);

		Call c = new Call(this.getClient(), response.toMap());
		c.setRequestAccountSid(this.getRequestAccountSid());
		return c;
	}

	/**
	 * Hangup.
	 *
	 * @return the call
	 * @throws TwilioRestException the twilio rest exception
	 */
	public Call hangup() throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Status", "completed");

		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);

		Call c = new Call(this.getClient(), response.toMap());
		c.setRequestAccountSid(this.getRequestAccountSid());
		return c;
	}

	/**
	 * Cancel.
	 *
	 * @return the call
	 * @throws TwilioRestException the twilio rest exception
	 */
	public Call cancel() throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Status", "canceled");

		TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);

		Call c = new Call(this.getClient(), response.toMap());
		c.setRequestAccountSid(this.getRequestAccountSid());
		return c;
	}

    /**
     * Delete this {@link Call}. This will remove it from this {@link Account}.
     *
     * @throws com.twilio.sdk.TwilioRestException
     *             if there is an error in the request
     * @return true, if successful
     *
     */
    public boolean delete() throws TwilioRestException {
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "DELETE", (Map) null);

        return !response.isError();
    }
}
