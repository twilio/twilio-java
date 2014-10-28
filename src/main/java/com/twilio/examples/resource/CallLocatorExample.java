package com.twilio.examples.resource;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.locators.CallLocator;
import com.twilio.sdk.resources.Call;

/**
 * Created by skimbrel on 14-10-28.
 */
public class CallLocatorExample {

	public static void main(String[] args) {
		Twilio.setAccountSid("ACf03058d205530a5dbded37b9ee6fe61b");
		Twilio.setAuthToken("b066e13bd65fced5bef47dd51903ba18");

		String callSid = "CAa4df5c8404a784212bc026f48c497219";

		Call call = new CallLocator().buildBySid(callSid);

		System.out.println(call.getSid());
		System.out.println(call.getTo());

	}
}
