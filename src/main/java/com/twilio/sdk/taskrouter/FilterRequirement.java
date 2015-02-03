package com.twilio.sdk.taskrouter;

import java.util.HashMap;

public class FilterRequirement extends HashMap<String, Boolean> {

	public FilterRequirement(final boolean required) {
		super();
		put("required", required);
	}

	public static FilterRequirement REQUIRED = new FilterRequirement(true);
	public static FilterRequirement OPTIONAL = new FilterRequirement(false);
}
