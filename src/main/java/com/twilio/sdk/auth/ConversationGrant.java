package com.twilio.sdk.auth;

public class ConversationGrant implements Grant {

	public String configurationProfileSid;

	public String getConfigurationProfileSid() {
		return configurationProfileSid;
	}

	public ConversationGrant setConfigurationProfileSid(String configurationProfileSid) {
		this.configurationProfileSid = configurationProfileSid;
		return this;
	}

	public String getGrantKey() {
		return "rtc";
	}

	public Object getPayload() {
		return new Payload(this);
	}

	public class Payload {
		public final String configuration_profile_sid;

		public Payload(ConversationGrant grant) {
			this.configuration_profile_sid = grant.configurationProfileSid;
		}
	}
}
