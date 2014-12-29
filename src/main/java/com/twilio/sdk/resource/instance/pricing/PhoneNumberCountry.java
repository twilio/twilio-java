package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.InstanceResource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneNumberCountry extends InstanceResource<TwilioPricingClient> {

    public PhoneNumberCountry(final TwilioPricingClient client) {
        super(client);
    }

    public PhoneNumberCountry(final TwilioPricingClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    public PhoneNumberCountry(final TwilioPricingClient client, final String isoCountry) {
        super(client);
        if (isoCountry == null || "".equals(isoCountry)) {
            throw new IllegalArgumentException("isoCountry cannot be null");
        }
        setProperty("iso_country", isoCountry);
    }

    public String getIsoCountry() {
        return getProperty("iso_country");
    }

    public String getCountry() {
        return getProperty("country");
    }

    public String getPriceUnit() {
        return getProperty("price_unit");
    }

    public List<NumberPrice> getPhoneNumberPrices() {
        List<Map<String, String>> priceData = (List<Map<String, String>>) getObject("phone_number_prices");
        List<NumberPrice> prices = new ArrayList<NumberPrice>();

        for (Map<String, String> p : priceData) {
            prices.add(new NumberPrice(
                    NumberType.valueOf(p.get("type").toUpperCase()),
                    new BigDecimal(p.get("base_price")),
                    new BigDecimal(p.get("current_price"))
            ));
        }
        return prices;
    }

    protected String getResourceLocation() {
        return "/" + TwilioPricingClient.DEFAULT_VERSION + "/PhoneNumbers/Countries/" + getIsoCountry();
    }

    public class NumberPrice {
        private final NumberType numberType;
        private final BigDecimal basePrice;
        private final BigDecimal currentPrice;

        public NumberPrice(final NumberType numberType, final BigDecimal basePrice, final BigDecimal currentPrice) {
            this.numberType = numberType;
            this.basePrice = basePrice;
            this.currentPrice = currentPrice;
        }

        public NumberType getNumberType() {
            return numberType;
        }

        public BigDecimal getBasePrice() {
            return basePrice;
        }

        public BigDecimal getCurrentPrice() {
            return currentPrice;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NumberPrice that = (NumberPrice) o;

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
