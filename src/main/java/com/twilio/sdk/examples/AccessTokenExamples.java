package com.twilio.sdk.examples;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.auth.AccessToken;
import com.twilio.sdk.auth.RestGrant;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.list.MessageList;

public class AccessTokenExamples {

	// You will need your Account Sid and a SigningKey Sid and Secret
	// to generate an Access Token for your SDK endpoint to connect to Twilio.
	public static final String ACCOUNT_SID = "account_sid";
	public static final String SIGNINGKEY_SID = "signing_sid";
	public static final String SIGNINGKEY_SECRET = "signing_secret";

	public static void main(String[] args) throws Exception {
		AccessToken token = new AccessToken(SIGNINGKEY_SID, ACCOUNT_SID, SIGNINGKEY_SECRET);

		// Grants access to list of messages
		// The json extension is required
		token.addGrant(new RestGrant("Messages.json"));

		String jwtToken = token.toJWT();

		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, jwtToken);

		// Get an object from its sid. If you do not have a sid,
		// check out the list resource examples on this page
		MessageList messages = client.getAccount().getMessages();
		System.out.println("Got back " + messages.getPageData().size() + " messages");
	}
}
