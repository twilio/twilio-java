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

public class MessageTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"body\": \"hello\", \"date_created\": \"Wed, 19 Nov 2014 20:50:25 +0000\", \"date_sent\": \"Wed, 19 Nov 2014 20:50:33 +0000\", \"date_updated\": \"Wed, 19 Nov 2014 20:50:33 +0000\", \"direction\": \"outbound-api\", \"error_code\": 30008, \"error_message\": \"Unknown error\", \"from\": \"+14155552345\", \"num_media\": \"0\", \"num_segments\": \"1\", \"price\": null, \"price_unit\": \"USD\", \"sid\": \"SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"status\": \"failed\", \"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"}, \"to\": \"+19197404420\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/Messages.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/Messages.json?PageSize=50&Page=0\", \"next_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/Messages.json?PageSize=50&Page=0&PageToken=PASM27bf146fd432986de7b982c27fa3e6c4\", \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"sms_messages\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"body\": \"hello\", \"date_created\": \"Fri, 21 Nov 2014 02:50:33 +0000\", \"date_sent\": \"Fri, 21 Nov 2014 02:50:41 +0000\", \"date_updated\": \"Fri, 21 Nov 2014 02:50:41 +0000\", \"direction\": \"outbound-api\", \"from\": \"+14155552345\", \"num_segments\": \"1\", \"price\": null, \"price_unit\": \"USD\", \"sid\": \"SMe26e14836b1c626c2f9ea0a93b848f99\", \"status\": \"failed\", \"to\": \"+19197404420\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/Messages/SMe26e14836b1c626c2f9ea0a93b848f99.json\"}], \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/Messages.json?PageSize=50&Page=0\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Messages/sid.json".replace("{AccountSid}",
                                                                                                         "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.delete("sid")
               .execute();
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Messages/sid.json".replace("{AccountSid}",
                                                                                                         "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.fetch("sid")
               .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message instance = Message.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                          .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Messages.json".replace("{AccountSid}",
                                                                                                     "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.list()
               .execute();
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Messages/sid.json".replace("{AccountSid}",
                                                                                                         "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.update("sid")
               .execute();
    }
}
