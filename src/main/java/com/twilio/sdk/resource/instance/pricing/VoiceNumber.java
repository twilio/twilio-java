package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.InstanceResource;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Pricing information for Twilio Voice calls to a specific number.
 *
 * For more information, see <a href="FIXME">the Twilio REST API documentation</a>.
 */
public class VoiceNumber extends InstanceResource<TwilioPricingClient> {

    public VoiceNumber(final TwilioPricingClient client) {
        super(client);
    }

    public VoiceNumber(final TwilioPricingClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    public VoiceNumber(final TwilioPricingClient client, final String number) {
        super(client);
        if (number == null || "".equals(number)) {
            throw new IllegalArgumentException("'number' cannot be null");
        }
        setProperty("number", number);
    }

    /**
     * The phone number this pricing information applies to, in E.164 format.
     * @return E.164-formatted phone number, e.g. "+15105551234".
     */
    public String getNumber() {
        return getProperty("number");
    }

    /**
     * The name of the country this number is located in.
     * @return Country name.
     */
    public String getCountry() {
        return getProperty("country");
    }

    /**
     * An abbreviated identifier for the country this number is located in.
     * @return ISO 3166-1 alpha-2 country code, e.g. "US".
     */
    public String getIsoCountry() {
        return getProperty("iso_country");
    }

    /**
     * The currency unit for this pricing information.
     * @return A string representing currency type, e.g. "USD".
     */
    public String getPriceUnit() {
        return getProperty("price_unit");
    }

    /**
     * Pricing information for outbound Twilio Voice calls to this phone number.
     *
     * Twilio Voice calls are priced per minute, and the price object will include
     * prices both before and after any discounts available for the requesting account
     * are applied.
     * @return Object containing pricing information for outbound calls to this phone number.
     */
    public OutboundCallPrice getOutboundCallPrice() {
        Map<String, String> prices = (Map<String, String>) getObject("outbound_call_price");
        return new OutboundCallPrice(new BigDecimal(prices.get("call_base_price")),
                new BigDecimal(prices.get("call_current_price")));
    }

    /**
     * Pricing information for inbound Twilio Voice calls to this phone number,
     * if it is a Twilio-hosted number.
     *
     * Twilio Voice calls are priced per minute, and the price object will include
     * prices both before and after any discounts available for the requesting account
     * are applied.
     *
     * If the number is not Twilio-hosted, this will return null.
     *
     * @return Object containing pricing information for inbound calls to this phone number, or null.
     */
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

    /**
     * Represents pricing information for outbound calls to a specific phone number.
     *
     * Price rates are per minute and reflect current Twilio list pricing and any discounts
     * available for the requesting account at the time this object was requested.
     */
    public class OutboundCallPrice {
        private final BigDecimal callBasePrice;
        private final BigDecimal callCurrentPrice;

        public OutboundCallPrice(final BigDecimal callBasePrice, final BigDecimal callCurrentPrice) {
            this.callBasePrice = callBasePrice;
            this.callCurrentPrice = callCurrentPrice;
        }

        /**
         * Price per minute for outbound calls to a specific phone number,
         * before any discounts have been applied.
         * @return Decimal price rate for outbound calls.
         */
        public BigDecimal getCallBasePrice() {
            return callBasePrice;
        }

        /**
         * Price per minute for outbound calls to a specific phone number,
         * after the requesting account's discounts, if any, have been applied.
         * @return Decimal discounted price rate for outbound calls.
         */
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

    /**
     * Represents pricing information for inbound calls to a specific
     * Twilio-hosted phone number.
     *
     * Price rates are per minute and reflect current Twilio list pricing
     * and any discounts available for the requesting account at the time
     * this object was requested.
     */
    public class InboundCallPrice {
        private final NumberType numberType;
        private final BigDecimal callBasePrice;
        private final BigDecimal callCurrentPrice;

        public InboundCallPrice(final NumberType numberType, final BigDecimal callBasePrice, final BigDecimal callCurrentPrice) {
            this.numberType = numberType;
            this.callBasePrice = callBasePrice;
            this.callCurrentPrice = callCurrentPrice;
        }

        /**
         * The type of phone number this price information applies to.
         * @return NumberType for this phone number
         */
        public NumberType getNumberType() {
            return numberType;
        }

        /**
         * Price per minute for inbound calls to a specific Twilio-hosted
         * phone number, before any discounts have been applied.
         * @return Decimal base price rate for inbound calls.
         */
        public BigDecimal getCallBasePrice() {
            return callBasePrice;
        }

        /**
         * Price per minute for outbound calls to a specific Twilio-hosted
         * phone number, after any available discounts have been applied.
         * @return Decimal disconutd price rate for inbound calls.
         */
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
