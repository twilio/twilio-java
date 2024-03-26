package com.twilio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.Domains;
import com.twilio.rest.content.v1.Content;

public class OAuthTest {
    public static void main(String[] args) {
        String clientId = "zMqwdhvVP8VUkzkPrxKl0vRnmugVJXar";
        String clientSecret = "9O-04YgPwbliyG2KEDE8yWH1m0WMNY2Qz_tjWTQ0mRSflzezUDumMuyAAeZFsjDF";
        //Twilio.init(clientId, clientSecret);
        String path = "https://api.twilio-dev.auth0app.com/oauth/token";
        Content.ContentCreateRequest contentCreateRequest = new Content.ContentCreateRequest(
                "client_credentials", "https://www.twilio.com/organizations", clientId, clientSecret);
        Content content = Content.creator(contentCreateRequest).create();
        System.out.println(content);
       
    }
}
