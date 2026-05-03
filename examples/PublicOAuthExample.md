```
import com.twilio.Twilio;
import com.twilio.credential.ClientCredentialProvider;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.http.TwilioRestClient;
import com.twilio.http.bearertoken.ApiTokenManager;
import com.twilio.http.bearertoken.TokenManager;
import com.twilio.auth_strategy.TokenAuthStrategy;

class PublicOAuthExample {
    private static final String GRANT_TYPE = "client_credentials";
    private static final String OAUTH_CLIENT_SID = System.getenv("OAUTH_CLIENT_ID");
    private static final String OAUTH_CLIENT_SECRET = System.getenv("OAUTH_CLIENT_SECRET");
    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String MESSAGE_SID = System.getenv("MESSAGE_SID");

    public static void main(String[] args) {

        
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

