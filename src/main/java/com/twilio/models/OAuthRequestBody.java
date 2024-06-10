package com.twilio.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class OAuthRequestBody {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("client_id")
    @Setter
    private String clientId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("client_secret")
    @Setter
    private String clientSecret;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("audience")
    @Getter
    @Setter
    private String audience = "https://www.twilio.com/organizations";

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("grant_type")
    @Getter
    @Setter
    private String grantType = "client_credentials";
    
    public OAuthRequestBody(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
