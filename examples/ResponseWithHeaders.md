```java

package com.twilio;

import com.twilio.base.Page;
import com.twilio.base.ResourceSet;
import com.twilio.base.ResourceSetResponse;
import com.twilio.base.TwilioResponse;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageReader;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Twilio.init("ACCOUNT_SID", "AUTH_TOKEN");
        deleteMessage();
    }

    public static void createMessage() {
        TwilioResponse response = Message
                .creator(new com.twilio.type.PhoneNumber("TO_NUMBER"),
                        new com.twilio.type.PhoneNumber("FROM_NUMBER"),
                        "Message body to send")
                .createWithResponse();
        
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getContent());
        
    }

    public static void fetchMessage() {
        TwilioResponse response = Message.fetcher("SM1123").fetchWithResponse();

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getContent());
    }

    public static void updateMessage() {
        TwilioResponse response = Message.updater("SM1123").setBody("").updateWithResponse();
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getContent());
    }

    public static void deleteMessage() {
        TwilioResponse response  = Message.deleter("SM1123").deleteWithResponse();
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getContent());
    }

    private static void readMessages() {
        ResourceSetResponse<Message> result = Message.reader()
                .limit(10)
                .pageSize(3)
                .readWithResponse(Twilio.getRestClient());

        System.out.println(result.getStatusCode());
        System.out.println(result.getHeaders());

        // Iterate (respects limit and pageSize)
        for (Message message : result) {
            System.out.println(message.getSid());
        }
    }
}

```