package com.twilio.sdk.bulk;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.exceptions.AuthenticationException;
import com.twilio.sdk.resources.Call;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class ImmediateBulkDialer implements BulkDialer {
    protected Map<String, ListenableFuture<Call>> results;

    public ImmediateBulkDialer() {
        results = new HashMap<>();
    }

    @Override
    public void add(final String key, final CallCreator callCreator) {
        results.put(key, callCreator.async());
    }

    public Call get(final String key) {
        if (!results.containsKey(key)) {
            return null;
        }

        try {
            return results.get(key).get();
        } catch (final InterruptedException | ExecutionException e) {
            // Log and continue
        }

        return null;
    }

    public void complete() {
        for (final String key : results.keySet()) {
            get(key);
        }
    }

    @Override
    public Iterator<Call> iterator() {
        List<Call> calls = new ArrayList<>();
        for (final String key : results.keySet()) {
            calls.add(get(key));
        }

        return calls.iterator();
    }
}
