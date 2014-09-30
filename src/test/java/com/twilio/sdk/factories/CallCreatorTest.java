package com.twilio.sdk.factories;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.ConsumableResponse;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CallCreatorTest {

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

    @Test
    public void testCreateCallSuccessful() throws Exception {
        TwilioRestClient client = TwilioRestClient.mock(new ConsumableResponse(JSON, 201));

        Call call = client.calls.create("+14155551234",
                                        "+14155557890",
                                        new URL("http://www.twilio.com"))
                                .build();

        assertCall(call);
    }

    @Test
    public void testCreateCallServerError() throws Exception {
        TwilioRestClient client = TwilioRestClient.mock(new ConsumableResponse("Internal Server Error", 500));

        try {
            client.calls.create("+14155551234",
                                "+14155557890",
                                new URL("http://www.twilio.com"))
                        .build();
            fail("Call creation should fail if the server is unable to create the call");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testCreateCallTransientError() throws MalformedURLException {
        TwilioRestClient client = TwilioRestClient.mock(
                // Serve 500 Error x 1 time
                new ConsumableResponse("Internal Server Error", 500, 1),
                // Return to normal operations
                new ConsumableResponse(JSON, 201)
        );

        Call call = client.calls.create("+14155551234",
                                        "+14155557890",
                                        new URL("http://www.twilio.com"))
                                .build();

        assertCall(call);
    }

    @Test
    public void testCreateCallClientError() throws Exception {
        TwilioRestClient client = TwilioRestClient.mock(new ConsumableResponse("To is a required field", 400));

        try {
            client.calls.create("",
                    "+14155557890",
                    new URL("http://www.twilio.com"))
                    .build();
            fail("Call creation should fail if the client provides bad data");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    protected void assertCall(Call call) {
        assertEquals("ACca498dbda0fef21f361a9a3326354175", call.getAccountSid());
        assertEquals("CA9e966bd3ef2abcfe941fb0a06e3fc027", call.getSid());
    }
}