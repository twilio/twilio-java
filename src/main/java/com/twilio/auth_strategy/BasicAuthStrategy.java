package com.twilio.auth_strategy;

import com.twilio.constant.EnumConstants;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthStrategy extends AuthStrategy {
    private String username;
    private String password;

    public BasicAuthStrategy(String username, String password) {
        super(EnumConstants.AuthType.BASIC);
        this.username = username;
        this.password = password;
    }

    @Override
    public String getAuthString() {
        String credentials = username + ":" + password;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + encoded;
    }

    @Override
    public boolean requiresAuthentication() {
        return true;
    }
}
