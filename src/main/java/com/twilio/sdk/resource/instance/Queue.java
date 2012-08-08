package com.twilio.sdk.resource.instance;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.MemberList;

public class Queue extends InstanceResource {
    private final static String SID = "Sid";
    private final static String FRIENDLY_NAME = "FriendlyName";
    private final static String CURRENT_SIZE = "CurrentSize";
    private final static String MAX_SIZE = "MaxSize";
    private final static String AVERAGE_WAIT_TIME = "AverageWaitTime";

    public Queue(final TwilioRestClient client, final String sid) {
        super(client);
        this.setProperty(Queue.SID, sid);
    }

    public Queue(TwilioRestClient client, Map<String, Object> params) {
        super(client, params);
    }

    public String getSid() {
        return this.getProperty(Queue.SID);
    }

    public String getFriendlyName() {
        return this.getProperty(Queue.FRIENDLY_NAME);
    }

    public String getCurrentSize() {
        return this.getProperty(Queue.CURRENT_SIZE);
    }

    public String getMaxSize() {
        return this.getProperty(Queue.MAX_SIZE);
    }

    public String getAverageWaitTime() {
        return this.getProperty(Queue.AVERAGE_WAIT_TIME);
    }

    public MemberList getMembers() {
        MemberList list = new MemberList(this.getClient(), this.getSid());
        list.setRequestAccountSid(this.getRequestAccountSid());
        return list;
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Queues/" + this.getSid()
                + ".json";
    }

    public void setFriendlyName(final String friendlyName) throws TwilioRestException {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(Queue.FRIENDLY_NAME, friendlyName);
        this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);
    }

    public void setMaxSize(final String maxSize) throws TwilioRestException {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(Queue.MAX_SIZE, maxSize);
        this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);
    }
}
