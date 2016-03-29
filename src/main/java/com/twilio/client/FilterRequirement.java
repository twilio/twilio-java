package com.twilio.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum FilterRequirement {
    REQUIRED(true),
    OPTIONAL(false);

    public final boolean required;
    public final Map<String, Boolean> map;

    FilterRequirement(boolean required) {
        this.required = required;

        Map<String, Boolean> map = new HashMap<>();
        map.put("required", required);
        this.map = Collections.unmodifiableMap(map);
    }

    public Map<String, Boolean> toMap() {
        return map;
    }
}

