    class BearerTokenAuthenticationExamples {
        public static void main {
            //Getting access token - Method #1
            TwilioOrgsTokenAuth.init(GRANT_TYPE, CLIENT_ID, CLIENT_SECRET);

            //Getting access token - Method #2
            //To provide custom token manager implementation
            //Need not call init method if customer token manager is passed
            //TwilioOrgsTokenAuth.setTokenManager(new OrgsTokenManager(grantType, clientId, clientSecret));

            fetchAccountDetails();
        }

        private static void fetchAccountDetails() {
           ResourceSet<Account> accountSet = Account.reader(ORGANISATION_ID).read();
           String accountSid = accountSet.iterator().next().getAccountSid();
           System.out.println(accountSid);
        }
    }