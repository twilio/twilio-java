package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.UsageCategory;
import com.twilio.sdk.resources.UsageTrigger;

import java.net.URI;

public class UsageTriggerCreator extends Creator<UsageTrigger> {

    private final URI callbackUrl;
    private final UsageCategory usageCategory;
    private final String triggerValue;
    private HttpMethod callbackMethod;
    private UsageTrigger.Recurring recurring;
    private UsageTrigger.TriggerField triggerBy;
    private String friendlyName;

    public UsageTriggerCreator(final URI callbackUrl, final UsageCategory usageCategory, final String triggerValue) {

        this.callbackUrl = callbackUrl;
        this.usageCategory = usageCategory;
        this.triggerValue = triggerValue;
    }

    public UsageTriggerCreator setCallbackMethod(final HttpMethod callbackMethod) {
        this.callbackMethod = callbackMethod;
        return this;
    }

    public UsageTriggerCreator setRecurring(final UsageTrigger.Recurring recurring) {
        this.recurring = recurring;
        return this;
    }

    public UsageTriggerCreator setTriggerBy(final UsageTrigger.TriggerField triggerBy) {
        this.triggerBy = triggerBy;
        return this;
    }

    public UsageTriggerCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public UsageTrigger execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Usage/Triggers.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("UsageTrigger creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return UsageTrigger.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (callbackUrl != null) {

            request.addPostParam("CallbackUrl", callbackUrl.toString());

        }
        if (usageCategory != null) {

            request.addPostParam("UsageCategory", usageCategory.toString());

        }
        if (triggerValue != null) {

            request.addPostParam("TriggerValue", triggerValue);

        }
        if (callbackMethod != null) {

            request.addPostParam("CallbackMethod", callbackMethod.toString());

        }
        if (recurring != null) {

            request.addPostParam("Recurring", recurring.toString());

        }
        if (triggerBy != null) {

            request.addPostParam("TriggerBy", triggerBy.toString());

        }
        if (friendlyName != null) {

            request.addPostParam("FriendlyName", friendlyName);

        }
    }

}
