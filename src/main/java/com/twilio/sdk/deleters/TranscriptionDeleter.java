package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Transcription;

public class TranscriptionDeleter extends Deleter<Transcription> {

    private final String sid;

    public TranscriptionDeleter(final String sid) {
        this.sid = sid;
    }

    public TranscriptionDeleter(final Transcription target) {
        this(target.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.DELETE, "/Transcriptions/" + sid + ".json");
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Transcription delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            throw new ApiException(
                    "Transcription delete failed: [" + response.getStatusCode() + "] " + response.getContent());
        }
    }
}
