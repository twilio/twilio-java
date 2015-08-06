package com.twilio.sdk.verbs;

import java.util.Map;

import org.json.simple.JSONObject;

public class Task extends Verb {

    public Task(String attributes) {
        super("Task", attributes);
    }

    public Task(Map<String, String> attributes) {
        super("Task", JSONObject.toJSONString(attributes));
    }

    public void setPriority(final int priority) {
        this.set("priority", String.valueOf(priority));
    }

    public void setTimeout(final int timeout) {
        this.set("timeout", String.valueOf(timeout));
    }
}
