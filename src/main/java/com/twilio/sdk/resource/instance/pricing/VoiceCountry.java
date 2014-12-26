package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.InstanceResource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VoiceCountry extends InstanceResource<TwilioPricingClient> {

    public VoiceCountry(final TwilioPricingClient client) {
        super(client);
    }

    public VoiceCountry(final TwilioPricingClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    public String getCountry() {
        return getProperty("country");
    }

    public String getIsoCountry() {
        return getProperty("iso_country");
    }

    public String getPriceUnit() {
        return getProperty("price_unit");
    }

    public List<InboundCallPrice> getInboundCallPrices() {
        List<Map<String, String>> priceData = (List<Map<String, String>>) getObject("inbound_call_prices");
        List<InboundCallPrice> prices = new ArrayList<InboundCallPrice>();

        for (Map<String, String> p : priceData) {
            prices.add(new InboundCallPrice(
                    NumberType.valueOf(p.get("number_type").toUpperCase()),
                    new BigDecimal(p.get("call_base_price")),
                    new BigDecimal(p.get("call_current_price")
            )));
        }
        return prices;
    }

    public List<OutboundPrefixPrice> getOutboundPrefixPrices() {
        List<Map<String, Object>> priceData = (List<Map<String, Object>>) getObject("outbound_prefix_prices");
        List<OutboundPrefixPrice> prices = new ArrayList<OutboundPrefixPrice>();

        for (Map<String, Object> p : priceData) {
            prices.add(new OutboundPrefixPrice(
                    (String) p.get("friendly_name"),
                    new BigDecimal((String) p.get("call_base_price")),
                    new BigDecimal((String) p.get("call_current_price")),
                    (List<String>) p.get("prefixes")
            ));
        }
        return prices;
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioPricingClient.DEFAULT_VERSION + "/Voice/Countries/" + getIsoCountry();
    }

    public class OutboundPrefixPrice {
        private final String friendlyName;
        private final BigDecimal callBasePrice;
        private final BigDecimal callCurrentPrice;
        private final List<String> prefixes;

        public OutboundPrefixPrice(final String friendlyName, final BigDecimal callBasePrice, final BigDecimal callCurrentPrice, final List<String> prefixes) {
            this.friendlyName = friendlyName;
            this.callBasePrice = callBasePrice;
            this.callCurrentPrice = callCurrentPrice;
            this.prefixes = prefixes;
        }

        public String getFriendlyName() {
            return friendlyName;
        }

        public BigDecimal getCallBasePrice() {
            return callBasePrice;
        }

        public BigDecimal getCallCurrentPrice() {
            return callCurrentPrice;
        }

        public List<String> getPrefixes() {
            return prefixes;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OutboundPrefixPrice that = (OutboundPrefixPrice) o;

            if (!callBasePrice.equals(that.callBasePrice)) return false;
            if (!callCurrentPrice.equals(that.callCurrentPrice)) return false;
            if (!friendlyName.equals(that.friendlyName)) return false;
            if (!prefixes.equals(that.prefixes)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = friendlyName.hashCode();
            result = 31 * result + callBasePrice.hashCode();
            result = 31 * result + callCurrentPrice.hashCode();
            result = 31 * result + prefixes.hashCode();
            return result;
        }
    }

    public class InboundCallPrice {
        private NumberType numberType;
        private BigDecimal callBasePrice;
        private BigDecimal callCurrentPrice;

        public InboundCallPrice(final NumberType numberType, final BigDecimal callBasePrice, final BigDecimal callCurrentPrice) {
            this.numberType = numberType;
            this.callBasePrice = callBasePrice;
            this.callCurrentPrice = callCurrentPrice;
        }

        public NumberType getNumberType() {
            return numberType;
        }

        public BigDecimal getCallBasePrice() {
            return callBasePrice;
        }

        public BigDecimal getCallCurrentPrice() {
            return callCurrentPrice;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            InboundCallPrice that = (InboundCallPrice) o;

            if (!callBasePrice.equals(that.callBasePrice)) return false;
            if (!callCurrentPrice.equals(that.callCurrentPrice)) return false;
            if (numberType != that.numberType) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = numberType.hashCode();
            result = 31 * result + callBasePrice.hashCode();
            result = 31 * result + callCurrentPrice.hashCode();
            return result;
        }
    }
}
