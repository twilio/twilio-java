package com.twilio.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

public class OutboundPrefixPrice {
    private List<String> prefixes;
    private String friendlyName;
    private double basePrice;
    private double currentPrice;

    @JsonCreator
    public OutboundPrefixPrice(@JsonProperty("prefixes") final List<String> prefixes,
                               @JsonProperty("friendly_name") final String friendlyName,
                               @JsonProperty("base_price") final double basePrice,
                               @JsonProperty("current_price") final double currentPrice) {
        this.prefixes = prefixes;
        this.friendlyName = friendlyName;
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
    }

    public List<String> getPrefixes() {
        return prefixes;
    }

    public String getFriendlyName() {
        return friendlyName;
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

        OutboundPrefixPrice that = (OutboundPrefixPrice) o;

        if (Double.compare(that.basePrice, basePrice) != 0) return false;
        if (Double.compare(that.currentPrice, currentPrice) != 0) return false;
        if (prefixes != null ? !prefixes.equals(that.prefixes) : that.prefixes != null) return false;
        return !(friendlyName != null ? !friendlyName.equals(that.friendlyName) : that.friendlyName != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = prefixes != null ? prefixes.hashCode() : 0;
        result = 31 * result + (friendlyName != null ? friendlyName.hashCode() : 0);
        temp = Double.doubleToLongBits(basePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(currentPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OutboundPrefixPrice{" +
                "prefixes=" + prefixes +
                ", friendlyName='" + friendlyName + '\'' +
                ", basePrice=" + basePrice +
                ", currentPrice=" + currentPrice +
                '}';
    }

}
