package com.twilio.sdk.examples;
/**
 * Author: wge
 * Date: 26/07/2015
 * Time: 16:33
 *
 * Taken from https://www.twilio.com/docs/api/rest/sending-messages
 * This is simpler as the client just cares about taking key/vals. The hard work is encapsulated inside the new method execute().
 */

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Example
{
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC5ef8732a3c49700934481addd5ce1659";
    public static final String AUTH_TOKEN = "{{ auth_token }}";

    public static void main(String[] args) throws TwilioRestException
    {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        Map<String,String> map = new ConcurrentHashMap<String,String>();
        map.put(MessageField.BODY, "Jenny please?! I love you <3");
        map.put(MessageField.TO, "+15558675309");
        map.put(MessageField.FROM, "+14158141829");
        map.put(MessageField.MEDIA_URL, "http://www.example.com/hearts.png");

        String sid =  client.execute(map);

        System.out.println(sid);
    }
}
