package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Pricing information for Twilio Voice services in a specific country.
 *
 * For more information, see <a href="FIXME">the Twilio Pricing API documentation</a>.
 */
public class VoiceCountry extends NextGenInstanceResource<TwilioPricingClient> {

    public VoiceCountry(final TwilioPricingClient client) {
        super(client);
    }

    public VoiceCountry(final TwilioPricingClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    public VoiceCountry(final TwilioPricingClient client, final String isoCountry) {
        super(client);
        if (isoCountry == null || "".equals(isoCountry)) {
            throw new IllegalArgumentException("The isoCountry for a VoiceCountry cannot be null");
        }
        setProperty("iso_country", isoCountry);
    }

    /**
     * Get the name of the country this pricing information applies to.
     * @return the country name
     */
    public String getCountry() {
        return getProperty("country");
    }

    /**
     * Get an abbreviated identifier for the country this pricing information
     * applies to.
     * @return the ISO 3166-1 alpha-2 country code, e.g. "US" for the United States
     */
    public String getIsoCountry() {
        return getProperty("iso_country");
    }

    /**
     * Get the currency unit for this pricing information.
     * @return A string representing the currency information, e.g. "USD" for US Dollars.
     */
    public String getPriceUnit() {
        return getProperty("price_unit");
    }

    /**
     * Get a list of prices for inbound voice calls in this country,
     * broken out by number type.
     *
     * Twilio Voice inbound call pricing is based on the type of the number
     * dialed.
     * For example, in Estonia, inbound calls to Mobile numbers might cost 0.0075 USD/min,
     * while National numbers could cost 0.0070 USD/min.
     *
     * Each type of number available in the country will have an entry in this list
     * with its inbound pricing information (list price and discounted price).
     *
     * @return List of objects with inbound call pricing information.
     */
    public List<InboundCallPrice> getInboundCallPrices() {
        List<Map<String, Object>> priceData = (List<Map<String, Object>>) getObject("inbound_call_prices");
        List<InboundCallPrice> prices = new ArrayList<InboundCallPrice>();

        for (Map<String, Object> p : priceData) {
            prices.add(new InboundCallPrice(
                    NumberType.valueOf(((String)p.get("number_type")).toUpperCase()),
                    new BigDecimal((String) p.get("base_price")),
                    new BigDecimal((String) p.get("current_price")
            )));
        }
        return prices;
    }

    /**
     * Get a list of prices for outbound voice calls in this country.
     *
     * Twilio Voice pricing is based on the number dialed and is determined
     * by the longest matching prefix of existing price rates.
     *
     * For example, if Twilio's pricing for Estonia contains the following
     * groups:
     *
     * <ul>
     *     <li>+372: 0.033 USD/min</li>
     *     <li>+37240, +37270: 0.465 USD/min</li>
     * </ul>
     *
     * Then calls to numbers starting with +37240 and +37270 both cost
     * 0.465 USD/min, and all other calls in Estonia (matching +372) cost
     * 0.033 USD/min.
     * @return List of objects with outbound call price information.
     */
    public List<OutboundPrefixPrice> getOutboundPrefixPrices() {
        List<Map<String, Object>> priceData = (List<Map<String, Object>>) getObject("outbound_prefix_prices");
        List<OutboundPrefixPrice> prices = new ArrayList<OutboundPrefixPrice>();

        for (Map<String, Object> p : priceData) {
            prices.add(new OutboundPrefixPrice(
                    (String) p.get("friendly_name"),
                    new BigDecimal((String) p.get("base_price")),
                    new BigDecimal((String) p.get("current_price")),
                    (List<String>) p.get("prefix_list")
            ));
        }
        return prices;
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioPricingClient.DEFAULT_VERSION + "/Voice/Countries/" + getIsoCountry();
    }

    /**
     * Represents current prices for outbound Twilio Voice calls to the given
     * list of number prefixes.
     *
     */
    public class OutboundPrefixPrice {
        private final String friendlyName;
        private final BigDecimal basePrice;
        private final BigDecimal currentPrice;
        private final List<String> prefixes;

        public OutboundPrefixPrice(final String friendlyName, final BigDecimal basePrice, final BigDecimal currentPrice, final List<String> prefixes) {
            this.friendlyName = friendlyName;
            this.basePrice = basePrice;
            this.currentPrice = currentPrice;
            this.prefixes = prefixes;
        }

        /**
         * A friendly name for this prefix group.
         * @return String name.
         */
        public String getFriendlyName() {
            return friendlyName;
        }

        /**
         * The price per minute for calls placed to numbers matching this
         * prefix group, before any discounts have been applied.
         * @return Base call price/minute.
         */
        public BigDecimal getBasePrice() {
            return basePrice;
        }

        /**
         * The price per minute for calls placed to numbers matching this
         * prefix group, after any available discounts have been applied.
         * @return Current (discounted) call price/minute.
         */
        public BigDecimal getCurrentPrice() {
            return currentPrice;
        }

        /** Prefixes of phone numbers to which this price applies.
         * For example, if the prefixes are "37240" and "37270",
         * all numbers beginning with +372 40 and +372 70 fall under this
         * pricing.
         * @return List of phone number prefixes for this pricing.
         */
        public List<String> getPrefixes() {
            return prefixes;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OutboundPrefixPrice that = (OutboundPrefixPrice) o;

            if (!basePrice.equals(that.basePrice)) return false;
            if (!currentPrice.equals(that.currentPrice)) return false;
            if (!friendlyName.equals(that.friendlyName)) return false;
            if (!prefixes.equals(that.prefixes)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = friendlyName.hashCode();
            result = 31 * result + basePrice.hashCode();
            result = 31 * result + currentPrice.hashCode();
            result = 31 * result + prefixes.hashCode();
            return result;
        }
    }

    public class InboundCallPrice {
        private NumberType numberType;
        private BigDecimal basePrice;
        private BigDecimal currentPrice;

        public InboundCallPrice(final NumberType numberType, final BigDecimal basePrice, final BigDecimal currentPrice) {
            this.numberType = numberType;
            this.basePrice = basePrice;
            this.currentPrice = currentPrice;
        }

        /**
         * The type of number for which this price applies,
         * e.g. NumberType.MOBILE
         * @return The number type
         */
        public NumberType getNumberType() {
            return numberType;
        }

        /**
         * The price per minute for inbound calls to numbers of this type,
         * before discounts have been applied.
         * @return Base inbound call price/minute.
         */
        public BigDecimal getBasePrice() {
            return basePrice;
        }

        /**
         * The price per minute for inbound calls to numbers of this type,
         * after available discounts have been applied.
         * @return Discounted inbound call price/minute.
         */
        public BigDecimal getCurrentPrice() {
            return currentPrice;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            InboundCallPrice that = (InboundCallPrice) o;

            if (!basePrice.equals(that.basePrice)) return false;
            if (!currentPrice.equals(that.currentPrice)) return false;
            if (numberType != that.numberType) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = numberType.hashCode();
            result = 31 * result + basePrice.hashCode();
            result = 31 * result + currentPrice.hashCode();
            return result;
        }
    }
}
