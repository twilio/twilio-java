package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.deleters.NotificationDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.NotificationFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.readers.NotificationReader;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final Integer log;
    private final String responseHeaders;
    private final DateTime dateUpdated;
    private final String messageText;
    private final String requestVariables;
    private final String uri;
    private final String accountSid;
    private final DateTime messageDate;
    private final HttpMethod requestMethod;
    private final String responseBody;
    private final String callSid;
    private final String sid;
    private final DateTime dateCreated;
    private final URI requestUrl;
    private final Integer errorCode;
    private final String apiVersion;
    private final URI moreInfo;

    @JsonCreator
    private Notification(@JsonProperty("log") final Integer log,
                         @JsonProperty("response_headers") final String responseHeaders,
                         @JsonProperty("date_updated") final String dateUpdated,
                         @JsonProperty("message_text") final String messageText,
                         @JsonProperty("request_variables") final String requestVariables,
                         @JsonProperty("uri") final String uri, @JsonProperty("account_sid") final String accountSid,
                         @JsonProperty("message_date") final String messageDate,
                         @JsonProperty("request_method") final HttpMethod requestMethod,
                         @JsonProperty("response_body") final String responseBody,
                         @JsonProperty("call_sid") final String callSid, @JsonProperty("sid") final String sid,
                         @JsonProperty("date_created") final String dateCreated,
                         @JsonProperty("request_url") final URI requestUrl,
                         @JsonProperty("error_code") final Integer errorCode,
                         @JsonProperty("api_version") final String apiVersion,
                         @JsonProperty("more_info") final URI moreInfo) {
        this.log = log;
        this.responseHeaders = responseHeaders;
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.messageText = messageText;
        this.requestVariables = requestVariables;
        this.uri = uri;
        this.accountSid = accountSid;
        this.messageDate = MarshalConverter.dateTimeFromString(messageDate);
        this.requestMethod = requestMethod;
        this.responseBody = responseBody;
        this.callSid = callSid;
        this.sid = sid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.requestUrl = requestUrl;
        this.errorCode = errorCode;
        this.apiVersion = apiVersion;
        this.moreInfo = moreInfo;

    }

    public static NotificationDeleter delete(final String sid) {
        return new NotificationDeleter(sid);
    }

    public static NotificationDeleter delete(final Notification target) {
        return new NotificationDeleter(target);
    }

    public static NotificationFetcher fetch(final String sid) {
        return new NotificationFetcher(sid);
    }

    public static NotificationReader read() {
        return new NotificationReader();
    }

    public static Notification fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Notification.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Notification fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Notification.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final Integer getLog() {
        return log;
    }

    public final String getResponseHeaders() {
        return responseHeaders;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final String getMessageText() {
        return messageText;
    }

    public final String getRequestVariables() {
        return requestVariables;
    }

    public final String getUri() {
        return uri;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final DateTime getMessageDate() {
        return messageDate;
    }

    public final HttpMethod getRequestMethod() {
        return requestMethod;
    }

    public final String getResponseBody() {
        return responseBody;
    }

    public final String getCallSid() {
        return callSid;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final URI getRequestUrl() {
        return requestUrl;
    }

    public final Integer getErrorCode() {
        return errorCode;
    }

    public final String getApiVersion() {
        return apiVersion;
    }

    public final URI getMoreInfo() {
        return moreInfo;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Notification self = (Notification) o;

        return (Objects.equals(log, self.log) &&
                Objects.equals(responseHeaders, self.responseHeaders) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(messageText, self.messageText) &&
                Objects.equals(requestVariables, self.requestVariables) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(messageDate, self.messageDate) &&
                Objects.equals(requestMethod, self.requestMethod) &&
                Objects.equals(responseBody, self.responseBody) &&
                Objects.equals(callSid, self.callSid) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(requestUrl, self.requestUrl) &&
                Objects.equals(errorCode, self.errorCode) &&
                Objects.equals(apiVersion, self.apiVersion) &&
                Objects.equals(moreInfo, self.moreInfo));
    }

    @Override
    public int hashCode() {
        return Objects.hash(log, responseHeaders, dateUpdated, messageText, requestVariables, uri, accountSid,
                            messageDate, requestMethod, responseBody, callSid, sid, dateCreated, requestUrl, errorCode,
                            apiVersion, moreInfo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("log", log)
                          .add("responseHeaders", responseHeaders)
                          .add("dateUpdated", dateUpdated)
                          .add("messageText", messageText)
                          .add("requestVariables", requestVariables)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("messageDate", messageDate)
                          .add("requestMethod", requestMethod)
                          .add("responseBody", responseBody)
                          .add("callSid", callSid)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("requestUrl", requestUrl)
                          .add("errorCode", errorCode)
                          .add("apiVersion", apiVersion)
                          .add("moreInfo", moreInfo)
                          .toString();
    }
}
