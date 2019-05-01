/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.serverless.v1.service;

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

import java.util.List;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class BuildCreator extends Creator<Build> {
    private final String pathServiceSid;
    private List<String> assetVersions;
    private List<String> functionVersions;
    private String dependencies;

    /**
     * Construct a new BuildCreator.
     * 
     * @param pathServiceSid Service Sid.
     */
    public BuildCreator(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    /**
     * The list of Asset Version Sids that are included in this Build. Optional..
     * 
     * @param assetVersions List of Asset Version Sids.
     * @return this
     */
    public BuildCreator setAssetVersions(final List<String> assetVersions) {
        this.assetVersions = assetVersions;
        return this;
    }

    /**
     * The list of Asset Version Sids that are included in this Build. Optional..
     * 
     * @param assetVersions List of Asset Version Sids.
     * @return this
     */
    public BuildCreator setAssetVersions(final String assetVersions) {
        return setAssetVersions(Promoter.listOfOne(assetVersions));
    }

    /**
     * The list of Function Version Sids that are included in this Build. Optional..
     * 
     * @param functionVersions List of Function Version Sids.
     * @return this
     */
    public BuildCreator setFunctionVersions(final List<String> functionVersions) {
        this.functionVersions = functionVersions;
        return this;
    }

    /**
     * The list of Function Version Sids that are included in this Build. Optional..
     * 
     * @param functionVersions List of Function Version Sids.
     * @return this
     */
    public BuildCreator setFunctionVersions(final String functionVersions) {
        return setFunctionVersions(Promoter.listOfOne(functionVersions));
    }

    /**
     * The list of Dependencies that are included in this Build, each described by a
     * `name` and a `version` in a JSON object. Optional..
     * 
     * @param dependencies List of Dependencies.
     * @return this
     */
    public BuildCreator setDependencies(final String dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Build
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Build create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SERVERLESS.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Builds",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Build creation failed: Unable to connect to server");
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
                null
            );
        }

        return Build.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (assetVersions != null) {
            for (String prop : assetVersions) {
                request.addPostParam("AssetVersions", prop);
            }
        }

        if (functionVersions != null) {
            for (String prop : functionVersions) {
                request.addPostParam("FunctionVersions", prop);
            }
        }

        if (dependencies != null) {
            request.addPostParam("Dependencies", dependencies);
        }
    }
}