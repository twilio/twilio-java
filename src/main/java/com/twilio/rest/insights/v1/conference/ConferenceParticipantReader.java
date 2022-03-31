/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.insights.v1.conference;

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

public class ConferenceParticipantReader extends Reader<ConferenceParticipant> {
    private final String pathConferenceSid;
    private String participantSid;
    private String label;
    private String events;

    /**
     * Construct a new ConferenceParticipantReader.
     *
     * @param pathConferenceSid Conference SID.
     */
    public ConferenceParticipantReader(final String pathConferenceSid) {
        this.pathConferenceSid = pathConferenceSid;
    }

    /**
     * The unique SID identifier of the Participant..
     *
     * @param participantSid Participant SID.
     * @return this
     */
    public ConferenceParticipantReader setParticipantSid(final String participantSid) {
        this.participantSid = participantSid;
        return this;
    }

    /**
     * User-specified label for a participant..
     *
     * @param label User-specified label for a participant.
     * @return this
     */
    public ConferenceParticipantReader setLabel(final String label) {
        this.label = label;
        return this;
    }

    /**
     * Conference events generated by application or participant activity; e.g.
     * `hold`, `mute`, etc..
     *
     * @param events Conference events generated by application or participant
     *               activity.
     * @return this
     */
    public ConferenceParticipantReader setEvents(final String events) {
        this.events = events;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return ConferenceParticipant ResourceSet
     */
    @Override
    public ResourceSet<ConferenceParticipant> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return ConferenceParticipant ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<ConferenceParticipant> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.INSIGHTS.toString(),
            "/v1/Conferences/" + this.pathConferenceSid + "/Participants"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return ConferenceParticipant ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<ConferenceParticipant> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<ConferenceParticipant> nextPage(final Page<ConferenceParticipant> page,
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
    public Page<ConferenceParticipant> previousPage(final Page<ConferenceParticipant> page,
                                                    final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.INSIGHTS.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of ConferenceParticipant Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<ConferenceParticipant> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ConferenceParticipant read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "participants",
            response.getContent(),
            ConferenceParticipant.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (participantSid != null) {
            request.addQueryParam("ParticipantSid", participantSid);
        }

        if (label != null) {
            request.addQueryParam("Label", label);
        }

        if (events != null) {
            request.addQueryParam("Events", events);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}