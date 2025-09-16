```
class PublicOAuthExample {
    public static void main {

        private static final String GRANT_TYPE = "grant_type_to_be_used";
        private static final String OAUTH_CLIENT_SID = "client_id";
        private static final String OAUTH_CLIENT_SECRET = "client_secret";
        private static final String ACCOUNT_SID = "account_sid";
        private static final String MESSAGE_SID = "message_sid";

        //Getting access token - Method #1
        Twilio.init(new ClientCredentialProvider(OAUTH_CLIENT_SID, OAUTH_CLIENT_SECRET), ACCOUNT_SID);
        fetchMessage(MESSAGE_SID);


        //Scenario: 2 If in case one doesn't want to change the existing stored credential
        // Pass Custom TwilioRestClient
        // TokenManager tokenManager = new ApiTokenManager(GRANT_TYPE, OAUTH_CLIENT_SID, OAUTH_CLIENT_SECRET);
        // TokenAuthStrategy tokenAuthStrategy = new TokenAuthStrategy(tokenManager);
        // TwilioRestClient client = new TwilioRestClient.Builder(tokenAuthStrategy).accountSid(ACCOUNT_SID).build();
        // fetchMessageWithClient(MESSAGE_SID, client);
    }

    public static void fetchMessage(String sid) {
        Message message = Message.fetcher(sid).fetch();
        System.out.println("Fetched Message SID: " + message.getSid());
    }

    public static void fetchMessageWithClient(String sid, TwilioRestClient client) {
        Message message = Message.fetcher(sid).fetch(client);
        System.out.println("Fetched Message SID: " + message.getSid());
    }
}
```

