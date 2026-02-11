package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.converter.Promoter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Pricing for inbound calls
 *
 * <p>
 * For more information see:
 * <a href=https://www.twilio.com/voice/pricing>Pricing Docs</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class InboundCallPrice {
    public enum Type {
        LOCAL("local"),
        MOBILE("mobile"),
        NATIONAL("national"),
        TOLLFREE("toll free");

        private final String value;

        Type(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static Type forValue(final String value) {
            return Promoter.enumFromString(value, Type.values());
        }
    }

    private final BigDecimal basePrice;
    private final BigDecimal currentPrice;
    private final Type type;

    /**
     * Initialize a InboundCallPrice.
     *
     * @param basePrice    base price of call
     * @param currentPrice current price of call
     * @param type         type of phone number
     */
    @JsonCreator
    public InboundCallPrice(@JsonProperty("base_price") final BigDecimal basePrice,
                            @JsonProperty("current_price") final BigDecimal currentPrice,
                            @JsonProperty("number_type") final Type type) {
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
        this.type = type;
    }

    /**
     * Returns the retail price per minute to receive a call to this phone number type. The value returned by this
     * method is represented as a {@code double}, which may result in loss of precision.
     *
     * @return the retail price per minute to receive a call to this phone number type
     *
     * @deprecated please use {{@link #getBasePriceDecimal()}} instead for a lossless representation of the price
     */
    @Deprecated
    public double getBasePrice() {
        return basePrice.doubleValue();
    }

    /**
     * Returns the retail price per minute to receive a call to this phone number type.
     *
     * @return the retail price per minute to receive a call to this phone number type
     */
    public BigDecimal getBasePriceDecimal() {
        return basePrice;
    }

    /**
     * Returns the current price per minute (which accounts for any volume or custom price discounts) to receive a call
     * to this phone number type. The value returned by this method is represented as a {@code double}, which may result
     * in loss of precision.
     *
     * @return the current price per minute to receive a call to this phone number type
     *
     * @deprecated please use {{@link #getCurrentPriceDecimal()} instead for a lossless representation of the price
     */
    @Deprecated
    public double getCurrentPrice() {
        return currentPrice.doubleValue();
    }

    /**
     * Returns the current price per minute (which accounts for any volume or custom price discounts) to receive a call
     * to this phone number type.
     *
     * @return the current price per minute to receive a call to this phone number type
     */
    public BigDecimal getCurrentPriceDecimal() {
        return currentPrice;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InboundCallPrice other = (InboundCallPrice) o;

        return (this.getBasePrice() == other.getBasePrice() &&
            this.getCurrentPrice() == other.getCurrentPrice() &&
            this.getType() == other.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.basePrice, this.currentPrice, this.type);
    }
}
