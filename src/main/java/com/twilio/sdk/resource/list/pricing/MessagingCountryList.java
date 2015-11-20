package com.twilio.sdk.resource.list.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.pricing.MessagingCountry;

import java.util.Map;

/**
 * A list of MessagingCountry objects where Twilio Phone Numbers are available.
 *
 * The returned MessagingCountry objects will not have pricing information populated.
 * To retrieve pricing information for a specific country, request it as follows:
 *
 * <code>
 *     TwilioPricingClient client = new TwilioPricingClient("ACCOUNT SID", "AUTH TOKEN");
 *     // Retrieve country-specific pricing info
 *     MessagingCountry country = client.getMessagingCountry("US");
 *     // Print number prices by type
 *     for (MessagingCountry.MessagingPrice p : country.getInboundSmsPrices()) {
 *         System.out.println(p.getNumberType().toString() + ": " + p.getBasePrice().toString());
 *     }
 * </code>
 */
public class MessagingCountryList extends NextGenListResource<MessagingCountry, TwilioPricingClient> {

    private static final String RESOURCE_LOCATION = "/" + TwilioPricingClient.DEFAULT_VERSION + "/Messaging/Countries";

    public MessagingCountryList(final TwilioPricingClient client) {
        this(client, null);
    }

    public MessagingCountryList(final TwilioPricingClient client, final Map<String, String> filters) {
        super(client, filters);
    }

    @Override
    protected MessagingCountry makeNew(final TwilioPricingClient client, final Map<String, Object> params) {
        return new MessagingCountry(client, params);
    }

    @Override
    protected String getResourceLocation() {
        return RESOURCE_LOCATION;
    }
}
