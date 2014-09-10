package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Transcription;

import java.util.Map;

/**
 * The Class TranscriptionList.
 * <p/>
 * For more information see <a href="https://www.twilio.com/docs/api/rest/transcription">https://www.twilio.com/docs/api/rest/transcription</a>
 */
public class TranscriptionList extends ListResource<Transcription, TwilioRestClient> {

	private static String requestCallSid;

	private static String requestRecordingSid;

	/**
	 * Instantiates a new transcription list from a call.
	 *
	 * @param client
	 * @param callSid
	 * @return a call transcription list
	 */
	public static TranscriptionList callTranscriptionList(final TwilioRestClient client, final String callSid) {
		return new TranscriptionList(client, callSid);
	}

	/**
	 * Instantiates a new transcription list from a call.
	 *
	 * @param client
	 * @param callSid
	 * @param filters
	 * @return a call transcription list
	 */
	public static TranscriptionList callTranscriptionList(final TwilioRestClient client, final String callSid,
	                                                      final Map<String, String> filters) {
		return new TranscriptionList(client, callSid, filters);
	}

	/**
	 * Instantiates a new transcription list from a recording.
	 *
	 * @param client
	 * @param recordingSid
	 * @return a call transcription list
	 */
	public static TranscriptionList recordingTranscriptionList(final TwilioRestClient client,
	                                                           final String recordingSid) {
		TranscriptionList transcriptions = new TranscriptionList(client);
		requestRecordingSid = recordingSid;
		return transcriptions;
	}

	/**
	 * Instantiates a new transcription list from a recording.
	 *
	 * @param client
	 * @param recordingSid
	 * @param filters
	 * @return a call transcription list
	 */
	public static TranscriptionList recordingTranscriptionList(final TwilioRestClient client, final String recordingSid,
	                                                           final Map<String, String> filters) {
		TranscriptionList transcriptions = new TranscriptionList(client, filters);
		requestRecordingSid = recordingSid;
		return transcriptions;
	}

	/**
	 * Instantiates a new transcription list.
	 *
	 * @param client the client
	 */
	public TranscriptionList(final TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new transcription list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public TranscriptionList(final TwilioRestClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	/**
	 * Instantiates a new transcription list from a call.
	 *
	 * @param client the client
	 * @param callSid the sid of the parent call
	 */
	public TranscriptionList(final TwilioRestClient client, final String callSid) {
		super(client);
		requestCallSid = callSid;
	}

	/**
	 * Instantiates a new transcription list from a call with filters.
	 *
	 * @param client the client
	 * @param callSid the sid of the parent call
	 * @param filters the filters
	 */
	public TranscriptionList(final TwilioRestClient client, final String callSid, final Map<String, String> filters) {
		super(client, filters);
		requestCallSid = callSid;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		if (requestCallSid != null) {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Calls/" + getRequestCallSid() + "/Transcriptions.json";
		} else if (requestRecordingSid != null) {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Recordings/" + requestRecordingSid + "/Transcriptions.json";
		} else {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Transcriptions.json";
		}

	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Transcription makeNew(final TwilioRestClient client, final Map<String, Object> params) {
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
	 * Gets the call sid of the transcription *if* it was constructed with a call sid.
	 *
	 * @return the call sid of the parent call
	 */
	public String getRequestCallSid() {
		return requestCallSid;
	}

	/**
	 * Gets the recording sid of the transcription *if* it was constructed with a recording sid.
	 *
	 * @return the recording sid of the parent recording
	 */
	public String getRequestRecordingSid() {
		return requestRecordingSid;
	}
}
