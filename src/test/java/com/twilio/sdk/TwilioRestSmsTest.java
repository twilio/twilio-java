package com.twilio.sdk;

import com.twilio.sdk.examples.Example;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Author: wge
 * Date: 29/07/2015
 * Time: 21:31
 */

public class TwilioRestSmsTest
{
    private static final String API = "https://api.twilio.com";
    private static final String TEST_ACCOUNT_SID = "AC0649d36d4016abb849d571b713bc65b3";
    private static final String TEST_SMS_URL = "https://api.twilio.com/2010-04-01/Accounts/" + TEST_ACCOUNT_SID + "/SMS/Messages";
    private final TwilioClient client = new TwilioRestClient(TEST_ACCOUNT_SID, Example.AUTH_TOKEN, API);

    private static final String VALID_FROM_NUMBER = "+15005550006";

    @Test
    @Ignore //runs as an integration test - takes too long for a unit test, but does work.
    public void sendPinToInvalidPhone() throws TwilioUnreachableUrlException
    {
        try
        {
            client.sendSms(TEST_SMS_URL, "+15005550001", VALID_FROM_NUMBER, "test");
            assertFalse(true);
        }
        catch (TwilioRestException e)
        {
            assertEquals(21211, e.getErrorCode());
            assertEquals("The 'To' number +15005550001 is not a valid phone number.", e.getMessage());
        }
    }

}
