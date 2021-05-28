package com.twilio.jwt.accesstoken;

import lombok.Builder;

@Builder
public class PlayerGrant implements Grant {

    private final String player;

    @Override
    public String getGrantKey() {
        return "player";
    }

    @Override
    public Object getPayload() {
        return player;
    }

}
