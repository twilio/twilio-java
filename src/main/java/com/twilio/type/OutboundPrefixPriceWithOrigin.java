package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * Outbound prices for prefixes with origin.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OutboundPrefixPriceWithOrigin {

    private final List<String> destination_prefixes;
    private final List<String> origination_prefixes;
    private final String friendlyName;
    private final double basePrice;
    private final double currentPrice;

    /**
     * Initialize an OutboundPrefixPriceWithOrigin.
     *
     * @param destination_prefixes destination price prefixes
     * @param origination_prefixes origination price prefixes
     * @param friendlyName         friend name for the price
     * @param basePrice            base price
     * @param currentPrice         current price
     */
    @JsonCreator
    public OutboundPrefixPriceWithOrigin(@JsonProperty("destination_prefixes") final List<String> destination_prefixes,
                                         @JsonProperty("origination_prefixes") final List<String> origination_prefixes,
                                         @JsonProperty("friendly_name") final String friendlyName,
                                         @JsonProperty("base_price") final double basePrice,
                                         @JsonProperty("current_price") final double currentPrice) {
        this.destination_prefixes = destination_prefixes;
        this.origination_prefixes = origination_prefixes;
        this.friendlyName = friendlyName;
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
    }

    public List<String> getDestinationPrefixes() {
        return destination_prefixes;
    }

    public List<String> getOriginationPrefixes() {
        return origination_prefixes;
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

        OutboundPrefixPriceWithOrigin other = (OutboundPrefixPriceWithOrigin) o;
        return Objects.equals(this.basePrice, other.basePrice) &&
            Objects.equals(this.currentPrice, other.currentPrice) &&
            Objects.equals(this.destination_prefixes, other.destination_prefixes) &&
            Objects.equals(this.origination_prefixes, other.origination_prefixes) &&
            Objects.equals(this.friendlyName, other.friendlyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.destination_prefixes, this.origination_prefixes, this.friendlyName, this.basePrice, this.currentPrice);
    }
}
