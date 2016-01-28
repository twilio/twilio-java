package com.twilio.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

public class OutboundSmsPrice {
    private String mcc;
    private String mnc;
    private String carrier;
    private List<InboundSmsPrice> prices;

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

    public List<InboundSmsPrice> getPrices() {
        return prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutboundSmsPrice that = (OutboundSmsPrice) o;

        if (mcc != null ? !mcc.equals(that.mcc) : that.mcc != null) return false;
        if (mnc != null ? !mnc.equals(that.mnc) : that.mnc != null) return false;
        if (carrier != null ? !carrier.equals(that.carrier) : that.carrier != null) return false;
        return !(prices != null ? !prices.equals(that.prices) : that.prices != null);

    }

    @Override
    public int hashCode() {
        int result = mcc != null ? mcc.hashCode() : 0;
        result = 31 * result + (mnc != null ? mnc.hashCode() : 0);
        result = 31 * result + (carrier != null ? carrier.hashCode() : 0);
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        return result;
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
