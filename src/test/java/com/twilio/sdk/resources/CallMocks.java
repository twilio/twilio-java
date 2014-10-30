package com.twilio.sdk.resources;

public class CallMocks {
    public static final String INSTANCE_JSON = "{" +
            "\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\n" +
            "\"annotation\": null,\n" +
            "\"answered_by\": null,\n" +
            "\"api_version\": \"2008-08-01\",\n" +
            "\"caller_name\": null,\n" +
            "\"date_created\": \"Mon, 29 Sep 2014 20:39:42 +0000\",\n" +
            "\"date_updated\": \"Mon, 29 Sep 2014 20:39:50 +0000\",\n" +
            "\"direction\": \"inbound\",\n" +
            "\"duration\": \"8\",\n" +
            "\"end_time\": \"Mon, 29 Sep 2014 20:39:50 +0000\",\n" +
            "\"forwarded_from\": \"+19143689587\",\n" +
            "\"from\": \"+16507843280\",\n" +
            "\"from_formatted\": \"(650) 784-3280\",\n" +
            "\"group_sid\": null,\n" +
            "\"parent_call_sid\": null,\n" +
            "\"phone_number_sid\": \"PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\n" +
            "\"price\": \"-0.01000\",\n" +
            "\"price_unit\": \"USD\",\n" +
            "\"sid\": \"CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\n" +
            "\"start_time\": \"Mon, 29 Sep 2014 20:39:42 +0000\",\n" +
            "\"status\": \"completed\",\n" +
            "\"subresource_uris\": {\n" +
            "    \"notifications\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Notifications.json\",\n" +
            "    \"recordings\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Recordings.json\"\n" +
            "},\n" +
            "\"to\": \"+19143689587\",\n" +
            "\"to_formatted\": \"(914) 368-9587\",\n" +
            "\"uri\": \"/2010-04-01/Accounts/ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Calls/CAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.json\"\n" +
            "}";

    public static final String FIRST_PAGE_JSON = "{\n" +
            "    \"calls\": [\n" +
                    INSTANCE_JSON +
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
}
