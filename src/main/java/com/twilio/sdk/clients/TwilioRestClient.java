package com.twilio.sdk.clients;

import com.twilio.sdk.http.HttpClient;
import com.twilio.sdk.http.NetworkHttpClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

public class TwilioRestClient {

    public enum Domains {
        API ("api"),
        WDS ("wds");

        private String domain;

        Domains(String domain) {
            this.domain = domain;
        }

        public String toString() {
            return this.domain;
        }
    }

    public enum Versions {
        v1 ("v1"),
        v2008 ("2008-08-01"),
        v2010 ("2010-04-01");

        private String version;

        Versions(String version) {
            this.version = version;
        }

        public String toString() {
            return this.version;
        }
    }

    protected HttpClient httpClient;
    protected String accountSid;
    protected String authToken;

    public TwilioRestClient(String accountSid, String authToken) {
        this(accountSid, authToken, new NetworkHttpClient());
    }

    public TwilioRestClient(String accountSid, String authToken, HttpClient httpClient) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.httpClient = httpClient;
    }

    public Response request(Request request) {
        String resolvedUri = "https://"
                           + request.getDomain().toString()
                           + ".twilio.com/"
                           + request.getVersion().toString()
                           + request.getUri().replace("{AccountSid}", this.accountSid);

        request.setUri(resolvedUri);
        request.setAuth(this.accountSid, this.authToken);

        return this.httpClient.reliableRequest(request);
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
