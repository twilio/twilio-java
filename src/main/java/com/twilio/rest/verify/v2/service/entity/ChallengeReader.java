/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Verify
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.verify.v2.service.entity;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class ChallengeReader extends Reader<Challenge> {

    private String pathServiceSid;
    private String pathIdentity;
    private String factorSid;
    private Challenge.ChallengeStatuses status;
    private Challenge.ListOrders order;
    private Integer pageSize;

    public ChallengeReader(
        final String pathServiceSid,
        final String pathIdentity
    ) {
        this.pathServiceSid = pathServiceSid;
        this.pathIdentity = pathIdentity;
    }

    public ChallengeReader setFactorSid(final String factorSid) {
        this.factorSid = factorSid;
        return this;
    }

    public ChallengeReader setStatus(final Challenge.ChallengeStatuses status) {
        this.status = status;
        return this;
    }

    public ChallengeReader setOrder(final Challenge.ListOrders order) {
        this.order = order;
        return this;
    }

    public ChallengeReader setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<Challenge> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Challenge> firstPage(final TwilioRestClient client) {
        String path =
            "/v2/Services/{ServiceSid}/Entities/{Identity}/Challenges";
        path =
            path.replace(
                "{" + "ServiceSid" + "}",
                this.pathServiceSid.toString()
            );
        path =
            path.replace("{" + "Identity" + "}", this.pathIdentity.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.VERIFY.toString(),
            path
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    private Page<Challenge> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Challenge read failed: Unable to connect to server"
            );
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(
                response.getStream(),
                client.getObjectMapper()
            );
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Page.fromJson(
            "challenges",
            response.getContent(),
            Challenge.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Challenge> previousPage(
        final Page<Challenge> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.VERIFY.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Challenge> nextPage(
        final Page<Challenge> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.VERIFY.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Challenge> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (factorSid != null) {
            request.addQueryParam("FactorSid", factorSid);
        }
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }
        if (order != null) {
            request.addQueryParam("Order", order.toString());
        }
        if (pageSize != null) {
            request.addQueryParam("PageSize", pageSize.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
