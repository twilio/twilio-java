    class BearerTokenAuthenticationExamples {
        public static void main {

            private static final String GRANT_TYPE = "grant_type_to_be_used";
            private static final String CLIENT_SID =
            "client_id_of_the_organization";
            private static final String CLIENT_SECRET = "client_secret_of_organization";
            private static final String ORGANISATION_ID = "id_of_the_organization";

            //Getting access token - Method #1
            TwilioOrgsTokenAuth.init(GRANT_TYPE, CLIENT_ID, CLIENT_SECRET);

            //Getting access token - Method #2
            //To provide custom token manager implementation
            //Need not call init method if customer token manager is passed
            //TwilioOrgsTokenAuth.setTokenManager(new CustomTokenManagerImpl(GRANT_TYPE, CLIENT_ID, CLIENT_SECRET));

            fetchAccountDetails();
        }

        private static void fetchAccountDetails() {
           ResourceSet<Account> accountSet = Account.reader(ORGANISATION_ID).read();
           String accountSid = accountSet.iterator().next().getAccountSid();
           System.out.println(accountSid);
        }
    }