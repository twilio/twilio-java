package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.CallFeedback;
import com.twilio.sdk.resources.RestException;

import java.util.List;

public class CallFeedbackUpdater extends Updater<CallFeedback> {

    private final String sid;
    private final int qualityScore;
    private List<CallFeedback.Issue> issues;

    public CallFeedbackUpdater(final String sid, final int qualityScore) {
        this.sid = sid;
        this.qualityScore = qualityScore;
    }

    public CallFeedbackUpdater(final CallFeedback target) {
        this(target.getSid(), target.getQualityScore());
    }

    public CallFeedbackUpdater setIssues(final List<CallFeedback.Issue> issues) {
        this.issues = issues;
        return this;
    }

    @Override
    public CallFeedback execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Calls/" + sid + "/Feedback.json",
                                      client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("CallFeedback update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
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
