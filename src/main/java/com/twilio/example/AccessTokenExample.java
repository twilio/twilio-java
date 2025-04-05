package com.twilio.example;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VoiceGrant;
import java.util.HashMap;
import com.google.gson.Gson;

public class AccessTokenExample {
  public static void main(String args[]){
    String acctSid = System.getenv("TWILIO_ACCOUNT_SID");
    String applicationSid = System.getenv("TWILIO_TWIML_APP_SID");
    String apiKey = System.getenv("API_KEY");
    String apiSecret = System.getenv("API_SECRET");
    // Create Voice grant
    VoiceGrant grant = new VoiceGrant();
    grant.setOutgoingApplicationSid(applicationSid);

    // Optional: add to allow incoming calls
    grant.setIncomingAllow(true);

    String randomIdentity = "random-identity";
    // Create access token
    AccessToken accessToken = new AccessToken.Builder(acctSid, apiKey, apiSecret.getBytes())
        .identity(randomIdentity)
        .grant(grant)
        .build();

    String token = accessToken.toJwt();

    // create JSON response payload
    HashMap<String, String> json = new HashMap<>();
    json.put("identity", randomIdentity);
    json.put("token", token);

    Gson gson = new Gson();
    System.out.println(gson.toJson(json));
  }
}
