package com.twilio.sdk.resource.instance.trunking;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * Represents a phone number associated to a trunk
 *
 * @see <a href="https://www.twilio.com/docs/sip-trunking/rest/phone-numbers">Phone Numbers</a>
 */
public class PhoneNumber extends NextGenInstanceResource<TwilioTrunkingClient> {

	/**
	 * Initialize phone number
	 *
	 * @param client A TwilioTrunkingClient
	 * @param trunkSid The trunk sid
	 * @param phoneNumberSid The phone number sid
	 */
	public PhoneNumber(final TwilioTrunkingClient client,
					   final String trunkSid,
					   final String phoneNumberSid) {
		super(client);
		if (StringUtils.isEmpty(trunkSid)) {
			throw new IllegalArgumentException("trunkSid cannot be null");
		}
		if (StringUtils.isEmpty(phoneNumberSid)) {
			throw new IllegalArgumentException("phoneNumberSid cannot be null");
		}

		setProperty("trunk_sid", trunkSid);
		setProperty(SID_PROPERTY, phoneNumberSid);
	}

	/**
	 * Initialize phone number with data
	 *
	 * @param client A TwilioTrunkingClient
	 * @param properties The data for this phone number instance
	 */
	public PhoneNumber(final TwilioTrunkingClient client,
					   final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * This is a set of boolean properties that indicate whether a phone number
	 * can receive calls or messages.
	 * Possible capabilities are Voice, SMS, and MMS with each having a
	 * value of either true or false.
	 */
	public class Capabilities {
		private final Map<String, Object> data;

		/**
		 * Initialize capability with data
		 *
		 * @param data The capability data
		 */
		public Capabilities(final Map<String, Object> data) {
			this.data = data;
		}

		/**
		 * True iff voice is enabled
		 *
		 * @return True iff voice is enabled
		 */
		public Boolean isVoice() {
			return (Boolean) data.get("voice");
		}

		/**
		 * True iff sms is enabled
		 *
		 * @return True iff sms is enabled
		 */
		public Boolean isSms() {
			return (Boolean) data.get("sms");
		}

		/**
		 * True iff mms is enabled
		 *
		 * @return True iff mms is enabled
		 */
		public Boolean isMms() {
			return (Boolean) data.get("mms");
		}
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
	 * A 34 character string that uniquely identifies the Phone Number in this Twilio Trunk.
	 *
	 * @return The phone number sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The unique ID of the Account that owns this Phone Number
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty(ACCOUNT_SID_PROPERTY);
	}

	/**
	 * The unique ID of the Trunk that is associated to this phone number.
	 *
	 * @return The trunk sid
	 */
	public String getTrunkSid() {
		return getProperty("trunk_sid");
	}

	/**
	 * A human readable descriptive text, up to 64 characters long.
	 *
	 * @return The friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * The SIP address you want Twilio to route your Origination calls to.
	 * This must be a sip: schema.
	 *
	 * @return The sip url
	 */
	public String getSipUrl() {
		return getProperty("sip_url");
	}

	/**
	 * The date this Origination Url was created.
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date this Origination Url was updated.
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * The absolute URL for this resource
	 *
	 * @return The absolute URL
	 */
	public String getUrl() {
		return getProperty("url");
	}

	/**
	 * The incoming phone number. e.g., +16175551212 (E.164 format)
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return getProperty("phone_number");
	}

	/**
	 * Look up the caller's caller-ID name from the CNAM database ($0.01 per look up).
	 * Either true or false.
	 *
	 * @return The voice caller id lookup
	 */
	public Boolean isVoiceCallerIdLookup() {
		return (Boolean) getObject("voice_caller_id_lookup");
	}

	/**
	 * The URL Twilio will request when this phone number receives a call.
	 * The VoiceURL will no longer be used if a VoiceApplicationSid or a TrunkSid is set.
	 *
	 * @return The voice url
	 */
	public String getVoiceUrl() {
		return getProperty("voice_url");
	}

	/**
	 * The HTTP method Twilio will use when requesting the above Url.
	 * Either GET or POST.
	 *
	 * @return The voice method
	 */
	public String getVoiceMethod() {
		return getProperty("voice_method");
	}

	/**
	 * The URL that Twilio will request if an error occurs
	 * retrieving or executing the TwiML requested by Url.
	 *
	 * @return The voice fallback url
	 */
	public String getVoiceFallbackUrl() {
		return getProperty("voice_fallback_url");
	}

	/**
	 * The HTTP method Twilio will use when requesting the VoiceFallbackUrl.
	 * Either GET or POST.
	 *
	 * @return The voice fallback method
	 */
	public String getVoiceFallbackMethod() {
		return getProperty("voice_fallback_method");
	}

	/**
	 * The URL that Twilio will request to pass status parameters
	 * (such as call ended) to your application.
	 *
	 * @return The status callback url
	 */
	public String getStatusCallback() {
		return getProperty("status_callback");
	}

	/**
	 * The HTTP method Twilio will use to make requests to the StatusCallback URL.
	 * Either GET or POST.
	 *
	 * @return The status callback method
	 */
	public String getStatusCallbackMethod() {
		return getProperty("status_callback_method");
	}

	/**
	 * The 34 character sid of the application Twilio should use to handle phone calls
	 * to this number. If a VoiceApplicationSid is present, Twilio will ignore
	 * all of the voice urls above and use those set on the application.
	 * Setting a VoiceApplicationSid will automatically delete your TrunkSid and vice versa.
	 *
	 * @return the voice application sid
	 */
	public String getVoiceApplicationSid() {
		return this.getProperty("voice_application_sid");
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
	public Boolean isBeta() {
		return (Boolean) getObject("beta");
	}

	/**
	 * Return capabilities associated to this phone number
	 *
	 * @return The capability for this number
	 */
	@SuppressWarnings("unchecked")
	public Capabilities getCapabilities() {
		return new Capabilities((Map<String, Object>) getObject("capabilities"));
	}

	/**
	 * Remove the associated phone number
	 *
	 * @return True iff the delete was successful
	 * @throws TwilioRestException
	 */
	@SuppressWarnings("unchecked")
	public boolean delete() throws TwilioRestException {
		final TwilioRestResponse response = this.getClient()
				.safeRequest(this.getResourceLocation(), "DELETE", (Map) null);
		return !response.isError();
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTrunkingClient.DEFAULT_VERSION
				+ "/Trunks/" + getTrunkSid()
				+ "/PhoneNumbers" + getSid();
	}
}
