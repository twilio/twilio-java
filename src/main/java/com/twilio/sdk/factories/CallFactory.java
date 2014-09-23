package com.twilio.sdk.factories;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.URL;

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

            Response actualResponse = this.factory.makeRequest(request);
            return (Call)actualResponse.getPayload();
        }
    }
}
