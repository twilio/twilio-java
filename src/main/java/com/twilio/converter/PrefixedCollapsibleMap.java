package com.twilio.converter;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefixedCollapsibleMap {

    private static Map<String, String> flatten(
        Map<String, Object> map,
        Map<String, String> result,
        List<String> previous
    ) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            List<String> next = Lists.newArrayList(previous);
            next.add(entry.getKey());

            if (entry.getValue() instanceof Map) {
                flatten((Map<String, Object>)entry.getValue(), result, next);
            } else {
                result.put(Joiner.on('.').join(next), entry.getValue().toString());
            }
        }

        return result;
    }

    /**
     * Flatten a Map of String, Object into a Map of String, String where keys are '.' separated
     * and prepends a key.
     *
     * @param map map to transform
     * @param prefix key to prepend
     * @return flattened map
     */
    public static Map<String, String> serialize(Map<String, Object> map, String prefix) {
        if (map == null || map.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, String> flattened = flatten(map, new HashMap<String, String>(), new ArrayList<String>());

        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : flattened.entrySet()) {
            result.put(prefix + "." + entry.getKey(), entry.getValue());
        }

        return result;
    }
    
}
