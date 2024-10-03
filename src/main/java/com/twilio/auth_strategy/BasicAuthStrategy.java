package com.twilio.auth_strategy;

import com.twilio.constant.EnumConstants;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicAuthStrategy that = (BasicAuthStrategy) o;
        return Objects.equals(username, that.username) && 
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
