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

public class AccountTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"auth_token\": \"token\", \"date_created\": \"Mon, 19 May 2014 17:42:59 +0000\", \"date_updated\": \"Thu, 16 Oct 2014 18:01:39 +0000\", \"friendly_name\": \"Test Account\", \"owner_account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"status\": \"active\", \"subresource_uris\": { \"applications\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Applications.json\", \"authorized_connect_apps\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/AuthorizedConnectApps.json\", \"available_phone_numbers\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/AvailablePhoneNumbers.json\", \"calls\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls.json\", \"conferences\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Conferences.json\", \"connect_apps\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ConnectApps.json\", \"incoming_phone_numbers\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers.json\", \"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\", \"messages\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json\", \"notifications\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json\", \"outgoing_caller_ids\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/OutgoingCallerIds.json\", \"queues\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Queues.json\", \"recordings\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Recordings.json\", \"sandbox\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Sandbox.json\", \"sip\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SIP.json\", \"sms_messages\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/Messages.json\", \"transcriptions\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Transcriptions.json\", \"usage\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage.json\" }, \"type\": \"Full\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"accounts\": [{\"auth_token\": \"foobar\",\"date_created\": \"Mon, 22 Aug 2011 20:58:45 +0000\",\"date_updated\": \"Mon, 22 Aug 2011 20:58:45 +0000\",\"friendly_name\": \"Upgraded Account for API testing\",\"owner_account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"active\",\"subresource_uris\": {\"applications\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Applications.json\",\"authorized_connect_apps\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/AuthorizedConnectApps.json\",\"available_phone_numbers\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/AvailablePhoneNumbers.json\",\"calls\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls.json\",\"conferences\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Conferences.json\",\"connect_apps\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ConnectApps.json\",\"incoming_phone_numbers\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers.json\",\"media\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media.json\",\"messages\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages.json\",\"notifications\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json\",\"outgoing_caller_ids\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/OutgoingCallerIds.json\",\"queues\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Queues.json\",\"recordings\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Recordings.json\",\"sandbox\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Sandbox.json\",\"sip\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SIP.json\",\"sms_messages\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/SMS/Messages.json\",\"transcriptions\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Transcriptions.json\",\"usage\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage.json\"},\"type\": \"Full\",\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}],\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts.json?Page=0&PageSize=50\",\"last_page_uri\": \"/2010-04-01/Accounts.json?Page=0&PageSize=50\",\"next_page_uri\": null,\"num_pages\": 1,\"page\": 0,\"page_size\": 50,\"previous_page_uri\": null,\"start\": 0,\"total\": 1,\"uri\": \"/2010-04-01/Accounts.json\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Account.fetch("sid")
               .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Account instance = Account.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                          .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Account.read()
               .execute();
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Account.update("sid")
               .execute();
    }
}
