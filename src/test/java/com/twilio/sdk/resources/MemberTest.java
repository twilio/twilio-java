package com.twilio.sdk.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.assertNotNull;

public class MemberTest {

    private static final String INSTANCE_JSON_RESPONSE = "{ \"call_sid\": \"CA579818d8659efe7a6c549a2d55c49d01\", \"date_enqueued\": \"Tue, 25 Nov 2014 23:11:59 +0000\", \"position\": 1, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Queues/QU60fc03977b1f6376bcf0b3cd9988914a/Members/CA579818d8659efe7a6c549a2d55c49d01.json\", \"wait_time\": 148}";

    private static final String LIST_JSON_RESPONSE = "{ \"end\": 1, \"first_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Queues/QU60fc03977b1f6376bcf0b3cd9988914a/Members.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Queues/QU60fc03977b1f6376bcf0b3cd9988914a/Members.json?PageSize=50&Page=0\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"queue_members\": [ { \"call_sid\": \"CA579818d8659efe7a6c549a2d55c49d01\", \"date_enqueued\": \"Tue, 25 Nov 2014 23:11:59 +0000\", \"position\": 1, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Queues/QU60fc03977b1f6376bcf0b3cd9988914a/Members/CA579818d8659efe7a6c549a2d55c49d01.json\", \"wait_time\": 140 } ], \"start\": 0, \"total\": 3, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Queues/QU60fc03977b1f6376bcf0b3cd9988914a/Members.json?PageSize=50&Page=0\"}";

    @Mocked
    private Request request;

    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Queues/queueSid/Members/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Member.fetch("queueSid", "sid").execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Member instance = Member.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Queues/queueSid/Members.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Member.read("queueSid").execute();
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Queues/queueSid/Member/sid.json";
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Member.update("queueSid", "sid", new URI("http://foo.com")).execute();
    }
}
