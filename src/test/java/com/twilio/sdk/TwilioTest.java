package com.twilio.sdk;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.AuthenticationException;
import org.junit.Test;

import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TwilioTest {

    public static String serialize(Object object) {
        return object.toString();
    }

    @Test
    public void testGetExecutorService() {
        assertNotNull(Twilio.getExecutorService());
    }

    @Test(expected = AuthenticationException.class)
    public void testGetRestClientNullAccountSid() {
        Twilio.setRestClient(null);
        Twilio.setAccountSid(null);
        Twilio.setAuthToken(null);

        Twilio.getRestClient();
        fail("AuthenticationException was expected");
    }

    @Test(expected = AuthenticationException.class)
    public void testSetAccountSidNull() {
        Twilio.setAccountSid(null);
        fail("AuthenticationException was expected");
    }

    @Test(expected = AuthenticationException.class)
    public void testSetAuthTokenNull() {
        Twilio.setAuthToken(null);
        fail("AuthenticationException was expected");
    }

    @Test
    public void testSetExecutorService() {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(
                Executors.newCachedThreadPool());
        Twilio.setExecutorService(listeningExecutorService);
        assertEquals(listeningExecutorService, Twilio.getExecutorService());
    }

    @Test
    public void testSetRestClient() {
        TwilioRestClient twilioRestClient = new TwilioRestClient("AC123", "AUTH TOKEN");
        Twilio.setRestClient(twilioRestClient);
        assertEquals(twilioRestClient, Twilio.getRestClient());
    }

}
