package com.twilio.types;

public class PhoneNumber {
    private String rawNumber;

    public PhoneNumber(String number) {
        this.rawNumber = number;
    }

    public String toString() {
        return this.rawNumber;
    }
}
