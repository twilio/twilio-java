package com.twilio.sdk.factories;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.RequiredFieldException;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.URL;
import java.util.ArrayList;

public class CallFactory extends Factory {

    public CallFactory(TwilioRestClient client) {
        super(client);
    }

    public CallCreator create() {
        return new CallCreator(this);
    }

    public CallCreator create(String to, String from, URL url) {
        return new CallCreator(this).setTo(to)
                                    .setFrom(from)
                                    .setUrl(url);
    }

    public CallUpdater update() {
        return new CallUpdater(this);
    }

    public CallUpdater update(Call call) {
        return new CallUpdater(this, call);
    }

    public static class CallCreator extends Creator {
        protected String to;
        protected String from;
        protected URL url;
        protected String friendlyName;

        public CallCreator(Factory factory) {
            super(factory);
        }

        public void checkRequiredFields() {
            ArrayList<String> fields = new ArrayList<String>();

            if (this.to == null) {
                fields.add("to");
            }

            if (this.from == null) {
                fields.add("from");
            }

            if (this.url == null) {
                fields.add("url");
            }

            if (!fields.isEmpty()) {
                throw new RequiredFieldException(fields);
            }
        }

        public Call go() {
            checkRequiredFields();

            Request request = new Request();
            Response response = new Response();
            Call call = new Call(this.to, this.from, this.url, this.friendlyName);
            response.setPayload(call);
            request.setResponse(response);

            Response actualResponse = this.factory.makeRequest(request);

            return (Call)actualResponse.getPayload();
        }

        public CallCreator setTo(String to) {
            this.to = to;
            return this;
        }

        public CallCreator setFrom(String from) {
            this.from = from;
            return this;
        }

        public CallCreator setUrl(URL url) {
            this.url = url;
            return this;
        }
    }

    public static class CallUpdater {
        private CallFactory factory;
        private Call target;
        private String friendlyName;

        public CallUpdater(CallFactory factory) {
            this.factory = factory;
        }

        public CallUpdater(CallFactory factory, Call target) {
            this.factory = factory;
            this.target = target;
        }

        public CallUpdater setFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }

        public Call go(Call target) {
            this.target = target;
            return this.go();
        }

        public Call go() {
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
