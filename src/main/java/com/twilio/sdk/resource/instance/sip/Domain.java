package com.twilio.sdk.resource.instance.sip;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.factory.sip.CredentialListMappingFactory;
import com.twilio.sdk.resource.factory.sip.IpAccessControlListMappingFactory;
import com.twilio.sdk.resource.list.sip.CredentialListMappingList;
import com.twilio.sdk.resource.list.sip.IpAccessControlListMappingList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class Domain extends InstanceResource {

	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new Domain.
	 *
	 * @param client the client
	 */
	public Domain(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new Domain.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Domain(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for a Domain can not be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new Domain.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Domain(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
		protected String getResourceLocation() {
			return "/" + TwilioRestClient.DEFAULT_VERSION
				+ "/Accounts/" + this.getRequestAccountSid()
				+ "/SIP/Domains/" + this.getSid()
				+ ".json";
		}

	/*
	 * Property getters
	 */

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return this.getProperty(SID_PROPERTY);
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty("date_created"));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty("date_updated"));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return this.getProperty("account_sid");
	}

	/**
	 * Gets the auth type
	 *
	 * @return the auth type
	 */
	public String getAuthType() {
		return this.getProperty("auth_type");
	}

	/**
	 * Gets the domain name
	 *
	 * @return the domain name
	 */
	public String getDomainName() {
		return this.getProperty("domain_name");
	}

	/**
	 * Gets the friendly name
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return this.getProperty("friendly_name");
	}

	/**
	 * Gets the voice fallback method
	 *
	 * @return the voice fallback method
	 */
	public String getVoiceFallbackMethod() {
		return this.getProperty("voice_fallback_method");
	}

	/**
	 * Gets the voice fallback url
	 *
	 * @return the voice fallback url
	 */
	public String getVoiceFallbackUrl() {
		return this.getProperty("voice_fallback_url");
	}

	/**
	 * Gets the voice method
	 *
	 * @return the voice method
	 */
	public String getVoiceMethod() {
		return this.getProperty("voice_method");
	}

	/**
	 * Gets the voice status callback method
	 *
	 * @return the voice status callback method
	 */
	public String getVoiceStatusCallbackMethod() {
		return this.getProperty("voice_status_callback_method");
	}

	/**
	 * Gets the voice status callback url
	 *
	 * @return the voice status callback url
	 */
	public String getVoiceStatusCallbackUrl() {
		return this.getProperty("voice_status_callback_url");
	}

	/**
	 * Gets the voice url
	 *
	 * @return the voice url
	 */
	public String getVoiceUrl() {
		return this.getProperty("voice_url");
	}

	/**
	 * Gets the list of IpAccessControlListMappings associated with this domain.
	 *
	 * @return the IpAccessControlListMappingList
	 */
	public IpAccessControlListMappingList getIpAccessControlListMappings() {
		IpAccessControlListMappingList ipAccessControlListMappingList = new IpAccessControlListMappingList(
				this.getClient(), this.getSid());
		ipAccessControlListMappingList.setRequestAccountSid(this.getRequestAccountSid());
		return ipAccessControlListMappingList;
	}

	/**
	 * Gets the list of IpAccessControlListMappings associated with this domain.
	 *
	 * @return the IpAccessControlListMappingList
	 */
	public IpAccessControlListMapping getIpAccessControlListMapping(String ipAccessControlListMappingSid) {
		IpAccessControlListMapping ipAccessControlListMapping = new IpAccessControlListMapping(
				this.getClient(), this.getSid(), ipAccessControlListMappingSid);
		ipAccessControlListMapping.setRequestAccountSid(this.getRequestAccountSid());
		return ipAccessControlListMapping;
	}

	/**
	 * Gets the CredentialListMappingFactory so you can't create new CredentialListMappings
	 *
	 * @return the CredentialListMappingFactory
	 */
	public IpAccessControlListMappingFactory getIpAccessControlListMappingFactory() {
		return this.getIpAccessControlListMappings();
	}

	/**
	 * Gets the list of CredentialListMappings associated with this domain.
	 *
	 * @return the CredentialListMapping
	 */
	public CredentialListMappingList getCredentialListMappings() {
		CredentialListMappingList credentialListMappingList = new CredentialListMappingList(
				this.getClient(), this.getSid());
		credentialListMappingList.setRequestAccountSid(this.getRequestAccountSid());
		return credentialListMappingList;
	}

	/**
	 * Gets the list of CredentialListMappings associated with this domain.
	 *
	 * @return the CredentialListMapping
	 */
	public CredentialListMapping getCredentialListMapping(String credentialListMappingSid) {
		CredentialListMapping credentialListMapping = new CredentialListMapping(
				this.getClient(), this.getSid(), credentialListMappingSid);
		credentialListMapping.setRequestAccountSid(this.getRequestAccountSid());
		return credentialListMapping;
	}

	/**
	 * Gets the CredentialListMappingFactory so you can create new CredentialListMappings
	 *
	 * @return the CredentialListMappingFactory
	 */
	public CredentialListMappingFactory getCredentialListMappingFactory() {
		return this.getCredentialListMappings();
	}

	/**
	 * Delete this {@link Domain}.
	 * @throws TwilioRestException
	 *             if there is an error in the request
	 * @return true, if successful
	 *
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "DELETE", (Map) null);

		return !response.isError();
	}
}
