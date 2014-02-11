package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Transcription;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class TranscriptionList.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/transcription">https://www.twilio.com/docs/api/rest/transcription</a>
 */
public class TranscriptionList extends ListResource<Transcription> {

	private static String requestCallSid;

	/**
	 * Instantiates a new transcription list.
	 *
	 * @param client the client
	 */
	public TranscriptionList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new transcription list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public TranscriptionList(TwilioRestClient client,
			Map<String, String> filters) {
		super(client, filters);
	}

	/**
	 * Instantiates a new transcription list from a call.
	 *
	 * @param client the client
	 * @param callSid the sid of the parent call
	 */
	public TranscriptionList(TwilioRestClient client, String callSid) {
		super(client);
		this.requestCallSid = callSid;
	}

	/**
	 * Instantiates a new transcription list from a call with filters.
	 *
	 * @param client the client
	 * @param callSid the sid of the parent call
	 * @param filters the filters
	 */
	public TranscriptionList(TwilioRestClient client, String callSid,
			Map<String, String> filters) {
		super(client, filters);
		this.requestCallSid = callSid;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		if (this.requestCallSid != null) {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
					+ this.getRequestAccountSid() + "/Calls/"
					+ this.getRequestCallSid() + "/Transcriptions.json";
		} else {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
					+ this.getRequestAccountSid() + "/Transcriptions.json";
		}

	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Transcription makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new Transcription(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "transcriptions";
	}

	/**
	 * Gets the call sid of the transcription *if* it was constructed
	 * with a call sid.
	 *
	 * @return the call sid of the parent call
	 */
	public String getRequestCallSid() {
		return this.requestCallSid;
	}
}
