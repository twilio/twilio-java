package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.UsageCategory;
import com.twilio.sdk.resources.UsageTrigger;

public class UsageTriggerReader extends Reader<UsageTrigger> {

    private UsageTrigger.TriggerField triggerBy;
    private UsageTrigger.Recurring recurring;
    private UsageCategory usageCategory;

    @Override
    public ResourceSet<UsageTrigger> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/Usage/Triggers.json");
        addQueryParams(request);

        Page<UsageTrigger> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<UsageTrigger> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<UsageTrigger> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<UsageTrigger> result = new Page<>();
        result.deserialize("usage_triggers", response.getContent(), UsageTrigger.class, client.getObjectMapper());

        return result;
    }

    public UsageTriggerReader byTriggerBy(final UsageTrigger.TriggerField triggerBy) {
        this.triggerBy = triggerBy;
        return this;
    }

    public UsageTriggerReader byRecurring(final UsageTrigger.Recurring recurring) {
        this.recurring = recurring;
        return this;
    }

    public UsageTriggerReader byUsageCategory(final UsageCategory usageCategory) {
        this.usageCategory = usageCategory;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (triggerBy != null) {
            request.addQueryParam("TriggerBy", triggerBy.toString());
        }
        if (recurring != null) {
            request.addQueryParam("Recurring", recurring.toString());
        }
        if (usageCategory != null) {
            request.addQueryParam("UsageCategory", usageCategory.toString());
        }
    }
}
