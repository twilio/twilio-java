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

public class SipIpAccessControlListTest {

    private static final String INSTANCE_JSON_RESPONSE = "{ \"subresource_uris\": { \"ip_addresses\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists/AL9b2dac9980134c733b935af7ecf792f8/IpAddresses.json\" }, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists/AL9b2dac9980134c733b935af7ecf792f8.json\", \"date_updated\": \"Thu, 12 Sep 2013 20:56:26 +0000\", \"date_created\": \"Thu, 12 Sep 2013 20:56:26 +0000\", \"friendly_name\": \"HerptyDerp\", \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"sid\": \"AL9b2dac9980134c733b935af7ecf792f8\" }";

    private static final String LIST_JSON_RESPONSE = "{ \"page\": 0, \"last_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists.json?PageSize=50&Page=0\", \"ip_access_control_lists\": [ { \"subresource_uris\": { \"ip_addresses\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists/AL0c37d0be69a6a6fe1e270c5fa4a2cac7/IpAddresses.json\" }, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists/AL0c37d0be69a6a6fe1e270c5fa4a2cac7.json\", \"date_updated\": \"Thu, 12 Sep 2013 22:37:01 +0000\", \"date_created\": \"Wed, 11 Sep 2013 04:06:07 +0000\", \"friendly_name\": \"Avons Lieutenants\", \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"sid\": \"AL0c37d0be69a6a6fe1e270c5fa4a2cac7\" }, { \"subresource_uris\": { \"ip_addresses\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists/AL9b2dac9980134c733b935af7ecf792f8/IpAddresses.json\" }, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists/AL9b2dac9980134c733b935af7ecf792f8.json\", \"date_updated\": \"Thu, 12 Sep 2013 20:56:26 +0000\", \"date_created\": \"Thu, 12 Sep 2013 20:56:26 +0000\", \"friendly_name\": \"HerptyDerp\", \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"sid\": \"AL9b2dac9980134c733b935af7ecf792f8\" } ], \"num_pages\": 1, \"first_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists.json?PageSize=50&Page=0\", \"total\": 2, \"end\": 1, \"previous_page_uri\": null, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/IpAccessControlLists.json?PageSize=50&Page=0\", \"page_size\": 50, \"start\": 0, \"next_page_uri\": null }";

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
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/IpAccessControlLists/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipIpAccessControlList.delete("sid").execute();
    }

    
    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/IpAccessControlLists/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipIpAccessControlList.fetch("sid").execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        SipIpAccessControlList instance = SipIpAccessControlList.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient().getObjectMapper());
        assertNotNull(instance);
    }

    
    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/SIP/IpAccessControlLists.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipIpAccessControlList.list().execute();
    }

    
    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/IpAccessControlLists/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipIpAccessControlList.update("sid")
            .execute();
    }
}