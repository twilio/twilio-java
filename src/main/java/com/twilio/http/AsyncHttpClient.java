package com.twilio.http;

import com.google.common.util.concurrent.ListenableFuture;

public interface AsyncHttpClient {

    ListenableFuture<Response> reliableRequest(final Request request);

}
