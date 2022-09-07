/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Preview
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.preview.marketplace.installedaddon;

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
import com.twilio.base.Page;





/*
    * Twilio - Preview
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class InstalledAddOnExtensionReader extends Reader<InstalledAddOnExtension> {
    private String installedAddOnSid;
    private Integer pageSize;

    public InstalledAddOnExtensionReader(final String installedAddOnSid){
        this.installedAddOnSid = installedAddOnSid;
    }

    public InstalledAddOnExtensionReader setPageSize(final Integer pageSize){
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<InstalledAddOnExtension> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<InstalledAddOnExtension> firstPage(final TwilioRestClient client) {
        String path = "/marketplace/InstalledAddOns/{InstalledAddOnSid}/Extensions";
        path = path.replace("{"+"InstalledAddOnSid"+"}", this.installedAddOnSid.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEW.toString(),
            path
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    private Page<InstalledAddOnExtension> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("InstalledAddOnExtension read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Page.fromJson(
            "extensions",
            response.getContent(),
            InstalledAddOnExtension.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<InstalledAddOnExtension> previousPage(final Page<InstalledAddOnExtension> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.PREVIEW.toString())
        );
        return pageForRequest(client, request);
    }


    @Override
    public Page<InstalledAddOnExtension> nextPage(final Page<InstalledAddOnExtension> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.PREVIEW.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<InstalledAddOnExtension> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }
    private void addQueryParams(final Request request) {
        if (pageSize != null) {
    
            request.addQueryParam("PageSize", pageSize.toString());
        }
    }
}

