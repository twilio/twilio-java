package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.CompletableFuture;

/**
 * Executor for deletes of a resource that return a response object.
 *
 * @param <T> type of the resource
 * @param <R> type of the delete response
 */
public abstract class ResourceDeleter<T extends Resource, R extends Resource> {

  public CompletableFuture<R> deleteAsync() {
    return deleteAsync(Twilio.getRestClient());
  }

  public CompletableFuture<R> deleteAsync(final TwilioRestClient client) {
    return CompletableFuture.supplyAsync(() -> delete(client), Twilio.getExecutorService());
  }

  public R delete() {
    return delete(Twilio.getRestClient());
  }

  public abstract R delete(final TwilioRestClient client);

  public TwilioResponse<R> deleteWithResponse() {
    return deleteWithResponse(Twilio.getRestClient());
  }

  public TwilioResponse<R> deleteWithResponse(final TwilioRestClient client) {
    throw new UnsupportedOperationException("deleteWithResponse is not supported for this resource.");
  }
}
