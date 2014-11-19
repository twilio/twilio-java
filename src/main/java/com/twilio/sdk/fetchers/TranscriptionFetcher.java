package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Transcription;

public class TranscriptionFetcher extends Fetcher<Transcription> {

    private final String sid;

    public TranscriptionFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Transcription execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Transcriptions/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find Transcription for Sid " + sid);
        }

        return Transcription.fromJson(response.getStream(), client.getObjectMapper());
    }
}