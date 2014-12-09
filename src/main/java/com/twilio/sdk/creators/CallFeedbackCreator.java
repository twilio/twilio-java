package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.CallFeedback;
import com.twilio.sdk.resources.RestException;

import java.util.List;

public class CallFeedbackCreator extends Creator<CallFeedback> {

    private final String callSid;
    private final int qualityScore;
    private List<CallFeedback.Issue> issues;

    public CallFeedbackCreator(final String callSid, final int qualityScore) {
        this.callSid = callSid;
        this.qualityScore = qualityScore;
    }

    public CallFeedbackCreator setIssues(final List<CallFeedback.Issue> issues) {
        this.issues = issues;
        return this;
    }

    @Override
    public CallFeedback execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Calls/" + callSid + "/Feedback.json",
                                      client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("CallFeedback creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return CallFeedback.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        request.addPostParam("QualityScore", Integer.toString(qualityScore));

        if (issues != null) {
            for (final CallFeedback.Issue issue : issues) {
                request.addPostParam("Issue", issue.toString());
            }
        }
    }

}
