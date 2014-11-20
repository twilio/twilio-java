package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twilio.sdk.resource.instance.Token;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class TokenTest {

	final String accountSid = "AC12345678901234567890123456789012";

	TwilioRestClient client = mock(TwilioRestClient.class);
	TwilioRestResponse resp = mock(TwilioRestResponse.class);

	private void setupMocks() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		stub(resp.toMap()).toReturn(map);

		map.put("account_sid", accountSid);
		map.put("password", "M87Dd74GbNfyrAydvEKiDR43go52fo6ldoJBHB6gim0");
		map.put("registrars", null);
		map.put("ttl", "86400");
		map.put("username", "b759d275ddc641cd379f329882abe3c0618c8afdfc5e24be1b4d59482244240f");

		List<Map<String, String>> serverList = new ArrayList<Map<String, String>>();
		Map<String, String> serverMap = new HashMap<String, String>();

		serverMap.put("url", "stun:global.stun.twilio.com:3478?transport=udp");
		serverMap.put("username", "b759d275ddc641cd379f329882abe3c0618c8afdfc5e24be1b4d59482244240f");
		serverMap.put("credential", "M87Dd74GbNfyrAydvEKiDR43go52fo6ldoJBHB6gim0=");

		serverList.add(serverMap);

		map.put("ice_servers", serverList);

	}

	@Test
	public void testCreation() throws TwilioRestException {
		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Tokens.json"),
					Matchers.eq("POST"), Matchers.any(Map.class)))
			.toReturn(resp);

		final Token token = new Token(client, accountSid);
		token.setRequestAccountSid(accountSid);

		assertTrue(token.getPassword().equals("M87Dd74GbNfyrAydvEKiDR43go52fo6ldoJBHB6gim0"));
		assertTrue(token.getTtl().equals(86400));

		List<Token.IceServer> servers = token.getIceServers();

        for (Token.IceServer server : servers) {
            assertTrue(server.getUsername().equals("b759d275ddc641cd379f329882abe3c0618c8afdfc5e24be1b4d59482244240f"));
        }

	}

}
