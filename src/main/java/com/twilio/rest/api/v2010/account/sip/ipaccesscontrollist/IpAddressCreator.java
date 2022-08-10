/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.sip.ipaccesscontrollist;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class IpAddressCreator extends Creator<IpAddress> {
    private String pathAccountSid;
    private final String pathIpAccessControlListSid;
    private final String friendlyName;
    private final String ipAddress;
    private Integer cidrPrefixLength;

    /**
     * Construct a new IpAddressCreator.
     *
     * @param pathIpAccessControlListSid The IpAccessControlList Sid with which to
     *                                   associate the created IpAddress resource
     * @param friendlyName A human readable descriptive text for this resource, up
     *                     to 255 characters long.
     * @param ipAddress An IP address in dotted decimal notation from which you
     *                  want to accept traffic. Any SIP requests from this IP
     *                  address will be allowed by Twilio. IPv4 only supported
     *                  today.
     */
    public IpAddressCreator(final String pathIpAccessControlListSid,
                            final String friendlyName,
                            final String ipAddress) {
        this.pathIpAccessControlListSid = pathIpAccessControlListSid;
        this.friendlyName = friendlyName;
        this.ipAddress = ipAddress;
    }

    /**
     * Construct a new IpAddressCreator.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathIpAccessControlListSid The IpAccessControlList Sid with which to
     *                                   associate the created IpAddress resource
     * @param friendlyName A human readable descriptive text for this resource, up
     *                     to 255 characters long.
     * @param ipAddress An IP address in dotted decimal notation from which you
     *                  want to accept traffic. Any SIP requests from this IP
     *                  address will be allowed by Twilio. IPv4 only supported
     *                  today.
     */
    public IpAddressCreator(final String pathAccountSid,
                            final String pathIpAccessControlListSid,
                            final String friendlyName,
                            final String ipAddress) {
        this.pathAccountSid = pathAccountSid;
        this.pathIpAccessControlListSid = pathIpAccessControlListSid;
        this.friendlyName = friendlyName;
        this.ipAddress = ipAddress;
    }

    /**
     * An integer representing the length of the CIDR prefix to use with this IP
     * address when accepting traffic. By default the entire IP address is used..
     *
     * @param cidrPrefixLength An integer representing the length of the CIDR
     *                         prefix to use with this IP address when accepting
     *                         traffic. By default the entire IP address is used.
     * @return this
     */
    public IpAddressCreator setCidrPrefixLength(final Integer cidrPrefixLength) {
        this.cidrPrefixLength = cidrPrefixLength;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created IpAddress
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public IpAddress create(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/SIP/IpAccessControlLists/" + this.pathIpAccessControlListSid + "/IpAddresses.json"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("IpAddress creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return IpAddress.fromJson(response.getStream(), client.getObjectMapper());
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

        if (ipAddress != null) {
            request.addPostParam("IpAddress", ipAddress);
        }

        if (cidrPrefixLength != null) {
            request.addPostParam("CidrPrefixLength", cidrPrefixLength.toString());
        }
    }
}