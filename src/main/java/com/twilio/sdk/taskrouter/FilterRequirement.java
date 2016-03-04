package com.twilio.sdk.taskrouter;

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

//public class FilterRequirement extends HashMap<String, Boolean> {
//
//    public FilterRequirement(final boolean required) {
//        super();
//        put("required", required);
//    }
//
//    public static FilterRequirement REQUIRED = new FilterRequirement(true);
//    public static FilterRequirement OPTIONAL = new FilterRequirement(false);
//}
