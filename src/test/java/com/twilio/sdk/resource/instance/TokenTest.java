package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestException;
import org.apache.http.NameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TokenTest extends BasicRequestTester {

	@Mock
	private Account account;

	private Token token;

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("token.json");
		token = restClient.getAccount().getTokenFactory().create(new ArrayList<NameValuePair>());
	}

	@Test
	public void testUsername() throws TwilioRestException {
		assertEquals("b759d275ddc641cd379f329882abe3c0618c8afdfc5e24be1b4d59482244240f", token.getUsername());
	}

	@Test
	public void testPassword() throws TwilioRestException {
		assertEquals("M87Dd74GbNfyrAydvEKiDR43go52fo6ldoJBHB6gim0", token.getPassword());
	}

	@Test
	public void testAccountSid() throws TwilioRestException {
		assertEquals("AC12345678901234567890123456789012", token.getAccountSid());
	}

	@Test
	public void testGetTtl() throws TwilioRestException {
		assertEquals(86400, token.getTtl());
	}

    @Test
    public void testGetIceServers() throws TwilioRestException {
        List<Token.IceServer> servers = token.getIceServers();
        Token.IceServer server = servers.get(0);
        assertEquals("stun:global.stun.twilio.com:3478?transport=udp", server.getUrl());
        assertEquals("b759d275ddc641cd379f329882abe3c0618c8afdfc5e24be1b4d59482244240f", server.getUsername());
        assertEquals("M87Dd74GbNfyrAydvEKiDR43go52fo6ldoJBHB6gim0=", server.getCredential());
        assertTrue(server.hasUsername());
        assertTrue(server.hasCredential());
    }

}
