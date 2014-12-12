package com.twilio.sdk.creators;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.CallFeedbackSummary;
import com.twilio.sdk.resources.RestException;
import org.joda.time.LocalDate;

import java.net.URI;
import java.net.URISyntaxException;

public class CallFeedbackSummaryCreator extends Creator<CallFeedbackSummary> {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private Boolean includeSubaccounts;
    private HttpMethod statusCallbackMethod;
    private URI statusCallback;

    public CallFeedbackSummaryCreator(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CallFeedbackSummaryCreator setIncludeSubaccounts(final Boolean includeSubaccounts) {
        this.includeSubaccounts = includeSubaccounts;
        return this;
    }

    public CallFeedbackSummaryCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    public CallFeedbackSummaryCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public CallFeedbackSummaryCreator setStatusCallback(final String statusCallback) {
        try {
            this.statusCallback = new URI(statusCallback);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    @Override
    public CallFeedbackSummary execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/2010-04-01/Accounts/{AccountSid}/Calls/FeedbackSummary.json", client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("CallFeedbackSummary creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return CallFeedbackSummary.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (startDate != null) {
            request.addPostParam("StartDate", Twilio.DATE_FORMATTER.print(startDate));
        }
        if (endDate != null) {
            request.addPostParam("EndDate", Twilio.DATE_FORMATTER.print(endDate));
        }
        if (includeSubaccounts != null) {
            request.addPostParam("IncludeSubaccounts", includeSubaccounts.toString());
        }
        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }
    }

}
