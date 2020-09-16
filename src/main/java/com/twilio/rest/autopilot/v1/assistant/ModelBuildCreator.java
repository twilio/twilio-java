/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1.assistant;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
public class ModelBuildCreator extends Creator<ModelBuild> {
    private final String pathAssistantSid;
    private URI statusCallback;
    private String uniqueName;

    /**
     * Construct a new ModelBuildCreator.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         new resource
     */
    public ModelBuildCreator(final String pathAssistantSid) {
        this.pathAssistantSid = pathAssistantSid;
    }

    /**
     * The URL we should call using a POST method to send status information to
     * your application..
     *
     * @param statusCallback The URL we should call using a POST method to send
     *                       status information to your application
     * @return this
     */
    public ModelBuildCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL we should call using a POST method to send status information to
     * your application..
     *
     * @param statusCallback The URL we should call using a POST method to send
     *                       status information to your application
     * @return this
     */
    public ModelBuildCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * An application-defined string that uniquely identifies the new resource.
     * This value must be a unique string of no more than 64 characters. It can be
     * used as an alternative to the `sid` in the URL path to address the resource..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   new resource
     * @return this
     */
    public ModelBuildCreator setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created ModelBuild
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public ModelBuild create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.AUTOPILOT.toString(),
            "/v1/Assistants/" + this.pathAssistantSid + "/ModelBuilds"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ModelBuild creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return ModelBuild.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }
    }
}