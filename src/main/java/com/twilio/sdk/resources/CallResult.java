package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.Iterator;
import java.util.List;

public class CallResult extends Result<Call> {

    @JsonCreator
    private CallResult(@JsonProperty("calls") List<Call> calls,
                       @JsonProperty("first_page_uri") String firstPageUri,
                       @JsonProperty("next_page_uri") String nextPageUri,
                       @JsonProperty("previous_page_uri") String previousPageUri,
                       @JsonProperty("uri") String uri) {
        this.records = calls;
        this.firstPageUri = firstPageUri;
        this.nextPageUri = nextPageUri;
        this.previousPageUri = previousPageUri;
        this.uri = uri;
    }

    public static CallResult fromJson(String json) {
        return Result.fromJson(json, CallResult.class);
    }

    public Iterator<Call> fetchNextPage() {
        if (!this.autoPage) {
            return null;
        }

        Request request = new Request("GET", this.getNextPageUri());
        Response response = client.request(request);

        CallResult nextPage = CallResult.fromJson(response.getContent());
        this.records = nextPage.records;
        return this.records.iterator();
    }
}
