package com.twilio.sdk.taskrouter;

public class TaskRouterWorkerCapability extends TaskRouterCapability {

    private final String reservationsUrl;
    private final String activityUrl;

    /**
     * Create a new Capability object to authorize worker clients to interact
     * with the TaskRouter service.
     *
     * @param accountSid
     *            Account to authorize actions for
     * @param authToken
     *            Auth token for the account. Used to sign tokens and will not
     *            be included in the generated tokens.
     * @param workspaceSid
     *            Workspace to authorize tokens for.
     * @param workerSid
     *            Worker to create tokens for.
     */
    public TaskRouterWorkerCapability(final String accountSid, final String authToken, final String workspaceSid, final String workerSid) {
        super(accountSid, authToken, workspaceSid, workerSid);
        this.reservationsUrl = this.baseUrl + "/Tasks/**";
        this.activityUrl = this.baseUrl + "/Activities";

        // add permissions to fetch the list of activities and list of worker
        // reservations
        this.allow(activityUrl, "GET", null, null);
        this.allow(reservationsUrl, "GET", null, null);
    }

    @Override
    protected void setupResource() {
        this.resourceUrl = this.baseUrl + "/Workers/" + this.channelId;
    }

    /**
     * Allow a worker to update its own activity status
     */
    public void allowActivityUpdates() {
        final Policy policy = new Policy(this.resourceUrl, "POST", true);
        policy.postFilter.put("ActivitySid", FilterRequirement.REQUIRED);
        policies.add(policy);
    }

    /**
     * Allow a worker to update assigned reservations
     */
    public void allowReservationUpdates() {
        final Policy policy = new Policy(this.reservationsUrl, "POST", true);
        policies.add(policy);
    }

}
