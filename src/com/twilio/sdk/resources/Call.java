package com.twilio.sdk.resources;

import java.net.URL;

public class Call {
    private String to;
    private String from;
    private URL url;
    private String friendlyName;

    public Call(String to, String from, URL url, String friendlyName) {
        this.to = to;
        this.from = from;
        this.url = url;
        this.friendlyName = friendlyName;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public URL getUrl() {
        return url;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
