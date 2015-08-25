package com.twilio.sdk.resource.instance;

import com.twilio.sdk.LookupsClient;
import com.twilio.sdk.TwilioMonitorClient;
import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioTaskRouterClient;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Before;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicRequestTester {

	final String accountSid = "AC0123456789abcdef0123456789abcdef";
	final String authtoken = "0123456789abcdef0123456789abcdef";

	@Mock
	HttpClient httpClient;

	protected TwilioRestClient restClient = new TwilioRestClient(accountSid, authtoken);
	protected TwilioPricingClient pricingClient = new TwilioPricingClient(accountSid, authtoken);
	protected TwilioTaskRouterClient taskRouterClient = new TwilioTaskRouterClient(accountSid, authtoken);
	protected LookupsClient lookupsClient = new LookupsClient(accountSid, authtoken);
	protected TwilioMonitorClient monitorClient = new TwilioMonitorClient(accountSid, authtoken);

	protected BasicHttpResponse response = mock(BasicHttpResponse.class);
	protected Header[] headers = {mock(Header.class)};
	protected StatusLine status_line = mock(StatusLine.class);
	protected HttpEntity entity = mock(HttpEntity.class);

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		restClient.setHttpClient(httpClient);
		pricingClient.setHttpClient(httpClient);
		taskRouterClient.setHttpClient(httpClient);
		lookupsClient.setHttpClient(httpClient);
		monitorClient.setHttpClient(httpClient);

		when(headers[0].getValue()).thenReturn("application/xml");
		when(response.getHeaders("Content-Type")).thenReturn(headers);
		when(response.getStatusLine()).thenReturn(status_line);
		when(status_line.getStatusCode()).thenReturn(200);
		when(httpClient.execute(Matchers.<HttpUriRequest>anyObject())).thenReturn(response);
		when(response.getEntity()).thenReturn(entity);
	}

	protected void setExpectedServerReturnCode(final int code) throws Exception {
		when(status_line.getStatusCode()).thenReturn(code);
	}

	protected void setExpectedServerAnswer(final String resource_name) throws Exception {
		if (resource_name == null) {    // empty body
			when(entity.getContent()).thenReturn(new ByteArrayInputStream(new byte[0]));
		} else {
			when(entity.getContent()).thenReturn(BasicRequestTester.class.getResourceAsStream(resource_name));
		}
	}

	protected void setExpectedServerContentType(final String content_type) {
		when(headers[0].getValue()).thenReturn("application/json");
	}

}
