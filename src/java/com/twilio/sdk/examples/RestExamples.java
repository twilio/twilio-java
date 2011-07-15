package com.twilio.sdk.examples;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.AvailablePhoneNumber;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Conference;
import com.twilio.sdk.resource.instance.Participant;
import com.twilio.sdk.resource.list.AccountList;
import com.twilio.sdk.resource.list.AvailablePhoneNumberList;
import com.twilio.sdk.resource.list.ParticipantList;

// TODO: Auto-generated Javadoc
/**
 * The Class RestExamples.
 */
public class RestExamples {

	/** The Constant ACCOUNT_SID. */
	public static final String ACCOUNT_SID = "AC...";

	/** The Constant AUTH_TOKEN. */
	public static final String AUTH_TOKEN = "...";

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws TwilioRestException
	 *             the twilio rest exception
	 */
	public static void main(String[] args) throws TwilioRestException {

		// Create a rest client
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

		// Get the main account (The one we used to authenticate the client
		Account mainAccount = client.getAccount();

		// Get all accounts including sub accounts
		AccountList accountList = client.getAccounts();

		// All lists implement an iterable interface, you can use the foreach
		// syntax on them
		for (Account a : accountList) {
			System.out.println(a.getFriendlyName());
		}

		// You can also iterate manually...
		Iterator<Account> itr = accountList.iterator();
		while (itr.hasNext()) {
			Account a = itr.next();
			System.out.println(a.getFriendlyName());
		}

		// You can also get just the first page of data
		accountList = client.getAccounts();
		List<Account> accounts = accountList.getPageData();

		// Make a call
		CallFactory callFactory = mainAccount.getCallFactory();
		Map<String, String> callParams = new HashMap<String, String>();
		callParams.put("To", "5105551212"); // Replace with a valid phone number
		callParams.put("From", "(510) 555-1212"); // Replace with a valid phone
													// number in your account
		callParams.put("Url", "http://demo.twilio.com/welcome/voice/");
		Call call = callFactory.create(callParams);
		System.out.println(call.getSid());
		
		// Send an sms
		SmsFactory smsFactory = mainAccount.getSmsFactory();
		Map<String, String> smsParams = new HashMap<String, String>();
		smsParams.put("To", "5105551212"); // Replace with a valid phone number
		smsParams.put("From", "(510) 555-1212"); // Replace with a valid phone
													// number in your account
		smsParams.put("Body", "This is a test message!");
		smsFactory.create(smsParams);

		// Search for available phone numbers & then buy a random phone number
		AvailablePhoneNumberList phoneNumbers = mainAccount
				.getAvailablePhoneNumbers();
		List<AvailablePhoneNumber> list = phoneNumbers.getPageData();

		// Buy the first number returned
		Map<String, String> params = new HashMap<String, String>();
		params.put("PhoneNumber", list.get(0).getPhoneNumber());
		params.put("VoiceUrl", "http://demo.twilio.com/welcome/voice/");
		mainAccount.getIncomingPhoneNumberFactory().create(params);

		// View a conference using it's sid
		Conference c = mainAccount.getConference("CA12345...");
		ParticipantList participants = c.getParticipants();

		for (Participant p : participants) {
			// Randomly mute or kick each participant
			if (Math.random() > 0.5) {
				p.mute();
			} else {
				p.kick();
			}
		}

		// Make a raw request to the api... note, this is deprecated style
		TwilioRestResponse resp = client.request("/2010-04-01/Accounts", "GET",
				null);
		if (!resp.isError()) {
			System.out.println(resp.getResponseText());
		}

	}
}
