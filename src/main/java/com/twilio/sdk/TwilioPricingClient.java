package com.twilio.sdk;

import com.twilio.sdk.resource.instance.pricing.MessagingCountry;
import com.twilio.sdk.resource.instance.pricing.PhoneNumberCountry;
import com.twilio.sdk.resource.instance.pricing.VoiceCountry;
import com.twilio.sdk.resource.instance.pricing.VoiceNumber;
import com.twilio.sdk.resource.list.pricing.MessagingCountryList;
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
    private static final String DEFAULT_PRICING_ENDPOINT = "https://pricing.twilio.com";

    /**
     * Construct a new TwilioPricingClient.
     *
     * Your accountSid and authToken are the same as those you use to
     * authenticate to the main Twilio REST API, and are available in
     * <a href="https://www.twilio.com/user/account">the account portal</a>.
     * @param username Username for authentication. The 34 character Account identifier (starting with 'AC').
     *                 This can be found on your Twilio dashboard page.
     * @param password Password for authentication. The 32 character AuthToken.
     *                 This can be found on your Twilio dashboard page.
     */
    public TwilioPricingClient(final String username, final String password) {
        this(username, password, DEFAULT_PRICING_ENDPOINT);
    }

    /**
     * Construct a new TwilioPricingClient.
     *
     * Your accountSid and authToken are the same as those you use to
     * authenticate to the main Twilio REST API, and are available in
     * <a href="https://www.twilio.com/user/account">the account portal</a>.
     * @param username Username for authentication. The 34 character Account identifier (starting with 'AC').
     *                 This can be found on your Twilio dashboard page.
     * @param password Password for authentication. The 32 character AuthToken.
     *                 This can be found on your Twilio dashboard page.
     * @param endpoint Custom Twilio pricing endpoint
     */
    public TwilioPricingClient(final String username, final String password, String endpoint) {
        super(username, password, endpoint);
    }

    /**
     * Get a list of objects representing countries where Twilio Voice
     * services are available.
     *
     * This endpoint does not accept any filters.
     * @return List of countries where Twilio Voice is available.
     */
    public VoiceCountryList getVoiceCountries() {
        return new VoiceCountryList(this);
    }

    /**
     * Get pricing information for Twilio Voice services in a specific country.
     *
     * @param isoCountry ISO 3166-1 alpha-2 country code, e.g. "US" or "GB"
     * @return Pricing information for the requested country.
     */
    public VoiceCountry getVoiceCountry(String isoCountry) {
        return new VoiceCountry(this, isoCountry);
    }

    /**
     * Get pricing information for Twilio Voice calls to and from a specific phone number.
     * @param number E.164-formatted phone number, e.g. "+15105551234"
     * @return Pricing information for the requested phone number
     */
    public VoiceNumber getVoiceNumber(String number) {
        return new VoiceNumber(this, number);
    }

    /**
     * Get a list of countries where Twilio Phone Numbers are available.
     * @return Countries with Twilio Phone Numbers
     */
    public PhoneNumberCountryList getPhoneNumberCountries() {
        return new PhoneNumberCountryList(this);
    }

    /**
     * Get pricing information for Twilio Phone Numbers in a specific country.
     * @param isoCountry ISO 3166-1 alpha-2 country code, e.g. "US" or "GB"
     * @return Pricing information for the requested country.
     */
    public PhoneNumberCountry getPhoneNumberCountry(String isoCountry) {
        return new PhoneNumberCountry(this, isoCountry);
    }

    public MessagingCountryList getMessagingCountries() {
        return new MessagingCountryList(this);
    }

    public MessagingCountry getMessagingCountry(final String isoCountry) {
        return new MessagingCountry(this, isoCountry);
    }


}
