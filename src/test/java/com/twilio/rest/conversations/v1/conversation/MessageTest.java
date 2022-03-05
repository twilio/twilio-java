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

public class MessageTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Mocked
    private Twilio tw;

    @Before
    public void setUp() throws Exception {
        tw = new Twilio("AC123", "AUTH TOKEN");
    }

    @Test
    public void testCreateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages");
            request.addHeaderParam("X-Twilio-Webhook-Enabled", serialize(Message.WebhookEnabledType.TRUE));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.creator("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").setXTwilioWebhookEnabled(Message.WebhookEnabledType.TRUE).create(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": \"Hello\",\"media\": null,\"author\": \"message author\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"attributes\": \"{ \\\"importance\\\": \\\"high\\\" }\",\"date_created\": \"2015-12-16T22:18:37Z\",\"date_updated\": \"2015-12-16T22:18:38Z\",\"index\": 0,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").setXTwilioWebhookEnabled(Message.WebhookEnabledType.TRUE).create(tw);
    }

    @Test
    public void testCreateWithMediaResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": null,\"media\": [{\"sid\": \"MEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"size\": 42056,\"content_type\": \"image/jpeg\",\"filename\": \"car.jpg\"}],\"author\": \"message author\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"attributes\": \"{ \\\"importance\\\": \\\"high\\\" }\",\"date_created\": \"2015-12-16T22:18:37Z\",\"date_updated\": \"2015-12-16T22:18:38Z\",\"index\": 0,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").setXTwilioWebhookEnabled(Message.WebhookEnabledType.TRUE).create(tw);
    }

    @Test
    public void testCreateNoAttributesResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": \"Hello\",\"media\": null,\"author\": \"message author\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"attributes\": \"{}\",\"date_created\": \"2020-07-01T22:18:37Z\",\"date_updated\": \"2020-07-01T22:18:37Z\",\"index\": 0,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.creator("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").setXTwilioWebhookEnabled(Message.WebhookEnabledType.TRUE).create(tw);
    }

    @Test
    public void testUpdateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            request.addHeaderParam("X-Twilio-Webhook-Enabled", serialize(Message.WebhookEnabledType.TRUE));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.updater("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").setXTwilioWebhookEnabled(Message.WebhookEnabledType.TRUE).update(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testUpdateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": \"Hello\",\"media\": null,\"author\": \"message author\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"attributes\": \"{ \\\"importance\\\": \\\"high\\\" }\",\"date_created\": \"2015-12-16T22:18:37Z\",\"date_updated\": \"2015-12-16T22:18:38Z\",\"index\": 0,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Message.updater("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").setXTwilioWebhookEnabled(Message.WebhookEnabledType.TRUE).update(tw);
    }

    @Test
    public void testDeleteRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.DELETE,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            request.addHeaderParam("X-Twilio-Webhook-Enabled", serialize(Message.WebhookEnabledType.TRUE));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.deleter("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").setXTwilioWebhookEnabled(Message.WebhookEnabledType.TRUE).delete(tw);
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

        Message.deleter("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").setXTwilioWebhookEnabled(Message.WebhookEnabledType.TRUE).delete(tw);
    }

    @Test
    public void testFetchRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.fetcher("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testFetchResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": \"Welcome!\",\"media\": null,\"author\": \"system\",\"participant_sid\": null,\"attributes\": \"{ \\\"importance\\\": \\\"high\\\" }\",\"date_created\": \"2016-03-24T20:37:57Z\",\"date_updated\": \"2016-03-24T20:37:57Z\",\"index\": 0,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.fetcher("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "IMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch(tw));
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.CONVERSATIONS.toString(),
                                          "/v1/Conversations/CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Message.reader("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadFullResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages?PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages?PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"messages\"},\"messages\": [{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": \"I like pie.\",\"media\": null,\"author\": \"pie_preferrer\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"attributes\": \"{ \\\"importance\\\": \\\"high\\\" }\",\"date_created\": \"2016-03-24T20:37:57Z\",\"date_updated\": \"2016-03-24T20:37:57Z\",\"index\": 0,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}},{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": \"Cake is my favorite!\",\"media\": null,\"author\": \"cake_lover\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"attributes\": \"{ \\\"importance\\\": \\\"high\\\" }\",\"date_created\": \"2016-03-24T20:38:21Z\",\"date_updated\": \"2016-03-24T20:38:21Z\",\"index\": 5,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}},{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": null,\"media\": [{\"sid\": \"MEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"size\": 42056,\"content_type\": \"image/jpeg\",\"filename\": \"car.jpg\"}],\"author\": \"cake_lover\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"attributes\": \"{ \\\"importance\\\": \\\"high\\\" }\",\"date_created\": \"2016-03-24T20:38:21Z\",\"date_updated\": \"2016-03-24T20:38:21Z\",\"index\": 9,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}}]}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read(tw));
    }

    @Test
    public void testReadLastMessageResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"meta\": {\"page\": 0,\"page_size\": 1,\"first_page_url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages?Order=desc&PageSize=1&Page=0\",\"previous_page_url\": null,\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages?Order=desc&PageSize=1&Page=0\",\"next_page_url\": null,\"key\": \"messages\"},\"messages\": [{\"sid\": \"IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"conversation_sid\": \"CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"body\": null,\"media\": [{\"sid\": \"MEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"size\": 42056,\"content_type\": \"image/jpeg\",\"filename\": \"car.jpg\"}],\"author\": \"cake_lover\",\"participant_sid\": \"MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"attributes\": \"{ \\\"importance\\\": \\\"high\\\" }\",\"date_created\": \"2016-03-24T20:38:21Z\",\"date_updated\": \"2016-03-24T20:38:21Z\",\"index\": 9,\"delivery\": {\"total\": 2,\"sent\": \"all\",\"delivered\": \"some\",\"read\": \"some\",\"failed\": \"none\",\"undelivered\": \"none\"},\"url\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"delivery_receipts\": \"https://conversations.twilio.com/v1/Conversations/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Receipts\"}}]}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Message.reader("CHXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read(tw));
    }
}