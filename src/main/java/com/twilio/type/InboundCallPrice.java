package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.converter.Promoter;
import lombok.ToString;

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

    private final double basePrice;
    private final double currentPrice;
    private final Type type;

    /**
     * Initialize a InboundCallPrice.
     *
     * @param basePrice    base price of call
     * @param currentPrice current price of call
     * @param type         type of phone number
     */
    @JsonCreator
    public InboundCallPrice(@JsonProperty("base_price") final double basePrice,
                            @JsonProperty("current_price") final double currentPrice,
                            @JsonProperty("number_type") final Type type) {
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
        this.type = type;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getCurrentPrice() {
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
