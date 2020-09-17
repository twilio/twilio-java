/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.trustedComms;

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
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class CpsFetcher extends Fetcher<Cps> {
    private String xXcnamSensitivePhoneNumber;

    /**
     * Phone number used to retrieve its corresponding CPS..
     *
     * @param xXcnamSensitivePhoneNumber Phone number to retrieve CPS.
     * @return this
     */
    public CpsFetcher setXXcnamSensitivePhoneNumber(final String xXcnamSensitivePhoneNumber) {
        this.xXcnamSensitivePhoneNumber = xXcnamSensitivePhoneNumber;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Cps
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Cps fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEW.toString(),
            "/TrustedComms/CPS"
        );

        addHeaderParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Cps fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Cps.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested header parameters to the Request.
     *
     * @param request Request to add header params to
     */
    private void addHeaderParams(final Request request) {
        if (xXcnamSensitivePhoneNumber != null) {
            request.addHeaderParam("X-Xcnam-Sensitive-Phone-Number", xXcnamSensitivePhoneNumber);
        }
    }
}