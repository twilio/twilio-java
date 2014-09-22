package com.twilio.sdk.bulk;

import com.twilio.sdk.factories.CallFactory;
import com.twilio.sdk.resources.Call;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ImmediateBulkDialer implements BulkDialer {
    protected Map<String, Future<Call>> results;

    public ImmediateBulkDialer() {
        this.results = new HashMap<String, Future<Call>>();
    }

    @Override
    public void add(String key, CallFactory.CallCreator callCreator) {
        this.results.put(key, callCreator.async());
    }

    public Call get(String key) {
        if (!results.containsKey(key)) {
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

    public void complete() {
        for (String key : results.keySet()) {
            get(key);
        }
    }
}
