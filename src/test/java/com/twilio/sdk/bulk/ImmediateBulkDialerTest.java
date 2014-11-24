package com.twilio.sdk.bulk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Currency;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class ImmediateBulkDialerTest {

    private static final String JSON = "{" +
                                       "\"account_sid\": \"ACca498dbda0fef21f361a9a3326354175\",\n" +
                                       "\"annotation\": null,\n" +
                                       "\"answered_by\": null,\n" +
                                       "\"api_version\": \"2008-08-01\",\n" +
                                       "\"caller_name\": null,\n" +
                                       "\"date_created\": \"Mon, 29 Sep 2014 20:39:42 +0000\",\n" +
                                       "\"date_updated\": \"Mon, 29 Sep 2014 20:39:50 +0000\",\n" +
                                       "\"direction\": \"inbound\",\n" +
                                       "\"duration\": \"8\",\n" +
                                       "\"end_time\": \"Mon, 29 Sep 2014 20:39:50 +0000\",\n" +
                                       "\"forwarded_from\": \"+19143689587\",\n" +
                                       "\"from\": \"+16507843280\",\n" +
                                       "\"from_formatted\": \"(650) 784-3280\",\n" +
                                       "\"group_sid\": null,\n" +
                                       "\"parent_call_sid\": null,\n" +
                                       "\"phone_number_sid\": \"PN5c269861c7f337f6876d6ef214a094cc\",\n" +
                                       "\"price\": \"-0.01000\",\n" +
                                       "\"price_unit\": \"USD\",\n" +
                                       "\"sid\": \"CA9e966bd3ef2abcfe941fb0a06e3fc027\",\n" +
                                       "\"start_time\": \"Mon, 29 Sep 2014 20:39:42 +0000\",\n" +
                                       "\"status\": \"completed\",\n" +
                                       "\"subresource_uris\": {\n" +
                                       "    \"notifications\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Notifications.json\",\n" +
                                       "    \"recordings\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Recordings.json\"\n" +
                                       "},\n" +
                                       "\"to\": \"+19143689587\",\n" +
                                       "\"to_formatted\": \"(914) 368-9587\",\n" +
                                       "\"uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027.json\"\n" +
                                       "}";

    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testAdd() throws URISyntaxException {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(JSON, TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        BulkDialer dialer = new ImmediateBulkDialer();
        dialer.add("sales-call", Call.create("+14155551234", "+14155556789", new URI("http://example.com")));

        validateCall(dialer.get("sales-call"));
    }

    @Test
    public void testComplete() throws URISyntaxException {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(JSON, TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        BulkDialer dialer = new ImmediateBulkDialer();
        dialer.add("sales-call", Call.create("+14155551234", "+14155556789", new URI("http://example.com")));

        dialer.complete();

        validateCall(dialer.get("sales-call"));
    }

    @Test
    public void testIterator() throws URISyntaxException {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(JSON, TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        BulkDialer dialer = new ImmediateBulkDialer();
        dialer.add("sales-call", Call.create("+14155551234", "+14155556789", new URI("http://example.com")));

        assertNotNull(dialer.iterator());
    }

    @Test
    public void testGetMissingKey() {
        BulkDialer dialer = new ImmediateBulkDialer();
        assertNull(dialer.get("missing-key"));
    }

    private void validateCall(final Call call) {
        assertNotNull(call);
        assertEquals(call.getAccountSid(), "ACca498dbda0fef21f361a9a3326354175");
        assertEquals(call.getAnnotation(), null);
        assertEquals(call.getAnsweredBy(), null);
        assertEquals(call.getApiVersion(), "2008-08-01");
        assertEquals(call.getCallerName(), null);
        assertEquals("Mon, 29 Sep 2014 20:39:42 +0000", call.getDateCreated()
                                                            .toString(Twilio.DATE_TIME_FORMATTER));
        assertEquals("Mon, 29 Sep 2014 20:39:50 +0000", call.getDateUpdated()
                                                            .toString(Twilio.DATE_TIME_FORMATTER));
        assertEquals(call.getDirection(), "inbound");
        assertEquals(call.getDuration()
                         .intValue(), 8);
        assertEquals("Mon, 29 Sep 2014 20:39:50 +0000", call.getEndTime()
                                                            .toString(Twilio.DATE_TIME_FORMATTER));
        assertEquals(call.getForwardedFrom(), "+19143689587");
        assertEquals(call.getFrom(), "+16507843280");
        assertEquals(call.getFromFormatted(), "(650) 784-3280");
        assertEquals(call.getGroupSid(), null);
        assertEquals(call.getParentCallSid(), null);
        assertEquals(call.getPhoneNumberSid(), "PN5c269861c7f337f6876d6ef214a094cc");
        assertEquals(new BigDecimal("-0.01000"), call.getPrice());
        assertEquals(call.getPriceUnit(), Currency.getInstance("USD"));
        assertEquals(call.getSid(), "CA9e966bd3ef2abcfe941fb0a06e3fc027");
        assertEquals("Mon, 29 Sep 2014 20:39:42 +0000", call.getStartTime()
                                                            .toString(Twilio.DATE_TIME_FORMATTER));
        assertEquals(Call.Status.COMPLETED, call.getStatus());

        Map<String, String> uris = call.getSubresourceUris();

        for (final String key : uris.keySet()) {
            switch (key) {
                case "notifications":
                    assertEquals(uris.get(key),
                                 "/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Notifications.json");
                    break;
                case "recordings":
                    assertEquals(uris.get(key),
                                 "/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Recordings.json");
                    break;
                default:
                    fail("Unknown subresource uri key: " + key);
                    break;
            }
        }

        assertEquals(call.getTo(), "+19143689587");
        assertEquals(call.getToFormatted(), "(914) 368-9587");
        assertEquals(call.getUri(),
                     "/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027.json");
    }
}
