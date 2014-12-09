package com.twilio.sdk.readers;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.UsageCategory;
import com.twilio.sdk.resources.UsageRecord;
import com.twilio.sdk.resources.UsageRecordSubResource;
import org.joda.time.LocalDate;

public class UsageRecordReader extends Reader<UsageRecord> {

    private UsageCategory category;
    private LocalDate startDate;
    private LocalDate endDate;
    private UsageRecordSubResource usageRecordSubResource;

    @Override
    public ResourceSet<UsageRecord> execute(final TwilioRestClient client) {
        String uri = "/Accounts/{AccountSid}/Usage/Records.json";
        if (usageRecordSubResource != null) {
            uri = "/Accounts/{AccountSid}/Usage/Records/" + usageRecordSubResource + ".json";
        }
        Request request = new Request(HttpMethod.GET, uri);
        addQueryParams(request);

        Page<UsageRecord> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<UsageRecord> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<UsageRecord> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<UsageRecord> result = new Page<>();
        result.deserialize("usage_records", response.getContent(), UsageRecord.class, client.getObjectMapper());

        return result;
    }

    public UsageRecordReader byCategory(final UsageCategory category) {
        this.category = category;
        return this;
    }

    public UsageRecordReader byStartDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public UsageRecordReader byEndDate(final LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public UsageRecordReader bySubResource(final UsageRecordSubResource usageRecordSubResource) {
        this.usageRecordSubResource = usageRecordSubResource;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (category != null) {
            request.addQueryParam("Category", category.toString());
        }
        if (startDate != null) {
            request.addQueryParam("StartDate", Twilio.DATE_FORMATTER.print(startDate));
        }
        if (endDate != null) {
            request.addQueryParam("EndDate", Twilio.DATE_FORMATTER.print(endDate));
        }
    }
}
