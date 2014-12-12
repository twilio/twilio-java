package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Message;
import com.twilio.sdk.resources.RestException;

public class MessageUpdater extends Updater<Message> {

    private final String sid;
    private String body;
    
    public MessageUpdater(final String sid) {
        this.sid = sid;
    }

    public MessageUpdater(final Message target) {
        this(target.getSid());
    }

    public MessageUpdater setBody(final String body) {
        this.body = body;
        return this;
    }
    
    @Override
    public Message execute(final TwilioRestClient client)  {
        Request request = new Request(HttpMethod.POST, "/2010-04-01/Accounts/{AccountSid}/Messages/" + sid + ".json", client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Message update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        
        if (body != null) {
            request.addPostParam("Body", body);
        }
        
    }
}