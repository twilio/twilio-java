package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Message;



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
        Request request = new Request(HttpMethod.POST, "/Messages/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Message update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Message update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        
        if (body != null) {
            request.addPostParam("Body", body);
        }
        
    }
}