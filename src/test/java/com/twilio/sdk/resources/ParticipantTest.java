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

public class ParticipantTest {

    private static final String INSTANCE_JSON_RESPONSE = "{ \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"call_sid\": \"CA63e983f00d142a8ff86359566dda9bc3\", \"conference_sid\": \"CFff5f39624aa488e7b819ed4fa08f2ff2\", \"date_created\": \"Wed, 26 Nov 2014 17:55:55 +0000\", \"date_updated\": \"Wed, 26 Nov 2014 17:55:55 +0000\", \"end_conference_on_exit\": false, \"muted\": false, \"start_conference_on_enter\": true, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Conferences/CFff5f39624aa488e7b819ed4fa08f2ff2/Participants/CA63e983f00d142a8ff86359566dda9bc3.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\n" +
                                                     "    \"end\": 0, \n" +
                                                     "    \"first_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Conferences/CFff5f39624aa488e7b819ed4fa08f2ff2/Participants.json?Page=0&PageSize=50\", \n" +
                                                     "    \"last_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Conferences/CFff5f39624aa488e7b819ed4fa08f2ff2/Participants.json?Page=0&PageSize=50\", \n" +
                                                     "    \"next_page_uri\": null, \n" +
                                                     "    \"num_pages\": 1, \n" +
                                                     "    \"page\": 0, \n" +
                                                     "    \"page_size\": 50, \n" +
                                                     "    \"participants\": [\n" +
                                                     "        {\n" +
                                                     "            \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \n" +
                                                     "            \"call_sid\": \"CA63e983f00d142a8ff86359566dda9bc3\", \n" +
                                                     "            \"conference_sid\": \"CFff5f39624aa488e7b819ed4fa08f2ff2\", \n" +
                                                     "            \"date_created\": \"Wed, 26 Nov 2014 17:55:55 +0000\", \n" +
                                                     "            \"date_updated\": \"Wed, 26 Nov 2014 17:55:55 +0000\", \n" +
                                                     "            \"end_conference_on_exit\": false, \n" +
                                                     "            \"muted\": false, \n" +
                                                     "            \"start_conference_on_enter\": true, \n" +
                                                     "            \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Conferences/CFff5f39624aa488e7b819ed4fa08f2ff2/Participants/CA63e983f00d142a8ff86359566dda9bc3.json\"\n" +
                                                     "        }\n" +
                                                     "    ], \n" +
                                                     "    \"previous_page_uri\": null, \n" +
                                                     "    \"start\": 0, \n" +
                                                     "    \"total\": 1, \n" +
                                                     "    \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Conferences/CFff5f39624aa488e7b819ed4fa08f2ff2/Participants.json\"\n" +
                                                     "}\n";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Conferences/conferenceSid/Participants/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Participant.fetch("conferenceSid", "sid")
                   .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Participant instance = Participant.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                  .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Conferences/conferenceSid/Participants.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Participant.list("conferenceSid")
                   .execute();
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Conferences/conferenceSid/Participants/sid.json";
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Participant.update("conferenceSid", "sid", true)
                   .execute();
    }
}
