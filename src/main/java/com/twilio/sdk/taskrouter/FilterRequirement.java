package com.twilio.sdk.taskrouter;

public enum FilterRequirement {
	REQUIRED,
	OPTIONAL;

	public String toString() {
		return this.name().toLowerCase();
	}

}
