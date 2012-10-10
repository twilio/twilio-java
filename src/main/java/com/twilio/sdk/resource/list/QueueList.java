package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.QueueFactory;
import com.twilio.sdk.resource.instance.Queue;

/**
 * The {@link QueueList} represents a list of {@link Queue}s.
 * 
 * @author Christer Fahlgren
 * 
 */
public class QueueList extends ListResource<Queue> implements QueueFactory {

    /**
     * Creates a {@link QueueList}
     * 
     * @param client
     *            the {@link TwilioRestClient} to use
     */
    public QueueList(final TwilioRestClient client) {
        super(client);
    }

    /**
     * Creates a {@link QueueList} with filters.
     * 
     * @param client
     *            the {@link TwilioRestClient} to use
     * @param filters
     *            the filters to apply.
     */
    public QueueList(final TwilioRestClient client,
            final Map<String, String> filters) {
        super(client, filters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Queue create(Map<String, String> params) throws TwilioRestException {
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "POST", params);
        return makeNew(this.getClient(), response.toMap());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Queue makeNew(TwilioRestClient client, Map<String, Object> params) {
        return new Queue(client, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getListKey() {
        return "queues";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Queues.json";
    }

}
