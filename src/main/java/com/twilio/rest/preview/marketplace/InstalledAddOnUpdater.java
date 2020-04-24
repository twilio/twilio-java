/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.marketplace;

import com.twilio.base.Updater;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.util.Map;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class InstalledAddOnUpdater extends Updater<InstalledAddOn> {
    private final String pathSid;
    private Map<String, Object> configuration;
    private String uniqueName;

    /**
     * Construct a new InstalledAddOnUpdater.
     *
     * @param pathSid The SID of the InstalledAddOn resource to update
     */
    public InstalledAddOnUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * Valid JSON object that conform to the configuration schema exposed by the
     * associated AvailableAddOn resource. This is only required by Add-ons that
     * need to be configured.
     *
     * @param configuration The JSON object representing the configuration
     * @return this
     */
    public InstalledAddOnUpdater setConfiguration(final Map<String, Object> configuration) {
        this.configuration = configuration;
        return this;
    }

    /**
     * An application-defined string that uniquely identifies the resource. This
     * value must be unique within the Account..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return this
     */
    public InstalledAddOnUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated InstalledAddOn
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public InstalledAddOn update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/marketplace/InstalledAddOns/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("InstalledAddOn update failed: Unable to connect to server");
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

        return InstalledAddOn.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (configuration != null) {
            request.addPostParam("Configuration", Converter.mapToJson(configuration));
        }

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }
    }
}