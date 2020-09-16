/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.fax.v1;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class FaxCreator extends Creator<Fax> {
    private final String to;
    private final URI mediaUrl;
    private Fax.Quality quality;
    private URI statusCallback;
    private String from;
    private String sipAuthUsername;
    private String sipAuthPassword;
    private Boolean storeMedia;
    private Integer ttl;

    /**
     * Construct a new FaxCreator.
     *
     * @param to The phone number to receive the fax
     * @param mediaUrl The URL of the PDF that contains the fax
     */
    public FaxCreator(final String to,
                      final URI mediaUrl) {
        this.to = to;
        this.mediaUrl = mediaUrl;
    }

    /**
     * The <a
     * href="https://www.twilio.com/docs/fax/api/fax-resource#fax-quality-values">Fax
     * Quality value</a> that describes the fax quality. Can be: `standard`,
     * `fine`, or `superfine` and defaults to `fine`..
     *
     * @param quality The quality of this fax
     * @return this
     */
    public FaxCreator setQuality(final Fax.Quality quality) {
        this.quality = quality;
        return this;
    }

    /**
     * The URL we should call using the `POST` method to send <a
     * href="https://www.twilio.com/docs/fax/api/fax-resource#fax-status-callback">status
     * information</a> to your application when the status of the fax changes..
     *
     * @param statusCallback The URL we should call to send status information to
     *                       your application
     * @return this
     */
    public FaxCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL we should call using the `POST` method to send <a
     * href="https://www.twilio.com/docs/fax/api/fax-resource#fax-status-callback">status
     * information</a> to your application when the status of the fax changes..
     *
     * @param statusCallback The URL we should call to send status information to
     *                       your application
     * @return this
     */
    public FaxCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The number the fax was sent from. Can be the phone number in <a
     * href="https://www.twilio.com/docs/glossary/what-e164">E.164</a> format or
     * the SIP `from` value. The caller ID displayed to the recipient uses this
     * value. If this is a phone number, it must be a Twilio number or a verified
     * outgoing caller id from your account. If `to` is a SIP address, this can be
     * any alphanumeric string (and also the characters `+`, `_`, `.`, and `-`),
     * which will be used in the `from` header of the SIP request..
     *
     * @param from The number the fax was sent from
     * @return this
     */
    public FaxCreator setFrom(final String from) {
        this.from = from;
        return this;
    }

    /**
     * The username to use with the `sip_auth_password` to authenticate faxes sent
     * to a SIP address..
     *
     * @param sipAuthUsername The username for SIP authentication
     * @return this
     */
    public FaxCreator setSipAuthUsername(final String sipAuthUsername) {
        this.sipAuthUsername = sipAuthUsername;
        return this;
    }

    /**
     * The password to use with `sip_auth_username` to authenticate faxes sent to a
     * SIP address..
     *
     * @param sipAuthPassword The password for SIP authentication
     * @return this
     */
    public FaxCreator setSipAuthPassword(final String sipAuthPassword) {
        this.sipAuthPassword = sipAuthPassword;
        return this;
    }

    /**
     * Whether to store a copy of the sent media on our servers for later
     * retrieval. Can be: `true` or `false` and the default is `true`..
     *
     * @param storeMedia Whether to store a copy of the sent media
     * @return this
     */
    public FaxCreator setStoreMedia(final Boolean storeMedia) {
        this.storeMedia = storeMedia;
        return this;
    }

    /**
     * How long in minutes from when the fax is initiated that we should try to
     * send the fax..
     *
     * @param ttl How long in minutes to try to send the fax
     * @return this
     */
    public FaxCreator setTtl(final Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Fax
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Fax create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.FAX.toString(),
            "/v1/Faxes"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Fax creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Fax.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (to != null) {
            request.addPostParam("To", to);
        }

        if (mediaUrl != null) {
            request.addPostParam("MediaUrl", mediaUrl.toString());
        }

        if (quality != null) {
            request.addPostParam("Quality", quality.toString());
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (from != null) {
            request.addPostParam("From", from);
        }

        if (sipAuthUsername != null) {
            request.addPostParam("SipAuthUsername", sipAuthUsername);
        }

        if (sipAuthPassword != null) {
            request.addPostParam("SipAuthPassword", sipAuthPassword);
        }

        if (storeMedia != null) {
            request.addPostParam("StoreMedia", storeMedia.toString());
        }

        if (ttl != null) {
            request.addPostParam("Ttl", ttl.toString());
        }
    }
}