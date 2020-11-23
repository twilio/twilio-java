/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v1.service.channel;

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

public class MemberTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testFetchRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.IPMESSAGING.toString(),
                                          "/v1/Services/ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Channels/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Members/MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Member.fetcher("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testFetchResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"channel_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"service_sid\": \"ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"identity\": \"jing\",\"role_sid\": \"RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"last_consumed_message_index\": null,\"last_consumption_timestamp\": null,\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Member.fetcher("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch());
    }

    @Test
    public void testCreateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.IPMESSAGING.toString(),
                                          "/v1/Services/ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Channels/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Members");
            request.addPostParam("Identity", serialize("identity"));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Member.creator("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "identity").create();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"channel_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"service_sid\": \"ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"identity\": \"jing\",\"role_sid\": \"RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"last_consumed_message_index\": null,\"last_consumption_timestamp\": null,\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Member.creator("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "identity").create();
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.IPMESSAGING.toString(),
                                          "/v1/Services/ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Channels/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Members");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Member.reader("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadFullResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members?PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members?PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"members\"},\"members\": [{\"sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"channel_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"service_sid\": \"ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"identity\": \"jing\",\"role_sid\": \"RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"last_consumed_message_index\": null,\"last_consumption_timestamp\": null,\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}]}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Member.reader("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadEmptyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members?PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members?PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"members\"},\"members\": []}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Member.reader("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testDeleteRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.DELETE,
                                          Domains.IPMESSAGING.toString(),
                                          "/v1/Services/ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Channels/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Members/MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Member.deleter("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete();
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

        Member.deleter("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete();
    }

    @Test
    public void testUpdateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.IPMESSAGING.toString(),
                                          "/v1/Services/ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Channels/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Members/MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Member.updater("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testUpdateRoleSidResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"channel_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"service_sid\": \"ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"identity\": \"jing\",\"role_sid\": \"RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"last_consumed_message_index\": null,\"last_consumption_timestamp\": null,\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Member.updater("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update();
    }

    @Test
    public void testUpdateLastConsumedMessageIndexResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"channel_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"service_sid\": \"ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"identity\": \"jing\",\"role_sid\": \"RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"last_consumed_message_index\": 666,\"last_consumption_timestamp\": null,\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Member.updater("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "MBXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update();
    }
}