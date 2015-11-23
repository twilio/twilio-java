package com.twilio.sdk.auth;

/**
 * Grant used to access Twilio IP Messaging
 *
 * For more information see:
 * <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *     https://www.twilio.com/docs/api/rest/access-tokens
 * </a>
 */
public class IpMessagingGrant implements Grant {

	private String serviceSid;
	private String deploymentRoleSid;
	private String pushCredentialSid;
	private String endpointId;

	public String getServiceSid() {
		return serviceSid;
	}

	public IpMessagingGrant setServiceSid(String serviceSid) {
		this.serviceSid = serviceSid;
		return this;
	}


	public String getPushCredentialSid() {
		return pushCredentialSid;
	}

	public IpMessagingGrant setPushCredentialSid(String pushCredentialSid) {
		this.pushCredentialSid = pushCredentialSid;
		return this;
	}

	public String getDeploymentRoleSid() {
		return deploymentRoleSid;
	}

	public IpMessagingGrant setDeploymentRoleSid(String deploymentRoleSid) {
		this.deploymentRoleSid = deploymentRoleSid;
		return this;
	}

	public String getEndpointId() {
		return endpointId;
	}

	public IpMessagingGrant setEndpointId(String endpointId) {
		this.endpointId = endpointId;
		return this;
	}

	public String getGrantKey() {
		return "ip_messaging";
	}

	public Object getPayload() {
		return new Payload(this);
	}

	public class Payload {
		public final String service_sid;
		public final String deployment_role_sid;
		public final String endpoint_id;
		public final String push_credential_sid;

		public Payload(IpMessagingGrant grant) {
			this.service_sid = grant.serviceSid;
			this.deployment_role_sid = grant.deploymentRoleSid;
			this.endpoint_id = grant.endpointId;
			this.push_credential_sid = grant.pushCredentialSid;
		}
	}
}
