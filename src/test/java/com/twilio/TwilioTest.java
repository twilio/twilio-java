package com.twilio;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.http.TwilioRestClient;
import com.twilio.exception.AuthenticationException;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TwilioTest {

    public static String serialize(Object object) {
        return object.toString();
    }

    public static String serialize(List list) {
        // NOTE: This relies on the fact that integration tests only ever generate single element lists.
        return list.get(0).toString();
    }

    @Test
    public void testGetExecutorService() {
        assertNotNull(Twilio.getExecutorService());
    }

    @Test(expected = AuthenticationException.class)
    public void testGetRestClientNullAccountSid() {
        Twilio.setRestClient(null);
        Twilio.setUsername(null);
        Twilio.setPassword(null);

        Twilio.getRestClient();
        fail("AuthenticationException was expected");
    }

    @Test(expected = AuthenticationException.class)
    public void testSetAccountSidNull() {
        Twilio.setUsername(null);
        fail("AuthenticationException was expected");
    }

    @Test(expected = AuthenticationException.class)
    public void testSetAuthTokenNull() {
        Twilio.setPassword(null);
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
        TwilioRestClient twilioRestClient = new TwilioRestClient.Builder("AC123", "AUTH TOKEN").build();
        Twilio.setRestClient(twilioRestClient);
        assertEquals(twilioRestClient, Twilio.getRestClient());
    }

}
