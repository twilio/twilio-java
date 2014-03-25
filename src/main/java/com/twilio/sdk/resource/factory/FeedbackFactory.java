package com.twilio.sdk.resource.factory;

import java.util.List;

import org.apache.http.NameValuePair;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Feedback;

/**
 * A factory for creating/adding a Feedback object to a resource. To use, call the 
 * resource's getFeedbackFactory method. Currently, only 
 * {@link com.twilio.sdk.resource.instance.Call} resource's are supported.
 */
public interface FeedbackFactory {

    /**
     * Creates the feedback factory for adding feedback to an resource.
     *
     * @param params the params
     * @return the feedback
     * @throws TwilioRestException
     */
    public Feedback create(List<NameValuePair> params) throws TwilioRestException;
}
