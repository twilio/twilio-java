package com.twilio.sdk.examples;

import com.twilio.sdk.auth.AccessToken;
import com.twilio.sdk.auth.IpMessagingGrant;

public class AccessTokenExamples {

	// You will need your Account Sid and a SigningKey Sid and Secret
	// to generate an Access Token for your SDK endpoint to connect to Twilio.
	public static final String ACCOUNT_SID = "account_sid";
	public static final String SIGNINGKEY_SID = "signing_sid";
	public static final String SIGNINGKEY_SECRET = "signing_secret";

	public static void main(String[] args) throws Exception {
		AccessToken token =
			new AccessToken.Builder(ACCOUNT_SID, SIGNINGKEY_SID, SIGNINGKEY_SECRET)
				.grant(new IpMessagingGrant())
				.build();

		String jwtToken = token.toJWT();
		System.out.println(jwtToken);
	}
}
