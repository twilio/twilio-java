package com.twilio;

import com.twilio.exception.ApiException;
import com.twilio.exception.AuthenticationException;
import com.twilio.exception.CertificateValidationException;
import com.twilio.http.HttpMethod;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TwilioTest {

    @Mocked
    private Twilio tw;

    public static String serialize(Object object) {
        return object.toString();
    }

    public static String serialize(List list) {
        // NOTE: This relies on the fact that integration tests only ever generate single element lists.
        return list.get(0).toString();
    }

    @Mocked
    private NetworkHttpClient networkHttpClient;

    @Before
    public void setUp() throws Exception {
        tw = new Twilio("AC123", "AUTH TOKEN");
        System.out.println("in before");
    }

    @Test
    public void testGetExecutorService() {
        assertNotNull(Twilio.getExecutorService());
    }

    @Test(expected = AuthenticationException.class)
    public void testGetRestClientNullAccountSid() {
        tw.setRestClient(null);
        tw.setUsername(null);
        tw.setPassword(null);

        tw.getRestClient();
        fail("AuthenticationException was expected");
    }

    @Test(expected = AuthenticationException.class)
    public void testSetAccountSidNull() {
        tw.setUsername(null);
        fail("AuthenticationException was expected");
    }

    @Test(expected = AuthenticationException.class)
    public void testSetAuthTokenNull() {
        tw.setPassword(null);
        fail("AuthenticationException was expected");
    }

    @Test
    public void testSetExecutorService() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Twilio.setExecutorService(executorService);
        assertEquals(executorService, Twilio.getExecutorService());
    }

    @Test
    public void testDestroyExecutorService() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Twilio.setExecutorService(executorService);
        tw.destroy();
        assertTrue(Twilio.getExecutorService().isShutdown());
    }

    @Test
    public void testSetRestClient() {
        TwilioRestClient twilioRestClient = new TwilioRestClient.Builder("AC123", "AUTH TOKEN").build();
        tw.setRestClient(twilioRestClient);
        assertEquals(twilioRestClient, tw.getRestClient());
    }

    @Test
    public void testValidateSslCertificateError() {
        new NonStrictExpectations() {{
            final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");
            networkHttpClient.makeRequest(request);
            times = 1;
            result = new Response("", 500);
        }};

        try {
            Twilio.validateSslCertificate();
            fail("Excepted CertificateValidationException");
        } catch (final CertificateValidationException e) {
            assertEquals("Unexpected response from certificate endpoint", e.getMessage());
        }
    }

    @Test
    public void testValidateSslCertificateException() {
        new NonStrictExpectations() {{
            final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");
            networkHttpClient.makeRequest(request);
            times = 1;
            result = new ApiException("No");
        }};

        try {
            Twilio.validateSslCertificate();
            fail("Excepted CertificateValidationException");
        } catch (final CertificateValidationException e) {
            assertEquals("Could not get response from certificate endpoint", e.getMessage());
        }
    }

    @Test
    public void testValidateSslCertificateSuccess() {
        new NonStrictExpectations() {{
            final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");
            networkHttpClient.makeRequest(request);
            times = 1;
            result = new Response("", 200);
        }};

        Twilio.validateSslCertificate();
    }
}
