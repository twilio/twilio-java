package com.twilio.sdk;

import com.twilio.sdk.resource.instance.pricing.PhoneNumberCountry;
import com.twilio.sdk.resource.instance.pricing.VoiceCountry;
import com.twilio.sdk.resource.instance.pricing.VoiceNumber;
import com.twilio.sdk.resource.list.pricing.PhoneNumberCountryList;
import com.twilio.sdk.resource.list.pricing.VoiceCountryList;

/**
 * Client for the Twilio Pricing API.
 *
 * The Twilio Pricing API provides pricing information on Twilio's hosted
 * phone numbers and voice services in the countries where they are available.
 *
 * For more information, see <a href="FIXME">the Twilio Pricing API documentation</a>.
 */
public class TwilioPricingClient extends TwilioClient {

    public static final String DEFAULT_VERSION = "v1";

    /**
     * Construct a new TwilioPricingClient.
     *
     * Your accountSid and authToken are the same as those you use to
     * authenticate to the main Twilio REST API, and are available in
     * <a href="https://www.twilio.com/user/account">the account portal</a>.
     * @param accountSid Your Twilio Account ID
     * @param authToken Your Twilio Account's authorization token
     */
    public TwilioPricingClient(final String accountSid, final String authToken) {
        super(accountSid, authToken, "https://pricing.twilio.com");
    }

    /**
     * Construct a new TwilioPricingClient.
     *
     * Your accountSid and authToken are the same as those you use to
     * authenticate to the main Twilio REST API, and are available in
     * <a href="https://www.twilio.com/user/account">the account portal</a>.
     * @param accountSid Your Twilio Account ID
     * @param authToken Your Twilio Account's authorization token
     * @param endpoint Custom Twilio pricing endpoint
     */
    public TwilioPricingClient(final String accountSid, final String authToken, String endpoint) {
        super(accountSid, authToken, endpoint);
    }

    /**
     * Get a list of objects representing countries where Twilio Voice
     * services are available.
     *
     * This endpoint does not accept any filters.
     * @return List of countries where Twilio Voice is available.
     */
    public VoiceCountryList getVoiceCountries() {
        VoiceCountryList list = new VoiceCountryList(this);
        list.setRequestAccountSid(this.getAccountSid());
        return list;
    }

    /**
     * Get pricing information for Twilio Voice services in a specific country.
     *
     * @param isoCountry ISO 3166-1 alpha-2 country code, e.g. "US" or "GB"
     * @return Pricing information for the requested country.
     */
    public VoiceCountry getVoiceCountry(String isoCountry) {
        VoiceCountry country = new VoiceCountry(this, isoCountry);
        country.setRequestAccountSid(this.getAccountSid());
        return country;
    }

    /**
     * Get pricing information for Twilio Voice calls to and from a specific phone number.
     * @param number E.164-formatted phone number, e.g. "+15105551234"
     * @return Pricing information for the requested phone number
     */
    public VoiceNumber getVoiceNumber(String number) {
        VoiceNumber voiceNumber = new VoiceNumber(this, number);
        voiceNumber.setRequestAccountSid(this.getAccountSid());
        return voiceNumber;
    }

    /**
     * Get a list of countries where Twilio Phone Numbers are available.
     * @return Countries with Twilio Phone Numbers
     */
    public PhoneNumberCountryList getPhoneNumberCountries() {
        PhoneNumberCountryList list = new PhoneNumberCountryList(this);
        list.setRequestAccountSid(this.getAccountSid());
        return list;
    }

    /**
     * Get pricing information for Twilio Phone Numbers in a specific country.
     * @param isoCountry ISO 3166-1 alpha-2 country code, e.g. "US" or "GB"
     * @return Pricing information for the requested country.
     */
    public PhoneNumberCountry getPhoneNumberCountry(String isoCountry) {
        PhoneNumberCountry country = new PhoneNumberCountry(this, isoCountry);
        country.setRequestAccountSid(this.getAccountSid());
        return country;
    }

}
