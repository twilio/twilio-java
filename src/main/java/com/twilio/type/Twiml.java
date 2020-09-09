package com.twilio.type;

import java.util.Objects;

public class Twiml {
    private final String rawTwiml;

    public Twiml(String twiml) {
        this.rawTwiml = twiml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Twiml other = (Twiml) o;
        return Objects.equals(this.rawTwiml, other.rawTwiml);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.rawTwiml);
    }

    @Override
    public String toString() {
        return this.rawTwiml;
    }
}
