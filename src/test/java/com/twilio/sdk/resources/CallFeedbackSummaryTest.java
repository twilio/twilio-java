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

public class CallFeedbackSummaryTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"call_count\": 0, \"call_feedback_count\": 4, \"date_created\": \"Wed, 26 Nov 2014 17:32:24 +0000\", \"date_updated\": \"Wed, 26 Nov 2014 17:32:25 +0000\", \"end_date\": \"2014-11-26\", \"include_subaccounts\": false, \"issues\": [{\"count\": 1, \"description\": \"post-dial-delay\", \"percentage_of_total_calls\": \"0.0%\"}, {\"count\": 1, \"description\": \"imperfect-audio\", \"percentage_of_total_calls\": \"0.0%\"}], \"quality_score_average\": 3.0, \"quality_score_median\": 3.0, \"quality_score_standard_deviation\": 0.0, \"sid\": \"FSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"start_date\": \"2014-01-01\", \"status\": \"completed\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Calls/FeedbackSummary/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        CallFeedbackSummary.delete("sid")
                           .execute();
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Calls/FeedbackSummary/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        CallFeedbackSummary.fetch("sid")
                           .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        CallFeedbackSummary instance = CallFeedbackSummary.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                                  .getObjectMapper());
        assertNotNull(instance);
    }

}
