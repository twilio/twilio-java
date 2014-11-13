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

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class ParticipantTest {
    final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    final String accountSid = "AC12345678901234567890123456789012";
    final String callSid = "CA12345678901234567890123456789012";
    final String conferenceSid = "CF12345678901234567890123456789012";
    final String uri = "/2010-04-01/Accounts/" + accountSid +
                                  "/Conferences/" + conferenceSid +
                                  "/Participants/" + callSid + ".json";

    TwilioRestClient client = mock(TwilioRestClient.class);
    TwilioRestResponse resp = mock(TwilioRestResponse.class);
    String formattedDate;
    HashMap<String, Object> map;

    private void setupMocks() {
        map = new HashMap<String, Object>();
        stub(resp.toMap()).toReturn(map);
        formattedDate = dateFormat.format(new Date());

        map.put("account_sid", accountSid);
        map.put("call_sid", callSid);
        map.put("conference_sid", conferenceSid);
        map.put("end_conference_on_exit", false);
        map.put("muted", false);
        map.put("start_conference_on_enter", false);
        map.put("date_created", formattedDate);
        map.put("date_updated", formattedDate);
    }

    /**
     * This test loads a {@link Participant} object by interacting with a {@link TwilioRestClient}.
     *
     * @throws com.twilio.sdk.TwilioRestException
     */
    @Test
    public void testBooleanPropertyMarshalling() throws TwilioRestException {

        setupMocks();
        stub(client.safeRequest(Matchers.eq(uri), Matchers.eq("GET"), Matchers.any(Map.class))).toReturn(resp);

        Participant participant = new Participant(client, conferenceSid, callSid);
        participant.setRequestAccountSid(accountSid);


        assertFalse(participant.isMuted());
        assertFalse(participant.isEndConferenceOnExit());
        assertFalse(participant.isStartConferenceOnEnter());
    }
}