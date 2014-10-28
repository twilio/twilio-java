package com.twilio.sdk.bulk;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.http.ConsumableResponse;
import com.twilio.sdk.http.MockHttpClient;
import com.twilio.sdk.resources.Call;
import com.twilio.sdk.timing.Stopwatch;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ImmediateBulkDialerTest {
    public static final String JSON = "{" +
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

    @Before
    public void setUp() throws Exception {
        Twilio.setAccountSid("ACca498dbda0fef21f361a9a3326354175");
        Twilio.setAuthToken("8b20fad4aaf29e4d9f30ee0994a2e3bd");
    }

    @Test
    public void testBulkDialing() throws Exception {
        long overhead = 500;
        long requestDelay = 1000;

        Twilio.useMockResponses(new ConsumableResponse(JSON, 201));
        Twilio.setMockDelay(requestDelay);

        URI applicationUrl = new URI("http://example.com");

        BulkDialer dialer = new ImmediateBulkDialer();
        Stopwatch stopwatch = new Stopwatch();

        // Start the stopwatch
        stopwatch.start();

        // Add five calls (each one delays 1000ms)
        dialer.add("sales-call", Call.create("+14155551234", "+14155556789", applicationUrl));
        dialer.add("other-call", Call.create("+14155551234", "+14155550000", applicationUrl));
        dialer.add("mrktg-call", Call.create("+14155551234", "+14155559876", applicationUrl));
        dialer.add("legal-call", Call.create("+14155551234", "+14155559876", applicationUrl));
        dialer.add("final-call", Call.create("+14155551234", "+14155559876", applicationUrl));

        // Wait for all calls to complete
        dialer.complete();

        // Stop the stopwatch
        stopwatch.stop();

        assertTrue("Elapsed time (" + stopwatch.getElapsed() + ") should be less than [request delay] + [overhead] (" + (requestDelay + overhead) + ")",
                   stopwatch.getElapsed() < (requestDelay + overhead));

        // Validate that all the calls got populated successfully
        validateCall(dialer.get("sales-call"));
        validateCall(dialer.get("other-call"));
        validateCall(dialer.get("mrktg-call"));
        validateCall(dialer.get("legal-call"));
        validateCall(dialer.get("final-call"));
    }

    private void validateCall(Call call) {
        assertNotNull(call);
        assertEquals(call.getAccountSid(), "ACca498dbda0fef21f361a9a3326354175");
        assertEquals(call.getAnnotation(), null);
        assertEquals(call.getAnsweredBy(), null);
        assertEquals(call.getApiVersion(), "2008-08-01");
        assertEquals(call.getCallerName(), null);
        assertEquals(call.getDateCreated(), "Mon, 29 Sep 2014 20:39:42 +0000");
        assertEquals(call.getDateUpdated(), "Mon, 29 Sep 2014 20:39:50 +0000");
        assertEquals(call.getDirection(), "inbound");
        assertEquals(call.getDuration().intValue(), 8);
        assertEquals(call.getEndTime(), "Mon, 29 Sep 2014 20:39:50 +0000");
        assertEquals(call.getForwardedFrom(), "+19143689587");
        assertEquals(call.getFrom(), "+16507843280");
        assertEquals(call.getFromFormatted(), "(650) 784-3280");
        assertEquals(call.getGroupSid(), null);
        assertEquals(call.getParentCallSid(), null);
        assertEquals(call.getPhoneNumberSid(), "PN5c269861c7f337f6876d6ef214a094cc");
        assertEquals(call.getPrice(), -0.01, 0.0001);
        assertEquals(call.getPriceUnit(), "USD");
        assertEquals(call.getSid(), "CA9e966bd3ef2abcfe941fb0a06e3fc027");
        assertEquals(call.getStartTime(), "Mon, 29 Sep 2014 20:39:42 +0000");
        assertEquals(call.getStatus(), "completed");

        Map<String, String> uris = call.getSubresourceUris();

        for (String key : uris.keySet()) {
            if (key.equals("notifications")) {
                assertEquals(uris.get(key), "/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Notifications.json");
            } else if (key.equals("recordings")) {
                assertEquals(uris.get(key), "/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Recordings.json");
            } else {
                fail("Unknown subresource uri key: " + key);
            }
        }

        assertEquals(call.getTo(), "+19143689587");
        assertEquals(call.getToFormatted(), "(914) 368-9587");
        assertEquals(call.getUri(), "/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027.json");
    }
}