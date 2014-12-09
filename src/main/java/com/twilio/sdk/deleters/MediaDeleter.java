package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Media;
import com.twilio.sdk.resources.Message;
import com.twilio.sdk.resources.RestException;

public class MediaDeleter extends Deleter<Media> {

    private final String sid;
    private final String messageSid;

    public MediaDeleter(final String messageSid, final String sid) {
        this.sid = sid;
        this.messageSid = messageSid;
    }

    public MediaDeleter(final Media target) {
        this(target.getParentSid(), target.getSid());
    }

    public MediaDeleter(final Message targetMessage, final String sid) {
        this(targetMessage.getSid(), sid);
    }

    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.DELETE,
                                      String.format("/Accounts/{AccountSid}/Messages/%s/Media/%s.json", messageSid,
                                                    sid), client.getAccountSid());
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Media delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }
    }
}
