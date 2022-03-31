/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.trunking.v1;

import com.twilio.base.Updater;
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

public class TrunkUpdater extends Updater<Trunk> {
    private final String pathSid;
    private String friendlyName;
    private String domainName;
    private URI disasterRecoveryUrl;
    private HttpMethod disasterRecoveryMethod;
    private Trunk.TransferSetting transferMode;
    private Boolean secure;
    private Boolean cnamLookupEnabled;
    private Trunk.TransferCallerId transferCallerId;

    /**
     * Construct a new TrunkUpdater.
     *
     * @param pathSid The unique string that identifies the resource
     */
    public TrunkUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * A descriptive string that you create to describe the resource. It can be up
     * to 64 characters long..
     *
     * @param friendlyName A string to describe the resource
     * @return this
     */
    public TrunkUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The unique address you reserve on Twilio to which you route your SIP traffic.
     * Domain names can contain letters, digits, and `-` and must end with
     * `pstn.twilio.com`. See <a
     * href="https://www.twilio.com/docs/sip-trunking#termination">Termination
     * Settings</a> for more information..
     *
     * @param domainName The unique address you reserve on Twilio to which you
     *                   route your SIP traffic
     * @return this
     */
    public TrunkUpdater setDomainName(final String domainName) {
        this.domainName = domainName;
        return this;
    }

    /**
     * The URL we should call using the `disaster_recovery_method` if an error
     * occurs while sending SIP traffic towards the configured Origination URL. We
     * retrieve TwiML from the URL and execute the instructions like any other
     * normal TwiML call. See <a
     * href="https://www.twilio.com/docs/sip-trunking#disaster-recovery">Disaster
     * Recovery</a> for more information..
     *
     * @param disasterRecoveryUrl The HTTP URL that we should call if an error
     *                            occurs while sending SIP traffic towards your
     *                            configured Origination URL
     * @return this
     */
    public TrunkUpdater setDisasterRecoveryUrl(final URI disasterRecoveryUrl) {
        this.disasterRecoveryUrl = disasterRecoveryUrl;
        return this;
    }

    /**
     * The URL we should call using the `disaster_recovery_method` if an error
     * occurs while sending SIP traffic towards the configured Origination URL. We
     * retrieve TwiML from the URL and execute the instructions like any other
     * normal TwiML call. See <a
     * href="https://www.twilio.com/docs/sip-trunking#disaster-recovery">Disaster
     * Recovery</a> for more information..
     *
     * @param disasterRecoveryUrl The HTTP URL that we should call if an error
     *                            occurs while sending SIP traffic towards your
     *                            configured Origination URL
     * @return this
     */
    public TrunkUpdater setDisasterRecoveryUrl(final String disasterRecoveryUrl) {
        return setDisasterRecoveryUrl(Promoter.uriFromString(disasterRecoveryUrl));
    }

    /**
     * The HTTP method we should use to call the `disaster_recovery_url`. Can be:
     * `GET` or `POST`..
     *
     * @param disasterRecoveryMethod The HTTP method we should use to call the
     *                               disaster_recovery_url
     * @return this
     */
    public TrunkUpdater setDisasterRecoveryMethod(final HttpMethod disasterRecoveryMethod) {
        this.disasterRecoveryMethod = disasterRecoveryMethod;
        return this;
    }

    /**
     * The call transfer settings for the trunk. Can be: `enable-all`, `sip-only`
     * and `disable-all`. See <a
     * href="https://www.twilio.com/docs/sip-trunking/call-transfer">Transfer</a>
     * for more information..
     *
     * @param transferMode The call transfer settings for the trunk
     * @return this
     */
    public TrunkUpdater setTransferMode(final Trunk.TransferSetting transferMode) {
        this.transferMode = transferMode;
        return this;
    }

    /**
     * Whether Secure Trunking is enabled for the trunk. If enabled, all calls going
     * through the trunk will be secure using SRTP for media and TLS for signaling.
     * If disabled, then RTP will be used for media. See <a
     * href="https://www.twilio.com/docs/sip-trunking#securetrunking">Secure
     * Trunking</a> for more information..
     *
     * @param secure Whether Secure Trunking is enabled for the trunk
     * @return this
     */
    public TrunkUpdater setSecure(final Boolean secure) {
        this.secure = secure;
        return this;
    }

    /**
     * Whether Caller ID Name (CNAM) lookup should be enabled for the trunk. If
     * enabled, all inbound calls to the SIP Trunk from the United States and Canada
     * automatically perform a CNAM Lookup and display Caller ID data on your phone.
     * See <a href="https://www.twilio.com/docs/sip-trunking#CNAM">CNAM Lookups</a>
     * for more information..
     *
     * @param cnamLookupEnabled Whether Caller ID Name (CNAM) lookup should be
     *                          enabled for the trunk
     * @return this
     */
    public TrunkUpdater setCnamLookupEnabled(final Boolean cnamLookupEnabled) {
        this.cnamLookupEnabled = cnamLookupEnabled;
        return this;
    }

    /**
     * Caller Id for transfer target. Can be: `from-transferee` (default) or
     * `from-transferor`..
     *
     * @param transferCallerId Caller Id for transfer target
     * @return this
     */
    public TrunkUpdater setTransferCallerId(final Trunk.TransferCallerId transferCallerId) {
        this.transferCallerId = transferCallerId;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Trunk
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Trunk update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.TRUNKING.toString(),
            "/v1/Trunks/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Trunk update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Trunk.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (domainName != null) {
            request.addPostParam("DomainName", domainName);
        }

        if (disasterRecoveryUrl != null) {
            request.addPostParam("DisasterRecoveryUrl", disasterRecoveryUrl.toString());
        }

        if (disasterRecoveryMethod != null) {
            request.addPostParam("DisasterRecoveryMethod", disasterRecoveryMethod.toString());
        }

        if (transferMode != null) {
            request.addPostParam("TransferMode", transferMode.toString());
        }

        if (secure != null) {
            request.addPostParam("Secure", secure.toString());
        }

        if (cnamLookupEnabled != null) {
            request.addPostParam("CnamLookupEnabled", cnamLookupEnabled.toString());
        }

        if (transferCallerId != null) {
            request.addPostParam("TransferCallerId", transferCallerId.toString());
        }
    }
}