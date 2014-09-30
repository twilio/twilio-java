package com.twilio.examples;

import com.twilio.sdk.resources.Call;

public class FromJson {

    public static void main(String[] args) {
        String json = "{" +
                "\"account_sid\": \"ACca498dbda0fef21f361a9a3326354175\",\n" +
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
                "\"phone_number_sid\": \"PN5c269861c7f337f6876d6ef214a094cc\",\n" +
                "\"price\": \"-0.01000\",\n" +
                "\"price_unit\": \"USD\",\n" +
                "\"sid\": \"CA9e966bd3ef2abcfe941fb0a06e3fc027\",\n" +
                "\"start_time\": \"Mon, 29 Sep 2014 20:39:42 +0000\",\n" +
                "\"status\": \"completed\",\n" +
                "\"subresource_uris\": {\n" +
                "    \"notifications\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Notifications.json\",\n" +
                "    \"recordings\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Recordings.json\"\n" +
                "},\n" +
                "\"to\": \"+19143689587\",\n" +
                "\"to_formatted\": \"(914) 368-9587\",\n" +
                "\"uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027.json\"\n" +
                "}";

        Call call = Call.fromJson(json);

        System.out.println(call.getAccountSid());
        System.out.println(call.getDuration());
        for (String key : call.getSubresourceUris().keySet()) {
            System.out.println(key + ": " + call.getSubresourceUris().get(key));
        }
    }
}
