/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.voice.v1.dialingpermissions;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
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
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class CountryReader extends Reader<Country> {
    private String isoCode;
    private String continent;
    private String countryCode;
    private Boolean lowRiskNumbersEnabled;
    private Boolean highRiskSpecialNumbersEnabled;
    private Boolean highRiskTollfraudNumbersEnabled;

    /**
     * Filter to retrieve the country permissions by specifying the <a
     * href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO country code</a>.
     *
     * @param isoCode Filter to retrieve the country permissions by specifying the
     *                ISO country code
     * @return this
     */
    public CountryReader setIsoCode(final String isoCode) {
        this.isoCode = isoCode;
        return this;
    }

    /**
     * Filter to retrieve the country permissions by specifying the continent.
     *
     * @param continent Filter to retrieve the country permissions by specifying
     *                  the continent
     * @return this
     */
    public CountryReader setContinent(final String continent) {
        this.continent = continent;
        return this;
    }

    /**
     * Filter the results by specified <a
     * href="https://www.itu.int/itudoc/itu-t/ob-lists/icc/e164_763.html">country
     * codes</a>.
     *
     * @param countryCode Country code filter
     * @return this
     */
    public CountryReader setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    /**
     * Filter to retrieve the country permissions with dialing to low-risk numbers
     * enabled. Can be: `true` or `false`..
     *
     * @param lowRiskNumbersEnabled Filter to retrieve the country permissions with
     *                              dialing to low-risk numbers enabled
     * @return this
     */
    public CountryReader setLowRiskNumbersEnabled(final Boolean lowRiskNumbersEnabled) {
        this.lowRiskNumbersEnabled = lowRiskNumbersEnabled;
        return this;
    }

    /**
     * Filter to retrieve the country permissions with dialing to high-risk special
     * service numbers enabled. Can be: `true` or `false`.
     *
     * @param highRiskSpecialNumbersEnabled Filter to retrieve the country
     *                                      permissions with dialing to high-risk
     *                                      special service numbers enabled
     * @return this
     */
    public CountryReader setHighRiskSpecialNumbersEnabled(final Boolean highRiskSpecialNumbersEnabled) {
        this.highRiskSpecialNumbersEnabled = highRiskSpecialNumbersEnabled;
        return this;
    }

    /**
     * Filter to retrieve the country permissions with dialing to high-risk <a
     * href="https://www.twilio.com/learn/voice-and-video/toll-fraud">toll fraud</a>
     * numbers enabled. Can be: `true` or `false`..
     *
     * @param highRiskTollfraudNumbersEnabled Filter to retrieve the country
     *                                        permissions with dialing to high-risk
     *                                        toll fraud numbers enabled
     * @return this
     */
    public CountryReader setHighRiskTollfraudNumbersEnabled(final Boolean highRiskTollfraudNumbersEnabled) {
        this.highRiskTollfraudNumbersEnabled = highRiskTollfraudNumbersEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Country ResourceSet
     */
    @Override
    public ResourceSet<Country> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Country ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Country> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.VOICE.toString(),
            "/v1/DialingPermissions/Countries"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Country ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Country> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Country> nextPage(final Page<Country> page,
                                  final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.VOICE.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<Country> previousPage(final Page<Country> page,
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.VOICE.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Country Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Country> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Country read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "content",
            response.getContent(),
            Country.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (isoCode != null) {
            request.addQueryParam("IsoCode", isoCode);
        }

        if (continent != null) {
            request.addQueryParam("Continent", continent);
        }

        if (countryCode != null) {
            request.addQueryParam("CountryCode", countryCode);
        }

        if (lowRiskNumbersEnabled != null) {
            request.addQueryParam("LowRiskNumbersEnabled", lowRiskNumbersEnabled.toString());
        }

        if (highRiskSpecialNumbersEnabled != null) {
            request.addQueryParam("HighRiskSpecialNumbersEnabled", highRiskSpecialNumbersEnabled.toString());
        }

        if (highRiskTollfraudNumbersEnabled != null) {
            request.addQueryParam("HighRiskTollfraudNumbersEnabled", highRiskTollfraudNumbersEnabled.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
