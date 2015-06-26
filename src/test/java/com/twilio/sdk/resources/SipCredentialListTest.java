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

public class SipCredentialListTest {

    private static final String INSTANCE_JSON_RESPONSE = "{ \"subresource_uris\": { \"credentials\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/CredentialLists/CL1e9949149f055138a8c215fb7ccd5b64/Credentials.json\" }, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/CredentialLists/CL1e9949149f055138a8c215fb7ccd5b64.json\", \"date_updated\": \"Wed, 11 Sep 2013 17:51:38 +0000\", \"date_created\": \"Wed, 11 Sep 2013 17:51:38 +0000\", \"friendly_name\": \"Low Rises\", \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"sid\": \"CL1e9949149f055138a8c215fb7ccd5b64\" }";

    private static final String LIST_JSON_RESPONSE = "{ \"page\": 0, \"last_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/CredentialLists.json?PageSize=50&Page=0\", \"total\": 1, \"num_pages\": 1, \"first_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/CredentialLists.json?PageSize=50&Page=0\", \"end\": 0, \"credential_lists\": [ { \"subresource_uris\": { \"credentials\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/CredentialLists/CL1e9949149f055138a8c215fb7ccd5b64/Credentials.json\" }, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/CredentialLists/CL1e9949149f055138a8c215fb7ccd5b64.json\", \"date_updated\": \"Wed, 11 Sep 2013 17:51:38 +0000\", \"date_created\": \"Wed, 11 Sep 2013 17:51:38 +0000\", \"friendly_name\": \"Low Rises\", \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"sid\": \"CL1e9949149f055138a8c215fb7ccd5b64\" } ], \"previous_page_uri\": null, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/CredentialLists.json?PageSize=50&Page=0\", \"page_size\": 50, \"start\": 0, \"next_page_uri\": null }";

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
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/CredentialLists/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipCredentialList.delete("sid").execute();
    }

    
    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/CredentialLists/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipCredentialList.fetch("sid").execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        SipCredentialList instance = SipCredentialList.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient().getObjectMapper());
        assertNotNull(instance);
    }

    
    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/SIP/CredentialLists.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipCredentialList.read().execute();
    }

    
    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/CredentialLists/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipCredentialList.update("sid")
            .execute();
    }
}