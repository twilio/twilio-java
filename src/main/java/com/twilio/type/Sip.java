package com.twilio.type;


import com.google.common.base.Objects;

public class Sip implements Endpoint {

    private final String sip;

    public Sip(String sip) {
        this.sip = sip;
    }

    @Override
    public String getEndpoint() {
        return sip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Sip other = (Sip) o;
        return Objects.equal(sip, other.sip);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sip);
    }

    @Override
    public String toString() {
        return this.sip;
    }
}
