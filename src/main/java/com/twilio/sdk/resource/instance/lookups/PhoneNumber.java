package com.twilio.sdk.resource.instance.lookups;

import com.twilio.sdk.LookupsClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Map;

public class PhoneNumber extends NextGenInstanceResource<LookupsClient> {

	public PhoneNumber(final LookupsClient client) {
		super(client);
	}

	public PhoneNumber(final LookupsClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	public PhoneNumber(final LookupsClient client, final String number) {
		super(client);
		setProperty("phone_number", number);
	}

	public PhoneNumber(final LookupsClient client, final String number, final Map<String, String> filters) {
		super(client);
		setProperty("phone_number", number);
		this.filters = filters;
	}

	/**
	 * @return The phone number, in E.164 format (e.g. "+14158675309")
	 */
	public String getPhoneNumber() {
		return getProperty("phone_number");
	}

	/**
	 * @return The ISO 3166-1 alpha-2 country code for this phone number's country.
	 */
	public String getCountryCode() {
		return getProperty("country_code");
	}

	/**
	 * @return The phone number in localized format, e.g. "(415) 867-5309"
	 */
	public String getFormattedNumber() {
		return getProperty("national_format");
	}

	/**
	 * The country code for the mobile carrier, or null.
	 * Only available if carrier information was requested and the number is
	 * a mobile number.
	 * @return Mobile carrier country code
	 */
	public String getMobileCountryCode() {
		return (String) getCarrierProperty("mobile_country_code");
	}

	/**
	 * The mobile carrier's network code, or null.
	 * Only populated if carrier information was requested and the number is a mobile number.
	 * @return Mobile carrier network code
	 */
	public String getMobileNetworkCode() {
		return (String) getCarrierProperty("mobile_network_code");
	}

	/**
	 * Only available if carrier information was requested.
	 * @return The carrier name
	 */
	public String getCarrierName() {
		return (String) getCarrierProperty("name");
	}

	/**
	 * @return Number type, or null if carrier information wasn't requested or lookup failed
	 */
	public Type getType() {
		String t = (String) getCarrierProperty("type");
		if (t != null) {
			return Type.valueOf(t.toUpperCase());
		}
		return null;
	}

	/**
	 * @return Error code, if carrier lookup failed
	 */
	public Integer getErrorCode() {
		return (Integer) getCarrierProperty("error_code");
	}

	/**
	 * @return This resource's URL
	 */
	public String getUrl() {
		return getProperty("url");
	}

	private Object getCarrierProperty(String name) {
		Map<String, Object> carrierProperties = (Map<String, Object>) getObject("carrier");
		if (carrierProperties != null) {
			return carrierProperties.get(name);
		}
		return null;
	}

	public String getResourceLocation() {
		return "/" + LookupsClient.DEFAULT_VERSION + "/PhoneNumbers/" + getPhoneNumber();
	}

	public enum Type {
		LANDLINE, MOBILE, VOIP;
	}
}
