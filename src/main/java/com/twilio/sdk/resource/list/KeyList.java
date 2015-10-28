package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.ResourceFactory;
import com.twilio.sdk.resource.instance.Key;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * List resource representation for a Auth Key
 */
public class KeyList extends ListResource<Key, TwilioRestClient> implements ResourceFactory<Key> {

    /**
     * Instantiates a new key list.
     *
     * @param client Twilio client
     */
    public KeyList(final TwilioRestClient client) {
        this(client, null);
    }

    /**
     * Instantiates a new key list.
     *
     * @param client Twilio client
     * @param filters request filters
     */
    public KeyList(final TwilioRestClient client, final Map<String, String> filters) {
        super(client, filters);
    }

    @Override
    protected Key makeNew(TwilioRestClient client, Map<String, Object> params) {
        return new Key(client, params);
    }

    @Override
    protected String getListKey() {
        return "keys";
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Keys.json";
    }

    @Override
    public Key create(Map<String, String> params) throws TwilioRestException {
        return makeNew(getClient(), getClient().safeRequest(getResourceLocation(), "POST", params).toMap());
    }

    @Override
    public Key create(List<NameValuePair> params) throws TwilioRestException {
        return makeNew(getClient(), getClient().safeRequest(getResourceLocation(), "POST", params).toMap());
    }
}
