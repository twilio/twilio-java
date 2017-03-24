package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.twilio.converter.Promoter;

import java.util.Objects;

/**
 * Object representation of a price of a phone number.
 *
 * <p>
 *  For more information see:
 *  <a href=https://www.twilio.com/voice/pricing>Pricing Docs</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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

    private final double basePrice;
    private final double currentPrice;
    private final Type type;

    /**
     * Initialize a PhoneNumberPrice.
     *
     * @param basePrice base price of the phone number
     * @param currentPrice current price of the phone number
     * @param type type of phone number
     */
    @JsonCreator
    public PhoneNumberPrice(@JsonProperty("base_price") final double basePrice,
                            @JsonProperty("current_price") final double currentPrice,
                            @JsonProperty("number_type") final Type type) {
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
        this.type = type;
    }

    /**
     * Returns the base price of the phone number.
     * @return the base price of the phone number
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Returns the current price of the phone number.
     * @return the current price of the phone number
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Returns the type of phone number.
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("base_price", basePrice)
                .add("current_price", currentPrice)
                .add("type", type)
                .toString();
    }
}
