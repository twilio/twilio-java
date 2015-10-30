package com.twilio.sdk.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ConversationGrant implements Grant {

	public String configuration_profile_sid;

	public String getConfiguration_profile_sid() {
		return configuration_profile_sid;
	}

	public void setConfiguration_profile_sid(String configuration_profile_sid) {
		this.configuration_profile_sid = configuration_profile_sid;
	}

	public String getGrantKey() {
		return "rtc";
	}

	@JsonIgnore
	public Object getPayload() {
		return this;
	}
}
