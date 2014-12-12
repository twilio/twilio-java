package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Member;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.Queue;
import com.twilio.sdk.resources.ResourceSet;

public class MemberReader extends Reader<Member> {

    private final String queueSid;

    public MemberReader(final String queueSid) {
        this.queueSid = queueSid;
    }

    public MemberReader(final Queue target) {
        this(target.getSid());
    }

    @Override
    public ResourceSet<Member> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/2010-04-01/Accounts/{AccountSid}/Queues/" + queueSid + "/Members.json",
                                      client.getAccountSid());

        Page<Member> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Member> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<Member> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for Member");
        }

        Page<Member> result = new Page<>();
        result.deserialize("queue_members", response.getContent(), Member.class, client.getObjectMapper());

        return result;
    }

}
