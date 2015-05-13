package com.twilio.sdk.auth;

import java.util.HashSet;

public class EndpointGrant extends Grant {
    public EndpointGrant(String endpoint) {
        super(endpoint, new HashSet<Action>(2) {{ add(Action.LISTEN); add(Action.INVITE); }});
    }
}
