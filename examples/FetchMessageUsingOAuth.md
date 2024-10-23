```
import com.twilio.Twilio;
import com.twilio.credential.ClientCredentialProvider;
import com.twilio.rest.api.v2010.account.Message;

public class FetchMessageUsingOAuth {
    public static void main(String[] args) {
        String clientId = "YOUR_CLIENT_ID";
        String clientSecret = "YOUR_CLIENT_SECRET";
        String accountSid = "YOUR_ACCOUNT_SID";
        Twilio.init(new ClientCredentialProvider(clientId, clientSecret), accountSid);
        /*
          Or use the following if accountSid is not required as a path parameter for an API or when setting accountSid in the API.
          Twilio.init(new ClientCredentialProvider(clientId, clientSecret));
        */
        String messageSid = "YOUR_MESSAGE_SID";
        Message message = Message.fetcher(messageSid).fetch();
    }
}
```

