package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipIpAccessControlList;

public class SipIpAccessControlListFetcher extends Fetcher<SipIpAccessControlList> {
    private final String sid;
    
    public SipIpAccessControlListFetcher(String sid) {
        this.sid = sid;
    }
    
    @Override
    public SipIpAccessControlList execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/SIP/IpAccessControlLists/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to retrieve SIP IpAccessControlList for Sid " + sid + ": [" + response.getStatusCode() + "] " + response.getContent());
        }

        return SipIpAccessControlList.fromJson(response.getStream(), client.getObjectMapper());
    }
}
