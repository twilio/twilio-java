package com.twilio.sdk.ivr;

import java.util.Map;

public abstract class Action {
    public static final String CONTEXT_PREFIX = "__tw_ctx.";
    public abstract String execute(Map<String, String> context);
}
