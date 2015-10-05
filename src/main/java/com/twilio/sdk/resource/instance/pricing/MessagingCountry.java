package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pricing information for Messaging in a specific country
 * <p/>
 * For more information see <a href="https://www.twilio.com/docs/api/rest/pricing">the Twilio REST API documentation.</a>
 */
public class MessagingCountry extends NextGenInstanceResource<TwilioPricingClient> {

    public MessagingCountry(final TwilioPricingClient client) {
        this(client, new HashMap<String, Object>());
    }

    public MessagingCountry(final TwilioPricingClient client, final String isoCountry) {
        super(client);
        if (StringUtils.isBlank(isoCountry)) {
            throw new IllegalArgumentException("The isoCountry for a MessagingCountry cannot be blank");
        }
        setProperty("iso_country", isoCountry);
    }

    public MessagingCountry(final TwilioPricingClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioPricingClient.DEFAULT_VERSION + "/Messaging/Countries/" + getIsoCountry();
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
     * Get a list of prices for outbound SNS in this country,
     * broken out by number type
     *
     * @return List of SMS prices with outbound pricing info
     */
    public List<OutboundSmsPrice> getOutboundSmsPrices() {
        List<Map<String, Object>> priceData = getCastedObject("outbound_sms_prices");

        List<OutboundSmsPrice> details = new ArrayList<OutboundSmsPrice>();
        for (Map<String, Object> data : priceData) {
            details.add(new OutboundSmsPrice(
                data.get("mcc").toString(),
                data.get("mnc").toString(),
                data.get("carrier").toString(),
                getMessagingPrices((List<Map<String, String>>) data.get("prices"))
            ));
        }

        return details;
    }

    /**
     * Get a list of prices for inbound SNS in this country,
     * broken out by number type
     *
     * @return List of SMS prices with inbound pricing info
     */
    public List<MessagingPrice> getInboundSmsPrices() {
        List<Map<String, String>> priceData = getCastedObject("inbound_sms_prices");
        return getMessagingPrices(priceData);
    }

    private List<MessagingPrice> getMessagingPrices(List<Map<String, String>> priceData) {
        List<MessagingPrice> prices = new ArrayList<MessagingPrice>();

        for (Map<String, String> p : priceData) {
            prices.add(new MessagingPrice(
                    NumberType.valueOf(p.get("number_type").toUpperCase()),
                    new BigDecimal(p.get("base_price")),
                    new BigDecimal(p.get("current_price"))));
        }
        return prices;
    }

    /**
     * Represents current prices for outbound Twilio SMS to the
     * given list of number prefixes.
     */
    public class OutboundSmsPrice {
        private final String mcc;
        private final String mnc;
        private final String carrier;
        private final List<MessagingPrice> prices;

        public OutboundSmsPrice(final String mcc, final String mnc, final String carrier, final List<MessagingPrice> prices) {
            this.mcc = mcc;
            this.mnc = mnc;
            this.carrier = carrier;
            this.prices = prices;
        }

        /**
         * @return the mobile country code
         */
        public String getMcc() {
            return mcc;
        }

        /**
         * @return the mobile network code
         */
        public String getMnc() {
            return mnc;
        }

        /**
         * @return the mobile carrier name
         */
        public String getCarrier() {
            return carrier;
        }

        /**
         * @return the prices for this carrier
         */
        public List<MessagingPrice> getPrices() {
            return prices;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                .append(mcc)
                .append(mnc)
                .append(carrier)
                .append(prices)
                .toHashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }

            OutboundSmsPrice o = (OutboundSmsPrice) obj;
            return new EqualsBuilder()
                .append(this.mcc, o.mcc)
                .append(this.mnc, o.mnc)
                .append(this.carrier, o.carrier)
                .append(this.prices, o.prices)
                .isEquals();
        }
    }

    public class MessagingPrice {
        private final NumberType numberType;
        private final BigDecimal basePrice;
        private final BigDecimal currentPrice;

        public MessagingPrice(final NumberType numberType, final BigDecimal basePrice, final BigDecimal currentPrice) {
            this.numberType = numberType;
            this.basePrice = basePrice;
            this.currentPrice = currentPrice;
        }

        /**
         * The type of number for which this price applies,
         * e.g. NumberType.MOBILE
         *
         * @return The number type
         */
        public NumberType getNumberType() {
            return numberType;
        }

        /**
         * The price per minute for inbound calls to numbers of this type,
         * before discounts have been applied.
         *
         * @return Base inbound call price/minute.
         */
        public BigDecimal getBasePrice() {
            return basePrice;
        }

        /**
         * The price per minute for inbound calls to numbers of this type,
         * after available discounts have been applied.
         *
         * @return Discounted inbound call price/minute.
         */
        public BigDecimal getCurrentPrice() {
            return currentPrice;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            MessagingPrice that = (MessagingPrice) o;
            return new EqualsBuilder()
                    .append(basePrice, that.basePrice)
                    .append(currentPrice, that.currentPrice)
                    .append(numberType, that.numberType)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(numberType)
                    .append(basePrice)
                    .append(currentPrice)
                    .toHashCode();
        }
    }
}
