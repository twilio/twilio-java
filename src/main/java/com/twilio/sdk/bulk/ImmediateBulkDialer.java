package com.twilio.sdk.bulk;

import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.resources.Call;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ImmediateBulkDialer implements BulkDialer {
    protected Map<String, Future<Call>> results;

    public ImmediateBulkDialer() {
        this.results = new HashMap<String, Future<Call>>();
    }

    @Override
    public void add(String key, CallCreator callCreator) {
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

    @Override
    public Iterator<Call> iterator() {
        List<Call> calls = new ArrayList<Call>();
        for (String key : results.keySet()) {
            calls.add(get(key));
        }

        return calls.iterator();
    }
}
