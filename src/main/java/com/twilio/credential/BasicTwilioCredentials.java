package com.twilio.credential;

import lombok.Getter;
import lombok.Setter;

public class BasicTwilioCredentials implements TwilioCredentials {

    @Getter @Setter
    private String accountSid;
    @Getter @Setter
    private String authToken;

    public BasicTwilioCredentials(String accountSid, String authToken) {

        this.accountSid = accountSid;
        this.authToken  = authToken;

    }

    public TwilioCredentials getCredentials() {
        return this;
    }

}
