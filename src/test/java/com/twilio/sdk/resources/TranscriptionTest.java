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

public class TranscriptionTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2008-08-01\", \"date_created\": \"Tue, 23 Aug 2011 20:58:45 +0000\", \"date_updated\": \"Tue, 23 Aug 2011 20:58:45 +0000\", \"duration\": \"10\", \"price\": \"0.00000\", \"price_unit\": \"USD\", \"recording_sid\": \"REaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"sid\": \"TRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"status\": \"in-progress\", \"transcription_text\": null, \"type\": \"fast\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Transcriptions/TRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Transcriptions.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Transcriptions.json?PageSize=50&Page=0\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"transcriptions\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2008-08-01\", \"date_created\": \"Mon, 22 Aug 2011 20:58:44 +0000\", \"date_updated\": \"Mon, 22 Aug 2011 20:58:44 +0000\", \"duration\": \"10\", \"price\": \"0.00000\", \"price_unit\": \"USD\", \"recording_sid\": \"REaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"sid\": \"TRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"status\": \"in-progress\", \"transcription_text\": \"THIS IS A TEST\", \"type\": \"fast\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Transcriptions/TRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}], \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Transcriptions.json?PageSize=50&Page=0\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Transcriptions/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Transcription.fetch("sid")
                     .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Transcription instance = Transcription.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                      .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Transcriptions.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Transcription.list()
                     .execute();
    }
}
