package com.twilio.sdk.auth;

import java.util.HashSet;
import java.util.Set;

public class EndpointGrant extends Grant {
    public EndpointGrant(String endpoint) {
        super(endpoint, new HashSet<Action>(2) {{ add(Action.LISTEN); add(Action.INVITE); }});
    }

    public EndpointGrant(String resource, Action action) {
        super(resource, action);
    }

    public EndpointGrant(String resource, Set<Action> actions) {
        super(resource, actions);
    }
}
