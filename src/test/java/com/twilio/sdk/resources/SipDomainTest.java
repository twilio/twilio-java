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

public class SipDomainTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"api_version\": \"2010-04-01\", \"auth_type\": \"\", \"date_created\": \"Fri, 06 Sep 2013 18:48:50 +0000\", \"date_updated\": \"Fri, 14 Nov 2014 18:31:35 +0000\", \"domain_name\": \"sam.sip.twilio.com\", \"friendly_name\": \"Sam's domain\", \"sid\": \"SD098e7b11c00d0ba152b1d3f084c4b776\", \"subresource_uris\": { \"credential_list_mappings\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains/SD098e7b11c00d0ba152b1d3f084c4b776/CredentialListMappings.json\", \"ip_access_control_list_mappings\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains/SD098e7b11c00d0ba152b1d3f084c4b776/IpAccessControlListMappings.json\" }, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains/SD098e7b11c00d0ba152b1d3f084c4b776.json\", \"voice_fallback_method\": \"POST\", \"voice_fallback_url\": null, \"voice_method\": \"POST\", \"voice_status_callback_method\": \"POST\", \"voice_status_callback_url\": null, \"voice_url\": null}";

    private static final String LIST_JSON_RESPONSE = "{\"page\": 0, \"last_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains.json?PageSize=50&Page=0\", \"total\": 1, \"num_pages\": 1, \"first_page_uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains.json?PageSize=50&Page=0\", \"end\": 1, \"previous_page_uri\": null, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains.json?PageSize=50&Page=0\", \"page_size\": 50, \"start\": 0, \"next_page_uri\": null, \"domains\": [ { \"subresource_uris\": { \"ip_access_control_list_mappings\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains/SD098e7b11c00d0ba152b1d3f084c4b776/IpAccessControlListMappings.json\", \"credential_list_mappings\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains/SD098e7b11c00d0ba152b1d3f084c4b776/CredentialListMappings.json\" }, \"uri\": \"/2010-04-01/Accounts/AC1fcc43cc0b4157cae6b77cdb692b437e/SIP/Domains/SD098e7b11c00d0ba152b1d3f084c4b776.json\", \"date_updated\": \"Fri, 14 Nov 2014 18:31:35 +0000\", \"date_created\": \"Fri, 06 Sep 2013 18:48:50 +0000\", \"voice_status_callback_method\": \"POST\", \"voice_status_callback_url\": null, \"voice_fallback_method\": \"POST\", \"voice_fallback_url\": null, \"sid\": \"SD098e7b11c00d0ba152b1d3f084c4b776\", \"account_sid\": \"AC1fcc43cc0b4157cae6b77cdb692b437e\", \"friendly_name\": \"Sam's domain\", \"api_version\": \"2010-04-01\", \"domain_name\": \"sam.sip.twilio.com\", \"auth_type\": \"\", \"voice_url\": null, \"voice_method\": \"POST\"}]}";

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
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/Domains/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipDomain.delete("sid").execute();
    }

    
    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/Domains/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipDomain.fetch("sid").execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        SipDomain instance = SipDomain.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient().getObjectMapper());
        assertNotNull(instance);
    }

    
    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/SIP/Domains.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipDomain.list().execute();
    }

    
    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/SIP/Domains/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        SipDomain.update("sid")
            .execute();
    }
}