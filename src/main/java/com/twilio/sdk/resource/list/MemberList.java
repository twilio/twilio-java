package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Member;

/**
 * The {@link MemberList} represents the list resource for {@link Member}s.
 * 
 * For more information see <a
 * href="http://www.twilio.com/docs/api/rest/member">http://www.twilio.com/docs/api/rest/member</a>
 */
public class MemberList extends ListResource<Member> {

    /**
     * The queue sid.
     */
    private String queueSid;

    /**
     * Instantiates a new participant list.
     * 
     * @param client
     *            the client to use
     */
    public MemberList(final TwilioRestClient client) {
        super(client);
    }

    /**
     * Instantiates a new {@link MemberList}.
     * 
     * @param client
     *            the client
     * @param filters
     *            the filters if any
     */
    public MemberList(final TwilioRestClient client, final Map<String, String> filters) {
        super(client, filters);
    }

    /**
     *Instantiates a new {@link MemberList}.
     * 
     * @param client
     *            the {@link TwilioRestClient} to use
     * @param queueSid
     *            the queue sid for which this {@link MemberList} exists
     */
    public MemberList(final TwilioRestClient client, final String queueSid) {
        super(client);
        this.queueSid = queueSid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.twilio.sdk.resource.Resource#getResourceLocation()
     */
    @Override
    protected String getResourceLocation() {
        return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
                + this.getRequestAccountSid() + "/Queues/"
                + this.queueSid + "/Members.json";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
     */
    @Override
    protected Member makeNew(final TwilioRestClient client,
            final Map<String, Object> params) {
        return new Member(client, params, queueSid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.twilio.sdk.resource.ListResource#getListKey()
     */
    @Override
    protected String getListKey() {
        return "queue_members";
    }
}
