# Installing 

The pre-built jars are available at http://www.twilio.com/docs/libraries/ You
can just add this jar to your project to get started!

You can view the javadocs for this project at:
http://twilio.github.com/twilio-java

# Building From Source

Important: you must have ant installed to build the project from source.

If you would like to build twilio-java from source just clone the project
(located at https://github.com/twilio/twilio-java) and run:

	cd twilio-java
	ant 

This will build two jars in `twilio-java/dist/lib/` The first is a jar of only
code related to the twilio-java project useful when integrating into a larger
project. The second is a jar bundling twilio-java with all of its dependencies
into a standalone jar. (see ivy.xml for the full list of dependencies)

This will also build the javadoc in `twilio-java/dist/api`. You can open the
index.html located there to view it locally.

# Examples

Here are some examples (also found in TwilioRestExamples.java) 

``` java
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
	callParams.put("From", "(510) 555-1212"); // Replace with a valid phone number in your account
	callParams.put("Url", "http://demo.twilio.com/welcome/voice/");
	Call call = callFactory.create(callParams);
	System.out.println(call.getSid());

	// Send an sms
	SmsFactory smsFactory = mainAccount.getSmsFactory();
	Map<String, String> smsParams = new HashMap<String, String>();
	smsParams.put("To", "5105551212"); // Replace with a valid phone number
	smsParams.put("From", "(510) 555-1212"); // Replace with a valid phone number in your account
	smsParams.put("Body", "This is a test message!");
	smsFactory.create(smsParams);

	// Search for available phone numbers & then buy a random phone number
	AvailablePhoneNumberList phoneNumbers = mainAccount.getAvailablePhoneNumbers();
	List<AvailablePhoneNumber> list = phoneNumbers.getPageData();

	// Buy the first number returned
	Map<String, String> params = new HashMap<String, String>();
	params.put("PhoneNumber", list.get(0).getPhoneNumber());
	params.put("VoiceUrl", "http://demo.twilio.com/welcome/voice/");
	mainAccount.getIncomingPhoneNumberFactory().create(params);

	// View a conference using its sid
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

	// Make a raw HTTP request to the api... note, this is deprecated style
	TwilioRestResponse resp = client.request("/2010-04-01/Accounts", "GET", null);
	if (!resp.isError()) {
		System.out.println(resp.getResponseText());
	}
```

