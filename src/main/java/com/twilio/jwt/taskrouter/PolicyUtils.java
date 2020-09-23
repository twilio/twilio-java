package com.twilio.jwt.taskrouter;

import com.twilio.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

public class PolicyUtils {

    private static final String TASKROUTER_EVENT_URL = "https://event-bridge.twilio.com/v1/wschannels";

    private PolicyUtils() {}

    /**
     * Build the default Polices for a Worker.
     *
     * @param workspaceSid Workspace sid of the worker
     * @param workerSid    Worker sid
     * @return generated Policies
     */
    public static List<Policy> defaultWorkerPolicies(String workspaceSid, String workerSid) {
        final List<Policy> policies = new ArrayList<>();

        policies.add(new Policy.Builder()
            .url(UrlUtils.activities(workspaceSid))
            .method(HttpMethod.GET)
            .allowed(true)
            .build());

        policies.add(new Policy.Builder()
            .url(UrlUtils.allTasks(workspaceSid))
            .method(HttpMethod.GET)
            .allowed(true)
            .build());

        policies.add(new Policy.Builder()
            .url(UrlUtils.allReservations(workspaceSid, workerSid))
            .method(HttpMethod.GET)
            .allowed(true)
            .build());

        policies.add(new Policy.Builder()
            .url(UrlUtils.worker(workspaceSid, workerSid))
            .method(HttpMethod.GET)
            .allowed(true)
            .build());

        return policies;
    }

    /**
     * Build the default Event Bridge Policies.
     *
     * @param accountSid account sid
     * @param channelId  channel id
     * @return generated Policies
     */
    public static List<Policy> defaultEventBridgePolicies(String accountSid, String channelId) {
        final List<Policy> policies = new ArrayList<>();

        String url = String.join("/", TASKROUTER_EVENT_URL, accountSid, channelId);

        policies.add(new Policy.Builder().url(url).method(HttpMethod.GET).allowed(true).build());
        policies.add(new Policy.Builder().url(url).method(HttpMethod.POST).allowed(true).build());

        return policies;
    }
}
