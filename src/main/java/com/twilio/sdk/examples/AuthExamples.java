package com.twilio.sdk.examples;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.list.MessageList;

public class AuthExamples {

	// You will need your Account Sid and a SigningKey Sid and Secret
	// to generate an Access Token for your SDK endpoint to connect to Twilio.
	public static final String ACCOUNT_SID = "account_sid";
	public static final String AUTH_TOKEN = "auth_token";
	public static final String SIGNINGKEY_SID = "key";
	public static final String SIGNINGKEY_SECRET = "secret";

	public static void main(String[] args) throws Exception {
		authUsingAuthToken();
		authUsingKeys();
	}

	public static void authUsingAuthToken() throws Exception {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

		// Get an object from its sid. If you do not have a sid,
		// check out the list resource examples on this page
		MessageList messages = client.getAccount().getMessages();
		System.out.println("Got back " + messages.getPageData().size() + " messages using auth token");
	}

	public static void authUsingKeys() throws Exception {
		TwilioRestClient client = new TwilioRestClient(SIGNINGKEY_SID, SIGNINGKEY_SECRET);

		// set the account sid used for building request urls
		client.setRequestAccountSid(ACCOUNT_SID);

		// Get an object from its sid. If you do not have a sid,
		// check out the list resource examples on this page
		MessageList messages = client.getAccount().getMessages();
		System.out.println("Got back " + messages.getPageData().size() + " messages using signing keys");
	}

}
