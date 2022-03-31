/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.fax.v1;

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

public class FaxTest {
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
                                          Domains.FAX.toString(),
                                          "/v1/Faxes/FXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Fax.fetcher("FXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testFetchResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"v1\",\"date_created\": \"2015-07-30T20:00:00Z\",\"date_updated\": \"2015-07-30T20:00:00Z\",\"direction\": \"outbound\",\"from\": \"+14155551234\",\"media_url\": \"https://www.example.com/fax.pdf\",\"media_sid\": \"MEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"num_pages\": null,\"price\": null,\"price_unit\": null,\"quality\": \"fine\",\"sid\": \"FXaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"queued\",\"to\": \"+14155554321\",\"duration\": null,\"links\": {\"media\": \"https://fax.twilio.com/v1/Faxes/FXaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media\"},\"url\": \"https://fax.twilio.com/v1/Faxes/FXaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Fax.fetcher("FXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch());
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.FAX.toString(),
                                          "/v1/Faxes");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Fax.reader().read();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadEmptyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"faxes\": [],\"meta\": {\"first_page_url\": \"https://fax.twilio.com/v1/Faxes?DateCreatedOnOrBefore=2017-04-01T00%3A00%3A00Z&To=%2B14155554321&DateCreatedAfter=2017-03-31T00%3A00%3A00Z&From=%2B14155551234&PageSize=50&Page=0\",\"key\": \"faxes\",\"next_page_url\": null,\"page\": 0,\"page_size\": 50,\"previous_page_url\": null,\"url\": \"https://fax.twilio.com/v1/Faxes?DateCreatedOnOrBefore=2017-04-01T00%3A00%3A00Z&To=%2B14155554321&DateCreatedAfter=2017-03-31T00%3A00%3A00Z&From=%2B14155551234&PageSize=50&Page=0\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Fax.reader().read());
    }

    @Test
    public void testReadFullResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"faxes\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"api_version\": \"v1\",\"date_created\": \"2015-07-30T20:00:00Z\",\"date_updated\": \"2015-07-30T20:00:00Z\",\"direction\": \"outbound\",\"from\": \"+14155551234\",\"media_url\": \"https://www.example.com/fax.pdf\",\"media_sid\": \"MEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"num_pages\": null,\"price\": null,\"price_unit\": null,\"quality\": \"fine\",\"sid\": \"FXaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"queued\",\"to\": \"+14155554321\",\"duration\": null,\"links\": {\"media\": \"https://fax.twilio.com/v1/Faxes/FXaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media\"},\"url\": \"https://fax.twilio.com/v1/Faxes/FXaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}],\"meta\": {\"first_page_url\": \"https://fax.twilio.com/v1/Faxes?To=%2B14155554321&From=%2B14155551234&PageSize=50&Page=0\",\"key\": \"faxes\",\"next_page_url\": null,\"page\": 0,\"page_size\": 50,\"previous_page_url\": null,\"url\": \"https://fax.twilio.com/v1/Faxes?To=%2B14155554321&From=%2B14155551234&PageSize=50&Page=0\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Fax.reader().read());
    }

    @Test
    public void testDeleteRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.DELETE,
                                          Domains.FAX.toString(),
                                          "/v1/Faxes/FXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Fax.deleter("FXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete();
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

        Fax.deleter("FXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete();
    }
}