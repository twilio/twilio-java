```java
package com.twilio.example;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MultiRegionExample {
  public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

  public static void main(String[] args) {

    TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).region("us1").build();

    Message message = Message
        .creator(
            new PhoneNumber("+1XXXXXXXXXX"),
            new PhoneNumber("+1XXXXXXXXXX"),
            "This is the ship that made the Kessel Run in fourteen parsecs?"
        )
        .create(client);

    System.out.println(message.getSid());

    TwilioRestClient client2 = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).region("au1").build();

    Message message2 = Message
        .creator(
            new PhoneNumber("+1XXXXXXXXXX"),
            new PhoneNumber("+1XXXXXXXXXX"),
            "This is the ship that made the Kessel Run in fourteen parsecs?"
        )
        .create(client2);

    System.out.println(message2.getSid());
  }
}


```
