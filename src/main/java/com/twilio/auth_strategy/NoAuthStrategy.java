package com.twilio.auth_strategy;

import com.twilio.constant.EnumConstants;

public class NoAuthStrategy extends AuthStrategy {

    public NoAuthStrategy(String token) {
        super(EnumConstants.AuthType.NO_AUTH);
    }

    @Override
    public String getAuthString() {
        return "";
    }

    @Override
    public boolean requiresAuthentication() {
        return false;
    }
}
