package com.twilio.sdk.resources.ipmessaging.v1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.TwilioException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static com.twilio.sdk.TwilioTest.serialize;
import static org.junit.Assert.*;

public class UserTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testFetchRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          TwilioRestClient.Domains.IPMESSAGING,
                                          "/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users/USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                                          "AC123");
            
            
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};
        
        try {
            User.fetch("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testDeleteRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          TwilioRestClient.Domains.IPMESSAGING,
                                          "/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users/USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                                          "AC123");
            
            
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};
        
        try {
            User.delete("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateRequest() {
                    new NonStrictExpectations() {{
                        Request request = new Request(HttpMethod.POST,
                                                      TwilioRestClient.Domains.IPMESSAGING,
                                                      "/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users",
                                                      "AC123");
                        request.addPostParam("Identity", serialize("identity"));
        request.addPostParam("RoleSid", serialize("RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
                        
                        twilioRestClient.request(request);
                        times = 1;
                        result = new Response("", 500);
                        twilioRestClient.getAccountSid();
                        result = "AC123";
                    }};
        
        try {
            User.create("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "identity", "RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          TwilioRestClient.Domains.IPMESSAGING,
                                          "/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users",
                                          "AC123");
            
            request.addQueryParam("PageSize", "50");
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};
        
        try {
            User.read("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testUpdateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          TwilioRestClient.Domains.IPMESSAGING,
                                          "/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users/USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                                          "AC123");
            request.addPostParam("RoleSid", serialize("RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
            
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};
        
        try {
            User.update("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }
}