package com.twilio.sdk.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashRing<T> {

    private final HashFunction hashFunction;
    private final int iterations;
    private final SortedMap<Integer, T> circle = new TreeMap<>();

    public static final int DEFAULT_ITERATIONS = 10;
    public static final HashFunction DEFAULT_HASH_FUNCTION = Hashing.md5();

    public ConsistentHashRing() {
        this(DEFAULT_HASH_FUNCTION);
    }

    public ConsistentHashRing(final HashFunction hashFunction) {
        this(hashFunction, DEFAULT_ITERATIONS);
    }

    public ConsistentHashRing(final HashFunction hashFunction, final int iterations) {
        this.hashFunction = hashFunction;
        this.iterations = iterations;
    }

    public ConsistentHashRing<T> add(final T node) {
        for (int i = 0; i < iterations; i++) {
            circle.put(hash(node.toString() + i), node);
        }
        return this;
    }

    public ConsistentHashRing<T> remove(final T node) {
        for (int i = 0; i < iterations; i++) {
            circle.remove(hash(node.toString() + i));
        }
        return this;
    }

    public T get(final String key) {
        if (circle.isEmpty()) {
            return null;
        }

        int hash = hash(key);

        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    protected int hash(final String payload) {
        return hashFunction.newHasher().putString(payload, Charsets.UTF_8).hash().asInt() % 360;
    }

}
