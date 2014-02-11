package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Media;

import java.util.Map;

/**
 * The Class MediaList.
 *
 *  For more information see <a href="https://www.twilio.com/docs/api/rest/media">https://www.twilio.com/docs/api/rest/media</a>
 */
public class MediaList extends ListResource<Media> {

	private String requestMessageSid;

	/**
	 * Instantiates a new media list.
	 *
	 * @param client the client
	 */
	public MediaList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new media list.
	 *
	 * @param client the client
	 * @param messageSid the sid of the parent message requesting media
	 */
	public MediaList(TwilioRestClient client, String messageSid) {
		super(client);
		this.requestMessageSid = messageSid;
	}

	/**
	 * Instantiates a new media list.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public MediaList(TwilioRestClient client, Map<String, String> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
		protected String getResourceLocation() {
			if (this.requestMessageSid != null) {
				return "/" + TwilioRestClient.DEFAULT_VERSION
					+ "/Accounts/" + this.getRequestAccountSid()
					+ "/Messages/" + this.getRequestMessageSid()
					+ "/Media.json";

			} else {
				return "/" + TwilioRestClient.DEFAULT_VERSION
					+ "/Accounts/" + this.getRequestAccountSid()
					+ "/Media.json";
			}
		}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
		protected Media makeNew(TwilioRestClient client, Map<String, Object> params) {
			return new Media(client, params);
		}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
		protected String getListKey() {
			return "media_list";
		}

	/**
	 * Gets the message sid of this media *if* it was initially referenced
	 * as the child of a message.
	 *
	 * @return the message sid of the parent message
	 */
	public String getRequestMessageSid() {
		return this.requestMessageSid;
	}

}
