package com.twilio.sdk.resource.instance;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;

import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.MemberList;

/**
 * The {@link Queue} represents a queue resource.
 *
 * @author Christer Fahlgren
 */
public class Queue extends InstanceResource {

    // Constants
    private final static String SID = "sid";
    private final static String FRIENDLY_NAME = "friendly_name";
    private final static String CURRENT_SIZE = "current_size";
    private final static String MAX_SIZE = "max_size";
    private final static String AVERAGE_WAIT_TIME = "average_wait_time";

    /**
     * Creates a {@link Queue} instance.
     *
     * @param client
     *            the {@link TwilioRestClient} to use
     * @param sid
     *            the queue sid
     */
    public Queue(TwilioRestClient client, String sid) {
        super(client);
        this.setProperty(Queue.SID, sid);
    }

    /**
     * Creates a {@link Queue} instance.
     *
     * @param client
     *            the {@link TwilioRestClient} to use
     * @param params
     *            the map of properties to read from
     */
    public Queue(TwilioRestClient client, Map<String, Object> params) {
        super(client, params);
    }

    /**
     * Retrieves the queue sid of this {@link Queue}
     *
     * @return
     */
    public String getSid() {
        return this.getProperty(Queue.SID);
    }

    /**
     * Retrieves the friendly name of this Queue.
     *
     * @return the friendly name
     */
    public String getFriendlyName() {
        return this.getProperty(Queue.FRIENDLY_NAME);
    }

    /**
     * Retrieves the current size of the {@link Queue}
     *
     * @return the current size
     * @throws IllegalStateException
     *             if the current size is not set
     */
    public int getCurrentSize() {
        Integer prop = (Integer)this.getObject(Queue.CURRENT_SIZE);
        if (prop != null) {
            return prop;
        } else {
            throw new IllegalStateException("The Queue instance doesn't have the current size property set");
        }
    }

    /**
     * Returns the max size of the {@link Queue}
     *
     * @return the max size of the {@link Queue}
     */
    public int getMaxSize() {
        Integer prop = (Integer)this.getObject(Queue.MAX_SIZE);
        if (prop != null) {
            return prop;
        } else {
            throw new IllegalStateException("The Queue instance doesn't have the max size property set");
        }
    }

    /**
     * Returns the average wait time in the {@link Queue}
     *
     * @return the average wait time
     */
    public int getAverageWaitTime() {
        Integer prop = (Integer) this.getObject(Queue.AVERAGE_WAIT_TIME);
        if (prop != null) {
            return prop;
        } else {
            throw new IllegalStateException("The Queue instance doesn't have the average wait time property set");
        }
    }

    /**
     * Retrieves the {@link MemberList} for this {@link Queue}
     *
     * @return the {@link MemberList}
     */
    public MemberList getMembers() {
        MemberList list = new MemberList(this.getClient(), this.getSid());
        list.setRequestAccountSid(this.getRequestAccountSid());
        return list;
    }

    /**
     * Return a single Member instance
     */
    public Member getMember(String callSid) {
        Member member = new Member(this.getClient(), this.getSid(), callSid);
        member.setRequestAccountSid(this.getRequestAccountSid());
        return member;
    }

    /**
     * Dequeue the head of the {@link Queue}, returning the {@link Member} or null if the {@link Queue} is empty.
     * Control is transferred to the url and method passed in.
     *
     * @param url
     *            the url to transfer control to
     * @param method
     *            the method to use.
     * @return
     * @throws TwilioRestException
     */
    public Member dequeueHeadOfQueue(String url, String method) throws TwilioRestException {
        // Creating a {@link Member} without passing in call sid denotes a member representing the front of the queue
        Member m = new Member(this.getClient(), this.getSid());
        m.setRequestAccountSid(this.getRequestAccountSid());
        return m.dequeue(url, method);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getResourceLocation() {
        return getBareResourceLocation() + ".json";

    }

    /**
     * Gets the resource location without specifying the format.
     *
     * @return the resource location
     */
    protected String getBareResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Queues/" + this.getSid();

    }

    /**
     * Sets the friendly name of this {@link Queue}.
     *
     * @param friendlyName
     *            the new friendly name
     * @throws TwilioRestException
     *             if setting the friendly name fails
     */
    public void setFriendlyName(String friendlyName) throws TwilioRestException {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(Queue.FRIENDLY_NAME, friendlyName);
        TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);
        if (response.isError()) {
            throw new IllegalStateException("Response indicated error:" + response.getResponseText());
        }
        this.setProperty(Queue.FRIENDLY_NAME, friendlyName);
    }

    /**
     * Sets the max size of this {@link Queue}.
     *
     * @param maxSize
     *            the new max size of the queue
     * @throws TwilioRestException
     *             if setting the max size fails
     */
    public void setMaxSize(int maxSize) throws TwilioRestException {
        Map<String, String> vars = new HashMap<String, String>();
        String maxSizeString = Integer.toString(maxSize);
        vars.put(Queue.MAX_SIZE, maxSizeString);
        TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);
        if (response.isError()) {
            throw new IllegalStateException("Response indicated error:" + response.getResponseText());
        }
        // if we reached this point, store it in our own set of properties, i.e. we don't have to load the instance
        // just to get to this property
        this.setProperty(Queue.MAX_SIZE, maxSize);
    }
}
