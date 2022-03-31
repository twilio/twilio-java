/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1;

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

public class ParticipantConversationTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/ParticipantConversations");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            ParticipantConversation.reader().read();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadEmptyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"conversations\": [],\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://conversations.twilio.com/v1/ParticipantConversations?Identity=identity&PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://conversations.twilio.com/v1/ParticipantConversations?Identity=identity&PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"conversations\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(ParticipantConversation.reader().read());
    }

    @Test
    public void testReadFullByIdentityResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"conversations\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"chat_service_sid\": \"ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_friendly_name\": \"friendly_name\",\"conversation_state\": \"inactive\",\"conversation_timers\": {\"date_inactive\": \"2015-12-16T22:19:38Z\",\"date_closed\": \"2015-12-16T22:28:38Z\"},\"conversation_attributes\": \"{}\",\"conversation_date_created\": \"2015-07-30T20:00:00Z\",\"conversation_date_updated\": \"2015-07-30T20:00:00Z\",\"conversation_created_by\": \"created_by\",\"conversation_unique_name\": \"unique_name\",\"participant_user_sid\": \"USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"participant_identity\": \"identity\",\"participant_messaging_binding\": null,\"links\": {\"participant\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}}],\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://conversations.twilio.com/v1/ParticipantConversations?Identity=identity&PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://conversations.twilio.com/v1/ParticipantConversations?Identity=identity&PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"conversations\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(ParticipantConversation.reader().read());
    }

    @Test
    public void testReadFullByAddressResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"conversations\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"chat_service_sid\": \"ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_friendly_name\": \"friendly_name\",\"conversation_state\": \"inactive\",\"conversation_timers\": {\"date_inactive\": \"2015-12-16T22:19:38Z\",\"date_closed\": \"2015-12-16T22:28:38Z\"},\"conversation_attributes\": \"{}\",\"conversation_date_created\": \"2015-07-30T20:00:00Z\",\"conversation_date_updated\": \"2015-07-30T20:00:00Z\",\"conversation_created_by\": \"created_by\",\"conversation_unique_name\": \"unique_name\",\"participant_user_sid\": null,\"participant_identity\": null,\"participant_messaging_binding\": {\"address\": \"+375255555555\",\"proxy_address\": \"+12345678910\",\"type\": \"sms\",\"level\": null,\"name\": null,\"projected_address\": null},\"links\": {\"participant\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}}],\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://conversations.twilio.com/v1/ParticipantConversations?Address=%2B375255555555&PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://conversations.twilio.com/v1/ParticipantConversations?Address=%2B375255555555&PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"conversations\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(ParticipantConversation.reader().read());
    }
}