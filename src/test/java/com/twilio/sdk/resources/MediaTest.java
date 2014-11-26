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

public class MediaTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"body\": \"hello\", \"date_created\": \"Wed, 19 Nov 2014 20:50:25 +0000\", \"date_sent\": \"Wed, 19 Nov 2014 20:50:33 +0000\", \"date_updated\": \"Wed, 19 Nov 2014 20:50:33 +0000\", \"direction\": \"outbound-api\", \"error_code\": 30008, \"error_message\": \"Unknown error\", \"from\": \"+14155552345\", \"num_media\": \"0\", \"num_segments\": \"1\", \"price\": null, \"price_unit\": \"USD\", \"sid\": \"SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"status\": \"failed\", \"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"}, \"to\": \"+19197404420\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";
    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Messages/MMbe048e3c13a247859be1bae67d2dc48d/Media.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Messages/MMbe048e3c13a247859be1bae67d2dc48d/Media.json?PageSize=50&Page=0\", \"media_list\": [ { \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"content_type\": \"image/gif\", \"date_created\": \"Tue, 25 Nov 2014 19:25:25 +0000\", \"date_updated\": \"Tue, 25 Nov 2014 19:25:27 +0000\", \"parent_sid\": \"MMbe048e3c13a247859be1bae67d2dc48d\", \"sid\": \"ME8bbfa27fc02d4d8d7c45c47bbbcf145d\", \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Messages/MMbe048e3c13a247859be1bae67d2dc48d/Media/ME8bbfa27fc02d4d8d7c45c47bbbcf145d.json\" } ], \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/Messages/MMbe048e3c13a247859be1bae67d2dc48d/Media.json?PageSize=50&Page=0\"}";

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
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Messages/messageSid/Media/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        Media.delete("messageSid", "sid").execute();
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Messages/messageSid/Media/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        Media.fetch("messageSid", "sid").execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Media instance = Media.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient().getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Messages/messageSid/Media.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        Media.list("messageSid").execute();
    }
}