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

public class IncomingPhoneNumberTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"address_requirements\": \"none\", \"api_version\": \"2010-04-01\", \"capabilities\": {\"mms\": false, \"sms\": true, \"voice\": true}, \"date_created\": \"Wed, 13 Oct 2010 20:46:51 +0000\", \"date_updated\": \"Wed, 13 Oct 2010 20:46:51 +0000\", \"friendly_name\": \"BMP Test Phone Number\", \"phone_number\": \"+14155552345\", \"sid\": \"PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"sms_application_sid\": null, \"sms_fallback_method\": \"GET\", \"sms_fallback_url\": null, \"sms_method\": \"POST\", \"sms_url\": null, \"status_callback\": null, \"status_callback_method\": null, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers/PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\", \"voice_application_sid\": null, \"voice_caller_id_lookup\": false, \"voice_fallback_method\": \"POST\", \"voice_fallback_url\": null, \"voice_method\": \"POST\", \"voice_url\": null}";

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers.json?Page=0&PageSize=50\", \"incoming_phone_numbers\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"address_requirements\": \"none\", \"api_version\": \"2010-04-01\", \"capabilities\": {\"mms\": false, \"sms\": true, \"voice\": true}, \"date_created\": \"Wed, 13 Oct 2010 20:46:51 +0000\", \"date_updated\": \"Wed, 13 Oct 2010 20:46:51 +0000\", \"friendly_name\": \"Incoming number\", \"phone_number\": \"+14155552340\", \"sid\": \"PNmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm\", \"sms_application_sid\": null, \"sms_fallback_method\": \"GET\", \"sms_fallback_url\": null, \"sms_method\": \"POST\", \"sms_url\": null, \"status_callback\": null, \"status_callback_method\": null, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers/PNmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm.json\", \"voice_application_sid\": null, \"voice_caller_id_lookup\": false, \"voice_fallback_method\": \"POST\", \"voice_fallback_url\": null, \"voice_method\": \"POST\", \"voice_url\": null}], \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers.json?Page=0&PageSize=50\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/IncomingPhoneNumbers.json\"}";

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
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        IncomingPhoneNumber.delete("sid")
                           .execute();
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        IncomingPhoneNumber.fetch("sid")
                           .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        IncomingPhoneNumber instance = IncomingPhoneNumber.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                                  .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        IncomingPhoneNumber.read()
                           .execute();
    }

    @Test
    public void testListLocal() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers/Local.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        IncomingPhoneNumber.read()
                           .byType(PhoneNumberType.LOCAL)
                           .execute();
    }

    @Test
    public void testListMobile() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers/Mobile.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        IncomingPhoneNumber.read()
                           .byType(PhoneNumberType.MOBILE)
                           .execute();
    }

    @Test
    public void testListTollFree() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers/TollFree.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        IncomingPhoneNumber.read()
                           .byType(PhoneNumberType.TOLL_FREE)
                           .execute();
    }

    @Test
    public void testUpdate() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        IncomingPhoneNumber.update("sid")
                           .execute();
    }
}
