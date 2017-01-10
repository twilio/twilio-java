package com.twilio.jwt.taskrouter;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.twilio.http.HttpMethod;

import java.util.List;

public class PolicyUtils {

    private static final String TASKROUTER_EVENT_URL = "https://event-bridge.twilio.com/v1/wschannels";

    private PolicyUtils() {}

    /**
     * Build the default Polices for a Worker.
     *
     * @param workspaceSid Workspace sid of the worker
     * @param workerSid Worker sid
     * @return generated Policies
     */
    public static List<Policy> defaultWorkerPolicies(String workspaceSid, String workerSid) {
        Policy activities = new Policy.Builder()
            .url(UrlUtils.activities(workspaceSid))
            .method(HttpMethod.GET)
            .allowed(true)
            .build();

        Policy tasks = new Policy.Builder()
            .url(UrlUtils.allTasks(workspaceSid))
            .method(HttpMethod.GET)
            .allowed(true)
            .build();

        Policy reservations = new Policy.Builder()
            .url(UrlUtils.allReservations(workspaceSid, workerSid))
            .method(HttpMethod.GET)
            .allowed(true)
            .build();
        
        Policy workerFetch = new Policy.Builder()
            .url(UrlUtils.worker(workspaceSid, workerSid))
            .method(HttpMethod.GET)
            .allowed(true)
            .build();

        return Lists.newArrayList(activities, tasks, reservations, workerFetch);
    }

    /**
     * Build the default Event Bridge Policies.
     *
     * @param accountSid account sid
     * @param channelId channel id
     * @return generated Policies
     */
    public static List<Policy> defaultEventBridgePolicies(String accountSid, String channelId) {
        String url = Joiner.on('/').join(TASKROUTER_EVENT_URL, accountSid, channelId);

        Policy get = new Policy.Builder().url(url).method(HttpMethod.GET).allowed(true).build();
        Policy post = new Policy.Builder().url(url).method(HttpMethod.POST).allowed(true).build();

        return Lists.newArrayList(get, post);
    }
}
