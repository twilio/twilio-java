package com.twilio.sdk.resource.instance.trunking;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * Represents trunk credential list
 *
 * @see <a href="https://www.twilio.com/docs/sip-trunking/rest/credential-lists">Credential Lists</a>
 */
public class CredentialList extends NextGenInstanceResource<TwilioTrunkingClient> {

	/**
	 * Initialize credential list
	 *
	 * @param client A TwilioTrunkingClient
	 * @param trunkSid The trunk sid
	 * @param sid The credential list sid
	 */
	public CredentialList(final TwilioTrunkingClient client,
						  final String trunkSid, final String sid) {
		super(client);
		if (StringUtils.isEmpty(trunkSid)) {
			throw new IllegalArgumentException("trunkSid cannot be null");
		}
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("sid cannot be null");
		}

		this.setProperty("trunk_sid", trunkSid);
		this.setProperty("sid", sid);
	}

	/**
	 * Initialize credential list with the given data
	 *
	 * @param client A TwilioTrunkingClient
	 * @param properties The data for this credential list
	 */
	public CredentialList(final TwilioTrunkingClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * A 34 character string that uniquely identifies the Origination URL in this Twilio Trunk.
	 *
	 * @return The Trunk sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The unique ID of the Trunk that owns this Credential Lists.
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
	 * The date this Credential List was created.
	 *
	 * @return The created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date this Credential List was updated.
	 *
	 * @return The updated date
	 */
	public Calendar getDateUpdated() {
		return parseCalendar(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * The unique ID of the Account that owns this Credential Lists.
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty(ACCOUNT_SID_PROPERTY);
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
	 * Remove association to trunk
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
				+ "/CredentialLists/" + getSid();
	}
}
