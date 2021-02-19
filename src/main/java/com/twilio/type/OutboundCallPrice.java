package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OutboundCallPrice {
    private final double basePrice;
    private final double currentPrice;

    @JsonCreator
    public OutboundCallPrice(@JsonProperty("base_price") final double basePrice,
                             @JsonProperty("current_price") final double currentPrice) {
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getCurrentPrice() {
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
