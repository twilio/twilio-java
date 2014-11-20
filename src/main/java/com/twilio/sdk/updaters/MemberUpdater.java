package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Member;
import com.twilio.sdk.resources.Queue;

import java.net.URI;

public class MemberUpdater extends Updater<Member> {
    private final String sid;
    private final String queueSid;
    private final URI url;
    private HttpMethod method;

    public MemberUpdater(final String queueSid, final String sid, final URI url) {
        this.sid = sid;
        this.queueSid = queueSid;
        this.url = url;
    }

    public MemberUpdater(final Queue targetQueue, final String sid, final URI url) {
        this(targetQueue.getSid(), sid, url);
    }

    public MemberUpdater(final String queueSid, final Member target, final URI url) {
        this(queueSid, target.getCallSid(), url);
    }

    public MemberUpdater(final Queue targetQueue, final Member target, final URI url) {
        this(targetQueue.getSid(), target.getCallSid(), url);
    }

    public MemberUpdater setMethod(final HttpMethod method) {
        this.method = method;
        return this;
    }

    @Override
    public Member execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Queues/" + queueSid + "/Members/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("QueueMember update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("QueueMember update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Member.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        request.addPostParam("Url", url.toString());

        if (method != null) {
            request.addPostParam("Method", method.toString());
        }
    }
}
