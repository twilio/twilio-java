package com.twilio.sdk.factories;

public class MessageFactory {

    public MessageCreator create() {

    }

    public MessageCreator create(String to, String from, String body) {

    }

    public static class MessageCreator extends Creator {

        private String to;
        private String from;
        private String body;

        public MessageCreator(Factory factory) {
            super(factory);
        }
    }
}
