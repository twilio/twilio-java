/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Messaging
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.messaging.v1;

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class BrandRegistrationCreator extends Creator<BrandRegistration> {

    private String customerProfileBundleSid;
    private String a2PProfileBundleSid;
    private String brandType;
    private Boolean mock;
    private Boolean skipAutomaticSecVet;

    public BrandRegistrationCreator(
        final String customerProfileBundleSid,
        final String a2PProfileBundleSid
    ) {
        this.customerProfileBundleSid = customerProfileBundleSid;
        this.a2PProfileBundleSid = a2PProfileBundleSid;
    }

    public BrandRegistrationCreator setCustomerProfileBundleSid(
        final String customerProfileBundleSid
    ) {
        this.customerProfileBundleSid = customerProfileBundleSid;
        return this;
    }

    public BrandRegistrationCreator setA2PProfileBundleSid(
        final String a2PProfileBundleSid
    ) {
        this.a2PProfileBundleSid = a2PProfileBundleSid;
        return this;
    }

    public BrandRegistrationCreator setBrandType(final String brandType) {
        this.brandType = brandType;
        return this;
    }

    public BrandRegistrationCreator setMock(final Boolean mock) {
        this.mock = mock;
        return this;
    }

    public BrandRegistrationCreator setSkipAutomaticSecVet(
        final Boolean skipAutomaticSecVet
    ) {
        this.skipAutomaticSecVet = skipAutomaticSecVet;
        return this;
    }

    @Override
    public BrandRegistration create(final TwilioRestClient client) {
        String path = "/v1/a2p/BrandRegistrations";

        path =
            path.replace(
                "{" + "CustomerProfileBundleSid" + "}",
                this.customerProfileBundleSid.toString()
            );
        path =
            path.replace(
                "{" + "A2PProfileBundleSid" + "}",
                this.a2PProfileBundleSid.toString()
            );

        Request request = new Request(
            HttpMethod.POST,
            Domains.MESSAGING.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "BrandRegistration creation failed: Unable to connect to server"
            );
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(
                response.getStream(),
                client.getObjectMapper()
            );
            if (restException == null) {
                throw new ApiException(
                    "Server Error, no content",
                    response.getStatusCode()
                );
            }
            throw new ApiException(restException);
        }

        return BrandRegistration.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (customerProfileBundleSid != null) {
            request.addPostParam(
                "CustomerProfileBundleSid",
                customerProfileBundleSid
            );
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
            request.addPostParam(
                "SkipAutomaticSecVet",
                skipAutomaticSecVet.toString()
            );
        }
    }
}
