package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO representation of a Twilio ICE server.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IceServer {
    private final String credential;
    private final String username;
    private final String url;
    private final String urls;


    /**
     * Initialize an IceServer.
     *
     * @param credential credentials for the server
     * @param username   user account name
     * @param url        url of server
     * @param urls       url of server
     */
    @JsonCreator
    public IceServer(@JsonProperty("credential") final String credential,
                     @JsonProperty("username") final String username,
                     @JsonProperty("url") final String url,
                     @JsonProperty("urls") final String urls) {
        this.credential = credential;
        this.username = username;
        this.url = url;
        this.urls = urls;
    }
}
