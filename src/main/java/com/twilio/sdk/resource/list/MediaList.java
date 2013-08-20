package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.MediaFactory;
import com.twilio.sdk.resource.instance.MediaInstance;

public class MediaList extends ListResource<MediaInstance> implements MediaFactory {

    public MediaList(TwilioRestClient client) {
        super(client);
    }

    public MediaList(TwilioRestClient client, Map<String, String> filters) {
        super(client, filters);
    }

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Media.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected MediaInstance makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new MediaInstance(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "media";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.MediaFactory#create(java.util.Map)
	 */
	public MediaInstance create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
