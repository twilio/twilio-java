package com.twilio.jwt.taskrouter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.twilio.http.HttpMethod;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link PolicyUtils}.
 */
public class PolicyUtilsTest {

    @Test
    public void testDefaultWorkerPolicies() {
        String workspaceSid = "WS123";
        String workerSid = "WK123";

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

        List<Policy> policies = Arrays.asList(activities, tasks, reservations, workerFetch);
        assertEquals(
            policies,
            PolicyUtils.defaultWorkerPolicies(workspaceSid, workerSid)
        );
    }

    @Test
    public void testDefaultEventBridgePolicies() {
        String accountSid = "AC123";
        String channelId = "CH123";
        String url = String.join("/", "https://event-bridge.twilio.com/v1/wschannels", accountSid, channelId);

        Policy get = new Policy.Builder().url(url).method(HttpMethod.GET).allowed(true).build();
        Policy post = new Policy.Builder().url(url).method(HttpMethod.POST).allowed(true).build();
        List<Policy> policies = Arrays.asList(get, post);

        assertEquals(
            policies,
            PolicyUtils.defaultEventBridgePolicies(accountSid, channelId)
        );
    }

}
