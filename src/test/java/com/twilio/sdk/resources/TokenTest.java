package com.twilio.sdk.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TokenTest {

    private static final String INSTANCE_JSON_RESPONSE = "{ \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"date_created\": \"Wed, 26 Nov 2014 19:32:18 +0000\", \"date_updated\": \"Wed, 26 Nov 2014 19:32:18 +0000\", \"ice_servers\": [ { \"url\": \"stun:global.stun.twilio.com:3478?transport=udp\" }, { \"credential\": \"LWCEdkvAeNG8uaNb2bCmQPVnuN4oTpgLhoRWWnUwESE=\", \"url\": \"turn:global.turn.twilio.com:3478?transport=udp\", \"username\": \"b499cbf0ddaf456e50b3c0f569efd1152d6b338a916494ba26ad01c70fd82612\" } ], \"password\": \"LWCEdkvAeNG8uaNb2bCmQPVnuN4oTpgLhoRWWnUwESE=\", \"registrars\": null, \"ttl\": \"86400\", \"username\": \"b499cbf0ddaf456e50b3c0f569efd1152d6b338a916494ba26ad01c70fd82612\"}";

    private static final String LIST_JSON_RESPONSE = "";

    @Mocked
    private Request request;

    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testFromJsonString() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Token instance = Token.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                      .getObjectMapper());
        assertNotNull(instance);

        List<IceServer> iceServers = new ArrayList<>();
        iceServers.add(new IceServer(null, null, new URI("stun:global.stun.twilio.com:3478?transport=udp")));
        iceServers.add(new IceServer("LWCEdkvAeNG8uaNb2bCmQPVnuN4oTpgLhoRWWnUwESE=",
                                     "b499cbf0ddaf456e50b3c0f569efd1152d6b338a916494ba26ad01c70fd82612",
                                     new URI("turn:global.turn.twilio.com:3478?transport=udp")));
        assertEquals(iceServers, instance.getIceServers());
    }
}
