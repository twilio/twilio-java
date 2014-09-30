package com.twilio.sdk.factories;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;
import org.apache.commons.httpclient.URI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallFactory extends Factory {

    public CallFactory(TwilioRestClient client) {
        super(client);
    }

    public CallCreator create(String to, String from, URL url) {
        return new CallCreator(this, to, from, url);
    }

    public CallUpdater update() {
        return new CallUpdater(this);
    }

    public CallLocator find() { return new CallLocator(this); }

    public Call get(final String sid) {
        return null;
    }

    public ListenableFuture<Call> getAsync(final String sid) {
        return this.getExecutor().submit(new Callable<Call>() {
            public Call call() {
                return get(sid);
            }
        });
    }

    public static class CallCreator extends Creator<Call> {
        protected String to;
        protected String from;
        protected URL url;
        protected String friendlyName;

        /**
         * @param factory The Factory that owns the Creator
         * @param to The phone number to dial
         * @param from The twilio number to originate the call from
         * @param url The url to fetch twiml from
         */
        public CallCreator(Factory factory, String to, String from, URL url) {
            super(factory);
            this.to = to;
            this.from = from;
            this.url = url;
        }

        @Override
        public Call build() {
            Request request = new Request("POST", "/Accounts/{AccountSid}/Calls");
            Response response = this.makeRequest(request);

            if (response.getStatusCode() != 201) {
                throw new RuntimeException("Call creation failed: [" + response.getStatusCode() + "] " + response.getContent());
            }

            return Call.fromJson(response.getContent());
        }
    }

    public static class CallUpdater extends Updater<Call> {
        private String friendlyName;

        public CallUpdater(Factory factory) {
            super(factory);
        }

        public CallUpdater setFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }

        @Override
        public Call build(Call target) {
            return null;
        }
    }

    public static class CallLocator extends Locator<Call> {
        protected String friendlyName;

        public CallLocator(Factory factory) {
            super(factory);
        }

        @Override
        public List<Call> build() {
            return null;
        }

        public CallLocator byFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }
    }
}
