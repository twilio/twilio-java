package com.twilio;

import com.twilio.base.Page;
import com.twilio.base.bearertoken.ResourceSet;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumberReader;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.chat.v2.Service;
import com.twilio.rest.chat.v2.service.User;
import com.twilio.rest.events.v1.Sink;
import com.twilio.rest.events.v1.Subscription;
import com.twilio.rest.previewiam.organizations.Account;
import com.twilio.rest.previewiam.organizations.RoleAssignment;
import com.twilio.rest.previewiam.organizations.RoleAssignmentReader;
import com.twilio.rest.previewiam.organizations.Token;
import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class ClusterTest {
    String fromNumber;
    String toNumber;
    String grantType;
    String clientId;
    String clientSecret;
    String organisationSid;

    @Before
    public void setUp() {
        // only run when ClusterTest property is passed (mvn test -Dtest="ClusterTest"), skip test run on mvn test
        Assume.assumeThat(System.getProperty("Test"), CoreMatchers.is("ClusterTest"));
        fromNumber = System.getenv("TWILIO_FROM_NUMBER");
        toNumber = System.getenv("TWILIO_TO_NUMBER");
        String apiKey = System.getenv("TWILIO_API_KEY");
        String secret = System.getenv("TWILIO_API_SECRET");
        String accountSid = System.getenv("TWILIO_ACCOUNT_SID");
        Twilio.init(apiKey, secret, accountSid);

        //Secrets required for tests for token based auth flows
        grantType = System.getenv("TWILIO_GRANT_TYPE");
        clientId = System.getenv("TWILIO_CLIENT_ID");
        clientSecret = System.getenv("TWILIO_CLIENT_SECRET");
        organisationSid = System.getenv("TWILIO_ORG_SID");
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
        List<Map<String, Object>> types = new ArrayList<>();
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

    public void testOrgsApi() throws IOException {


        //Getting access token
        Token token  = Token.creator(grantType, clientId).setClientSecret(clientSecret).create();
        assertNotNull(token);

        //Setting access token
        TwilioBearerTokenAuth.init(token.getAccessToken());

        //Fetching the account information
        ResourceSet<Account> accountSet = Account.reader(organisationSid).read();
        String accountSid = accountSet.iterator().next().getAccountSid();
        System.out.println(accountSid);

        //Fetching specific account
        Account account = Account.fetcher(organisationSid, accountSid).fetch();
        assertNotNull(account.getAccountSid());

        //Fetching users of this organisation
        ResourceSet<com.twilio.rest.previewiam.organizations.User>
            userSet = com.twilio.rest.previewiam.organizations.User.reader(organisationSid).read();
        assertNotNull(userSet);
        String userId = userSet.iterator().next().getId().toString();
        assertNotNull(userId);

        //Fetch specific user
        com.twilio.rest.previewiam.organizations.UserFetcher user = com.twilio.rest.previewiam.organizations.User.fetcher(organisationSid, userId);
        com.twilio.rest.previewiam.organizations.User user1 = user.fetch();
        assertNotNull(user1);

        //Create a new user, userName and external id should be unique and new every time we execute this test case
//        String userName = "asabuUser210@asabu1.test.twilio.com";
//        com.twilio.rest.previewiam.organizations.User.ScimUser scimUser = new com.twilio.rest.previewiam.organizations.User.ScimUser(userName);
//        scimUser.setExternalId("43536374422");
//        com.twilio.rest.previewiam.organizations.User.ScimEmailAddress emailAddress = new com.twilio.rest.previewiam.organizations.User.ScimEmailAddress();
//        emailAddress.setPrimary(true);
//        emailAddress.setValue(userName);
//
//
//        List<com.twilio.rest.previewiam.organizations.User.ScimEmailAddress> listemails;
//        listemails = new ArrayList<>();
//        listemails.add(emailAddress);
//        scimUser.setEmails(listemails);
//        com.twilio.rest.previewiam.organizations.User userNew = com.twilio.rest.previewiam.organizations.User.creator(organisationSid, scimUser).create();
//        System.out.println(userNew.getUserName());
//
//
//        //Update users details
//        System.out.println("Executing patches");
//        com.twilio.rest.previewiam.organizations.User.ScimUser uscimUser = new com.twilio.rest.previewiam.organizations.User.ScimUser(userName);
//        com.twilio.rest.previewiam.organizations.User.ScimEmailAddress emailAddress1 = new com.twilio.rest.previewiam.organizations.User.ScimEmailAddress();
//        emailAddress1.setPrimary(true);
//        emailAddress1.setValue(userName);
//
//
//        List<com.twilio.rest.previewiam.organizations.User.ScimEmailAddress> listemails1;
//        listemails1 = new ArrayList<>();
//        listemails1.add(emailAddress1);
//        uscimUser.setEmails(listemails1);
//        uscimUser.setDisplayName("Auth1 user test");
//
//        com.twilio.rest.previewiam.organizations.User userUpdated = null;
//        userUpdated = com.twilio.rest.previewiam.organizations.User.updater(organisationSid, userId, uscimUser).update();
//        System.out.println(userUpdated.getDisplayName());
//
//        //Delete an existing user
//        boolean isDeleted = com.twilio.rest.previewiam.organizations.User.deleter(organisationSid,"US3bdcff893e4f3a91afa1afd742feb1ed").delete();
//        System.out.println("is deleted "+ isDeleted);
//


    }



}
