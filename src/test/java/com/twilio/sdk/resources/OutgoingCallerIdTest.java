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

public class OutgoingCallerIdTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"date_created\": \"Thu, 28 Mar 2013 03:32:03 +0000\", \"date_updated\": \"Thu, 28 Mar 2013 03:32:03 +0000\", \"friendly_name\": \"(410) 555-1234\", \"phone_number\": \"+14105551234\", \"sid\": \"PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/OutgoingCallerIds/PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/OutgoingCallerIds.json?Page=0&PageSize=50\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/OutgoingCallerIds.json?Page=0&PageSize=50\", \"next_page_uri\": null, \"num_pages\": 1, \"outgoing_caller_ids\": [{\"account_sid\": \"ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\", \"date_created\": \"Wed, 13 Oct 2010 20:46:51 +0000\", \"date_updated\": \"Fri, 21 Nov 2014 02:49:53 +0000\", \"friendly_name\": \"Caller ID\", \"phone_number\": \"+14155552348\", \"sid\": \"PNcccccccccccccccccccccccccccccccc\", \"uri\": \"/2010-04-01/Accounts/ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/OutgoingCallerIds/PNcccccccccccccccccccccccccccccccc.json\"}], \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/OutgoingCallerIds.json\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/OutgoingCallerIds/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        OutgoingCallerId.fetch("sid")
                        .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        OutgoingCallerId instance = OutgoingCallerId.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                            .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/OutgoingCallerIds.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        OutgoingCallerId.list()
                        .execute();
    }
}
