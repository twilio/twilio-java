package com.twilio.http;

import org.eclipse.jetty.server.NetworkConnector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class NetworkHttpClientRetryRequestTest {

    private Server httpServer;
    private TestRequestHandler testRequestHandler;
    private NetworkHttpClient client;
    private com.twilio.http.Request request;

    @Before
    public void setUp() throws Exception {
        testRequestHandler = new TestRequestHandler();

        httpServer = new Server(0);
        httpServer.setHandler(testRequestHandler);
        httpServer.start();

        NetworkConnector connector = (NetworkConnector) httpServer.getConnectors()[0];
        int serverPort = connector.getLocalPort();

        client = new NetworkHttpClient();
        request = new com.twilio.http.Request(HttpMethod.POST, "http://localhost:" + serverPort + "/test");
    }

    @After
    public void tearDown() throws Exception {
        httpServer.stop();
    }

    @Test
    public void should_not_indefinitely_hang_when_receiving_a_retryable_status_code_that_contains_no_response_body() {
        testRequestHandler.responseBody = false;
        testRequestHandler.responseCode = 500;

        Response response = client.reliableRequest(request);

        assertEquals(3, testRequestHandler.requestCount);
        assertEquals(500, response.getStatusCode());
    }

    @Test
    public void should_not_indefinitely_hang_when_receiving_a_retryable_status_code_that_contains_a_response_body() {
        testRequestHandler.responseBody = true;
        testRequestHandler.responseCode = 500;

        Response response = client.reliableRequest(request);

        assertEquals(3, testRequestHandler.requestCount);
        assertEquals(500, response.getStatusCode());
    }

    @Test
    public void should_only_make_one_request_if_the_status_code_is_not_retryable() {
        testRequestHandler.responseBody = true;
        testRequestHandler.responseCode = 200;

        Response response = client.reliableRequest(request);

        assertEquals(1, testRequestHandler.requestCount);
        assertEquals(200, response.getStatusCode());
    }

    private class TestRequestHandler extends AbstractHandler {
        private int requestCount = 0;
        private boolean responseBody = false;
        private int responseCode = 200;

        @Override
        public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
            requestCount++;
            httpServletResponse.setStatus(responseCode);

            if (responseBody) {
                try (PrintWriter writer = httpServletResponse.getWriter()) {
                    writer.append("RESPONSE BODY");
                    writer.flush();
                    writer.close();
                }
            }

            request.setHandled(true);
        }
    }
}