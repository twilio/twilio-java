package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutboundCallPriceWithOrigin {
    private final double basePrice;
    private final double currentPrice;
    private final List<String> originationPrefixes;

    @JsonCreator
    public OutboundCallPriceWithOrigin(@JsonProperty("base_price") final double basePrice,
                                       @JsonProperty("current_price") final double currentPrice,
                                       @JsonProperty("origination_prefixes") final List<String> originationPrefixes) {
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
        this.originationPrefixes = originationPrefixes;
    }

    public double getBasePrice() { return basePrice; }

    public double getCurrentPrice() { return currentPrice; }

    public List<String> getOriginationPrefixes() { return originationPrefixes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OutboundCallPriceWithOrigin other = (OutboundCallPriceWithOrigin) o;
        return Objects.equals(this.basePrice, other.basePrice) &&
               Objects.equals(this.currentPrice, other.currentPrice) &&
               Objects.equals(this.originationPrefixes, other.originationPrefixes);
    }

    @Override
    public int hashCode() { return Objects.hash(this.basePrice, this.currentPrice, this.originationPrefixes); }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("base_price", this.basePrice)
            .add("current_price", this.currentPrice)
            .add("origination_prefixes", this.originationPrefixes)
            .toString();
    }
}