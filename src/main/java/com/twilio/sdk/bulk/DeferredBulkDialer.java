package com.twilio.sdk.bulk;

import com.twilio.sdk.factories.CallFactory;
import com.twilio.sdk.resources.Call;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DeferredBulkDialer implements BulkDialer {
    protected Map<String, CallFactory.CallCreator> promises;
    protected Map<String, Future<Call>> results;

    public DeferredBulkDialer() {
        this.promises = new HashMap<String, CallFactory.CallCreator>();
        this.results = new HashMap<String, Future<Call>>();
    }

    @Override
    public void add(String key, CallFactory.CallCreator callCreator) {
        this.promises.put(key, callCreator);
    }

    @Override
    public Call get(String key) {
        // If there is no Future to resolve, bail
        if (!async(key)) {
            return null;
        }

        try {
            return results.get(key).get();
        } catch (InterruptedException e) {
            // Log and continue
        } catch (ExecutionException e) {
            // Log and continue
        }

        return null;
    }

    protected boolean async(String key) {
        // No promise, abort
        if (!promises.containsKey(key)) {
            return false;
        }

        // Already converted, nothing to do here
        if (results.containsKey(key)) {
            return true;
        }

        // Convert the promise into a future
        results.put(key, promises.get(key).async());
        return true;
    }

    @Override
    public void complete() {
        // First make sure every promise gets converted into a result
        for (String key : promises.keySet()) {
            async(key);
        }

        // Then make sure every result gets resolved
        for (String key : results.keySet()) {
            get(key);
        }
    }
}
