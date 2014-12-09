package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Member;
import com.twilio.sdk.resources.Queue;

public class MemberFetcher extends Fetcher<Member> {

    private final String queueSid;
    private final String sid;

    public MemberFetcher(final String queueSid, final String sid) {
        this.queueSid = queueSid;
        this.sid = sid;
    }

    public MemberFetcher(final Queue targetQueue, final String sid) {
        this(targetQueue.getSid(), sid);
    }

    @Override
    public Member execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET,
                                      String.format("/Accounts/{AccountSid}/Queues/%s/Members/%s.json", queueSid, sid),
                                      client.getAccountSid());
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to retrieve Member " + sid + " for Queue " + queueSid +
                                   ": [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Member.fromJson(response.getStream(), client.getObjectMapper());
    }
}
