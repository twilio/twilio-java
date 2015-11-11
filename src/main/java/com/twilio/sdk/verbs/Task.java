package com.twilio.sdk.verbs;

import java.util.Map;

import com.twilio.sdk.TwilioUtils;

public class Task extends Verb {

    public Task(String attributes) {
        super(V_TASK, attributes);
    }

    public Task(Map<String, String> attributes)  {
        super(V_TASK, TwilioUtils.asJsonString(attributes));
    }

    public void setPriority(final int priority) {
        this.set("priority", String.valueOf(priority));
    }

    public void setTimeout(final int timeout) {
        this.set("timeout", String.valueOf(timeout));
    }
}
