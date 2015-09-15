package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
        this(client, new HashMap<String, Object>() {{
            put("iso_country", isoCountry);
        }});
    }

    public MessagingCountry(final TwilioPricingClient client, final Map<String, Object> properties) {
        super(client, properties);
        this.setRequestAccountSid(client.getAccountSid());
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioPricingClient.DEFAULT_VERSION + "/PhoneNumbers/Countries/" + getIsoCountry();
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
        List<Map<String, Object>> priceData = getCastedObject("outbound_sms_pricing");

        List<OutboundSmsPrice> prices = new ArrayList<OutboundSmsPrice>();
        for (Map<String, Object> data : priceData) {
            prices.add(new OutboundSmsPrice(
                data.get("mcc").toString(),
                data.get("mnc").toString(),
                data.get("carrier").toString(),
                PriceDefinition.fromMapList(
                    (List<Map<String, String>>) data.get("prices")
                )
            ));
        }

        return prices;
    }

    /**
     * Get a list of prices for inbound SNS in this country,
     * broken out by number type
     *
     * @return List of SMS prices with inbound pricing info
     */
    public List<PriceDefinition> getInboundSmsPrices() {
        List<Map<String, String>> priceData = getCastedObject("inbound_sms_pricing");
        return PriceDefinition.fromMapList(priceData);
    }

    /**
     * Represents current prices for outbound Twilio SMS to the
     * given list of number prefixes.
     */
    public class OutboundSmsPrice {
        private final String mcc;
        private final String mnc;
        private final String carrier;
        private final List<PriceDefinition> prices;

        public OutboundSmsPrice(final String mcc, final String mnc, final String carrier, final List<PriceDefinition> prices) {
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
        public List<PriceDefinition> getPrices() {
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
}
