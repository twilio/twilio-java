package com.twilio.sdk.resources;

import com.google.common.collect.Range;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.http.ConsumableResponse;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URL;
import java.util.Map;

import static com.twilio.sdk.Assert.assertQueryStringsEqual;
import static com.twilio.sdk.Assert.assertUrlsEqual;
import static org.junit.Assert.*;

public class CallTest {
    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @After
    public void tearDown() throws Exception {
        Twilio.clearMockResponses();
    }

    @Test
    public void testFromJson() throws Exception {
        Call call = Call.fromJson(CallMocks.INSTANCE_JSON);
        validateCall(call);
    }

    @Test
    public void testBuild() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 200));
        Call call = Call.fetch("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        validateCall(call);
    }

    @Test
    public void testCreateRequest() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 201));

        Call.create("+14155551234", "+14155557890", new URI("http://www.twilio.com"))
            .execute();

        Request request = Twilio.getMockRequest();

        URL url = request.constructURL();
        assertEquals("https://api.twilio.com/2010-04-01/Accounts/AC123/Calls.json", url.toString());

        String formBody = request.encodeFormBody();
        assertQueryStringsEqual("To=+14155551234&From=+14155557890&Url=http://www.twilio.com", formBody);
    }

    @Test
    public void testCreateResponse() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 201));

        Call call = Call.create("+14155551234", "+14155557890", new URI("http://www.twilio.com"))
                        .execute();

        validateCall(call);
    }

    @Test
    public void testUpdateRequest() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 200));

        Call.update()
            .setUrl(new URI("https://www.twilio.com"))
            .setMethod(HttpMethod.POST)
            .execute("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Request request = Twilio.getMockRequest();

        URL url = request.constructURL();
        assertEquals(
                "https://api.twilio.com/2010-04-01/Accounts/AC123/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json",
                url.toString()
        );

        String formBody = request.encodeFormBody();
        assertQueryStringsEqual("Url=https://www.twilio.com&Method=POST", formBody);
    }

    @Test
    public void testUpdateResponse() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 200));
        Call call = Call.fetch("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        call = Call.update()
                   .setUrl(new URI("https://www.twilio.com"))
                   .setMethod(HttpMethod.POST)
                   .execute(call);

        validateCall(call);
    }

    @Test
    public void testListRequest() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.FIRST_PAGE_JSON, 200));

        Call.list()
            .byEndTime(Range.lessThan(LocalDate.parse("2014-01-01")))
            .execute();

        Request request = Twilio.getMockRequest();
        assertUrlsEqual(
            "https://api.twilio.com/2010-04-01/Accounts/AC123/Calls.json?EndTime<=2014-01-01",
            request.constructURL()
        );
    }

    private void validateCall(Call call) {
        assertNotNull(call);
        assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", call.getAccountSid());
        assertEquals(null, call.getAnnotation());
        assertEquals(null, call.getAnsweredBy());
        assertEquals("2008-08-01", call.getApiVersion());
        assertEquals(null, call.getCallerName());
        assertEquals("Mon, 29 Sep 2014 20:39:42 +0000", call.getDateCreated());
        assertEquals("Mon, 29 Sep 2014 20:39:50 +0000", call.getDateUpdated());
        assertEquals("inbound", call.getDirection());
        assertEquals(8, call.getDuration().intValue());
        assertEquals("Mon, 29 Sep 2014 20:39:50 +0000", call.getEndTime());
        assertEquals("+19143689587", call.getForwardedFrom());
        assertEquals("+16507843280", call.getFrom());
        assertEquals("(650) 784-3280", call.getFromFormatted());
        assertEquals(null, call.getGroupSid());
        assertEquals(null, call.getParentCallSid());
        assertEquals("PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", call.getPhoneNumberSid());
        assertEquals(-0.01, call.getPrice(), 0.0001);
        assertEquals("USD", call.getPriceUnit());
        assertEquals("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", call.getSid());
        assertEquals("Mon, 29 Sep 2014 20:39:42 +0000", call.getStartTime());
        assertEquals(Call.Status.COMPLETED, call.getStatus());

        Map<String, String> uris = call.getSubresourceUris();

        for (String key : uris.keySet()) {
            if (key.equals("notifications")) {
                assertEquals("/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json", uris.get(key));
            } else if (key.equals("recordings")) {
                assertEquals("/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Recordings.json", uris.get(key));
            } else {
                fail("Unknown subresource uri key: " + key);
            }
        }

        assertEquals("+19143689587", call.getTo());
        assertEquals("(914) 368-9587", call.getToFormatted());
        assertEquals("/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json", call.getUri());
    }




}