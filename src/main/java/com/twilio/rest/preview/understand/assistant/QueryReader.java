/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.understand.assistant;

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

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class QueryReader extends Reader<Query> {
    private final String pathAssistantSid;
    private String language;
    private String modelBuild;
    private String status;

    /**
     * Construct a new QueryReader.
     *
     * @param pathAssistantSid The unique ID of the parent Assistant.
     */
    public QueryReader(final String pathAssistantSid) {
        this.pathAssistantSid = pathAssistantSid;
    }

    /**
     * An ISO language-country string of the sample..
     *
     * @param language An ISO language-country string of the sample.
     * @return this
     */
    public QueryReader setLanguage(final String language) {
        this.language = language;
        return this;
    }

    /**
     * The Model Build Sid or unique name of the Model Build to be queried..
     *
     * @param modelBuild The Model Build Sid or unique name of the Model Build to
     *                   be queried.
     * @return this
     */
    public QueryReader setModelBuild(final String modelBuild) {
        this.modelBuild = modelBuild;
        return this;
    }

    /**
     * A string that described the query status. The values can be: pending_review,
     * reviewed, discarded.
     *
     * @param status A string that described the query status. The values can be:
     *               pending_review, reviewed, discarded
     * @return this
     */
    public QueryReader setStatus(final String status) {
        this.status = status;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Query ResourceSet
     */
    @Override
    public ResourceSet<Query> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Query ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Query> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEW.toString(),
            "/understand/Assistants/" + this.pathAssistantSid + "/Queries",
            client.getRegion()
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Query ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Query> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Query> nextPage(final Page<Query> page,
                                final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.PREVIEW.toString(),
                client.getRegion()
            )
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
    public Page<Query> previousPage(final Page<Query> page,
                                    final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.PREVIEW.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Query Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Query> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Query read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                restException.getDetails(),
                null
            );
        }

        return Page.fromJson(
            "queries",
            response.getContent(),
            Query.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (language != null) {
            request.addQueryParam("Language", language);
        }

        if (modelBuild != null) {
            request.addQueryParam("ModelBuild", modelBuild.toString());
        }

        if (status != null) {
            request.addQueryParam("Status", status);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}