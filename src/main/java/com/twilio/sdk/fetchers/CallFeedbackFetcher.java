package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.CallFeedback;
import com.twilio.sdk.resources.RestException;

public class CallFeedbackFetcher extends Fetcher<CallFeedback> {

    private final String sid;

    public CallFeedbackFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public CallFeedback execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/2010-04-01/Accounts/{AccountSid}/Calls/" + sid + "/Feedback/" + sid + ".json", client.getAccountSid());
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return CallFeedback.fromJson(response.getStream(), client.getObjectMapper());
    }
}