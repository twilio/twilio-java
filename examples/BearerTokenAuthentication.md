    class BearerTokenAuthenticationExamples {
        public static void main {
            //Getting access token
            TokenManager tokenManager = new TokenManagerImpl(GRANT_TYPE, CLIENT_ID, CLIENT_SECRET);
            TwilioBearerTokenAuth.init(tokenManager);

            fetchAccountDetails();
        }

        private static void fetchAccountDetails() {
           ResourceSet<Account> accountSet = Account.reader(ORGANISATION_ID).read();
           String accountSid = accountSet.iterator().next().getAccountSid();
           System.out.println(accountSid);
        }
    }