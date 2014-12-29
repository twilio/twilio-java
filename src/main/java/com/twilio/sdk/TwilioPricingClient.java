package com.twilio.sdk;

import com.twilio.sdk.resource.instance.pricing.PhoneNumberCountry;
import com.twilio.sdk.resource.instance.pricing.VoiceCountry;
import com.twilio.sdk.resource.instance.pricing.VoiceNumber;
import com.twilio.sdk.resource.list.pricing.PhoneNumberCountryList;
import com.twilio.sdk.resource.list.pricing.VoiceCountryList;

public class TwilioPricingClient extends TwilioClient {

    public static final String DEFAULT_VERSION = "v1";

    public TwilioPricingClient(final String accountSid, final String authToken) {
        super(accountSid, authToken, "https://pricing.twilio.com");
    }

    public VoiceCountryList getVoiceCountries() {
        VoiceCountryList list = new VoiceCountryList(this);
        list.setRequestAccountSid(this.getAccountSid());
        return list;
    }

    public VoiceCountry getVoiceCountry(String isoCountry) {
        VoiceCountry country = new VoiceCountry(this, isoCountry);
        country.setRequestAccountSid(this.getAccountSid());
        return country;
    }

    public VoiceNumber getVoiceNumber(String number) {
        VoiceNumber voiceNumber = new VoiceNumber(this, number);
        voiceNumber.setRequestAccountSid(this.getAccountSid());
        return voiceNumber;
    }

    public PhoneNumberCountryList getPhoneNumberCountries() {
        PhoneNumberCountryList list = new PhoneNumberCountryList(this);
        list.setRequestAccountSid(this.getAccountSid());
        return list;
    }

    public PhoneNumberCountry getPhoneNumberCountry(String isoCountry) {
        PhoneNumberCountry country = new PhoneNumberCountry(this, isoCountry);
        country.setRequestAccountSid(this.getAccountSid());
        return country;
    }

}
