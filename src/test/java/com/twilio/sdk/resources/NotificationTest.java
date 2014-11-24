package com.twilio.sdk.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NotificationTest {

    private static final String INSTANCE_JSON_RESPONSE = "{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2008-08-01\", \"call_sid\": \"MMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"date_created\": \"Thu, 23 Oct 2014 21:45:31 +0000\", \"date_updated\": \"Thu, 23 Oct 2014 21:50:35 +0000\", \"error_code\": \"11200\", \"log\": \"0\", \"message_date\": \"Thu, 23 Oct 2014 21:45:31 +0000\", \"message_text\": \"url=http%3A%2F%2Fexample.com%2Fimage.jpg&httpResponse=404&Msg=Attempt+to+retrieve+media+failed.&LogLevel=ERROR&EmailNotification=false\", \"more_info\": \"https://www.twilio.com/docs/errors/11200\", \"request_method\": \"GET\", \"request_url\": \"http://example.com/image.jpg\", \"request_variables\": \"\", \"response_body\": \"\", \"response_headers\": \"Accept-Ranges=bytes&Cache-Control=max-age%3D604800&Content-Type=text%2Fhtml&Date=Fri%2C+17+Oct+2014+13%3A48%3A41+GMT&ETag=%22359670651%22&Expires=Fri%2C+24+Oct+2014+13%3A48%3A41\", \"sid\": \"NOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications/NOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"}";

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json?PageSize=50&Page=0\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json?PageSize=50&Page=0\", \"next_page_uri\": null, \"notifications\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2008-08-01\", \"call_sid\": \"CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"date_created\": \"Mon, 22 Aug 2011 20:58:45 +0000\", \"date_updated\": \"Mon, 22 Aug 2011 20:58:45 +0000\", \"error_code\": \"11200\", \"log\": \"0\", \"message_date\": \"Mon, 22 Aug 2011 20:58:45 +0000\", \"message_text\": \"EmailNotification=false&LogLevel=ERROR&sourceComponent=12000&Msg=&httpResponse=403&ErrorCode=11200&url=http%3A%2F%2Fec2-107-20-68-95.compute-1.amazonaws.com%2F\", \"more_info\": \"https://www.twilio.com/docs/errors/11200\", \"request_method\": \"POST\", \"request_url\": \"http://ec2-107-20-68-95.compute-1.amazonaws.com/\", \"sid\": \"NOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications/NOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}], \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json?PageSize=50&Page=0\"}";

    @Mocked
    private Request request;

    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testFetch() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(INSTANCE_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Notifications/sid.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Notification.fetch("sid")
                    .execute();
    }

    @Test
    public void testFromJsonString() {
        new NonStrictExpectations() {{
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Notification instance = Notification.fromJson(INSTANCE_JSON_RESPONSE, Twilio.getRestClient()
                                                                                    .getObjectMapper());
        assertNotNull(instance);
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Notifications.json".replace(
                    "{AccountSid}", "AC123");
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        Notification.list()
                    .execute();
    }
}
