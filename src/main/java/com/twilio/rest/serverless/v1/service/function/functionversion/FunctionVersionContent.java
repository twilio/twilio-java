/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.serverless.v1.service.function.functionversion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionVersionContent extends Resource {
    private static final long serialVersionUID = 128349955760970L;

    /**
     * Create a FunctionVersionContentFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Service to fetch the Function Version
     *                       content from
     * @param pathFunctionSid The SID of the function that is the parent of the
     *                        Function Version content to fetch
     * @param pathSid The SID that identifies the Function Version content to fetch
     * @return FunctionVersionContentFetcher capable of executing the fetch
     */
    public static FunctionVersionContentFetcher fetcher(final String pathServiceSid,
                                                        final String pathFunctionSid,
                                                        final String pathSid) {
        return new FunctionVersionContentFetcher(pathServiceSid, pathFunctionSid, pathSid);
    }

    /**
     * Converts a JSON String into a FunctionVersionContent object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return FunctionVersionContent object represented by the provided JSON
     */
    public static FunctionVersionContent fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FunctionVersionContent.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a FunctionVersionContent object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return FunctionVersionContent object represented by the provided JSON
     */
    public static FunctionVersionContent fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FunctionVersionContent.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String functionSid;
    private final String content;
    private final URI url;

    @JsonCreator
    private FunctionVersionContent(@JsonProperty("sid")
                                   final String sid,
                                   @JsonProperty("account_sid")
                                   final String accountSid,
                                   @JsonProperty("service_sid")
                                   final String serviceSid,
                                   @JsonProperty("function_sid")
                                   final String functionSid,
                                   @JsonProperty("content")
                                   final String content,
                                   @JsonProperty("url")
                                   final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.functionSid = functionSid;
        this.content = content;
        this.url = url;
    }

    /**
     * Returns The unique string that identifies the Function Version resource.
     *
     * @return The unique string that identifies the Function Version resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the Account that created the Function Version resource.
     *
     * @return The SID of the Account that created the Function Version resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Service that the Function Version resource is
     * associated with.
     *
     * @return The SID of the Service that the Function Version resource is
     *         associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The SID of the Function that is the parent of the Function Version.
     *
     * @return The SID of the Function that is the parent of the Function Version
     */
    public final String getFunctionSid() {
        return this.functionSid;
    }

    /**
     * Returns The content of the Function Version resource.
     *
     * @return The content of the Function Version resource
     */
    public final String getContent() {
        return this.content;
    }

    /**
     * Returns The url.
     *
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FunctionVersionContent other = (FunctionVersionContent) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(functionSid, other.functionSid) &&
               Objects.equals(content, other.content) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            functionSid,
                            content,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("serviceSid", serviceSid)
                          .add("functionSid", functionSid)
                          .add("content", content)
                          .add("url", url)
                          .toString();
    }
}