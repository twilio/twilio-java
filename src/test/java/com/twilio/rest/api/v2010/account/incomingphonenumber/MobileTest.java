/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.incomingphonenumber;

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

public class MobileTest {
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
                                          Domains.API.toString(),
                                          "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/IncomingPhoneNumbers/Mobile.json");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Mobile.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadFullResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers/Mobile.json?FriendlyName=friendly_name&Beta=true&PhoneNumber=%2B19876543210&PageSize=50&Page=0\",\"incoming_phone_numbers\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"address_requirements\": \"none\",\"address_sid\": \"ADaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"beta\": null,\"capabilities\": {\"mms\": true,\"sms\": false,\"voice\": true},\"date_created\": \"Thu, 30 Jul 2015 23:19:04 +0000\",\"date_updated\": \"Thu, 30 Jul 2015 23:19:04 +0000\",\"emergency_status\": \"Active\",\"emergency_address_sid\": \"ADaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"emergency_address_status\": \"registered\",\"friendly_name\": \"(808) 925-5327\",\"identity_sid\": \"RIaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"origin\": \"origin\",\"phone_number\": \"+18089255327\",\"sid\": \"PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sms_application_sid\": \"\",\"sms_fallback_method\": \"POST\",\"sms_fallback_url\": \"\",\"sms_method\": \"POST\",\"sms_url\": \"\",\"status_callback\": \"\",\"status_callback_method\": \"POST\",\"trunk_sid\": null,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers/PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\",\"voice_application_sid\": \"\",\"voice_caller_id_lookup\": false,\"voice_fallback_method\": \"POST\",\"voice_fallback_url\": null,\"voice_method\": \"POST\",\"voice_url\": null,\"bundle_sid\": \"BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"voice_receive_mode\": \"voice\",\"status\": \"in-use\"}],\"next_page_uri\": null,\"page\": 0,\"page_size\": 50,\"previous_page_uri\": null,\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers/Mobile.json?FriendlyName=friendly_name&Beta=true&PhoneNumber=%2B19876543210&PageSize=50&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Mobile.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadEmptyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"end\": 0,\"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers/Mobile.json?FriendlyName=friendly_name&Beta=true&PhoneNumber=%2B19876543210&PageSize=50&Page=0\",\"incoming_phone_numbers\": [],\"next_page_uri\": null,\"page\": 0,\"page_size\": 50,\"previous_page_uri\": null,\"start\": 0,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers/Mobile.json?FriendlyName=friendly_name&Beta=true&PhoneNumber=%2B19876543210&PageSize=50&Page=0\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Mobile.reader("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testCreateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.API.toString(),
                                          "/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/IncomingPhoneNumbers/Mobile.json");
            request.addPostParam("PhoneNumber", serialize(new com.twilio.type.PhoneNumber("+15017122661")));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Mobile.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15017122661")).create(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"address_requirements\": \"none\",\"address_sid\": \"ADaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"2010-04-01\",\"beta\": false,\"capabilities\": {\"voice\": true,\"sms\": false,\"mms\": true,\"fax\": false},\"date_created\": \"Thu, 30 Jul 2015 23:19:04 +0000\",\"date_updated\": \"Thu, 30 Jul 2015 23:19:04 +0000\",\"emergency_status\": \"Active\",\"emergency_address_sid\": \"ADaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"emergency_address_status\": \"registered\",\"friendly_name\": \"friendly_name\",\"identity_sid\": \"RIaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"origin\": \"origin\",\"phone_number\": \"+18089255327\",\"sid\": \"PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sms_application_sid\": \"APaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"sms_fallback_method\": \"GET\",\"sms_fallback_url\": \"https://example.com\",\"sms_method\": \"GET\",\"sms_url\": \"https://example.com\",\"status_callback\": \"https://example.com\",\"status_callback_method\": \"GET\",\"trunk_sid\": null,\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers/PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\",\"voice_application_sid\": \"APaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"voice_caller_id_lookup\": true,\"voice_fallback_method\": \"GET\",\"voice_fallback_url\": \"https://example.com\",\"voice_method\": \"GET\",\"voice_url\": \"https://example.com\",\"bundle_sid\": \"BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"voice_receive_mode\": \"voice\",\"status\": \"in-use\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Mobile.creator("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", new com.twilio.type.PhoneNumber("+15017122661")).create(tw);
    }
}