/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Content
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.content.v2;

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
import java.time.ZonedDateTime;
import java.util.List;

public class ContentReader extends Reader<Content> {

    private Integer pageSize;
    private String sortByDate;
    private String sortByContentName;
    private ZonedDateTime dateCreatedAfter;
    private ZonedDateTime dateCreatedBefore;
    private String contentName;
    private String content;
    private List<String> language;
    private List<String> contentType;
    private List<String> channelEligibility;

    public ContentReader() {}

    public ContentReader setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ContentReader setSortByDate(final String sortByDate) {
        this.sortByDate = sortByDate;
        return this;
    }

    public ContentReader setSortByContentName(final String sortByContentName) {
        this.sortByContentName = sortByContentName;
        return this;
    }

    public ContentReader setDateCreatedAfter(
        final ZonedDateTime dateCreatedAfter
    ) {
        this.dateCreatedAfter = dateCreatedAfter;
        return this;
    }

    public ContentReader setDateCreatedBefore(
        final ZonedDateTime dateCreatedBefore
    ) {
        this.dateCreatedBefore = dateCreatedBefore;
        return this;
    }

    public ContentReader setContentName(final String contentName) {
        this.contentName = contentName;
        return this;
    }

    public ContentReader setContent(final String content) {
        this.content = content;
        return this;
    }

    public ContentReader setLanguage(final List<String> language) {
        this.language = language;
        return this;
    }

    public ContentReader setLanguage(final String language) {
        return setLanguage(Promoter.listOfOne(language));
    }

    public ContentReader setContentType(final List<String> contentType) {
        this.contentType = contentType;
        return this;
    }

    public ContentReader setContentType(final String contentType) {
        return setContentType(Promoter.listOfOne(contentType));
    }

    public ContentReader setChannelEligibility(
        final List<String> channelEligibility
    ) {
        this.channelEligibility = channelEligibility;
        return this;
    }

    public ContentReader setChannelEligibility(
        final String channelEligibility
    ) {
        return setChannelEligibility(Promoter.listOfOne(channelEligibility));
    }

    @Override
    public ResourceSet<Content> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Content> firstPage(final TwilioRestClient client) {
        String path = "/v2/Content";

        Request request = new Request(
            HttpMethod.GET,
            Domains.CONTENT.toString(),
            path
        );

        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        return pageForRequest(client, request);
    }

    private Page<Content> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Content read failed: Unable to connect to server"
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
            "contents",
            response.getContent(),
            Content.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Content> previousPage(
        final Page<Content> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.CONTENT.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Content> nextPage(
        final Page<Content> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.CONTENT.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Content> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (pageSize != null) {
            request.addQueryParam("PageSize", pageSize.toString());
        }
        if (sortByDate != null) {
            request.addQueryParam("SortByDate", sortByDate);
        }
        if (sortByContentName != null) {
            request.addQueryParam("SortByContentName", sortByContentName);
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

        if (contentName != null) {
            request.addQueryParam("ContentName", contentName);
        }
        if (content != null) {
            request.addQueryParam("Content", content);
        }
        if (language != null) {
            for (String prop : language) {
                request.addQueryParam("Language", prop);
            }
        }
        if (contentType != null) {
            for (String prop : contentType) {
                request.addQueryParam("ContentType", prop);
            }
        }
        if (channelEligibility != null) {
            for (String prop : channelEligibility) {
                request.addQueryParam("ChannelEligibility", prop);
            }
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
