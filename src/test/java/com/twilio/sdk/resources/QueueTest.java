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

import static org.junit.Assert.assertNotNull;

public class QueueTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"average_wait_time\": 0, \"current_size\": 0, \"date_created\": \"Wed, 16 Apr 2014 00:48:14 +0000\", \"date_updated\": \"Wed, 16 Apr 2014 00:48:14 +0000\", \"friendly_name\": \"4cfb5265-312e-4db1-8\", \"max_size\": 1000, \"sid\": \"QUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Queues/QUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Queues.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Queues.json?PageSize=50&Page=0\", \"next_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Queues.json?PageSize=50&Page=0&PageToken=PAQUb0d74108ad454ed3b0c6067c3ea2d5d8\", \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"queues\": [{\"average_wait_time\": 0, \"current_size\": 0, \"date_created\": \"Fri, 21 Nov 2014 02:49:58 +0000\", \"date_updated\": \"Fri, 21 Nov 2014 02:49:58 +0000\", \"friendly_name\": \"35e0fb9e-1e77-4eda-8056-944ac7368a78\", \"max_size\": 100, \"sid\": \"QUca6de44eb1e24d06a5369221011942c5\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Queues/QUca6de44eb1e24d06a5369221011942c5.json\"}], \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Queues.json?PageSize=50&Page=0\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Queues/sid.json".replace("{AccountSid}",
                                                                                                       "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Queue.fetch("sid")
             .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Queue instance = Queue.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                      .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Queues.json".replace("{AccountSid}",
                                                                                                   "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Queue.list()
             .execute();
    }
}
