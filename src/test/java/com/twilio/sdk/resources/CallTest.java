package com.twilio.sdk.resources;

import com.google.common.collect.Range;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.http.ConsumableResponse;
import com.twilio.sdk.http.Request;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
        Call call = Call.build("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        validateCall(call);
    }

    @Test
    public void testCreateRequest() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 201));

        Call.create("+14155551234", "+14155557890", new URI("http://www.twilio.com"))
            .build();

        Request request = Twilio.getMockRequest();

        URL url = request.constructURL();
        assertEquals("https://api.twilio.com/2010-04-01/Accounts/AC123/Calls.json", url.toString());

        String formBody = request.encodeFormBody();
        validateQueryParams("To=+14155551234&From=+14155557890&Url=http://www.twilio.com", formBody);
    }

    @Test
    public void testCreateResponse() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 201));

        Call call = Call.create("+14155551234", "+14155557890", new URI("http://www.twilio.com"))
                        .build();

        validateCall(call);
    }

    @Test
    public void testUpdateRequest() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 200));
        Call call = Call.build("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Call.update()
            .setFriendlyName("Hello World")
            .build(call);

        Request request = Twilio.getMockRequest(1);
        assertNotNull(request);

        URL url = request.constructURL();
        assertEquals("https://api.twilio.com/2010-04-01/Accounts/AC123/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json", url.toString());

        // TODO: Make this work
//        String formBody = request.encodeFormBody();
//        assertEquals("FriendlyName=Hello+World", formBody);
    }

    @Test
    public void testUpdateResponse() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.INSTANCE_JSON, 200));
        Call call = Call.build("CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        call = Call.update()
                   .setStatus(Call.Status.COMPLETED)
                   .build(call);

        validateCall(call);
    }

    @Test
    public void testListRequest() throws Exception {
        Twilio.setMockResponses(new ConsumableResponse(CallMocks.FIRST_PAGE_JSON, 200));

        Call.find().byEndTime(Range.lessThan(LocalDate.now())).build();

        // TODO: Is this right?
//        Request request = Twilio.getMockRequest();
//        URL url = request.constructURL();
//        assertEquals("https://api.twilio.com/2010-04-01/Accounts/AC123/Calls.json", url.toString());
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
        assertEquals("completed", call.getStatus());

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

    private void validateQueryParams(String expected, String actual) {
        Map<String, String> expectedMap = queryStringToMap(expected, true);
        Map<String, String> actualMap = queryStringToMap(actual);

        assertEquals(expectedMap, actualMap);
    }

    private Map<String, String> queryStringToMap(String queryString) {
        return queryStringToMap(queryString, false);
    }

    private Map<String, String> queryStringToMap(String queryString, boolean encode) {
        Map<String, String> result = new HashMap<String, String>();
        String[] parts = queryString.split("&");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];

                if (encode) {
                    try {
                        key = URLEncoder.encode(key, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        // Ignore, just use the non-encoded
                    }

                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        // Ignore, just use the non-encoded
                    }
                }

                result.put(key, value);
            }
        }
        return result;
    }
}
