    class OrgsAPIExample {
        public static void main {

            private static final String GRANT_TYPE = "grant_type_to_be_used";
            private static final String CLIENT_SID =
            "client_id_of_the_organization";
            private static final String CLIENT_SECRET = "client_secret_of_organization";
            private static final String ORGANISATION_ID = "id_of_the_organization";

            //Getting access token - Method #1
            Twilio.init(new OrgsClientCredentialProvider(CLIENT_SID, CLIENT_SECRET));
            fetchAccountDetails();


            //Scenario: 2 If in case one doesn't want to change the existing stored credential
            // Pass Custom TwilioRestClient
            // TokenManager tokenManager = new OrgsTokenManager(GRANT_TYPE, CLIENT_SID, CLIENT_SECRET);
            // TokenAuthStrategy tokenAuthStrategy = new TokenAuthStrategy(tokenManager);
            // TwilioRestClient client = new TwilioRestClient.Builder(tokenAuthStrategy).build();
            // fetchAccountDetailsWithClient(client);
        }

        private static void fetchAccountDetails() {
           ResourceSet<Account> accountSet = Account.reader(ORGANISATION_ID).read();
           String accountSid = accountSet.iterator().next().getAccountSid();
           System.out.println(accountSid);
        }

        private static void fetchAccountDetailsWithClient(TwilioRestClient client) {
           ResourceSet<Account> accountSet = Account.reader(ORGANISATION_ID).read(client);
           String accountSid = accountSet.iterator().next().getAccountSid();
           System.out.println(accountSid);
        }
    }