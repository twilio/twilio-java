package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.TokenCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token extends Resource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String username;
    private final String password;
    private final DateTime dateUpdated;
    private final String accountSid;
    private final Integer ttl;
    private final DateTime dateCreated;
    private final List<IceServer> iceServers;

    @JsonCreator
    private Token(@JsonProperty("username") final String username, @JsonProperty("password") final String password,
                  @JsonProperty("date_updated") final String dateUpdated,
                  @JsonProperty("account_sid") final String accountSid, @JsonProperty("ttl") final Integer ttl,
                  @JsonProperty("date_created") final String dateCreated,
                  @JsonProperty("ice_servers") final List<IceServer> iceServers) {
        this.username = username;
        this.password = password;
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.accountSid = accountSid;
        this.ttl = ttl;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.iceServers = iceServers;
    }

    public static TokenCreator create() {
        return new TokenCreator();
    }

    public static Token fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Token.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Token fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Token.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final String getUsername() {
        return username;
    }

    public final String getPassword() {
        return password;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final Integer getTtl() {
        return ttl;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final List<IceServer> getIceServers() {
        return iceServers;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Token self = (Token) o;

        return (Objects.equals(username, self.username) &&
                Objects.equals(password, self.password) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(ttl, self.ttl) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(iceServers, self.iceServers));
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, dateUpdated, accountSid, ttl, dateCreated, iceServers);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("username", username)
                          .add("password", password)
                          .add("dateUpdated", dateUpdated)
                          .add("accountSid", accountSid)
                          .add("ttl", ttl)
                          .add("dateCreated", dateCreated)
                          .add("iceServers", iceServers)
                          .toString();
    }
}
