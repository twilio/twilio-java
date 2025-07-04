/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Messaging
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.messaging.v1.brandregistration;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class BrandVettingReader extends Reader<BrandVetting> {

    private String pathBrandSid;
    private BrandVetting.VettingProvider vettingProvider;

    public BrandVettingReader(final String pathBrandSid) {
        this.pathBrandSid = pathBrandSid;
    }

    public BrandVettingReader setVettingProvider(
        final BrandVetting.VettingProvider vettingProvider
    ) {
        this.vettingProvider = vettingProvider;
        return this;
    }

    @Override
    public ResourceSet<BrandVetting> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<BrandVetting> firstPage(final TwilioRestClient client) {
        String path = "/v1/a2p/BrandRegistrations/{BrandSid}/Vettings";
        path =
            path.replace("{" + "BrandSid" + "}", this.pathBrandSid.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.MESSAGING.toString(),
            path
        );

        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        return pageForRequest(client, request);
    }

    private Page<BrandVetting> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "BrandVetting read failed: Unable to connect to server"
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
            "data",
            response.getContent(),
            BrandVetting.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<BrandVetting> previousPage(
        final Page<BrandVetting> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.MESSAGING.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<BrandVetting> nextPage(
        final Page<BrandVetting> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.MESSAGING.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<BrandVetting> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (vettingProvider != null) {
            request.addQueryParam(
                "VettingProvider",
                vettingProvider.toString()
            );
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
