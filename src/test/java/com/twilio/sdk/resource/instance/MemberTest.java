package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;

import org.junit.Test;
import org.mockito.Matchers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;



/**
 * This class contains tests for the {@link Member} resource.
 * @author Christer Fahlgren
 *
 */
public class MemberTest {
    final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
    final String callSid = "CA12345678901234567890123456789012";
    final String queueSid = "QU12345678901234567890123456789012";

    final String accountSid = "AC12345678901234567890123456789012";
    TwilioRestClient client = mock(TwilioRestClient.class);
    TwilioRestResponse resp = mock(TwilioRestResponse.class);
    String formattedDate;

    /**
     * Set up the mocks.
     */
    private void setupMocks() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        stub(resp.toMap()).toReturn(map);
        formattedDate = dateFormat.format(new Date());

        map.put("call_sid", callSid);
        map.put("queue_sid", queueSid);
        map.put("position", 1);
        map.put("date_enqueued", formattedDate);
        map.put("wait_time", 10);

    }

    /**
     * This test creates a {@link Member} object by interacting with a {@link TwilioRestClient}.
     * 
     * @throws TwilioRestException
     */
    @Test
    public void testCreation() throws TwilioRestException {

        setupMocks();
        stub(
                client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
                        + "/Members/" + callSid + ".json"), Matchers.eq("GET"), Matchers.any(Map.class)))
                .toReturn(resp);
        Member m = new Member(client, queueSid, callSid);
        m.setRequestAccountSid(accountSid);

        assertTrue(m.getPosition() == 1);
        assertTrue(dateFormat.format(m.getDateEnqueued()).equals(formattedDate));
        assertTrue(m.getWaitTime() == 10);
    }

    /**
     * This test creates a {@link Member} object by interacting with a {@link TwilioRestClient}.
     * 
     * @throws TwilioRestException
     */
    @Test
    public void testDequeue() throws TwilioRestException {

        setupMocks();
        stub(
                client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
                        + "/Members/" + callSid + ".json"), Matchers.eq("POST"), Matchers.any(Map.class)))
                .toReturn(resp);
        final Member m = new Member(client, queueSid, callSid);
        m.setRequestAccountSid(accountSid);
        final String method = "GET";
        final String url = "http://www.example.com";
        final Member newm = m.dequeue(url, method);

        assertTrue(newm.getPosition() == 1);
        assertTrue(dateFormat.format(newm.getDateEnqueued()).equals(formattedDate));
        assertTrue(newm.getWaitTime() == 10);
    }

}
