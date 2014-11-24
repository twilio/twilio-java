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

public class AuthorizedConnectAppTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"connect_app_sid\": \"CNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"permissions\": [\"get-all\", \"post-all\"], \"connect_app_friendly_name\": \"My Connect App\", \"connect_app_description\": null, \"connect_app_company_name\": \"My Company\", \"connect_app_homepage_url\": \"http://www.mycompany.com\"}";

    private static final String LIST_JSON_RESPONSE = "{\"authorized_connect_apps\": [{\"account_sid\": \"AC55555555555555555555555555555555\", \"connect_app_company_name\": \"Not Twilio\", \"connect_app_description\": null, \"connect_app_friendly_name\": \"Connect app for testing\", \"connect_app_homepage_url\": \"http://example.com/nothome\", \"connect_app_sid\": \"CNbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\", \"permissions\": [\"post-all\"], \"uri\": \"/2010-04-01/Accounts/AC55555555555555555555555555555555/AuthorizedConnectApps/CNbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb.json\"}], \"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/AuthorizedConnectApps.json?Page=0&PageSize=50\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/AuthorizedConnectApps.json?Page=0&PageSize=50\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/AuthorizedConnectApps.json\"}";

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
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/AuthorizedConnectApps/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        AuthorizedConnectApp.fetch("sid")
                            .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        AuthorizedConnectApp instance = AuthorizedConnectApp.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                                    .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/AuthorizedConnectApps.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        AuthorizedConnectApp.list()
                            .execute();
    }
}
