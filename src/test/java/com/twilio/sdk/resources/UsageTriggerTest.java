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

public class UsageTriggerTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"callback_method\": \"GET\", \"callback_url\": \"http://cap.com/streetfight\", \"current_value\": \"0\", \"date_created\": \"Mon, 01 Dec 2014 14:17:45 +0000\", \"date_fired\": null, \"date_updated\": \"Mon, 01 Dec 2014 14:17:45 +0000\", \"friendly_name\": \"test\", \"recurring\": \"yearly\", \"sid\": \"UTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"trigger_by\": \"price\", \"trigger_value\": \"50\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Triggers/UTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"usage_category\": \"totalprice\", \"usage_record_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records?Category=totalprice\"}";

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Triggers?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Triggers?PageSize=50&Page=0\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Triggers\", \"usage_triggers\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"callback_method\": \"GET\", \"callback_url\": \"http://cap.com/streetfight\", \"current_value\": \"0\", \"date_created\": \"Mon, 01 Dec 2014 14:17:45 +0000\", \"date_fired\": null, \"date_updated\": \"Mon, 01 Dec 2014 14:17:45 +0000\", \"friendly_name\": \"test\", \"recurring\": \"yearly\", \"sid\": \"UTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"trigger_by\": \"price\", \"trigger_value\": \"50\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Triggers/Uaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"usage_category\": \"totalprice\", \"usage_record_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records?Category=totalprice\"}]}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Usage/Triggers/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        UsageTrigger.delete("sid")
                    .execute();
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Usage/Triggers/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        UsageTrigger.fetch("sid")
                    .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        UsageTrigger instance = UsageTrigger.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                    .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Usage/Triggers.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        UsageTrigger.list()
                    .execute();
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Usage/Triggers/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        UsageTrigger.update("sid")
                    .execute();
    }
}
