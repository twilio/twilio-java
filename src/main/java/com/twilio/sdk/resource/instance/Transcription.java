package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * The Class Transcription.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/transcription">https://www.twilio.com/docs/api/rest/transcription</a>
 */
public class Transcription extends InstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new transcription.
	 *
	 * @param client the client
	 */
	public Transcription(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new transcription.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Transcription(TwilioRestClient client, String sid) {
		super(client);
	    if (sid == null) {
            throw new IllegalStateException("The Sid for a Transcription can not be null");
        }
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new transcription.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Transcription(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Transcriptions/"
				+ this.getSid() + ".json";
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.getProperty("status");
	}

	/**
	 * Gets the recording sid.
	 *
	 * @return the recording sid
	 */
	public String getRecordingSid() {
		return this.getProperty("recording_sid");
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return Integer.parseInt(this.getProperty("duration"));
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
	 * Gets the transcription text.
	 *
	 * @return the transcription text
	 */
	public String getTranscriptionText() {
		return this.getProperty("transcription_text");
	}

	/**
	 * Delete this Transcription
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
