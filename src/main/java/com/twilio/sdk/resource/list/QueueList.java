package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.QueueFactory;
import com.twilio.sdk.resource.instance.Queue;

public class QueueList extends ListResource<Queue> implements QueueFactory {

    public QueueList(final TwilioRestClient client) {
        super(client);
    }

    public QueueList(final TwilioRestClient client, 
            final Map<String, String> filters) {
        super(client, filters);
    }

    @Override
    public Queue create(Map<String, String> params) throws TwilioRestException {
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "POST", params);
        return makeNew(this.getClient(), response.toMap());
    }

    @Override
    protected Queue makeNew(TwilioRestClient client, Map<String, Object> params) {
        return new Queue(client, params);
    }

    @Override
    protected String getListKey() {
        return "queues";
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Queues.json";
    }

}
