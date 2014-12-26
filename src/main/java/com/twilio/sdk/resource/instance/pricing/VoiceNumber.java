package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.InstanceResource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VoiceNumber extends InstanceResource<TwilioPricingClient> {

    public VoiceNumber(final TwilioPricingClient client) {
        super(client);
    }

    public VoiceNumber(final TwilioPricingClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    public String getNumber() {
        return getProperty("number");
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

    public OutboundCallPrice getOutboundCallPrice() {
        Map<String, String> prices = (Map<String, String>) getObject("outbound_call_price");
        return new OutboundCallPrice(new BigDecimal(prices.get("call_base_price")),
                new BigDecimal(prices.get("call_current_price")));
    }

    public InboundCallPrice getInboundCallPrice() {
        Map<String, String> priceInfo = (Map<String, String>) getObject("inbound_call_price");
        if (priceInfo == null) {
            return null;
        }

        return new InboundCallPrice(
                NumberType.valueOf(priceInfo.get("number_type").toUpperCase()),
                new BigDecimal(priceInfo.get("call_base_price")),
                new BigDecimal(priceInfo.get("call_current_price"))
        );
    }

    protected String getResourceLocation() {
        return "/" + TwilioPricingClient.DEFAULT_VERSION + "/Voice/Numbers/" + getNumber();
    }

    public class OutboundCallPrice {
        private final BigDecimal callBasePrice;
        private final BigDecimal callCurrentPrice;

        public OutboundCallPrice(final BigDecimal callBasePrice, final BigDecimal callCurrentPrice) {
            this.callBasePrice = callBasePrice;
            this.callCurrentPrice = callCurrentPrice;
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

            OutboundCallPrice that = (OutboundCallPrice) o;

            if (!callBasePrice.equals(that.callBasePrice)) return false;
            if (!callCurrentPrice.equals(that.callCurrentPrice)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = callBasePrice.hashCode();
            result = 31 * result + callCurrentPrice.hashCode();
            return result;
        }
    }

    public class InboundCallPrice {
        private final NumberType numberType;
        private final BigDecimal callBasePrice;
        private final BigDecimal callCurrentPrice;

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
            if (!numberType.equals(that.numberType)) return false;

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
