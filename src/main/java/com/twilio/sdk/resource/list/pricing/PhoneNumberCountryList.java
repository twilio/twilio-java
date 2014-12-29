package com.twilio.sdk.resource.list.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.pricing.PhoneNumberCountry;

import java.util.Map;

public class PhoneNumberCountryList extends ListResource<PhoneNumberCountry, TwilioPricingClient> {

    public PhoneNumberCountryList(final TwilioPricingClient client) {
        super(client);
    }

    public PhoneNumberCountryList(final TwilioPricingClient client, final Map<String, String> filters) {
        super(client, filters);
    }

    @Override
    protected String getListKey() {
        return "countries";
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
