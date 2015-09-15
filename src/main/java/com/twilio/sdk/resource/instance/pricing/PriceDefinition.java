package com.twilio.sdk.resource.instance.pricing;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Base fields of a price
 */
public class PriceDefinition {
    private final NumberType numberType;
    private final BigDecimal basePrice;
    private final BigDecimal currentPrice;

    public PriceDefinition(final NumberType numberType, final BigDecimal basePrice, final BigDecimal currentPrice) {
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
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriceDefinition that = (PriceDefinition) o;
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

    public static List<PriceDefinition> fromMapList(List<Map<String, String>> priceData) {
        List<PriceDefinition> prices = new ArrayList<PriceDefinition>();
        for (Map<String, String> p : priceData) {
            prices.add(new PriceDefinition(
                NumberType.valueOf(p.get("number_type").toUpperCase()),
                new BigDecimal(p.get("base_price")),
                new BigDecimal(p.get("current_price"))
            ));
        }
        return prices;
    }
}
