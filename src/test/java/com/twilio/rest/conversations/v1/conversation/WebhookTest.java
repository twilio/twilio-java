/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1.conversation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
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

import static com.twilio.TwilioTest.serialize;
import static org.junit.Assert.*;

public class WebhookTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Mocked
    private Twilio tw;

    @Before
    public void setUp() throws Exception {
        tw = new Twilio("AC123", "AUTH TOKEN");
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Webhooks");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Webhook.reader("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadFullResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"meta\": {\"page\": 0,\"page_size\": 5,\"first_page_url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks?PageSize=5&Page=0\",\"previous_page_url\": null,\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks?PageSize=5&Page=0\",\"next_page_url\": null,\"key\": \"webhooks\"},\"webhooks\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sid\": \"WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"target\": \"webhook\",\"configuration\": {\"url\": \"https://example.com\",\"method\": \"get\",\"filters\": [\"onMessageSent\",\"onConversationDestroyed\"]},\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks/WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"},{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sid\": \"WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"target\": \"trigger\",\"configuration\": {\"url\": \"https://example.com\",\"method\": \"post\",\"filters\": [\"keyword1\",\"keyword2\"]},\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks/WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"},{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sid\": \"WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"target\": \"studio\",\"configuration\": {\"flow_sid\": \"FWaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"},\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks/WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}]}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Webhook.reader("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read(tw));
    }

    @Test
    public void testReadEmptyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"meta\": {\"page\": 0,\"page_size\": 5,\"first_page_url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks?PageSize=5&Page=0\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks?PageSize=5&Page=0\",\"previous_page_url\": null,\"next_page_url\": null,\"key\": \"webhooks\"},\"webhooks\": []}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Webhook.reader("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read(tw));
    }

    @Test
    public void testFetchRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Webhooks/WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Webhook.fetcher("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testFetchResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sid\": \"WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"target\": \"studio\",\"configuration\": {\"flow_sid\": \"FWaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"},\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks/WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Webhook.fetcher("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch(tw));
    }

    @Test
    public void testCreateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Webhooks");
            request.addPostParam("Target", serialize(Webhook.Target.WEBHOOK));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Webhook.creator("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", Webhook.Target.WEBHOOK).create(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sid\": \"WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"target\": \"webhook\",\"configuration\": {\"url\": \"https://example.com\",\"method\": \"get\",\"filters\": [\"onMessageSent\",\"onConversationDestroyed\"]},\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:50Z\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks/WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Webhook.creator("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", Webhook.Target.WEBHOOK).create(tw);
    }

    @Test
    public void testUpdateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Webhooks/WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Webhook.updater("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testUpdateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sid\": \"WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"target\": \"trigger\",\"configuration\": {\"url\": \"https://example.com\",\"method\": \"post\",\"filters\": [\"keyword1\",\"keyword2\"]},\"date_created\": \"2016-03-24T21:05:50Z\",\"date_updated\": \"2016-03-24T21:05:51Z\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Webhooks/WHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Webhook.updater("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update(tw);
    }

    @Test
    public void testDeleteRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.DELETE,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Webhooks/WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Webhook.deleter("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete(tw);
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

        Webhook.deleter("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "WHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete(tw);
    }
}