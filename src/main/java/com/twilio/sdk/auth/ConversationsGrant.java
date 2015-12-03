package com.twilio.sdk.auth;

/**
 * Grant used to access Twilio Conversations
 *
 * For more information see:
 * <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *     https://www.twilio.com/docs/api/rest/access-tokens
 * </a>
 */
public class ConversationsGrant implements Grant {

	public String configurationProfileSid;

	public String getConfigurationProfileSid() {
		return configurationProfileSid;
	}

	public ConversationsGrant setConfigurationProfileSid(String configurationProfileSid) {
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

		public Payload(ConversationsGrant grant) {
			this.configuration_profile_sid = grant.configurationProfileSid;
		}
	}
}
