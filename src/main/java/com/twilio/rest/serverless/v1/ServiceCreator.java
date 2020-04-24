/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.serverless.v1;

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
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class ServiceCreator extends Creator<Service> {
    private final String uniqueName;
    private final String friendlyName;
    private Boolean includeCredentials;
    private Boolean uiEditable;

    /**
     * Construct a new ServiceCreator.
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   Service resource
     * @param friendlyName A string to describe the Service resource
     */
    public ServiceCreator(final String uniqueName,
                          final String friendlyName) {
        this.uniqueName = uniqueName;
        this.friendlyName = friendlyName;
    }

    /**
     * Whether to inject Account credentials into a function invocation context. The
     * default value is `false`..
     *
     * @param includeCredentials Whether to inject Account credentials into a
     *                           function invocation context
     * @return this
     */
    public ServiceCreator setIncludeCredentials(final Boolean includeCredentials) {
        this.includeCredentials = includeCredentials;
        return this;
    }

    /**
     * Whether the Service's properties and subresources can be edited via the UI.
     * The default value is `false`..
     *
     * @param uiEditable Whether the Service's properties and subresources can be
     *                   edited via the UI
     * @return this
     */
    public ServiceCreator setUiEditable(final Boolean uiEditable) {
        this.uiEditable = uiEditable;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Service
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Service create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SERVERLESS.toString(),
            "/v1/Services",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Service creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                restException.getDetails(),
                null
            );
        }

        return Service.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (includeCredentials != null) {
            request.addPostParam("IncludeCredentials", includeCredentials.toString());
        }

        if (uiEditable != null) {
            request.addPostParam("UiEditable", uiEditable.toString());
        }
    }
}