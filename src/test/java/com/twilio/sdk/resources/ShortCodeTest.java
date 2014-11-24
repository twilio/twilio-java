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

public class ShortCodeTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"date_created\": \"Mon, 22 Aug 2011 20:58:45 +0000\", \"date_updated\": \"Mon, 22 Aug 2011 20:58:45 +0000\", \"friendly_name\": \"TEST_SHORT_CODE\", \"short_code\": \"99990\", \"sid\": \"SCaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"sms_fallback_method\": \"POST\", \"sms_fallback_url\": null, \"sms_method\": \"POST\", \"sms_url\": null, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/ShortCodes/SCaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/ShortCodes.json?Page=0&PageSize=50\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/ShortCodes.json?Page=0&PageSize=50\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"short_codes\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"date_created\": \"Mon, 22 Aug 2011 20:58:45 +0000\", \"date_updated\": \"Mon, 20 Nov 8226 22:16:53 +0000\", \"friendly_name\": \"TEST_SHORT_CODE\", \"short_code\": \"99990\", \"sid\": \"SCaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"sms_fallback_method\": \"POST\", \"sms_fallback_url\": null, \"sms_method\": \"POST\", \"sms_url\": null, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/ShortCodes/SCaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}], \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/ShortCodes.json\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/SMS/ShortCodes/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        ShortCode.fetch("sid")
                 .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        ShortCode instance = ShortCode.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                              .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/SMS/ShortCodes.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        ShortCode.list()
                 .execute();
    }
}
