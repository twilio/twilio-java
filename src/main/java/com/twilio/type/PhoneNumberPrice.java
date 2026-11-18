package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.converter.Promoter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Object representation of a price of a phone number.
 *
 * <p>
 * For more information see:
 * <a href=https://www.twilio.com/voice/pricing>Pricing Docs</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PhoneNumberPrice {

    /**
     * Possible phone number type.
     */
    public enum Type {
        LOCAL("local"),
        MOBILE("mobile"),
        NATIONAL("national"),
        TOLLFREE("toll free");

        private final String value;

        /**
         * Initialize the phone number type.
         *
         * @param value name of phone number type
         */
        Type(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Returns the type of phone number given a string value.
         *
         * @param value type of phone number
         * @return the type of phone number if valid; null otherwise
         */
        @JsonCreator
        public static Type forValue(final String value) {
            return Promoter.enumFromString(value, Type.values());
        }
    }

    private final BigDecimal basePrice;
    private final BigDecimal currentPrice;
    private final Type type;

    /**
     * Initialize a PhoneNumberPrice.
     *
     * @param basePrice    base price of the phone number
     * @param currentPrice current price of the phone number
     * @param type         type of phone number
     */
    @JsonCreator
    public PhoneNumberPrice(@JsonProperty("base_price") final BigDecimal basePrice,
                            @JsonProperty("current_price") final BigDecimal currentPrice,
                            @JsonProperty("number_type") final Type type) {
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
        this.type = type;
    }

    /**
     * Returns the base price of the phone number. The value returned by this method is represented as a {@code double},
     * which may result in loss of precision.
     *
     * @return the base price of the phone number
     *
     * @deprecated please use {{@link #getBasePriceDecimal()} instead for a lossless representation of the price
     */
    @Deprecated
    public double getBasePrice() {
        return basePrice.doubleValue();
    }

    /**
     * Returns the base price of the phone number.
     *
     * @return the base price of the phone number
     */
    public BigDecimal getBasePriceDecimal() {
        return basePrice;
    }

    /**
     * Returns the current price of the phone number. The value returned by this method is represented as a
     * {@code double}, which may result in loss of precision.
     *
     * @return the current price of the phone number
     *
     * @deprecated please use {{@link #getCurrentPriceDecimal()} instead for a lossless representation of the price
     */
    @Deprecated
    public double getCurrentPrice() {
        return currentPrice.doubleValue();
    }

    /**
     * Returns the current price of the phone number.
     *
     * @return the current price of the phone number
     */    public BigDecimal getCurrentPriceDecimal() {
        return currentPrice;
    }

    /**
     * Returns the type of phone number.
     *
     * @return the type of phone number
     */
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

        PhoneNumberPrice other = (PhoneNumberPrice) o;
        return Objects.equals(this.basePrice, other.basePrice) &&
            Objects.equals(this.currentPrice, other.currentPrice) &&
            Objects.equals(this.type, other.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.basePrice, this.currentPrice, this.type);
    }
}
