package com.twilio.sdk.resource.factory;


import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Address;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating Address objects.
 *
 * Required parameters to create an Address:
 * - CustomerName: You or your customer's business name
 * - Street: The number and street for the address
 * - City: The city for this address
 * - Region: The state or region
 * - PostalCode: The postal code or zip code
 * - IsoCountry: The country, in ISO-3166-1 alpha-2 (two-character) format, e.g. 'CA' for Canada
 * Optional parameters:
 * - FriendlyName: A user-defined name for this Address; 64 characters maximum
 */
public interface AddressFactory {
	public Address create(Map<String, String> params) throws TwilioRestException;
	public Address create(List<NameValuePair> params) throws TwilioRestException;
}
