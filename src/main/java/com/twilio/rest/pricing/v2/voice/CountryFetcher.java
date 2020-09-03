/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.pricing.v2.voice;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class CountryFetcher extends Fetcher<Country> {
    private final String pathIsoCountry;

    /**
     * Construct a new CountryFetcher.
     *
     * @param pathIsoCountry The ISO country code of the pricing information to
     *                       fetch
     */
    public CountryFetcher(final String pathIsoCountry) {
        this.pathIsoCountry = pathIsoCountry;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Country
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Country fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.PRICING.toString(),
            "/v2/Voice/Countries/" + this.pathIsoCountry + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Country fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Country.fromJson(response.getStream(), client.getObjectMapper());
    }
}
