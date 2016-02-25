package com.twilio.sdk.creators.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.Promoter;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.Message;

import java.net.URI;
import java.util.List;

public class MessageCreator extends Creator<Message> {
    private final String accountSid;
    private final com.twilio.types.PhoneNumber to;
    private final com.twilio.types.PhoneNumber from;
    private String body;
    private List<URI> mediaUrl;
    private URI statusCallback;
    private String applicationSid;

    /**
     * Construct a new MessageCreator.
     * 
     * @param accountSid The account_sid
     * @param to The phone number to receive the message
     * @param from The phone number that initiated the message
     * @param body The body
     */
    public MessageCreator(final String accountSid, 
                          final com.twilio.types.PhoneNumber to, 
                          final com.twilio.types.PhoneNumber from, 
                          final String body) {
        this.accountSid = accountSid;
        this.to = to;
        this.from = from;
        this.body = body;
    }

    /**
     * Construct a new MessageCreator.
     * 
     * @param accountSid The account_sid
     * @param to The phone number to receive the message
     * @param from The phone number that initiated the message
     * @param mediaUrl The media_url
     */
    public MessageCreator(final String accountSid, 
                          final com.twilio.types.PhoneNumber to, 
                          final com.twilio.types.PhoneNumber from, 
                          final List<URI> mediaUrl) {
        this.accountSid = accountSid;
        this.to = to;
        this.from = from;
        this.mediaUrl = mediaUrl;
    }

    /**
     * The URL that Twilio will POST to each time your message status changes.
     * 
     * @param statusCallback URL Twilio will request when the status changes
     * @return this
     */
    public MessageCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL that Twilio will POST to each time your message status changes.
     * 
     * @param statusCallback URL Twilio will request when the status changes
     * @return this
     */
    public MessageCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * Twilio the POST MessageSid as well as MessageStatus to the URL in the
     * MessageStatusCallback property of this Application.
     * 
     * @param applicationSid The application to use for callbacks
     * @return this
     */
    public MessageCreator setApplicationSid(final String applicationSid) {
        this.applicationSid = applicationSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Message
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Message execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/Messages.json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Message creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (to != null) {
            request.addPostParam("To", to.toString());
        }
        
        if (from != null) {
            request.addPostParam("From", from.toString());
        }
        
        if (body != null) {
            request.addPostParam("Body", body);
        }
        
        if (mediaUrl != null) {
            request.addPostParam("MediaUrl", mediaUrl.toString());
        }
        
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }
        
        if (applicationSid != null) {
            request.addPostParam("ApplicationSid", applicationSid);
        }
    }
}