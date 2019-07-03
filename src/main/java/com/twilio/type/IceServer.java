package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.net.URI;
import java.util.Objects;

/**
 * POJO representation of a Twilio ICE server.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IceServer {
    private final String credential;
    private final String username;
    private final URI url;
    private final URI urls;


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
            @JsonProperty("url") final URI url,
            @JsonProperty("urls") final URI urls) {
        this.credential = credential;
        this.username = username;
        this.url = url;
        this.urls = urls;
    }

    public URI getUrl() {
        return url;
    }

    public URI getUrls() {
        return urls;
    }

    public String getUsername() {
        return username;
    }

    public String getCredential() {
        return credential;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IceServer other = (IceServer) o;

        return (Objects.equals(credential, other.credential) &&
                Objects.equals(username, other.username) &&
                Objects.equals(url, other.url) &&
                Objects.equals(urls, other.urls));
    }

    @Override
    public int hashCode() {
        return Objects.hash(credential, username, url, urls);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("credential", credential)
                .add("username", username)
                .add("url", url)
                .add("urls", urls)
                .toString();
    }
}
