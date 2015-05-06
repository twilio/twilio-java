package com.twilio.sdk.resource.list.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.pricing.PhoneNumberCountry;

import java.util.Map;

/**
 * A list of PhoneNumberCountry objects where Twilio Phone Numbers are available.
 *
 * The returned PhoneNumberCountry objects will not have pricing information populated.
 * To retrieve pricing information for a specific country, request it as follows:
 *
 * <code>
 *     TwilioPricingClient client = new TwilioPricingClient("ACCOUNT SID", "AUTH TOKEN");
 *     // Retrieve country-specific pricing info
 *     PhoneNumberCountry country = client.getPhoneNumberCountry("US");
 *     // Print number prices by type
 *     for (PhoneNumberCountry.NumberPrice p : country.getPhoneNumberPrices()) {
 *         System.out.println(p.getNumberType().toString() + ": " + p.getBasePrice().toString());
 *     }
 * </code>
 */
public class PhoneNumberCountryList extends NextGenListResource<PhoneNumberCountry, TwilioPricingClient> {

    public PhoneNumberCountryList(final TwilioPricingClient client) {
        super(client);
    }

    public PhoneNumberCountryList(final TwilioPricingClient client, final Map<String, String> filters) {
        super(client, filters);
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioPricingClient.DEFAULT_VERSION + "/PhoneNumbers/Countries";
    }

    @Override
    protected PhoneNumberCountry makeNew(final TwilioPricingClient client, final Map<String, Object> properties) {
        return new PhoneNumberCountry(client, properties);
    }
}
