package com.twilio.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;

public class OutboundCallPrice {
    private double basePrice;
    private double currentPrice;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutboundCallPrice that = (OutboundCallPrice) o;

        if (Double.compare(that.basePrice, basePrice) != 0) return false;
        return Double.compare(that.currentPrice, currentPrice) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(basePrice);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(currentPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OutboundCallPrice{" +
                "basePrice=" + basePrice +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
