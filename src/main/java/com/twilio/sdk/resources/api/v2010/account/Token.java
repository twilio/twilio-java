package com.twilio.sdk.resources.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.v2010.account.TokenCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.IceServer;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token extends Resource {
    private static final long serialVersionUID = 281090396283982L;

    /**
     * Create a new token
     * 
     * @param accountSid The account_sid
     * @return TokenCreator capable of executing the create
     */
    public static TokenCreator create(final String accountSid) {
        return new TokenCreator(accountSid);
    }

    /**
     * Converts a JSON String into a Token object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Token object represented by the provided JSON
     */
    public static Token fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Token.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Token object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Token object represented by the provided JSON
     */
    public static Token fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Token.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final List<IceServer> iceServers;
    private final String password;
    private final String ttl;
    private final String username;

    @JsonCreator
    private Token(@JsonProperty("account_sid")
                  final String accountSid, 
                  @JsonProperty("date_created")
                  final String dateCreated, 
                  @JsonProperty("date_updated")
                  final String dateUpdated, 
                  @JsonProperty("ice_servers")
                  final List<IceServer> iceServers, 
                  @JsonProperty("password")
                  final String password, 
                  @JsonProperty("ttl")
                  final String ttl, 
                  @JsonProperty("username")
                  final String username) {
        this.accountSid = accountSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.iceServers = iceServers;
        this.password = password;
        this.ttl = ttl;
        this.username = username;
    }

    /**
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The date this resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date this resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return An array representing the ephemeral credentials
     */
    public final List<IceServer> getIceServers() {
        return this.iceServers;
    }

    /**
     * @return The temporary password used for authenticating
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * @return The duration in seconds the credentials are valid
     */
    public final String getTtl() {
        return this.ttl;
    }

    /**
     * @return The temporary username that uniquely identifies a Token.
     */
    public final String getUsername() {
        return this.username;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Token other = (Token) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(iceServers, other.iceServers) && 
               Objects.equals(password, other.password) && 
               Objects.equals(ttl, other.ttl) && 
               Objects.equals(username, other.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            iceServers,
                            password,
                            ttl,
                            username);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("iceServers", iceServers)
                          .add("password", password)
                          .add("ttl", ttl)
                          .add("username", username)
                          .toString();
    }
}