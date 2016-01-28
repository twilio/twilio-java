package com.twilio.sdk.resources.monitor.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.deleters.monitor.v1.AlertDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.monitor.v1.AlertFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.monitor.v1.AlertReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Alert extends SidResource {
    private static final long serialVersionUID = 84420812948753L;

    /**
     * fetch
     * 
     * @param sid The sid
     * @return AlertFetcher capable of executing the fetch
     */
    public static AlertFetcher fetch(final String sid) {
        return new AlertFetcher(sid);
    }

    /**
     * delete
     * 
     * @param sid The sid
     * @return AlertDeleter capable of executing the delete
     */
    public static AlertDeleter delete(final String sid) {
        return new AlertDeleter(sid);
    }

    /**
     * read
     * 
     * @return AlertReader capable of executing the read
     */
    public static AlertReader read() {
        return new AlertReader();
    }

    /**
     * Converts a JSON String into a Alert object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Alert object represented by the provided JSON
     */
    public static Alert fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Alert.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Alert object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Alert object represented by the provided JSON
     */
    public static Alert fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Alert.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String alertText;
    private final String apiVersion;
    private final DateTime dateCreated;
    private final DateTime dateGenerated;
    private final DateTime dateUpdated;
    private final String errorCode;
    private final String logLevel;
    private final String moreInfo;
    private final HttpMethod requestMethod;
    private final String requestUrl;
    private final String requestVariables;
    private final String resourceSid;
    private final String responseBody;
    private final String responseHeaders;
    private final String sid;
    private final URI url;

    @JsonCreator
    private Alert(@JsonProperty("account_sid")
                  final String accountSid, 
                  @JsonProperty("alert_text")
                  final String alertText, 
                  @JsonProperty("api_version")
                  final String apiVersion, 
                  @JsonProperty("date_created")
                  final String dateCreated, 
                  @JsonProperty("date_generated")
                  final String dateGenerated, 
                  @JsonProperty("date_updated")
                  final String dateUpdated, 
                  @JsonProperty("error_code")
                  final String errorCode, 
                  @JsonProperty("log_level")
                  final String logLevel, 
                  @JsonProperty("more_info")
                  final String moreInfo, 
                  @JsonProperty("request_method")
                  final HttpMethod requestMethod, 
                  @JsonProperty("request_url")
                  final String requestUrl, 
                  @JsonProperty("request_variables")
                  final String requestVariables, 
                  @JsonProperty("resource_sid")
                  final String resourceSid, 
                  @JsonProperty("response_body")
                  final String responseBody, 
                  @JsonProperty("response_headers")
                  final String responseHeaders, 
                  @JsonProperty("sid")
                  final String sid, 
                  @JsonProperty("url")
                  final URI url) {
        this.accountSid = accountSid;
        this.alertText = alertText;
        this.apiVersion = apiVersion;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateGenerated = MarshalConverter.dateTimeFromString(dateGenerated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.errorCode = errorCode;
        this.logLevel = logLevel;
        this.moreInfo = moreInfo;
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.requestVariables = requestVariables;
        this.resourceSid = resourceSid;
        this.responseBody = responseBody;
        this.responseHeaders = responseHeaders;
        this.sid = sid;
        this.url = url;
    }

    /**
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The alert_text
     */
    public final String getAlertText() {
        return this.alertText;
    }

    /**
     * @return The api_version
     */
    public final String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date_generated
     */
    public final DateTime getDateGenerated() {
        return this.dateGenerated;
    }

    /**
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return The error_code
     */
    public final String getErrorCode() {
        return this.errorCode;
    }

    /**
     * @return The log_level
     */
    public final String getLogLevel() {
        return this.logLevel;
    }

    /**
     * @return The more_info
     */
    public final String getMoreInfo() {
        return this.moreInfo;
    }

    /**
     * @return The request_method
     */
    public final HttpMethod getRequestMethod() {
        return this.requestMethod;
    }

    /**
     * @return The request_url
     */
    public final String getRequestUrl() {
        return this.requestUrl;
    }

    /**
     * @return The request_variables
     */
    public final String getRequestVariables() {
        return this.requestVariables;
    }

    /**
     * @return The resource_sid
     */
    public final String getResourceSid() {
        return this.resourceSid;
    }

    /**
     * @return The response_body
     */
    public final String getResponseBody() {
        return this.responseBody;
    }

    /**
     * @return The response_headers
     */
    public final String getResponseHeaders() {
        return this.responseHeaders;
    }

    /**
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
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
        
        Alert other = (Alert) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(alertText, other.alertText) && 
               Objects.equals(apiVersion, other.apiVersion) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateGenerated, other.dateGenerated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(errorCode, other.errorCode) && 
               Objects.equals(logLevel, other.logLevel) && 
               Objects.equals(moreInfo, other.moreInfo) && 
               Objects.equals(requestMethod, other.requestMethod) && 
               Objects.equals(requestUrl, other.requestUrl) && 
               Objects.equals(requestVariables, other.requestVariables) && 
               Objects.equals(resourceSid, other.resourceSid) && 
               Objects.equals(responseBody, other.responseBody) && 
               Objects.equals(responseHeaders, other.responseHeaders) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            alertText,
                            apiVersion,
                            dateCreated,
                            dateGenerated,
                            dateUpdated,
                            errorCode,
                            logLevel,
                            moreInfo,
                            requestMethod,
                            requestUrl,
                            requestVariables,
                            resourceSid,
                            responseBody,
                            responseHeaders,
                            sid,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("alertText", alertText)
                          .add("apiVersion", apiVersion)
                          .add("dateCreated", dateCreated)
                          .add("dateGenerated", dateGenerated)
                          .add("dateUpdated", dateUpdated)
                          .add("errorCode", errorCode)
                          .add("logLevel", logLevel)
                          .add("moreInfo", moreInfo)
                          .add("requestMethod", requestMethod)
                          .add("requestUrl", requestUrl)
                          .add("requestVariables", requestVariables)
                          .add("resourceSid", resourceSid)
                          .add("responseBody", responseBody)
                          .add("responseHeaders", responseHeaders)
                          .add("sid", sid)
                          .add("url", url)
                          .toString();
    }
}