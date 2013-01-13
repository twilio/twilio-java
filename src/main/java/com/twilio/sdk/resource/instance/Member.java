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

/**
 * The {@link Member} represents a single {@link Queue} member.
 *
 * @author Christer Fahlgren
 */
public class Member extends InstanceResource {
    // Constants
    private final static String URL = "Url";
    private final static String METHOD = "Method";
    private final static String QUEUE_SID = "queue_sid";
    private final static String CALL_SID = "call_sid";
    private final static String DATE_ENQUEUED = "date_enqueued";
    private final static String WAIT_TIME = "wait_time";
    private final static String POSITION = "position";
    /**
     * The {@link SimpleDateFormat} string to use for formatting dates.
     */
    final static SimpleDateFormat format = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss Z");

    /**
     * The front of the queue is identified with a special id, "Front".
     */
    public final static String QUEUE_FRONT = "Front";

    /**
     * Creates a {@link Member}.
     *
     * @param client
     *            the {@link TwilioRestClient} to use.
     * @param queueSid
     *            the queue sid this {@link Member} represents.
     * @param callSid
     *            the call sid this {@link Member} represents
     */
    public Member(final TwilioRestClient client, final String queueSid,
            final String callSid) {
        super(client);
        this.setProperty(Member.QUEUE_SID, queueSid);
        this.setProperty(Member.CALL_SID, callSid);
    }

    /**
     * Creates a {@link Member} representing the front of the queue.
     *
     * @param client
     *            the {
     * @param queueSid
     */
    public Member(final TwilioRestClient client, final String queueSid) {
        this(client, queueSid, Member.QUEUE_FRONT);
    }

    /**
     * Constructs a {@link Member} by passing in the properties to read from.
     *
     * @param client
     *            the {@link TwilioRestClient} to use
     * @param properties
     *            the map of properties
     */
    public Member(final TwilioRestClient client, final Map<String, Object> properties, final String queueSid) {
        super(client, properties);
        this.setProperty(Member.QUEUE_SID, queueSid);
    }

    /**
     * Retrieves the queue sid.
     *
     * @return the queue sid
     */
    public String getQueueSid() {
        return this.getProperty(Member.QUEUE_SID);
    }

    /**
     * Retrieves the call sid.
     *
     * @return the call sid
     */
    public String getCallSid() {
        return this.getProperty(Member.CALL_SID);
    }

    /**
     * Retrieves the {@link Date} the call was enqueued.
     *
     * @return the {@link Date} the call was enqueued.
     */
    public Date getDateEnqueued() {
        try {
            return format.parse(this.getProperty(Member.DATE_ENQUEUED));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Retrieves the wait time in the queue for this {@link Member}.
     *
     * @return the wait time as a String
     */
    public String getWaitTime() {
        return this.getProperty(Member.WAIT_TIME);
    }

    /**
     * Retrieves the position of the {@link Member} in the queue.
     *
     * @return the position as a String
     */
    public String getPosition() {
        return this.getProperty(Member.POSITION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Queues/" + this.getQueueSid()
                + "/Members/" + this.getCallSid() + ".json";
    }

    /**
     * Dequeue this {@link Member} and transfer control to the passed in url and method. Returns an updated
     * {@link Member} object containing the actual wait time at the time of dequeue.
     *
     * @param url
     *            the url to redirect to
     * @param method
     *            the method to use
     * @return an updated {@link Member} object
     * @throws TwilioRestException
     *             if communication fails
     */
    public Member dequeue(final String url, final String method) throws TwilioRestException {
        final Map<String, String> vars = new HashMap<String, String>();
        vars.put(URL, url);
        vars.put(METHOD, method);
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "POST", vars);

        final Member member = new Member(this.getClient(), response.toMap(), getQueueSid());
        member.setRequestAccountSid(this.getRequestAccountSid());
        return member;
    }
}
