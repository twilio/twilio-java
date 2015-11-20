package com.twilio.sdk;

import com.twilio.sdk.resource.instance.trunking.*;
import com.twilio.sdk.resource.list.trunking.TrunkList;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A client to query against trunking.twilio.com
 */
public class TwilioTrunkingClient extends TwilioClient {

	public static final String DEFAULT_VERSION = "v1";

	public TwilioTrunkingClient(String username, String password) {
		this(username, password, "https://trunking.twilio.com");
	}

	public TwilioTrunkingClient(String username, String password, String endpoint) {
		super(username, password, endpoint);
	}

	/**
	 * Get the trunks for a set of filters
	 *
	 * @param filters The filters
	 * @return trunks matching the filters
	 */
	public TrunkList getTrunks(final Map<String, String> filters) {
		return new TrunkList(this, filters);
	}

	/**
	 * Get the trunks without any filters
	 *
	 * @return The trunk list
	 */
	public TrunkList getTrunks() {
		return new TrunkList(this);
	}

	/**
	 * Get a specific trunk
	 *
	 * @param trunkSid The trunk sid
	 * @return The trunk
	 */
	public Trunk getTrunk(String trunkSid) {
		return new Trunk(this, trunkSid);
	}

	/**
	 * Create a new trunk
	 *
	 * @param params The trunk parameters
	 *
	 * @return The created trunk
	 * @throws TwilioRestException
	 */
	public Trunk createTrunk(Map<String, String> params) throws TwilioRestException {
		return getTrunks().create(params);
	}

	/**
	 * Create a new trunk
	 *
	 * @param params The trunk parameters
	 *
	 * @return The created trunk
	 * @throws TwilioRestException
	 */
	public Trunk createTrunk(List<NameValuePair> params) throws TwilioRestException {
		return getTrunks().create(params);
	}

	/**
	 * Associate a phone number to a trunk
	 *
	 * @param trunkSid The trunk sid
	 * @param params The params
	 * @return The associated phone number
	 * @throws TwilioRestException
	 */
	public PhoneNumber associatePhoneNumber(final String trunkSid,
											final Map<String, String> params) throws TwilioRestException {
		return getTrunk(trunkSid).getPhoneNumbers().create(params);
	}

	/**
	 * Associate a phone number to a trunk
	 *
	 * @param trunkSid The trunk sid
	 * @param params The params
	 * @return The associated phone number
	 * @throws TwilioRestException
	 */
	public PhoneNumber associatePhoneNumber(final String trunkSid,
											final List<NameValuePair> params) throws TwilioRestException {
		return getTrunk(trunkSid).getPhoneNumbers().create(params);
	}

	/**
	 * Create a new origination url
	 *
	 * @param trunkSid The trunk sid
	 * @param params The params
	 * @return The created origination url
	 * @throws TwilioRestException
	 */
	public OriginationUrl createOriginationUrl(final String trunkSid,
											   final Map<String, String> params) throws TwilioRestException {
		return getTrunk(trunkSid).getOriginationUrls().create(params);
	}

	/**
	 * Create a new origination url
	 *
	 * @param trunkSid The trunk sid
	 * @param params The params
	 * @return The created origination url
	 * @throws TwilioRestException
	 */
	public OriginationUrl createOriginationUrl(final String trunkSid,
											   final List<NameValuePair> params) throws TwilioRestException {
		return getTrunk(trunkSid).getOriginationUrls().create(params);
	}

	/**
	 * Associate credential list to a trunk
	 *
	 * @param trunkSid The trunk sid
	 * @param params The params
	 * @return The associated CredentialList
	 * @throws TwilioRestException
	 */
	public CredentialList associateCredentialList(final String trunkSid,
												  final Map<String, String> params) throws TwilioRestException {
		return getTrunk(trunkSid).getCredentialLists().create(params);
	}

	/**
	 * Associate credential list to a trunk
	 *
	 * @param trunkSid The trunk sid
	 * @param params The params
	 * @return The associated CredentialList
	 * @throws TwilioRestException
	 */
	public CredentialList associateCredentialList(final String trunkSid,
												  final List<NameValuePair> params) throws TwilioRestException {
		return getTrunk(trunkSid).getCredentialLists().create(params);
	}

	/**
	 * Associate ip access control list to a trunk
	 *
	 * @param trunkSid The trunk sid
	 * @param params The params
	 * @return The associated ip access control list
	 * @throws TwilioRestException
	 */
	public IpAccessControlList associateIpAccessControlList(final String trunkSid,
															final Map<String, String> params) throws TwilioRestException {
		return getTrunk(trunkSid).getIpAccessControlLists().create(params);
	}

	/**
	 * Associate ip access control list to a trunk
	 *
	 * @param trunkSid The trunk sid
	 * @param params The params
	 * @return The associated ip access control list
	 * @throws TwilioRestException
	 */
	public IpAccessControlList associateIpAccessControlListt(final String trunkSid,
															 final List<NameValuePair> params) throws TwilioRestException {
		return getTrunk(trunkSid).getIpAccessControlLists().create(params);
	}
}
