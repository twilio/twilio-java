/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.voice.v1.dialingpermissions;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
public class CountryFetcher extends Fetcher<Country> {
    private final String pathIsoCode;

    /**
     * Construct a new CountryFetcher.
     *
     * @param pathIsoCode The ISO country code
     */
    public CountryFetcher(final String pathIsoCode) {
        this.pathIsoCode = pathIsoCode;
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
            Domains.VOICE.toString(),
            "/v1/DialingPermissions/Countries/" + this.pathIsoCode + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Country fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Country.fromJson(response.getStream(), client.getObjectMapper());
    }
}