package com.twilio.sdk;

import com.twilio.sdk.auth.AccessToken;
import com.twilio.sdk.resource.instance.Call;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;


@RunWith(PowerMockRunner.class)
@PrepareForTest(DefaultHttpClient.class)
@PowerMockIgnore("javax.*")
public class TwilioRestClientIntegrationTest {

    private static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    private static final String AUTH_TOKEN = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    private CredentialsProvider credentialsProvider;
    private DefaultHttpClient fakeHttpClient;
    private HttpResponse fakeHttpResponse;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        credentialsProvider = new BasicCredentialsProvider();
        fakeHttpClient = PowerMockito.mock(DefaultHttpClient.class);
        fakeHttpResponse = new BasicHttpResponse(new ProtocolVersion("HTTP", 1, 1), 200, "blah");
        fakeHttpResponse.setHeader(new BasicHeader("Content-Type", "application/json"));

        Mockito.when(fakeHttpClient.getCredentialsProvider()).thenReturn(credentialsProvider);
        Mockito.when(fakeHttpClient.execute(Mockito.any(HttpUriRequest.class))).thenReturn(fakeHttpResponse);
    }

    @Test
    public void testCreateCallWithAuthToken() throws Exception {
        Map<String, String> response_json = new HashMap<String, String>();
        response_json.put("from", "+123");
        fakeHttpResponse.setEntity(new StringEntity(mapper.writeValueAsString(response_json), ContentType.APPLICATION_JSON));

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        client.setHttpClient(fakeHttpClient);

        Map<String, String> request_data = new HashMap<String, String>();
        request_data.put("From", "+123");

        Call call = client.getAccount().getCallFactory().create(request_data);

        // validate call
        assertEquals("+123", call.getFrom());

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
        Mockito.verify(fakeHttpClient).execute(captor.capture());
        HttpPost request = captor.getValue();

        // validate http request
        assertEquals("https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Calls.json", request.getURI().toString());
        assertEquals(ACCOUNT_SID, credentialsProvider.getCredentials(AuthScope.ANY).getUserPrincipal().getName());
        assertEquals(AUTH_TOKEN, credentialsProvider.getCredentials(AuthScope.ANY).getPassword());
    }

    @Test
    public void testCreateCallWithAccessToken() throws Exception {
        Map<String, String> response_json = new HashMap<String, String>();
        response_json.put("from", "+123");
        fakeHttpResponse.setEntity(new StringEntity(mapper.writeValueAsString(response_json), ContentType.APPLICATION_JSON));

        AccessToken token = new AccessToken("signingId", ACCOUNT_SID, "signingKey");
        String jwt = token.toJWT();

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, jwt);
        client.setHttpClient(fakeHttpClient);

        Map<String, String> request_data = new HashMap<String, String>();
        request_data.put("From", "+123");

        Call call = client.getAccount().getCallFactory().create(request_data);

        // validate call
        assertEquals("+123", call.getFrom());

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
        Mockito.verify(fakeHttpClient).execute(captor.capture());
        HttpPost request = captor.getValue();

        // validate http request
        assertEquals("https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Calls.json", request.getURI().toString());
        assertEquals("Token", credentialsProvider.getCredentials(AuthScope.ANY).getUserPrincipal().getName());
        assertEquals(jwt, credentialsProvider.getCredentials(AuthScope.ANY).getPassword());
    }

    @Test
    public void testCreateCallWithSigningKey() throws Exception {
        Map<String, String> response_json = new HashMap<String, String>();
        response_json.put("from", "+123");
        fakeHttpResponse.setEntity(new StringEntity(mapper.writeValueAsString(response_json), ContentType.APPLICATION_JSON));

        TwilioRestClient client = new TwilioRestClient("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        client.setRequestAccountSid(ACCOUNT_SID);
        client.setHttpClient(fakeHttpClient);

        Map<String, String> request_data = new HashMap<String, String>();
        request_data.put("From", "+123");

        Call call = client.getAccount().getCallFactory().create(request_data);

        // validate call
        assertEquals("+123", call.getFrom());

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
        Mockito.verify(fakeHttpClient).execute(captor.capture());
        HttpPost request = captor.getValue();

        // validate http request
        assertEquals("https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Calls.json", request.getURI().toString());
        assertEquals("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", credentialsProvider.getCredentials(AuthScope.ANY).getUserPrincipal().getName());
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", credentialsProvider.getCredentials(AuthScope.ANY).getPassword());
    }
}
