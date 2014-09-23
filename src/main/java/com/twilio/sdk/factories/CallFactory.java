package com.twilio.sdk.factories;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

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
        try {
            return new Call("+14155551234", "+14155555551", new URL("http://www.twilio.com"), "Sample Call #1");
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public Future<Call> getAsync(final String sid) {
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
        public Call go() {
            Request request = new Request();
            Response response = new Response();
            Call call = new Call(this.to, this.from, this.url, this.friendlyName);
            response.setPayload(call);
            request.setResponse(response);

            try {
                Thread.sleep(1000L);
            } catch(InterruptedException e) {
                // ignore
            }

            Response actualResponse = this.makeRequest(request);

            return (Call)actualResponse.getPayload();
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
        public Call go(Call target) {
            Request request = new Request();
            Response response = new Response();
            Call updatedCall = new Call(target.getTo(), target.getFrom(), target.getUrl(), this.friendlyName);

            response.setPayload(updatedCall);
            request.setResponse(response);

            Response actualResponse = this.makeRequest(request);
            return (Call)actualResponse.getPayload();
        }
    }

    public static class CallLocator extends Locator<Call> {
        protected String friendlyName;

        public CallLocator(Factory factory) {
            super(factory);
        }

        @Override
        public List<Call> go() {
            Request request = new Request();
            Response response = new Response();
            List<Call> callList = new ArrayList<Call>();
            try {
                if (friendlyName == null || "Sample Call #1".equals(friendlyName)) {
                    callList.add(new Call("+14155551234", "+14155555551", new URL("http://www.twilio.com"), "Sample Call #1"));
                }

                if (friendlyName == null || "Sample Call #2".equals(friendlyName)) {
                    callList.add(new Call("+14155554567", "+14155555552", new URL("http://www.twilio.com"), "Sample Call #2"));
                }

                if (friendlyName == null || "Sample Call #3".equals(friendlyName)) {
                    callList.add(new Call("+14155557890", "+14155555553", new URL("http://www.twilio.com"), "Sample Call #3"));
                }
            } catch(MalformedURLException e) {
                // Ignore
            }

            response.setPayload(callList);
            request.setResponse(response);

            Response actualResponse = this.makeRequest(request);

            return (List<Call>)actualResponse.getPayload();
        }

        public CallLocator byFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }
    }
}
