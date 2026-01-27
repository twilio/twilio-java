package com.twilio;

import com.twilio.auth_strategy.TokenAuthStrategy;
import com.twilio.base.Page;
import com.twilio.base.ResourceSet;
import com.twilio.http.CustomHttpClient;
import com.twilio.http.TwilioRestClient;
import com.twilio.http.bearertoken.OrgsTokenManager;
import com.twilio.http.bearertoken.TokenManager;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumberReader;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.chat.v2.Service;
import com.twilio.rest.chat.v2.service.User;
import com.twilio.rest.events.v1.Sink;
import com.twilio.rest.events.v1.Subscription;
import com.twilio.rest.previewiam.organizations.Account;
import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ClusterTest {
    String fromNumber;
    String toNumber;
    String grantType;
    String orgsClientId;
    String orgsClientSecret;
    String organisationSid;
    String clientId;
    String clientSecret;
    String messageSid;
    TwilioRestClient customRestClient;

    String accountSid;

    @Before
    public void setUp() {
        // only run when ClusterTest property is passed (mvn test -Dtest="ClusterTest"), skip test run on mvn test
        Assume.assumeThat(System.getProperty("Test"), CoreMatchers.is("ClusterTest"));
        fromNumber = System.getenv("TWILIO_FROM_NUMBER");
        toNumber = System.getenv("TWILIO_TO_NUMBER");
        String apiKey = System.getenv("TWILIO_API_KEY");
        String secret = System.getenv("TWILIO_API_SECRET");
        accountSid = System.getenv("TWILIO_ACCOUNT_SID");
        Twilio.init(apiKey, secret, accountSid);

        grantType = "client_credentials";
        orgsClientId = System.getenv("TWILIO_ORGS_CLIENT_ID");
        orgsClientSecret = System.getenv("TWILIO_ORGS_CLIENT_SECRET");
        organisationSid = System.getenv("TWILIO_ORG_SID");
        
        clientId = System.getenv("TWILIO_CLIENT_ID");
        clientSecret = System.getenv("TWILIO_CLIENT_SECRET");
        messageSid = System.getenv("TWILIO_MESSAGE_SID");

        // CustomHttpClient
        customRestClient = new TwilioRestClient.Builder(apiKey, secret).accountSid(accountSid).httpClient(new CustomHttpClient()).build();
    }

    @Test
    public void testSendingAText() {
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(toNumber), new com.twilio.type.PhoneNumber(fromNumber),
                "Where's Wallace?")
            .create();
        assertNotNull(message);
        assertTrue(message.getBody().contains("Where's Wallace?"));
        assertEquals(fromNumber, message.getFrom().toString());
        assertEquals(toNumber, message.getTo().toString());
    }

    @Test
    public void testListingNumbers() {
        Page<IncomingPhoneNumber> phoneNumbers = IncomingPhoneNumber.reader().firstPage();
        assertNotNull(phoneNumbers);
        assertTrue(phoneNumbers.getRecords().size() > 0);
    }

    @Test
    public void testListingANumber() {
        IncomingPhoneNumberReader phoneNumberReader =
        IncomingPhoneNumber.reader();
        phoneNumberReader.setPhoneNumber(fromNumber);
        Page<IncomingPhoneNumber> phoneNumbers = phoneNumberReader.firstPage();
        assertNotNull(phoneNumbers);
        assertEquals(fromNumber, phoneNumbers.getRecords().get(0).getPhoneNumber().toString());
    }

    @Test
    public void testSpecialCharacters() {
        Service service = Service.creator("service|friendly&name").create();
        assertNotNull(service);

        User user = User.creator(service.getSid(), "user|identity&string").create();
        assertNotNull(user);

        boolean isUserDeleted =  User.deleter(service.getSid(), user.getSid()).delete();
        assertTrue(isUserDeleted);

        boolean isServiceDeleted = Service.deleter(service.getSid()).delete();
        assertTrue(isServiceDeleted);

    }

    @Test
    public void testListParams() {
        Map<String, Object> sinkConfiguration = new HashMap<>();
        sinkConfiguration.put("destination", "http://example.org/webhook");
        sinkConfiguration.put("method", "post");
        sinkConfiguration.put("batch_events",false);
        List<Object> types = new ArrayList<>();
        Map<String, Object> types1 = new HashMap<>();
        Map<String, Object> types2 = new HashMap<>();
        types1.put("type", "com.twilio.messaging.message.delivered");
        types2.put("type", "com.twilio.messaging.message.sent");
        types.add(types1);
        types.add(types2);

        Sink sink = Sink.creator("test sink java", sinkConfiguration, Sink.SinkType.WEBHOOK).create();
        assertNotNull(sink);

        Subscription subscription = Subscription.creator
                ("test subscription java", sink.getSid(),types).create();
        assertNotNull(subscription);

        // Clean up the resources we've created
        assertTrue(Subscription.deleter(subscription.getSid()).delete());
        assertTrue(Sink.deleter(sink.getSid()).delete());
    }

    @Test
    public void testOrgsApi(){
        //Twilio.init(new OrgsClientCredentialProvider(orgsClientId, orgsClientSecret));

        TokenManager tokenManager = new OrgsTokenManager("client_credentials", orgsClientId, orgsClientSecret);
        TokenAuthStrategy tokenAuthStrategy = new TokenAuthStrategy(tokenManager);
        TwilioRestClient client = new TwilioRestClient.Builder(tokenAuthStrategy)
                .build();

        //Fetching the account information
        ResourceSet<Account> accountSet = Account.reader(organisationSid).read(client);
        String accountSid = accountSet.iterator().next().getAccountSid();
        assertNotNull(accountSid);

        //Fetching specific account
        Account account = Account.fetcher(organisationSid, accountSid).fetch(client);
        assertNotNull(account.getAccountSid());

        //Fetching users of this organisation
        ResourceSet<com.twilio.rest.previewiam.organizations.User>
            userSet = com.twilio.rest.previewiam.organizations.User.reader(organisationSid).read(client);
        assertNotNull(userSet);
        String userId = userSet.iterator().next().getId().toString();
        assertNotNull(userId);
    }

    // Test multipart/form-data
    @Test
    public void testMultiPartFormData() {
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(toNumber), new com.twilio.type.PhoneNumber(fromNumber),
                        "Where's Wallace?")
                .create(customRestClient);
        assertNotNull(message);
        assertTrue(message.getBody().contains("Where's Wallace?"));
        assertEquals(fromNumber, message.getFrom().toString());
        assertEquals(toNumber, message.getTo().toString());
    }

    // Note: This test should be last as we are initialising OAuth App creds.
    // Disabling test as OAuth is not working.
//    @Test
//    public void testPublicOAuthFetchMessage() {
//        Twilio.init(new ClientCredentialProvider(clientId, clientSecret), accountSid);
//        // Fetching an existing message; if this test fails, the SID might be deleted,
//        // in that case, change TWILIO_MESSAGE_SID in twilio-java repo env variables
//        Message message = Message.fetcher(messageSid).fetch();
//        assertNotNull(message);
//        assertTrue(message.getBody().contains("Where's Wallace?"));
//        assertEquals(fromNumber, message.getFrom().toString());
//        assertEquals(toNumber, message.getTo().toString());
//    }
}
