package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CallPage extends Page<Call> {

    @JsonCreator
    private CallPage(@JsonProperty("calls") List<Call> calls,
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

    public static Page<Call> fromJson(String json) {
        return Page.fromJson(json, CallPage.class);
    }
}
