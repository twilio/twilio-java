
package com.twilio.sdk.resource.factory.impl;

import java.util.List;

import org.apache.http.NameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.FeedbackFactory;
import com.twilio.sdk.resource.instance.Feedback;

/**
 * An implementation of the interface {@link com.twilio.sdk.resource.factory.FeedbackFactory}.
 */
public class FeedbackFactoryImpl implements FeedbackFactory {

    private TwilioRestClient client;
    private String parentLocation;
   
    /**
     * Instantiates a new feedback factory.
     *
     * @param client the client
     * @param parentLocation the parent location
     */
    public FeedbackFactoryImpl(TwilioRestClient client, String parentLocation) {
        this.client = client;
        this.parentLocation = parentLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Feedback create(List<NameValuePair> params) throws TwilioRestException {
        TwilioRestResponse response = this.client.safeRequest(this.parentLocation + "/Feedback.json", "POST", params);
        return new Feedback(this.client, response.toMap(), this.parentLocation);
    }
}
