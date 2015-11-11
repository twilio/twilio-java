package com.twilio.sdk.taskrouter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskRouterCapabilityTest {

    @Test
    public void testDeprecatedWorker() throws Exception {
        final TaskRouterCapability capability = new TaskRouterCapability("AC123", "foobar", "WS456", "WK789");
        capability.allowTaskReservationUpdates();
        capability.allowWorkerActivityUpdates();
        final String token = capability.generateToken();
        final String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        final String payload = parts[1];
        final byte[] bytes = Base64.decodeBase64(payload);
        final String json = new String(bytes, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode o = objectMapper.readTree(json);
        assertEquals("AC123", o.get("iss").asText());
        assertEquals("AC123", o.get("account_sid").asText());
        assertEquals("WK789", o.get("channel").asText());
        assertEquals("WK789", o.get("friendly_name").asText());
        assertEquals("v1", o.get("version").asText());
        assertEquals("WS456", o.get("workspace_sid").asText());
        final JsonNode policies = o.get("policies");
        assertEquals(7, policies.size());
        JsonNode p = policies.get(0);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Activities", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(1);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Tasks/**", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(2);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(3);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        p = policies.get(4);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Workers/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        assertTrue(p.get("allow").asBoolean());
        p = policies.get(5);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Tasks/**", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        p = policies.get(6);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Workers/WK789", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        final JsonNode filters = p.get("post_filter");
        assertEquals(1, filters.size());
        final JsonNode required = filters.get("ActivitySid");
        assertEquals(Boolean.TRUE, required.get("required").asBoolean());
    }

    @Test
    public void testGenerateDefaultWorkerToken() throws Exception {
        final TaskRouterWorkerCapability capability = new TaskRouterWorkerCapability("AC123", "foobar", "WS456", "WK789");
        final String token = capability.generateToken();
        final String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        final String payload = parts[1];
        final byte[] bytes = Base64.decodeBase64(payload);
        final String json = new String(bytes, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode o = objectMapper.readTree(json);
        assertEquals("AC123", o.get("iss").asText());
        assertEquals("AC123", o.get("account_sid").asText());
        assertEquals("WK789", o.get("channel").asText());
        assertEquals("WK789", o.get("friendly_name").asText());
        assertEquals("v1", o.get("version").asText());
        assertEquals("WS456", o.get("workspace_sid").asText());
        JsonNode policies = o.get("policies");
        assertEquals(5, policies.size());
        JsonNode p = policies.get(0);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(1);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        p = policies.get(2);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Workers/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        assertTrue(p.get("allow").asBoolean());
        p = policies.get(3);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Activities", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(4);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Tasks/**", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());

    }

    @Test
    public void testGenerateWorkerTokenWithActivities() throws Exception {
        final TaskRouterWorkerCapability capability = new TaskRouterWorkerCapability("AC123", "foobar", "WS456", "WK789");
        capability.allowActivityUpdates();
        final String token = capability.generateToken();
        final String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        final String payload = parts[1];
        final byte[] bytes = Base64.decodeBase64(payload);
        final String json = new String(bytes, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode o = objectMapper.readTree(json);
        assertEquals("AC123", o.get("iss").asText());
        assertEquals("AC123", o.get("account_sid").asText());
        assertEquals("WK789", o.get("channel").asText());
        assertEquals("WK789", o.get("friendly_name").asText());
        assertEquals("v1", o.get("version").asText());
        assertEquals("WS456", o.get("workspace_sid").asText());
        final JsonNode policies = o.get("policies");
        assertEquals(6, policies.size());
        JsonNode p = policies.get(0);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(1);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        p = policies.get(2);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Workers/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        assertTrue(p.get("allow").asBoolean());
        p = policies.get(3);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Activities", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(4);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Tasks/**", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(5);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Workers/WK789", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());

        final JsonNode filters = p.get("post_filter");
        assertEquals(1, filters.size());
        final JsonNode required = filters.get("ActivitySid");
        assertTrue(required.get("required").asBoolean());
    }

    @Test
    public void testGenerateWorkerTokenWithReservations() throws Exception {
        final TaskRouterWorkerCapability capability = new TaskRouterWorkerCapability("AC123", "foobar", "WS456", "WK789");
        capability.allowReservationUpdates();
        final String token = capability.generateToken();
        final String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        final String payload = parts[1];
        final byte[] bytes = Base64.decodeBase64(payload);
        final String json = new String(bytes, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode o = objectMapper.readTree(json);
        assertEquals("AC123", o.get("iss").asText());
        assertEquals("AC123", o.get("account_sid").asText());
        assertEquals("WK789", o.get("channel").asText());
        assertEquals("WK789", o.get("friendly_name").asText());
        assertEquals("v1", o.get("version").asText());
        assertEquals("WS456", o.get("workspace_sid").asText());
        final JsonNode policies = o.get("policies");
        assertEquals(6, policies.size());
        JsonNode p = policies.get(0);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(1);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        p = policies.get(2);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Workers/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        assertTrue(p.get("allow").asBoolean());
        p = policies.get(3);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Activities", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(4);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Tasks/**", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(5);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Tasks/**", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());

        final JsonNode filters = p.get("post_filter");
        assertEquals(0, filters.size());

    }

    // Workspace tokens
    @Test
    public void testGenerateWorkspaceToken() throws Exception {
        final TaskRouterWorkspaceCapability capability = new TaskRouterWorkspaceCapability("AC123", "foobar", "WS456");
        final String token = capability.generateToken();
        final String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        final String payload = parts[1];
        final byte[] bytes = Base64.decodeBase64(payload);
        final String json = new String(bytes, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode o = objectMapper.readTree(json);
        assertEquals("AC123", o.get("iss").asText());
        assertEquals("AC123", o.get("account_sid").asText());
        assertEquals("v1", o.get("version").asText());
        assertEquals("WS456", o.get("workspace_sid").asText());
        final JsonNode policies = o.get("policies");
        assertEquals(3, policies.size());
        JsonNode p = policies.get(0);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WS456", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(1);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WS456", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        p = policies.get(2);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        assertTrue(p.get("allow").asBoolean());

    }

    @Test
    public void testGenerateTaskQueueToken() throws Exception {
        final TaskRouterTaskQueueCapability capability = new TaskRouterTaskQueueCapability("AC123", "foobar", "WS456", "WQ111");
        final String token = capability.generateToken();
        final String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        final String payload = parts[1];
        final byte[] bytes = Base64.decodeBase64(payload);
        final String json = new String(bytes, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode o = objectMapper.readTree(json);
        assertEquals("AC123", o.get("iss").asText());
        assertEquals("AC123", o.get("account_sid").asText());
        assertEquals("v1", o.get("version").asText());
        assertEquals("WS456", o.get("workspace_sid").asText());
        final JsonNode policies = o.get("policies");
        assertEquals(3, policies.size());
        JsonNode p = policies.get(0);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WQ111", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(1);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WQ111", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        p = policies.get(2);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/TaskQueues/WQ111", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        assertTrue(p.get("allow").asBoolean());

    }

    @Test
    public void testGenerateTaskQueueTokenDisallow() throws Exception {
        try {
            final TaskRouterTaskQueueCapability capability = new TaskRouterTaskQueueCapability("AC123", "foobar", "WS456", "WQ111");
            capability.deny("https://taskrouter.twilio.com/v1/Workspaces/WS456/TaskQueues/WQ111", "GET", null, null);
            final String token = capability.generateToken();
            final String[] parts = token.split("\\.");
            assertEquals(3, parts.length);
            final String payload = parts[1];
            final byte[] bytes = Base64.decodeBase64(payload);
            final String json = new String(bytes, "UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            final JsonNode o = objectMapper.readTree(json);
            assertEquals("AC123", o.get("iss").asText());
            assertEquals("AC123", o.get("account_sid").asText());
            assertEquals("v1", o.get("version").asText());
            assertEquals("WS456", o.get("workspace_sid").asText());
            final JsonNode policies = o.get("policies");
        }
        catch (final Exception e) {
            assertEquals("Policy already exists", e.getMessage());
        }
    }

    @Test
    public void testGenerateTaskQueueTokenAllow() throws Exception {

        final TaskRouterTaskQueueCapability capability = new TaskRouterTaskQueueCapability("AC123", "foobar", "WS456", "WQ111");
        capability.allow("https://taskrouter.twilio.com/v1/Workspaces/WS456/TaskQueues/WQ111", "POST", null, null);
        final String token = capability.generateToken();
        final String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        final String payload = parts[1];
        final byte[] bytes = Base64.decodeBase64(payload);
        final String json = new String(bytes, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode o = objectMapper.readTree(json);
        assertEquals("AC123", o.get("iss").asText());
        assertEquals("AC123", o.get("account_sid").asText());
        assertEquals("v1", o.get("version").asText());
        assertEquals("WS456", o.get("workspace_sid").asText());
        final JsonNode policies = o.get("policies");
        assertEquals(4, policies.size());
    }

    @Test
    public void testGenerateDefaultToken() throws Exception {
        final TaskRouterCapability capability = new TaskRouterCapability("AC123", "foobar", "WS456", "WK789");
        final String token = capability.generateToken();
        final String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        final String payload = parts[1];
        final byte[] bytes = Base64.decodeBase64(payload);
        final String json = new String(bytes, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode o = objectMapper.readTree(json);
        assertEquals("AC123", o.get("iss").asText());
        assertEquals("AC123", o.get("account_sid").asText());
        assertEquals("WK789", o.get("channel").asText());
        assertEquals("WK789", o.get("friendly_name").asText());
        assertEquals("v1", o.get("version").asText());
        assertEquals("WS456", o.get("workspace_sid").asText());
        final JsonNode policies = o.get("policies");
        assertEquals(5, policies.size());
        JsonNode p = policies.get(0);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Activities", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(1);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Tasks/**", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(2);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        p = policies.get(3);
        assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url").asText());
        assertEquals("POST", p.get("method").asText());
        p = policies.get(4);
        assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Workers/WK789", p.get("url").asText());
        assertEquals("GET", p.get("method").asText());
        assertTrue(p.get("allow").asBoolean());
    }

}
