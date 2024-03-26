package com.twilio.models;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
public class OAuthResponseBody {

    @JsonProperty("access_token")
    @Getter
    @Setter
    private String accessToken;

    @JsonProperty("expires_in")
    @Getter
    @Setter
    private String expiresIn;

    @JsonProperty("token_type")
    @Getter
    @Setter
    private String tokenType;

    public boolean isAccessTokenExpired() {
        DecodedJWT jwt = JWT.decode(this.accessToken);
        Date expiresAt = jwt.getExpiresAt();
        // Add a buffer of 30 seconds
        long bufferMilliseconds = 30 * 1000;
        Date bufferExpiresAt = new Date(expiresAt.getTime() - bufferMilliseconds);
        return bufferExpiresAt.before(new Date());
    }
}