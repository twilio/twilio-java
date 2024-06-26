package com.twilio;

import com.twilio.base.Page;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumberReader;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.chat.v2.Service;
import com.twilio.rest.chat.v2.service.User;
import com.twilio.rest.events.v1.Sink;
import com.twilio.rest.events.v1.Subscription;
import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.twilio.rest.previewiam.organizations.Account;
import com.twilio.rest.previewiam.organizations.Token;
import com.twilio.base.bearertoken.ResourceSet;
import com.twilio.http.bearertoken.OrgsTokenManager;

import static org.junit.Assert.*;

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
        grantType = "client_credentials";
        clientId = "client_id";
        clientSecret = "client_secret";
        organisationSid = "organisation_sid";

    }

    @Test
    public void testOrgsApi(){

        //Setting access token
        TwilioOrgsTokenAuth.init(grantType, clientId, clientSecret);


        //Fetching the account information
        ResourceSet<Account> accountSet = Account.reader(organisationSid).read();
        String accountSid = accountSet.iterator().next().getAccountSid();
        System.out.println(accountSid);
    }


}
