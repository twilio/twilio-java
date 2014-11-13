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

public class AccountTest {
    final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    final String accountSid = "AC12345678901234567890123456789012";
    final String otherAccountSid = "ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    final String authToken = "abcd1234";
    TwilioRestClient client = mock(TwilioRestClient.class);
    TwilioRestResponse resp = mock(TwilioRestResponse.class);
    String formattedDate;
    HashMap<String, Object> map;

    private void setupMocks() {
        map = new HashMap<String, Object>();
        stub(resp.toMap()).toReturn(map);
        formattedDate = dateFormat.format(new Date());

        map.put("date_created", formattedDate);
        map.put("date_updated", formattedDate);
        map.put("owner_account_sid", accountSid);
        map.put("friendly_name", "second account");
        map.put("status", "active");
        map.put("auth_token", authToken);
        map.put("sid", otherAccountSid);
    }

    /**
     * This test creates a {@link Account} object by interacting with a {@link TwilioRestClient}.
     *
     * @throws com.twilio.sdk.TwilioRestException
     */
    @Test
    public void testCreation() throws TwilioRestException {

        setupMocks();
        stub(
                client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + otherAccountSid + ".json"),
                        Matchers.eq("GET"), Matchers.any(Map.class)))
                .toReturn(resp);
        Account a = new Account(client, map);
        a.setRequestAccountSid(accountSid);

        assertTrue(a.getSid().equals(otherAccountSid));
    }
}