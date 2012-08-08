package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

public class Member extends InstanceResource {
    private final static String QUEUE_SID = "QueueSid";
    private final static String CALL_SID = "CallSid";
    private final static String DATE_ENQUEUED = "DateEnqueued";
    private final static String WAIT_TIME = "WaitTime";
    private final static String POSITION = "Position";

    public final static String QUEUE_FRONT = "Front";

    public Member(final TwilioRestClient client, final String queueSid,
            final String callSid) {
        super(client);
        this.setProperty(Member.QUEUE_SID, queueSid);
        this.setProperty(Member.CALL_SID, callSid);
    }

    public Member(final TwilioRestClient client, final String queueSid) {
        this(client, queueSid, Member.QUEUE_FRONT);
    }

    public String getQueueSid() {
        return this.getProperty(Member.QUEUE_SID);
    }

    public String getCallSid() {
        return this.getProperty(Member.CALL_SID);
    }

    public String getDateEnqueued() {
        return this.getProperty(Member.DATE_ENQUEUED);
    }

    public String getWaitTime() {
        return this.getProperty(Member.WAIT_TIME);
    }

    public String getPosition() {
        return this.getProperty(Member.POSITION);
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Queues/" + this.getQueueSid()
                + "/Members/" + this.getCallSid() + ".json";
    }

}
