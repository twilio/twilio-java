/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.TwilioException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static com.twilio.TwilioTest.serialize;
import static org.junit.Assert.*;

public class MessageTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testCreateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.API.toString(),
                                          "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages.json");
            request.addPostParam("To", serialize(new com.twilio.type.PhoneNumber("+15558675310")));
            request.addPostParam("From", serialize(new com.twilio.type.PhoneNumber("+15017122661")));
            request.addPostParam("Body", serialize("body"));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15558675310"), new com.twilio.type.PhoneNumber("+15017122661"), "body").create();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"Hello! \\ud83d\\udc4d\",\"date_created\": \"Thu, 30 Jul 2015 20:12:31 +0000\",\"date_sent\": \"Thu, 30 Jul 2015 20:12:33 +0000\",\"date_updated\": \"Thu, 30 Jul 2015 20:12:33 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": \"+14155552345\",\"messaging_service_sid\": \"MGaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"num_media\": \"0\",\"num_segments\": \"1\",\"price\": null,\"price_unit\": null,\"sid\": \"SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"sent\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"},\"to\": \"+14155552345\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15558675310"), new com.twilio.type.PhoneNumber("+15017122661"), "body").create();
    }

    @Test
    public void testCreateWoServiceResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"Hello! \\ud83d\\udc4d\",\"date_created\": \"Thu, 30 Jul 2015 20:12:31 +0000\",\"date_sent\": \"Thu, 30 Jul 2015 20:12:33 +0000\",\"date_updated\": \"Thu, 30 Jul 2015 20:12:33 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": \"+14155552345\",\"messaging_service_sid\": null,\"num_media\": \"0\",\"num_segments\": \"1\",\"price\": null,\"price_unit\": null,\"sid\": \"SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"sent\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"},\"to\": \"+14155552345\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15558675310"), new com.twilio.type.PhoneNumber("+15017122661"), "body").create();
    }

    @Test
    public void testCreateWithMessagingServiceResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"Hello! \\ud83d\\udc4d\",\"date_created\": \"Thu, 30 Jul 2015 20:12:31 +0000\",\"date_sent\": \"Thu, 30 Jul 2015 20:12:33 +0000\",\"date_updated\": \"Thu, 30 Jul 2015 20:12:33 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": null,\"messaging_service_sid\": \"MGaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"num_media\": \"0\",\"num_segments\": \"1\",\"price\": null,\"price_unit\": null,\"sid\": \"SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"sent\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"},\"to\": \"+14155552345\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15558675310"), new com.twilio.type.PhoneNumber("+15017122661"), "body").create();
    }

    @Test
    public void testCreateWithQueuedStatusResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"Hello! \\ud83d\\udc4d\",\"date_created\": \"Thu, 30 Jul 2015 20:12:31 +0000\",\"date_sent\": \"Thu, 30 Jul 2015 20:12:33 +0000\",\"date_updated\": \"Thu, 30 Jul 2015 20:12:33 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": \"+14155552345\",\"messaging_service_sid\": \"MGaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"num_media\": \"0\",\"num_segments\": \"1\",\"price\": null,\"price_unit\": null,\"sid\": \"SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"queued\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"},\"to\": \"+14155552345\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15558675310"), new com.twilio.type.PhoneNumber("+15017122661"), "body").create();
    }

    @Test
    public void testCreateScheduledMessageSmsResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"Hello! \\ud83d\\udc4d\",\"date_created\": \"Mon, 29 Nov 2021 22:40:10 +0000\",\"date_sent\": null,\"date_updated\": \"Mon, 29 Nov 2021 22:40:10 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": null,\"messaging_service_sid\": \"MGaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"num_media\": \"0\",\"num_segments\": \"0\",\"price\": null,\"price_unit\": null,\"sid\": \"SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"scheduled\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"},\"to\": \"+15558675310\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15558675310"), new com.twilio.type.PhoneNumber("+15017122661"), "body").create();
    }

    @Test
    public void testCreateScheduledMessageMmsResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"Hello! \\ud83d\\udc4d\",\"date_created\": \"Mon, 29 Nov 2021 22:40:10 +0000\",\"date_sent\": null,\"date_updated\": \"Mon, 29 Nov 2021 22:40:10 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": null,\"messaging_service_sid\": \"MGaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"num_media\": \"1\",\"num_segments\": \"1\",\"price\": null,\"price_unit\": null,\"sid\": \"MMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"scheduled\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/MMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"},\"to\": \"+15558675310\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/MMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15558675310"), new com.twilio.type.PhoneNumber("+15017122661"), "body").create();
    }

    @Test
    public void testCreateScheduledMessageWhatsappResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"Hello! \\ud83d\\udc4d\",\"date_created\": \"Mon, 29 Nov 2021 22:40:10 +0000\",\"date_sent\": null,\"date_updated\": \"Mon, 29 Nov 2021 22:40:10 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": null,\"messaging_service_sid\": \"MGaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"num_media\": \"0\",\"num_segments\": \"0\",\"price\": null,\"price_unit\": null,\"sid\": \"SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"scheduled\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\"},\"to\": \"whatsapp:+15558675310\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15558675310"), new com.twilio.type.PhoneNumber("+15017122661"), "body").create();
    }

    @Test
    public void testDeleteRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.DELETE,
                                          Domains.API.toString(),
                                          "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.json");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.deleter("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testDeleteResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("null", TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.deleter("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete();
    }

    @Test
    public void testFetchRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.API.toString(),
                                          "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.json");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.fetcher("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testFetchResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"testing\",\"date_created\": \"Fri, 24 May 2019 17:18:27 +0000\",\"date_sent\": \"Fri, 24 May 2019 17:18:28 +0000\",\"date_updated\": \"Fri, 24 May 2019 17:18:28 +0000\",\"direction\": \"outbound-api\",\"error_code\": 30007,\"error_message\": \"Carrier violation\",\"from\": \"+12019235161\",\"messaging_service_sid\": \"MGdeadbeefdeadbeefdeadbeefdeadbeef\",\"num_media\": \"0\",\"num_segments\": \"1\",\"price\": \"-0.00750\",\"price_unit\": \"USD\",\"sid\": \"SMb7c0a2ce80504485a6f653a7110836f5\",\"status\": \"sent\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5/Media.json\",\"feedback\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5/Feedback.json\"},\"to\": \"+18182008801\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5.json\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.fetcher("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch());
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.API.toString(),
                                          "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages.json");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadFullPage1Response() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 1,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=2008-01-02&PageSize=2&Page=0\",\"next_page_uri\": null,\"page\": 0,\"page_size\": 2,\"previous_page_uri\": null,\"messages\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"testing\",\"date_created\": \"Fri, 24 May 2019 17:44:46 +0000\",\"date_sent\": \"Fri, 24 May 2019 17:44:50 +0000\",\"date_updated\": \"Fri, 24 May 2019 17:44:50 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": \"+12019235161\",\"messaging_service_sid\": null,\"num_media\": \"0\",\"num_segments\": \"1\",\"price\": \"-0.00750\",\"price_unit\": \"USD\",\"sid\": \"SMded05904ccb347238880ca9264e8fe1c\",\"status\": \"sent\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMded05904ccb347238880ca9264e8fe1c/Media.json\",\"feedback\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMded05904ccb347238880ca9264e8fe1c/Feedback.json\"},\"to\": \"+18182008801\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMded05904ccb347238880ca9264e8fe1c.json\"},{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"look mom I have media!\",\"date_created\": \"Fri, 24 May 2019 17:44:46 +0000\",\"date_sent\": \"Fri, 24 May 2019 17:44:49 +0000\",\"date_updated\": \"Fri, 24 May 2019 17:44:49 +0000\",\"direction\": \"inbound\",\"error_code\": 30004,\"error_message\": \"Message blocked\",\"from\": \"+12019235161\",\"messaging_service_sid\": null,\"num_media\": \"3\",\"num_segments\": \"1\",\"price\": \"-0.00750\",\"price_unit\": \"USD\",\"sid\": \"MMc26223853f8c46b4ab7dfaa6abba0a26\",\"status\": \"received\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/MMc26223853f8c46b4ab7dfaa6abba0a26/Media.json\",\"feedback\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/MMc26223853f8c46b4ab7dfaa6abba0a26/Feedback.json\"},\"to\": \"+18182008801\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/MMc26223853f8c46b4ab7dfaa6abba0a26.json\"}],\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=2008-01-02&PageSize=2&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadEmptySentdateLessResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3C=2008-01-02&PageSize=25&Page=0\",\"next_page_uri\": null,\"page\": 0,\"page_size\": 25,\"previous_page_uri\": null,\"messages\": [],\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3C=2008-01-02&PageSize=25&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadEmptySentdateEqualsResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent=2008-01-02&PageSize=25&Page=0\",\"next_page_uri\": null,\"page\": 0,\"page_size\": 25,\"previous_page_uri\": null,\"messages\": [],\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent=2008-01-02&PageSize=25&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadEmptySentdateGreaterResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=2008-01-02&PageSize=25&Page=0\",\"next_page_uri\": null,\"page\": 0,\"page_size\": 25,\"previous_page_uri\": null,\"messages\": [],\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=2008-01-02&PageSize=25&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadEmptySentdateGreaterFormat1Response() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=06%2F11%2F2019+22%3A05%3A25+MST&PageSize=25&Page=0\",\"next_page_uri\": null,\"page\": 0,\"page_size\": 25,\"previous_page_uri\": null,\"messages\": [],\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=06%2F11%2F2019+22%3A05%3A25+MST&PageSize=25&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadEmptySentdateGreaterFormat2Response() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=2019-06-11+22%3A05%3A25.000&PageSize=25&Page=0\",\"next_page_uri\": null,\"page\": 0,\"page_size\": 25,\"previous_page_uri\": null,\"messages\": [],\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=2019-06-11+22%3A05%3A25.000&PageSize=25&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadEmptySentdateGreaterFormat3Response() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=Wed%2C+19+Jun+2019+22%3A04%3A00+-0000&PageSize=25&Page=0\",\"next_page_uri\": null,\"page\": 0,\"page_size\": 25,\"previous_page_uri\": null,\"messages\": [],\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json?To=%2B123456789&From=%2B987654321&DateSent%3E=Wed%2C+19+Jun+2019+22%3A04%3A00+-0000&PageSize=25&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testUpdateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.API.toString(),
                                          "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.json");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.updater("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testRedactBodyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"\",\"date_created\": \"Fri, 24 May 2019 17:18:27 +0000\",\"date_sent\": \"Fri, 24 May 2019 17:18:28 +0000\",\"date_updated\": \"Fri, 24 May 2019 17:18:28 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": \"+12019235161\",\"messaging_service_sid\": \"MGdeadbeefdeadbeefdeadbeefdeadbeef\",\"num_media\": \"0\",\"num_segments\": \"1\",\"price\": null,\"price_unit\": \"USD\",\"sid\": \"SMb7c0a2ce80504485a6f653a7110836f5\",\"status\": \"sent\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5/Media.json\",\"feedback\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5/Feedback.json\"},\"to\": \"+18182008801\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5.json\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.updater("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update();
    }

    @Test
    public void testCancelMessageResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"body\": \"Hello World!\",\"date_created\": \"Fri, 24 May 2019 17:18:27 +0000\",\"date_sent\": null,\"date_updated\": \"Fri, 24 May 2019 18:18:28 +0000\",\"direction\": \"outbound-api\",\"error_code\": null,\"error_message\": null,\"from\": null,\"messaging_service_sid\": \"MGdeadbeefdeadbeefdeadbeefdeadbeef\",\"num_media\": \"0\",\"num_segments\": \"1\",\"price\": null,\"price_unit\": \"USD\",\"sid\": \"SMb7c0a2ce80504485a6f653a7110836f5\",\"status\": \"canceled\",\"subresource_uris\": {\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5/Media.json\",\"feedback\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5/Feedback.json\"},\"to\": \"+18182008801\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/SMb7c0a2ce80504485a6f653a7110836f5.json\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.updater("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update();
    }
}