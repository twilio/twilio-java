/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.supersim.v1;

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
public class SimCreator extends Creator<Sim> {
    private final String iccid;
    private final String registrationCode;

    /**
     * Construct a new SimCreator.
     *
     * @param iccid The
     *              <a href="https://en.wikipedia.org/wiki/Subscriber_identity_module#ICCID">ICCID</a>
     *              of the Super SIM to be added to your Account
     * @param registrationCode The 10-digit code required to claim the Super SIM
     *                         for your Account
     */
    public SimCreator(final String iccid,
                      final String registrationCode) {
        this.iccid = iccid;
        this.registrationCode = registrationCode;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Sim
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Sim create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SUPERSIM.toString(),
            "/v1/Sims"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Sim creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Sim.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (iccid != null) {
            request.addPostParam("Iccid", iccid);
        }

        if (registrationCode != null) {
            request.addPostParam("RegistrationCode", registrationCode);
        }
    }
}