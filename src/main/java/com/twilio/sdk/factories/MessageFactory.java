package com.twilio.sdk.factories;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Message;

public class MessageFactory extends Factory {

    public MessageFactory(TwilioRestClient client) {
        super(client);
    }

    public MessageCreator create(String to, String from, String body) {
        return new MessageCreator(this, to, from, body);
    }

    public MessageUpdater update() {
        return new MessageUpdater(this);
    }

    public static class MessageCreator extends Creator<Message> {

        private String to;
        private String from;
        private String body;
        private String friendlyName;

        public MessageCreator(Factory factory, String to, String from, String body) {
            super(factory);
            this.to = to;
            this.from = from;
            this.body = body;
            this.friendlyName = null;
        }

        public MessageCreator setFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }

        @Override
        public Message build() {
            Request request = new Request();
            Response response = new Response();
            Message message = new Message(this.to, this.from, this.body, this.friendlyName);
            response.setPayload(message);
            request.setResponse(response);

            Response actualResponse = this.factory.makeRequest(request);

            return (Message)actualResponse.getPayload();
        }
    }

    public static class MessageUpdater extends Updater<Message> {

        private String friendlyName;

        public MessageUpdater(Factory factory) {
            super(factory);
        }

        public MessageUpdater setFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }

        @Override
        public Message build(Message target) {
            Request request = new Request();
            Response response = new Response();
            Message message = new Message(target.getTo(), target.getFrom(), target.getBody(), this.friendlyName);

            response.setPayload(message);
            request.setResponse(response);

            Response actualResponse = this.makeRequest(request);

            return (Message)actualResponse.getPayload();
        }
    }
}
