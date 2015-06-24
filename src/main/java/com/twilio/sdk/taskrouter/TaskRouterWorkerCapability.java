package com.twilio.sdk.taskrouter;

public class TaskRouterWorkerCapability extends TaskRouterCapability {

    private final String reservationsUrl;
    private final String activityUrl;

    public TaskRouterWorkerCapability(final String accountSid, final String authToken, final String workspaceSid, final String workerSid) throws Exception {
        super(accountSid, authToken, workspaceSid, workerSid);
        this.reservationsUrl = this.baseUrl + "/Tasks/**";
        this.activityUrl = this.baseUrl + "/Activities";

        // add permissions to fetch the list of activities and list of worker
        // reservations
        this.allow(activityUrl, "GET", null, null);
        this.allow(reservationsUrl, "GET", null, null);

    }

    public void allowActivityUpdates() {
        final Policy policy = new Policy(this.resourceUrl, "POST", true);
        policy.postFilter.put("ActivitySid", FilterRequirement.REQUIRED);
        policies.add(policy);
    }

    public void allowReservationUpdates() {

        final Policy policy = new Policy(this.reservationsUrl, "POST", true);
        policies.add(policy);
    }

}
