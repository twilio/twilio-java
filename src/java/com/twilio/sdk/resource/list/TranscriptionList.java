package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Transcription;

// TODO: Auto-generated Javadoc
/**
 * The Class TranscriptionList.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/transcription">http://www.twilio.com/docs/api/rest/transcription}
 */
public class TranscriptionList extends ListResource<Transcription> {

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

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Transcriptions.json";
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
}
