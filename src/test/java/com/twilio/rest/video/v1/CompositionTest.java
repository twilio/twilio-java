/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1;

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

public class CompositionTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Mocked
    private Twilio tw;

    @Before
    public void setUp() throws Exception {
        tw = new Twilio("AC123", "AUTH TOKEN");
    }

    @Test
    public void testFetchRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.VIDEO.toString(),
                                          "/v1/Compositions/CJXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Composition.fetcher("CJXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testFetchResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"completed\",\"date_created\": \"2015-07-30T20:00:00Z\",\"date_completed\": \"2015-07-30T20:01:33Z\",\"date_deleted\": null,\"sid\": \"CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"room_sid\": \"RMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"audio_sources\": [\"PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"user*\"],\"audio_sources_excluded\": [\"RTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"],\"video_layout\": {\"grid\": {\"video_sources\": [\"*\"],\"video_sources_excluded\": [\"MTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"],\"reuse\": \"show_oldest\",\"x_pos\": 100,\"y_pos\": 600,\"z_pos\": 10,\"width\": 0,\"height\": 0,\"max_columns\": 0,\"max_rows\": 0,\"cells_excluded\": []},\"pip\": {\"video_sources\": [\"RTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab\"],\"video_sources_excluded\": [],\"reuse\": \"none\",\"x_pos\": 100,\"y_pos\": 600,\"z_pos\": 10,\"width\": 0,\"height\": 0,\"max_columns\": 0,\"max_rows\": 0,\"cells_excluded\": []}},\"resolution\": \"1280x720\",\"format\": \"webm\",\"bitrate\": 64,\"size\": 4,\"duration\": 6,\"trim\": true,\"media_external_location\": null,\"encryption_key\": null,\"url\": \"https://video.twilio.com/v1/Compositions/CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"media\": \"https://video.twilio.com/v1/Compositions/CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Composition.fetcher("CJXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch(tw));
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          Domains.VIDEO.toString(),
                                          "/v1/Compositions");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Composition.reader().read(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadEnqueuedResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"compositions\": [],\"meta\": {\"page\": 0,\"page_size\": 10,\"first_page_url\": \"https://video.twilio.com/v1/Compositions?Status=enqueued&PageSize=10&Page=0\",\"previous_page_url\": null,\"url\": \"https://video.twilio.com/v1/Compositions?Status=enqueued&PageSize=10&Page=0\",\"next_page_url\": null,\"key\": \"compositions\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Composition.reader().read(tw));
    }

    @Test
    public void testReadEmptyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"compositions\": [],\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://video.twilio.com/v1/Compositions?Status=completed&PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://video.twilio.com/v1/Compositions?Status=completed&PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"compositions\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Composition.reader().read(tw));
    }

    @Test
    public void testReadResultsResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"compositions\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"completed\",\"date_created\": \"2015-07-30T20:00:00Z\",\"date_completed\": \"2015-07-30T20:01:33Z\",\"date_deleted\": null,\"sid\": \"CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"room_sid\": \"RMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"audio_sources\": [\"RTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"user*\"],\"audio_sources_excluded\": [],\"video_layout\": {\"grid\": {\"video_sources\": [\"user*\"],\"video_sources_excluded\": [],\"reuse\": \"show_oldest\",\"x_pos\": 100,\"y_pos\": 600,\"z_pos\": 10,\"width\": 0,\"height\": 0,\"max_columns\": 0,\"max_rows\": 0,\"cells_excluded\": []},\"pip\": {\"video_sources\": [\"RTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab\"],\"video_sources_excluded\": [],\"reuse\": \"none\",\"x_pos\": 100,\"y_pos\": 600,\"z_pos\": 10,\"width\": 0,\"height\": 0,\"max_columns\": 0,\"max_rows\": 0,\"cells_excluded\": []}},\"resolution\": \"1280x720\",\"format\": \"webm\",\"bitrate\": 64,\"size\": 4,\"duration\": 6,\"trim\": true,\"media_external_location\": null,\"encryption_key\": null,\"url\": \"https://video.twilio.com/v1/Compositions/CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"media\": \"https://video.twilio.com/v1/Compositions/CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media\"}}],\"meta\": {\"page\": 0,\"page_size\": 50,\"first_page_url\": \"https://video.twilio.com/v1/Compositions?Status=completed&RoomSid=RMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa&DateCreatedAfter=2017-01-01T00%3A00%3A01Z&DateCreatedBefore=2017-12-31T23%3A59%3A59Z&PageSize=50&Page=0\",\"previous_page_url\": null,\"url\": \"https://video.twilio.com/v1/Compositions?Status=completed&RoomSid=RMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa&DateCreatedAfter=2017-01-01T00%3A00%3A01Z&DateCreatedBefore=2017-12-31T23%3A59%3A59Z&PageSize=50&Page=0\",\"next_page_url\": null,\"key\": \"compositions\"}}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        assertNotNull(Composition.reader().read(tw));
    }

    @Test
    public void testDeleteRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.DELETE,
                                          Domains.VIDEO.toString(),
                                          "/v1/Compositions/CJXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Composition.deleter("CJXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete(tw);
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

        Composition.deleter("CJXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete(tw);
    }

    @Test
    public void testCreateRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.POST,
                                          Domains.VIDEO.toString(),
                                          "/v1/Compositions");
            request.addPostParam("RoomSid", serialize("RMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};

        try {
            Composition.creator("RMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").create(tw);
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"status\": \"processing\",\"date_created\": \"2015-07-30T20:00:00Z\",\"date_completed\": null,\"date_deleted\": null,\"sid\": \"CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"room_sid\": \"RMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"audio_sources\": [\"RTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"user*\"],\"audio_sources_excluded\": [\"RTbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"],\"video_layout\": {\"custom\": {\"video_sources\": [\"user*\"],\"video_sources_excluded\": [\"RTcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"],\"reuse\": \"show_oldest\",\"x_pos\": 100,\"y_pos\": 600,\"z_pos\": 10,\"width\": 800,\"height\": 0,\"max_columns\": 0,\"max_rows\": 0,\"cells_excluded\": [2,3]}},\"trim\": true,\"format\": \"mp4\",\"resolution\": \"1920x1080\",\"bitrate\": 0,\"size\": 0,\"duration\": 0,\"media_external_location\": null,\"encryption_key\": null,\"url\": \"https://video.twilio.com/v1/Compositions/CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"links\": {\"media\": \"https://video.twilio.com/v1/Compositions/CJaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Media\"}}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Composition.creator("RMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").create(tw);
    }
}