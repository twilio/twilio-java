package com.twilio.sdk.resource.instance.trunking;

import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import com.twilio.sdk.resource.list.trunking.CredentialListList;
import com.twilio.sdk.resource.list.trunking.IpAccessControlListList;
import com.twilio.sdk.resource.list.trunking.OriginationUrlList;
import com.twilio.sdk.resource.list.trunking.PhoneNumberList;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Represents an Trunk
 *
 * @see <a href="https://www.twilio.com/docs/sip-trunking/rest/trunks">Trunks</a>
 */
public class Trunk extends NextGenInstanceResource<TwilioTrunkingClient> {

	/**
	 * Initialize a Trunk
	 *
	 * @param client A TwilioTrunkingclient
	 */
	public Trunk(final TwilioTrunkingClient client, final String sid) {
		super(client, null);
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("sid cannot be null");
		}
		this.setProperty("sid", sid);
	}

	/**
	 * Initialize a Trunk with properties
	 *
	 * @param client A TwilioTrunkingclient
	 * @param properties The data associated with a Trunk
	 */
	public Trunk(final TwilioTrunkingClient client,
				 final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * The recording settings for this trunk.
	 *
	 * If turned on, all calls going through this trunk will be recorded and the recording
	 * can either start when the call is ringing or when the call is answered.
	 * TwiML from this URL and execute those instructions like any other normal TwiML call.
	 *
	 * @see <a href="https://www.twilio.com/docs/sip-trunking/getting-started#recording">Recording</a>
	 * for more information.
	 */
	public class Recording {
		public static final String TRIM_SILIENCE = "trim-silence";
		public static final String DO_NOT_TRIM = "do-not-trim";

		public static final String DO_NOT_RECORD = "do-no-record";
		public static final String RECORD_FROM_ANSWER = "record-from-answer";
		public static final String RECORD_FROM_RINGING = "record-from-ringing";

		private final Map<String, Object> data;

		public Recording(final Map<String, Object> data) {
			this.data = data;
		}

		/**
		 * The 'trim' attribute lets you specify whether to trim leading and trailing silence
		 * from your audio files. 'trim' defaults to trim-silence, which removes any silence
		 * at the beginning or end of your recording. This may cause the duration of the
		 * recording to be slightly less than the duration of the call.
		 *
		 * Allowed values : trim-silence, do-not-trim
		 *
		 * @return The trim attribute
		 */
		public String getTrim() {
			return String.valueOf(data.get("trim"));
		}

		/**
		 * The mode attribute lets you specify whether recording should be enabled or
		 * when recording should begin.
		 *
		 * Allowed values : do-not-record, record-from-answer, record-from-ringing
		 *
		 * @return The mode attribute
		 */
		public String getMode() {
			return String.valueOf(data.get("mode"));
		}
	}

	/**
	 * Initialize origination urls without a filter
	 *
	 * @return The origination url query
	 */
	public OriginationUrlList getOriginationUrls() {
		return new OriginationUrlList(getClient(), getSid());
	}

	/**
	 * Initialize origination urls with a filter
	 *
	 * @param filters The filters to apply
	 * @return The origination url query
	 */
	public OriginationUrlList getOriginationUrls(final Map<String, String> filters) {
		return new OriginationUrlList(getClient(), getSid(), filters);
	}

	/**
	 * Initialize a single origination url
	 *
	 * @param originationUrlSid The origination url sid
	 * @return The origination url
	 */
	public OriginationUrl getOriginationUrl(final String originationUrlSid) {
		return new OriginationUrl(getClient(), getSid(), originationUrlSid);
	}

	/**
	 * Initialize credential lists with a filter
	 *
	 * @param filters The filters to apply
	 * @return The credential lists query
	 */
	public CredentialListList getCredentialLists(final Map<String, String> filters) {
		return new CredentialListList(getClient(), getSid(), filters);
	}

	/**
	 * Initialize credential lists without a filter
	 *
	 * @return The credential lists query
	 */
	public CredentialListList getCredentialLists() {
		return new CredentialListList(getClient(), getSid());
	}

	/**
	 * Initialize a single credential list
	 *
	 * @param credentialListSid The credential list sid
	 * @return The credential list
	 */
	public CredentialList getCredentialList(final String credentialListSid) {
		return new CredentialList(getClient(), getSid(), credentialListSid);
	}

	/**
	 * Initialize an ip access control lists with a filter
	 *
	 * @param filters The filters to apply
	 * @return The IpAccessControlLists query
	 */
	public IpAccessControlListList getIpAccessControlLists(final Map<String, String> filters) {
		return new IpAccessControlListList(getClient(), getSid(), filters);
	}

	/**
	 * Initialize an ip access control lists without a filter
	 *
	 * @return The IpAccessControlLists query
	 */
	public IpAccessControlListList getIpAccessControlLists() {
		return new IpAccessControlListList(getClient(), getSid());
	}

	/**
	 * Initialize a single ip access control list
	 *
	 * @param ipAccessControlListSid The IpAccessControlList sid
	 * @return The IpAccessControlList instance
	 */
	public IpAccessControlList getIpAccessControlList(final String ipAccessControlListSid) {
		return new IpAccessControlList(getClient(), getSid(), ipAccessControlListSid);
	}

	/**
	 * Initialize phone numbers query
	 *
	 * @return The phone numbers query
	 */
	public PhoneNumberList getPhoneNumbers() {
		return new PhoneNumberList(getClient(), getSid());
	}

	/**
	 * Initialize phone numbers query with filters
	 *
	 * @param filters The filters to apply
	 * @return The phone numbers query
	 */
	public PhoneNumberList getPhoneNumbers(final Map<String, String> filters) {
		return new PhoneNumberList(getClient(), getSid(), filters);
	}

	/**
	 * Initialize a query for a single phone number
	 *
	 * @param phoneNumberSid The phone number sid
	 * @return The phone nubmer instance
	 */
	public PhoneNumber getPhoneNumber(final String phoneNumberSid) {
		return new PhoneNumber(getClient(), getSid(), phoneNumberSid);
	}

	/**
	 * A 34 character string that uniquely identifies the SIP Trunk in Twilio.
	 *
	 * @return The Trunk sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The unique ID of the Account that owns this Trunk.
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty(ACCOUNT_SID_PROPERTY);
	}

	/**
	 * The unique address you reserve on Twilio to which you route your SIP traffic.
	 * Domain names can contain letters, digits, and - and must always end with
	 * pstn.twilio.com.
	 *
	 * @see <a href="https://www.twilio.com/docs/sip-trunking/getting-started#termination">Termination Settings</a>
	 * for more information.
	 * @return The domain name
	 */
	public String getDomainName() {
		return getProperty("domain_name");
	}

	/**
	 * The HTTP method Twilio will use when requesting the DisasterRecoveryUrl.
	 * Either GET or POST.
	 *
	 * @return The disaster recovery method
	 */
	public String getDisasterRecoveryMethod() {
		return getProperty("disaster_recovery_method");
	}

	/**
	 * The HTTP URL that Twilio will request if an error occurs while sending SIP
	 * traffic towards your configured Origination URL. Twilio will retrieve TwiML
	 * from this URL and execute those instructions like any other normal TwiML call.
	 *
	 * @see <a href="https://www.twilio.com/docs/sip-trunking/getting-started#disaster-recovery">Disaster Recovery</a>
	 * for more information.
	 * @return The disaster recovery url
	 */
	public String getDisasterRecoveryUrl() {
		return getProperty("disaster_recovery_url");
	}

	/**
	 * A human-readable name for the Trunk.
	 *
	 * @return The friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * The Secure Trunking settings for this trunk.
	 * If turned on, all calls going through this trunk will be secure using SRTP for
	 * media and TLS for signalling. If turned off, then RTP will be used for media.
	 * TwiML from this URL and execute those instructions like any other normal TwiML call.
	 *
	 * @see <a href="https://www.twilio.com/docs/sip-trunking/getting-started#securetrunking">>Secure Trunking</a>
	 * for more information.
	 * @return The secure flag
	 */
	public Boolean isSecure() {
		return (Boolean) getObject("secure");
	}

	/**
	 * The recording settings for this trunk.
	 * If turned on, all calls going through this trunk will be recorded and the
	 * recording can either start when the call is ringing or when the call is answered.
	 * TwiML from this URL and execute those instructions like any other normal TwiML call.
	 *
	 * @see <a href="https://www.twilio.com/docs/sip-trunking/getting-started#recording">Recording</a>
	 * for more information.
	 * @return The recording settings
	 */
	@SuppressWarnings("unchecked")
	public Recording getRecording() {
		return new Recording((Map<String, Object>)getObject("recording"));
	}

	/**
	 * The types of authentication you have mapped to your domain.
	 * The possible values are IP_ACL and CREDENTIAL_LIST.
	 * If you have both setup for your domain, both will be returned comma delimited.
	 * If you do not have one setup for your domain, it will not be able to receive
	 * any traffic.
	 *
	 * @return The auth type
	 */
	public String getAuthType() {
		return getProperty("auth_type");
	}

	/**
	 * The types of authentication you have mapped to your domain.
	 * The possible values are IP_ACL and CREDENTIAL_LIST.
	 * If you do not have one setup for your domain, it will not be able to receive
	 * any traffic.
	 *
	 * @return The auth type
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAuthTypeSet() {
		return (List<String>) getObject("auth_type_set");
	}

	/**
	 * The date this Trunk was created.
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date this Trunk was updated.
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * The absolute URL for this resource
	 *
	 * @return The absolute url
	 */
	public String getUrl() {
		return getProperty("url");
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTrunkingClient.DEFAULT_VERSION + "/Trunks/" + getSid();
	}

}
