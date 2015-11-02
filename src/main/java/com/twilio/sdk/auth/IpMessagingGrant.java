package com.twilio.sdk.auth;

public class IpMessagingGrant implements Grant {

	private String serviceSid;
	private String roleSid;
	private String credentialSid;
	private String endpointId;

	public String getServiceSid() {
		return serviceSid;
	}

	public IpMessagingGrant setServiceSid(String serviceSid) {
		this.serviceSid = serviceSid;
		return this;
	}

	public String getRoleSid() {
		return roleSid;
	}

	public IpMessagingGrant setRoleSid(String roleSid) {
		this.roleSid = roleSid;
		return this;
	}

	public String getCredentialSid() {
		return credentialSid;
	}

	public IpMessagingGrant setCredentialSid(String credentialSid) {
		this.credentialSid = credentialSid;
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
		public final String instance_sid;
		public final String deployment_role_sid;
		public final String endpoint_id;
		public final String push_credential_sid;

		public Payload(IpMessagingGrant grant) {
			this.instance_sid = grant.serviceSid;
			this.deployment_role_sid = grant.roleSid;
			this.endpoint_id = grant.endpointId;
			this.push_credential_sid = grant.credentialSid;
		}
	}
}
