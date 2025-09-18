package com.twilio.auth_strategy;

import com.twilio.constant.EnumConstants;

// Does not have any state thus thread safe.
public class NoAuthStrategy extends AuthStrategy {
    private static volatile NoAuthStrategy INSTANCE;

    private static volatile NoAuthStrategy instance;

    public static NoAuthStrategy getInstance() {
        if (instance == null) {
            synchronized (NoAuthStrategy.class) {
                if (instance == null) {
                    instance = new NoAuthStrategy();
                }
            }
        }
        return instance;
    }

    private NoAuthStrategy() {
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
