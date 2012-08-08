package com.twilio.sdk.resource.instance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
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

    public Member(final TwilioRestClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    public String getQueueSid() {
        return this.getProperty(Member.QUEUE_SID);
    }

    public String getCallSid() {
        return this.getProperty(Member.CALL_SID);
    }

    public Date getDateEnqueued() {
        final SimpleDateFormat format = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss Z");
        try {
            return format.parse(this.getProperty(Member.DATE_ENQUEUED));
        } catch (ParseException e) {
            return null;
        }
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

    public Member dequeue(final String url, final String method) throws TwilioRestException {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("url", url);
        vars.put("method", method);
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "POST", vars);

        Member member = new Member(this.getClient(), response.toMap());
        member.setRequestAccountSid(this.getRequestAccountSid());
        return member;
    }
}
