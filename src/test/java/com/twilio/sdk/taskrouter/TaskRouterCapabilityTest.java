package com.twilio.sdk.taskrouter;


import org.apache.commons.codec.binary.Base64;
import com.twilio.sdk.CapabilityToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskRouterCapabilityTest {

	@Test
	public void testGenerateToken() throws Exception {
		TaskRouterCapability capability = new TaskRouterCapability("AC123", "foobar", "WS456", "WK789");
		capability.allowWorkerActivityUpdates();
		String token = capability.generateToken();
		String[] parts = token.split("\\.");
		assertEquals(3, parts.length);
		String payload = parts[1];
		byte[] bytes = Base64.decodeBase64(payload);
		String json = new String(bytes, "UTF-8");
		JSONParser parser = new JSONParser();
		Object decoded = parser.parse(json);
		JSONObject o = (JSONObject) decoded;
		assertEquals("AC123", o.get("iss"));
		assertEquals("AC123", o.get("account_sid"));
		assertEquals("WK789", o.get("channel"));
		assertEquals("WK789", o.get("friendly_name"));
		assertEquals("v1", o.get("version"));
		assertEquals("WS456", o.get("workspace_sid"));
		JSONArray policies = (JSONArray) o.get("policies");
		assertEquals(4, policies.size());
		JSONObject p = (JSONObject) policies.get(0);
		assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url"));
		assertEquals("GET", p.get("method"));
		p = (JSONObject) policies.get(1);
		assertEquals("https://event-bridge.twilio.com/v1/wschannels/AC123/WK789", p.get("url"));
		assertEquals("POST", p.get("method"));
		p = (JSONObject) policies.get(2);
		assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Activities", p.get("url"));
		assertEquals("GET", p.get("method"));
		assertTrue((Boolean)p.get("allow"));
		p = (JSONObject) policies.get(3);
		assertEquals("https://taskrouter.twilio.com/v1/Workspaces/WS456/Workers/WK789", p.get("url"));
		assertEquals("POST", p.get("method"));
		JSONObject filters = (JSONObject) p.get("post_filter");
		assertEquals(1, filters.size());
		JSONObject required = (JSONObject) filters.get("ActivitySid");
		assertEquals(Boolean.TRUE, required.get("required"));
	}
}
