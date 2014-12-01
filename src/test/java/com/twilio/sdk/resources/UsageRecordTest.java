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

import java.net.URL;

public class UsageRecordTest {

    private static final String LIST_JSON_RESPONSE = "{\"end\": 0, \"first_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records.json?Page=0&PageSize=50\", \"last_page_uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records.json?Page=0&PageSize=50\", \"next_page_uri\": null, \"num_pages\": 1, \"page\": 0, \"page_size\": 50, \"previous_page_uri\": null, \"start\": 0, \"total\": 1, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records.json\", \"usage_records\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"api_version\": \"2010-04-01\", \"category\": \"calls\", \"count\": \"0\", \"count_unit\": \"calls\", \"description\": \"Total Calls\", \"end_date\": \"2014-11-26\", \"price\": \"0\", \"price_unit\": \"usd\", \"start_date\": \"2011-08-22\", \"subresource_uris\": {\"all_time\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records/AllTime.json\", \"daily\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records/Daily.json\", \"last_month\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records/LastMonth.json\", \"monthly\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records/Monthly.json\", \"this_month\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records/ThisMonth.json\", \"today\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records/Today.json\", \"yearly\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records/Yearly.json\", \"yesterday\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records/Yesterday.json\"}, \"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Usage/Records.json?StartDate=2011-08-22&EndDate=2014-11-26\", \"usage\": \"0\", \"usage_unit\": \"minutes\"}]}";

    @Mocked
    private Request request;

    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testList() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = new URL(
                    "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Usage/Records.json".replace("{AccountSid}",
                                                                                                         "AC123"));
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        UsageRecord.list()
                   .execute();
    }

    @Test
    public void testListByCategory() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = new URL(
                    "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Usage/Records.json?Category=calleridlookups".replace(
                            "{AccountSid}", "AC123"));
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        UsageRecord.list()
                   .byCategory(UsageRecord.Category.CALLER_ID_LOOKUPS)
                   .execute();
    }

    @Test
    public void testListBySubResource() throws Exception {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response(LIST_JSON_RESPONSE, TwilioRestClient.HTTP_STATUS_CODE_OK);
            request.constructURL();
            result = new URL("https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Usage/Records/Daily.json".replace(
                    "{AccountSid}", "AC123"));
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};

        UsageRecord.list()
                   .bySubResource(UsageRecordSubResource.DAILY)
                   .execute();
    }

}
