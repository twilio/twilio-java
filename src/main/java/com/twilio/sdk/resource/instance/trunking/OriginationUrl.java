package com.twilio.sdk.resource.instance.trunking;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTrunkingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * Represents an Origination Url
 *
 * @see <a href="https://www.twilio.com/docs/sip-trunking/rest/origination-urls">OriginationUrls</a>
 */
public class OriginationUrl extends NextGenInstanceResource<TwilioTrunkingClient> {

	/**
	 * Initialize origination url
	 *
	 * @param client A TwilioTrunkingclient
	 * @param trunkSid The trunk sid
	 * @param sid The sid for this origination url
	 */
	public OriginationUrl(final TwilioTrunkingClient client,
						  final String trunkSid,
						  final String sid) {
		this(client, null);
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
	 * Initialize origination url
	 *
	 * @param client A TwilioTrunkingClient
	 * @param properties The data for this origination url
	 */
	public OriginationUrl(final TwilioTrunkingClient client,
						  final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * A 34 character string that uniquely identifies the Origination URL in this Twilio Trunk.
	 *
	 * @return The origination url sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The unique ID of the Account that owns this Origination URL.
	 *
	 * @return The account sid
	 */
	public String getAccountSid() {
		return getProperty(ACCOUNT_SID_PROPERTY);
	}

	/**
	 * The unique ID of the Trunk that owns this Origination URL.
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
	 * Priority ranks the importance of the URI.
	 * Values range from 0 to 65535, where the lowest number represents the
	 * highest importance. Defaults to 10.
	 *
	 * @return The priority
	 */
	public Integer getPriority() {
		return (Integer) getObject("priority");
	}

	/**
	 * Weight is used to determine the share of load when more than one URI has
	 * the same priority. Its values range from 1 to 65535.
	 * The higher the value, the more load a URI is given. Defaults to 10.
	 *
	 * @return The weight
	 */
	public Integer getWeight() {
		return (Integer) getObject("weight");
	}

	/**
	 * A boolean value indicating whether the URL is enabled or disabled.
	 * Defaults to true.
	 *
	 * @return The enabled flag
	 */
	public Boolean isEnabled() {
		return (Boolean) getObject("enabled");
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
	 * Delete the origination url
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
				+ "/OriginationUrls/" + getSid();
	}
}
