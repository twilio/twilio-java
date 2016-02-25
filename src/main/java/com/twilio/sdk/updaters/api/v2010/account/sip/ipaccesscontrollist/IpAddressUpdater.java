package com.twilio.sdk.updaters.api.v2010.account.sip.ipaccesscontrollist;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.sip.ipaccesscontrollist.IpAddress;
import com.twilio.sdk.updaters.Updater;

public class IpAddressUpdater extends Updater<IpAddress> {
    private final String accountSid;
    private final String ipAccessControlListSid;
    private final String sid;
    private final String ipAddress;
    private final String friendlyName;

    /**
     * Construct a new IpAddressUpdater.
     * 
     * @param accountSid The account_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @param sid The sid
     * @param ipAddress The ip_address
     * @param friendlyName The friendly_name
     */
    public IpAddressUpdater(final String accountSid, 
                            final String ipAccessControlListSid, 
                            final String sid, 
                            final String ipAddress, 
                            final String friendlyName) {
        this.accountSid = accountSid;
        this.ipAccessControlListSid = ipAccessControlListSid;
        this.sid = sid;
        this.ipAddress = ipAddress;
        this.friendlyName = friendlyName;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated IpAddress
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public IpAddress execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/SIP/IpAccessControlLists/" + this.ipAccessControlListSid + "/IpAddresses/" + this.sid + ".json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("IpAddress update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return IpAddress.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (ipAddress != null) {
            request.addPostParam("IpAddress", ipAddress);
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }
}