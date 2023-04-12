# Custom HTTP Clients for the Twilio Java Helper Library

If you are working with the Twilio Java Helper Library, and you need to be able to modify the HTTP requests that the library makes to the Twilio servers, you’re in the right place. The most common need to alter the HTTP request is to connect and authenticate with an enterprise’s proxy server. We’ll provide sample code that you can drop right into your app to handle this use case.

## Connect and authenticate with a proxy server

To connect and provide credentials to a proxy server that may be between your app and Twilio, you need a way to modify the HTTP requests that the Twilio helper library makes on your behalf to invoke the Twilio REST API.

The Twilio Java helper library uses the HttpClient interface (in the org.apache.http.client package) under the hood to make the HTTP requests. With this in mind, the following two facts should help us arrive at the solution:

1. Connecting to a proxy server with HttpClient is a [solved problem](https://hc.apache.org/httpcomponents-client-ga/examples.html).
1. The Twilio Helper Library allows you to provide your own `HttpClient` for making API requests.

So the question becomes: how do we apply this to a typical Twilio REST API example?

```java
Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

Message message = Message.creator(new PhoneNumber("+15558675310"),
        new PhoneNumber("+15017122661"), "Hey there!").create();
```

Where is `TwilioRestClient` created and used? Out of the box, the helper library creates a default `TwilioRestClient` for you, using the Twilio credentials you pass to the `init` method. However, nothing is stopping you from creating your own and using that.

Once you have your own `TwilioRestClient`, you can pass it to any Twilio REST API resource action you want. Here’s an example of sending an SMS message with a custom client:

```java
// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

public class Example {

  public static void main(String args[]) {
    Dotenv dotenv = Dotenv.configure().directory(".").load();
    String ACCOUNT_SID = dotenv.get("ACCOUNT_SID");
    String AUTH_TOKEN = dotenv.get("AUTH_TOKEN");
    String PROXY_HOST = dotenv.get("PROXY_HOST");
    int PROXY_PORT = Integer.parseInt(dotenv.get("PROXY_PORT"));

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    ProxiedTwilioClientCreator clientCreator = new ProxiedTwilioClientCreator(
      ACCOUNT_SID,
      AUTH_TOKEN,
      PROXY_HOST,
      PROXY_PORT
    );
    TwilioRestClient twilioRestClient = clientCreator.getClient();
    Twilio.setRestClient(twilioRestClient);

    Message message = Message
      .creator(
        new PhoneNumber("+15558675310"),
        new PhoneNumber("+15017122661"),
        "Hey there!"
      )
      .create();

    System.out.println(message.getSid());
  }
}
```

## Create your custom TwilioRestClient

When you take a closer look at the constructor for `TwilioRestClient`, you see that the `httpClient` parameter is actually of type `com.twilio.http.HttpClient`.

`HttpClient` is an abstraction that allows plugging in any implementation of an HTTP client you want (or even creating a mocking layer for unit testing).

However, within the helper library, there is an implementation of `com.twilio.http.HttpClient` called `NetworkHttpClient`. This class wraps the `org.apache.http.client.HttpClient` and provides it to the Twilio helper library to make the necessary HTTP requests.

## Call Twilio through the proxy server

Now that we understand how all the components fit together, we can create our own `TwilioRestClient` that can connect through a proxy server. To make this reusable, here’s a class that you can use to create this `TwilioRestClient` whenever you need one:

```java
import com.twilio.http.HttpClient;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.TwilioRestClient;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class ProxiedTwilioClientCreator {

  private String username;
  private String password;
  private String proxyHost;
  private int proxyPort;
  private HttpClient httpClient;

  /**
   * Constructor for ProxiedTwilioClientCreator
   * @param username
   * @param password
   * @param proxyHost
   * @param proxyPort
   */
  public ProxiedTwilioClientCreator(
    String username,
    String password,
    String proxyHost,
    int proxyPort
  ) {
    this.username = username;
    this.password = password;
    this.proxyHost = proxyHost;
    this.proxyPort = proxyPort;
  }

  /**
   * Creates a custom HttpClient based on default config as seen on:
   * {@link com.twilio.http.NetworkHttpClient#NetworkHttpClient() constructor}
   */
  private void createHttpClient() {
    RequestConfig config = RequestConfig
      .custom()
      .setConnectTimeout(10000)
      .setSocketTimeout(30500)
      .build();

    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setDefaultMaxPerRoute(10);
    connectionManager.setMaxTotal(10 * 2);

    HttpHost proxy = new HttpHost(proxyHost, proxyPort);

    HttpClientBuilder clientBuilder = HttpClientBuilder.create();
    clientBuilder
      .setConnectionManager(connectionManager)
      .setProxy(proxy)
      .setDefaultRequestConfig(config);

    // Inclusion of Twilio headers and build() is executed under this constructor
    this.httpClient = new NetworkHttpClient(clientBuilder);
  }

  /**
   * Get the custom client or builds a new one
   * @return a TwilioRestClient object
   */
  public TwilioRestClient getClient() {
    if (this.httpClient == null) {
      this.createHttpClient();
    }

    TwilioRestClient.Builder builder = new TwilioRestClient.Builder(
      username,
      password
    );
    return builder.httpClient(this.httpClient).build();
  }
}
```

In this example, we use some environment variables loaded at the program startup to retrieve various configuration settings:

- Your Twilio Account Sid and Auth Token (found here, in the Twilio console)
- Your proxy server host
- Your proxy port

These settings are located in a file named `.env` like so:

```env
ACCOUNT_SID=ACxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
AUTH_TOKEN= your_auth_token

PROXY_HOST=127.0.0.1
PROXY_PORT=8888
```

Here’s a console program that sends a text message and shows how it all can work together. It loads the `.env` file for us.

```java
// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

public class Example {

  public static void main(String args[]) {
    Dotenv dotenv = Dotenv.configure().directory(".").load();
    String ACCOUNT_SID = dotenv.get("ACCOUNT_SID");
    String AUTH_TOKEN = dotenv.get("AUTH_TOKEN");
    String PROXY_HOST = dotenv.get("PROXY_HOST");
    int PROXY_PORT = Integer.parseInt(dotenv.get("PROXY_PORT"));

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    ProxiedTwilioClientCreator clientCreator = new ProxiedTwilioClientCreator(
      ACCOUNT_SID,
      AUTH_TOKEN,
      PROXY_HOST,
      PROXY_PORT
    );
    TwilioRestClient twilioRestClient = clientCreator.getClient();
    Twilio.setRestClient(twilioRestClient);

    Message message = Message
      .creator(
        new PhoneNumber("+15558675310"),
        new PhoneNumber("+15017122661"),
        "Hey there!"
      )
      .create();

    System.out.println(message.getSid());
  }
}
```

## What else can this technique be used for?

Now that you know how to inject your own HttpClient into the Twilio API request pipeline, you could use this technique to add custom HTTP headers and authorization to the requests, perhaps as required by an upstream proxy server.

You could also implement your own HttpClient to mock the Twilio API responses so your unit and integration tests can run quickly without needing to make a connection to Twilio. In fact, there’s already an example online showing [how to do exactly that with Node.js and Prism](https://www.twilio.com/docs/openapi/mock-api-generation-with-twilio-openapi-spec).

We can’t wait to see what you build!
