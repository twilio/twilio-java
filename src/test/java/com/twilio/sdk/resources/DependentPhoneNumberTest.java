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

public class DependentPhoneNumberTest {

    private static final String LIST_JSON_RESPONSE = "{\"dependent_phone_numbers\": [ { \"account_sid\": \"AC9fa6e6422f53463a89b5d81b2645d070\", \"address_requirements\": \"local\", \"api_version\": \"2010-04-01\", \"capabilities\": { \"MMS\": true, \"SMS\": true, \"Voice\": true }, \"date_created\": \"Mon, 06 Oct 2014 16:57:48 -0700\", \"date_updated\": \"Mon, 06 Oct 2014 16:57:48 -0700\", \"friendly_name\": \"61286070029\", \"phone_number\": \"+61286070029\", \"sid\": \"PN5a75c1c20dca2212e38f030eeada3099\", \"sms_application_sid\": \"\", \"sms_fallback_method\": \"POST\", \"sms_fallback_url\": \"\", \"sms_method\": \"POST\", \"sms_url\": \"\", \"status_callback\": \"\", \"status_callback_method\": \"POST\", \"uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/IncomingPhoneNumbers/PN5a75c1c20dca2212e38f030eeada3099.json\", \"voice_application_sid\": \"AP96f73082ac3e593d2624b0e641348ff2\", \"voice_caller_id_lookup\": false, \"voice_fallback_method\": \"POST\", \"voice_fallback_url\": null, \"voice_method\": \"POST\", \"voice_url\": null } ], \"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/Addresses/AD79f1863d1ad0c25e795bb7577da0999a/DependentPhoneNumbers.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/Addresses/AD79f1863d1ad0c25e795bb7577da0999a/DependentPhoneNumbers.json?PageSize=50&Page=0\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/AC9fa6e6422f53463a89b5d81b2645d070/Addresses/AD79f1863d1ad0c25e795bb7577da0999a/DependentPhoneNumbers.json?PageSize=50&Page=0\"}";

    @Mocked
    private Request request;

    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Addresses/AD123/DependentPhoneNumbers.json".replace("{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper(); result = new ObjectMapper();
        }};

        DependentPhoneNumber.list("AD123").execute();
    }
}
