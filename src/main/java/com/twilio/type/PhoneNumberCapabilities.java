package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.Objects;

/**
 * Capabilities of a Phone Number.
 *
 * <p>
 * For more information see:
 * <a href=https://www.twilio.com/docs/api/rest/available-phone-numbers>Phone Number Docs</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PhoneNumberCapabilities {
    private final boolean mms;
    private final boolean sms;
    private final boolean voice;
    private final boolean fax;

    /**
     * Initialize a PhoneNumberCapability.
     *
     * @param mms   mms enabled
     * @param sms   sms enabled
     * @param voice voice enabled
     * @param fax   fax enabled
     */
    @JsonCreator
    public PhoneNumberCapabilities(@JsonProperty("MMS") final boolean mms,
                                   @JsonProperty("SMS") final boolean sms,
                                   @JsonProperty("voice") final boolean voice,
                                   @JsonProperty("fax") final boolean fax) {
        this.mms = mms;
        this.sms = sms;
        this.voice = voice;
        this.fax = fax;
    }

    public boolean getMms() {
        return this.mms;
    }

    public boolean getSms() {
        return this.sms;
    }

    public boolean getVoice() {
        return this.voice;
    }

    public boolean getFax() {
        return this.fax;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhoneNumberCapabilities other = (PhoneNumberCapabilities) o;
        return Objects.equals(this.mms, other.mms) &&
            Objects.equals(this.sms, other.sms) &&
            Objects.equals(this.voice, other.voice) &&
            Objects.equals(this.fax, other.fax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mms, this.sms, this.voice, this.fax);
    }
}
