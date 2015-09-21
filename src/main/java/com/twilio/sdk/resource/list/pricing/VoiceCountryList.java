package com.twilio.sdk.resource.list.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.pricing.VoiceCountry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A list of VoiceCountry objects where Twilio Voice services are available.
 *
 * The returned VoiceCountry objects will not have pricing information populated.
 * To retrieve pricing information for a specific country, request it as follows:
 *
 * <code>
 *     TwilioPricingClient client = new TwilioPricingClient("ACCOUNT SID", "AUTH TOKEN");
 *     // Retrieve country-specific pricing info
 *     VoiceCountry country = client.getVoiceCountry("US");
 *     // Print inbound call prices by type
 *     for (VoiceCountry.InboundCallPrice p : country.getInboundCallPrices()) {
 *         System.out.println(p.getNumberType.toString() + ": " + p.getBasePrice.toString());
 *     }
 * </code>
 */
public class VoiceCountryList extends NextGenListResource<VoiceCountry, TwilioPricingClient> {

    private static final String RESOURCE_LOCATION = "/" + TwilioPricingClient.DEFAULT_VERSION + "/Voice/Countries";

    public VoiceCountryList(final TwilioPricingClient client) {
        this(client, null);
    }

    public VoiceCountryList(final TwilioPricingClient client, final Map<String, String> filters) {
        super(client, filters);
    }

    @Override
    protected String getResourceLocation() {
        return RESOURCE_LOCATION;
    }

    @Override
    protected VoiceCountry makeNew(final TwilioPricingClient client, final Map<String, Object> properties) {
        return new VoiceCountry(client, properties);
    }
}
