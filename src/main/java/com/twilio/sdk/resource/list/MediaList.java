package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.MediaFactory;
import com.twilio.sdk.resource.instance.Media;
import com.twilio.sdk.resource.instance.Image;
import com.twilio.sdk.resource.list.ImageList;

/**
 * The Class MediaList.
 *
 *  For more information see <a href="http://www.twilio.com/docs/api/rest/media">http://www.twilio.com/docs/api/rest/media</a>
 */
public class MediaList extends ListResource<Media> implements MediaFactory {

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
		return "media";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.MediaFactory#create(java.util.Map)
	 */
	public Media create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
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

    /**
     * Gets the set of images in the media list
     *
     * @return the images for this media list
     */
    public ImageList getImages() {
        ImageList images;
        if (this.getRequestMessageSid() != null) {
            images = new ImageList(this.getClient(), this.getRequestMessageSid());
        } else {
            images = new ImageList(this.getClient());
        }
        images.setRequestAccountSid(this.getRequestAccountSid());
        return images;
    }

    /**
     * Gets an image by imageSid from this media list.
     *
     * @param imageSid the image sid
     * @return the image
     */
    public Image getImage(String imageSid) {
        Image image;
        if (this.getRequestMessageSid() != null) {
            image = new Image(this.getClient(), this.getRequestMessageSid(), imageSid);
        } else {
            image = new Image(this.getClient(), imageSid);
        }
        image.setRequestAccountSid(this.getRequestAccountSid());
        return image;
    }

}
