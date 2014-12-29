package com.twilio.sdk.resource.list.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.pricing.VoiceCountry;

import java.util.Map;

public class VoiceCountryList extends ListResource<VoiceCountry, TwilioPricingClient> {

    public VoiceCountryList(final TwilioPricingClient client) {
        super(client);
    }

    public VoiceCountryList(final TwilioPricingClient client, final Map<String, String> filters) {
        super(client, filters);
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioPricingClient.DEFAULT_VERSION + "/Voice/Countries";
    }

    @Override
    protected String getListKey() {
        return "countries";
    }

    @Override
    protected VoiceCountry makeNew(final TwilioPricingClient client, final Map<String, Object> properties) {
        return new VoiceCountry(client, properties);
    }
}
