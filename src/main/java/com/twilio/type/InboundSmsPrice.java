package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.twilio.converter.Promoter;

import java.util.Objects;

/**
 * Pricing for inbound sms.
 *
 * <p>
 *     For more information see:
 *     <a href=https://www.twilio.com/docs/api/pricing/messaging>Message Pricing Docs</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InboundSmsPrice {
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
     * Initialize an InboundSmsPrice.
     *
     * @param basePrice base price for sms
     * @param currentPrice current price for sms
     * @param type type of phone number
     */
    @JsonCreator
    public InboundSmsPrice(@JsonProperty("base_price") final double basePrice,
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

        InboundSmsPrice other = (InboundSmsPrice) o;
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
