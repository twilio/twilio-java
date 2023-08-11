package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OutboundCallPrice {
    private final BigDecimal basePrice;
    private final BigDecimal currentPrice;

    @JsonCreator
    public OutboundCallPrice(@JsonProperty("base_price") final BigDecimal basePrice,
                             @JsonProperty("current_price") final BigDecimal currentPrice) {
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
    }

    /**
     * Returns the retail price per minute to make a call from this phone number type. The value returned by this
     * method is represented as a {@code double}, which may result in loss of precision.
     *
     * @return the retail price per minute to make a call from this phone number type
     *
     * @deprecated please use {{@link #getBasePriceDecimal()}} instead for a lossless representation of the price
     */
    @Deprecated
    public double getBasePrice() {
        return basePrice.doubleValue();
    }

    /**
     * Returns the retail price per minute to make a call from this phone number type.
     *
     * @return the retail price per minute to make a call from this phone number type
     */
    public BigDecimal getBasePriceDecimal() {
        return basePrice;
    }

    /**
     * Returns the current price per minute (which accounts for any volume or custom price discounts) to make a call
     * from this phone number type. The value returned by this method is represented as a {@code double}, which may
     * result in loss of precision.
     *
     * @return the current price per minute to make a call from this phone number type
     *
     * @deprecated please use {{@link #getCurrentPriceDecimal()} instead for a lossless representation of the price
     */
    @Deprecated
    public double getCurrentPrice() {
        return currentPrice.doubleValue();
    }

    /**
     * Returns the current price per minute (which accounts for any volume or custom price discounts) to make a call
     * from this phone number type.
     *
     * @return the current price per minute to make a call from this phone number type
     */
    public BigDecimal getCurrentPriceDecimal() {
        return currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OutboundCallPrice that = (OutboundCallPrice) o;
        return Objects.equals(this.basePrice, that.basePrice) &&
            Objects.equals(this.currentPrice, that.currentPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.basePrice, this.currentPrice);
    }
}
