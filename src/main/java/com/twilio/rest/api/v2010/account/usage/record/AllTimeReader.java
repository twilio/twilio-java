/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account.usage.record;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.time.LocalDate;

public class AllTimeReader extends Reader<AllTime> {

    private String pathAccountSid;
    private AllTime.Category category;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean includeSubaccounts;
    private Long pageSize;

    public AllTimeReader() {}

    public AllTimeReader(final String pathAccountSid) {
        this.pathAccountSid = pathAccountSid;
    }

    public AllTimeReader setCategory(final AllTime.Category category) {
        this.category = category;
        return this;
    }

    public AllTimeReader setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public AllTimeReader setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public AllTimeReader setIncludeSubaccounts(
        final Boolean includeSubaccounts
    ) {
        this.includeSubaccounts = includeSubaccounts;
        return this;
    }

    public AllTimeReader setPageSize(final Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<AllTime> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<AllTime> firstPage(final TwilioRestClient client) {
        String path =
            "/2010-04-01/Accounts/{AccountSid}/Usage/Records/AllTime.json";
        this.pathAccountSid =
            this.pathAccountSid == null
                ? client.getAccountSid()
                : this.pathAccountSid;
        path =
            path.replace(
                "{" + "AccountSid" + "}",
                this.pathAccountSid.toString()
            );

        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            path
        );

        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        return pageForRequest(client, request);
    }

    private Page<AllTime> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "AllTime read failed: Unable to connect to server"
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

        return Page.fromJson(
            "usage_records",
            response.getContent(),
            AllTime.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<AllTime> previousPage(
        final Page<AllTime> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<AllTime> nextPage(
        final Page<AllTime> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<AllTime> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (category != null) {
            request.addQueryParam("Category", category.toString());
        }
        if (startDate != null) {
            request.addQueryParam(
                "StartDate",
                DateConverter.dateStringFromLocalDate(startDate)
            );
        }

        if (endDate != null) {
            request.addQueryParam(
                "EndDate",
                DateConverter.dateStringFromLocalDate(endDate)
            );
        }

        if (includeSubaccounts != null) {
            request.addQueryParam(
                "IncludeSubaccounts",
                includeSubaccounts.toString()
            );
        }
        if (pageSize != null) {
            request.addQueryParam("PageSize", pageSize.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
