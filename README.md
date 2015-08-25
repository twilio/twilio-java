[![Build Status](https://travis-ci.org/twilio/twilio-java.png?branch=master)](https://travis-ci.org/twilio/twilio-java)

# Installing

TwilioJava is now using Maven.  At present the jars *are* available from a public [maven](http://maven.apache.org/download.html) repository.

Use the following dependency in your project:

       <dependency>
          <groupId>com.twilio.sdk</groupId>
          <artifactId>twilio-java-sdk</artifactId>
          <version>4.5.0</version>
          <scope>compile</scope>
       </dependency>

If you want to compile it yourself, here's how:

    $ git clone git@github.com:twilio/twilio-java
    $ cd twilio-java
    $ mvn install       # Requires maven, download from http://maven.apache.org/download.html

This will also build the javadoc in `twilio-java/target/apidocs`. You can open the
index.html located there to view it locally.

Pre-built jars are available [here](http://search.maven.org/#browse%7C-1416163511). Select the directory for
the latest version and download one of these jar files:

* twilio-java-sdk-4.x.x-with-dependencies.jar
* twilio-java-sdk-4.x.x.jar -- use this if you have issues with conflicting jars in your project. You'll need to include versions of the dependencies yourself. See the [pom.xml](https://github.com/twilio/twilio-java/blob/master/pom.xml) file in [twilio-java](https://github.com/twilio/twilio-java) for the list of libraries.

You can view the javadocs for this project at:
[http://twilio.github.io/twilio-java](http://twilio.github.io/twilio-java)

# Examples

Here are some examples (also found in [TwilioRestExamples.java](https://github.com/twilio/twilio-java/blob/master/src/main/java/com/twilio/sdk/examples/RestExamples.java) )

```java
package com.twilio.sdk.examples;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
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
import com.twilio.sdk.verbs.TwiMLException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Example {

  public static final String ACCOUNT_SID = "AC.....";
  public static final String AUTH_TOKEN = ".......";

  public static void main(final String[] args) throws TwilioRestException, TwiMLException {

    // Create a rest client
    final TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

    // Get the main account (The one we used to authenticate the client)
    final Account mainAccount = client.getAccount();

    // Get all accounts including sub accounts
    AccountList accountList = client.getAccounts();

    // All lists implement an iterable interface, you can use the foreach
    // syntax on them
    for (final Account a : accountList) {
      System.out.println(a.getFriendlyName());
    }

    // You can also iterate manually...
    final Iterator<Account> itr = accountList.iterator();
    while (itr.hasNext()) {
      final Account a = itr.next();
      System.out.println(a.getFriendlyName());
    }

    // You can also get just the first page of data
    accountList = client.getAccounts();
    final List<Account> accounts = accountList.getPageData();

    // Make a call
    final CallFactory callFactory = mainAccount.getCallFactory();
    final Map<String, String> callParams = new HashMap<String, String>();
    callParams.put("To", "5105551212"); // Replace with a valid phone number
    callParams.put("From", "(510) 555-1212"); // Replace with a valid phone number in your account
    callParams.put("Url", "http://demo.twilio.com/welcome/voice/");
    final Call call = callFactory.create(callParams);
    System.out.println(call.getSid());

    // Send an SMS (Requires version 3.4+)
    final SmsFactory messageFactory = mainAccount.getSmsFactory();
    final List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
    messageParams.add(new BasicNameValuePair("To", "5105551212")); // Replace with a valid phone number
    messageParams.add(new BasicNameValuePair("From", "(510) 555-1212")); // Replace with a valid phone number in your account
    messageParams.add(new BasicNameValuePair("Body", "This is a test message!"));
    messageFactory.create(messageParams);

    // Search for available phone numbers & then buy a random phone number
    final AvailablePhoneNumberList phoneNumbers = mainAccount.getAvailablePhoneNumbers();
    final List<AvailablePhoneNumber> list = phoneNumbers.getPageData();

    // Buy the first number returned
    final Map<String, String> params = new HashMap<String, String>();
    params.put("PhoneNumber", list.get(0).getPhoneNumber());
    params.put("VoiceUrl", "http://demo.twilio.com/welcome/voice/");
    mainAccount.getIncomingPhoneNumberFactory().create(params);

    // View a conference using its sid
    final Conference c = mainAccount.getConference("CA12345...");
    final ParticipantList participants = c.getParticipants();

    for (final Participant p : participants) {
      // Randomly mute or kick each participant
      if (Math.random() > 0.5) {
        p.mute();
      } else {
        p.kick();
      }
    }

    // Make a raw HTTP request to the api... note, this is deprecated style
    final TwilioRestResponse resp = client.request("/2010-04-01/Accounts", "GET", null);
    if (!resp.isError()) {
      System.out.println(resp.getResponseText());
    }
  }
}
```

# Getting help

If you need help installing or using the library, please contact Twilio Support at help@twilio.com first. Twilio's Support staff are well-versed in all of the Twilio Helper Libraries, and usually reply within 24 hours.

If you've instead found a bug in the library or would like new features added, go ahead and open issues or pull requests against this repo!
