package com.twilio.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

public class OutboundSmsPrice {
    private final String mcc;
    private final String mnc;
    private final String carrier;
    private final List<InboundSmsPrice> prices;

    @JsonCreator
    public OutboundSmsPrice(@JsonProperty("mcc") final String mcc,
                            @JsonProperty("mnc") final String mnc,
                            @JsonProperty("carrier") final String carrier,
                            @JsonProperty("prices") final List<InboundSmsPrice> prices) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.carrier = carrier;
        this.prices = prices;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public String getCarrier() {
        return carrier;
    }

    public List<InboundSmsPrice> getPrices() {
        return prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OutboundSmsPrice other = (OutboundSmsPrice) o;
        return Objects.equals(this.mcc, other.mcc) &&
               Objects.equals(this.mnc, other.mnc) &&
               Objects.equals(this.carrier, other.carrier) &&
               Objects.equals(this.prices, other.prices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mcc, this.mnc, this.carrier, this.prices);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mcc", mcc)
                .add("mnc", mnc)
                .add("carrier", carrier)
                .add("prices", prices)
                .toString();
    }
}
