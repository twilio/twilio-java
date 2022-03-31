/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.messaging.v1;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class BrandRegistrationCreator extends Creator<BrandRegistration> {
    private final String customerProfileBundleSid;
    private final String a2PProfileBundleSid;
    private String brandType;
    private Boolean mock;
    private Boolean skipAutomaticSecVet;

    /**
     * Construct a new BrandRegistrationCreator.
     *
     * @param customerProfileBundleSid Customer Profile Bundle Sid
     * @param a2PProfileBundleSid A2P Messaging Profile Bundle Sid
     */
    public BrandRegistrationCreator(final String customerProfileBundleSid,
                                    final String a2PProfileBundleSid) {
        this.customerProfileBundleSid = customerProfileBundleSid;
        this.a2PProfileBundleSid = a2PProfileBundleSid;
    }

    /**
     * Type of brand being created. One of: "STANDARD", "STARTER". STARTER is for
     * low volume, starter use cases. STANDARD is for all other use cases..
     *
     * @param brandType Type of brand being created. One of: "STANDARD", "STARTER".
     * @return this
     */
    public BrandRegistrationCreator setBrandType(final String brandType) {
        this.brandType = brandType;
        return this;
    }

    /**
     * A boolean that specifies whether brand should be a mock or not. If true,
     * brand will be registered as a mock brand. Defaults to false if no value is
     * provided..
     *
     * @param mock A boolean that specifies whether brand should be a mock or not.
     *             If true, brand will be registered as a mock brand. Defaults to
     *             false if no value is provided.
     * @return this
     */
    public BrandRegistrationCreator setMock(final Boolean mock) {
        this.mock = mock;
        return this;
    }

    /**
     * A flag to disable automatic secondary vetting for brands which it would
     * otherwise be done..
     *
     * @param skipAutomaticSecVet Skip Automatic Secondary Vetting
     * @return this
     */
    public BrandRegistrationCreator setSkipAutomaticSecVet(final Boolean skipAutomaticSecVet) {
        this.skipAutomaticSecVet = skipAutomaticSecVet;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created BrandRegistration
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public BrandRegistration create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.MESSAGING.toString(),
            "/v1/a2p/BrandRegistrations"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("BrandRegistration creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return BrandRegistration.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (customerProfileBundleSid != null) {
            request.addPostParam("CustomerProfileBundleSid", customerProfileBundleSid);
        }

        if (a2PProfileBundleSid != null) {
            request.addPostParam("A2PProfileBundleSid", a2PProfileBundleSid);
        }

        if (brandType != null) {
            request.addPostParam("BrandType", brandType);
        }

        if (mock != null) {
            request.addPostParam("Mock", mock.toString());
        }

        if (skipAutomaticSecVet != null) {
            request.addPostParam("SkipAutomaticSecVet", skipAutomaticSecVet.toString());
        }
    }
}