/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.insights.v1;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
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

import java.time.ZonedDateTime;
import java.util.List;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class RoomReader extends Reader<Room> {
    private List<Room.RoomType> roomType;
    private List<Room.Codec> codec;
    private String roomName;
    private ZonedDateTime createdAfter;
    private ZonedDateTime createdBefore;

    /**
     * The room_type.
     *
     * @param roomType The room_type
     * @return this
     */
    public RoomReader setRoomType(final List<Room.RoomType> roomType) {
        this.roomType = roomType;
        return this;
    }

    /**
     * The room_type.
     *
     * @param roomType The room_type
     * @return this
     */
    public RoomReader setRoomType(final Room.RoomType roomType) {
        return setRoomType(Promoter.listOfOne(roomType));
    }

    /**
     * The codec.
     *
     * @param codec The codec
     * @return this
     */
    public RoomReader setCodec(final List<Room.Codec> codec) {
        this.codec = codec;
        return this;
    }

    /**
     * The codec.
     *
     * @param codec The codec
     * @return this
     */
    public RoomReader setCodec(final Room.Codec codec) {
        return setCodec(Promoter.listOfOne(codec));
    }

    /**
     * The room_name.
     *
     * @param roomName The room_name
     * @return this
     */
    public RoomReader setRoomName(final String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * The created_after.
     *
     * @param createdAfter The created_after
     * @return this
     */
    public RoomReader setCreatedAfter(final ZonedDateTime createdAfter) {
        this.createdAfter = createdAfter;
        return this;
    }

    /**
     * The created_before.
     *
     * @param createdBefore The created_before
     * @return this
     */
    public RoomReader setCreatedBefore(final ZonedDateTime createdBefore) {
        this.createdBefore = createdBefore;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Room ResourceSet
     */
    @Override
    public ResourceSet<Room> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Room ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Room> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.INSIGHTS.toString(),
            "/v1/Video/Rooms"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Room ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Room> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Room> nextPage(final Page<Room> page,
                               final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.INSIGHTS.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<Room> previousPage(final Page<Room> page,
                                   final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.INSIGHTS.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Room Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Room> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Room read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
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

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (roomType != null) {
            for (Room.RoomType prop : roomType) {
                request.addQueryParam("RoomType", prop.toString());
            }
        }

        if (codec != null) {
            for (Room.Codec prop : codec) {
                request.addQueryParam("Codec", prop.toString());
            }
        }

        if (roomName != null) {
            request.addQueryParam("RoomName", roomName);
        }

        if (createdAfter != null) {
            request.addQueryParam("CreatedAfter", createdAfter.toOffsetDateTime().toString());
        }

        if (createdBefore != null) {
            request.addQueryParam("CreatedBefore", createdBefore.toOffsetDateTime().toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}