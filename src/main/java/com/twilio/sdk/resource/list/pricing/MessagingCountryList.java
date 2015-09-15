package com.twilio.sdk.resource.list.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.pricing.MessagingCountry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jniu on 9/14/15.
 */
public class MessagingCountryList extends NextGenListResource<MessagingCountry, TwilioPricingClient> {

    private static final String RESOURCE_LOCATION = "/" + TwilioPricingClient.DEFAULT_VERSION + "/Messaging/Countries";

    public MessagingCountryList(final TwilioPricingClient client) {
        this(client, new HashMap<String, String>());
    }

    public MessagingCountryList(final TwilioPricingClient client, final Map<String, String> filters) {
        super(client, filters);
        this.setRequestAccountSid(client.getAccountSid());
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
