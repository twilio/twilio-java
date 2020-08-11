/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.numbers.v2.regulatorycompliance.bundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.TwilioException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static com.twilio.TwilioTest.serialize;
import static org.junit.Assert.*;

public class ItemAssignmentTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testCreateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.NUMBERS.toString(),
                                          "/v2/RegulatoryCompliance/Bundles/BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/ItemAssignments");
            request.addPostParam("ObjectSid", serialize("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            ItemAssignment.creator("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").create();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"BVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"bundle_sid\": \"BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"object_sid\": \"RDaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"date_created\": \"2019-07-31T02:34:41Z\",\"url\": \"https://numbers.twilio.com/v2/RegulatoryCompliance/Bundles/BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ItemAssignments/BVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        ItemAssignment.creator("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").create();
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.NUMBERS.toString(),
                                          "/v2/RegulatoryCompliance/Bundles/BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/ItemAssignments");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            ItemAssignment.reader("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadEmptyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"results\": [],\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://numbers.twilio.com/v2/RegulatoryCompliance/Bundles/BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ItemAssignments?PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://numbers.twilio.com/v2/RegulatoryCompliance/Bundles/BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ItemAssignments?PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"results\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(ItemAssignment.reader("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testReadFullResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"results\": [{\"sid\": \"BVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"bundle_sid\": \"BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"object_sid\": \"RDaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"date_created\": \"2019-07-31T02:34:41Z\",\"url\": \"https://numbers.twilio.com/v2/RegulatoryCompliance/Bundles/BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ItemAssignments/BVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}],\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://numbers.twilio.com/v2/RegulatoryCompliance/Bundles/BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ItemAssignments?PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://numbers.twilio.com/v2/RegulatoryCompliance/Bundles/BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ItemAssignments?PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"results\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(ItemAssignment.reader("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").read());
    }

    @Test
    public void testFetchRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.NUMBERS.toString(),
                                          "/v2/RegulatoryCompliance/Bundles/BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/ItemAssignments/BVXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            ItemAssignment.fetcher("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "BVXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testFetchResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"sid\": \"BVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"bundle_sid\": \"BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"object_sid\": \"RDaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"date_created\": \"2019-07-31T02:34:41Z\",\"url\": \"https://numbers.twilio.com/v2/RegulatoryCompliance/Bundles/BUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/ItemAssignments/BVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(ItemAssignment.fetcher("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "BVXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch());
    }

    @Test
    public void testDeleteRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.DELETE,
                                          Domains.NUMBERS.toString(),
                                          "/v2/RegulatoryCompliance/Bundles/BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/ItemAssignments/BVXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            ItemAssignment.deleter("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "BVXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testDeleteResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("null", TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        ItemAssignment.deleter("BUXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "BVXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete();
    }
}