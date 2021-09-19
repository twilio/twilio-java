package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO representation of a Twilio ICE server.
 */
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

  public String getCredential() {
    return this.credential;
  }

  public String getUsername() {
    return this.username;
  }

  public String getUrl() {
    return this.url;
  }

  public String getUrls() {
    return this.urls;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof IceServer)) {
      return false;
    }
    final IceServer other = (IceServer) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    final Object this$credential = this.getCredential();
    final Object other$credential = other.getCredential();
    if (this$credential == null ? other$credential != null : !this$credential.equals(other$credential)) {
      return false;
    }
    final Object this$username = this.getUsername();
    final Object other$username = other.getUsername();
    if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
      return false;
    }
    final Object this$url = this.getUrl();
    final Object other$url = other.getUrl();
    if (this$url == null ? other$url != null : !this$url.equals(other$url)) {
      return false;
    }
    final Object this$urls = this.getUrls();
    final Object other$urls = other.getUrls();
    if (this$urls == null ? other$urls != null : !this$urls.equals(other$urls)) {
      return false;
    }
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof IceServer;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $credential = this.getCredential();
    result = result * PRIME + ($credential == null ? 43 : $credential.hashCode());
    final Object $username = this.getUsername();
    result = result * PRIME + ($username == null ? 43 : $username.hashCode());
    final Object $url = this.getUrl();
    result = result * PRIME + ($url == null ? 43 : $url.hashCode());
    final Object $urls = this.getUrls();
    result = result * PRIME + ($urls == null ? 43 : $urls.hashCode());
    return result;
  }

  public String toString() {
    return "IceServer(credential=" + this.getCredential() + ", username=" + this.getUsername() + ", url=" + this.getUrl() + ", urls=" + this.getUrls() + ")";
  }
}
