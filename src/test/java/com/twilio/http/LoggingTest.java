package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.rest.Domains;
import com.twilio.rest.api.v2010.account.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LoggingTest {
    private ByteArrayOutputStream output;
    private PrintStream originalStream;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    public void logCapturingSetup() {
        output = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(output);
        originalStream = System.out;
        System.setOut(outputStream);
    }

    public void finishLogCapturingSetup(Request request) {
        TwilioRestClient twilioRestClient = Twilio.getRestClient();
        twilioRestClient.logRequest(request);
        System.out.flush();
        System.setOut(originalStream);
    }

    @Test
    public void testDebugLogging() {
        logCapturingSetup();
        Twilio.setLoggerConfiguration("src/main/java/com/twilio/example/log4j2.xml");
        final Request request = new Request(HttpMethod.GET, Domains.API.toString(),
                "/2010-04-01/Accounts/AC123/Messages/MM123.json");
        request.addHeaderParam("Authorization", "authorization value");
        request.addHeaderParam("Test Header", "test value");
        finishLogCapturingSetup(request);
        Assert.assertTrue(output.toString().contains("GET"));
        Assert.assertFalse(output.toString().contains("Authorization"));
    }

    @Test
    public void testUsingDefaultConfigurationFileDebugLogging() {
        logCapturingSetup();
        final Request request = new Request(HttpMethod.GET, Domains.API.toString(),
                "/2010-04-01/Accounts/AC123/Messages/MM123.json");
        request.addHeaderParam("Authorization", "authorization value");
        request.addHeaderParam("Test Header", "test value");
        finishLogCapturingSetup(request);
        Assert.assertTrue(output.toString().isEmpty());
    }
}
