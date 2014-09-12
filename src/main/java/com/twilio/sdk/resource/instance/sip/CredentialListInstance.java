package com.twilio.sdk.resource.instance.sip;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.factory.sip.CredentialFactory;
import com.twilio.sdk.resource.list.sip.CredentialList;

import java.util.Date;
import java.util.Map;

/**
 * This class is the instance resource corresponding to /CredentialLists/CLXXX.
 *
 * NOTE: The 'Instance' suffix in the name was added to avoid a name clash with the list resource
 * that corresponds to /Credentials. This unfortunate quirk is caused by this library's convention of
 * appending 'List' to instance resources to form their list counterpart.
 */
public class CredentialListInstance extends InstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new CredentialListInstance.
	 *
	 * @param client the client
	 */
	public CredentialListInstance(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new CredentialListInstance.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public CredentialListInstance(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for a CredentialListInstance can not be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new CredentialListInstance.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public CredentialListInstance(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
		protected String getResourceLocation() {
			return "/" + TwilioRestClient.DEFAULT_VERSION
				+ "/Accounts/" + this.getRequestAccountSid()
				+ "/SIP/CredentialLists/" + this.getSid()
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
		return getDateProperty("date_created");
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return getDateProperty("date_updated");
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
	 * Gets the realm
	 *
	 * @return the realm
	 */
	public String getRealm() {
		return this.getProperty("realm");
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
	 * Gets the credentials from the credential list
	 *
	 * @return the credentials
	 */
	public CredentialList getCredentials() {
		CredentialList credentials = new CredentialList(this.getClient(), this.getSid());
		credentials.setRequestAccountSid(this.getRequestAccountSid());
		return credentials;
	}

	/**
	 * Gets the credentials from the credential list
	 *
	 * @return the credentials
	 */
	public Credential getCredential(String credentialSid) {
		Credential credential = new Credential(this.getClient(), this.getSid(), credentialSid);
		credential.setRequestAccountSid(this.getRequestAccountSid());
		return credential;
	}

	/**
	 * Gets a CredentialFactory which creates credentials
	 *
	 * @return the CredentialFactory
	 */
	public CredentialFactory getCredentialFactory() {
		return this.getCredentials();
	}

	/**
	 * Delete this {@link CredentialListInstance}.
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
