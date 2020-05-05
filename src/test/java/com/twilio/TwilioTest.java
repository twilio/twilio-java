package com.twilio;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.exception.ApiException;
import com.twilio.exception.AuthenticationException;
import com.twilio.exception.CertificateValidationException;
import com.twilio.http.HttpMethod;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.Executors;

import mockit.Mocked;
import mockit.Expectations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TwilioTest {

    public static String serialize(Object object) {
        return object.toString();
    }

    public static String serialize(List list) {
        // NOTE: This relies on the fact that integration tests only ever generate single element lists.
        return list.get(0).toString();
    }

    @Mocked
    private NetworkHttpClient networkHttpClient;

    @Mocked
    private TwilioRestClient twilioRestClient;

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
    public void testDestroyExecutorService() {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(
                Executors.newCachedThreadPool());
        Twilio.setExecutorService(listeningExecutorService);
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
        new Expectations() {{
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
        new Expectations() {{
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
        new Expectations() {{
            final Request request = new Request(HttpMethod.GET, "https://api.twilio.com:8443");
            networkHttpClient.makeRequest(request);
            times = 1;
            result = new Response("", 200);
        }};

        Twilio.validateSslCertificate();
    }

    @Test
    public void testEdgeNoRegion() {
        new Expectations() {{
            final Request request = new Request(HttpMethod.GET, "https://api.edge.us1.twilio.com/test");
            networkHttpClient.makeRequest(request);
            times = 1;
            result = new Response("", 200);
        }};

        Twilio.init("accountSid", "authToken");
        Twilio.setEdge("edge");
        Request request = new Request(HttpMethod.GET, "api", "/test", null, Twilio.getEdge());
        //Request request = new Request(HttpMethod.GET, "https://api.edge.us1.twilio.com/test");
        Response result = networkHttpClient.makeRequest(request);
        assertEquals(200, result.getStatusCode());
    }
}

//     @Test
//     public void testEdgePredifinedRegion() {
//         new Expectations() {{
//             final Request request = new Request(HttpMethod.GET, "https://api.edge.region.twilio.com/test");
//             TwilioRestClient twilioRestClient = new TwilioRestClient.Builder("AC123", "AUTH TOKEN").build();
//             twilioRestClient.request(request);
//             times = 1;
//             result = new Response("", 200);
//         }};

//         Twilio.init("accountSid", "authToken");
//         Twilio.setEdge("edge");
//         TwilioRestClient client = Twilio.getRestClient();
//         Request request = new Request(HttpMethod.GET, "api", "/test", "region", Twilio.getEdge());
//         Response result = client.request(request);
//         assertEquals(200, result.getStatusCode());
//     }

//     @Test
//     public void testRegionNoEdge() {
//         new Expectations() {{
//             final Request request = new Request(HttpMethod.GET, "https://api.au1.twilio.com/test");
//             TwilioRestClient twilioRestClient = new TwilioRestClient.Builder("AC123", "AUTH TOKEN").build();
//             twilioRestClient.request(request);
//             times = 1;
//             result = new Response("", 200);
//         }};

//         Twilio.init("accountSid", "authToken");
//         Twilio.setRegion("au1");
//         TwilioRestClient client = Twilio.getRestClient();
//         Request request = new Request(HttpMethod.GET, "api", "/test");
//         Response result = client.request(request);
//         assertEquals(200, result.getStatusCode());
//     }

//     @Test
//     public void testRegionAndEdge() {
//         new Expectations() {{
//             final Request request = new Request(HttpMethod.GET, "https://api.edge.region.twilio.com/test");
//             TwilioRestClient twilioRestClient = new TwilioRestClient.Builder("AC123", "AUTH TOKEN").build();
//             twilioRestClient.request(request);
//             times = 1;
//             result = new Response("", 200);
//         }};

//         Twilio.init("accountSid", "authToken");
//         Twilio.setEdge("edge");
//         Twilio.setRegion("region");
//         TwilioRestClient client = Twilio.getRestClient();
//         Request request = new Request(HttpMethod.GET, "api", "/test", Twilio.getRegion(), Twilio.getEdge());
//         Response result = client.request(request);
//         assertEquals(200, result.getStatusCode());
//     }

//     @Test
//     public void testRegionExistingRegion() {
//         new Expectations() {{
//             final Request request = new Request(HttpMethod.GET, "https://api.region.twilio.com/test");
//             TwilioRestClient twilioRestClient = new TwilioRestClient.Builder("AC123", "AUTH TOKEN").build();
//             twilioRestClient.request(request);
//             times = 1;
//             result = new Response("", 200);
//         }};

//         Twilio.init("accountSid", "authToken");
//         Twilio.setRegion("region");
//         TwilioRestClient client = Twilio.getRestClient();
//         assertEquals("region", client.getRegion());
//         Request request = new Request(HttpMethod.GET, "api", "/test", "region2");
//         //Request request = new Request(HttpMethod.GET, "https://api.region2.twilio.com/test");
//         Response result = client.request(request);
//         assertEquals(200, result.getStatusCode());
//     }
// }
