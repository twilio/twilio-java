package com.twilio.sdk.fetchers.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.AvailablePhoneNumberCountry;

public class AvailablePhoneNumberCountryFetcher extends Fetcher<AvailablePhoneNumberCountry> {
    private final String accountSid;
    private final String countryCode;

    /**
     * Construct a new AvailablePhoneNumberCountryFetcher
     * 
     * @param accountSid The account_sid
     * @param countryCode The country_code
     */
    public AvailablePhoneNumberCountryFetcher(final String accountSid, final String countryCode) {
        this.accountSid = accountSid;
        this.countryCode = countryCode;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched AvailablePhoneNumberCountry
     */
    @Override
    public AvailablePhoneNumberCountry execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/AvailablePhoneNumbers/" + this.countryCode + ".json",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("AvailablePhoneNumberCountry fetch failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null)
                throw new ApiException("Server Error, no content");
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return AvailablePhoneNumberCountry.fromJson(response.getStream(), client.getObjectMapper());
    }
}