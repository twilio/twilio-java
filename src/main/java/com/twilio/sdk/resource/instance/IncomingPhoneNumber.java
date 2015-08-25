package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class IncomingPhoneNumber.
 *
 * For more information see <a
 * href="https://www.twilio.com/docs/api/rest/incoming-phone-numbers"
 * >https://www.twilio.com/docs/api/rest/incoming-phone-numbers</a>
 */
public class IncomingPhoneNumber extends InstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new incoming phone number.
	 *
	 * @param client
	 *            the client
	 */
	public IncomingPhoneNumber(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new incoming phone number.
	 *
	 * @param client
	 *            the client
	 * @param sid
	 *            the sid
	 */
	public IncomingPhoneNumber(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
            throw new IllegalStateException("The Sid for an IncomingPhoneNumber can not be null");
        }
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new incoming phone number.
	 *
	 * @param client
	 *            the client
	 * @param properties
	 *            the properties
	 */
	public IncomingPhoneNumber(TwilioRestClient client,
			Map<String, Object> properties) {
		super(client, properties);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/IncomingPhoneNumbers/"
				+ this.getSid() + ".json";
	}

	/**
	 * Deprovision this IncomingPhoneNumber. This will remove it from your
	 * account.
	 *
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
	 * Gets the friendly name.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return this.getProperty("friendly_name");
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
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.getProperty("phone_number");
	}

	/**
	 * Gets the voice application sid.
	 *
	 * @return the voice application sid
	 */
	public String getVoiceApplicationSid() {
		return this.getProperty("voice_application_sid");
	}

	/**
	 * Gets the sms application sid.
	 *
	 * @return the sms application sid
	 */
	public String getSmsApplicationSid() {
		return this.getProperty("sms_application_sid");
	}

	/**
	 * Gets the api version.
	 *
	 * @return the api version
	 */
	public String getApiVersion() {
		return this.getProperty("api_version");
	}

	/**
	 * Gets the voice url.
	 *
	 * @return the voice url
	 */
	public String getVoiceUrl() {
		return this.getProperty("voice_url");
	}

	/**
	 * Gets the voice method.
	 *
	 * @return the voice method
	 */
	public String getVoiceMethod() {
		return this.getProperty("voice_method");
	}

	/**
	 * Gets the voice fallback url.
	 *
	 * @return the voice fallback url
	 */
	public String getVoiceFallbackUrl() {
		return this.getProperty("voice_fallback_url");
	}

	/**
	 * Gets the voice fallback method.
	 *
	 * @return the voice fallback method
	 */
	public String getVoiceFallbackMethod() {
		return this.getProperty("voice_fallback_method");
	}

	/**
	 * Gets the status callback.
	 *
	 * @return the status callback
	 */
	public String getStatusCallback() {
		return this.getProperty("status_callback");
	}

	/**
	 * Gets the status callback method.
	 *
	 * @return the status callback method
	 */
	public String getStatusCallbackMethod() {
		return this.getProperty("status_callback_method");
	}

	/**
	 * Whether this number has caller-ID lookup enabled for incoming voice calls.
	 *
	 * @return Voice caller ID lookup setting
	 */
	public Boolean getVoiceCallerIdLookup() {
		return (Boolean) this.getObject("voice_caller_id_lookup");
	}

	/**
	 * Gets the sms url.
	 *
	 * @return the sms url
	 */
	public String getSmsUrl() {
		return this.getProperty("sms_url");
	}

	/**
	 * Gets the sms method.
	 *
	 * @return the sms method
	 */
	public String getSmsMethod() {
		return this.getProperty("sms_method");
	}

	/**
	 * Gets the sms fallback url.
	 *
	 * @return the sms fallback url
	 */
	public String getSmsFallbackUrl() {
		return this.getProperty("sms_fallback_url");
	}

	/**
	 * Gets the sms fallback method.
	 *
	 * @return the sms fallback method
	 */
	public String getSmsFallbackMethod() {
		return this.getProperty("sms_fallback_method");
	}

	/**
	 * Gets the sms status callback.
	 *
	 * @return the sms status callback
	 */
	public String getSmsStatusCallback() {
		return this.getProperty("sms_status_callback");
	}

	/**
	 * Indicates whether this number requires an Address to be on file with Twilio.
	 * Potential values are "any", "local", "foreign", or "none".
	 *
	 * @return the address requirements
	 */
	public String getAddressRequirements() {
		return this.getProperty("address_requirements");
	}

	/**
	 * Whether this number is new to the Twilio platform.
	 * @return Beta status
	 */
	public boolean getBeta() {
		return (Boolean) getObject("beta");
	}
}
