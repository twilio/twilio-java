/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1;

import com.twilio.base.Updater;
import com.twilio.converter.Converter;
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
import java.util.Map;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class AssistantUpdater extends Updater<Assistant> {
    private final String pathSid;
    private String friendlyName;
    private Boolean logQueries;
    private String uniqueName;
    private URI callbackUrl;
    private String callbackEvents;
    private Map<String, Object> styleSheet;
    private Map<String, Object> defaults;
    private String developmentStage;

    /**
     * Construct a new AssistantUpdater.
     *
     * @param pathSid The unique string that identifies the resource
     */
    public AssistantUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * A descriptive string that you create to describe the resource. It is not
     * unique and can be up to 255 characters long..
     *
     * @param friendlyName A string to describe the resource
     * @return this
     */
    public AssistantUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Whether queries should be logged and kept after training. Can be: `true` or
     * `false` and defaults to `true`. If `true`, queries are stored for 30 days,
     * and then deleted. If `false`, no queries are stored..
     *
     * @param logQueries Whether queries should be logged and kept after training
     * @return this
     */
    public AssistantUpdater setLogQueries(final Boolean logQueries) {
        this.logQueries = logQueries;
        return this;
    }

    /**
     * An application-defined string that uniquely identifies the resource. It can
     * be used as an alternative to the `sid` in the URL path to address the
     * resource. The first 64 characters must be unique..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return this
     */
    public AssistantUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * Reserved..
     *
     * @param callbackUrl Reserved
     * @return this
     */
    public AssistantUpdater setCallbackUrl(final URI callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    /**
     * Reserved..
     *
     * @param callbackUrl Reserved
     * @return this
     */
    public AssistantUpdater setCallbackUrl(final String callbackUrl) {
        return setCallbackUrl(Promoter.uriFromString(callbackUrl));
    }

    /**
     * Reserved..
     *
     * @param callbackEvents Reserved
     * @return this
     */
    public AssistantUpdater setCallbackEvents(final String callbackEvents) {
        this.callbackEvents = callbackEvents;
        return this;
    }

    /**
     * The JSON string that defines the Assistant's <a
     * href="https://www.twilio.com/docs/autopilot/api/assistant/stylesheet">style
     * sheet</a>.
     *
     * @param styleSheet A JSON string that defines the Assistant's style sheet
     * @return this
     */
    public AssistantUpdater setStyleSheet(final Map<String, Object> styleSheet) {
        this.styleSheet = styleSheet;
        return this;
    }

    /**
     * A JSON object that defines the Assistant's <a
     * href="https://www.twilio.com/docs/autopilot/api/assistant/defaults">default
     * tasks</a> for various scenarios, including initiation actions and fallback
     * tasks..
     *
     * @param defaults A JSON object that defines the Assistant's [default
     *                 tasks](https://www.twilio.com/docs/autopilot/api/assistant/defaults)
     *                 for various scenarios
     * @return this
     */
    public AssistantUpdater setDefaults(final Map<String, Object> defaults) {
        this.defaults = defaults;
        return this;
    }

    /**
     * A string describing the state of the assistant..
     *
     * @param developmentStage A string describing the state of the assistant.
     * @return this
     */
    public AssistantUpdater setDevelopmentStage(final String developmentStage) {
        this.developmentStage = developmentStage;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Assistant
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Assistant update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.AUTOPILOT.toString(),
            "/v1/Assistants/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Assistant update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Assistant.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (logQueries != null) {
            request.addPostParam("LogQueries", logQueries.toString());
        }

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (callbackUrl != null) {
            request.addPostParam("CallbackUrl", callbackUrl.toString());
        }

        if (callbackEvents != null) {
            request.addPostParam("CallbackEvents", callbackEvents);
        }

        if (styleSheet != null) {
            request.addPostParam("StyleSheet", Converter.mapToJson(styleSheet));
        }

        if (defaults != null) {
            request.addPostParam("Defaults", Converter.mapToJson(defaults));
        }

        if (developmentStage != null) {
            request.addPostParam("DevelopmentStage", developmentStage);
        }
    }
}
