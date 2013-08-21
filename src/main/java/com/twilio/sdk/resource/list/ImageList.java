package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.ImageFactory;
import com.twilio.sdk.resource.instance.Image;

/**
 * The Class ImageList.
 *
 *  For more information see <a href="http://www.twilio.com/docs/api/rest/image">http://www.twilio.com/docs/api/rest/image</a>
 */
public class ImageList extends ListResource<Image> implements ImageFactory {

    private String requestMessageSid;

    /**
     * Instantiates a new image list.
     *
     * @param client the client
     */
    public ImageList(TwilioRestClient client) {
        super(client);
    }

    /**
     * Instantiates a new image list.
     *
     * @param client the client
     * @param messageSid the sid of the parent message requesting image
     */
    public ImageList(TwilioRestClient client, String messageSid) {
        super(client);
        this.requestMessageSid = messageSid;
    }

    /**
     * Instantiates a new image list.
     *
     * @param client the client
     * @param properties the properties
     */
    public ImageList(TwilioRestClient client, Map<String, String> properties) {
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
                + "/Media/Images.json";

        } else {
            return "/" + TwilioRestClient.DEFAULT_VERSION
                + "/Accounts/" + this.getRequestAccountSid()
                + "/Media/Images.json";
        }
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Image makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new Image(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "images";
	}

    /**
     * Gets the message sid of this image *if* it was initially referenced
     * as the child of a message.
     *
     * @return the message sid of the parent message
     */
    public String getRequestMessageSid() {
        return this.requestMessageSid;
    }

}
