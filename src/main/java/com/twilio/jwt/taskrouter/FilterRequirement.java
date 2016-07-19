package com.twilio.jwt.taskrouter;

public enum FilterRequirement {
    REQUIRED(true),
    OPTIONAL(false);

    public final boolean required;

    FilterRequirement(boolean required) {
        this.required = required;
    }

    public boolean value() {
        return this.required;
    }
}

