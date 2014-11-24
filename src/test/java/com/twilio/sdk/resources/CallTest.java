package com.twilio.sdk.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Range;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CallTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"annotation\": null, \"answered_by\": null, \"api_version\": \"2008-08-01\", \"caller_name\": null, \"date_created\": \"Mon, 29 Sep 2014 20:39:42 +0000\", \"date_updated\": \"Mon, 29 Sep 2014 20:39:50 +0000\", \"direction\": \"inbound\", \"duration\": \"8\", \"end_time\": \"Mon, 29 Sep 2014 20:39:50 +0000\", \"forwarded_from\": \"+19143689587\", \"from\": \"+16507843280\", \"from_formatted\": \"(650) 784-3280\", \"group_sid\": null, \"parent_call_sid\": null, \"phone_number_sid\": \"PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"price\": \"-0.01000\", \"price_unit\": \"USD\", \"sid\": \"CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"start_time\": \"Mon, 29 Sep 2014 20:39:42 +0000\", \"status\": \"completed\", \"subresource_uris\": {\"notifications\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json\", \"recordings\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Recordings.json\"}, \"to\": \"+19143689587\", \"to_formatted\": \"(914) 368-9587\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"calls\": [" + INSTANCE_JSON_RESPONSE +
                                                     "], \"end\": 49, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls.json?PageSize=50&Page=33\", \"next_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls.json?PageSize=50&Page=1&AfterSid=CA7e123477b24eb76416705b82a02a1e8b\", \"num_pages\": 34, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1682, \"uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls.json?PageSize=50&Page=0\"}";

    @Mocked
    private Request request;

    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testFromJson() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Call call = Call.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                .getObjectMapper());
        validateCall(call);
    }

    @Test
    public void testBuild() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};
        Call call = Call.fetch("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                        .execute();
        validateCall(call);
    }

    @Test
    public void testCreateRequest() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Calls.json";
            request.encodeFormBody();
            result = "To=+14155551234&From=+14155557890&Url=http://www.twilio.com";
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Call.create("+14155551234", "+14155557890", new URI("http://www.twilio.com"))
            .execute();
    }

    @Test
    public void testCreateResponse() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Call call = Call.create("+14155551234", "+14155557890", new URI("http://www.twilio.com"))
                        .execute();

        validateCall(call);
    }

    @Test
    public void testFetchRequest() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json";
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Call.fetch("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            .execute();
    }

    @Test
    public void testUpdateRequest() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json";
            request.encodeFormBody();
            result = "Url=https://www.twilio.com&Method=POST";
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Call.update("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            .setUrl(new URI("https://www.twilio.com"))
            .setMethod(HttpMethod.POST)
            .execute();
    }

    @Test
    public void testUpdateResponse() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Call call = Call.fetch("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                        .execute();

        call = Call.update(call)
                   .setUrl(new URI("https://www.twilio.com"))
                   .setMethod(HttpMethod.POST)
                   .execute();

        validateCall(call);
    }

    @Test
    public void testListRequest() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/AC123/Calls.json?PageSize=51&EndTime<=2014-01-01";
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Call.list()
            .byEndTime(Range.lessThan(DateTime.parse("2014-01-01")))
            .pageSize(51)
            .execute();
    }

    private void validateCall(final Call call) {
        assertNotNull(call);
        assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", call.getAccountSid());
        assertEquals(null, call.getAnnotation());
        assertEquals(null, call.getAnsweredBy());
        assertEquals("2008-08-01", call.getApiVersion());
        assertEquals(null, call.getCallerName());
        assertEquals("Mon, 29 Sep 2014 20:39:42 +0000", call.getDateCreated()
                                                            .toString(Twilio.DATE_TIME_FORMATTER));
        assertEquals("Mon, 29 Sep 2014 20:39:50 +0000", call.getDateUpdated()
                                                            .toString(Twilio.DATE_TIME_FORMATTER));
        assertEquals("inbound", call.getDirection());
        assertEquals(8, call.getDuration()
                            .intValue());
        assertEquals("Mon, 29 Sep 2014 20:39:50 +0000", call.getEndTime()
                                                            .toString(Twilio.DATE_TIME_FORMATTER));
        assertEquals("+19143689587", call.getForwardedFrom());
        assertEquals("+16507843280", call.getFrom());
        assertEquals("(650) 784-3280", call.getFromFormatted());
        assertEquals(null, call.getGroupSid());
        assertEquals(null, call.getParentCallSid());
        assertEquals("PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", call.getPhoneNumberSid());
        assertEquals(new BigDecimal("-0.01000"), call.getPrice());
        assertEquals(Currency.getInstance("USD"), call.getPriceUnit());
        assertEquals("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", call.getSid());
        assertEquals("Mon, 29 Sep 2014 20:39:42 +0000", call.getStartTime()
                                                            .toString(Twilio.DATE_TIME_FORMATTER));
        assertEquals(Call.Status.COMPLETED, call.getStatus());

        Map<String, String> uris = call.getSubresourceUris();

        for (final String key : uris.keySet()) {
            switch (key) {
                case "notifications":
                    assertEquals(
                            "/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json",
                            uris.get(key));
                    break;
                case "recordings":
                    assertEquals(
                            "/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Recordings.json",
                            uris.get(key));
                    break;
                default:
                    fail("Unknown subresource uri key: " + key);
                    break;
            }
        }

        assertEquals("+19143689587", call.getTo());
        assertEquals("(914) 368-9587", call.getToFormatted());
        assertEquals(
                "/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json",
                call.getUri());
    }

}
