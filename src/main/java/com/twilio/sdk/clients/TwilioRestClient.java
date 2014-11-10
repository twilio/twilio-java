package com.twilio.sdk.clients;

import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.http.HttpClient;
import com.twilio.sdk.http.NetworkHttpClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

public class TwilioRestClient {

    public enum Domains {
        API("api"),
        WDS("wds");

        private String domain;

        Domains(final String domain) {
            this.domain = domain;
        }

        public String toString() {
            return domain;
        }
    }

    public enum Versions {
        v1("v1"),
        v2008("2008-08-01"),
        v2010("2010-04-01");

        private String version;

        Versions(final String version) {
            this.version = version;
        }

        public String toString() {
            return version;
        }
    }

    protected HttpClient httpClient;
    protected String accountSid;
    protected String authToken;

    public TwilioRestClient(final String accountSid, final String authToken) {
        this(accountSid, authToken, new NetworkHttpClient());
    }

    public TwilioRestClient(final String accountSid, final String authToken, final HttpClient httpClient) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.httpClient = httpClient;
    }

    public Response request(final Request request) {
        String resolvedUri =
                "https://" + request.getDomain().toString() + ".twilio.com/" + request.getVersion().toString() +
                request.getUri().replace("{AccountSid}", accountSid);

        request.setUri(resolvedUri);
        request.setAuth(accountSid, authToken);

        return httpClient.reliableRequest(request);
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
