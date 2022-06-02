package com.twilio;

import com.twilio.exception.ApiException;
import com.twilio.exception.AuthenticationException;
import com.twilio.exception.CertificateValidationException;
import com.twilio.http.HttpMethod;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class TwilioTest {

    private static final String USER_NAME = "UserName";

    private static final String TOKEN = "Password";

    public static String serialize(Object object) {
        return object.toString();
    }

    public static String serialize(List list) {
        // NOTE: This relies on the fact that integration tests only ever generate single element lists.
        return list.get(0).toString();
    }

    @Mock
    private NetworkHttpClient networkHttpClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
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
    public void testUserAgentExtensions() {
        Twilio.setUsername(USER_NAME);
        Twilio.setPassword(TOKEN);
        Twilio.setUserAgentExtensions(Arrays.asList("ce-appointment-reminders/1.0.0", "code-exchange"));
        Twilio.getRestClient();
        assertEquals(Arrays.asList("ce-appointment-reminders/1.0.0", "code-exchange"), Twilio.getUserAgentExtensions());
    }

    @Test
    public void testUserAgentExtensionsEmpty() {
        Twilio.setUsername(USER_NAME);
        Twilio.setPassword(TOKEN);
        Twilio.setUserAgentExtensions(Collections.emptyList()); // Resetting userAgentExtension
        Twilio.getRestClient();
        assertNull(Twilio.getUserAgentExtensions());
    }

    @Test
    public void testUserAgentExtensionsNull() {
        Twilio.setUsername(USER_NAME);
        Twilio.setPassword(TOKEN);
        Twilio.setUserAgentExtensions(null); // Resetting userAgentExtension
        Twilio.getRestClient();
        assertNull(Twilio.getUserAgentExtensions());
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
        Twilio.destroy();
        assertTrue(Twilio.getExecutorService().isShutdown());
    }

    @Test
    public void testSetRestClient() {
        TwilioRestClient twilioRestClient = new TwilioRestClient.Builder("AC123", "AUTH TOKEN").build();
        Twilio.setRestClient(twilioRestClient);
        assertEquals(twilioRestClient, Twilio.getRestClient());
    }

    @Test
    public void testValidateSslCertificateError() {
        final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");
        when(networkHttpClient.makeRequest(request)).thenReturn(new Response("", 500));
        try {
            Twilio.validateSslCertificate(networkHttpClient);
            fail("Excepted CertificateValidationException");
        } catch (final CertificateValidationException e) {
            assertEquals("Unexpected response from certificate endpoint", e.getMessage());
        }
    }

    @Test
    public void testValidateSslCertificateException() {
        final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");
        when(networkHttpClient.makeRequest(request)).thenThrow(new ApiException("No"));

        try {
            Twilio.validateSslCertificate(networkHttpClient);
            fail("Excepted CertificateValidationException");
        } catch (final CertificateValidationException e) {
            assertEquals("Could not get response from certificate endpoint", e.getMessage());
        }
    }

    @Test
    public void testValidateSslCertificateSuccess() {
        final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");
        when(networkHttpClient.makeRequest(request)).thenReturn(new Response("", 200));

        Twilio.validateSslCertificate(networkHttpClient);
    }
}
