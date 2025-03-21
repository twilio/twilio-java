/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Notify
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.notify.v1.service;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.time.LocalDate;
import java.util.List;

public class BindingReader extends Reader<Binding> {

    private String pathServiceSid;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> identity;
    private List<String> tag;
    private Long pageSize;

    public BindingReader(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    public BindingReader setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public BindingReader setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public BindingReader setIdentity(final List<String> identity) {
        this.identity = identity;
        return this;
    }

    public BindingReader setIdentity(final String identity) {
        return setIdentity(Promoter.listOfOne(identity));
    }

    public BindingReader setTag(final List<String> tag) {
        this.tag = tag;
        return this;
    }

    public BindingReader setTag(final String tag) {
        return setTag(Promoter.listOfOne(tag));
    }

    public BindingReader setPageSize(final Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<Binding> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Binding> firstPage(final TwilioRestClient client) {
        String path = "/v1/Services/{ServiceSid}/Bindings";
        path =
            path.replace(
                "{" + "ServiceSid" + "}",
                this.pathServiceSid.toString()
            );

        Request request = new Request(
            HttpMethod.GET,
            Domains.NOTIFY.toString(),
            path
        );

        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        return pageForRequest(client, request);
    }

    private Page<Binding> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Binding read failed: Unable to connect to server"
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
            "bindings",
            response.getContent(),
            Binding.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Binding> previousPage(
        final Page<Binding> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.NOTIFY.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Binding> nextPage(
        final Page<Binding> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.NOTIFY.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Binding> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
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

        if (identity != null) {
            for (String prop : identity) {
                request.addQueryParam("Identity", prop);
            }
        }
        if (tag != null) {
            for (String prop : tag) {
                request.addQueryParam("Tag", prop);
            }
        }
        if (pageSize != null) {
            request.addQueryParam("PageSize", pageSize.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
