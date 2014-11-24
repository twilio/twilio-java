package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Message;
import com.twilio.sdk.resources.RestException;

import java.net.URI;
import java.net.URISyntaxException;

public class MessageCreator extends Creator<Message> {

    private final String to;
    private final String from;
    private String body;
    private String applicationSid;
    private URI statusCallback;
    private String mediaUrl;

    public MessageCreator(final String to, final String from) {

        this.to = to;
        this.from = from;
    }

    public MessageCreator setBody(final String body) {
        this.body = body;
        return this;
    }

    public MessageCreator setApplicationSid(final String applicationSid) {
        this.applicationSid = applicationSid;
        return this;
    }

    public MessageCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public MessageCreator setStatusCallback(final String statusCallback) {
        try {
            this.statusCallback = new URI(statusCallback);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public MessageCreator setMediaUrl(final String mediaUrl) {
        this.mediaUrl = mediaUrl;
        return this;
    }

    @Override
    public Message execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Messages.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Message creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (to != null) {
            request.addPostParam("To", to);
        }
        if (from != null) {
            request.addPostParam("From", from);
        }
        if (body != null) {
            request.addPostParam("Body", body);
        }
        if (applicationSid != null) {
            request.addPostParam("ApplicationSid", applicationSid);
        }
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }
        if (mediaUrl != null) {
            request.addPostParam("MediaUrl", mediaUrl);
        }
    }

}
