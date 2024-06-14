    class BearerTokenAuthenticationExamples {
        public static void main {
            //Getting access token
            Token token  = Token.creator(GRANT_TYPE, CLIENT_ID).setClientSecret(CLIENT_SECRET).create();

            TwilioBearerTokenAuth.init(token.getAccessToken());
            fetchAccountDetails();
        }

        private static void fetchAccountDetails() {
           ResourceSet<Account> accountSet = Account.reader(ORGANISATION_ID).read();
           String accountSid = accountSet.iterator().next().getAccountSid();
           System.out.println(accountSid);
        }
    }