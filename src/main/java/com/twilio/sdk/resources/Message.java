package com.twilio.sdk.resources;

public class Message {
    private String to;
    private String from;
    private String body;
    private String friendlyName;

    public Message(String to, String from, String body, String friendlyName) {
        this.to = to;
        this.from = from;
        this.body = body;
        this.friendlyName = friendlyName;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getBody() {
        return body;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
