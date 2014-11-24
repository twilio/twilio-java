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

public class ApplicationTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"date_created\": \"Mon, 22 Aug 2011 20:59:45 +0000\", \"date_updated\": \"Wed, 19 Nov 2014 17:50:04 +0000\", \"friendly_name\": \"Application Friendly Name\", \"message_status_callback\": \"http://www.example.com/sms-status-callback\", \"sid\": \"APaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"sms_fallback_method\": \"GET\", \"sms_fallback_url\": \"http://www.example.com/sms-fallback\", \"sms_method\": \"GET\", \"sms_status_callback\": \"http://www.example.com/sms-status-callback\", \"sms_url\": \"http://example.com\", \"status_callback\": \"http://example.com\", \"status_callback_method\": \"GET\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Applications/APaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\", \"voice_caller_id_lookup\": false, \"voice_fallback_method\": \"GET\", \"voice_fallback_url\": \"http://www.example.com/voice-callback\", \"voice_method\": \"GET\", \"voice_url\": \"http://example.com\"}";

    private static final String LIST_JSON_RESPONSE = "{\"applications\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"date_created\": \"Wed, 19 Mar 2014 19:46:59 +0000\", \"date_updated\": \"Wed, 19 Mar 2014 19:46:59 +0000\", \"friendly_name\": null, \"message_status_callback\": null, \"sid\": \"AP502ed11d06de4ff69340055f37af77de\", \"sms_fallback_method\": \"POST\", \"sms_fallback_url\": null, \"sms_method\": \"POST\", \"sms_status_callback\": null, \"sms_url\": null, \"status_callback\": null, \"status_callback_method\": \"POST\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Applications/AP502ed11d06de4ff69340055f37af77de.json\", \"voice_caller_id_lookup\": false, \"voice_fallback_method\": \"POST\", \"voice_fallback_url\": null, \"voice_method\": \"POST\", \"voice_url\": null}], \"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Applications.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Applications.json?PageSize=50&Page=0\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Applications.json?PageSize=50&Page=0\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Applications/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Application.delete("sid")
                   .execute();
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Applications/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Application.fetch("sid")
                   .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Application instance = Application.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                  .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Applications.json".replace("{AccountSid}",
                                                                                                         "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Application.list()
                   .execute();
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Applications/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Application.update("sid")
                   .execute();
    }
}
