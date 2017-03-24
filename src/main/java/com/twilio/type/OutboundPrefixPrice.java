package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

/**
 * Outbound prices for prefixes.
 *
 * <p>
 *     For more information see:
 *     <a href=https://www.twilio.com/docs/api/pricing/voice#outbound-prefix-price>Pricing docs</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutboundPrefixPrice {

    private final List<String> prefixes;
    private final String friendlyName;
    private final double basePrice;
    private final double currentPrice;

    /**
     * Initialize an OutboundPrefixPrice.
     *
     * @param prefixes price prefixes
     * @param friendlyName friend name for the price
     * @param basePrice base price
     * @param currentPrice current price
     */
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OutboundPrefixPrice other = (OutboundPrefixPrice) o;
        return Objects.equals(this.basePrice, other.basePrice) &&
               Objects.equals(this.currentPrice, other.currentPrice) &&
               Objects.equals(this.prefixes, other.prefixes) &&
               Objects.equals(this.friendlyName, other.friendlyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.prefixes, this.friendlyName, this.basePrice, this.currentPrice);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("prefixes", this.prefixes)
                .add("friendly_name", this.friendlyName)
                .add("base_price", this.basePrice)
                .add("current_price", this.currentPrice)
                .toString();
    }

}
