package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Outbound prices for prefixes.
 *
 * <p>
 * For more information see:
 * <a href=https://www.twilio.com/docs/api/pricing/voice#outbound-prefix-price>Pricing docs</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OutboundPrefixPrice {

    private final List<String> prefixes;
    private final String friendlyName;
    private final BigDecimal basePrice;
    private final BigDecimal currentPrice;

    /**
     * Initialize an OutboundPrefixPrice.
     *
     * @param prefixes     price prefixes
     * @param friendlyName friend name for the price
     * @param basePrice    base price
     * @param currentPrice current price
     */
    @JsonCreator
    public OutboundPrefixPrice(@JsonProperty("prefixes") final List<String> prefixes,
                               @JsonProperty("friendly_name") final String friendlyName,
                               @JsonProperty("base_price") final BigDecimal basePrice,
                               @JsonProperty("current_price") final BigDecimal currentPrice) {
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

    /**
     * Returns the retail price per minute to make a call to numbers matching this prefix list. The value returned by
     * this method is represented as a {@code double}, which may result in loss of precision.
     *
     * @return the retail price per minute to make a call to numbers matching this prefix list
     *
     * @deprecated please use {{@link #getBasePriceDecimal()}} instead for a lossless representation of the price
     */
    @Deprecated
    public double getBasePrice() {
        return basePrice.doubleValue();
    }

    /**
     * Returns the retail price per minute to make a call to numbers matching this prefix list.
     *
     * @return the retail price per minute to make a call to numbers matching this prefix list
     */
    public BigDecimal getBasePriceDecimal() {
        return basePrice;
    }

    /**
     * Returns the current price per minute (which accounts for any volume or custom price discounts) to make a call to
     * numbers matching this prefix list. The value returned by this method is represented as a {@code double}, which
     * may result in loss of precision.
     *
     * @return the current price per minute to make a call to numbers matching this prefix list
     *
     * @deprecated please use {{@link #getCurrentPriceDecimal()} instead for a lossless representation of the price
     */
    @Deprecated
    public double getCurrentPrice() {
        return currentPrice.doubleValue();
    }

    /**
     * Returns the current price per minute (which accounts for any volume or custom price discounts) to make a call to
     * numbers matching this prefix list.
     *
     * @return the current price per minute to make a call to numbers matching this prefix list
     */
    public BigDecimal getCurrentPriceDecimal() {
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
}
