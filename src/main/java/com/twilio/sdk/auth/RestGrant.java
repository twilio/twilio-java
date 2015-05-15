package com.twilio.sdk.auth;

import java.util.Set;

public class RestGrant extends Grant {
    public RestGrant(String resource) {
        super(resource);
    }

    public RestGrant(String resource, Action action) {
        super(resource, action);
    }

    public RestGrant(String resource, Set<Action> actions) {
        super(resource, actions);
    }
}
