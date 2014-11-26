package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.net.URI;
import java.util.Objects;

public class IceServer {
    private String credential;
    private String username;
    private URI url;

    public URI getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getCredential() {
        return credential;
    }

    @JsonCreator
    public IceServer(@JsonProperty("credential") final String credential,
                     @JsonProperty("username") final String username,
                     @JsonProperty("url") final URI url) {
        this.credential = credential;
        this.username = username;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IceServer other = (IceServer) o;

        return (Objects.equals(credential, other.credential) &&
                Objects.equals(username, other.username) &&
                Objects.equals(url, other.url)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(credential, username, url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("credential", credential)
                .add("username", username)
                .add("url", url)
                .toString();
    }
}
