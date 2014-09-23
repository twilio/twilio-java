package com.twilio.sdk.hash;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConsistentHashRingTest {

    @Test
    public void testAddConsistency() throws Exception {
        String aResult;
        String bResult;
        ConsistentHashRing<String> ring;

        ring = makeRing(new String[] {"Alpha", "Beta", "Gamma"});
        aResult = ring.get("Query");

        ring = makeRing(new String[] {"Alpha", "Gamma", "Beta"});
        bResult = ring.get("Query");

        assertEquals(aResult, bResult);

        ring = makeRing(new String[] {"Beta", "Alpha", "Gamma"});
        aResult = ring.get("Query");

        assertEquals(aResult, bResult);

        ring = makeRing(new String[] {"Beta", "Gamma", "Alpha"});
        bResult = ring.get("Query");

        assertEquals(aResult, bResult);

        ring = makeRing(new String[] {"Gamma", "Alpha", "Beta"});
        aResult = ring.get("Query");

        assertEquals(aResult, bResult);

        ring = makeRing(new String[] {"Gamma", "Beta", "Alpha"});
        bResult = ring.get("Query");

        assertEquals(aResult, bResult);
    }

    protected ConsistentHashRing<String> makeRing(String[] values) {
        ConsistentHashRing<String> result = new ConsistentHashRing<String>();

        for (String value : values) {
            result.add(value);
        }

        return result;
    }
}