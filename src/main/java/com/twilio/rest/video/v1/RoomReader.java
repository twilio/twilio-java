/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Video
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.video.v1;

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
import java.time.ZonedDateTime;

public class RoomReader extends Reader<Room> {

    private Room.RoomStatus status;
    private String uniqueName;
    private ZonedDateTime dateCreatedAfter;
    private ZonedDateTime dateCreatedBefore;
    private Integer pageSize;

    public RoomReader() {}

    public RoomReader setStatus(final Room.RoomStatus status) {
        this.status = status;
        return this;
    }

    public RoomReader setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    public RoomReader setDateCreatedAfter(
        final ZonedDateTime dateCreatedAfter
    ) {
        this.dateCreatedAfter = dateCreatedAfter;
        return this;
    }

    public RoomReader setDateCreatedBefore(
        final ZonedDateTime dateCreatedBefore
    ) {
        this.dateCreatedBefore = dateCreatedBefore;
        return this;
    }

    public RoomReader setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<Room> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Room> firstPage(final TwilioRestClient client) {
        String path = "/v1/Rooms";

        Request request = new Request(
            HttpMethod.GET,
            Domains.VIDEO.toString(),
            path
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    private Page<Room> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Room read failed: Unable to connect to server"
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
            "rooms",
            response.getContent(),
            Room.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Room> previousPage(
        final Page<Room> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.VIDEO.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Room> nextPage(
        final Page<Room> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.VIDEO.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Room> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }
        if (uniqueName != null) {
            request.addQueryParam("UniqueName", uniqueName);
        }
        if (dateCreatedAfter != null) {
            request.addQueryParam(
                "DateCreatedAfter",
                dateCreatedAfter.toInstant().toString()
            );
        }

        if (dateCreatedBefore != null) {
            request.addQueryParam(
                "DateCreatedBefore",
                dateCreatedBefore.toInstant().toString()
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
