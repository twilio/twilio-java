package com.twilio.sdk.fetchers.taskrouter.v1.workspace;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.Worker;

public class WorkerFetcher extends Fetcher<Worker> {
    private final String workspaceSid;
    private final String sid;

    /**
     * Construct a new WorkerFetcher
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     */
    public WorkerFetcher(final String workspaceSid, final String sid) {
        this.workspaceSid = workspaceSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Worker
     */
    @Override
    public Worker execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.TASKROUTER,
            "/v1/Workspaces/" + this.workspaceSid + "/Workers/" + this.sid + "",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Worker fetch failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Worker.fromJson(response.getStream(), client.getObjectMapper());
    }
}