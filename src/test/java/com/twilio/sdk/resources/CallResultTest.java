package com.twilio.sdk.resources;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.http.ConsumableResponse;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CallResultTest {
    public static final String JSON = "{\n" +
            "    \"calls\": [\n" +
            "        {\n" +
            "            \"account_sid\": \"ACca498dbda0fef21f361a9a3326354175\", \n" +
            "            \"annotation\": null, \n" +
            "            \"answered_by\": null, \n" +
            "            \"api_version\": \"2010-04-01\", \n" +
            "            \"caller_name\": null, \n" +
            "            \"date_created\": \"Sat, 20 Sep 2014 00:39:46 +0000\", \n" +
            "            \"date_updated\": \"Sat, 20 Sep 2014 00:39:47 +0000\", \n" +
            "            \"direction\": \"outbound-dial\", \n" +
            "            \"duration\": \"0\", \n" +
            "            \"end_time\": \"Sat, 20 Sep 2014 00:39:47 +0000\", \n" +
            "            \"forwarded_from\": \"+14038003428\", \n" +
            "            \"from\": \"+14038003319\", \n" +
            "            \"from_formatted\": \"(403) 800-3319\", \n" +
            "            \"group_sid\": null, \n" +
            "            \"parent_call_sid\": \"CAfd6a12335cbdaa04f412ff1bc90d88ae\", \n" +
            "            \"phone_number_sid\": \"PN7290cde132d50043b5c1ca12cd366cd9\", \n" +
            "            \"price\": null, \n" +
            "            \"price_unit\": \"USD\", \n" +
            "            \"sid\": \"CA7e123477b24eb76416705b82a02a1e8b\", \n" +
            "            \"start_time\": \"Sat, 20 Sep 2014 00:39:46 +0000\", \n" +
            "            \"status\": \"failed\", \n" +
            "            \"subresource_uris\": {\n" +
            "                \"notifications\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA7e123477b24eb76416705b82a02a1e8b/Notifications.json\", \n" +
            "                \"recordings\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA7e123477b24eb76416705b82a02a1e8b/Recordings.json\"\n" +
            "            }, \n" +
            "            \"to\": \"+4402076301000\", \n" +
            "            \"to_formatted\": \"+4402076301000\", \n" +
            "            \"uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA7e123477b24eb76416705b82a02a1e8b.json\"\n" +
            "        }\n" +
            "    ], \n" +
            "    \"end\": 49, \n" +
            "    \"first_page_uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls.json?PageSize=50&Page=0\", \n" +
            "    \"last_page_uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls.json?PageSize=50&Page=33\", \n" +
            "    \"next_page_uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls.json?PageSize=50&Page=1&AfterSid=CA7e123477b24eb76416705b82a02a1e8b\", \n" +
            "    \"num_pages\": 34, \n" +
            "    \"page\": 0, \n" +
            "    \"page_size\": 50, \n" +
            "    \"previous_page_uri\": null, \n" +
            "    \"start\": 0, \n" +
            "    \"total\": 1682, \n" +
            "    \"uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls.json?PageSize=50&Page=0\"\n" +
            "}\n";

    @Test
    public void testFromJson() throws Exception {
        Twilio.setAccountSid("AC123");
        Twilio.setAuthToken("AUTH TOKEN");
        Twilio.useMockResponses(new ConsumableResponse(JSON, 200, 5));

        Result<Call> result = Call.find().build();

        int i = 0;

        for (Call call : result) {
            System.out.println(call.getSid());
            i++;
            if (i > 10) {
                break;
            }
        }

        assertNotNull(result);
    }

}
