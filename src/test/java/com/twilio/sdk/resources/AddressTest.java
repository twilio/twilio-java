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

public class AddressTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"AC9fa6e6422f53463a89b5d81b2645d070\", \"city\": \"Sydney\", \"customer_name\": \"Tester\", \"date_created\": \"Mon, 06 Oct 2014 23:57:11 +0000\", \"date_updated\": \"Mon, 06 Oct 2014 23:57:11 +0000\", \"friendly_name\": \"Test\", \"iso_country\": \"AU\", \"postal_code\": \"12345\", \"region\": \"NSW\", \"sid\": \"AD79f1863d1ad0c25e795bb7577da0999a\", \"street\": \"123+Main+St\", \"uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/Addresses/AD79f1863d1ad0c25e795bb7577da0999a.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"addresses\": [ { \"account_sid\": \"AC9fa6e6422f53463a89b5d81b2645d070\", \"city\": \"Sydney\", \"customer_name\": \"Tester\", \"date_created\": \"Mon, 06 Oct 2014 23:57:11 +0000\", \"date_updated\": \"Mon, 06 Oct 2014 23:57:11 +0000\", \"friendly_name\": \"Test\", \"iso_country\": \"AU\", \"postal_code\": \"12345\", \"region\": \"NSW\", \"sid\": \"AD79f1863d1ad0c25e795bb7577da0999a\", \"street\": \"123+Main+St\", \"uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/Addresses/AD79f1863d1ad0c25e795bb7577da0999a.json\" } ], \"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/Addresses.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/Addresses.json?PageSize=50&Page=0\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/Addresses.json?PageSize=50&Page=0\"}";

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
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/Addresses/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        Address.delete("sid").execute();
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/Addresses/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        Address.fetch("sid").execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Address instance = Address.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient().getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Addresses.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        Address.list().execute();
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL(); result = "https://api.twilio.com/2010-04-01/Addresses/sid.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        Address.update("sid")
            .execute();
    }
}
