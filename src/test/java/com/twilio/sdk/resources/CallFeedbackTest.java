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

public class CallFeedbackTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"date_created\": \"Tue, 25 Nov 2014 21:30:52 +0000\", \"date_updated\": \"Tue, 25 Nov 2014 21:35:49 +0000\", \"issues\": [\"imperfect-audio\"], \"quality_score\": 3, \"sid\": \"CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}";

    @Mocked
    private Request request;

    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testDelete() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("", TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT);
            request.constructURL();
            result = String.format("https://api.twilio.com/2010-04-01/Accounts/%s/Calls/%s/Feedback/sid.json", "AC123",
                                   "CA123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        CallFeedback.delete("CA123")
                    .execute();
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = String.format("https://api.twilio.com/2010-04-01/Accounts/%s/Calls/%s/Feedback/sid.json", "AC123",
                                   "CA123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        CallFeedback.fetch("CA123")
                    .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        CallFeedback instance = CallFeedback.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                    .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = String.format("https://api.twilio.com/2010-04-01/Accounts/%s/Calls/%s/Feedback/sid.json", "AC123",
                                   "CA123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        CallFeedback.update("CA123", 3)
                    .execute();
    }
}
