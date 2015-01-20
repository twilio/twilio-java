package com.twilio.sdk.examples;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.*;
import com.twilio.sdk.resource.list.AccountList;
import com.twilio.sdk.resource.list.AvailablePhoneNumberList;
import com.twilio.sdk.resource.list.ParticipantList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class RestExamples.
 */
public class RestExamples {

	/** The Constant ACCOUNT_SID. Find it at twilio.com/user/account */
	public static final String ACCOUNT_SID = "AC123";

	/** The Constant AUTH_TOKEN. Find it at twilio.com/user/account */
	public static final String AUTH_TOKEN = "456bef";

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

		// Get the main account (The one we used to authenticate the client)
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

		// Send an sms (using the new messages endpoint)
		MessageFactory messageFactory = mainAccount.getMessageFactory();
		List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
		messageParams.add(new BasicNameValuePair("To", "5105551212")); // Replace with a valid phone number
		messageParams.add(new BasicNameValuePair("From", "(510) 555-1212")); // Replace with a valid phone
		// number in your account
		messageParams.add(new BasicNameValuePair("Body", "This is a test message!"));
		messageFactory.create(messageParams);

	        // Search for all available phone numbers
	        AvailablePhoneNumberList phoneNumbers = mainAccount.getAvailablePhoneNumbers();
	        List<AvailablePhoneNumber> phoneNumberList = phoneNumbers.getPageData();
	
	        // Search for available phone numbers & filter by area code
	        // For available filters see:
	        //     http://www.twilio.com/docs/api/rest/available-phone-numbers#local-get-basic-filters
	        //     http://www.twilio.com/docs/api/rest/available-phone-numbers#local-get-advanced-filters
	        Map<String, String> areaCodeFilter = new HashMap<String, String>();
	        areaCodeFilter.put("AreaCode", "94103");
	        AvailablePhoneNumberList phoneNumbersByAreaCode = mainAccount.getAvailablePhoneNumbers(areaCodeFilter);
	        List<AvailablePhoneNumber> phoneNumbersByAreaCodeList = phoneNumbersByAreaCode.getPageData();
	
	        // Search for phone numbers local to a country (Great Britain), and filter by SMS enabled
	        // For country codes, see:
	        //      http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
	        Map<String, String> smsFilter = new HashMap<String, String>();
	        smsFilter.put("SmsEnabled", "true");
	        AvailablePhoneNumberList phoneNumbersByCountryAndSms = mainAccount.getAvailablePhoneNumbers(smsFilter, "GB", AvailablePhoneNumberList.TYPE_LOCAL);
	        List<AvailablePhoneNumber> phoneNumbersByCountryAndSmsList = phoneNumbersByCountryAndSms.getPageData();

		// Buy the first number in a list
		Map<String, String> params = new HashMap<String, String>();
		params.put("PhoneNumber", phoneNumberList.get(0).getPhoneNumber());
		params.put("VoiceUrl", "http://demo.twilio.com/welcome/voice/");
		// Uncomment to complete the purchase!
		// mainAccount.getIncomingPhoneNumberFactory().create(params);

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
				(Map) null);
		if (!resp.isError()) {
			System.out.println(resp.getResponseText());
		}

	}
}
