package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.TranscriptionList;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * The Class Recording.
 * <p/>
 * For more information see <a href="https://www.twilio.com/docs/api/rest/recording">https://www.twilio.com/docs/api/rest/recording</a>
 */
public class Recording extends InstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new recording.
	 *
	 * @param client the client
	 */
	public Recording(final TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new recording.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Recording(final TwilioRestClient client, final String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for a Recording can not be null");
		}
		setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new recording.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Recording(final TwilioRestClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return getResourceLocation(".json");
	}

	/**
	 * Gets the resource location.
	 *
	 * @param extension the extension
	 * @return the resource location
	 */
	protected String getResourceLocation(final String extension) {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Recordings/" + getSid() + extension;
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
		return getProperty(SID_PROPERTY);
	}

	/**
	 * Returns the list of associated transcriptions
	 *
	 * @return the TranscriptionList associated with the recording
	 */
	public TranscriptionList getTranscriptions() {
		TranscriptionList transcriptions = TranscriptionList.recordingTranscriptionList(getClient(), getSid());
		transcriptions.setRequestAccountSid(getRequestAccountSid());
		return transcriptions;
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
		return getProperty("account_sid");
	}

	/**
	 * Gets the call sid.
	 *
	 * @return the call sid
	 */
	public String getCallSid() {
		return getProperty("call_sid");
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return Integer.parseInt(getProperty("duration"));
	}

	/**
	 * Gets the api version.
	 *
	 * @return the api version
	 */
	public String getApiVersion() {
		return getProperty("api_version");
	}

	/**
	 * Gets the media.
	 *
	 * @param extension the extension
	 * @return the media
	 */
	public InputStream getMedia(final String extension) {
		return getClient().requestStream(getResourceLocation(extension), "GET", (Map) null);
	}

	/**
	 * Delete this Recording
	 *
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "DELETE", (Map) null);

		return !response.isError();
	}
}
