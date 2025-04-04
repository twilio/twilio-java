/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Ip_messaging
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.ipmessaging.v1.service.channel;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.util.List;

public class InviteReader extends Reader<Invite> {

    private String pathServiceSid;
    private String pathChannelSid;
    private List<String> identity;
    private Long pageSize;

    public InviteReader(
        final String pathServiceSid,
        final String pathChannelSid
    ) {
        this.pathServiceSid = pathServiceSid;
        this.pathChannelSid = pathChannelSid;
    }

    public InviteReader setIdentity(final List<String> identity) {
        this.identity = identity;
        return this;
    }

    public InviteReader setIdentity(final String identity) {
        return setIdentity(Promoter.listOfOne(identity));
    }

    public InviteReader setPageSize(final Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<Invite> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Invite> firstPage(final TwilioRestClient client) {
        String path = "/v1/Services/{ServiceSid}/Channels/{ChannelSid}/Invites";
        path =
            path.replace(
                "{" + "ServiceSid" + "}",
                this.pathServiceSid.toString()
            );
        path =
            path.replace(
                "{" + "ChannelSid" + "}",
                this.pathChannelSid.toString()
            );

        Request request = new Request(
            HttpMethod.GET,
            Domains.IPMESSAGING.toString(),
            path
        );

        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        return pageForRequest(client, request);
    }

    private Page<Invite> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Invite read failed: Unable to connect to server"
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
            "invites",
            response.getContent(),
            Invite.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Invite> previousPage(
        final Page<Invite> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.IPMESSAGING.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Invite> nextPage(
        final Page<Invite> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.IPMESSAGING.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Invite> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (identity != null) {
            for (String prop : identity) {
                request.addQueryParam("Identity", prop);
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
